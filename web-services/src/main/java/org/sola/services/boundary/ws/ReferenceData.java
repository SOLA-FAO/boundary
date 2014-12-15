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
package org.sola.services.boundary.ws;

import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import org.sola.common.RolesConstants;
import org.sola.services.boundary.transferobjects.referencedata.*;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.ejb.application.businesslogic.ApplicationEJBLocal;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.party.businesslogic.PartyEJBLocal;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.AbstractCodeTO;
import org.sola.services.common.faults.*;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejb.administrative.businesslogic.AdministrativeEJBLocal;
import org.sola.services.ejb.administrative.repository.entities.BaUnitRelType;
import org.sola.services.ejb.administrative.repository.entities.BaUnitType;
import org.sola.services.ejb.administrative.repository.entities.RrrSubType;
import org.sola.services.ejb.administrative.repository.entities.MortgageType;
import org.sola.services.ejb.administrative.repository.entities.NotationStatusType;
import org.sola.services.ejb.administrative.repository.entities.RrrGroupType;
import org.sola.services.ejb.administrative.repository.entities.RrrType;
import org.sola.services.ejb.administrative.repository.entities.SourceBaUnitRelationType;
import org.sola.services.ejb.administrative.repository.entities.ValuationType;
import org.sola.services.ejb.application.repository.entities.ApplicationActionType;
import org.sola.services.ejb.application.repository.entities.ApplicationStatusType;
import org.sola.services.ejb.application.repository.entities.Authority;
import org.sola.services.ejb.application.repository.entities.ChecklistGroup;
import org.sola.services.ejb.application.repository.entities.ChecklistItem;
import org.sola.services.ejb.application.repository.entities.NegotiateStatus;
import org.sola.services.ejb.application.repository.entities.NegotiateType;
import org.sola.services.ejb.application.repository.entities.NotifyRelationshipType;
import org.sola.services.ejb.application.repository.entities.ObjectionStatus;
import org.sola.services.ejb.application.repository.entities.PublicDisplayItemStatus;
import org.sola.services.ejb.application.repository.entities.PublicDisplayItemType;
import org.sola.services.ejb.application.repository.entities.RequestCategoryType;
import org.sola.services.ejb.application.repository.entities.RequestDisplayGroup;
import org.sola.services.ejb.application.repository.entities.RequestType;
import org.sola.services.ejb.application.repository.entities.TypeAction;
import org.sola.services.ejb.application.repository.entities.ServiceActionType;
import org.sola.services.ejb.application.repository.entities.ServiceStatusType;
import org.sola.services.ejb.cadastre.businesslogic.CadastreEJBLocal;
import org.sola.services.ejb.cadastre.repository.entities.CadastreObjectType;
import org.sola.services.ejb.cadastre.repository.entities.StateLandStatusType;
import org.sola.services.ejb.party.repository.entities.CommunicationType;
import org.sola.services.ejb.party.repository.entities.GenderType;
import org.sola.services.ejb.party.repository.entities.IdType;
import org.sola.services.ejb.party.repository.entities.PartyRoleType;
import org.sola.services.ejb.party.repository.entities.PartyType;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.AvailabilityStatus;
import org.sola.services.ejb.source.repository.entities.PresentationFormType;
import org.sola.services.ejb.source.repository.entities.SourceType;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;
import org.sola.services.ejb.system.repository.entities.BrSeverityType;
import org.sola.services.ejb.system.repository.entities.BrTechnicalType;
import org.sola.services.ejb.system.repository.entities.BrValidationTargetType;
import org.sola.services.ejb.transaction.businesslogic.TransactionEJBLocal;
import org.sola.services.ejb.transaction.repository.entities.RegistrationStatusType;

/**
 * Web Service Boundary class to expose the SOLA reference code values.
 */
@WebService(serviceName = "referencedata-service", targetNamespace = ServiceConstants.REF_DATA_WS_NAMESPACE)
public class ReferenceData extends AbstractWebService {

