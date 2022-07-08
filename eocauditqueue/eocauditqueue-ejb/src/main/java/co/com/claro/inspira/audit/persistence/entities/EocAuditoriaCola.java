package co.com.claro.inspira.audit.persistence.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "EOC_AUDITORIA_COLA")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "EocAuditoriaCola.findAll", query = "SELECT e FROM EocAuditoriaCola e"), @NamedQuery(name = "EocAuditoriaCola.findByIdAuditoria", query = "SELECT e FROM EocAuditoriaCola e WHERE e.idAuditoria = :idAuditoria"), @NamedQuery(name = "EocAuditoriaCola.findByFechaCrea", query = "SELECT e FROM EocAuditoriaCola e WHERE e.fechaCrea = :fechaCrea"), @NamedQuery(name = "EocAuditoriaCola.findByUsuarioCrea", query = "SELECT e FROM EocAuditoriaCola e WHERE e.usuarioCrea = :usuarioCrea"), @NamedQuery(name = "EocAuditoriaCola.findByReintento", query = "SELECT e FROM EocAuditoriaCola e WHERE e.reintento = :reintento"), @NamedQuery(name = "EocAuditoriaCola.findByObservacion", query = "SELECT e FROM EocAuditoriaCola e WHERE e.observacion = :observacion")})
public class EocAuditoriaCola implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @Column(name = "ID_AUDITORIA")
  private BigDecimal idAuditoria;
  
  @Column(name = "FECHA_CREA")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaCrea;
  
  @Size(max = 20)
  @Column(name = "USUARIO_CREA")
  private String usuarioCrea;
  
  @Size(max = 20)
  @Column(name = "REINTENTO")
  private String reintento;
  
  @Size(max = 255)
  @Column(name = "OBSERVACION")
  private String observacion;
  
  @Column(name = "IDMENSAJE")
  private String idmensaje;
  
  public EocAuditoriaCola() {}
  
  public EocAuditoriaCola(BigDecimal idAuditoria) {
    this.idAuditoria = idAuditoria;
  }
  
  public BigDecimal getIdAuditoria() {
    return this.idAuditoria;
  }
  
  public void setIdAuditoria(BigDecimal idAuditoria) {
    this.idAuditoria = idAuditoria;
  }
  
  public Date getFechaCrea() {
    return this.fechaCrea;
  }
  
  public void setFechaCrea(Date fechaCrea) {
    this.fechaCrea = fechaCrea;
  }
  
  public String getUsuarioCrea() {
    return this.usuarioCrea;
  }
  
  public void setUsuarioCrea(String usuarioCrea) {
    this.usuarioCrea = usuarioCrea;
  }
  
  public String getReintento() {
    return this.reintento;
  }
  
  public void setReintento(String reintento) {
    this.reintento = reintento;
  }
  
  public String getObservacion() {
    return this.observacion;
  }
  
  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }
  
  public String getIdmensaje() {
    return this.idmensaje;
  }
  
  public void setIdmensaje(String idmensaje) {
    this.idmensaje = idmensaje;
  }
  
  public int hashCode() {
    int hash = 0;
    hash += (this.idAuditoria != null) ? this.idAuditoria.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof EocAuditoriaCola))
      return false; 
    EocAuditoriaCola other = (EocAuditoriaCola)object;
    if ((this.idAuditoria == null && other.idAuditoria != null) || (this.idAuditoria != null && !this.idAuditoria.equals(other.idAuditoria)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "co.com.claro.inspira.audit.persistence.entities.EocAuditoriaCola[ idAuditoria=" + this.idAuditoria + " ]";
  }
}
