// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.exception.dto;

public class EricssonFault extends Exception
{
    private EricssonFaultMessageType faultInfo;
    
    public EricssonFault(final String message, final EricssonFaultMessageType faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }
    
    public EricssonFault(final String message, final EricssonFaultMessageType faultInfo, final Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }
    
    public EricssonFaultMessageType getFaultInfo() {
        return this.faultInfo;
    }
}
