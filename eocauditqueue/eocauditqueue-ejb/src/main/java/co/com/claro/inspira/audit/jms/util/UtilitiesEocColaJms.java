package co.com.claro.inspira.audit.jms.util;

import co.com.claro.inspira.audit.jms.config.LoadAuditQueueConfigProperties;
import co.com.claro.inspira.audit.jms.config.constant.EOperationProperty;
import co.com.claro.inspira.audit.persistence.entities.EocColaJms;
import co.com.claro.inspira.ecmparser.web.persistence.exception.PersistAuditException;
import co.com.claro.inspira.eocauditqueue.dto.Data;
import co.com.claro.inspira.eocauditqueue.dto.EocAuditQueueRequest;
import co.com.claro.inspira.eocauditqueue.dto.EquiposFinanciados;
import co.com.claro.inspira.eocauditqueue.dto.Indicadores;
import co.com.claro.inspira.eocauditqueue.dto.Planes;
import co.com.claro.inspira.eocauditqueue.dto.PlanesDetalles;
import co.com.claro.inspira.eocauditqueue.dto.SMS;
import co.com.claro.inspira.eocauditqueue.dto.ServiciosDetalles;
import co.com.claro.inspira.eocauditqueue.dto.ServiciosSumario;
import co.com.claro.inspira.eocauditqueue.dto.Voz;
import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import co.com.claro.inspira.utilities.common.exception.dto.InternalException;
import co.com.claro.inspira.utilities.common.util.Utilidades;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilitiesEocColaJms {
  public static String dateFormat;
  
  private static final LoadAuditQueueConfigProperties CONFIG = LoadAuditQueueConfigProperties.getInstance();
  
  public static EocColaJms convertirRequestEntidad(EocAuditQueueRequest request) {
    EocColaJms entidad = new EocColaJms();
    entidad.setContractid(request.getContractId());
    entidad.setBscscustomer(request.getBSCSCustomer());
    entidad.setServiceorderid(request.getServiceOrderId());
    validarEquiposFinanciados(request, entidad);
    validarPlanes(request, entidad);
    PlanesDetalles planesDetalles = request.getPlanesDetalles();
    if (planesDetalles != null) {
      entidad.setPlancode(planesDetalles.getPlanCode());
      validarIndicadoresDetalles(planesDetalles, entidad);
      ServiciosDetalles serviciosDetalles = planesDetalles.getServiciosDetalles();
      if (planesDetalles.getServiciosDetalles() != null) {
        validarData(serviciosDetalles, entidad);
        validarSms(serviciosDetalles, entidad);
        validarVoz(serviciosDetalles, entidad);
      } 
      ServiciosSumario serviciosSumario = planesDetalles.getServiciosSumario();
      if (serviciosSumario != null)
        validarServiciosSumarios(serviciosSumario, entidad); 
    } 
    return entidad;
  }
  
  private static void validarEquiposFinanciados(EocAuditQueueRequest request, EocColaJms entidad) {
    EquiposFinanciados equiposFinanciados = request.getEquiposFinanciados();
    if (equiposFinanciados != null && 
      !Utilities.validarListNull(equiposFinanciados.getIndicadores()))
      equiposFinanciados.getIndicadores().parallelStream()
        .forEach(indicador -> {
            switch (indicador.getName()) {
              case "CantEquiposFinanciadas":
                entidad.setCantequiposfinanciadas(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "SaldoInsolutoCreditosEquipos":
                entidad.setSaldoinsolutocreditosequipos(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
            } 
          }); 
  }
  
  private static void validarPlanes(EocAuditQueueRequest request, EocColaJms entidad) {
    Planes planes = request.getPlanes();
    if (planes != null && !Utilities.validarListNull(planes.getIndicadores()))
      planes.getIndicadores().parallelStream()
        .forEach(indicador -> {
            switch (indicador.getName()) {
              case "CantContratosFija":
                entidad.setCantcontratosfija(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CantPlanesMovilPosAlta":
                entidad.setCantplanesmovilposalta(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CantPlanesMovilPosMedia":
                entidad.setCantplanesmovilposmedia(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CantPlanesMovilPosBaja":
                entidad.setCantplanesmovilposbaja(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
            } 
          }); 
  }
  
  private static void validarIndicadoresDetalles(PlanesDetalles planesDetalles, EocColaJms entidad) {
    SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormat);
    if (!Utilities.validarListNull(planesDetalles.getIndicadores()))
      planesDetalles.getIndicadores().parallelStream()
        .forEach(indicador -> {
            switch (indicador.getName()) {
              case "CntDescuentosXPlanXBC":
                entidad.setCntdescuentosxplanxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CntDescuentosXPlanUltimos12Meses":
                entidad.setCntdescuentosxplanultimos12meses(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "TotalCFMXPlan12Meses":
                entidad.setTotalcfmxplan12meses(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "TotalDesXPlan12Meses":
                entidad.setTotaldesxplan12meses(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "UltimoValorDescuentoxPlan":
                entidad.setUltimovalordescuentoxplan(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "FechaUltimoDescuentoCFM":
                try {
                  entidad.setFechaultimodescuentocfm(simpleDate.parse(indicador.getValue()));
                } catch (ParseException parseException) {}
                break;
              case "SupsensionLuegoBonificacion":
                entidad.setSupsensionluegobonificacion(!Utilidades.validarCampoNulo(indicador.getValue()) ? Character.valueOf(indicador.getValue().charAt(0)) : null);
                break;
              case "FechaUltimoDowngrade":
                try {
                  entidad.setFechaultimodowngrade(simpleDate.parse(indicador.getValue()));
                } catch (ParseException parseException) {}
                break;
              case "FechaUltimoBlindaje":
                try {
                  entidad.setFechaultimoblindaje(simpleDate.parse(indicador.getValue()));
                } catch (ParseException parseException) {}
                break;
            } 
          }); 
  }
  
  private static void validarData(ServiciosDetalles serviciosDetalles, EocColaJms entidad) {
    if (!Utilities.validarListNull(serviciosDetalles.getData()))
      serviciosDetalles.getData().parallelStream().forEach(indicador -> {
            switch (indicador.getName()) {
              case "CntRegaloServiciosXPlanDatXBC":
                entidad.setCntregaloserviciosxplandatxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CntDesServiciosXPlanDatXBC":
                entidad.setCntdesserviciosxplandatxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
            } 
          }); 
  }
  
  private static void validarSms(ServiciosDetalles serviciosDetalles, EocColaJms entidad) {
    if (!Utilities.validarListNull(serviciosDetalles.getSMS()))
      serviciosDetalles.getSMS().parallelStream().forEach(indicador -> {
            switch (indicador.getName()) {
              case "CntRegaloServiciosXPlanSMSXBC":
                entidad.setCntregaloserviciosxplansmsxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CntDesServiciosXPlanSMSXBC":
                entidad.setCntdesserviciosxplansmsxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
            } 
          }); 
  }
  
  private static void validarVoz(ServiciosDetalles serviciosDetalles, EocColaJms entidad) {
    if (!Utilities.validarListNull(serviciosDetalles.getVoz()))
      serviciosDetalles.getVoz().parallelStream().forEach(indicador -> {
            switch (indicador.getName()) {
              case "CntRegaloServiciosXPlanMinXBC":
                entidad.setCntregaloserviciosxplanminxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
              case "CntDesServiciosXPlanMinXBC":
                entidad.setCntdesserviciosxplanminxbc(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
            } 
          }); 
  }
  
  private static void validarServiciosSumarios(ServiciosSumario serviciosSumario, EocColaJms entidad) {
    if (!Utilities.validarListNull(serviciosSumario.getIndicadores()))
      serviciosSumario.getIndicadores().parallelStream()
        .forEach(indicador -> {
            switch (indicador.getName()) {
              case "CntDesServiciosXPlanUltimos12Meses":
                entidad.setCntdesserviciosxplanultimos12meses(Utilities.asignarBigDecimal(indicador.getValue()));
                break;
            } 
          }); 
  }
  
  public static void consumirDB(EocColaJms entidad, int reintento, Connection connection) throws BackendException, InternalException, SQLException, PersistAuditException {
    CallableStatement statement = null;
    try {
      statement = connection.prepareCall(CONFIG
          .findProperty(EOperationProperty.AUDITORIA_INSERT_MESSAGE));
      statement.setString(1, entidad.getContractid());
      statement.setString(2, entidad.getBscscustomer());
      statement.setString(3, entidad.getServiceorderid());
      statement.setBigDecimal(4, entidad.getCantequiposfinanciadas());
      statement.setBigDecimal(5, entidad.getSaldoinsolutocreditosequipos());
      statement.setBigDecimal(6, entidad.getCantcontratosfija());
      statement.setBigDecimal(7, entidad.getCantplanesmovilposalta());
      statement.setBigDecimal(8, entidad.getCantplanesmovilposmedia());
      statement.setBigDecimal(9, entidad.getCantplanesmovilposbaja());
      statement.setString(10, entidad.getPlancode());
      statement.setBigDecimal(11, entidad.getCntdescuentosxplanxbc());
      statement.setBigDecimal(12, entidad.getCntdescuentosxplanultimos12meses());
      statement.setBigDecimal(13, entidad.getTotalcfmxplan12meses());
      statement.setBigDecimal(14, entidad.getTotaldesxplan12meses());
      statement.setBigDecimal(15, entidad.getUltimovalordescuentoxplan());
      if (entidad.getFechaultimodescuentocfm() != null) {
        statement.setDate(16, new Date(entidad.getFechaultimodescuentocfm().getTime()));
      } else {
        statement.setDate(16, (Date)null);
      } 
      String suspensionBonificacion = null;
      if (entidad.getSupsensionluegobonificacion() != null)
        suspensionBonificacion = "" + entidad.getSupsensionluegobonificacion(); 
      statement.setString(17, suspensionBonificacion);
      if (entidad.getFechaultimodowngrade() != null) {
        statement.setDate(18, new Date(entidad.getFechaultimodowngrade().getTime()));
      } else {
        statement.setDate(18, (Date)null);
      } 
      if (entidad.getFechaultimoblindaje() != null) {
        statement.setDate(19, new Date(entidad.getFechaultimoblindaje().getTime()));
      } else {
        statement.setDate(19, (Date)null);
      } 
      statement.setBigDecimal(20, entidad.getCntregaloserviciosxplanminxbc());
      statement.setBigDecimal(21, entidad.getCntdesserviciosxplanminxbc());
      statement.setBigDecimal(22, entidad.getCntregaloserviciosxplandatxbc());
      statement.setBigDecimal(23, entidad.getCntdesserviciosxplandatxbc());
      statement.setBigDecimal(24, entidad.getCntregaloserviciosxplansmsxbc());
      statement.setBigDecimal(25, entidad.getCntdesserviciosxplansmsxbc());
      statement.setBigDecimal(26, entidad.getCntdesserviciosxplanultimos12meses());
      statement.setString(27, entidad.getIdmensaje());
      statement.execute();
      statement.close();
    } catch (SQLException ex) {
      if (statement != null)
        statement.close(); 
      throw new PersistAuditException(ex.getLocalizedMessage(), ex);
    } 
  }
}
