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

import java.util.Date;
import org.sola.services.common.contracts.AbstractVersionedTO;

/**
 *
 * @author soladev
 */
public class ApplicationLogTO extends AbstractVersionedTO {

    private String applicationId;
    private String actionType;
    private String serviceOrder;
    private String serviceType;
    private Date changeTime;
    private String userFullname;
    private String actionDescription;

    public ApplicationLogTO() {
        super();
    }

    /**
     * @return the applicationId
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * @param applicationId the applicationId to set
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    /**
     * @return the actionType
     */
    public String getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the serviceOrder
     */
    public String getServiceOrder() {
        return serviceOrder;
    }

    /**
     * @param serviceOrder the serviceOrder to set
     */
    public void setServiceOrder(String serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the changeTime
     */
    public Date getChangeTime() {
        return changeTime;
    }

    /**
     * @param changeTime the changeTime to set
     */
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    /**
     * @return the userFullname
     */
    public String getUserFullname() {
        return userFullname;
    }

    /**
     * @param userFullname the userFullname to set
     */
    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    /**
     * @return the actionDescription
     */
    public String getActionDescription() {
        return actionDescription;
    }

    /**
     * @param actionDescription the actionDescription to set
     */
    public void setActionDescription(String actionDescription) {
        this.actionDescription = actionDescription;
    }
}
