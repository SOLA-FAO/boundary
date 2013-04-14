package org.sola.services.boundary.transferobjects.administrative;

import org.sola.services.common.contracts.AbstractIdTO;

public class LeaseConditionForRrrTO extends AbstractIdTO {
    private String rrrId;
    private String leaseConditionCode;
    private String customConditionText;
    
    public LeaseConditionForRrrTO(){
        super();
    }

    public String getCustomConditionText() {
        return customConditionText;
    }

    public void setCustomConditionText(String customConditionText) {
        this.customConditionText = customConditionText;
    }

    public String getLeaseConditionCode() {
        return leaseConditionCode;
    }

    public void setLeaseConditionCode(String leaseConditionCode) {
        this.leaseConditionCode = leaseConditionCode;
    }

    public String getRrrId() {
        return rrrId;
    }

    public void setRrrId(String rrrId) {
        this.rrrId = rrrId;
    }
}
