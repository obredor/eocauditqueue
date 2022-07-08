package co.com.claro.inspira.audit.jms.util;

import co.com.claro.inspira.ecmparser.web.persistence.exception.ConnectionManager;
import co.com.claro.inspira.eocauditqueue.dto.EocAuditQueueRequest;
import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import co.com.claro.inspira.utilities.common.exception.dto.InternalException;
import co.com.claro.inspira.utilities.common.util.Utilidades;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

public class Utilities {
  public static String observacion;
  
  private static final ConnectionManager CONNECTION_MANAGER = ConnectionManager.getInstance();
  
  public static boolean validateReadQueueEnd(int numMessagesQueue, int contador) {
    return (numMessagesQueue > 0 && contador < numMessagesQueue);
  }
  
  public static EocAuditQueueRequest convertirJsonPojo(String json) {
    EocAuditQueueRequest eocAuditQueueRequest = (EocAuditQueueRequest)(new Gson()).fromJson(json, EocAuditQueueRequest.class);
    return eocAuditQueueRequest;
  }
  
  public static boolean validarListNull(Collection<?> lista) {
    return (lista == null || lista.isEmpty());
  }
  
  public static BigInteger asignarBigInteger(String value) {
    return !Utilidades.validarCampoNulo(value) ? new BigInteger(value) : null;
  }
  
  public static BigDecimal asignarBigDecimal(String value) {
    return !Utilidades.validarCampoNulo(value) ? new BigDecimal(value) : null;
  }
  
  public static String generarAuditoriaCola(int reintento, String queryPersistAudit, Connection connection) throws BackendException, InternalException, SQLException {
    CallableStatement statement = null;
    try {
      statement = connection.prepareCall(queryPersistAudit);
      statement.setString(1, System.getProperty("weblogic.Name"));
      statement.setString(2, String.valueOf(reintento));
      statement.setString(3, observacion);
      statement.registerOutParameter(4, 12);
      statement.execute();
      String idMensaje = statement.getString(4);
      statement.close();
      return idMensaje;
    } catch (SQLException ex) {
      if (statement != null)
        statement.close(); 
      throw ex;
    } 
  }
}
