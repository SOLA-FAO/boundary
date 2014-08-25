package org.sola.services.boundary.transferobjects.search;

import java.util.Date;
import org.sola.services.common.contracts.AbstractTO;

public class ClaimSearchParamsTO extends AbstractTO {
    String description;
    String claimantName;
    String statusCode;
    String languageCode;
    boolean searchByUser = false;
    String claimNumber;
    Date lodgementDateFrom;
    Date lodgementDateTo;
    
    public ClaimSearchParamsTO(){
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
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

    public Date getLodgementDateFrom() {
        return lodgementDateFrom;
    }

    public void setLodgementDateFrom(Date lodgementDateFrom) {
        this.lodgementDateFrom = lodgementDateFrom;
    }

    public Date getLodgementDateTo() {
        return lodgementDateTo;
    }

    public void setLodgementDateTo(Date lodgementDateTo) {
        this.lodgementDateTo = lodgementDateTo;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public boolean isSearchByUser() {
        return searchByUser;
    }

    public void setSearchByUser(boolean searchByUser) {
        this.searchByUser = searchByUser;
    }
}
