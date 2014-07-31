package org.sola.services.boundary.transferobjects.claim;

import org.sola.services.common.contracts.AbstractReadWriteTO;

public class ClaimLocationTO extends AbstractReadWriteTO {
    private String id;
    private String claimId;
    private String mappedLocation;
    private String gpsLocation;
    private String description;
    
    public ClaimLocationTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public String getMappedLocation() {
        return mappedLocation;
    }

    public void setMappedLocation(String mappedLocation) {
        this.mappedLocation = mappedLocation;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
