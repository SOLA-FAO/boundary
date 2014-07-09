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
package org.sola.services.boundary.transferobjects.referencedata;

import java.math.BigDecimal;
import java.util.List;
import org.sola.services.common.contracts.AbstractCodeTO;

public class RequestTypeTO extends AbstractCodeTO {

    private int nrDaysToComplete;
    private String requestCategoryCode;
    private int nrPropertiesRequired;
    private String notationTemplate;
    private String typeActionCode;
    private String rrrTypeCode;
    private List<RequestTypeSourceTypeTO> sourceTypeCodes;
    private BigDecimal baseFee;
    private BigDecimal areaBaseFee;
    private BigDecimal valueBaseFee;
    private String displayGroupName;
    private String servicePanelCode;
    
    public RequestTypeTO() {
        super();
    }

    public int getNrDaysToComplete() {
        return nrDaysToComplete;
    }

    public void setNrDaysToComplete(int nrDaysToComplete) {
        this.nrDaysToComplete = nrDaysToComplete;
    }

    public int getNrPropertiesRequired() {
        return nrPropertiesRequired;
    }

    public void setNrPropertiesRequired(int nrPropertiesRequired) {
        this.nrPropertiesRequired = nrPropertiesRequired;
    }

    public String getRequestCategoryCode() {
        return requestCategoryCode;
    }

    public void setRequestCategoryCode(String requestCategoryCode) {
        this.requestCategoryCode = requestCategoryCode;
    }

    public String getNotationTemplate() {
        return notationTemplate;
    }

    public void setNotationTemplate(String notationTemplate) {
        this.notationTemplate = notationTemplate;
    }

    public String getTypeActionCode() {
        return typeActionCode;
    }

    public void setTypeActionCode(String typeActionCode) {
        this.typeActionCode = typeActionCode;
    }

    public String getRrrTypeCode() {
        return rrrTypeCode;
    }

    public void setRrrTypeCode(String rrrTypeCode) {
        this.rrrTypeCode = rrrTypeCode;
    }

    public List<RequestTypeSourceTypeTO> getSourceTypeCodes() {
        return sourceTypeCodes;
    }

    public void setSourceTypeCodes(List<RequestTypeSourceTypeTO> sourceTypeCodes) {
        this.sourceTypeCodes = sourceTypeCodes;
    }
    
    public BigDecimal getAreaBaseFee() {
        return areaBaseFee;
    }

    public void setAreaBaseFee(BigDecimal areaBaseFee) {
        this.areaBaseFee = areaBaseFee;
    }

    public BigDecimal getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(BigDecimal baseFee) {
        this.baseFee = baseFee;
    }

    public BigDecimal getValueBaseFee() {
        return valueBaseFee;
    }

    public void setValueBaseFee(BigDecimal valueBaseFee) {
        this.valueBaseFee = valueBaseFee;
    }

    public String getDisplayGroupName() {
        return displayGroupName;
    }

    public void setDisplayGroupName(String displayGroupName) {
        this.displayGroupName = displayGroupName;
    }

    public String getServicePanelCode() {
        return servicePanelCode;
    }

    public void setServicePanelCode(String servicePanelCode) {
        this.servicePanelCode = servicePanelCode;
    }
}
