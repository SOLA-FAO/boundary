package org.sola.services.boundary.transferobjects.search;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.sola.services.common.contracts.AbstractTO;

@XmlRootElement(name = "claimSpatialSearchResult")
@XmlAccessorType(XmlAccessType.NONE)
public class ClaimSpatialSearchResultTO extends AbstractTO {
    @XmlElement
    private String id;
    @XmlElement
    private String statusCode;
    
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
}
