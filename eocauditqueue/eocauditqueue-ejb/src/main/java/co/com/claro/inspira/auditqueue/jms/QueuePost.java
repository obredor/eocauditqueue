package co.com.claro.inspira.auditqueue.jms;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import java.io.IOException;
import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class QueuePost {
  private static QueuePost instance;
  
  private QueueConnectionFactory queueConnectionFactory;
  
  private QueueConnection queueConnection;
  
  private QueueSession queueSession;
  
  private QueueSender queueSender;
  
  private Queue queue;
  
  private TextMessage message;
  
  private static final LoadAuditQueueConfigProperties config = LoadAuditQueueConfigProperties.getInstance();
  
  public static synchronized QueuePost getInstance() {
    if (instance == null)
      instance = new QueuePost(); 
    return instance;
  }
  
  public void init(Context context, String queueName) throws NamingException, JMSException {
    this
      .queueConnectionFactory = (QueueConnectionFactory)context.lookup(config.findProperty(EOperationProperty.JMS_FACTORY));
    this.queueConnection = this.queueConnectionFactory.createQueueConnection();
    this
      .queueSession = this.queueConnection.createQueueSession(false, 1);
    this.queue = (Queue)context.lookup(queueName);
    this.queueSender = this.queueSession.createSender(this.queue);
    this.message = this.queueSession.createTextMessage();
    this.queueConnection.start();
  }
  
  public void post(String msg) throws JMSException {
    this.message.setText(msg);
    this.queueSender.send((Message)this.message);
  }
  
  public void close() throws JMSException {
    this.queueSender.close();
    this.queueSession.close();
    this.queueConnection.close();
  }
  
  public static void sendToServer(QueuePost queuePoster, String msg) throws IOException, JMSException {
    queuePoster.post(msg);
  }
  
  public static InitialContext getInitialContext() throws NamingException {
    Hashtable<String, String> env = new Hashtable<>();
    env.put("java.naming.factory.initial", config
        .findProperty(EOperationProperty.JNDI_FACTORY));
    env.put("java.naming.provider.url", config
        .findProperty(EOperationProperty.SERVER));
    return new InitialContext(env);
  }
}
