package org.sola.services.boundary.transferobjects.claim;

import java.util.Date;
import java.util.List;
import org.sola.services.common.contracts.AbstractReadWriteTO;

public class ClaimTO extends AbstractReadWriteTO {
    private String id;
    private String nr;
    private Date lodgementDate;
    private Date challengeExpiryDate;
    private Date decisionDate;
    private String description;
    private String challengedClaimId;
    private PartyTO claimant;
    private List<ClaimShareTO> shares;
    private List<AttachmentTO> attachments;
    private String mappedGeometry;
    private String gpsGeometry;
    private String statusCode;
    private String typeCode;
    
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

    public PartyTO getClaimant() {
        return claimant;
    }

    public void setClaimant(PartyTO claimant) {
        this.claimant = claimant;
    }

    public List<ClaimShareTO> getShares() {
        return shares;
    }

    public void setShares(List<ClaimShareTO> shares) {
        this.shares = shares;
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

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
}
