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
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.casemanagement.CaseManagement;
import org.sola.webservices.casemanagement.CasemanagementService;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.*;

/**
 * Implementation class for the {@linkplain CaseManagementClient} interface.
 *
 */
public class CaseManagementClientImpl extends AbstractWSClientImpl implements CaseManagementClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/casemanagement";
    private static final String LOCAL_PART = "casemanagement-service";

    /**
     * Creates a web service client class for the web service hosted at the
     * specified URL
     *
     * @param url The location of the service WSDL
     */
    public CaseManagementClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected CaseManagement getPort() {
        return getPort(CaseManagement.class, CasemanagementService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = CaseManagementClient.CHECK_CONNECTION;
        try {
            beforeWebMethod(methodName);
            result = getPort().checkConnection();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public ApplicationTO createApplication(ApplicationTO application)
            throws WebServiceClientException {
        ApplicationTO result = null;
        final String methodName = CaseManagementClient.CREATE_APPLICATION;
        try {
            beforeWebMethod(methodName, application);
            result = getPort().createApplication(application, getLanguageCode());
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, application);
        }
        return result;
    }

    @Override
    public ApplicationTO saveApplication(ApplicationTO application)
            throws WebServiceClientException {
        ApplicationTO result = null;
        final String methodName = CaseManagementClient.SAVE_APPLICATION;
        try {
            beforeWebMethod(methodName, application);
            result = getPort().saveApplication(application, getLanguageCode());
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, application);
        }
        return result;
    }

    @Override
    public List<BrReportTO> getAllBrs() throws WebServiceClientException {
        List<BrReportTO> result = null;
        final String methodName = CaseManagementClient.GET_ALL_BRS;
        try {
            beforeWebMethod(methodName);
            result = getPort().getAllBrs();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public AddressTO getAddress(String id) throws WebServiceClientException {
        AddressTO result = null;
        final String methodName = CaseManagementClient.GET_ADDRESS;
        try {
            beforeWebMethod(methodName, id);
            result = getPort().getAddress(id);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id);
        }
        return result;
    }

    @Override
    public ApplicationTO getApplication(String id) throws WebServiceClientException {
        ApplicationTO result = null;
        final String methodName = CaseManagementClient.GET_APPLICATION;
        try {
            beforeWebMethod(methodName, id);
            result = getPort().getApplication(id);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id);
        }
        return result;
    }

    @Override
    public PartyTO getParty(String id) throws WebServiceClientException {
        PartyTO result = null;
        final String methodName = CaseManagementClient.GET_PARTY;
        try {
            beforeWebMethod(methodName, id);
            result = getPort().getParty(id);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id);
        }
        return result;
    }

    @Override
    public List<PartySummaryTO> getAgents() throws WebServiceClientException {
        List<PartySummaryTO> result = null;
        final String methodName = CaseManagementClient.GET_AGENTS;
        try {
            beforeWebMethod(methodName);
            result = getPort().getAgents();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public ApplicationTO calculateFee(ApplicationTO application) throws WebServiceClientException {
        ApplicationTO result = null;
        final String methodName = CaseManagementClient.CALCULATE_FEE;
        try {
            beforeWebMethod(methodName, application);
            result = getPort().calculateFee(application);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, application);
        }
        return result;
    }

    @Override
    public PartyTO saveParty(PartyTO party)
            throws WebServiceClientException {
        PartyTO result = null;
        final String methodName = CaseManagementClient.SAVE_PARTY;
        try {
            beforeWebMethod(methodName, party);
            result = getPort().saveParty(party);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, party);
        }
        return result;
    }

    @Override
    public SourceTO attachSourceToTransaction(String serviceId, String sourceId)
            throws WebServiceClientException {
        SourceTO result = null;
        final String methodName = CaseManagementClient.ATTACH_SOURCE_TO_TRANSACTION;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, serviceId, sourceId, languageCode);
            result = getPort().attachSourceToTransaction(serviceId, sourceId, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, sourceId, languageCode);
        }
        return result;
    }

    @Override
    public boolean dettachSourceFromTransaction(String sourceId) throws WebServiceClientException {
        boolean result = false;
        final String methodName = CaseManagementClient.DETTACH_SOURCE_FROM_TRANSACTION;
        try {
            beforeWebMethod(methodName, sourceId);
            result = getPort().dettachSourceFromTransaction(sourceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sourceId);
        }
        return result;
    }

    @Override
    public List<SourceTO> getSourcesByServiceId(String serviceId) throws WebServiceClientException {
        List<SourceTO> result = null;
        final String methodName = CaseManagementClient.GET_SOURCES_BY_SERVICE_ID;
        try {
            beforeWebMethod(methodName, serviceId);
            result = getPort().getSourcesByServiceId(serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId);
        }
        return result;
    }

    @Override
    public List<SourceTO> getSourcesByIds(List<String> sourceIds) throws WebServiceClientException {
        List<SourceTO> result = null;
        final String methodName = CaseManagementClient.GET_SOURCES_BY_IDS;
        try {
            beforeWebMethod(methodName, sourceIds);
            result = getPort().getSourcesByIds(sourceIds);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sourceIds);
        }
        return result;
    }

    @Override
    public List<ValidationResult> serviceActionComplete(String serviceId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.SERVICE_ACTION_COMPLETE;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, serviceId, languageCode, rowVersion);
            result = getPort().serviceActionComplete(serviceId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> serviceActionRevert(String serviceId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.SERVICE_ACTION_REVERT;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, serviceId, languageCode, rowVersion);
            result = getPort().serviceActionRevert(serviceId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> serviceActionStart(String serviceId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.SERVICE_ACTION_START;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, serviceId, languageCode, rowVersion);
            result = getPort().serviceActionStart(serviceId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> serviceActionCancel(String serviceId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.SERVICE_ACTION_CANCEL;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, serviceId, languageCode, rowVersion);
            result = getPort().serviceActionCancel(serviceId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionApprove(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_APPROVE;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionApprove(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionArchive(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_ARCHIVE;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionArchive(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionAssign(
            String applicationId, String userId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_ASSIGN;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, userId, languageCode, rowVersion);
            result = getPort().applicationActionAssign(applicationId, userId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, userId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionCancel(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_CANCEL;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionCancel(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionDespatch(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_DESPATCH;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionDespatch(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionLapse(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_LAPSE;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionLapse(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionRequisition(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_REQUISITION;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionRequisition(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionResubmit(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_RESUBMIT;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionResubmit(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionUnassign(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_UNASSIGN;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionUnassign(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionValidate(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_VALIDATE;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionValidate(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionWithdraw(String applicationId, int rowVersion)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_WITHDRAW;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionWithdraw(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }

    @Override
    public ServiceTO saveInformationService(ServiceTO service) throws WebServiceClientException {
        ServiceTO result = null;
        final String methodName = CaseManagementClient.SAVE_INFORMATION_SERVICE;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, service, languageCode);
            result = getPort().saveInformationService(service, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, service, languageCode);
        }
        return result;
    }

    @Override
    public SourceTO saveSource(SourceTO sourceTO) {
        SourceTO result = null;
        final String methodName = CaseManagementClient.SAVE_SOURCE;
        try {
            beforeWebMethod(methodName, sourceTO);
            result = getPort().saveSource(sourceTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sourceTO);
        }
        return result;
    }

    @Override
    public SourceTO getSourceById(String sourceId) throws WebServiceClientException {
        SourceTO result = null;
        final String methodName = CaseManagementClient.GET_SOURCE_BY_ID;
        try {
            beforeWebMethod(methodName, sourceId);
            result = getPort().getSourceById(sourceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sourceId);
        }
        return result;
    }

    @Override
    public List<ApplicationLogTO> getUserActions(String userName, XMLGregorianCalendar from, XMLGregorianCalendar to)
            throws WebServiceClientException {
        List<ApplicationLogTO> result = null;
        final String methodName = CaseManagementClient.GET_USER_ACTIONS;
        try {
            beforeWebMethod(methodName, userName, from, to);
            result = getPort().getUserActions(userName, from, to);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, userName, from, to);
        }
        return result;
    }

    @Override
    public PowerOfAttorneyTO attachPowerOfAttorneyToTransaction(String serviceId, PowerOfAttorneyTO powerOfAttorney) throws WebServiceClientException {
        PowerOfAttorneyTO result = null;
        final String methodName = CaseManagementClient.ATTACH_POWER_OF_ATTORNEY_TO_TRANSACTION;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, serviceId, powerOfAttorney, languageCode);
            result = getPort().attachPowerOfAttorneyToTransaction(serviceId, powerOfAttorney, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, powerOfAttorney, languageCode);
        }
        return result;
    }

    @Override
    public List<PowerOfAttorneyTO> getPowerOfAttorneyByServiceId(String serviceId) throws WebServiceClientException {
        List<PowerOfAttorneyTO> result = null;
        final String methodName = CaseManagementClient.GET_POWER_OF_ATTORNEY_BY_SERVICE_ID;
        try {
            beforeWebMethod(methodName, serviceId);
            result = getPort().getPowerOfAttorneyByServiceId(serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId);
        }
        return result;
    }

    @Override
    public PowerOfAttorneyTO getPowerOfAttorneyById(String id) throws WebServiceClientException {
        PowerOfAttorneyTO result = null;
        final String methodName = CaseManagementClient.GET_POWER_OF_ATTORNEY_BY_ID;
        try {
            beforeWebMethod(methodName, id);
            result = getPort().getPowerOfAttorneyById(id);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id);
        }
        return result;
    }

    @Override
    public ApplicationTO getApplicationByTransactionId(String transactionId) throws WebServiceClientException {
        ApplicationTO result = null;
        final String methodName = CaseManagementClient.GET_APPLICATION_BY_TRANSACTION_ID;
        try {
            beforeWebMethod(methodName, transactionId);
            result = getPort().getApplicationByTransactionId(transactionId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, transactionId);
        }
        return result;
    }

    @Override
    public List<SysRegCertificatesTO> getSysRegCertificatesByLocation(String searchString)
            throws WebServiceClientException {
        List<SysRegCertificatesTO> result = null;
        final String methodName = CaseManagementClient.GET_SYS_REG_CERTIFICATES;
        try {
            beforeWebMethod(methodName, searchString);
            result = getPort().getSysRegCertificatesByLocation(searchString);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString);
        }
        return result;
    }

    @Override
    public List<SysRegCertificatesTO> getSysRegCertificatesByApplication(String searchString, String nr)
            throws WebServiceClientException {
        List<SysRegCertificatesTO> result = null;
        final String methodName = CaseManagementClient.GET_SYS_REG_CERTIFICATES_APP;
        try {
            beforeWebMethod(methodName, searchString, nr);
            result = getPort().getSysRegCertificatesByApplication(searchString, nr);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString);
        }
        return result;
    }

    @Override
    public List<WorkSummaryTO> getWorkSummary(LodgementViewParamsTO paramsTO) throws WebServiceClientException {
        List<WorkSummaryTO> result = null;
        final String methodName = CaseManagementClient.GET_WORK_SUMMARY;
        try {
            beforeWebMethod(methodName, paramsTO);
            result = getPort().getWorkSummary(paramsTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, paramsTO);
        }
        return result;
    }

    @Override
    public List<ValidationResult> applicationActionTransfer(
            String applicationId, int rowVersion) throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CaseManagementClient.APPLICATION_ACTION_TRANSFER;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, applicationId, languageCode, rowVersion);
            result = getPort().applicationActionTransfer(applicationId, languageCode, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, applicationId, languageCode, rowVersion);
        }
        return result;
    }
}
