package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.sola.services.common.contracts.AbstractReadWriteTO;

@XmlRootElement(name = "claim")
@XmlAccessorType(XmlAccessType.NONE)
@XmlType (propOrder={"id", "nr", "lodgementDate", "challengeExpiryDate", "decisionDate", 
    "description", "challengedClaimId", "statusCode", "claimant", "attachments", 
    "mappedGeometry", "gpsGeometry"})
public class ClaimTO extends AbstractReadWriteTO {
    @XmlElement
    private String id;
    @XmlElement
    private String nr;
    @XmlElement
    private Date lodgementDate;
    @XmlElement
    private Date challengeExpiryDate;
    @XmlElement
    private Date decisionDate;
    @XmlElement
    private String description;
    @XmlElement
    private String challengedClaimId;
    @XmlElement
    private ClaimantTO claimant;
    @XmlElement
    private List<AttachmentTO> attachments;
    @XmlElement
    private String mappedGeometry;
    @XmlElement
    private String gpsGeometry;
    @XmlElement
    private String statusCode;
    
    public ClaimTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public Date getLodgementDate() {
        return lodgementDate;
    }

    public void setLodgementDate(Date lodgementDate) {
        this.lodgementDate = lodgementDate;
    }

    public Date getChallengeExpiryDate() {
        return challengeExpiryDate;
    }

    public void setChallengeExpiryDate(Date challengeExpiryDate) {
        this.challengeExpiryDate = challengeExpiryDate;
    }

    public Date getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(Date decisionDate) {
        this.decisionDate = decisionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChallengedClaimId() {
        return challengedClaimId;
    }

    public void setChallengedClaimId(String challengedClaimId) {
        this.challengedClaimId = challengedClaimId;
    }

    public ClaimantTO getClaimant() {
        return claimant;
    }

    public void setClaimant(ClaimantTO claimant) {
        this.claimant = claimant;
    }

    public List<AttachmentTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentTO> attachments) {
        this.attachments = attachments;
    }

    public String getMappedGeometry() {
        return mappedGeometry;
    }

    public void setMappedGeometry(String mappedGeometry) {
        this.mappedGeometry = mappedGeometry;
    }

    public String getGpsGeometry() {
        return gpsGeometry;
    }

    public void setGpsGeometry(String gpsGeometry) {
        this.gpsGeometry = gpsGeometry;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
