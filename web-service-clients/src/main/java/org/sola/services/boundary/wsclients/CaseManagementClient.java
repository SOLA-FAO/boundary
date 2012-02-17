/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.wsclients;

import java.util.Date;
import java.util.List;

import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.AddressTO;
import org.sola.webservices.transferobjects.casemanagement.ApplicationTO;
import org.sola.webservices.transferobjects.casemanagement.BrReportTO;
import org.sola.webservices.transferobjects.casemanagement.LodgementTimingTO;
import org.sola.webservices.transferobjects.casemanagement.PartySummaryTO;
import org.sola.webservices.transferobjects.casemanagement.PartyTO;
import org.sola.webservices.transferobjects.casemanagement.ServiceTO;
import org.sola.webservices.transferobjects.casemanagement.SourceTO;
import org.sola.webservices.transferobjects.casemanagement.LodgementViewTO;
import org.sola.webservices.transferobjects.casemanagement.LodgementViewParamsTO;

/**
 * Interface for the CaseManagement Service. Implemented by {@linkplain CaseManagementClientImpl}
 * and {@linkplain mock.MockCaseManagementClient}
 * @author amcdowell
 */
public interface CaseManagementClient extends AbstractWSClient {

    ApplicationTO calculateFee(ApplicationTO application) throws WebServiceClientException;

    /** Some Stuff */
    boolean checkConnection() throws WebServiceClientException;

    ApplicationTO createApplication(ApplicationTO application) throws WebServiceClientException;

    AddressTO getAddress(String id) throws WebServiceClientException;
    
    List<LodgementViewTO> getLodgementView(LodgementViewParamsTO  lodgementViewParamsTO) throws WebServiceClientException;
    
    List<LodgementTimingTO> getLodgementTiming(LodgementViewParamsTO  lodgementViewParamsTO) throws WebServiceClientException;
    
    List<BrReportTO> getAllBrs() throws WebServiceClientException;

    List<PartySummaryTO> getAgents() throws WebServiceClientException;

    ApplicationTO getApplication(String id) throws WebServiceClientException;
    
    PartyTO getParty(String id) throws WebServiceClientException;

    ApplicationTO saveApplication(ApplicationTO application) throws WebServiceClientException;
    
    PartyTO saveParty(PartyTO party) throws WebServiceClientException;

    SourceTO attachSourceToTransaction(String serviceId, String sourceId) 
            throws WebServiceClientException;
    boolean dettachSourceFromTransaction(String sourceId) throws WebServiceClientException;
    List<SourceTO> getSourcesByServiceId(String serviceId) throws WebServiceClientException;
    
    List<SourceTO> getSourcesByIds(List<String> sourceIds) throws WebServiceClientException;

    List<ValidationResult> serviceActionComplete(
            String serviceId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> serviceActionRevert(
            String serviceId, int rowVersion) throws WebServiceClientException;
    
    List<ValidationResult> serviceActionStart(
            String serviceId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> serviceActionCancel(
            String serviceId, int rowVersion) throws WebServiceClientException;
    
    List<ValidationResult> applicationActionWithdraw(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionCancel(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionRequisition(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionValidate(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionApprove(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionArchive(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionDespatch(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionLapse(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionUnassign(
            String applicationId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionAssign(
            String applicationId, String userId, int rowVersion) throws WebServiceClientException;

    List<ValidationResult> applicationActionResubmit(
            String applicationId, int rowVersion) throws WebServiceClientException;
    
    ServiceTO saveInformationService(ServiceTO service) throws WebServiceClientException;
}
