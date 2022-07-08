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
@Table(name = "EOC_COLA_JMS")
@XmlRootElement
@NamedQueries({@NamedQuery(name = "EocColaJms.findAll", query = "SELECT e FROM EocColaJms e"), @NamedQuery(name = "EocColaJms.findByBscscustomer", query = "SELECT e FROM EocColaJms e WHERE e.bscscustomer = :bscscustomer"), @NamedQuery(name = "EocColaJms.findByServiceorderid", query = "SELECT e FROM EocColaJms e WHERE e.serviceorderid = :serviceorderid"), @NamedQuery(name = "EocColaJms.findByCantequiposfinanciadas", query = "SELECT e FROM EocColaJms e WHERE e.cantequiposfinanciadas = :cantequiposfinanciadas"), @NamedQuery(name = "EocColaJms.findBySaldoinsolutocreditosequipos", query = "SELECT e FROM EocColaJms e WHERE e.saldoinsolutocreditosequipos = :saldoinsolutocreditosequipos"), @NamedQuery(name = "EocColaJms.findByCantcontratosfija", query = "SELECT e FROM EocColaJms e WHERE e.cantcontratosfija = :cantcontratosfija"), @NamedQuery(name = "EocColaJms.findByCantplanesmovilposalta", query = "SELECT e FROM EocColaJms e WHERE e.cantplanesmovilposalta = :cantplanesmovilposalta"), @NamedQuery(name = "EocColaJms.findByCantplanesmovilposmedia", query = "SELECT e FROM EocColaJms e WHERE e.cantplanesmovilposmedia = :cantplanesmovilposmedia"), @NamedQuery(name = "EocColaJms.findByCantplanesmovilposbaja", query = "SELECT e FROM EocColaJms e WHERE e.cantplanesmovilposbaja = :cantplanesmovilposbaja"), @NamedQuery(name = "EocColaJms.findByPlancode", query = "SELECT e FROM EocColaJms e WHERE e.plancode = :plancode"), @NamedQuery(name = "EocColaJms.findByCntdescuentosxplanxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntdescuentosxplanxbc = :cntdescuentosxplanxbc"), @NamedQuery(name = "EocColaJms.findByCntdescuentosxplanultimos12meses", query = "SELECT e FROM EocColaJms e WHERE e.cntdescuentosxplanultimos12meses = :cntdescuentosxplanultimos12meses"), @NamedQuery(name = "EocColaJms.findByTotalcfmxplan12meses", query = "SELECT e FROM EocColaJms e WHERE e.totalcfmxplan12meses = :totalcfmxplan12meses"), @NamedQuery(name = "EocColaJms.findByTotaldesxplan12meses", query = "SELECT e FROM EocColaJms e WHERE e.totaldesxplan12meses = :totaldesxplan12meses"), @NamedQuery(name = "EocColaJms.findByUltimovalordescuentoxplan", query = "SELECT e FROM EocColaJms e WHERE e.ultimovalordescuentoxplan = :ultimovalordescuentoxplan"), @NamedQuery(name = "EocColaJms.findByFechaultimodescuentocfm", query = "SELECT e FROM EocColaJms e WHERE e.fechaultimodescuentocfm = :fechaultimodescuentocfm"), @NamedQuery(name = "EocColaJms.findBySupsensionluegobonificacion", query = "SELECT e FROM EocColaJms e WHERE e.supsensionluegobonificacion = :supsensionluegobonificacion"), @NamedQuery(name = "EocColaJms.findByFechaultimodowngrade", query = "SELECT e FROM EocColaJms e WHERE e.fechaultimodowngrade = :fechaultimodowngrade"), @NamedQuery(name = "EocColaJms.findByFechaultimoblindaje", query = "SELECT e FROM EocColaJms e WHERE e.fechaultimoblindaje = :fechaultimoblindaje"), @NamedQuery(name = "EocColaJms.findByCntregaloserviciosxplanminxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntregaloserviciosxplanminxbc = :cntregaloserviciosxplanminxbc"), @NamedQuery(name = "EocColaJms.findByCntdesserviciosxplanminxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntdesserviciosxplanminxbc = :cntdesserviciosxplanminxbc"), @NamedQuery(name = "EocColaJms.findByCntregaloserviciosxplandatxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntregaloserviciosxplandatxbc = :cntregaloserviciosxplandatxbc"), @NamedQuery(name = "EocColaJms.findByCntdesserviciosxplandatxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntdesserviciosxplandatxbc = :cntdesserviciosxplandatxbc"), @NamedQuery(name = "EocColaJms.findByCntregaloserviciosxplansmsxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntregaloserviciosxplansmsxbc = :cntregaloserviciosxplansmsxbc"), @NamedQuery(name = "EocColaJms.findByCntdesserviciosxplansmsxbc", query = "SELECT e FROM EocColaJms e WHERE e.cntdesserviciosxplansmsxbc = :cntdesserviciosxplansmsxbc"), @NamedQuery(name = "EocColaJms.findByCntdesserviciosxplanultimos12meses", query = "SELECT e FROM EocColaJms e WHERE e.cntdesserviciosxplanultimos12meses = :cntdesserviciosxplanultimos12meses"), @NamedQuery(name = "EocColaJms.findByIdmensaje", query = "SELECT e FROM EocColaJms e WHERE e.idmensaje = :idmensaje")})
public class EocColaJms implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Size(max = 255)
  @Column(name = "CONTRACTID")
  private String contractid;
  
  @Size(max = 20)
  @Column(name = "BSCSCUSTOMER")
  private String bscscustomer;
  
  @Size(max = 20)
  @Column(name = "SERVICEORDERID")
  private String serviceorderid;
  
  @Column(name = "CANTEQUIPOSFINANCIADAS")
  private BigDecimal cantequiposfinanciadas;
  
  @Column(name = "SALDOINSOLUTOCREDITOSEQUIPOS")
  private BigDecimal saldoinsolutocreditosequipos;
  
  @Column(name = "CANTCONTRATOSFIJA")
  private BigDecimal cantcontratosfija;
  
  @Column(name = "CANTPLANESMOVILPOSALTA")
  private BigDecimal cantplanesmovilposalta;
  
  @Column(name = "CANTPLANESMOVILPOSMEDIA")
  private BigDecimal cantplanesmovilposmedia;
  
  @Column(name = "CANTPLANESMOVILPOSBAJA")
  private BigDecimal cantplanesmovilposbaja;
  
  @Size(max = 20)
  @Column(name = "PLANCODE")
  private String plancode;
  
  @Column(name = "CNTDESCUENTOSXPLANXBC")
  private BigDecimal cntdescuentosxplanxbc;
  
  @Column(name = "CNTDESCUENTOSXPLANULTIMOS12MESES")
  private BigDecimal cntdescuentosxplanultimos12meses;
  
  @Column(name = "TOTALCFMXPLAN12MESES")
  private BigDecimal totalcfmxplan12meses;
  
  @Column(name = "TOTALDESXPLAN12MESES")
  private BigDecimal totaldesxplan12meses;
  
  @Column(name = "ULTIMOVALORDESCUENTOXPLAN")
  private BigDecimal ultimovalordescuentoxplan;
  
  @Column(name = "FECHAULTIMODESCUENTOCFM")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaultimodescuentocfm;
  
  @Column(name = "SUPSENSIONLUEGOBONIFICACION")
  private Character supsensionluegobonificacion;
  
  @Column(name = "FECHAULTIMODOWNGRADE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaultimodowngrade;
  
  @Column(name = "FECHAULTIMOBLINDAJE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date fechaultimoblindaje;
  
  @Column(name = "CNTREGALOSERVICIOSXPLANMINXBC")
  private BigDecimal cntregaloserviciosxplanminxbc;
  
  @Column(name = "CNTDESSERVICIOSXPLANMINXBC")
  private BigDecimal cntdesserviciosxplanminxbc;
  
  @Column(name = "CNTREGALOSERVICIOSXPLANDATXBC")
  private BigDecimal cntregaloserviciosxplandatxbc;
  
  @Column(name = "CNTDESSERVICIOSXPLANDATXBC")
  private BigDecimal cntdesserviciosxplandatxbc;
  
  @Column(name = "CNTREGALOSERVICIOSXPLANSMSXBC")
  private BigDecimal cntregaloserviciosxplansmsxbc;
  
  @Column(name = "CNTDESSERVICIOSXPLANSMSXBC")
  private BigDecimal cntdesserviciosxplansmsxbc;
  
  @Column(name = "CNTDESSERVICIOSXPLANULTIMOS12MESES")
  private BigDecimal cntdesserviciosxplanultimos12meses;
  
  @Id
  @Column(name = "IDMENSAJE")
  private String idmensaje;
  
  public EocColaJms() {}
  
  public EocColaJms(String idmensaje) {
    this.idmensaje = idmensaje;
  }
  
  public String getContractid() {
    return this.contractid;
  }
  
  public void setContractid(String contractid) {
    this.contractid = contractid;
  }
  
  public String getBscscustomer() {
    return this.bscscustomer;
  }
  
  public void setBscscustomer(String bscscustomer) {
    this.bscscustomer = bscscustomer;
  }
  
  public String getServiceorderid() {
    return this.serviceorderid;
  }
  
  public void setServiceorderid(String serviceorderid) {
    this.serviceorderid = serviceorderid;
  }
  
  public BigDecimal getCantequiposfinanciadas() {
    return this.cantequiposfinanciadas;
  }
  
  public void setCantequiposfinanciadas(BigDecimal cantequiposfinanciadas) {
    this.cantequiposfinanciadas = cantequiposfinanciadas;
  }
  
  public BigDecimal getSaldoinsolutocreditosequipos() {
    return this.saldoinsolutocreditosequipos;
  }
  
  public void setSaldoinsolutocreditosequipos(BigDecimal saldoinsolutocreditosequipos) {
    this.saldoinsolutocreditosequipos = saldoinsolutocreditosequipos;
  }
  
  public BigDecimal getCantcontratosfija() {
    return this.cantcontratosfija;
  }
  
  public void setCantcontratosfija(BigDecimal cantcontratosfija) {
    this.cantcontratosfija = cantcontratosfija;
  }
  
  public BigDecimal getCantplanesmovilposalta() {
    return this.cantplanesmovilposalta;
  }
  
  public void setCantplanesmovilposalta(BigDecimal cantplanesmovilposalta) {
    this.cantplanesmovilposalta = cantplanesmovilposalta;
  }
  
  public BigDecimal getCantplanesmovilposmedia() {
    return this.cantplanesmovilposmedia;
  }
  
  public void setCantplanesmovilposmedia(BigDecimal cantplanesmovilposmedia) {
    this.cantplanesmovilposmedia = cantplanesmovilposmedia;
  }
  
  public BigDecimal getCantplanesmovilposbaja() {
    return this.cantplanesmovilposbaja;
  }
  
  public void setCantplanesmovilposbaja(BigDecimal cantplanesmovilposbaja) {
    this.cantplanesmovilposbaja = cantplanesmovilposbaja;
  }
  
  public String getPlancode() {
    return this.plancode;
  }
  
  public void setPlancode(String plancode) {
    this.plancode = plancode;
  }
  
  public BigDecimal getCntdescuentosxplanxbc() {
    return this.cntdescuentosxplanxbc;
  }
  
  public void setCntdescuentosxplanxbc(BigDecimal cntdescuentosxplanxbc) {
    this.cntdescuentosxplanxbc = cntdescuentosxplanxbc;
  }
  
  public BigDecimal getCntdescuentosxplanultimos12meses() {
    return this.cntdescuentosxplanultimos12meses;
  }
  
  public void setCntdescuentosxplanultimos12meses(BigDecimal cntdescuentosxplanultimos12meses) {
    this.cntdescuentosxplanultimos12meses = cntdescuentosxplanultimos12meses;
  }
  
  public BigDecimal getTotalcfmxplan12meses() {
    return this.totalcfmxplan12meses;
  }
  
  public void setTotalcfmxplan12meses(BigDecimal totalcfmxplan12meses) {
    this.totalcfmxplan12meses = totalcfmxplan12meses;
  }
  
  public BigDecimal getTotaldesxplan12meses() {
    return this.totaldesxplan12meses;
  }
  
  public void setTotaldesxplan12meses(BigDecimal totaldesxplan12meses) {
    this.totaldesxplan12meses = totaldesxplan12meses;
  }
  
  public BigDecimal getUltimovalordescuentoxplan() {
    return this.ultimovalordescuentoxplan;
  }
  
  public void setUltimovalordescuentoxplan(BigDecimal ultimovalordescuentoxplan) {
    this.ultimovalordescuentoxplan = ultimovalordescuentoxplan;
  }
  
  public Date getFechaultimodescuentocfm() {
    return this.fechaultimodescuentocfm;
  }
  
  public void setFechaultimodescuentocfm(Date fechaultimodescuentocfm) {
    this.fechaultimodescuentocfm = fechaultimodescuentocfm;
  }
  
  public Character getSupsensionluegobonificacion() {
    return this.supsensionluegobonificacion;
  }
  
  public void setSupsensionluegobonificacion(Character supsensionluegobonificacion) {
    this.supsensionluegobonificacion = supsensionluegobonificacion;
  }
  
  public Date getFechaultimodowngrade() {
    return this.fechaultimodowngrade;
  }
  
  public void setFechaultimodowngrade(Date fechaultimodowngrade) {
    this.fechaultimodowngrade = fechaultimodowngrade;
  }
  
  public Date getFechaultimoblindaje() {
    return this.fechaultimoblindaje;
  }
  
  public void setFechaultimoblindaje(Date fechaultimoblindaje) {
    this.fechaultimoblindaje = fechaultimoblindaje;
  }
  
  public BigDecimal getCntregaloserviciosxplanminxbc() {
    return this.cntregaloserviciosxplanminxbc;
  }
  
  public void setCntregaloserviciosxplanminxbc(BigDecimal cntregaloserviciosxplanminxbc) {
    this.cntregaloserviciosxplanminxbc = cntregaloserviciosxplanminxbc;
  }
  
  public BigDecimal getCntdesserviciosxplanminxbc() {
    return this.cntdesserviciosxplanminxbc;
  }
  
  public void setCntdesserviciosxplanminxbc(BigDecimal cntdesserviciosxplanminxbc) {
    this.cntdesserviciosxplanminxbc = cntdesserviciosxplanminxbc;
  }
  
  public BigDecimal getCntregaloserviciosxplandatxbc() {
    return this.cntregaloserviciosxplandatxbc;
  }
  
  public void setCntregaloserviciosxplandatxbc(BigDecimal cntregaloserviciosxplandatxbc) {
    this.cntregaloserviciosxplandatxbc = cntregaloserviciosxplandatxbc;
  }
  
  public BigDecimal getCntdesserviciosxplandatxbc() {
    return this.cntdesserviciosxplandatxbc;
  }
  
  public void setCntdesserviciosxplandatxbc(BigDecimal cntdesserviciosxplandatxbc) {
    this.cntdesserviciosxplandatxbc = cntdesserviciosxplandatxbc;
  }
  
  public BigDecimal getCntregaloserviciosxplansmsxbc() {
    return this.cntregaloserviciosxplansmsxbc;
  }
  
  public void setCntregaloserviciosxplansmsxbc(BigDecimal cntregaloserviciosxplansmsxbc) {
    this.cntregaloserviciosxplansmsxbc = cntregaloserviciosxplansmsxbc;
  }
  
  public BigDecimal getCntdesserviciosxplansmsxbc() {
    return this.cntdesserviciosxplansmsxbc;
  }
  
  public void setCntdesserviciosxplansmsxbc(BigDecimal cntdesserviciosxplansmsxbc) {
    this.cntdesserviciosxplansmsxbc = cntdesserviciosxplansmsxbc;
  }
  
  public BigDecimal getCntdesserviciosxplanultimos12meses() {
    return this.cntdesserviciosxplanultimos12meses;
  }
  
  public void setCntdesserviciosxplanultimos12meses(BigDecimal cntdesserviciosxplanultimos12meses) {
    this.cntdesserviciosxplanultimos12meses = cntdesserviciosxplanultimos12meses;
  }
  
  public String getIdmensaje() {
    return this.idmensaje;
  }
  
  public void setIdmensaje(String idmensaje) {
    this.idmensaje = idmensaje;
  }
  
  public int hashCode() {
    int hash = 0;
    hash += (this.idmensaje != null) ? this.idmensaje.hashCode() : 0;
    return hash;
  }
  
  public boolean equals(Object object) {
    if (!(object instanceof EocColaJms))
      return false; 
    EocColaJms other = (EocColaJms)object;
    if ((this.idmensaje == null && other.idmensaje != null) || (this.idmensaje != null && !this.idmensaje.equals(other.idmensaje)))
      return false; 
    return true;
  }
  
  public String toString() {
    return "co.com.claro.inspira.audit.persistence.entities.EocColaJms[ idmensaje=" + this.idmensaje + " ]";
  }
}
