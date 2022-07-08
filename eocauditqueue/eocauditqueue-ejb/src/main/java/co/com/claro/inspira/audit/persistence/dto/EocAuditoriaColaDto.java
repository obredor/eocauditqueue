package co.com.claro.inspira.audit.persistence.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EocAuditoriaColaDto {
	
	private BigDecimal idAuditoria;

	private Date fechaCrea;

	private String usuarioCrea;

	private String reintento;

	private String observacion;

	private EocColaJmsDto idmensaje;

	public EocAuditoriaColaDto() {
	}

	public EocAuditoriaColaDto(BigDecimal idAuditoria) {
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

	public EocColaJmsDto getIdmensaje() {
		return this.idmensaje;
	}

	public void setIdmensaje(EocColaJmsDto idmensaje) {
		this.idmensaje = idmensaje;
	}
}
