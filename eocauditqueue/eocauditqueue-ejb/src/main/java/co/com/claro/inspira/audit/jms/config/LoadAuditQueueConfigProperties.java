package co.com.claro.inspira.audit.jms.config;

import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import co.com.claro.inspira.utilities.common.properties.LoadPropertiesConfig;

public class LoadAuditQueueConfigProperties extends LoadPropertiesConfig {
  private static final String FILE_PATH = "/App/Properties/eocAuditQueue-V1.0/eocauditqueue.properties";
  
  private static LoadAuditQueueConfigProperties instance;
  
  public static LoadAuditQueueConfigProperties getInstance() {
    createInstance();
    return instance;
  }
  
  public String findProperty(EOperationProperty property) {
    return findProperty(property.getProperty());
  }
  
  private static synchronized void createInstance() {
    instance = new LoadAuditQueueConfigProperties();
  }
  
  private LoadAuditQueueConfigProperties() {
    super("/App/Properties/eocAuditQueue-V1.0/eocauditqueue.properties");
  }
}
