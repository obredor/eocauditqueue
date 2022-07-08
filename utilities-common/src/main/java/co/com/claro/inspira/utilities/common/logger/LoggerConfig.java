// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.logger;

import org.apache.logging.log4j.Logger;
import java.io.File;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;

public class LoggerConfig
{
    protected String configurationFile;
    
    public LoggerConfig(final String configurationFile) {
        this.configurationFile = configurationFile;
        this.reconfigure();
    }
    
    public void reconfigure() {
        final LoggerContext context = (LoggerContext)LogManager.getContext(false);
        final File file = new File(this.configurationFile);
        context.setConfigLocation(file.toURI());
    }
    
    public Logger getRootLogger() {
        return LogManager.getRootLogger();
    }
    
    public Logger getLogger(final String name) {
        return LogManager.getLogger(name);
    }
    
    public Logger getLogger(final Class<?> clazz) {
        return LogManager.getLogger((Class)clazz);
    }
}
