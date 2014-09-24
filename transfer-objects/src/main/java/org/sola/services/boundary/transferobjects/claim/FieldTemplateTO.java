package org.sola.services.boundary.transferobjects.claim;

import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class FieldTemplateTO extends AbstractReadWriteTO {
    private String id;
    private String name;
    private String displayName;
    private String fieldType;
    private String sectionTemplateId;
    private String hint;
    private List<FieldConstraintTO> fieldConstraintList;
    
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

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getSectionTemplateId() {
        return sectionTemplateId;
    }

    public void setSectionTemplateId(String sectionTemplateId) {
        this.sectionTemplateId = sectionTemplateId;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public List<FieldConstraintTO> getFieldConstraintList() {
        return fieldConstraintList;
    }

    public void setFieldConstraintList(List<FieldConstraintTO> fieldConstraintList) {
        this.fieldConstraintList = fieldConstraintList;
    }
    
    public FieldTemplateTO(){
    }
}
