/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice,this list
 *       of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright notice,this list
 *       of conditions and the following disclaimer in the documentation and/or other
 *       materials provided with the distribution.
 *    3. Neither the name of FAO nor the names of its contributors may be used to endorse or
 *       promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT
 * OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.transferobjects.administrative;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sola.services.boundary.transferobjects.casemanagement.PartySummaryTO;
import org.sola.services.boundary.transferobjects.casemanagement.SourceTO;
import org.sola.services.common.contracts.AbstractIdTO;

public class RrrTO extends AbstractIdTO {
    
    private String baUnitId;
    private String typeCode;
    private String nr;
    private String statusCode;
    private boolean primary;
    private Date registrationDate;
    private String transactionId;
    private Date expirationDate;
    private Double share;
    private BigDecimal amount;
    private Date dueDate;
    private BigDecimal mortgageInterestRate;
    private Integer mortgageRanking;
    private String mortgageTypeCode;
    private boolean locked;
    private List<SourceTO> sourceList;
    private List<RrrShareTO> rrrShareList;
    private BaUnitNotationTO notation;
    private List<PartySummaryTO> rightHolderList;
    private List<ConditionForRrrTO> conditionsList;
    private String concatenatedName;

    public String getConcatenatedName() {
        return concatenatedName;
    }

    public void setConcatenatedName(String concatenatedName) {
        this.concatenatedName = concatenatedName;
    }
    
    public RrrTO(){
        super();
    }

    public String getBaUnitId() {
        return baUnitId;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal mortgageAmount) {
        this.amount = mortgageAmount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getMortgageInterestRate() {
        return mortgageInterestRate;
    }

    public void setMortgageInterestRate(BigDecimal mortgageInterestRate) {
        this.mortgageInterestRate = mortgageInterestRate;
    }

    public Integer getMortgageRanking() {
        return mortgageRanking;
    }

    public void setMortgageRanking(Integer mortgageRanking) {
        this.mortgageRanking = mortgageRanking;
    }

    public String getMortgageTypeCode() {
        return mortgageTypeCode;
    }

    public void setMortgageTypeCode(String mortgageTypeCode) {
        this.mortgageTypeCode = mortgageTypeCode;
    }

    public BaUnitNotationTO getNotation() {
        return notation;
    }

    public void setNotation(BaUnitNotationTO notation) {
        this.notation = notation;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public List<PartySummaryTO> getRightHolderList() {
        return rightHolderList;
    }

    public void setRightHolderList(List<PartySummaryTO> rightHolderList) {
        this.rightHolderList = rightHolderList;
    }

    public void addRightHolder(PartySummaryTO partySummaryTO) {
        if (rightHolderList == null) {
            rightHolderList = new ArrayList<PartySummaryTO>();
        }
        rightHolderList.add(partySummaryTO);
    }
    
    public Double getShare() {
        return share;
    }

    public void setShare(Double share) {
        this.share = share;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<SourceTO> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<SourceTO> sourceList) {
        this.sourceList = sourceList;
    }

    public void addSource(SourceTO source) {
        if (sourceList == null) {
            sourceList = new ArrayList<SourceTO>();
        }
        sourceList.add(source);
    }
    
    public List<RrrShareTO> getRrrShareList() {
        return rrrShareList;
    }

    public void setRrrShareList(List<RrrShareTO> rrrSharesList) {
        this.rrrShareList = rrrSharesList;
    }

    public void addRrrShare(RrrShareTO rrrShareTO) {
        if (rrrShareList == null) {
            rrrShareList = new ArrayList<RrrShareTO>();
        }
        rrrShareList.add(rrrShareTO);
    }

    public List<ConditionForRrrTO> getConditionsList() {
        return conditionsList;
    }

    public void setConditionsList(List<ConditionForRrrTO> conditionsList) {
        this.conditionsList = conditionsList;
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

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

}
