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
package org.sola.services.boundary.wsclients;

import java.util.List;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.*;

/**
 * Interface for the Case Management Service. Implemented by {@linkplain CaseManagementClientImpl}.
 * To obtain a reference to the Case Management Service, use {@linkplain WSManager#getCaseManagementService()}
 *
 * @see CaseManagementClientImpl
 * @see WSManager#getCaseManagementService()
 */
public interface CaseManagementClient extends AbstractWSClient {

    /**
     * CaseManagement. - Service name prefix for the Case Management Web Service
     */
    public static final String SERVICE_NAME = "CaseManagement.";
    /**
     * CaseManagement.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * CaseManagement.calculateFee - Identifier for the calculateFee method
     */
    public static final String CALCULATE_FEE = SERVICE_NAME + "calculateFee";
    /**
     * CaseManagement.createApplication - Identifier for the createApplication method
     */
    public static final String CREATE_APPLICATION = SERVICE_NAME + "createApplication";
    /**
     * CaseManagement.getAddress - Identifier for the getAddress method
     */
    public static final String GET_ADDRESS = SERVICE_NAME + "getAddress";
    /**
     * CaseManagement.getLodgementView - Identifier for the getLodgementView method
     */
    public static final String GET_LODGEMENT_VIEW = SERVICE_NAME + "getLodgementView";
    /**
     * CaseManagement.getLodgementTiming - Identifier for the getLodgementTiming method
     */
    public static final String GET_LODGEMENT_TIMING = SERVICE_NAME + "getLodgementTiming";
    /**
     * CaseManagement.getAllBrs - Identifier for the getAllBrs method
     */
    public static final String GET_ALL_BRS = SERVICE_NAME + "getAllBrs";
    /**
     * CaseManagement.getAgents - Identifier for the getAgents method
     */
    public static final String GET_AGENTS = SERVICE_NAME + "getAgents";
    /**
     * CaseManagement.getApplication - Identifier for the getApplication method
     */
    public static final String GET_APPLICATION = SERVICE_NAME + "getApplication";
    /**
     * CaseManagement.getParty - Identifier for the getParty method
     */
    public static final String GET_PARTY = SERVICE_NAME + "getParty";
    /**
     * CaseManagement.saveApplication - Identifier for the saveApplication method
     */
    public static final String SAVE_APPLICATION = SERVICE_NAME + "saveApplication";
    /**
     * CaseManagement.saveParty - Identifier for the saveParty method
     */
    public static final String SAVE_PARTY = SERVICE_NAME + "saveParty";
    /**
     * CaseManagement.attachSourceToTransaction - Identifier for the attachSourceToTransaction method
     */
    public static final String ATTACH_SOURCE_TO_TRANSACTION = SERVICE_NAME + "attachSourceToTransaction";
    /**
     * CaseManagement.dettachSourceFromTransaction - Identifier for the dettachSourceFromTransaction method
     */
    public static final String DETTACH_SOURCE_FROM_TRANSACTION = SERVICE_NAME + "dettachSourceFromTransaction";
    /**
     * CaseManagement.getSourcesByServiceId - Identifier for the getSourcesByServiceId method
     */
    public static final String GET_SOURCES_BY_SERVICE_ID = SERVICE_NAME + "getSourcesByServiceId";
    /**
     * CaseManagement.getSourcesByIds - Identifier for the getSourcesByIds method
     */
    public static final String GET_SOURCES_BY_IDS = SERVICE_NAME + "getSourcesByIds";
    /**
     * CaseManagement.getSourceById - Identifier for the getSourceById method
     */
    public static final String GET_SOURCE_BY_ID = SERVICE_NAME + "getSourceById";
    /**
     * CaseManagement.serviceActionComplete - Identifier for the serviceActionComplete method
     */
    public static final String SERVICE_ACTION_COMPLETE = SERVICE_NAME + "serviceActionComplete";
    /**
     * CaseManagement.serviceActionRevert - Identifier for the serviceActionRevert method
     */
    public static final String SERVICE_ACTION_REVERT = SERVICE_NAME + "serviceActionRevert";
    /**
     * CaseManagement.serviceActionStart - Identifier for the serviceActionStart method
     */
    public static final String SERVICE_ACTION_START = SERVICE_NAME + "serviceActionStart";
    /**
     * CaseManagement.serviceActionCancel - Identifier for the serviceActionCancel method
     */
    public static final String SERVICE_ACTION_CANCEL = SERVICE_NAME + "serviceActionCancel";
    /**
     * CaseManagement.applicationActionWithdraw - Identifier for the applicationActionWithdraw method
     */
    public static final String APPLICATION_ACTION_WITHDRAW = SERVICE_NAME + "applicationActionWithdraw";
    /**
     * CaseManagement.applicationActionCancel - Identifier for the applicationActionCancel method
     */
    public static final String APPLICATION_ACTION_CANCEL = SERVICE_NAME + "applicationActionCancel";
    /**
     * CaseManagement.applicationActionRequisition - Identifier for the applicationActionRequisition method
     */
    public static final String APPLICATION_ACTION_REQUISITION = SERVICE_NAME + "applicationActionRequisition";
    /**
     * CaseManagement.applicationActionValidate - Identifier for the applicationActionValidate method
     */
    public static final String APPLICATION_ACTION_VALIDATE = SERVICE_NAME + "applicationActionValidate";
    /**
     * CaseManagement.applicationActionApprove - Identifier for the applicationActionApprove method
     */
    public static final String APPLICATION_ACTION_APPROVE = SERVICE_NAME + "applicationActionApprove";
    /**
     * CaseManagement.applicationActionArchive - Identifier for the applicationActionArchive method
     */
    public static final String APPLICATION_ACTION_ARCHIVE = SERVICE_NAME + "applicationActionArchive";
    /**
     * CaseManagement.applicationActionDespatch - Identifier for the applicationActionDespatch method
     */
    public static final String APPLICATION_ACTION_DESPATCH = SERVICE_NAME + "applicationActionDespatch";
    /**
     * CaseManagement.applicationActionLapse - Identifier for the applicationActionLapse method
     */
    public static final String APPLICATION_ACTION_LAPSE = SERVICE_NAME + "applicationActionLapse";
    /**
     * CaseManagement.applicationActionUnassign - Identifier for the applicationActionUnassign method
     */
    public static final String APPLICATION_ACTION_UNASSIGN = SERVICE_NAME + "applicationActionUnassign";
    /**
     * CaseManagement.applicationActionAssign - Identifier for the applicationActionAssign method
     */
    public static final String APPLICATION_ACTION_ASSIGN = SERVICE_NAME + "applicationActionAssign";
    /**
     * CaseManagement.applicationActionResubmit - Identifier for the applicationActionResubmit method
     */
    public static final String APPLICATION_ACTION_RESUBMIT = SERVICE_NAME + "applicationActionResubmit";
    /**
     * CaseManagement.saveInformationService - Identifier for the saveInformationService method
     */
    public static final String SAVE_INFORMATION_SERVICE = SERVICE_NAME + "saveInformationService";
    /**
     * CaseManagement.saveSource - Identifier for the saveSource method
     */
    public static final String SAVE_SOURCE = SERVICE_NAME + "saveSource";

    ApplicationTO calculateFee(ApplicationTO application) throws WebServiceClientException;

    ApplicationTO createApplication(ApplicationTO application) throws WebServiceClientException;

    AddressTO getAddress(String id) throws WebServiceClientException;

    List<LodgementViewTO> getLodgementView(LodgementViewParamsTO lodgementViewParamsTO) throws WebServiceClientException;

    List<LodgementTimingTO> getLodgementTiming(LodgementViewParamsTO lodgementViewParamsTO) throws WebServiceClientException;

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

    SourceTO getSourceById(String sourceId) throws WebServiceClientException;

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

    SourceTO saveSource(SourceTO sourceTO);
}
