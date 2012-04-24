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
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.casemanagement.CaseManagement;
import org.sola.webservices.casemanagement.CasemanagementService;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.*;

/**
 * Implementation class for the {@linkplain CaseManagementClient} interface.
 *
 * @author amcdowell
 */
public class CaseManagementClientImpl extends AbstractWSClientImpl implements CaseManagementClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/casemanagement";
    private static final String LOCAL_PART = "casemanagement-service";
    private static final String SERVICE_NAME = "CaseManagement.";

    public CaseManagementClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    private CaseManagement getPort() {
        return getPort(CaseManagement.class, CasemanagementService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        try {
            boolean result = getPort().checkConnection();
            return result;
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "checkConnection", t);
        }
    }

    @Override
    public ApplicationTO createApplication(ApplicationTO application)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "createApplication";
        try {
            ApplicationTO result = getPort().createApplication(application);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public ApplicationTO saveApplication(ApplicationTO application)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "saveApplication";
        try {
            ApplicationTO result = getPort().saveApplication(application);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<LodgementViewTO> getLodgementView(LodgementViewParamsTO lodgementViewParamsTO) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getLodgementView";
        try {
            List<LodgementViewTO> result = getPort().getLodgementView(lodgementViewParamsTO);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<LodgementTimingTO> getLodgementTiming(LodgementViewParamsTO lodgementViewParamsTO) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getLodgementTiming";
        try {
            List<LodgementTimingTO> result = getPort().getLodgementTiming(lodgementViewParamsTO);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<BrReportTO> getAllBrs() throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getBrReport";
        try {
            List<BrReportTO> result = getPort().getAllBrs();
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public AddressTO getAddress(String id) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getAddress";
        try {
            AddressTO result = getPort().getAddress(id);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public ApplicationTO getApplication(String id) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getApplication";
        try {
            ApplicationTO result = getPort().getApplication(id);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public PartyTO getParty(String id) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getParty";
        try {
            PartyTO result = getPort().getParty(id);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<PartySummaryTO> getAgents() throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getAgents";
        try {
            List<PartySummaryTO> result = getPort().getAgents();
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public ApplicationTO calculateFee(ApplicationTO application) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "calculateFee";
        try {
            ApplicationTO result = getPort().calculateFee(application);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public PartyTO saveParty(PartyTO party)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "saveParty";
        try {
            PartyTO result = getPort().saveParty(party);
            return result;
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public SourceTO attachSourceToTransaction(String serviceId, String sourceId)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "attachSourceToTransaction";
        try {
            return getPort().attachSourceToTransaction(serviceId, sourceId, this.getLanguageCode());
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public boolean dettachSourceFromTransaction(String sourceId) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "attachSourceToTransaction";
        try {
            return getPort().dettachSourceFromTransaction(sourceId);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return false;
        }
    }

    @Override
    public List<SourceTO> getSourcesByServiceId(String serviceId) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getAgents";
        try {
            return getPort().getSourcesByServiceId(serviceId);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<SourceTO> getSourcesByIds(List<String> sourceIds) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getSourcesByIds";
        try {
            return getPort().getSourcesByIds(sourceIds);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> serviceActionComplete(String serviceId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "serviceActionComplete";
        try {
            return getPort().serviceActionComplete(serviceId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> serviceActionRevert(String serviceId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "serviceActionRevert";
        try {
            return getPort().serviceActionRevert(serviceId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> serviceActionStart(String serviceId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "serviceActionStart";
        try {
            return getPort().serviceActionStart(serviceId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> serviceActionCancel(String serviceId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "serviceActionCancel";
        try {
            return getPort().serviceActionCancel(serviceId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionApprove(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionApprove";
        try {
            return getPort().applicationActionApprove(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionArchive(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionArchive";
        try {
            return getPort().applicationActionArchive(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionAssign(
            String applicationId, String userId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionAssign";
        try {
            return getPort().applicationActionAssign(
                    applicationId, userId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionCancel(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionCancel";
        try {
            return getPort().applicationActionCancel(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionDespatch(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionDespatch";
        try {
            return getPort().applicationActionDespatch(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionLapse(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionLapse";
        try {
            return getPort().applicationActionLapse(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionRequisition(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionRequisition";
        try {
            return getPort().applicationActionRequisition(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionResubmit(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionResubmit";
        try {
            return getPort().applicationActionResubmit(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionUnassign(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionUnassign";
        try {
            return getPort().applicationActionUnassign(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionValidate(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionValidate";
        try {
            return getPort().applicationActionValidate(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public List<ValidationResult> applicationActionWithdraw(String applicationId, int rowVersion)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "applicationActionWithdraw";
        try {
            return getPort().applicationActionWithdraw(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public ServiceTO saveInformationService(ServiceTO service) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "saveInformationService";
        try {
            return getPort().saveInformationService(
                    service, this.getLanguageCode());
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public SourceTO saveSource(SourceTO sourceTO) {
        final String inputService = SERVICE_NAME + "saveSource";
        try {
            return getPort().saveSource(sourceTO);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }

    @Override
    public SourceTO getSourceById(String sourceId) throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getSourceById";
        try {
            return getPort().getSourceById(sourceId);
        } catch (Throwable e) {
            handleExceptionsMethod(inputService, e);
            return null;
        }
    }
}
