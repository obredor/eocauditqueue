// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.exception.constant;

public enum EClaroFault
{
    FUNCTIONAL("01", "Error Funcional"), 
    BAD_REQUEST("02", "Error en la validacion del mensaje"), 
    CONNECTION("03", "Error de conectividad"), 
    BACKEND("04", "Error del backend"), 
    INTERNAL("05", "Error interno en el flujo");
    
    private String code;
    private String description;
    
    private EClaroFault(final String code, final String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getDescription() {
        return this.description;
    }
}
