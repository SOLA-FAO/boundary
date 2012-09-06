/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO). All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this list of conditions
 * and the following disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice,this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.transferobjects.casemanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sola.services.common.contracts.AbstractIdTO;

/**
 *
 * @author dounnaah
 */
public class ApplicationTO extends AbstractIdTO {

    private String nr;
    private Date lodgingDatetime;
    private Date expectedCompletionDate;
    private String assigneeId;
    private Date assignedDatetime;
    private byte[] location;
    private String statusCode;
    private BigDecimal servicesFee;
    private BigDecimal tax;
    private BigDecimal totalFee;
    private BigDecimal totalAmountPaid;
    private boolean feePaid;
    private String receiptRef;
    private String actionCode;
    private String actionNotes;
    private PartyTO contactPerson;
    private PartySummaryTO agent;
    private List<ServiceTO> serviceList;
    private List<ApplicationPropertyTO> propertyList;
    private List<SourceTO> sourceList;

    public ApplicationTO() {
        super();
    }

    public Date getAssignedDatetime() {
        return assignedDatetime;
    }

    public void setAssignedDatetime(Date assignedDatetime) {
        this.assignedDatetime = assignedDatetime;
    }

    public PartyTO getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(PartyTO contactPerson) {
        this.contactPerson = contactPerson;
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

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(String assigneeLoginName) {
        this.assigneeId = assigneeLoginName;
    }

    public boolean isFeePaid() {
        return feePaid;
    }

    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }

    public String getReceiptRef() {
        return receiptRef;
    }

    public void setReceiptRef(String receiptRef) {
        this.receiptRef = receiptRef;
    }

    public byte[] getLocation() {
        return location;
    }

    public void setLocation(byte[] location) { //NOSONAR
        this.location = location; //NOSONAR
    }

    public BigDecimal getServicesFee() {
        return servicesFee;
    }

    public void setServicesFee(BigDecimal servicesFee) {
        this.servicesFee = servicesFee;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(BigDecimal totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public List<ServiceTO> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceTO> serviceList) {
        this.serviceList = serviceList;
    }

    public void addService(ServiceTO service) {
        if (serviceList == null) {
            serviceList = new ArrayList<ServiceTO>();
        }
        serviceList.add(service);
    }

    public void addApplicationProperty(ApplicationPropertyTO appProperty) {
        if (propertyList == null) {
            propertyList = new ArrayList<ApplicationPropertyTO>();
        }
        propertyList.add(appProperty);
    }

    public void addSource(SourceTO source) {
        if (sourceList == null) {
            sourceList = new ArrayList<SourceTO>();
        }
        sourceList.add(source);
    }

    public PartySummaryTO getAgent() {
        return agent;
    }

    public void setAgent(PartySummaryTO agent) {
        this.agent = agent;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    public String getActionNotes() {
        return actionNotes;
    }

    public void setActionNotes(String actionNotes) {
        this.actionNotes = actionNotes;
    }

    public List<ApplicationPropertyTO> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<ApplicationPropertyTO> propertyList) {
        this.propertyList = propertyList;
    }

    public List<SourceTO> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<SourceTO> sourceList) {
        this.sourceList = sourceList;
    }
}
