package co.com.claro.inspira.ecmparser.web.persistence.exception;

public class DataBaseConnectionException extends Exception {
  private static final long serialVersionUID = 1L;
  
  public DataBaseConnectionException(String message, Throwable cause) {
    super(message, cause);
  }
}
