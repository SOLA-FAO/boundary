/**
 * ******************************************************************************************
 * Copyright (C) 2015 - Food and Agriculture Organization of the United Nations (FAO).
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.casemanagement;

import java.math.BigDecimal;
import java.util.Date;
import org.sola.services.common.contracts.AbstractIdTO;

/**
 *
 * @author soladev
 */
public class ServiceTO extends AbstractIdTO {

    private String applicationId; 
    private String requestTypeCode;
    private int serviceOrder;
    private Date lodgingDatetime;
    private Date expectedCompletionDate;
    private String statusCode;
    private String actionCode;
    private String actionNotes;
    private BigDecimal baseFee; 
    private BigDecimal areaFee;
    private BigDecimal valueFee; 
    private String concatenatedName;

    public String getConcatenatedName() {
        return concatenatedName;
    }

    public void setConcatenatedName(String concatenatedName) {
        this.concatenatedName = concatenatedName;
    }

    public ServiceTO() {
        super(); 
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRequestTypeCode() {
        return requestTypeCode;
    }

    public void setRequestTypeCode(String requestTypeCode) {
        this.requestTypeCode = requestTypeCode;
    }

    public int getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(int serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionNotes() {
        return actionNotes;
    }

    public void setActionNotes(String actionNotes) {
        this.actionNotes = actionNotes;
    }

    public BigDecimal getAreaFee() {
        return areaFee;
    }

    public void setAreaFee(BigDecimal areaFee) {
        this.areaFee = areaFee;
    }

    public BigDecimal getBaseFee() {
        return baseFee;
    }

    public void setBaseFee(BigDecimal baseFee) {
        this.baseFee = baseFee;
    }

    public Date getExpectedCompletionDate() {
        return expectedCompletionDate;
    }

    public void setExpectedCompletionDate(Date expectedCompletionDate) {
        this.expectedCompletionDate = expectedCompletionDate;
    }

    public Date getLodgingDatetime() {
        return lodgingDatetime;
    }

    public void setLodgingDatetime(Date lodgingDatetime) {
        this.lodgingDatetime = lodgingDatetime;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public BigDecimal getValueFee() {
        return valueFee;
    }

    public void setValueFee(BigDecimal valueFee) {
        this.valueFee = valueFee;
    }
    
}
