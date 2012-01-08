/**
 * ******************************************************************************************
 * Copyright (C) 2011 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.CaseManagementClient;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.AddressTO;
import org.sola.webservices.transferobjects.casemanagement.ApplicationTO;
import org.sola.webservices.transferobjects.casemanagement.PartySummaryTO;
import org.sola.webservices.transferobjects.casemanagement.PartyTO;
import org.sola.webservices.transferobjects.casemanagement.SourceTO;

/**
 * Provides a mock implementation for the 
 * {@linkplain org.sola.services.boundary.wsclients.CaseManagementClient} interface. Uses the 
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method. 
 * <p>Each method mocked by this class has a public constant defined that can be used to reference 
 * a mock response object from the {@linkplain MockServiceManager}. To set a response object
 * for a web method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from this class.</p>
 * @author amcdowell
 * @see MockResponse
 */
public class MockCaseManagementClient extends AbstractMockWSClient implements CaseManagementClient {

    private static final String SERVICE_NAME = "CaseManagement.";
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    public static final String CREATE_APPLICATION = SERVICE_NAME + "createApplication";
    public static final String SAVE_APPLICATION = SERVICE_NAME + "saveApplication";
    public static final String GET_ADDRESS = SERVICE_NAME + "getAddress";
    public static final String GET_APPLICATION = SERVICE_NAME + "getApplication";
    public static final String GET_PARTY = SERVICE_NAME + "getParty";
    public static final String GET_APPLICATION_SERVICES = SERVICE_NAME + "getApplicationServices";
    public static final String GET_AGENTS = SERVICE_NAME + "getAgents";
    public static final String CHANGE_APPLICATION_ASSIGNMENT = SERVICE_NAME + "changeApplicationAssignment";
    public static final String GET_APPLICATION_LOG = SERVICE_NAME + "getApplicationLog";
    public static final String CALCULATE_FEE = SERVICE_NAME + "calculateFee";
    public static final String ADD_APPLICATION_ACTION = SERVICE_NAME + "addApplicationAction";
    public static final String VALIDATE = "validate";
    public static final String CHANGE_APPLICATION_STATUS = "changeApplicationStatus";
    public static final String CHANGE_SERVICE_STATUS = "changeServiceStatus";

    public MockCaseManagementClient(String url) {
        super(url, null);
    }

    /** @return default = true */
    @Override
    public boolean checkConnection() {
        return getManager().getResponse(CHECK_CONNECTION, Boolean.class, true);
    }

    /** @return default = application */
    @Override
    public ApplicationTO createApplication(ApplicationTO application) {
        ApplicationTO defaultResponse = application;
        return getManager().getResponse(CREATE_APPLICATION, ApplicationTO.class, defaultResponse,
                application);
    }

    @Override
    public ApplicationTO saveApplication(ApplicationTO application) {
        ApplicationTO defaultResponse = application;
        return getManager().getResponse(SAVE_APPLICATION, ApplicationTO.class, defaultResponse,
                application);
    }

    /** @return default = new AddressTO with id set */
    @Override
    public AddressTO getAddress(String id) {
        AddressTO defaultResponse = new AddressTO();
        defaultResponse.setId(id);
        return getManager().getResponse(GET_ADDRESS, AddressTO.class, defaultResponse, id);
    }

    /** @return default = new ApplicationTO with id set */
    @Override
    public ApplicationTO getApplication(String id) {
        ApplicationTO defaultResponse = new ApplicationTO();
        defaultResponse.setId(id);
        return getManager().getResponse(GET_APPLICATION, ApplicationTO.class, defaultResponse, id);
    }

    /** @return default = new PartyTO with id set */
    @Override
    public PartyTO getParty(String id) {
        PartyTO defaultResponse = new PartyTO();
        defaultResponse.setId(id);
        return getManager().getResponse(GET_PARTY, PartyTO.class, defaultResponse, id);
    }

    /** @return default = new ArrayList<PartySummaryTO>() */
    @Override
    public List<PartySummaryTO> getAgents() {
        List<PartySummaryTO> defaultResponse = new ArrayList<PartySummaryTO>();
        return getManager().getResponse(GET_AGENTS, List.class, defaultResponse);
    }

    /** @return default = application */
    @Override
    public ApplicationTO calculateFee(ApplicationTO application) {
        ApplicationTO defaultResponse = application;
        return getManager().getResponse(CALCULATE_FEE, ApplicationTO.class, defaultResponse,
                application);
    }

    /** @return default = application */
    @Override
    public PartyTO createParty(PartyTO party) {
        PartyTO defaultResponse = party;
        return getManager().getResponse(CREATE_APPLICATION, PartyTO.class, defaultResponse,
                party);
//    TODO return getManager().getResponse(CREATE_PARTY, PartyTO.class, defaultResponse,
//                party);    
    }

    @Override
    public PartyTO saveParty(PartyTO party) {
        PartyTO defaultResponse = party;
        return getManager().getResponse(SAVE_APPLICATION, PartyTO.class, defaultResponse,
                party);
//          TODO   return getManager().getResponse(SAVE_PARTY, PartyTO.class, defaultResponse,
//                party);
    }

    @Override
    public SourceTO attachSourceToTransaction(String serviceId, String sourceId) {
        return new SourceTO();
    }

    @Override
    public boolean dettachSourceFromTransaction(String sourceId) {
        return true;
    }

    @Override
    public List<SourceTO> getSourcesByServiceId(String serviceId) {
        return new ArrayList<SourceTO>();
    }

    @Override
    public List<SourceTO> getSourcesByIds(List<String> sourceIds) throws WebServiceClientException {
        return new ArrayList<SourceTO>();
    }

    @Override
    public List<ValidationResult> serviceActionComplete(
            String serviceId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> serviceActionRevert(
            String serviceId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> serviceActionStart(
            String serviceId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> serviceActionCancel(
            String serviceId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionWithdraw(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionCancel(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionRequisition(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionValidate(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionApprove(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionArchive(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionDespatch(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionLapse(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionUnassign(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionAssign(
            String applicationId, String userId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }

    @Override
    public List<ValidationResult> applicationActionResubmit(
            String applicationId, int rowVersion) throws WebServiceClientException {
        return new ArrayList<ValidationResult>();
    }
}
