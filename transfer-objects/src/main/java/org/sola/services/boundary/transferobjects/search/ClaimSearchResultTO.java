package org.sola.services.boundary.transferobjects.search;

import java.util.Date;
import org.sola.services.common.contracts.AbstractTO;

public class ClaimSearchResultTO extends AbstractTO {
    
    private String id;
    private String nr;
    private Date lodgementDate;
    private Date challengeExpiryDate;
    private Date decisionDate;
    private String description;
    private String challengedClaimId;
    private String claimantId;
    private String claimantName;
    private String statusCode;
    private String statusName;
    private Long claimArea;
    private int version;
    
    public ClaimSearchResultTO(){
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

    public String getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(String claimantId) {
        this.claimantId = claimantId;
    }

    public String getClaimantName() {
        return claimantName;
    }

    public void setClaimantName(String claimantName) {
        this.claimantName = claimantName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public Long getClaimArea() {
        return claimArea;
    }

    public void setClaimArea(Long claimArea) {
        this.claimArea = claimArea;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
