// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.httpresponse.constant;

public enum EHttpInternalErrorResponse
{
    PAYLOAD_TOO_LARGE("413"), 
    URI_TOO_LONG("414"), 
    RANGE_NOT_SATISFIABLE("416"), 
    REQUEST_NOT_EXTENDED("510"), 
    NETWORK_AUTHENTICATION_REQUIRED("511");
    
    private String code;
    
    private EHttpInternalErrorResponse(final String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
}
