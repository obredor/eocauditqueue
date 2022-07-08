package co.com.claro.inspira.eocauditqueue.dto;

public class EocAuditQueueRequest {
  private PlanesDetalles PlanesDetalles;
  
  private Planes Planes;
  
  private String contractId;
  
  private String BSCSCustomer;
  
  private EquiposFinanciados EquiposFinanciados;
  
  private String serviceOrderId;
  
  public PlanesDetalles getPlanesDetalles() {
    return this.PlanesDetalles;
  }
  
  public void setPlanesDetalles(PlanesDetalles planesDetalles) {
    this.PlanesDetalles = planesDetalles;
  }
  
  public Planes getPlanes() {
    return this.Planes;
  }
  
  public void setPlanes(Planes planes) {
    this.Planes = planes;
  }
  
  public String getContractId() {
    return this.contractId;
  }
  
  public void setContractId(String contractId) {
    this.contractId = contractId;
  }
  
  public String getBSCSCustomer() {
    return this.BSCSCustomer;
  }
  
  public void setBSCSCustomer(String BSCSCustomer) {
    this.BSCSCustomer = BSCSCustomer;
  }
  
  public EquiposFinanciados getEquiposFinanciados() {
    return this.EquiposFinanciados;
  }
  
  public void setEquiposFinanciados(EquiposFinanciados equiposFinanciados) {
    this.EquiposFinanciados = equiposFinanciados;
  }
  
  public String getServiceOrderId() {
    return this.serviceOrderId;
  }
  
  public void setServiceOrderId(String serviceOrderId) {
    this.serviceOrderId = serviceOrderId;
  }
}
