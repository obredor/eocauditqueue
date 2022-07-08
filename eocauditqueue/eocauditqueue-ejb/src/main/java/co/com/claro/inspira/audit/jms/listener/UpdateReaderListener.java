package co.com.claro.inspira.audit.jms.listener;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import co.com.claro.inspira.auditqueue.jms.QueueReader;
import co.com.claro.inspira.ecmparser.web.persistence.exception.ConnectionManager;
import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import co.com.claro.inspira.utilities.common.logger.LoggerConfig;
import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.Logger;

@Startup
@Singleton
public class UpdateReaderListener {
  private LoadAuditQueueConfigProperties config;
  
  private QueueReader queueReader;
  
  private static final ConnectionManager CONNECTION_MANAGER = ConnectionManager.getInstance();
  
  private Logger logger;
  
  private Thread thread = null;
  
  private void init() {
    this.config = LoadAuditQueueConfigProperties.getInstance();
    this
      .logger = (new LoggerConfig(this.config.findProperty(EOperationProperty.LOG_CONFIG_FILE))).getRootLogger();
    this.logger.info("Inicio cargue propiedades");
  }
  
  @PostConstruct
  public void contextInitialized() {
    init();
    this.logger.info("Contexto inicializado ");
    QueueReaderRunnable.config = this.config;
    this.thread = new Thread(this::run);
    this.thread.start();
  }
  
  public void run() {
    try {
      Connection connection = CONNECTION_MANAGER.getConnection();
      this.queueReader = QueueReader.getInstance();
      InitialContext initialContext = this.queueReader.getInitialContext();
      String queue = this.config.findProperty(EOperationProperty.QUEUE);
      String url = this.config.findProperty(EOperationProperty.SERVER);
      this.logger.info("Server " + url);
      this.logger.info("Queue " + queue);
      this.queueReader.init(initialContext, queue, connection);
    } catch (NamingException|JMSException ex) {
      this.logger.error("Error en ejecucion readMessageQueue: " + ex);
    } catch (BackendException|co.com.claro.inspira.utilities.common.exception.dto.InternalException|SQLException ex) {
      this.logger.error("Error en conexion a base de datos: " + ex);
    } catch (SecurityException ex) {
      this.logger.error("Error de seguridad initMessageQueue " + ex);
    } 
  }
  
  @PreDestroy
  public void contextDestroyed() {
    if (this.queueReader != null)
      try {
        this.queueReader.closeContext();
      } catch (SQLException ex) {
        this.logger.error("Error Closing DataBase Connection ", ex);
      } catch (JMSException ex) {
        this.logger.error("Error Closing JMS Connection ", (Throwable)ex);
      } catch (SecurityException ex) {
        this.logger.error("Error SecurityException Closing JMS Connection ", ex);
      }  
    if (this.thread != null) {
      this.thread.interrupt();
      this.logger.info("Thread successfully stopped.");
    } 
    this.logger.info("Contexto finalizado");
  }
}
