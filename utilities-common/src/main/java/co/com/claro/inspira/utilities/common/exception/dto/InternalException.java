// 
// Decompiled by Procyon v0.5.36
// 

package co.com.claro.inspira.utilities.common.exception.dto;

public class InternalException extends Exception
{
    private String code;
    private String description;
    private String location;
    private String technicalDescription;
    
    public InternalException(final String code, final String description) {
        super(description);
        this.code = code;
        this.description = description;
    }
    
    public InternalException(final String code, final String description, final String location, final String technicalDescription) {
        super(description);
        this.code = code;
        this.description = description;
        this.location = location;
        this.technicalDescription = technicalDescription;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(final String code) {
        this.code = code;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(final String location) {
        this.location = location;
    }
    
    public String getTechnicalDescription() {
        return this.technicalDescription;
    }
    
    public void setTechnicalDescription(final String technicalDescription) {
        this.technicalDescription = technicalDescription;
    }
}
