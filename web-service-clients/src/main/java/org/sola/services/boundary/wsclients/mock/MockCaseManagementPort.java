/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.sola.services.boundary.wsclients.CaseManagementClient;
import org.sola.webservices.casemanagement.*;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.*;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.casemanagement.CaseManagement} interface.
 * Uses the {@linkplain MockServiceManager} to obtain the appropriate mock
 * response for each web method.
 * <p>Each method mocked by this class has a public constant defined that can be
 * used to reference a mock response object from the
 * {@linkplain MockServiceManager}. To set a response object for a web method,
 * use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from
 * {@linkplain org.sola.services.boundary.wsclients.CaseManagementClient}.</p>
 *
 * @see MockCaseManagementClient
 * @see CaseManagementClient
 * @see MockServiceManager
 * @see MockResponse
 */
public class MockCaseManagementPort implements CaseManagement {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type.
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionBasic(Exception ex) throws SOLAFault, UnhandledFault {
        if (SOLAFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAFault) ex;
        } else if (UnhandledFault.class.isAssignableFrom(ex.getClass())) {
            throw (UnhandledFault) ex;
        } else if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
            throw (RuntimeException) ex;
        } else {
            // The type of the exception does not match any recognized exception type used by this 
            // web service. Throw a MockResponseException to fail the test. 
            throw new MockResponseException("Unable to convert the mocked response exception to "
                    + "recognized exception type: " + ex.getClass().getName());
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the SOLAAccessFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAccess(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, MockResponseException {
        if (SOLAAccessFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAAccessFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the OptimisticLockingFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionUpdate(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, OptimisticLockingFault, MockResponseException {
        if (OptimisticLockingFault.class.isAssignableFrom(ex.getClass())) {
            throw (OptimisticLockingFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionUpdate(java.lang.Exception) processExceptionUpdate}
     * to include the OptimisticLockingFault and SOLAValidationFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAll(Exception ex) throws OptimisticLockingFault, SOLAAccessFault,
            SOLAFault, SOLAValidationFault, UnhandledFault, MockResponseException {
        if (SOLAValidationFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAValidationFault) ex;
        } else {
            processExceptionUpdate(ex);
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_DESPATCH
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionDespatch(String applicationId,
            String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_DESPATCH,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.CREATE_APPLICATION
     *
     * @return default = application param
     */
    @Override
    public ApplicationTO createApplication(ApplicationTO application, String languageCode)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        ApplicationTO defaultResponse = application;
        try {
            return getManager().getResponse(CaseManagementClient.CREATE_APPLICATION,
                    ApplicationTO.class, defaultResponse, application, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_ARCHIVE
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionArchive(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_ARCHIVE,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_SOURCES_BY_SERVICE_ID
     *
     * @return default = new ArrayList<SourceTO>()
     */
    @Override
    public List<SourceTO> getSourcesByServiceId(String serviceId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SourceTO> defaultResponse = new ArrayList<SourceTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_SOURCES_BY_SERVICE_ID,
                    List.class, defaultResponse, serviceId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SAVE_APPLICATION
     *
     * @return default = application param
     */
    @Override
    public ApplicationTO saveApplication(ApplicationTO application, String languageCode)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        ApplicationTO defaultResponse = application;
        try {
            return getManager().getResponse(CaseManagementClient.SAVE_APPLICATION,
                    ApplicationTO.class, defaultResponse, application, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SERVICE_ACTION_REVERT
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> serviceActionRevert(String serviceId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.SERVICE_ACTION_REVERT,
                    List.class, defaultResponse, serviceId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.ATTACH_SOURCE_TO_TRANSACTION
     *
     * @return default = new SourceTO()
     */
    @Override
    public SourceTO attachSourceToTransaction(String serviceId, String sourceId, String languageCode)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        SourceTO defaultResponse = new SourceTO();
        defaultResponse.setId(sourceId);
        try {
            return getManager().getResponse(CaseManagementClient.ATTACH_SOURCE_TO_TRANSACTION,
                    SourceTO.class, defaultResponse, serviceId, sourceId, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_WITHDRAW
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionWithdraw(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_WITHDRAW,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_APPROVE
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionApprove(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_APPROVE,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_VALIDATE
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionValidate(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_VALIDATE,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SERVICE_ACTION_START
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> serviceActionStart(String serviceId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.SERVICE_ACTION_START,
                    List.class, defaultResponse, serviceId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SERVICE_ACTION_CANCEL
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> serviceActionCancel(String serviceId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.SERVICE_ACTION_CANCEL,
                    List.class, defaultResponse, serviceId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.DETTACH_SOURCE_FROM_TRANSACTION
     *
     * @return default = true
     */
    @Override
    public boolean dettachSourceFromTransaction(String sourceId)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        boolean defaultResponse = true;
        try {
            return getManager().getResponse(CaseManagementClient.DETTACH_SOURCE_FROM_TRANSACTION,
                    Boolean.class, defaultResponse, sourceId);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return false;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_SOURCES_BY_IDS
     *
     * @return default = new ArrayList<SourceTO>()
     */
    @Override
    public List<SourceTO> getSourcesByIds(List<String> sourceIds)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SourceTO> defaultResponse = new ArrayList<SourceTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_SOURCES_BY_IDS,
                    List.class, defaultResponse, sourceIds);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_APPLICATION
     *
     * @return default = new ApplicationTO()
     */
    @Override
    public ApplicationTO getApplication(String id) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        ApplicationTO defaultResponse = new ApplicationTO();
        defaultResponse.setId(id);
        try {
            return getManager().getResponse(CaseManagementClient.GET_APPLICATION,
                    ApplicationTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_USER_ACTIONS
     *
     * @return default = new ArrayList<ApplicationLogTO>()
     */
    @Override
    public List<ApplicationLogTO> getUserActions(String userName, XMLGregorianCalendar from, XMLGregorianCalendar to)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ApplicationLogTO> defaultResponse = new ArrayList<ApplicationLogTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_USER_ACTIONS,
                    List.class, defaultResponse, userName, from, to);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_CANCEL
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionCancel(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_CANCEL,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_SOURCE_BY_ID
     *
     * @return default = new SourceTO()
     */
    @Override
    public SourceTO getSourceById(String id) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        SourceTO defaultResponse = new SourceTO();
        defaultResponse.setId(id);
        try {
            return getManager().getResponse(CaseManagementClient.GET_SOURCE_BY_ID,
                    SourceTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SERVICE_ACTION_COMPLETE
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> serviceActionComplete(String serviceId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.SERVICE_ACTION_COMPLETE,
                    List.class, defaultResponse, serviceId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_REQUISITION
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionRequisition(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_REQUISITION,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_LAPSE
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionLapse(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_LAPSE,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_UNASSIGN
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionUnassign(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_UNASSIGN,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_ASSIGN
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionAssign(String applicationId, String userId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_ASSIGN,
                    List.class, defaultResponse, applicationId, userId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_RESUBMIT
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> applicationActionResubmit(String applicationId, String languageCode, int rowVersion)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CaseManagementClient.APPLICATION_ACTION_RESUBMIT,
                    List.class, defaultResponse, applicationId, languageCode, rowVersion);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SAVE_INFORMATION_SERVICE
     *
     * @return default = service param
     */
    @Override
    public ServiceTO saveInformationService(ServiceTO service, String languageCode)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        ServiceTO defaultResponse = service;
        try {
            return getManager().getResponse(CaseManagementClient.SAVE_INFORMATION_SERVICE,
                    ServiceTO.class, defaultResponse, service, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_ALL_BRS
     *
     * @return default = new ArrayList<BrReportTO>()
     */
    @Override
    public List<BrReportTO> getAllBrs() throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<BrReportTO> defaultResponse = new ArrayList<BrReportTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_ALL_BRS,
                    List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.CHECK_CONNECTION
     *
     * @return default = true
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(CaseManagementClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = CaseManagementClient.SAVE_PARTY
     *
     * @return default = party param
     */
    @Override
    public PartyTO saveParty(PartyTO party)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        PartyTO defaultResponse = party;
        try {
            return getManager().getResponse(CaseManagementClient.SAVE_PARTY,
                    PartyTO.class, defaultResponse, party);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.CALCULATE_FEE
     *
     * @return default = application param
     */
    @Override
    public ApplicationTO calculateFee(ApplicationTO application)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        ApplicationTO defaultResponse = application;
        try {
            return getManager().getResponse(CaseManagementClient.CALCULATE_FEE,
                    ApplicationTO.class, defaultResponse, application);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_ADDRESS
     *
     * @return default = new AddressTO()
     */
    @Override
    public AddressTO getAddress(String id) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        AddressTO defaultResponse = new AddressTO();
        defaultResponse.setId(id);
        try {
            return getManager().getResponse(CaseManagementClient.GET_ADDRESS,
                    AddressTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_AGENTS
     *
     * @return default = new ArrayList<PartySummaryTO>()
     */
    @Override
    public List<PartySummaryTO> getAgents() throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<PartySummaryTO> defaultResponse = new ArrayList<PartySummaryTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_AGENTS,
                    List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_PARTY
     *
     * @return default = new PartyTO()
     */
    @Override
    public PartyTO getParty(String id) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        PartyTO defaultResponse = new PartyTO();
        defaultResponse.setId(id);
        try {
            return getManager().getResponse(CaseManagementClient.GET_PARTY,
                    PartyTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.SAVE_SOURCE
     *
     * @return default = sourceTO param
     */
    @Override
    public SourceTO saveSource(SourceTO sourceTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        SourceTO defaultResponse = sourceTO;
        try {
            return getManager().getResponse(CaseManagementClient.SAVE_SOURCE,
                    SourceTO.class, defaultResponse, sourceTO);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    @Override
    public PowerOfAttorneyTO attachPowerOfAttorneyToTransaction(String serviceId, PowerOfAttorneyTO powerOfAttorney, String languageCode) throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        PowerOfAttorneyTO defaultResponse = new PowerOfAttorneyTO();
        defaultResponse.setId(powerOfAttorney.getId());
        try {
            return getManager().getResponse(CaseManagementClient.ATTACH_POWER_OF_ATTORNEY_TO_TRANSACTION,
                    PowerOfAttorneyTO.class, defaultResponse, serviceId, powerOfAttorney, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    @Override
    public List<PowerOfAttorneyTO> getPowerOfAttorneyByServiceId(String serviceId) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<PowerOfAttorneyTO> defaultResponse = new ArrayList<PowerOfAttorneyTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_POWER_OF_ATTORNEY_BY_SERVICE_ID,
                    List.class, defaultResponse, serviceId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.GET_POWER_OF_ATTORNEY_BY_ID
     *
     * @return default = new SourceTO()
     */
    @Override
    public PowerOfAttorneyTO getPowerOfAttorneyById(String id) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        PowerOfAttorneyTO defaultResponse = new PowerOfAttorneyTO();
        defaultResponse.setId(id);
        try {
            return getManager().getResponse(CaseManagementClient.GET_POWER_OF_ATTORNEY_BY_ID,
                    PowerOfAttorneyTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public ApplicationTO getApplicationByTransactionId(String transactionId) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        ApplicationTO defaultResponse = new ApplicationTO();
        try {
            return getManager().getResponse(CaseManagementClient.GET_APPLICATION_BY_TRANSACTION_ID,
                    ApplicationTO.class, defaultResponse, transactionId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = ApplicationClient.GET_CADASTRE_OBJECT_BY_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<SysRegCertificatesTO> getSysRegCertificatesByLocation(String searchString)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegCertificatesTO> defaultResponse = new ArrayList<SysRegCertificatesTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_SYS_REG_CERTIFICATES,
                    List.class, defaultResponse, searchString);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = ApplicationClient.GET_CADASTRE_OBJECT_BY_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<SysRegCertificatesTO> getSysRegCertificatesByApplication(String searchString, String nr)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegCertificatesTO> defaultResponse = new ArrayList<SysRegCertificatesTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_SYS_REG_CERTIFICATES_APP,
                    List.class, defaultResponse, searchString, nr);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

     /**
     * Response Key = CaseManagementClient.GET_WORK_SUMMARY
     *
     * @return default = new ArrayList<WorkSummaryTO>()
     */
    @Override
    public List<WorkSummaryTO> getWorkSummary(LodgementViewParamsTO paramsTO) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<WorkSummaryTO> defaultResponse = new ArrayList<WorkSummaryTO>();
        try {
            return getManager().getResponse(CaseManagementClient.GET_WORK_SUMMARY,
                    List.class, defaultResponse, paramsTO);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }
}
