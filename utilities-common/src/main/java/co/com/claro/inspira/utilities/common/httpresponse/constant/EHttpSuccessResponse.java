// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.httpresponse.constant;

public enum EHttpSuccessResponse
{
    RESOURCE_CONTINUE("100"), 
    SWITCHING_PROTOCOLS("101"), 
    RESOURCE_PROCESSING("102"), 
    OK_RESPONSE("200"), 
    RESOURCE_CREATED("201"), 
    RESOURCE_ACCEPTED("202"), 
    NON_AUTHORITATIVE_INFORMATION("203"), 
    NO_CONTENT("204"), 
    RESET_CONTENT("205"), 
    PARTIAL_CONTENT("206"), 
    MULTI_STATUS("207"), 
    ALREADY_REPORTED("208"), 
    IM_USED("226");
    
    private String code;
    
    private EHttpSuccessResponse(final String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
}
