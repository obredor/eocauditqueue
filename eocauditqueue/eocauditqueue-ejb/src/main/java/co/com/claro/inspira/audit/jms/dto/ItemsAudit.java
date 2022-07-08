package co.com.claro.inspira.audit.jms.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "itemsAudit")
public class ItemsAudit {
  @XmlElement
  private List<ItemAudit> itemAudit;
  
  public List<ItemAudit> getItemAudit() {
    return this.itemAudit;
  }
  
  public void setItemAudit(List<ItemAudit> itemAudit) {
    this.itemAudit = itemAudit;
  }
}
