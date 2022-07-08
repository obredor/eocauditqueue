// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.exception.dto;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.bind.JAXBElement;

public class EricssonFaultMessageType
{
    protected String enterpriseServiceName;
    protected String enterpriseServiceOperationName;
    protected JAXBElement<String> originSystem;
    protected String idAudit;
    protected String idESBTransaction;
    protected String idBusinessTransaction;
    protected String errorCode;
    protected String errorMessage;
    protected String errorLocation;
    protected XMLGregorianCalendar date;
    protected Object faultSource;
    
    public String getEnterpriseServiceName() {
        return this.enterpriseServiceName;
    }
    
    public void setEnterpriseServiceName(final String value) {
        this.enterpriseServiceName = value;
    }
    
    public String getEnterpriseServiceOperationName() {
        return this.enterpriseServiceOperationName;
    }
    
    public void setEnterpriseServiceOperationName(final String value) {
        this.enterpriseServiceOperationName = value;
    }
    
    public JAXBElement<String> getOriginSystem() {
        return this.originSystem;
    }
    
    public void setOriginSystem(final JAXBElement<String> value) {
        this.originSystem = value;
    }
    
    public String getIdAudit() {
        return this.idAudit;
    }
    
    public void setIdAudit(final String value) {
        this.idAudit = value;
    }
    
    public String getIdESBTransaction() {
        return this.idESBTransaction;
    }
    
    public void setIdESBTransaction(final String value) {
        this.idESBTransaction = value;
    }
    
    public String getIdBusinessTransaction() {
        return this.idBusinessTransaction;
    }
    
    public void setIdBusinessTransaction(final String value) {
        this.idBusinessTransaction = value;
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(final String value) {
        this.errorCode = value;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public void setErrorMessage(final String value) {
        this.errorMessage = value;
    }
    
    public String getErrorLocation() {
        return this.errorLocation;
    }
    
    public void setErrorLocation(final String value) {
        this.errorLocation = value;
    }
    
    public XMLGregorianCalendar getDate() {
        return this.date;
    }
    
    public void setDate(final XMLGregorianCalendar value) {
        this.date = value;
    }
    
    public Object getFaultSource() {
        return this.faultSource;
    }
    
    public void setFaultSource(final Object value) {
        this.faultSource = value;
    }
}
