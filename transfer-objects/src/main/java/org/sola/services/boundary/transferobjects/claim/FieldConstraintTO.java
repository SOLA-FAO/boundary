package org.sola.services.boundary.transferobjects.claim;

import java.math.BigDecimal;
import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class FieldConstraintTO extends AbstractReadWriteTO {
    private String id;
    private String name;
    private String displayName;
    private String errorMsg;
    private String format;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String fieldTemplateId;
    private String fieldConstraintType;
    private List<FieldConstraintOptionTO> fieldConstraintOptionList;
    
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

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public BigDecimal getMinValue() {
        return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
        this.minValue = minValue;
    }

    public BigDecimal getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
        this.maxValue = maxValue;
    }

    public String getFieldTemplateId() {
        return fieldTemplateId;
    }

    public void setFieldTemplateId(String fieldTemplateId) {
        this.fieldTemplateId = fieldTemplateId;
    }

    public String getFieldConstraintType() {
        return fieldConstraintType;
    }

    public void setFieldConstraintType(String fieldConstraintType) {
        this.fieldConstraintType = fieldConstraintType;
    }

    public List<FieldConstraintOptionTO> getFieldConstraintOptionList() {
        return fieldConstraintOptionList;
    }

    public void setFieldConstraintOptionList(List<FieldConstraintOptionTO> fieldConstraintOptionList) {
        this.fieldConstraintOptionList = fieldConstraintOptionList;
    }
    
    public FieldConstraintTO(){
    }
}
