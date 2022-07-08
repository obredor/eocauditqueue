package co.com.claro.inspira.audit.persistence.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlTransient;

public class EocColaJmsDto {
	
	private String bscscustomer;

	private String serviceorderid;

	private BigInteger cantequiposfinanciadas;

	private BigDecimal saldoinsolutocreditosequipos;

	private BigInteger cantcontratosfija;

	private BigInteger cantplanesmovilposalta;

	private BigInteger cantplanesmovilposmedia;

	private BigInteger cantplanesmovilposbaja;

	private String plancode;

	private BigInteger cntdescuentosxplanxbc;

	private BigInteger cntdescuentosxplanultimos12meses;

	private BigDecimal totalcfmxplan12meses;

	private BigDecimal totaldesxplan12meses;

	private BigDecimal ultimovalordescuentoxplan;

	private Date fechaultimodescuentocfm;

	private Character supsensionluegobonificacion;

	private Date fechaultimodowngrade;

	private Date fechaultimoblindaje;

	private BigInteger cntregaloserviciosxplanminxbc;

	private BigInteger cntdesserviciosxplanminxbc;

	private BigInteger cntregaloserviciosxplandatxbc;

	private BigInteger cntdesserviciosxplandatxbc;

	private BigInteger cntregaloserviciosxplansmsxbc;

	private BigInteger cntdesserviciosxplansmsxbc;

	private BigInteger cntdesserviciosxplanultimos12meses;

	private String idmensaje;

	private Collection<EocAuditoriaColaDto> eocAuditoriaColaCollection;

	public EocColaJmsDto() {
	}

	public EocColaJmsDto(String idmensaje) {
		this.idmensaje = idmensaje;
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

	public BigInteger getCantequiposfinanciadas() {
		return this.cantequiposfinanciadas;
	}

	public void setCantequiposfinanciadas(BigInteger cantequiposfinanciadas) {
		this.cantequiposfinanciadas = cantequiposfinanciadas;
	}

	public BigDecimal getSaldoinsolutocreditosequipos() {
		return this.saldoinsolutocreditosequipos;
	}

	public void setSaldoinsolutocreditosequipos(BigDecimal saldoinsolutocreditosequipos) {
		this.saldoinsolutocreditosequipos = saldoinsolutocreditosequipos;
	}

	public BigInteger getCantcontratosfija() {
		return this.cantcontratosfija;
	}

	public void setCantcontratosfija(BigInteger cantcontratosfija) {
		this.cantcontratosfija = cantcontratosfija;
	}

	public BigInteger getCantplanesmovilposalta() {
		return this.cantplanesmovilposalta;
	}

	public void setCantplanesmovilposalta(BigInteger cantplanesmovilposalta) {
		this.cantplanesmovilposalta = cantplanesmovilposalta;
	}

	public BigInteger getCantplanesmovilposmedia() {
		return this.cantplanesmovilposmedia;
	}

	public void setCantplanesmovilposmedia(BigInteger cantplanesmovilposmedia) {
		this.cantplanesmovilposmedia = cantplanesmovilposmedia;
	}

	public BigInteger getCantplanesmovilposbaja() {
		return this.cantplanesmovilposbaja;
	}

	public void setCantplanesmovilposbaja(BigInteger cantplanesmovilposbaja) {
		this.cantplanesmovilposbaja = cantplanesmovilposbaja;
	}

	public String getPlancode() {
		return this.plancode;
	}

	public void setPlancode(String plancode) {
		this.plancode = plancode;
	}

	public BigInteger getCntdescuentosxplanxbc() {
		return this.cntdescuentosxplanxbc;
	}

	public void setCntdescuentosxplanxbc(BigInteger cntdescuentosxplanxbc) {
		this.cntdescuentosxplanxbc = cntdescuentosxplanxbc;
	}

	public BigInteger getCntdescuentosxplanultimos12meses() {
		return this.cntdescuentosxplanultimos12meses;
	}

	public void setCntdescuentosxplanultimos12meses(BigInteger cntdescuentosxplanultimos12meses) {
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

	public BigInteger getCntregaloserviciosxplanminxbc() {
		return this.cntregaloserviciosxplanminxbc;
	}

	public void setCntregaloserviciosxplanminxbc(BigInteger cntregaloserviciosxplanminxbc) {
		this.cntregaloserviciosxplanminxbc = cntregaloserviciosxplanminxbc;
	}

	public BigInteger getCntdesserviciosxplanminxbc() {
		return this.cntdesserviciosxplanminxbc;
	}

	public void setCntdesserviciosxplanminxbc(BigInteger cntdesserviciosxplanminxbc) {
		this.cntdesserviciosxplanminxbc = cntdesserviciosxplanminxbc;
	}

	public BigInteger getCntregaloserviciosxplandatxbc() {
		return this.cntregaloserviciosxplandatxbc;
	}

	public void setCntregaloserviciosxplandatxbc(BigInteger cntregaloserviciosxplandatxbc) {
		this.cntregaloserviciosxplandatxbc = cntregaloserviciosxplandatxbc;
	}

	public BigInteger getCntdesserviciosxplandatxbc() {
		return this.cntdesserviciosxplandatxbc;
	}

	public void setCntdesserviciosxplandatxbc(BigInteger cntdesserviciosxplandatxbc) {
		this.cntdesserviciosxplandatxbc = cntdesserviciosxplandatxbc;
	}

	public BigInteger getCntregaloserviciosxplansmsxbc() {
		return this.cntregaloserviciosxplansmsxbc;
	}

	public void setCntregaloserviciosxplansmsxbc(BigInteger cntregaloserviciosxplansmsxbc) {
		this.cntregaloserviciosxplansmsxbc = cntregaloserviciosxplansmsxbc;
	}

	public BigInteger getCntdesserviciosxplansmsxbc() {
		return this.cntdesserviciosxplansmsxbc;
	}

	public void setCntdesserviciosxplansmsxbc(BigInteger cntdesserviciosxplansmsxbc) {
		this.cntdesserviciosxplansmsxbc = cntdesserviciosxplansmsxbc;
	}

	public BigInteger getCntdesserviciosxplanultimos12meses() {
		return this.cntdesserviciosxplanultimos12meses;
	}

	public void setCntdesserviciosxplanultimos12meses(BigInteger cntdesserviciosxplanultimos12meses) {
		this.cntdesserviciosxplanultimos12meses = cntdesserviciosxplanultimos12meses;
	}

	public String getIdmensaje() {
		return this.idmensaje;
	}

	public void setIdmensaje(String idmensaje) {
		this.idmensaje = idmensaje;
	}

	@XmlTransient
	public Collection<EocAuditoriaColaDto> getEocAuditoriaColaCollection() {
		return this.eocAuditoriaColaCollection;
	}

	public void setEocAuditoriaColaCollection(Collection<EocAuditoriaColaDto> eocAuditoriaColaCollection) {
		this.eocAuditoriaColaCollection = eocAuditoriaColaCollection;
	}
}
