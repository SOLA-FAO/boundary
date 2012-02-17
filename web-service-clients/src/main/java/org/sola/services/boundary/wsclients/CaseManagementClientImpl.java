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
 package org.sola.services.boundary.wsclients;


import java.util.Date;
import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;
import org.sola.webservices.casemanagement.SOLAFault;
import org.sola.webservices.casemanagement.SOLAValidationFault;
import org.sola.webservices.casemanagement.UnhandledFault;
import org.sola.webservices.casemanagement.CaseManagement;
import org.sola.webservices.casemanagement.CasemanagementService;
import org.sola.webservices.casemanagement.OptimisticLockingFault;
import org.sola.webservices.casemanagement.SOLAAccessFault;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.casemanagement.PartySummaryTO;
import org.sola.webservices.transferobjects.casemanagement.AddressTO;
import org.sola.webservices.transferobjects.casemanagement.ApplicationLogTO;
import org.sola.webservices.transferobjects.casemanagement.ApplicationTO;
import org.sola.webservices.transferobjects.casemanagement.BrReportTO;
import org.sola.webservices.transferobjects.casemanagement.LodgementTimingTO;
import org.sola.webservices.transferobjects.casemanagement.LodgementViewTO;
import org.sola.webservices.transferobjects.casemanagement.PartyTO;
import org.sola.webservices.transferobjects.casemanagement.ServiceTO;
import org.sola.webservices.transferobjects.casemanagement.SourceTO;
import org.sola.webservices.transferobjects.casemanagement.LodgementViewParamsTO;


/**
 * Implementation class for the {@linkplain CaseManagementClient} interface. 
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
        try {
            ApplicationTO result = getPort().createApplication(application);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "createApplication", t);
        }
    }

    @Override
    public ApplicationTO saveApplication(ApplicationTO application)
            throws WebServiceClientException {
        try {
            ApplicationTO result = getPort().saveApplication(application);
            return result;
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "saveApplication", t);
        }
    }
     
    
     @Override
    public List<LodgementViewTO> getLodgementView(LodgementViewParamsTO lodgementViewParamsTO) throws WebServiceClientException {
        try {
           List<LodgementViewTO> result = getPort().getLodgementView(lodgementViewParamsTO);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getLodgementView", t);
        }
    }
    
     @Override
    public List<LodgementTimingTO> getLodgementTiming(LodgementViewParamsTO lodgementViewParamsTO) throws WebServiceClientException {
        try {
           List<LodgementTimingTO> result = getPort().getLodgementTiming(lodgementViewParamsTO);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getLodgementTiming", t);
        }
    }
    
    @Override
    public List<BrReportTO> getAllBrs() throws WebServiceClientException {
        try {
           List<BrReportTO> result = getPort().getAllBrs();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBrReport", t);
        }
    }
    
      @Override
    public AddressTO getAddress(String id) throws WebServiceClientException {
        try {
            AddressTO result = getPort().getAddress(id);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getAddress", t);
        }
    }

    @Override
    public ApplicationTO getApplication(String id) throws WebServiceClientException {
        try {
            ApplicationTO result = getPort().getApplication(id);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getApplication", t);
        }
    }

    @Override
    public PartyTO getParty(String id) throws WebServiceClientException {
        try {
            PartyTO result = getPort().getParty(id);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getParty", t);
        }
    }

    @Override
    public List<PartySummaryTO> getAgents() throws WebServiceClientException {
        try {
            List<PartySummaryTO> result = getPort().getAgents();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getAgents", t);
        }
    }

    @Override
    public ApplicationTO calculateFee(ApplicationTO application) throws WebServiceClientException {
        try {
            ApplicationTO result = getPort().calculateFee(application);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "calculateFee", t);
        }
    }

    @Override
    public PartyTO saveParty(PartyTO party)
            throws WebServiceClientException {
        try {
            PartyTO result = getPort().saveParty(party);
            return result;
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "saveParty", t);
        }
    }

    @Override
    public SourceTO attachSourceToTransaction(String serviceId, String sourceId)
            throws WebServiceClientException {
        try {
            return getPort().attachSourceToTransaction(serviceId, sourceId, this.getLanguageCode());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "attachSourceToTransaction", t);
        }
    }

    @Override
    public boolean dettachSourceFromTransaction(String sourceId) throws WebServiceClientException {
        try {
            return getPort().dettachSourceFromTransaction(sourceId);
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "attachSourceToTransaction", t);
        }
    }

    @Override
    public List<SourceTO> getSourcesByServiceId(String serviceId) throws WebServiceClientException {
        try {
            return getPort().getSourcesByServiceId(serviceId);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getAgents", t);
        }
    }

    @Override
    public List<SourceTO> getSourcesByIds(List<String> sourceIds) throws WebServiceClientException {
        try {
            return getPort().getSourcesByIds(sourceIds);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getSourcesByIds", t);
        }
    }
    
    @Override
    public List<ValidationResult> serviceActionComplete(String serviceId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().serviceActionComplete(serviceId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "serviceActionComplete", t);
        }
    }

    @Override
    public List<ValidationResult> serviceActionRevert(String serviceId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().serviceActionRevert(serviceId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "serviceActionRevert", t);
        }
    }

    @Override
    public List<ValidationResult> serviceActionStart(String serviceId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().serviceActionStart(serviceId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "serviceActionStart", t);
        }
    }

    @Override
    public List<ValidationResult> serviceActionCancel(String serviceId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().serviceActionCancel(serviceId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "serviceActionCancel", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionApprove(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionApprove(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionApprove", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionArchive(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionArchive(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionArchive", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionAssign(
            String applicationId, String userId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionAssign(
                    applicationId, userId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionAssign", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionCancel(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionCancel(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionCancel", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionDespatch(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionDespatch(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionDespatch", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionLapse(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionLapse(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionLapse", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionRequisition(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionRequisition(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionRequisition", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionResubmit(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionResubmit(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionResubmit", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionUnassign(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionUnassign(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionUnassign", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionValidate(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionValidate(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionValidate", t);
        }
    }

    @Override
    public List<ValidationResult> applicationActionWithdraw(String applicationId, int rowVersion)
            throws WebServiceClientException {
        try {
            return getPort().applicationActionWithdraw(
                    applicationId, this.getLanguageCode(), rowVersion);
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "applicationActionWithdraw", t);
        }
    }
    
    @Override
     public ServiceTO saveInformationService(ServiceTO service) throws WebServiceClientException{
        try {
            return getPort().saveInformationService(
                    service, this.getLanguageCode());
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "saveInformationService", t);
        }        
    }

}
