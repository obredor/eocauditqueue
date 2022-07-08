// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.httpresponse.constant;

public enum EHttpBackendResponse
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
    BAD_REQUEST("400"), 
    UNAUTHORIZED("401"), 
    PAYMENT_REQUIRED("402"), 
    FORBIDDEN("403"), 
    NOT_FOUND("404"), 
    METHOD_NOT_ALLOWED("405"), 
    NOT_ACCEPTABLE("406"), 
    PROXY_AUTHENTICATION("407"), 
    CONFLICT_CURRENT_STATE("409"), 
    GONE_RESOURCE_NO_LONGER("410"), 
    LENGTH_REQUIRED("411"), 
    PRECONDITION_FAILED("412"), 
    UNSUPPORTED_MEDIA_TYPE("413"), 
    EXPECTATION_FAILED("417"), 
    TEAPOT_HTTP("418"), 
    MISDIRECTED_REQUEST("421"), 
    UNPROCESSABLE_ENTITY("422"), 
    LOCKED_RESOURCE("423"), 
    FAILED_DEPENDENCY("424"), 
    TOO_EARLY("425"), 
    UPGRADE_REQUIRED("426"), 
    PRECONDITION_REQUIRED("428"), 
    TOO_MANY_REQUESTS("429"), 
    REQUEST_HEADER_FIELDS_LARGE("431"), 
    UNAVAILABLE_FOR_LEGAL_REASONS("451"), 
    INTERNAL_SERVER_ERROR("500"), 
    RESOURCE_NOT_IMPLEMENTED("501"), 
    BAD_GATEWAY("502"), 
    SERVICE_UNAVAILABLE("503"), 
    HTTP_VERSION_NOT_SUPPORTED("505"), 
    VARIANT_ALSO_NEGOTIATES("506"), 
    INSUFFICIENT_STORAGE("507"), 
    LOOP_DETECTED("508");
    
    private String code;
    
    private EHttpBackendResponse(final String code) {
        this.code = code;
    }
    
    public String getCode() {
        return this.code;
    }
}
