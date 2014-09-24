package org.sola.services.boundary.transferobjects.claim;

import org.sola.services.common.contracts.AbstractReadWriteTO;

public class FieldConstraintOptionTO extends AbstractReadWriteTO {
    private String id;
    private String name;
    private String displayName;
    private String fieldConstraintId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFieldConstraintId() {
        return fieldConstraintId;
    }

    public void setFieldConstraintId(String fieldConstraintId) {
        this.fieldConstraintId = fieldConstraintId;
    }
    
    public FieldConstraintOptionTO(){
    }
}
