package co.com.claro.inspira.eocauditqueue.dto;

import java.util.List;

public class PlanesDetalles {
  private String PlanCode;
  
  private ServiciosSumario ServiciosSumario;
  
  private List<Indicadores> Indicadores;
  
  private ServiciosDetalles ServiciosDetalles;
  
  public String getPlanCode() {
    return this.PlanCode;
  }
  
  public void setPlanCode(String PlanCode) {
    this.PlanCode = PlanCode;
  }
  
  public ServiciosSumario getServiciosSumario() {
    return this.ServiciosSumario;
  }
  
  public void setServiciosSumario(ServiciosSumario serviciosSumario) {
    this.ServiciosSumario = serviciosSumario;
  }
  
  public List<Indicadores> getIndicadores() {
    return this.Indicadores;
  }
  
  public void setIndicadores(List<Indicadores> Indicadores) {
    this.Indicadores = Indicadores;
  }
  
  public ServiciosDetalles getServiciosDetalles() {
    return this.ServiciosDetalles;
  }
  
  public void setServiciosDetalles(ServiciosDetalles serviciosDetalles) {
    this.ServiciosDetalles = serviciosDetalles;
  }
}
