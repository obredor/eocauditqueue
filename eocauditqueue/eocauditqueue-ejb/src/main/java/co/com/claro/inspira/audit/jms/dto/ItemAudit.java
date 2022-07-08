package co.com.claro.inspira.audit.jms.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemAudit")
public class ItemAudit {
  @XmlElement
  private String system;
  
  @XmlElement
  private String service;
  
  @XmlElement
  private String requestDate;
  
  @XmlElement
  private String traceabilityId;
  
  @XmlElement
  private String eventType;
  
  @XmlElement
  private String message;
  
  @XmlElement
  private String user;
  
  @XmlElement
  private String operation;
  
  @XmlElement
  private String transactionId;
  
  @XmlElement
  private String responseDate;
  
  public String getSystem() {
    return this.system;
  }
  
  public void setSystem(String system) {
    this.system = system;
  }
  
  public String getService() {
    return this.service;
  }
  
  public void setService(String service) {
    this.service = service;
  }
  
  public String getRequestDate() {
    return this.requestDate;
  }
  
  public void setRequestDate(String requestDate) {
    this.requestDate = requestDate;
  }
  
  public String getTraceabilityId() {
    return this.traceabilityId;
  }
  
  public void setTraceabilityId(String traceabilityId) {
    this.traceabilityId = traceabilityId;
  }
  
  public String getEventType() {
    return this.eventType;
  }
  
  public void setEventType(String eventType) {
    this.eventType = eventType;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
  
  public String getUser() {
    return this.user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public String getOperation() {
    return this.operation;
  }
  
  public void setOperation(String operation) {
    this.operation = operation;
  }
  
  public String getTransactionId() {
    return this.transactionId;
  }
  
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }
  
  public String getResponseDate() {
    return this.responseDate;
  }
  
  public void setResponseDate(String responseDate) {
    this.responseDate = responseDate;
  }
}
