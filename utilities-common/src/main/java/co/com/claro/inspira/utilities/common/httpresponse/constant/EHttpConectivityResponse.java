// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.httpresponse.constant;

public enum EHttpConectivityResponse
{
    MULTIPLE_CHOICES("300"), 
    MOVED_PERMANENTLY("301"), 
    MOVED_TEMPORARILY("302"), 
    ANOTHER_URI("303"), 
    RESOURCE_NOT_MODIFIED("304"), 
    USE_PROXY("305"), 
    SWITCH_PROXY("306"), 
    TEMPORARY_REDIRECT("307"), 
    PERMANENT_REDIRECT("308"), 
    REQUEST_TIMEOUT("408"), 
    GATEWAY_TIMEOUT("504");
    
    private String code;
    
    private EHttpConectivityResponse(final String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
}
