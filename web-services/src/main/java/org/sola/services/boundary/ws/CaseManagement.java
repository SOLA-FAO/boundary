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
package org.sola.services.boundary.ws;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.boundary.transferobjects.casemanagement.ServiceTO;
import org.sola.services.boundary.transferobjects.casemanagement.SourceTO;
import org.sola.services.common.br.ValidationResult;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.OptimisticLockingFault;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.SOLAValidationFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.ejb.address.businesslogic.AddressEJBLocal;
import org.sola.services.boundary.transferobjects.casemanagement.AddressTO;
import org.sola.services.ejb.application.businesslogic.ApplicationEJBLocal;
import org.sola.services.ejb.application.repository.entities.Application;
import org.sola.services.boundary.transferobjects.casemanagement.ApplicationLogTO;
import org.sola.services.boundary.transferobjects.casemanagement.ApplicationTO;
import org.sola.services.boundary.transferobjects.casemanagement.BrReportTO;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.application.repository.entities.Service;
import org.sola.services.ejb.party.businesslogic.PartyEJBLocal;
import org.sola.services.boundary.transferobjects.casemanagement.PartySummaryTO;
import org.sola.services.boundary.transferobjects.casemanagement.PartyTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.faults.SOLAAccessFault;
import org.sola.services.ejb.party.repository.entities.Party;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;

/**
 *
 * @author soladev
 */
@WebService(serviceName = "casemanagement-service", targetNamespace = ServiceConstants.CASE_MAN_WS_NAMESPACE)
public class CaseManagement extends AbstractWebService {

    @EJB
    private ApplicationEJBLocal applicationEJB;
    @EJB
    private PartyEJBLocal partyEJB;
    @EJB
    private AddressEJBLocal addressEJB;
    @EJB
    private SourceEJBLocal sourceEJB;
    @EJB
    private SystemEJBLocal systemEJB;
    @Resource
    private WebServiceContext wsContext;

