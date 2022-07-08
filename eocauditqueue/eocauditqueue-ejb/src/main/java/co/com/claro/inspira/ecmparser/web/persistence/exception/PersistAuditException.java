package co.com.claro.inspira.ecmparser.web.persistence.exception;

public class PersistAuditException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public PersistAuditException(String message, Throwable cause) {
    super(message, cause);
  }
}
