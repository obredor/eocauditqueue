// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.properties;

import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Properties;
import co.com.claro.inspira.utilities.common.string.StringUtil;
import org.apache.logging.log4j.Logger;
import java.util.Map;

public class LoadPropertiesConfig
{
    protected String filePath;
    private Map<String, String> properties;
    protected static Logger logger;
    
    public void reload() {
        synchronized (this.properties) {
            this.load();
        }
    }
    
    public Boolean findPropertyBoolean(final String property) {
        return Boolean.parseBoolean(this.findProperty(property));
    }
    
    public Integer findPropertyInteger(final String property) {
        Integer value = null;
        try {
            value = Integer.parseInt(this.findProperty(property));
        }
        catch (NumberFormatException e) {
            LoadPropertiesConfig.logger.error(StringUtil.concatStrings("The property [", property, "=", value.toString(), "] cannot be coverted to Integer"));
        }
        return value;
    }
    
    public Long findPropertyLong(final String property) {
        Long value = null;
        try {
            value = Long.parseLong(this.findProperty(property));
        }
        catch (NumberFormatException e) {
            LoadPropertiesConfig.logger.error(StringUtil.concatStrings("The property [", property, "=", value.toString(), "] cannot be coverted to Long"));
        }
        return value;
    }
    
    public String findProperty(final String property) {
        return this.properties.get(property);
    }
    
    public Boolean findPropertyBooleanFromSource(final String property) {
        return Boolean.parseBoolean(this.findPropertyFromSource(property));
    }
    
    public String findPropertyFromSource(final String property) {
        Properties properties = null;
        try {
            properties = this.getPropertiesFromFile();
        }
        catch (Exception e) {
            LoadPropertiesConfig.logger.error((Object)e);
        }
        return properties.getProperty(property);
    }
    
    public LoadPropertiesConfig(final String filePath) {
        this.filePath = filePath;
        this.load();
        LoadPropertiesConfig.logger.info(StringUtil.concatStrings("Configuration file [", this.filePath, "] loaded"));
    }
    
    private void load() {
        try {
            this.properties = this.convertPropertiesToConcurrentHashMap(this.getPropertiesFromFile());
        }
        catch (Exception e) {
            LoadPropertiesConfig.logger.error("Exception caught in LoadCachePropertiesConfig.load ");
        }
    }
    
    private ConcurrentHashMap<String, String> convertPropertiesToConcurrentHashMap(final Properties properties) {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        for (final String name : properties.stringPropertyNames()) {
            map.put(name, properties.getProperty(name));
        }
        return map;
    }
    
    protected synchronized Properties getPropertiesFromFile() throws Exception {
        InputStreamReader input = null;
        try {
            input = new InputStreamReader(new FileInputStream(this.filePath), "UTF-8");
            final Properties properties = new Properties();
            properties.load(input);
            input.close();
            return properties;
        }
        catch (IOException e) {
            LoadPropertiesConfig.logger.error(StringUtil.concatStrings("Error loading the configuration file [", this.filePath, "]"));
            throw e;
        }
        finally {
            if (input != null) {
                input.close();
            }
        }
    }
    
    static {
        LoadPropertiesConfig.logger = LogManager.getLogger();
    }
}