    @EJB
    ApplicationEJBLocal applicationEJB;
    @EJB
    PartyEJBLocal partyEJB;
    @EJB
    SourceEJBLocal sourceEJB;
    @EJB
    AdministrativeEJBLocal administrativeEJB;
    @EJB
    CadastreEJBLocal cadastreEJB;
    @EJB
    TransactionEJBLocal transactionEJB;
    @EJB
    SystemEJBLocal systemEJB;
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
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getCommunicationTypes(java.lang.String)
     * PartyEJB.getCommunicationTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCommunicationTypes")
    public List<CommunicationTypeTO> GetCommunicationTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        partyEJB.getCommunicationTypes(languageCodeTmp),
                        CommunicationTypeTO.class);
            }
        });

        return (List<CommunicationTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getGenderTypes(java.lang.String)
     * PartyEJB.getGenderTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetGenderTypes")
    public List<GenderTypeTO> GetGenderTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        partyEJB.getGenderTypes(languageCodeTmp),
                        GenderTypeTO.class);
            }
        });

        return (List<GenderTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getAvailabilityStatusList(java.lang.String)
     * SourceEJB.getAvailabilityStatusList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAvailabilityStatusList")
    public List<AvailabilityStatusTO> GetAvailabilityStatusList(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        sourceEJB.getAvailabilityStatusList(languageCodeTmp),
                        AvailabilityStatusTO.class);
            }
        });

        return (List<AvailabilityStatusTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getPresentationFormTypes(java.lang.String)
     * SourceEJB.getPresentationFormTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPresentationFormTypes")
    public List<PresentationFormTypeTO> GetPresentationFormTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        sourceEJB.getPresentationFormTypes(languageCodeTmp),
                        PresentationFormTypeTO.class);
            }
        });

        return (List<PresentationFormTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getRequestTypes(java.lang.String)
     * ApplicationEJB.getRequestTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRequestTypes")
    public List<RequestTypeTO> GetRequestTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getRequestTypes(languageCodeTmp), RequestTypeTO.class);
            }
        });

        return (List<RequestTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getRequestCategoryTypes(java.lang.String)
     * ApplicationEJB.getRequestCategoryTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRequestCategoryTypes")
    public List<RequestCategoryTypeTO> GetRequestCategoryTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getRequestCategoryTypes(languageCodeTmp), RequestCategoryTypeTO.class);
            }
        });

        return (List<RequestCategoryTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#getSourceTypes(java.lang.String)
     * SourceEJB.getSourceTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSourceTypes")
    public List<SourceTypeTO> GetSourceTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        sourceEJB.getSourceTypes(languageCodeTmp), SourceTypeTO.class);
            }
        });

        return (List<SourceTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getApplicationStatusTypes(java.lang.String)
     * ApplicationEJB.getApplicationStatusTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetApplicationStatusTypes")
    public List<ApplicationStatusTypeTO> GetApplicationStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getApplicationStatusTypes(languageCodeTmp),
                        ApplicationStatusTypeTO.class);
            }
        });

        return (List<ApplicationStatusTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getApplicationActionTypes(java.lang.String)
     * ApplicationEJB.getApplicationActionTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetApplicationActionTypes")
    public List<ApplicationActionTypeTO> GetApplicationActionTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getApplicationActionTypes(languageCodeTmp),
                        ApplicationActionTypeTO.class);
            }
        });

        return (List<ApplicationActionTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getServiceStatusTypes(java.lang.String)
     * ApplicationEJB.getServiceStatusTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetServiceStatusTypes")
    public List<ServiceStatusTypeTO> GetServiceStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getServiceStatusTypes(languageCodeTmp),
                        ServiceStatusTypeTO.class);
            }
        });

        return (List<ServiceStatusTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getServiceActionTypes(java.lang.String)
     * ApplicationEJB.getServiceActionTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetServiceActionTypes")
    public List<ServiceActionTypeTO> GetServiceActionTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getServiceActionTypes(languageCodeTmp),
                        ServiceActionTypeTO.class);
            }
        });

        return (List<ServiceActionTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getPartyTypes(java.lang.String)
     * PartyEJB.getPartyTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPartyTypes")
    public List<PartyTypeTO> GetPartyTypes(String languageCode) throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        partyEJB.getPartyTypes(languageCodeTmp), PartyTypeTO.class);
            }
        });

        return (List<PartyTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getPartyRoles(java.lang.String)
     * PartyEJB.getPartyRoles}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPartyRoles")
    public List<PartyRoleTypeTO> GetPartyRoles(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        partyEJB.getPartyRoles(languageCodeTmp), PartyRoleTypeTO.class);
            }
        });

        return (List<PartyRoleTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.party.businesslogic.PartyEJB#getIdTypes(java.lang.String)
     * PartyEJB.getIdTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetIdTypes")
    public List<IdTypeTO> GetIdTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        partyEJB.getIdTypes(languageCodeTmp), IdTypeTO.class);
            }
        });

        return (List<IdTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getBaUnitTypes(java.lang.String)
     * AdministrativeEJB.getBaUnitTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetBaUnitTypes")
    public List<BaUnitTypeTO> GetBaUnitTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getBaUnitTypes(languageCodeTmp), BaUnitTypeTO.class);
            }
        });

        return (List<BaUnitTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getChangeStatusTypes(java.lang.String)
     * AdministrativeEJB.getChangeStatusTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetChangeStatuTypes")
    public List<ChangeStatusTypeTO> GetChangeStatuTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getChangeStatusTypes(languageCodeTmp),
                        ChangeStatusTypeTO.class);
            }
        });

        return (List<ChangeStatusTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getMortgageTypes(java.lang.String)
     * AdministrativeEJB.getMortgageTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetMortgageTypes")
    public List<MortgageTypeTO> GetMortgageTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getMortgageTypes(languageCodeTmp), MortgageTypeTO.class);
            }
        });

        return (List<MortgageTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getConditionTypes(java.lang.String) (java.lang.String)
     * AdministrativeEJB.getConditionTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "getConditionTypes")
    public List<ConditionTypeTO> getConditionTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getConditionTypes(languageCodeTmp), ConditionTypeTO.class);
            }
        });

        return (List<ConditionTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getRRRGroupTypes(java.lang.String)
     * AdministrativeEJB.getRRRGroupTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRRRGroupTypes")
    public List<RrrGroupTypeTO> GetRRRGroupTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getRRRGroupTypes(languageCodeTmp), RrrGroupTypeTO.class);
            }
        });

        return (List<RrrGroupTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getRRRTypes(java.lang.String)
     * AdministrativeEJB.getRRRTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRRRTypes")
    public List<RrrTypeTO> GetRRRTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getRRRTypes(languageCodeTmp), RrrTypeTO.class);
            }
        });

        return (List<RrrTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getSourceBaUnitRelationTypes(java.lang.String)
     * AdministrativeEJB.getSourceBaUnitRelationTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSourceBaUnitRelationTypes")
    public List<SourceBaUnitRelationTypeTO> GetSourceBaUnitRelationTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getSourceBaUnitRelationTypes(languageCodeTmp),
                        SourceBaUnitRelationTypeTO.class);
            }
        });

        return (List<SourceBaUnitRelationTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#getRegistrationStatusTypes(java.lang.String)
     * TransactionEJB.getRegistrationStatusTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRegistrationStatusTypes")
    public List<RegistrationStatusTypeTO> GetRegistrationStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        transactionEJB.getRegistrationStatusTypes(languageCodeTmp),
                        RegistrationStatusTypeTO.class);
            }
        });

        return (List<RegistrationStatusTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getLandUseTypes(java.lang.String)
     * CadastreEJB.getCadastreObjectTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetLandUseTypes")
    public List<LandUseTypeTO> GetLandUseTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getLandUseTypes(languageCodeTmp),
                        LandUseTypeTO.class);
            }
        });

        return (List<LandUseTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectTypes(java.lang.String)
     * CadastreEJB.getCadastreObjectTypes}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectTypes")
    public List<CadastreObjectTypeTO> GetCadastreObjectTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectTypes(languageCodeTmp),
                        CadastreObjectTypeTO.class);
            }
        });

        return (List<CadastreObjectTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.application.businesslogic.ApplicationEJB#getTypeActions(java.lang.String)
     * ApplicationEJB.getTypeActions}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetTypeActions")
    public List<TypeActionTO> GetTypeActions(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        applicationEJB.getTypeActions(languageCodeTmp),
                        TypeActionTO.class);
            }
        });

        return (List<TypeActionTO>) result[0];
    }

    /**
     * Uses the {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * SystemEJB.getCodeEntityList} to retrieve the BrTechnicalType codes.
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetBrTechnicalTypes")
    public List<BrTechnicalTypeTO> GetBrTechnicalTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(systemEJB.getCodeEntityList(
                        BrTechnicalType.class, languageCode), BrTechnicalTypeTO.class);
            }
        });

        return (List<BrTechnicalTypeTO>) result[0];
    }

    /**
     * Uses the {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * SystemEJB.getCodeEntityList} to retrieve the BrValidationTargetType
     * codes.
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetBrValidationTargetTypes")
    public List<BrValidationTargetTypeTO> GetBrValidationTargetTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(systemEJB.getCodeEntityList(
                        BrValidationTargetType.class, languageCode), BrValidationTargetTypeTO.class);
            }
        });

        return (List<BrValidationTargetTypeTO>) result[0];
    }

    /**
     * Uses the {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * SystemEJB.getCodeEntityList} to retrieve the BrSeverityType codes.
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetBrSeverityTypes")
    public List<BrSeverityTypeTO> GetBrSeverityTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(systemEJB.getCodeEntityList(
                        BrSeverityType.class, languageCode), BrSeverityTypeTO.class);
            }
        });

        return (List<BrSeverityTypeTO>) result[0];
    }

    /**
     * Uses the {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * SystemEJB.getCodeEntityList} to retrieve the BaUnitRelType codes.
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetBaUnitRelTypes")
    public List<BaUnitRelTypeTO> GetBaUnitRelTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(administrativeEJB.getCodeEntityList(
                        BaUnitRelType.class, languageCode), BaUnitRelTypeTO.class);
            }
        });

        return (List<BaUnitRelTypeTO>) result[0];
    }

    /**
     * Uses the {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * SystemEJB.getCodeEntityList} to retrieve the BrTechnicalType codes.
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetHierarchyLevels")
    public List<HierarchyLevelTO> GetHierarchyLevels(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(cadastreEJB.getHierarchyLevels(languageCode), HierarchyLevelTO.class);
            }
        });

        return (List<HierarchyLevelTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetNotationStatusTypes")
    public List<NotationStatusTypeTO> GetNotationStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getCodeEntityList(NotationStatusType.class, languageCodeTmp),
                        NotationStatusTypeTO.class);
            }
        });

        return (List<NotationStatusTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * CadastreEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetStateLandStatusTypes")
    public List<StateLandStatusTypeTO> GetStateLandStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCodeEntityList(StateLandStatusType.class, languageCodeTmp),
                        StateLandStatusTypeTO.class);
            }
        });

        return (List<StateLandStatusTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRrrSubTypes")
    public List<RrrSubTypeTO> GetRrrSubTypes(String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        administrativeEJB.getCodeEntityList(RrrSubType.class, languageCodeTmp),
                        RrrSubTypeTO.class);
            }
        });

        return (List<RrrSubTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetChecklistGroups")
    public List<ChecklistGroupTO> GetChecklistGroups(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        ChecklistGroup.class, languageCode), ChecklistGroupTO.class);
            }
        });

        return (List<ChecklistGroupTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetChecklistItems")
    public List<ChecklistItemTO> GetChecklistItems(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        ChecklistItem.class, languageCode), ChecklistItemTO.class);
            }
        });

        return (List<ChecklistItemTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPublicDisplayItemTypes")
    public List<PublicDisplayItemTypeTO> GetPublicDisplayItemTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        PublicDisplayItemType.class, languageCode), PublicDisplayItemTypeTO.class);
            }
        });

        return (List<PublicDisplayItemTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPublicDisplayItemStatusTypes")
    public List<PublicDisplayItemStatusTO> GetPublicDisplayItemStatusTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        PublicDisplayItemStatus.class, languageCode), PublicDisplayItemStatusTO.class);
            }
        });

        return (List<PublicDisplayItemStatusTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetValuationTypes")
    public List<ValuationTypeTO> GetValuationTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(administrativeEJB.getCodeEntityList(
                        ValuationType.class, languageCode), ValuationTypeTO.class);
            }
        });
        return (List<ValuationTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAuthorityTypes")
    public List<AuthorityTO> GetAuthorityTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        Authority.class, languageCode), AuthorityTO.class);
            }
        });

        return (List<AuthorityTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetObjectionStatusTypes")
    public List<ObjectionStatusTO> GetObjectionStatusTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        ObjectionStatus.class, languageCode), ObjectionStatusTO.class);
            }
        });

        return (List<ObjectionStatusTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetNotifyRelationshipTypes")
    public List<NotifyRelationshipTypeTO> GetNotifyRelationshipTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        NotifyRelationshipType.class, languageCode), NotifyRelationshipTypeTO.class);
            }
        });

        return (List<NotifyRelationshipTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetNegotiateStatusTypes")
    public List<NegotiateStatusTO> GetNegotiateStatusTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        NegotiateStatus.class, languageCode), NegotiateStatusTO.class);
            }
        });

        return (List<NegotiateStatusTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetNegotiateTypes")
    public List<NegotiateTypeTO> GetNegotiateTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        NegotiateType.class, languageCode), NegotiateTypeTO.class);
            }
        });

        return (List<NegotiateTypeTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB#getCodeEntityList(java.lang.Class, java.lang.String)
     * AdministrativeEJB.getCodeEntityList}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRequestDisplayGroups")
    public List<RequestDisplayGroupTO> GetRequestDisplayGroups(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(applicationEJB.getCodeEntityList(
                        RequestDisplayGroup.class, languageCode), RequestDisplayGroupTO.class);
            }
        });

        return (List<RequestDisplayGroupTO>) result[0];
    }

    /**
     * Supports saving of all SOLA Reference Data types.
     * <p>
     * Requires the {@linkplain RolesConstants#ADMIN_MANAGE_REFDATA} role.</p>
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     */
    @RolesAllowed(RolesConstants.ADMIN_MANAGE_REFDATA)
    @WebMethod(operationName = "saveReferenceData")
    public AbstractCodeTO saveReferenceData(AbstractCodeTO refDataTO)
            throws SOLAFault, UnhandledFault, SOLAAccessFault, OptimisticLockingFault {

        try {
            try {
                beginTransaction();
                AbstractCodeTO result = null;
                AbstractCodeEntity codeEntity = null;

                if (refDataTO instanceof CommunicationTypeTO) {
                    codeEntity = partyEJB.getCodeEntity(CommunicationType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, CommunicationType.class, codeEntity);
                    partyEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof GenderTypeTO) {
                    codeEntity = partyEJB.getCodeEntity(GenderType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, GenderType.class, codeEntity);
                    partyEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof IdTypeTO) {
                    codeEntity = partyEJB.getCodeEntity(IdType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, IdType.class, codeEntity);
                    partyEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof PartyRoleTypeTO) {
                    codeEntity = partyEJB.getCodeEntity(PartyRoleType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, PartyRoleType.class, codeEntity);
                    partyEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof PartyTypeTO) {
                    codeEntity = partyEJB.getCodeEntity(PartyType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, PartyType.class, codeEntity);
                    partyEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof BaUnitTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(BaUnitType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, BaUnitType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof MortgageTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(MortgageType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, MortgageType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RrrGroupTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(RrrGroupType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RrrGroupType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RrrTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(RrrType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RrrType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof SourceBaUnitRelationTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(SourceBaUnitRelationType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, SourceBaUnitRelationType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ApplicationActionTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(ApplicationActionType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ApplicationActionType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ApplicationStatusTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(ApplicationStatusType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ApplicationStatusType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RequestCategoryTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(RequestCategoryType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RequestCategoryType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RequestTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(RequestType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RequestType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof TypeActionTO) {
                    codeEntity = applicationEJB.getCodeEntity(TypeAction.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, TypeAction.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ServiceActionTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(ServiceActionType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ServiceActionType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ServiceStatusTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(ServiceStatusType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ServiceStatusType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof CadastreObjectTypeTO) {
                    codeEntity = cadastreEJB.getCodeEntity(CadastreObjectType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, CadastreObjectType.class, codeEntity);
                    cadastreEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof SourceTypeTO) {
                    codeEntity = sourceEJB.getCodeEntity(SourceType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, SourceType.class, codeEntity);
                    sourceEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof AvailabilityStatusTO) {
                    codeEntity = sourceEJB.getCodeEntity(AvailabilityStatus.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, AvailabilityStatus.class, codeEntity);
                    sourceEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof PresentationFormTypeTO) {
                    codeEntity = sourceEJB.getCodeEntity(PresentationFormType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, PresentationFormType.class, codeEntity);
                    sourceEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RegistrationStatusTypeTO) {
                    codeEntity = transactionEJB.getCodeEntity(RegistrationStatusType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RegistrationStatusType.class, codeEntity);
                    transactionEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof BrSeverityTypeTO) {
                    codeEntity = systemEJB.getCodeEntity(BrSeverityType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, BrSeverityType.class, codeEntity);
                    systemEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof BrTechnicalTypeTO) {
                    codeEntity = systemEJB.getCodeEntity(BrTechnicalType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, BrTechnicalType.class, codeEntity);
                    systemEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof BrValidationTargetTypeTO) {
                    codeEntity = systemEJB.getCodeEntity(BrValidationTargetType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, BrValidationTargetType.class, codeEntity);
                    systemEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof BaUnitRelTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(BaUnitRelType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, BaUnitRelType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof NotationStatusTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(NotationStatusType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, NotationStatusType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof StateLandStatusTypeTO) {
                    codeEntity = cadastreEJB.getCodeEntity(StateLandStatusType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, StateLandStatusType.class, codeEntity);
                    cadastreEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RrrSubTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(RrrSubType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RrrSubType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ChecklistItemTO) {
                    codeEntity = applicationEJB.getCodeEntity(ChecklistItem.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ChecklistItem.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ChecklistGroupTO) {
                    codeEntity = applicationEJB.getCodeEntity(ChecklistGroup.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ChecklistGroup.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof PublicDisplayItemTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(PublicDisplayItemType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, PublicDisplayItemType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof PublicDisplayItemStatusTO) {
                    codeEntity = applicationEJB.getCodeEntity(PublicDisplayItemStatus.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, PublicDisplayItemStatus.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ValuationTypeTO) {
                    codeEntity = administrativeEJB.getCodeEntity(ValuationType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ValuationType.class, codeEntity);
                    administrativeEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof AuthorityTO) {
                    codeEntity = applicationEJB.getCodeEntity(Authority.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, Authority.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof ObjectionStatusTO) {
                    codeEntity = applicationEJB.getCodeEntity(ObjectionStatus.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, ObjectionStatus.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof NotifyRelationshipTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(NotifyRelationshipType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, NotifyRelationshipType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof NegotiateTypeTO) {
                    codeEntity = applicationEJB.getCodeEntity(NegotiateType.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, NegotiateType.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof NegotiateStatusTO) {
                    codeEntity = applicationEJB.getCodeEntity(NegotiateStatus.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, NegotiateStatus.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                } else if (refDataTO instanceof RequestDisplayGroupTO) {
                    codeEntity = applicationEJB.getCodeEntity(RequestDisplayGroup.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RequestDisplayGroup.class, codeEntity);
                    applicationEJB.saveCodeEntity(codeEntity);
                }

                result = GenericTranslator.toTO(codeEntity, refDataTO.getClass());
                commitTransaction();
                return result;

            } finally {
                rollbackTransaction();
            }
        } catch (Exception t) {
            Exception fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            if (fault.getClass() == SOLAAccessFault.class) {
                throw (SOLAAccessFault) fault;
            }
            if (fault.getClass() == OptimisticLockingFault.class) {
                throw (OptimisticLockingFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }
}
