// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.exception.config;

import co.com.claro.inspira.utilities.common.exception.dto.InternalException;
import co.com.claro.inspira.utilities.common.exception.dto.BackendException;
import co.com.claro.inspira.utilities.common.exception.dto.EricssonFault;
import co.com.claro.inspira.utilities.common.exception.dto.EricssonFaultMessageType;
import java.util.Arrays;
import co.com.claro.inspira.utilities.common.httpresponse.constant.EHttpInternalErrorResponse;
import co.com.claro.inspira.utilities.common.httpresponse.constant.EHttpBackendResponse;
import co.com.claro.inspira.utilities.common.httpresponse.constant.EHttpConectivityResponse;
import co.com.claro.inspira.utilities.common.httpresponse.constant.EHttpSuccessResponse;
import java.util.List;

public class ResponseCodeValidator
{
    private List<EHttpSuccessResponse> successCodes;
    private List<EHttpConectivityResponse> conectivityCodes;
    private List<EHttpBackendResponse> backendCodes;
    private List<EHttpInternalErrorResponse> internalCodes;
    
    public ResponseCodeValidator() {
        this.successCodes = Arrays.asList(EHttpSuccessResponse.values());
        this.conectivityCodes = Arrays.asList(EHttpConectivityResponse.values());
        this.backendCodes = Arrays.asList(EHttpBackendResponse.values());
        this.internalCodes = Arrays.asList(EHttpInternalErrorResponse.values());
    }
    
    public boolean validarCodigoRespuesta(final String responseCode, final String appName) throws EricssonFault, BackendException, Exception {
        final boolean isSuccessResponse = this.successCodes.parallelStream().anyMatch(code -> code.getCode().equals(responseCode));
        if (isSuccessResponse) {
            return true;
        }
        final boolean isConectivityError = this.conectivityCodes.parallelStream().anyMatch(code -> code.getCode().equals(responseCode));
        final boolean isBackendError = this.backendCodes.parallelStream().anyMatch(code -> code.getCode().equals(responseCode));
        final boolean isInternalError = this.internalCodes.parallelStream().anyMatch(code -> code.getCode().equals(responseCode));
        if (isConectivityError) {
            final EricssonFaultMessageType faultInfo = new EricssonFaultMessageType();
            faultInfo.setErrorLocation(appName);
            faultInfo.setFaultSource(EricssonFault.class.getSimpleName() + " - code " + responseCode);
            throw new EricssonFault(null, faultInfo);
        }
        if (isBackendError) {
            throw new BackendException(null, null, appName, BackendException.class.getSimpleName() + " - code " + responseCode);
        }
        if (isInternalError) {
            throw new InternalException(null, null, appName, Exception.class.getSimpleName() + " - code " + responseCode);
        }
        return false;
    }
}
