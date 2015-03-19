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
package org.sola.services.boundary.transferobjects.system;

import org.sola.services.common.contracts.AbstractReadWriteTO;

public class BrValidationTO extends AbstractReadWriteTO {
    private String id;
    private String brId;
    private String severityCode;
    private String targetCode;
    private String targetApplicationMoment;
    private String targetServiceMoment;
    private String targetRegMoment;
    private String targetRequestTypeCode;
    private String targetRrrTypeCode;
    private int orderOfExecution;
    
    public BrValidationTO(){
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrId() {
        return brId;
    }

    public void setBrId(String brId) {
        this.brId = brId;
    }

    public int getOrderOfExecution() {
        return orderOfExecution;
    }

    public void setOrderOfExecution(int orderOfExecution) {
        this.orderOfExecution = orderOfExecution;
    }

    public String getSeverityCode() {
        return severityCode;
    }

    public void setSeverityCode(String severityCode) {
        this.severityCode = severityCode;
    }

    public String getTargetApplicationMoment() {
        return targetApplicationMoment;
    }

    public void setTargetApplicationMoment(String targetApplicationMoment) {
        this.targetApplicationMoment = targetApplicationMoment;
    }

    public String getTargetCode() {
        return targetCode;
    }

    public void setTargetCode(String targetCode) {
        this.targetCode = targetCode;
    }

    public String getTargetRegMoment() {
        return targetRegMoment;
    }

    public void setTargetRegMoment(String targetRegMoment) {
        this.targetRegMoment = targetRegMoment;
    }

    public String getTargetRequestTypeCode() {
        return targetRequestTypeCode;
    }

    public void setTargetRequestTypeCode(String targetRequestTypeCode) {
        this.targetRequestTypeCode = targetRequestTypeCode;
    }

    public String getTargetRrrTypeCode() {
        return targetRrrTypeCode;
    }

    public void setTargetRrrTypeCode(String targetRrrTypeCode) {
        this.targetRrrTypeCode = targetRrrTypeCode;
    }

    public String getTargetServiceMoment() {
        return targetServiceMoment;
    }

    public void setTargetServiceMoment(String targetServiceMoment) {
        this.targetServiceMoment = targetServiceMoment;
    }
}
