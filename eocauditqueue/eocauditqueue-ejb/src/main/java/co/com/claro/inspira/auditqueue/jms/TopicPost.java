package co.com.claro.inspira.auditqueue.jms;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TopicPost {
  private static TopicReader instance;
  
  private static Logger logger = LogManager.getLogger();
  
  private TopicConnectionFactory topicConnectionFactory;
  
  private TopicConnection topicConnection;
  
  private TopicSession topicSession;
  
  private TopicPublisher topicPublisher;
  
  private Topic topic;
  
  private TextMessage message;
  
  private final LoadAuditQueueConfigProperties config = LoadAuditQueueConfigProperties.getInstance();
  
  public void init(Context context, String queueName) throws NamingException, JMSException {
    this
      .topicConnectionFactory = (TopicConnectionFactory)context.lookup(this.config.findProperty(EOperationProperty.JMS_FACTORY));
    this.topicConnection = this.topicConnectionFactory.createTopicConnection();
    this
      .topicSession = this.topicConnection.createTopicSession(false, 1);
    this.topic = (Topic)context.lookup(queueName);
    this.topicPublisher = this.topicSession.createPublisher(this.topic);
    this.message = this.topicSession.createTextMessage();
    this.topicConnection.start();
  }
  
  public void post(String msg) throws JMSException {
    this.message.setText(msg);
    this.topicPublisher.send((Message)this.message);
  }
  
  public void close() throws JMSException {
    this.topicPublisher.close();
    this.topicSession.close();
    this.topicConnection.close();
  }
  
  public static void sendToServer(TopicPost queuePoster) throws IOException, JMSException {
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
      boolean readLog = true;
      System.out.println("Mensaje a enviar a weblogic");
      while (readLog) {
        System.out.println("Ingrese el mensaje a enviar a la cola");
        String msg = bufferedReader.readLine();
        queuePoster.post(msg);
        if (msg.equals("quit"))
          System.exit(0); 
        System.out.println();
      } 
    } 
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
