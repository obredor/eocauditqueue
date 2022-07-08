// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.exception.dto;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceConnectionException extends Exception
{
    private static final long serialVersionUID = 1L;
    private static Logger logger;
    
    public ServiceConnectionException(final String code, final Throwable message) {
        super(code, message);
        ServiceConnectionException.logger.error(code, message);
    }
    
    public ServiceConnectionException() {
        ServiceConnectionException.logger.error("Error");
    }
    
    public ServiceConnectionException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        ServiceConnectionException.logger.error("Error: ", cause);
    }
    
    public ServiceConnectionException(final String message) {
        super(message);
        ServiceConnectionException.logger.error("Error: " + message);
    }
    
    public ServiceConnectionException(final Throwable cause) {
        super(cause);
        ServiceConnectionException.logger.error("Error: " + cause);
    }
    
    static {
        ServiceConnectionException.logger = LogManager.getLogger();
    }
}
