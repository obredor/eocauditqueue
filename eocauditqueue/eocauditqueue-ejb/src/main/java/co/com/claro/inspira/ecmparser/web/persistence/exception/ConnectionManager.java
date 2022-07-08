package co.com.claro.inspira.ecmparser.web.persistence.exception;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import co.com.claro.inspira.utilities.common.exception.dto.InternalException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionManager {
  private static final ConnectionManager INSTANCE = new ConnectionManager();
  
  private static final Logger LOGGER = LogManager.getLogger();
  
  private final LoadAuditQueueConfigProperties config = LoadAuditQueueConfigProperties.getInstance();
  
  public static ConnectionManager getInstance() {
    return INSTANCE;
  }
  
  public Connection getConnection() throws BackendException, InternalException {
    Connection connection = null;
    try {
      Context context = new InitialContext();
      DataSource dsApp = (DataSource)context.lookup(this.config.findProperty(EOperationProperty.JNDI_DB));
      connection = dsApp.getConnection();
    } catch (NamingException e) {
      LOGGER.error("Error obtaining context from datasource. ", e);
      throw new InternalException(null, null, DataBaseConnectionException.class
          .getSimpleName(), e
          .getClass().getSimpleName() + ": - Error obtaining context from datasource ");
    } catch (NullPointerException e) {
      LOGGER.error("Error obtaining context from datasource - NullPointerException. ", e);
      throw new InternalException(null, null, DataBaseConnectionException.class
          .getSimpleName(), "Error obtaining context from datasource - null or invalid property");
    } catch (SQLException e) {
      LOGGER.error("Error obtaining the connection. ", e);
      throw new BackendException(null, null, DataBaseConnectionException.class
          .getSimpleName(), e
          .getClass().getSimpleName() + ": - Error obtaining the connection ");
    } 
    return connection;
  }
  
  public void close(Connection conn, CallableStatement cs) {
    close(conn);
    close(cs);
  }
  
  public void close(Connection conn, CallableStatement cs, ResultSet rs) {
    close(conn);
    close(cs);
    close(rs);
  }
  
  public void close(Connection conn) {
    try {
      if (conn != null)
        conn.close(); 
    } catch (SQLException e) {
      LOGGER.error("Error closing connection", e);
    } 
  }
  
  public void close(CallableStatement ps) {
    try {
      if (ps != null)
        ps.close(); 
    } catch (SQLException e) {
      LOGGER.error("Error closing PreparedStatement", e);
    } 
  }
  
  public void close(ResultSet rs) {
    try {
      if (rs != null)
        rs.close(); 
    } catch (SQLException e) {
      LOGGER.error("Error closing ResultSet", e);
    } 
  }
}
