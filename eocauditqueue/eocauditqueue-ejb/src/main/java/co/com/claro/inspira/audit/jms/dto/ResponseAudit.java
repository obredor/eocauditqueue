package co.com.claro.inspira.audit.jms.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "responseAudit")
public class ResponseAudit {
  @XmlElement(name = "itemsAudit")
  private ItemsAudit itemsAudit;
  
  @XmlElement(name = "osbSource")
  private String osbSource;
  
  public String getOsbSource() {
    return this.osbSource;
  }
  
  public void setOsbSource(String osbSource) {
    this.osbSource = osbSource;
  }
  
  public ItemsAudit getItemsAudit() {
    return this.itemsAudit;
  }
  
  public void setItemsAudit(ItemsAudit itemsAudit) {
    this.itemsAudit = itemsAudit;
  }
}
