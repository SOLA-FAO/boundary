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
package org.sola.services.boundary.ws;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.boundary.transferobjects.casemanagement.*;
import org.sola.services.common.br.ValidationResult;
import org.sola.services.common.faults.OptimisticLockingFault;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.SOLAValidationFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.ejb.address.businesslogic.AddressEJBLocal;
import org.sola.services.ejb.application.businesslogic.ApplicationEJBLocal;
import org.sola.services.ejb.application.repository.entities.Application;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.application.repository.entities.Service;
import org.sola.services.ejb.party.businesslogic.PartyEJBLocal;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.faults.SOLAAccessFault;
import org.sola.services.ejb.application.repository.entities.WorkSummary;
import org.sola.services.ejb.party.repository.entities.Party;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.PowerOfAttorney;
import org.sola.services.ejb.source.repository.entities.Source;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;

/**
 * Web Service Boundary class to expose Case Management functionality available on  
 * {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB}, 
 * {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB}, 
 * {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB} and
 * {@linkplain org.sola.services.ejb.address.businesslogic.AddressEJB}. 
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

    /**
     * Web method that can be used to validate if the web service is available.
     *
     * @return Always true
     */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#createApplication(org.sola.services.ejb.application.repository.entities.Application)
     * ApplicationEJB.createApplication}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
    @WebMethod(operationName = "CreateApplication")
    public ApplicationTO CreateApplication(
            @WebParam(name = "application") ApplicationTO application,
            @WebParam(name = "languageCode") final String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault, OptimisticLockingFault, SOLAValidationFault {

        final Object[] params = {application};
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                ApplicationTO application = (ApplicationTO) params[0];
                if (application != null) {
                    Application newApp = applicationEJB.createApplication(
                            GenericTranslator.fromTO(application, Application.class,
                            applicationEJB.getApplication(application.getId())), languageCode);
                    result[0] = GenericTranslator.toTO(newApp, ApplicationTO.class);
                }
            }
        });

        return (ApplicationTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#saveApplication(org.sola.services.ejb.application.repository.entities.Application)
     * ApplicationEJB.saveApplication}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveApplication")
    public ApplicationTO SaveApplication(
            @WebParam(name = "application") ApplicationTO application,
            @WebParam(name = "languageCode") final String languageCode)
            throws SOLAFault, UnhandledFault, OptimisticLockingFault,
            SOLAValidationFault, SOLAAccessFault {

        final Object[] params = {application};
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                ApplicationTO application = (ApplicationTO) params[0];
                if (application != null) {

                    Application newApp = applicationEJB.saveApplication(
                            GenericTranslator.fromTO(application, Application.class,
                            applicationEJB.getApplication(application.getId())), languageCode);
                    result[0] = GenericTranslator.toTO(newApp, ApplicationTO.class);
                }
            }
        });

        return (ApplicationTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#saveSource(org.sola.services.ejb.source.repository.entities.Source)
     * SourceEJB#saveSource}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "saveSource")
    public SourceTO saveSource(@WebParam(name = "sourceTO") final SourceTO sourceTO)
            throws SOLAFault, UnhandledFault, OptimisticLockingFault,
            SOLAValidationFault, SOLAAccessFault {

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                if (sourceTO != null) {
                    Source source = sourceEJB.saveSource(
                            GenericTranslator.fromTO(sourceTO, Source.class,
                            sourceEJB.getSourceById(sourceTO.getId())));
                    result[0] = GenericTranslator.toTO(source, SourceTO.class);
                }
            }
        });

        return (SourceTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#saveParty(org.sola.services.ejb.party.repository.entities.Party)
     * PartyEJB#saveParty}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
    @WebMethod(operationName = "SaveParty")
    public PartyTO SaveParty(@WebParam(name = "party") PartyTO party)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final Object[] params = {party};
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

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

    /**
     * See {@linkplain org.sola.services.ejb.address.businesslogic.AddressEJB#getAddress(java.lang.String)
     * AddressEJB.getAddress}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAddress")
    public AddressTO GetAddress(@WebParam(name = "id") String id) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final String idTmp = id;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(addressEJB.getAddress(idTmp),
                        AddressTO.class);
            }
        });

        return (AddressTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getApplication(java.lang.String)
     * ApplicationEJB.getApplication}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetApplication")
    public ApplicationTO GetApplication(@WebParam(name = "id") String id)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String idTmp = id;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(applicationEJB.getApplication(idTmp),
                        ApplicationTO.class);
            }
        });

        return (ApplicationTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getParty(java.lang.String)
     * PartyEJB.getParty}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetParty")
    public PartyTO GetParty(@WebParam(name = "id") String id) throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String idTmp = id;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(partyEJB.getParty(idTmp), PartyTO.class);
            }
        });

        return (PartyTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getAgents()
     * PartyEJB.getAgents}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAgents")
    public List<PartySummaryTO> GetAgents() throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(partyEJB.getAgents(),
                        PartySummaryTO.class);
            }
        });

        return (List<PartySummaryTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getUserActions(java.lang.String, java.util.Date, java.util.Date)
     * ApplicationEJB.getUserActions}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetUserActions")
    public List<ApplicationLogTO> getUserActions(@WebParam(name = "userName") String user,
            @WebParam(name = "from") Date from,
            @WebParam(name = "to") Date to)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String userTmp = user;
        final Date fromTmp = from;
        final Date toTmp = to;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getUserActions(userTmp, fromTmp, toTmp),
                        ApplicationLogTO.class);
            }
        });

        return (List<ApplicationLogTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#calculateFeesAndDates(org.sola.services.ejb.application.repository.entities.Application)
     * ApplicationEJB.calculateFeesAndDates}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "CalculateFee")
    public ApplicationTO CalculateFee(@WebParam(name = "application") ApplicationTO application)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final ApplicationTO applicationTmp = application;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                Application app = GenericTranslator.fromTO(applicationTmp,
                        Application.class, null);
                result[0] = GenericTranslator.toTO(
                        applicationEJB.calculateFeesAndDates(app), ApplicationTO.class);
            }
        });

        return (ApplicationTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#attachSourceToTransaction(java.lang.String, java.lang.String, java.lang.String)
     * SourceEJB.attachSourceToTransaction}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "AttachSourceToTransaction")
    public SourceTO AttachSourceToTransaction(
            @WebParam(name = "serviceId") String serviceId,
            @WebParam(name = "sourceId") String sourceId,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        final String serviceIdTmp = serviceId;
        final String sourceIdTmp = sourceId;
        final String languageCodeTmp = languageCode;

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        sourceEJB.attachSourceToTransaction(serviceIdTmp, sourceIdTmp, languageCodeTmp),
                        SourceTO.class);
            }
        });

        return (SourceTO) result[0];
    }

    public PowerOfAttorneyTO attachPowerOfAttorneyToTransaction(
            @WebParam(name = "serviceId") final String serviceId,
            @WebParam(name = "powerOfAttorney") final PowerOfAttorneyTO powerOfAttorney,
            @WebParam(name = "languageCode") final String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {
        
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        sourceEJB.attachPowerOfAttorneyToTransaction(
                        serviceId, GenericTranslator.fromTO(powerOfAttorney, 
                        PowerOfAttorney.class, null), languageCode), PowerOfAttorneyTO.class);
            }
        });

        return (PowerOfAttorneyTO) result[0];
    }
    
    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#dettachSourceFromTransaction(java.lang.String)
     * SourceEJB.dettachSourceFromTransaction}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "DettachSourceFromTransaction")
    public boolean DettachSourceFromTransaction(
            @WebParam(name = "sourceId") String sourceId)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final String sourceIdTmp = sourceId;
        final boolean[] result = {false};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = sourceEJB.dettachSourceFromTransaction(sourceIdTmp);
            }
        });

        return result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getSourcesByServiceId(java.lang.String)
     * SourceEJB.getSourcesByServiceId}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSourcesByServiceId")
    public List<SourceTO> GetSourcesByServiceId(
            @WebParam(name = "serviceId") String serviceId) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final String serviceIdTmp = serviceId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        sourceEJB.getSourcesByServiceId(serviceIdTmp),
                        SourceTO.class);
            }
        });

        return (List<SourceTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getPowerOfAttorneyByServiceId(java.lang.String)
     * SourceEJB.getPowerOfAttorneyByServiceId}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "getPowerOfAttorneyByServiceId")
    public List<PowerOfAttorneyTO> getPowerOfAttorneyByServiceId(
            @WebParam(name = "serviceId") final String serviceId) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        sourceEJB.getPowerOfAttorneyByServiceId(serviceId),
                        PowerOfAttorneyTO.class);
            }
        });

        return (List<PowerOfAttorneyTO>) result[0];
    }
    
    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getSources(java.util.List)
     * SourceEJB.getSources}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSourcesByIds")
    public List<SourceTO> GetSourcesByIds(
            @WebParam(name = "sourceIds") List<String> sourceIds) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final List<String> sourceIdsTmp = sourceIds;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        sourceEJB.getSources(sourceIdsTmp),
                        SourceTO.class);
            }
        });

        return (List<SourceTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getSourceById(java.lang.String)
     * SourceEJB.getSourceById}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "getSourceById")
    public SourceTO getSourceById(
            @WebParam(name = "id") final String sourceId) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        sourceEJB.getSourceById(sourceId), SourceTO.class);
            }
        });

        return (SourceTO) result[0];
    }
    
    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getSourceById(java.lang.String)
     * SourceEJB.getSourceById}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "getPowerOfAttorneyById")
    public PowerOfAttorneyTO getPowerOfAttorneyById(
            @WebParam(name = "id") final String id) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        sourceEJB.getPowerOfAttorneyById(id), PowerOfAttorneyTO.class);
            }
        });

        return (PowerOfAttorneyTO) result[0];
    }

    /** Returns {@link ApplicationTO} by the given transaction ID. */
    @WebMethod(operationName = "getApplicationByTransactionId")
    public ApplicationTO getApplicationByTransactionId(
            @WebParam(name = "transactionId") final String transactionId) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        applicationEJB.getApplicationByTransactionId(transactionId), ApplicationTO.class);
            }
        });

        return (ApplicationTO) result[0];
    }
            
    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#serviceActionComplete(java.lang.String, java.lang.String, int)
     * ApplicationEJB.serviceActionComplete}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionComplete(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#serviceActionCancel(java.lang.String, java.lang.String, int)
     * ApplicationEJB.serviceActionCancel}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionCancel(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#serviceActionRevert(java.lang.String, java.lang.String, int)
     * ApplicationEJB.serviceActionRevert}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionRevert(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#serviceActionStart(java.lang.String, java.lang.String, int)
     * ApplicationEJB.serviceActionStart}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.serviceActionStart(
                        serviceIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionWithdraw(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionWithdraw}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionWithdraw(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionCancel(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionCancel}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionCancel(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionRequisition(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionRequisition}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionRequisition(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionValidate(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionValidate}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionValidate(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionApprove(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionApprove}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionApprove(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionArchive(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionArchive}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionArchive(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionDespatch(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionDespatch}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionDespatch(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionLapse(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionLapse}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionLapse(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionUnassign(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionUnassign}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionUnassign(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionAssign(java.lang.String, java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionAssign}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionAssign(
                        applicationIdTmp, userIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#applicationActionResubmit(java.lang.String, java.lang.String, int)
     * ApplicationEJB.applicationActionResubmit}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
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

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = applicationEJB.applicationActionResubmit(
                        applicationIdTmp, languageCodeTmp, rowVersionTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getAllBrs()
     * SystemEJB.getAllBrs}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAllBrs")
    public List<BrReportTO> GetAllBrs() throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(systemEJB.getAllBrs(),
                        BrReportTO.class);
            }
        });

        return (List<BrReportTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#saveInformationService(org.sola.services.ejb.application.repository.entities.Service, java.lang.String)
     * ApplicationEJB.saveInformationService}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
    @WebMethod(operationName = "SaveInformationService")
    public ServiceTO SaveInformationService(
            @WebParam(name = "service") ServiceTO service,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault, SOLAValidationFault {

        final String languageCodeTmp = languageCode;
        final ServiceTO serviceTmp = service;
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

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
    
     /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getSysRegCertificatesByLocation(java.lang.String)
     * CadastreEJB.getSysRegPubDisParcelNameByLocation}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSysRegCertificatesByLocation")
    public List<SysRegCertificatesTO> GetSysRegCertificatesByLocation(
            @WebParam(name = "searchString") String searchString)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String searchStringTmp = searchString;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getSysRegCertificatesByLocation(searchStringTmp),
                        SysRegCertificatesTO.class);
            }
        });

        return (List<SysRegCertificatesTO>) result[0];
    }
    
      /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getSysRegCertificatesByApplication(java.lang.String)
     * CadastreEJB.getSysRegCertificatesByApplication}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSysRegCertificatesByApplication")
    public List<SysRegCertificatesTO> GetSysRegCertificatesByApplication(
            @WebParam(name = "searchString") String searchString,
            @WebParam(name = "nr") String nr)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String searchStringTmp = searchString;
        final String nrTmp = nr;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getSysRegCertificatesByApplication(searchStringTmp, nrTmp),
                        SysRegCertificatesTO.class);
            }
        });

        return (List<SysRegCertificatesTO>) result[0];
    }
    
   /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getWorkSummary(java.util.Date, java.util.Date)
     * ApplicationEJB.getWorkSummary}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetWorkSummary")
    public List<WorkSummaryTO> getWorkSummary(
            @WebParam(name = "paramsTO") LodgementViewParamsTO paramsTO)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final LodgementViewParamsTO paramsTOTmp = paramsTO;
        final Object[] result = {null};

        if (paramsTO != null) {
            runGeneralQuery(wsContext, new Runnable() {

                @Override
                public void run() {
                    List<WorkSummary> appList = applicationEJB.getWorkSummary(paramsTOTmp.getFromDate(),
                            paramsTOTmp.getToDate());
                    result[0] = GenericTranslator.toTOList(
                            appList, WorkSummaryTO.class);
                }
            });
        }
        return (List<WorkSummaryTO>) result[0];
    }  
    
}
