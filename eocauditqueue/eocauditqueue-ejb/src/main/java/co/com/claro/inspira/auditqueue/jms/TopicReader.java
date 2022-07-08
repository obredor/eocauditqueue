package co.com.claro.inspira.auditqueue.jms;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TopicReader implements MessageListener {
  private static TopicReader instance;
  
  private TopicConnectionFactory topicConnectionFactory;
  
  private TopicConnection topicConnection;
  
  private TopicSession topicSession;
  
  private TopicSubscriber topicSubscriber;
  
  private Topic topic;
  
  private final LoadAuditQueueConfigProperties config = LoadAuditQueueConfigProperties.getInstance();
  
  private static final Logger LOGGER = LogManager.getLogger();
  
  public static synchronized TopicReader getInstance() {
    if (instance == null)
      instance = new TopicReader(); 
    return instance;
  }
  
  public void onMessage(Message msg) {
    try {
      String msgText;
      if (msg instanceof TextMessage) {
        msgText = ((TextMessage)msg).getText();
      } else {
        msgText = msg.toString();
      } 
      LOGGER.info("Message recibido " + msgText);
    } catch (JMSException jmsException) {
      LOGGER.error("Excepcion: " + jmsException.getMessage());
    } 
  }
  
  public void init(Context context, String queueName) throws NamingException, JMSException {
    this
      .topicConnectionFactory = (TopicConnectionFactory)context.lookup(this.config.findProperty(EOperationProperty.JMS_FACTORY));
    this.topicConnection = this.topicConnectionFactory.createTopicConnection();
    this
      .topicSession = this.topicConnection.createTopicSession(false, 1);
    this.topic = (Topic)context.lookup(queueName);
    this.topicSubscriber = this.topicSession.createSubscriber(this.topic);
    this.topicSubscriber.setMessageListener(this);
    this.topicConnection.start();
  }
  
  public void close() throws JMSException {
    this.topicSubscriber.close();
    this.topicSession.close();
    this.topicConnection.close();
  }
  
  public InitialContext getInitialContext() throws NamingException {
    Hashtable<String, String> env = new Hashtable<>();
    env.put("java.naming.factory.initial", this.config
        .findProperty(EOperationProperty.JNDI_FACTORY));
    env.put("java.naming.provider.url", this.config
        .findProperty(EOperationProperty.SERVER));
    return new InitialContext(env);
  }
}
