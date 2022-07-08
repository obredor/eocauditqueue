package co.com.claro.inspira.auditqueue.jms;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import co.com.claro.inspira.audit.jms.util.Utilities;
import co.com.claro.inspira.audit.jms.util.UtilitiesEocColaJms;
import co.com.claro.inspira.audit.persistence.entities.EocColaJms;
import co.com.claro.inspira.ecmparser.web.persistence.exception.PersistAuditException;
import co.com.claro.inspira.eocauditqueue.dto.EocAuditQueueRequest;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueueReader implements MessageListener {
  private static QueueReader instance;
  
  private QueueConnectionFactory queueConnectionFactory;
  
  private QueueConnection queueConnection;
  
  private QueueSession queueSession;
  
  private QueueReceiver queueReceiver;
  
  private Queue queue;
  
  public int numMessagesQueue;
  
  public int countQueue;
  
  private final int cantidadReintentos;
  
  private final int tiempoReintentos;
  
  private final LoadAuditQueueConfigProperties config = LoadAuditQueueConfigProperties.getInstance();
  
  private static final Logger LOGGER = LogManager.getLogger();
  
  private Connection connection;
  
  private QueueReader() {
    this.tiempoReintentos = 
      Integer.valueOf(this.config.findProperty(EOperationProperty.TIEMPO_REINTENTOS)).intValue();
    this.cantidadReintentos = 
      Integer.valueOf(this.config.findProperty(EOperationProperty.REINTENTOS)).intValue();
  }
  
  public static synchronized QueueReader getInstance() {
    if (instance == null)
      instance = new QueueReader(); 
    return instance;
  }
  
  public void onMessage(Message msg) {
    String msgText = null;
    int reintento = 1;
    try {
      if (msg instanceof TextMessage) {
        msgText = ((TextMessage)msg).getText();
      } else {
        msgText = msg.toString();
      } 
      EocAuditQueueRequest eocAuditQueueRequest = Utilities.convertirJsonPojo(msgText);
      String dateFormat = this.config.findProperty(EOperationProperty.AUDITORIA_DATEFORMAT);
      UtilitiesEocColaJms.dateFormat = dateFormat;
      EocColaJms entidad = UtilitiesEocColaJms.convertirRequestEntidad(eocAuditQueueRequest);
      persistirMensaje(entidad, reintento, msgText);
      synchronized (this) {
        this.countQueue++;
        notifyAll();
      } 
      LOGGER.info("Message recibido " + msgText);
    } catch (JsonSyntaxException e) {
      LOGGER.error("Error en serializaion de mensaje: " + e);
    } catch (JMSException|InterruptedException|NamingException|IOException ex) {
      LOGGER.error("Error en ejecucion: " + ex);
    } catch (IllegalArgumentException|NullPointerException e) {
      LOGGER.error("Excepcion: " + e);
      if (reintento == 1)
        try {
          postMessage(msgText);
        } catch (JMSException|NamingException|IOException ex) {
          LOGGER.error("Reintento excepcion - postmessage: " + ex);
        }  
    } 
  }
  
  public void init(Context context, String queueName, Connection connection) throws NamingException, JMSException, SQLException, SecurityException {
    this.connection = connection;
    this
      .queueConnectionFactory = (QueueConnectionFactory)context.lookup(this.config.findProperty(EOperationProperty.JMS_FACTORY));
    this.queueConnection = this.queueConnectionFactory.createQueueConnection();
    this
      .queueSession = this.queueConnection.createQueueSession(false, 1);
    this.queue = (Queue)context.lookup(queueName);
    this.queueReceiver = this.queueSession.createReceiver(this.queue);
    this.queueReceiver.setMessageListener(this);
    this.queueConnection.start();
    LOGGER.info("QueueConnection Started ");
  }
  
  public void countMessagesQueue() {
    try {
      QueueBrowser queueBrowser = this.queueSession.createBrowser(this.queue);
      Enumeration e = queueBrowser.getEnumeration();
      this.numMessagesQueue = 0;
      this.countQueue = 0;
      while (e.hasMoreElements()) {
        e.nextElement();
        this.numMessagesQueue++;
      } 
    } catch (JMSException ex) {
      LOGGER.info("Error JMSException ->" + ex.getMessage());
    } 
  }
  
  public void persistirMensaje(EocColaJms entidad, int reintento, String msg) throws InterruptedException, NamingException, JMSException, IOException {
    try {
      String queryPersistAuditoria = this.config.findProperty(EOperationProperty.AUDITORIA_INSERT_AUDIT);
      String observacion = this.config.findProperty(EOperationProperty.AUDITORIA_OBSERVACION);
      Utilities.observacion = observacion;
      String idMensaje = Utilities.generarAuditoriaCola(reintento, queryPersistAuditoria, this.connection);
      entidad.setIdmensaje(idMensaje);
      LOGGER.info("Creacion exitosa EOC_AUDITORIA_COLA: IDMENSAJE -> " + idMensaje);
      UtilitiesEocColaJms.consumirDB(entidad, reintento, this.connection);
      LOGGER.info("Creacion exitosa EOC_COLA_JMS, IDMENSAJE " + idMensaje);
    } catch (PersistAuditException ex) {
      LOGGER.error("Error en insercion de mensaje: " + ex);
      LOGGER.info("Mensaje ingresado: " + msg);
    } catch (SQLException|co.com.claro.inspira.utilities.common.exception.dto.BackendException|co.com.claro.inspira.utilities.common.exception.dto.InternalException ex) {
      LOGGER.error("Error en base de datos: " + ex);
      if (reintento <= this.cantidadReintentos) {
        LOGGER.warn("Reintento # " + reintento);
        Thread.sleep((this.tiempoReintentos * 6000));
        persistirMensaje(entidad, ++reintento, msg);
      } 
    } 
  }
  
  public void postMessage(String msg) throws NamingException, JMSException, IOException {
    try {
      String queuePostName = this.config.findProperty(EOperationProperty.QUEUE);
      QueuePost queuePost = QueuePost.getInstance();
      InitialContext initialContext = QueuePost.getInitialContext();
      queuePost.init(initialContext, queuePostName);
      QueuePost.sendToServer(queuePost, msg);
      queuePost.close();
    } catch (NamingException|JMSException|IOException ex) {
      LOGGER.error("Error encolando Mensaje : " + ex.getLocalizedMessage());
      throw ex;
    } 
  }
  
  public void close() throws JMSException, SecurityException {
    if (this.queueReceiver != null)
      this.queueReceiver.close(); 
    if (this.queueSession != null)
      this.queueSession.close(); 
    if (this.queueConnection != null)
      this.queueConnection.close(); 
  }
  
  public void closeContext() throws SQLException, JMSException, SecurityException {
    if (this.connection != null)
      this.connection.close(); 
    close();
    LOGGER.info("Connection DB closed");
  }
  
  public InitialContext getInitialContext() throws NamingException {
    Hashtable<String, String> env = new Hashtable<>();
    env.put("java.naming.factory.initial", this.config
        .findProperty(EOperationProperty.JNDI_FACTORY));
    env.put("java.naming.provider.url", this.config
        .findProperty(EOperationProperty.SERVER));
    env.put("java.naming.security.principal", this.config
        .findProperty(EOperationProperty.USERNAME));
    env.put("java.naming.security.credentials", this.config
        .findProperty(EOperationProperty.PASSWORD));
    return new InitialContext(env);
  }
}
