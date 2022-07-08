package co.com.claro.inspira.audit.jms.config.constant;

import co.com.claro.inspira.utilities.common.string.StringUtil;

public enum EOperationProperty {
  SERVER("server"),
  USERNAME("username"),
  PASSWORD("password"),
  JNDI_FACTORY("jndi.factory"),
  JMS_FACTORY("connection.factory"),
  QUEUE("queue"),
  TOPIC("topic"),
  JNDI_DB("eoc.db.jndi"),
  AUDITORIA_OBSERVACION("eoc.auditoria.observacion"),
  AUDITORIA_DATEFORMAT("eoc.auditoria.dateformat"),
  AUDITORIA_INSERT_MESSAGE("eoc.db.insertar.cola"),
  AUDITORIA_INSERT_AUDIT("eoc.db.insertar.auditoria"),
  REINTENTOS("reintentos"),
  TIEMPO_REINTENTOS("tiempo.reintentos"),
  LOG_CONFIG_FILE("log.config.file");
  
  private final String APP_NAME = "app.auditqueue.jms";
  
  private String property;
  
  EOperationProperty(String property) {
    this.property = StringUtil.concatStrings(new String[] { "app.auditqueue.jms", ".", property });
  }
  
  public String getAPP_NAME() {
    return "app.auditqueue.jms";
  }
  
  public String getProperty() {
    return this.property;
  }
  
  public void setProperty(String property) {
    this.property = property;
  }
}
