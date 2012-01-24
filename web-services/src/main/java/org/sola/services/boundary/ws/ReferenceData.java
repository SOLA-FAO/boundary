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

import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import org.sola.common.RolesConstants;
import org.sola.services.boundary.transferobjects.referencedata.ApplicationActionTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.ApplicationStatusTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.AvailabilityStatusTO;
import org.sola.services.boundary.transferobjects.referencedata.BaUnitRelTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.BaUnitTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.BrSeverityTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.BrTechnicalTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.BrValidationTargetTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.CadastreObjectTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.ChangeStatusTypeTO;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.ejb.application.businesslogic.ApplicationEJBLocal;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.party.businesslogic.PartyEJBLocal;
import org.sola.services.boundary.transferobjects.referencedata.CommunicationTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.GenderTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.IdTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.MortgageTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.PartyTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.PartyRoleTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.PresentationFormTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.RegistrationStatusTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.RequestCategoryTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.RequestTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.ServiceActionTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.ServiceStatusTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.SourceBaUnitRelationTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.SourceTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.RrrTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.RrrGroupTypeTO;
import org.sola.services.boundary.transferobjects.referencedata.RrrTypeActionTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.AbstractCodeTO;
import org.sola.services.common.repository.entities.AbstractCodeEntity;
import org.sola.services.ejb.administrative.businesslogic.AdministrativeEJBLocal;
import org.sola.services.ejb.administrative.repository.entities.BaUnitRelType;
import org.sola.services.ejb.administrative.repository.entities.BaUnitType;
import org.sola.services.ejb.administrative.repository.entities.MortgageType;
import org.sola.services.ejb.administrative.repository.entities.RrrGroupType;
import org.sola.services.ejb.administrative.repository.entities.RrrType;
import org.sola.services.ejb.administrative.repository.entities.SourceBaUnitRelationType;
import org.sola.services.ejb.application.repository.entities.ApplicationActionType;
import org.sola.services.ejb.application.repository.entities.ApplicationStatusType;
import org.sola.services.ejb.application.repository.entities.RequestCategoryType;
import org.sola.services.ejb.application.repository.entities.RequestType;
import org.sola.services.ejb.application.repository.entities.RrrTypeAction;
import org.sola.services.ejb.application.repository.entities.ServiceActionType;
import org.sola.services.ejb.application.repository.entities.ServiceStatusType;
import org.sola.services.ejb.cadastre.businesslogic.CadastreEJBLocal;
import org.sola.services.ejb.cadastre.repository.entities.CadastreObjectType;
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

    /** Dummy method to check the web service instance is working */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    @WebMethod(operationName = "GetCommunicationTypes")
    public List<CommunicationTypeTO> GetCommunicationTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<CommunicationTypeTO> result = GenericTranslator.toTOList(
                        partyEJB.getCommunicationTypes(languageCode),
                        CommunicationTypeTO.class);
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

    @WebMethod(operationName = "GetGenderTypes")
    public List<GenderTypeTO> GetGenderTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<GenderTypeTO> result = GenericTranslator.toTOList(
                        partyEJB.getGenderTypes(languageCode),
                        GenderTypeTO.class);
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

    @WebMethod(operationName = "GetAvailabilityStatusList")
    public List<AvailabilityStatusTO> GetAvailabilityStatusList(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<AvailabilityStatusTO> result = GenericTranslator.toTOList(
                        sourceEJB.getAvailabilityStatusList(languageCode),
                        AvailabilityStatusTO.class);
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

    @WebMethod(operationName = "GetPresentationFormTypes")
    public List<PresentationFormTypeTO> GetPresentationFormTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<PresentationFormTypeTO> result = GenericTranslator.toTOList(
                        sourceEJB.getPresentationFormTypes(languageCode),
                        PresentationFormTypeTO.class);
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

    @WebMethod(operationName = "GetRequestTypes")
    public List<RequestTypeTO> GetRequestTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<RequestTypeTO> result = GenericTranslator.toTOList(
                        applicationEJB.getRequestTypes(languageCode), RequestTypeTO.class);
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

    @WebMethod(operationName = "GetRequestCategoryTypes")
    public List<RequestCategoryTypeTO> GetRequestCategoryTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<RequestCategoryTypeTO> result = GenericTranslator.toTOList(
                        applicationEJB.getRequestCategoryTypes(languageCode), RequestCategoryTypeTO.class);
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

    @WebMethod(operationName = "GetSourceTypes")
    public List<SourceTypeTO> GetSourceTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<SourceTypeTO> result = GenericTranslator.toTOList(
                        sourceEJB.getSourceTypes(languageCode), SourceTypeTO.class);
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

    @WebMethod(operationName = "GetApplicationStatusTypes")
    public List<ApplicationStatusTypeTO> GetApplicationStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<ApplicationStatusTypeTO> result = GenericTranslator.toTOList(
                        applicationEJB.getApplicationStatusTypes(languageCode),
                        ApplicationStatusTypeTO.class);
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

    @WebMethod(operationName = "GetApplicationActionTypes")
    public List<ApplicationActionTypeTO> GetApplicationActionTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<ApplicationActionTypeTO> result = GenericTranslator.toTOList(
                        applicationEJB.getApplicationActionTypes(languageCode),
                        ApplicationActionTypeTO.class);
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

    @WebMethod(operationName = "GetServiceStatusTypes")
    public List<ServiceStatusTypeTO> GetServiceStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<ServiceStatusTypeTO> result = GenericTranslator.toTOList(
                        applicationEJB.getServiceStatusTypes(languageCode),
                        ServiceStatusTypeTO.class);
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

    @WebMethod(operationName = "GetServiceActionTypes")
    public List<ServiceActionTypeTO> GetServiceActionTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<ServiceActionTypeTO> result = GenericTranslator.toTOList(
                        applicationEJB.getServiceActionTypes(languageCode),
                        ServiceActionTypeTO.class);
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

    @WebMethod(operationName = "GetPartyTypes")
    public List<PartyTypeTO> GetPartyTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<PartyTypeTO> result = GenericTranslator.toTOList(
                        partyEJB.getPartyTypes(languageCode), PartyTypeTO.class);
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

    @WebMethod(operationName = "GetPartyRoles")
    public List<PartyRoleTypeTO> GetPartyRoles(String language_code) throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<PartyRoleTypeTO> result = GenericTranslator.toTOList(
                        partyEJB.getPartyRoles(language_code), PartyRoleTypeTO.class);
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

    @WebMethod(operationName = "GetIdTypes")
    public List<IdTypeTO> GetIdTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<IdTypeTO> result = GenericTranslator.toTOList(
                        partyEJB.getIdTypes(languageCode), IdTypeTO.class);
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

    @WebMethod(operationName = "GetBaUnitTypes")
    public List<BaUnitTypeTO> GetBaUnitTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<BaUnitTypeTO> result = GenericTranslator.toTOList(
                        administrativeEJB.getBaUnitTypes(languageCode), BaUnitTypeTO.class);
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

    @WebMethod(operationName = "GetChangeStatuTypes")
    public List<ChangeStatusTypeTO> GetChangeStatuTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<ChangeStatusTypeTO> result = GenericTranslator.toTOList(
                        administrativeEJB.getChangeStatusTypes(languageCode),
                        ChangeStatusTypeTO.class);
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

    @WebMethod(operationName = "GetMortgageTypes")
    public List<MortgageTypeTO> GetMortgageTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<MortgageTypeTO> result = GenericTranslator.toTOList(
                        administrativeEJB.getMortgageTypes(languageCode), MortgageTypeTO.class);
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

    @WebMethod(operationName = "GetRRRGroupTypes")
    public List<RrrGroupTypeTO> GetRRRGroupTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<RrrGroupTypeTO> result = GenericTranslator.toTOList(
                        administrativeEJB.getRRRGroupTypes(languageCode), RrrGroupTypeTO.class);
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

    @WebMethod(operationName = "GetRRRTypes")
    public List<RrrTypeTO> GetRRRTypes(String languageCode) throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<RrrTypeTO> result = GenericTranslator.toTOList(
                        administrativeEJB.getRRRTypes(languageCode), RrrTypeTO.class);
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

    @WebMethod(operationName = "GetSourceBaUnitRelationTypes")
    public List<SourceBaUnitRelationTypeTO> GetSourceBaUnitRelationTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            // initialize();
            try {
                beginTransaction();
                List<SourceBaUnitRelationTypeTO> result = GenericTranslator.toTOList(
                        administrativeEJB.getSourceBaUnitRelationTypes(languageCode),
                        SourceBaUnitRelationTypeTO.class);
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

    @WebMethod(operationName = "GetRegistrationStatusTypes")
    public List<RegistrationStatusTypeTO> GetRegistrationStatusTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                List<RegistrationStatusTypeTO> result = GenericTranslator.toTOList(
                        transactionEJB.getRegistrationStatusTypes(languageCode),
                        RegistrationStatusTypeTO.class);
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

    @WebMethod(operationName = "GetCadastreObjectTypes")
    public List<CadastreObjectTypeTO> GetCadastreObjectTypes(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //  initialize();
            try {
                beginTransaction();
                List<CadastreObjectTypeTO> result = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectTypes(languageCode),
                        CadastreObjectTypeTO.class);
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

    @WebMethod(operationName = "GetRrrTypeActions")
    public List<RrrTypeActionTO> GetRrrTypeActions(String languageCode)
            throws SOLAFault, UnhandledFault {
        try {
            //  initialize();
            try {
                beginTransaction();
                List<RrrTypeActionTO> result = GenericTranslator.toTOList(
                        applicationEJB.getRrrTypeActions(languageCode),
                        RrrTypeActionTO.class);
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

    @WebMethod(operationName = "GetBrTechnicalTypes")
    public List<BrTechnicalTypeTO> GetBrTechnicalTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(systemEJB.getCodeEntityList(
                        BrTechnicalType.class, languageCode), BrTechnicalTypeTO.class);
            }
        });

        return (List<BrTechnicalTypeTO>) result[0];
    }

    @WebMethod(operationName = "GetBrValidationTargetTypes")
    public List<BrValidationTargetTypeTO> GetBrValidationTargetTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(systemEJB.getCodeEntityList(
                        BrValidationTargetType.class, languageCode), BrValidationTargetTypeTO.class);
            }
        });

        return (List<BrValidationTargetTypeTO>) result[0];
    }
    
    @WebMethod(operationName = "GetBrSeverityTypes")
    public List<BrSeverityTypeTO> GetBrSeverityTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(systemEJB.getCodeEntityList(
                        BrSeverityType.class, languageCode), BrSeverityTypeTO.class);
            }
        });

        return (List<BrSeverityTypeTO>) result[0];
    }
    
    @WebMethod(operationName = "getBaUnitRelTypes")
    public List<BaUnitRelTypeTO> getBaUnitRelTypes(@WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault {
        final Object[] params = {languageCode};
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                String languageCode = params[0] == null ? null : params[0].toString();
                result[0] = GenericTranslator.toTOList(administrativeEJB.getCodeEntityList(
                        BaUnitRelType.class, languageCode), BaUnitRelTypeTO.class);
            }
        });

        return (List<BaUnitRelTypeTO>) result[0];
    }
    
    @RolesAllowed(RolesConstants.ADMIN_MANAGE_REFDATA)
    @WebMethod(operationName = "saveReferenceData")
    public AbstractCodeTO saveReferenceData(AbstractCodeTO refDataTO)
            throws SOLAFault, UnhandledFault {
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
                } else if (refDataTO instanceof RrrTypeActionTO) {
                    codeEntity = applicationEJB.getCodeEntity(RrrTypeAction.class, refDataTO.getCode());
                    codeEntity = GenericTranslator.fromTO(refDataTO, RrrTypeAction.class, codeEntity);
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
                }

                result = GenericTranslator.toTO(codeEntity, refDataTO.getClass());
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
}
