package co.com.claro.inspira.audit.jms.listener;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import co.com.claro.inspira.auditqueue.jms.QueueReader;
import co.com.claro.inspira.ecmparser.web.persistence.exception.ConnectionManager;
import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.jms.JMSException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QueueReaderRunnable {
  public static LoadAuditQueueConfigProperties config;
  
  private static final Logger LOGGER = LogManager.getLogger();
  
  private QueueReader queueReader;
  
  private static final ConnectionManager CONNECTION_MANAGER = ConnectionManager.getInstance();
  
  public void initMessageQueue() {
    try {
      Connection connection = CONNECTION_MANAGER.getConnection();
      this.queueReader = QueueReader.getInstance();
      InitialContext initialContext = this.queueReader.getInitialContext();
      String queue = config.findProperty(EOperationProperty.QUEUE);
      String url = config.findProperty(EOperationProperty.SERVER);
      LOGGER.info("Server " + url);
      LOGGER.info("Queue " + queue);
      this.queueReader.init(initialContext, queue, connection);
    } catch (NamingException|JMSException ex) {
      LOGGER.error("Error en ejecucion readMessageQueue: " + ex);
    } catch (BackendException|co.com.claro.inspira.utilities.common.exception.dto.InternalException|SQLException ex) {
      LOGGER.error("Error en conexion a base de datos: " + ex);
    } catch (SecurityException ex) {
      LOGGER.error("Error de seguridad initMessageQueue " + ex);
    } 
  }
  
  public void closeInstance() throws SQLException, JMSException, SecurityException {
    this.queueReader.closeContext();
  }
}