    /** Dummy method to check the web service instance is working */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    @WebMethod(operationName = "CreateApplication")
    public ApplicationTO CreateApplication(@WebParam(name = "application") ApplicationTO application)
            throws SOLAFault, UnhandledFault {

        final Object[] params = {application};
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                ApplicationTO application = (ApplicationTO) params[0];
                if (application != null) {
                    Application newApp = applicationEJB.createApplication(
                            GenericTranslator.fromTO(application, Application.class,
                            applicationEJB.getApplication(application.getId())));
                    result[0] = GenericTranslator.toTO(newApp, ApplicationTO.class);
                }
            }
        });

        return (ApplicationTO) result[0];
    }

    @WebMethod(operationName = "SaveApplication")
    public ApplicationTO SaveApplication(@WebParam(name = "application") ApplicationTO application)
            throws SOLAFault, UnhandledFault, OptimisticLockingFault,
            SOLAValidationFault, SOLAAccessFault {

        final Object[] params = {application};
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                ApplicationTO application = (ApplicationTO) params[0];
                if (application != null) {

                    Application newApp = applicationEJB.saveApplication(
                            GenericTranslator.fromTO(application, Application.class,
                            applicationEJB.getApplication(application.getId())));
                    result[0] = GenericTranslator.toTO(newApp, ApplicationTO.class);
                }
            }
        });

        return (ApplicationTO) result[0];
    }

    @WebMethod(operationName = "SaveParty")
    public PartyTO SaveParty(@WebParam(name = "party") PartyTO party)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final Object[] params = {party};
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                PartyTO party = (PartyTO) params[0];
                if (party != null) {

                    Party newParty = partyEJB.saveParty(
                            GenericTranslator.fromTO(party, Party.class,
                            partyEJB.getParty(party.getId())));
                    result[0] = GenericTranslator.toTO(newParty, PartyTO.class);
                }
            }
        });

        return (PartyTO) result[0];
    }

    @WebMethod(operationName = "GetAddress")
    public AddressTO GetAddress(@WebParam(name = "id") String id) throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                AddressTO result = null;
                GenericTranslator.toTO(addressEJB.getAddress(id),
                        AddressTO.class);
                commitTransaction();

                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetApplication")
    public ApplicationTO GetApplication(@WebParam(name = "id") String id)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                ApplicationTO result = GenericTranslator.toTO(applicationEJB.getApplication(id),
                        ApplicationTO.class);
                commitTransaction();

                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetParty")
    public PartyTO GetParty(@WebParam(name = "id") String id) throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                PartyTO result = GenericTranslator.toTO(partyEJB.getParty(id), PartyTO.class);

                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetAgents")
    public List<PartySummaryTO> GetAgents() throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                List<PartySummaryTO> agents = GenericTranslator.toTOList(partyEJB.getAgents(),
                        PartySummaryTO.class);
                commitTransaction();

                return agents;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetUserActions")
    public List<ApplicationLogTO> getUserActions(@WebParam(name = "userName") String user,
            @WebParam(name = "from") Date from,
            @WebParam(name = "to") Date to)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            List<ApplicationLogTO> result = GenericTranslator.toTOList(
                    applicationEJB.getUserActions(user, from, to),
                    ApplicationLogTO.class);

            return result;
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "CalculateFee")
    public ApplicationTO CalculateFee(@WebParam(name = "application") ApplicationTO application)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                // This action does not save any details, so translate into a
                // detached entity graph
                Application app = GenericTranslator.fromTO(application,
                        Application.class, null);
                ApplicationTO result = GenericTranslator.toTO(
                        applicationEJB.calculateFeesAndDates(app), ApplicationTO.class);

                commitTransaction();

                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "AttachSourceToTransaction")
    public SourceTO AttachSourceToTransaction(
            @WebParam(name = "serviceId") String serviceId,
            @WebParam(name = "sourceId") String sourceId,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {
        try {
            try {
                beginTransaction();
                SourceTO result = GenericTranslator.toTO(
                        sourceEJB.attachSourceToTransaction(serviceId, sourceId, languageCode),
                        SourceTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }

            if (fault.getClass() == OptimisticLockingFault.class) {
                throw (OptimisticLockingFault) fault;
            }
            if (fault.getClass() == SOLAValidationFault.class) {
                throw (SOLAValidationFault) fault;
            }
            if (fault.getClass() == SOLAAccessFault.class) {
                throw (SOLAAccessFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "DettachSourceFromTransaction")
    public boolean DettachSourceFromTransaction(
            @WebParam(name = "sourceId") String sourceId)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                boolean result = sourceEJB.dettachSourceFromTransaction(sourceId);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }

            if (fault.getClass() == OptimisticLockingFault.class) {
                throw (OptimisticLockingFault) fault;
            }
            if (fault.getClass() == SOLAValidationFault.class) {

                throw (SOLAValidationFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetSourcesByServiceId")
    public List<SourceTO> GetSourcesByServiceId(
            @WebParam(name = "serviceId") String serviceId) throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                List<SourceTO> result = GenericTranslator.toTOList(
                        sourceEJB.getSourcesByServiceId(serviceId),
                        SourceTO.class);
                commitTransaction();

                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetSourcesByIds")
    public List<SourceTO> GetSourcesByIds(
            @WebParam(name = "sourceIds") List<String> sourceIds) throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                List<SourceTO> result = GenericTranslator.toTOList(
                        sourceEJB.getSourcesByIds(sourceIds),
                        SourceTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "ServiceActionComplete")
    public List<ValidationResult> ServiceActionComplete(
            @WebParam(name = "serviceId") String serviceId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String serviceIdTmp = serviceId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionComplete(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ServiceActionCancel")
    public List<ValidationResult> ServiceActionCancel(
            @WebParam(name = "serviceId") String serviceId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String serviceIdTmp = serviceId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionCancel(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ServiceActionRevert")
    public List<ValidationResult> ServiceActionRevert(
            @WebParam(name = "serviceId") String serviceId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String serviceIdTmp = serviceId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionRevert(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ServiceActionStart")
    public List<ValidationResult> ServiceActionStart(
            @WebParam(name = "serviceId") String serviceId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String serviceIdTmp = serviceId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionStart(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionWithdraw")
    public List<ValidationResult> ApplicationActionWithdraw(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionWithdraw(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionCancel")
    public List<ValidationResult> ApplicationActionCancel(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionCancel(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionRequisition")
    public List<ValidationResult> ApplicationActionRequisition(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionRequisition(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionValidate")
    public List<ValidationResult> ApplicationActionValidate(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionValidate(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionApprove")
    public List<ValidationResult> ApplicationActionApprove(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionApprove(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionArchive")
    public List<ValidationResult> ApplicationActionArchive(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionArchive(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionDespatch")
    public List<ValidationResult> ApplicationActionDespatch(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionDespatch(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionLapse")
    public List<ValidationResult> ApplicationActionLapse(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionLapse(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionUnassign")
    public List<ValidationResult> ApplicationActionUnassign(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionUnassign(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionAssign")
    public List<ValidationResult> ApplicationActionAssign(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "userId") String userId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String userIdTmp = userId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionAssign(
                        applicationIdTmp, userIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "ApplicationActionResubmit")
    public List<ValidationResult> ApplicationActionResubmit(
            @WebParam(name = "applicationId") String applicationId,
            @WebParam(name = "languageCode") String languageCode,
            @WebParam(name = "rowVersion") int rowVersion)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String applicationIdTmp = applicationId;
        final String languageCodeTmp = languageCode;
        final int rowVersionTmp = rowVersion;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionResubmit(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "GetAllBrs")
    public List<BrReportTO> GetAllBrs() throws SOLAFault, UnhandledFault {
        try {
            // initialize()
            try {
                beginTransaction();
                List<BrReportTO> result = GenericTranslator.toTOList(systemEJB.getAllBrs(),
                        BrReportTO.class);
                commitTransaction();

                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);


            if (fault.getClass() == SOLAFault.class) {

                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "SaveInformationService")
    public ServiceTO SaveInformationService(
            @WebParam(name = "service") ServiceTO service,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String languageCodeTmp = languageCode;
        final ServiceTO serviceTmp = service;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                ServiceTO service = serviceTmp;
                String languageCode = languageCodeTmp;
                if (service != null) {
                    Service serverResult = applicationEJB.saveInformationService(
                            GenericTranslator.fromTO(service, Service.class, null),
                            languageCode);
                    result[0] = GenericTranslator.toTO(serverResult, ServiceTO.class);
                }
            }
        });

        return (ServiceTO) result[0];
    }
}
