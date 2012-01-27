package org.sola.services.boundary.transferobjects.administrative;

import org.sola.services.common.contracts.AbstractVersionedTO;

public class RelatedBaUnitInfoTO extends AbstractVersionedTO {

    private String baUnitId;
    private String relatedBaUnitId;
    private String relationCode;
    private BaUnitBasicTO relatedBaUnit;
    
    public RelatedBaUnitInfoTO() {
        super();
    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public void setBaUnitId(String baUnitId) {
        this.baUnitId = baUnitId;
    }

    public BaUnitBasicTO getRelatedBaUnit() {
        return relatedBaUnit;
    }

    public void setRelatedBaUnit(BaUnitBasicTO relatedBaUnit) {
        this.relatedBaUnit = relatedBaUnit;
    }

    public String getRelatedBaUnitId() {
        return relatedBaUnitId;
    }

    public void setRelatedBaUnitId(String relatedBaUnitId) {
        this.relatedBaUnitId = relatedBaUnitId;
    }

    public String getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode = relationCode;
    }
}
