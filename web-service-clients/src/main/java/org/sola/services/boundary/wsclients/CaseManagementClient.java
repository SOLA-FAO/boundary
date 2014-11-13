/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations
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
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.sola.common.RolesConstants;
import org.sola.common.SOLAAccessException;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.*;

/**
 * Interface for the Case Management Service. Implemented by
 * {@linkplain CaseManagementClientImpl}. To obtain a reference to the Case
 * Management Service, use {@linkplain WSManager#getCaseManagementService()}
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
     * CaseManagement.checkConnection - Identifier for the checkConnection
     * method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * CaseManagement.calculateFee - Identifier for the calculateFee method
     */
    public static final String CALCULATE_FEE = SERVICE_NAME + "calculateFee";
    /**
     * CaseManagement.createApplication - Identifier for the createApplication
     * method
     */
    public static final String CREATE_APPLICATION = SERVICE_NAME + "createApplication";
    /**
     * CaseManagement.getAddress - Identifier for the getAddress method
     */
    public static final String GET_ADDRESS = SERVICE_NAME + "getAddress";
    /**
     * CaseManagement.getAllBrs - Identifier for the getAllBrs method
     */
    public static final String GET_ALL_BRS = SERVICE_NAME + "getAllBrs";
    /**
     * CaseManagement.getAgents - Identifier for the getAgents method
     */
    public static final String GET_AGENTS = SERVICE_NAME + "getAgents";
    /**
     * CaseManagement.getPartiesByRole - Identifier for the getAgents method
     */
    public static final String GET_PARTIES_BY_ROLE = SERVICE_NAME + "getPartiesByRole";
    /**
     * CaseManagement.getApplication - Identifier for the getApplication method
     */
    public static final String GET_APPLICATION = SERVICE_NAME + "getApplication";
    /**
     * CaseManagement.getParty - Identifier for the getParty method
     */
    public static final String GET_PARTY = SERVICE_NAME + "getParty";
    /**
     * CaseManagement.saveApplication - Identifier for the saveApplication
     * method
     */
    public static final String SAVE_APPLICATION = SERVICE_NAME + "saveApplication";
    /**
     * CaseManagement.saveParty - Identifier for the saveParty method
     */
    public static final String SAVE_PARTY = SERVICE_NAME + "saveParty";
    /**
     * CaseManagement.attachPowerOfAttorneyToTransaction - Identifier for the
     * attachPowerOfAttorneyToTransaction method
     */
    public static final String ATTACH_POWER_OF_ATTORNEY_TO_TRANSACTION = SERVICE_NAME + "attachPowerOfAttorneyToTransaction";
    /**
     * CaseManagement.attachSourceToTransaction - Identifier for the
     * attachSourceToTransaction method
     */
    public static final String ATTACH_SOURCE_TO_TRANSACTION = SERVICE_NAME + "attachSourceToTransaction";
    /**
     * CaseManagement.dettachSourceFromTransaction - Identifier for the
     * dettachSourceFromTransaction method
     */
    public static final String DETTACH_SOURCE_FROM_TRANSACTION = SERVICE_NAME + "dettachSourceFromTransaction";
    /**
     * CaseManagement.getSourcesByServiceId - Identifier for the
     * getSourcesByServiceId method
     */
    public static final String GET_SOURCES_BY_SERVICE_ID = SERVICE_NAME + "getSourcesByServiceId";
    /**
     * CaseManagement.getPowerOfAttorneyByServiceId - Identifier for the
     * getPowerOfAttorneyByServiceId method
     */
    public static final String GET_POWER_OF_ATTORNEY_BY_SERVICE_ID = SERVICE_NAME + "getSourcesByServiceId";
    /**
     * CaseManagement.getSourcesByIds - Identifier for the getSourcesByIds
     * method
     */
    public static final String GET_SOURCES_BY_IDS = SERVICE_NAME + "getSourcesByIds";
    /**
     * CaseManagement.getSourceById - Identifier for the getSourceById method
     */
    public static final String GET_SOURCE_BY_ID = SERVICE_NAME + "getSourceById";
    public static final String GET_POWER_OF_ATTORNEY_BY_ID = SERVICE_NAME + "getPowerOfAttorneyById";
    public static final String GET_APPLICATION_BY_TRANSACTION_ID = SERVICE_NAME + "getApplicationByTransactionId";
    /**
     * CaseManagement.serviceActionComplete - Identifier for the
     * serviceActionComplete method
     */
    public static final String SERVICE_ACTION_COMPLETE = SERVICE_NAME + "serviceActionComplete";
    /**
     * CaseManagement.serviceActionRevert - Identifier for the
     * serviceActionRevert method
     */
    public static final String SERVICE_ACTION_REVERT = SERVICE_NAME + "serviceActionRevert";
    /**
     * CaseManagement.serviceActionStart - Identifier for the serviceActionStart
     * method
     */
    public static final String SERVICE_ACTION_START = SERVICE_NAME + "serviceActionStart";
    /**
     * CaseManagement.serviceActionCancel - Identifier for the
     * serviceActionCancel method
     */
    public static final String SERVICE_ACTION_CANCEL = SERVICE_NAME + "serviceActionCancel";
    /**
     * CaseManagement.applicationActionWithdraw - Identifier for the
     * applicationActionWithdraw method
     */
    public static final String APPLICATION_ACTION_WITHDRAW = SERVICE_NAME + "applicationActionWithdraw";
    /**
     * CaseManagement.applicationActionCancel - Identifier for the
     * applicationActionCancel method
     */
    public static final String APPLICATION_ACTION_CANCEL = SERVICE_NAME + "applicationActionCancel";
    /**
     * CaseManagement.applicationActionRequisition - Identifier for the
     * applicationActionRequisition method
     */
    public static final String APPLICATION_ACTION_REQUISITION = SERVICE_NAME + "applicationActionRequisition";
    /**
     * CaseManagement.applicationActionValidate - Identifier for the
     * applicationActionValidate method
     */
    public static final String APPLICATION_ACTION_VALIDATE = SERVICE_NAME + "applicationActionValidate";
    /**
     * CaseManagement.applicationActionApprove - Identifier for the
     * applicationActionApprove method
     */
    public static final String APPLICATION_ACTION_APPROVE = SERVICE_NAME + "applicationActionApprove";
    /**
     * CaseManagement.applicationActionArchive - Identifier for the
     * applicationActionArchive method
     */
    public static final String APPLICATION_ACTION_ARCHIVE = SERVICE_NAME + "applicationActionArchive";
    /**
     * CaseManagement.applicationActionDespatch - Identifier for the
     * applicationActionDespatch method
     */
    public static final String APPLICATION_ACTION_DESPATCH = SERVICE_NAME + "applicationActionDespatch";
    /**
     * CaseManagement.applicationActionLapse - Identifier for the
     * applicationActionLapse method
     */
    public static final String APPLICATION_ACTION_LAPSE = SERVICE_NAME + "applicationActionLapse";
    /**
     * CaseManagement.applicationActionUnassign - Identifier for the
     * applicationActionUnassign method
     */
    public static final String APPLICATION_ACTION_UNASSIGN = SERVICE_NAME + "applicationActionUnassign";
    /**
     * CaseManagement.applicationActionAssign - Identifier for the
     * applicationActionAssign method
     */
    public static final String APPLICATION_ACTION_ASSIGN = SERVICE_NAME + "applicationActionAssign";
    /**
     * CaseManagement.applicationActionResubmit - Identifier for the
     * applicationActionResubmit method
     */
    public static final String APPLICATION_ACTION_RESUBMIT = SERVICE_NAME + "applicationActionResubmit";
    /**
     * CaseManagement.saveInformationService - Identifier for the
     * saveInformationService method
     */
    public static final String SAVE_INFORMATION_SERVICE = SERVICE_NAME + "saveInformationService";
    /**
     * CaseManagement.saveSource - Identifier for the saveSource method
     */
    public static final String SAVE_SOURCE = SERVICE_NAME + "saveSource";
    /**
     * CaseManagement.getUserActions - Identifier for the getUserActions method
     */
    public static final String GET_USER_ACTIONS = SERVICE_NAME + "getUserActions";
    /**
     * Application.getSysRegCertificates - Identifier for the
     * getSysRegCertificates method
     */
    public static final String GET_SYS_REG_CERTIFICATES = SERVICE_NAME + "getSysRegCerificatesByLocation";
    /**
     * Application.getSysRegCertificates - Identifier for the
     * getSysRegCertificatesApp method
     */
    public static final String GET_SYS_REG_CERTIFICATES_APP = SERVICE_NAME + "getSysRegCerificatesByApplication";
    /**
     * CaseManagement.getWorkSummary - Identifier for the getWorkSummary method
     */
    public static final String GET_WORK_SUMMARY = SERVICE_NAME + "getWorkSummary";

    /**
     * CaseManagement.saveServiceChecklistItem - Identifier for the
     * saveServiceChecklistItem method
     */
    public static final String SAVE_SERVICE_CHECKLIST_ITEM = SERVICE_NAME + "saveServiceChecklistItem";

    /**
     * CaseManagement.getServiceChecklistItem - Identifier for the
     * getServiceChecklistItem method
     */
    public static final String GET_SERVICE_CHECKLIST_ITEM = SERVICE_NAME + "getServiceChecklistItem";
    /**
     * CaseManagement.saveService - Identifier for the saveService method
     */
    public static final String SAVE_SERVICE = SERVICE_NAME + "saveService";
    /**
     * CaseManagement.getPublicDisplayItems - Identifier for the
     * getPublicDisplayItems method
     */
    public static final String GET_PUBLIC_DISPLAY_ITEMS = SERVICE_NAME + "getPublicDisplayItems";
    /**
     * CaseManagement.savePublicDisplayItem - Identifier for the
     * savePublicDisplayItem method
     */
    public static final String SAVE_PUBLIC_DISPLAY_ITEM = SERVICE_NAME + "savePublicDisplayItem";
    /**
     * CaseManagement.savePublicDisplayItems - Identifier for the
     * savePublicDisplayItems method
     */
    public static final String SAVE_PUBLIC_DISPLAY_ITEMS = SERVICE_NAME + "savePublicDisplayItems";
    /**
     * CaseManagement.getObjections - Identifier for the getObjections method
     */
    public static final String GET_OBJECTIONS = SERVICE_NAME + "getObjections";
    /**
     * CaseManagement.saveObjections - Identifier for the saveObjections method
     */
    public static final String SAVE_OBJECTIONS = SERVICE_NAME + "saveObjections";
    /**
     * CaseManagement.getNotifyParties - Identifier for the getNotifyParties
     * method
     */
    public static final String GET_NOTIFY_PARTIES = SERVICE_NAME + "getNotifyParties";
    /**
     * CaseManagement.saveNotifyParties - Identifier for the saveNotifyParties
     * method
     */
    public static final String SAVE_NOTIFY_PARTIES = SERVICE_NAME + "saveNotifyParties";

    /**
     * Calculates the lodgement fees as well as the expected completions dates
     * for each service as well as the application.
     *
     * @param application The application to calculate fees and set completion
     * dates.
     * @return The application with the fees and completion dates set
     *
     * @throws WebServiceClientException
     */
    ApplicationTO calculateFee(ApplicationTO application) throws WebServiceClientException;

    /**
     * Creates a new application record and any new child objects. Sets the
     * initial action for the application (e.g. lodged) using a business rule.
     * Also sets the lodged date and expected completion date.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_CREATE_APPS}
     * role.</p>
     *
     * @param application The application to insert
     * @return The application after the insert.
     * @throws WebServiceClientException
     */
    ApplicationTO createApplication(ApplicationTO application) throws WebServiceClientException;

    /**
     * Returns the details for the specified address user.
     *
     * <p>
     * No role is required to execute this method.</p>
     *
     * @param id The identifier of the address to retrieve.
     * @throws WebServiceClientException
     */
    AddressTO getAddress(String id) throws WebServiceClientException;

    /**
     * Returns a br report for every business rule in the system.br table.
     *
     * <p>
     * No role is required to execute this method.</p>
     *
     * @throws WebServiceClientException
     */
    List<BrReportTO> getAllBrs() throws WebServiceClientException;

    /**
     * Returns all parties that have the lodgingAgent party role. Note that the
     * address and party role details for each agent are not loaded.
     * <p>
     * No role is required to execute this method.</p>
     *
     * @throws WebServiceClientException
     */
    List<PartySummaryTO> getAgents() throws WebServiceClientException;

    /**
     * Returns an application based on the id value.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_VIEW_APPS} role.</p>
     *
     * @param id The id of the application to retrieve
     * @return The found application or null.
     * @throws WebServiceClientException
     */
    ApplicationTO getApplication(String id) throws WebServiceClientException;

    /**
     * Returns the details for the specified party.
     * <p>
     * No role is required to execute this method.</p>
     *
     * @param id The identifier of the party to retrieve.
     * @throws WebServiceClientException
     */
    PartyTO getParty(String id) throws WebServiceClientException;

    /**
     * Saves changes to the application and child objects. Will also update the
     * completion dates and fees for the application if a new service as been
     * added.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_CREATE_APPS}
     * role.</p>
     *
     * @param application
     * @return The application after the save is completed.
     * @throws WebServiceClientException
     */
    ApplicationTO saveApplication(ApplicationTO application) throws WebServiceClientException;

    /**
     * Can be used to create a new party or save any updates to the details of
     * an existing party.
     * <p>
     * Requires the {@linkplain RolesConstants#PARTY_RIGHTHOLDERS_SAVE} or
     * {@linkplain RolesConstants#PARTY_SAVE} role.</p>
     *
     * @param party The party to create/save
     * @return The party after the save is completed.
     * @throws SOLAAccessException Where the party being saved is a right holder
     * but the user does not have the
     * {@linkplain RolesConstants#PARTY_RIGHTHOLDERS_SAVE} role
     * @throws WebServiceClientException
     */
    PartyTO saveParty(PartyTO party) throws WebServiceClientException;

    /**
     * Associates a source with a transaction and sets the source status to
     * <code>pending</code>. Also validates the source to ensure it does not
     * have any other pending transaction associations. Note that the original
     * source record is duplicated. Will also create a new transaction record if
     * one does not already exist for the service.
     *
     * <p>
     * Requires the {@linkplain RolesConstants#SOURCE_TRANSACTIONAL} role.</p>
     *
     * @param serviceId Identifier of the service the source relates to. Used to
     * determine the transaction to associate the source with.
     * @param sourceId Identifier of the source to validate
     * @throws WebServiceClientException If the source does not exist or the
     * source already has a pending association with another transaction.
     */
    SourceTO attachSourceToTransaction(String serviceId, String sourceId)
            throws WebServiceClientException;

    /**
     * Associates a Power of attorney with a transaction and sets the related
     * source status to <code>pending</code>. Also validates the source to
     * ensure it does not have any other pending transaction associations. Note
     * that the original source record is duplicated. Will also create a new
     * transaction record if one does not already exist for the service.
     *
     * <p>
     * Requires the {@linkplain RolesConstants#SOURCE_TRANSACTIONAL} role.</p>
     *
     * @param serviceId Identifier of the service the source relates to. Used to
     * determine the transaction to associate the source with.
     * @param powerOfAttorney Power of attorney object containing related
     * source.
     * @throws WebServiceClientException If the source does not exist or the
     * source already has a pending association with another transaction.
     */
    PowerOfAttorneyTO attachPowerOfAttorneyToTransaction(String serviceId, PowerOfAttorneyTO powerOfAttorney)
            throws WebServiceClientException;

    /**
     * Deletes the specified source if the status of the source is
     * <code>pending</code>.
     *
     * <p>
     * Requires the {@linkplain RolesConstants#SOURCE_TRANSACTIONAL} role.</p>
     *
     * @param sourceId Identifier of the source to detach from the transaction.
     * @return true if the source is successfully deleted.
     * @throws WebServiceClientException If the status of the source is not
     * pending
     */
    boolean dettachSourceFromTransaction(String sourceId) throws WebServiceClientException;

    /**
     * Retrieves all sources associated with the service. Uses the transaction
     * associated with the service to determine the sources to return.
     *
     * @param serviceId Identifier of the service
     * @throws WebServiceClientException
     */
    List<SourceTO> getSourcesByServiceId(String serviceId) throws WebServiceClientException;

    /**
     * Returns a list of sources matching the supplied ids.
     * <p>
     * No role is required to execute this method.</p>
     *
     * @param sourceIds The list of source ids
     * @throws WebServiceClientException
     */
    List<SourceTO> getSourcesByIds(List<String> sourceIds) throws WebServiceClientException;

    /**
     * Returns the details for the specified source.
     *
     * <p>
     * No role is required to execute this method.</p>
     *
     * @param id The identifier of the source to retrieve.
     * @throws WebServiceClientException
     */
    SourceTO getSourceById(String sourceId) throws WebServiceClientException;

    /**
     * Updates the status of the service to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.service_action_type</code> table for the
     * <code>complete</code> code. (i.e. completed)
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_SERVICE_COMPLETE}
     * role.</p>
     *
     * @param serviceId The service to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> serviceActionComplete(
            String serviceId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the service to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.service_action_type</code> table for the
     * <code>revert</code> code. (i.e. pending)
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_SERVICE_REVERT}
     * role.</p>
     *
     * @param serviceId The service to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> serviceActionRevert(
            String serviceId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the service to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.service_action_type</code> table for the
     * <code>start</code> code. (i.e. pending)
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_SERVICE_START}
     * role.</p>
     *
     * @param serviceId The service to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> serviceActionStart(
            String serviceId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the service to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.service_action_type</code> table for the
     * <code>cancel</code> code. (i.e. cancelled)
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_SERVICE_CANCEL}
     * role.</p>
     *
     * @param serviceId The service to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> serviceActionCancel(
            String serviceId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>withdraw</code> code. (i.e. anulled). Will also delete the
     * transaction records for each service that is associated with the
     * application.<p>
     * Requires the {@linkplain RolesConstants#APPLICATION_WITHDRAW} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionWithdraw(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>cancel</code> code. (i.e. anulled). Will also delete the
     * transaction records for each service that is associated with the
     * application.<p>
     * Requires the {@linkplain RolesConstants#APPLICATION_REJECT} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionCancel(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>requisition</code> code. (i.e. requisition).
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_REQUISITE} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionRequisition(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Triggers the validations for the application and updates the Application
     * Action code to indicate if the validation succeed or fail.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_VALIDATE} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionValidate(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>approve</code> code. (i.e. approved) if validations are successful.
     * Also updates the status of all services and BA Units and /or Cadastre
     * Objects linked to those services.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_APPROVE} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionApprove(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>archive</code> code. (i.e. completed).
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_ARCHIVE} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionArchive(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Sets the action code on the application to <cpde>dispatch</code> to
     * indicate the application has been dispatched.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_DISPATCH} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionDespatch(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>lapse</code> code. (i.e. anulled). Will also delete the transaction
     * records for each service that is associated with the application.<p>
     * Requires the {@linkplain RolesConstants#APPLICATION_LAPSE} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionLapse(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Clears the assignee id on the application and sets the action code on the
     * application to <cpde>unAssign</code> to indicate the application has been
     * unassigned.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_UNASSIGN_FROM_OTHERS}
     * or the {@linkplain RolesConstants#APPLICATION_UNASSIGN_FROM_YOURSELF}
     * role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionUnassign(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * Sets the assignee id on the application to the id of the user specified
     * as well as setting the action code on the application to
     * <cpde>assign</code> to indicate the application has been assigned.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_ASSIGN_TO_OTHERS} or
     * the {@linkplain RolesConstants#APPLICATION_ASSIGN_TO_YOURSELF} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param userId Identifier of the user to assign to the application
     * @param teamId Identifier of the team to assign the application
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionAssign(
            String applicationId, String userId, String teamId, int rowVersion) throws WebServiceClientException;

    /**
     * Updates the status of the application to the value indicated by the
     * <code>status_to_set</code> in the
     * <code>application.application_action_type</code> table for the
     * <code>resubmit</code> code. (i.e. lodged).
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_RESUBMIT} role.</p>
     *
     * @param applicationId The application to perform the action against
     * @param languageCode The language code to use for localization of
     * validation messages.
     * @param rowVersion The current row version of the service
     * @return The results of the validation performed as part of the service
     * action.
     * @throws WebServiceClientException
     */
    List<ValidationResult> applicationActionResubmit(
            String applicationId, int rowVersion) throws WebServiceClientException;

    /**
     * It registers a service of category type informationServices. If it is of
     * another kind of not specified it throws an exception. If the service
     * exists, it is only logged an action of type completed, otherwise it is
     * created.
     *
     * @param service The service to be saved
     * @param languageCode current language code. Used if business rules are
     * invoked.
     * @return The service after the save is completed
     * @throws WebServiceClientException
     */
    ServiceTO saveInformationService(ServiceTO service) throws WebServiceClientException;

    /**
     * Can be used to create a new source or save any updates to the details of
     * an existing source.
     * <p>
     * Requires the {@linkplain RolesConstants#SOURCE_SAVE} role.</p>
     *
     * @param source The source to create/save
     * @return The source after the save is completed.
     * @throws WebServiceClientException
     */
    SourceTO saveSource(SourceTO sourceTO);

    /**
     * Retrieves the actions a specific user has performed against any
     * application during a specific period.
     *
     * @param username The username of the user to query the application log
     * with
     * @param fromTime The start of the reporting period
     * @param toTime The end of the reporting period
     * @return The list of actions the user has performed against any
     * application during the reporting period.
     * @throws WebServiceClientException
     */
    List<ApplicationLogTO> getUserActions(String userName, XMLGregorianCalendar from, XMLGregorianCalendar to)
            throws WebServiceClientException;

    /**
     * Retrieves all power of attorney documents associated with the service.
     * Uses the transaction associated with the service to determine the
     * documents to return.
     *
     * @param serviceId Identifier of the service
     * @throws WebServiceClientException
     */
    List<PowerOfAttorneyTO> getPowerOfAttorneyByServiceId(String serviceId) throws WebServiceClientException;

    /**
     * Returns the details for the specified Power of attorney.
     *
     * <p>
     * No role is required to execute this method.</p>
     *
     * @param id The identifier of the Power of attorney to retrieve.
     * @throws WebServiceClientException
     */
    PowerOfAttorneyTO getPowerOfAttorneyById(String id) throws WebServiceClientException;

    /**
     * Returns {@link ApplicationTO} by the given transaction ID.
     */
    ApplicationTO getApplicationByTransactionId(String transactionId) throws WebServiceClientException;

    /**
     * Returns a list of cadastre objects that have a name last part that
     * matches the specified search string. This method supports partial matches
     * and is case insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<SysRegCertificatesTO> getSysRegCertificatesByLocation(String searchString)
            throws WebServiceClientException;

    /**
     * Returns a list of cadastre objects that have a name last part that
     * matches the specified search string. This method supports partial matches
     * and is case insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<SysRegCertificatesTO> getSysRegCertificatesByApplication(String searchString, String nr)
            throws WebServiceClientException;

    /**
     * Retrieves a summary of the services processed during the specified
     * reporting period.
     * <p>
     * Requires the {@linkplain RolesConstants#REPORTS_VIEW} role.</p>
     *
     * @param paramsTO The date parameters for the report.
     * @return The data for the Lodgement Report report
     * @throws WebServiceClientException
     */
    List<WorkSummaryTO> getWorkSummary(LodgementViewParamsTO paramsTO) throws WebServiceClientException;

    /**
     * Returns all parties that have the specified role
     * <p>
     * No security role is required to execute this method.</p>
     *
     * @param partyRole The party role.
     * @return
     * @throws WebServiceClientException
     */
    List<PartySummaryTO> getPartiesByRole(String partyRole) throws WebServiceClientException;

    /**
     * Saves changes to all checklist items associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#SERVICE_START_CHECKLIST}
     * role.</p>
     *
     * @param checklistGroupCode
     * @param serviceChecklist
     * @return the list of checklist items for the service after they have been
     * saved.
     * @throws WebServiceClientException
     */
    List<ServiceChecklistItemTO> saveServiceChecklistItem(String checklistGroupCode,
            List<ServiceChecklistItemTO> serviceChecklist)
            throws WebServiceClientException;

    /**
     * Retrieves all checklist items associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_VIEW_APPS} role.</p>
     *
     * @param serviceId Id of the service to retrieve checklist items for
     * @return The checklist items
     */
    List<ServiceChecklistItemTO> getServiceChecklistItem(String serviceId) throws WebServiceClientException;

    /**
     * Saves changes a service / task.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_EDIT_APPS} role.</p>
     *
     * @param service
     * @return the saved service saved.
     */
    ServiceTO saveService(ServiceTO service) throws WebServiceClientException;

    /**
     * Retrieves all public display items associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_VIEW_APPS} role.</p>
     *
     * @param serviceId Id of the service to retrieve public display items for
     * @return The public display items
     */
    List<PublicDisplayItemTO> getPublicDisplayItems(String serviceId) throws WebServiceClientException;

    /**
     * Saves changes a public display item.
     * <p>
     * Requires the {@linkplain RolesConstants#SERVICE_START_PUBLIC_DISPLAY}
     * role.</p>
     *
     * @param publicDisplayItem
     * @return the saved item.
     */
    PublicDisplayItemTO savePublicDisplayItem(PublicDisplayItemTO publicDisplayItem) throws WebServiceClientException;

    /**
     * Saves changes to all public display items associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#SERVICE_START_PUBLIC_DISPLAY}
     * role.</p>
     *
     * @param itemList
     * @return the list of display items for the service after they have been
     * saved.
     */
    List<PublicDisplayItemTO> savePublicDisplayItems(List<PublicDisplayItemTO> itemList) throws WebServiceClientException;

    /**
     * Retrieves all objections associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_VIEW_APPS} role.</p>
     *
     * @param serviceId Id of the service to retrieve objections for
     * @return Objection records
     */
    List<ObjectionTO> getObjections(String serviceId) throws WebServiceClientException;

    /**
     * Saves changes to all objections associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#SERVICE_START_OBJECTIONS}
     * role.</p>
     *
     * @param objections
     * @return the list of objections for the service after they have been
     * saved.
     */
    List<ObjectionTO> saveObjections(List<ObjectionTO> objections) throws WebServiceClientException;

    /**
     * Retrieves all notification parties associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#APPLICATION_VIEW_APPS} role.</p>
     *
     * @param serviceId Id of the service to retrieve notification parties for
     * @return Notify records
     */
    List<NotifyTO> getNotifyParties(String serviceId) throws WebServiceClientException;

    /**
     * Saves changes to all notification parties associated to a service.
     * <p>
     * Requires the {@linkplain RolesConstants#SERVICE_START_NOTIFY} role.</p>
     *
     * @param notifications
     * @return the list of notification parties for the service after they have
     * been saved.
     */
    List<NotifyTO> saveNotifyParties(List<NotifyTO> notifications) throws WebServiceClientException;

}
