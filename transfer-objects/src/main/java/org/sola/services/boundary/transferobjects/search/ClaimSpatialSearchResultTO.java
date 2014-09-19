package org.sola.services.boundary.transferobjects.search;

import org.sola.services.common.contracts.AbstractTO;

public class ClaimSpatialSearchResultTO extends AbstractTO {
    private String id;
    private String statusCode;
    private int version;
    
    public ClaimSpatialSearchResultTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
