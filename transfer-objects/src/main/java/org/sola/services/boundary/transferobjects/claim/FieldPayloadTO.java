package org.sola.services.boundary.transferobjects.claim;

import java.math.BigDecimal;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class FieldPayloadTO extends AbstractReadWriteTO {
    private String id;
    private String name;
    private String displayName;
    private String fieldType;
    private String sectionElementPayloadId;
    private String stringPayload;
    private BigDecimal bigDecimalPayload;
    private boolean booleanPayload;
    private String fieldValueType;

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

    public String getSectionElementPayloadId() {
        return sectionElementPayloadId;
    }

    public void setSectionElementPayloadId(String sectionElementPayloadId) {
        this.sectionElementPayloadId = sectionElementPayloadId;
    }

    public String getStringPayload() {
        return stringPayload;
    }

    public void setStringPayload(String stringPayload) {
        this.stringPayload = stringPayload;
    }

    public BigDecimal getBigDecimalPayload() {
        return bigDecimalPayload;
    }

    public void setBigDecimalPayload(BigDecimal bigDecimalPayload) {
        this.bigDecimalPayload = bigDecimalPayload;
    }

    public boolean isBooleanPayload() {
        return booleanPayload;
    }

    public void setBooleanPayload(boolean booleanPayload) {
        this.booleanPayload = booleanPayload;
    }

    public String getFieldValueType() {
        return fieldValueType;
    }

    public void setFieldValueType(String fieldValueType) {
        this.fieldValueType = fieldValueType;
    }
    
    public FieldPayloadTO(){
    }
}
