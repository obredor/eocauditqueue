package co.com.claro.inspira.eocauditqueue.dto;

import java.util.List;

public class ServiciosDetalles {
  private List<Voz> Voz;
  
  private List<SMS> SMS;
  
  private List<Data> Data;
  
  public List<Voz> getVoz() {
    return this.Voz;
  }
  
  public void setVoz(List<Voz> Voz) {
    this.Voz = Voz;
  }
  
  public List<SMS> getSMS() {
    return this.SMS;
  }
  
  public void setSMS(List<SMS> SMS) {
    this.SMS = SMS;
  }
  
  public List<Data> getData() {
    return this.Data;
  }
  
  public void setData(List<Data> Data) {
    this.Data = Data;
  }
}
