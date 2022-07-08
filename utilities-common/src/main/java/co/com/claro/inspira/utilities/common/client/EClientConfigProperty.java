// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.client;

public enum EClientConfigProperty
{
    ENDPOINT("service.endpoint"), 
    MIN_IDLE("pool.minidle"), 
    MAX_IDLE("pool.maxidle"), 
    MAX_TOTAL("pool.maxtotal");
    
    private String value;
    
    private EClientConfigProperty(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
