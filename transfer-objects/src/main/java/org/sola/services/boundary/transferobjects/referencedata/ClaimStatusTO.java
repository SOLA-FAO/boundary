package org.sola.services.boundary.transferobjects.referencedata;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.sola.services.common.contracts.AbstractCodeTO;

@XmlRootElement(name = "claimStatus")
@XmlAccessorType(XmlAccessType.NONE)
public class ClaimStatusTO extends AbstractCodeTO {
    public ClaimStatusTO(){
        super();
    }
}
