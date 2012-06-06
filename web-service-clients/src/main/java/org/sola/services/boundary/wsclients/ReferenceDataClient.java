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
package org.sola.services.boundary.wsclients;

import java.util.List;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.AbstractCodeTO;
import org.sola.webservices.transferobjects.referencedata.ApplicationActionTypeTO;
import org.sola.webservices.transferobjects.referencedata.ApplicationStatusTypeTO;
import org.sola.webservices.transferobjects.referencedata.BaUnitRelTypeTO;
import org.sola.webservices.transferobjects.referencedata.BaUnitTypeTO;
import org.sola.webservices.transferobjects.referencedata.BrSeverityTypeTO;
import org.sola.webservices.transferobjects.referencedata.BrTechnicalTypeTO;
import org.sola.webservices.transferobjects.referencedata.BrValidationTargetTypeTO;
import org.sola.webservices.transferobjects.referencedata.CadastreObjectTypeTO;
import org.sola.webservices.transferobjects.referencedata.ChangeStatusTypeTO;
import org.sola.webservices.transferobjects.referencedata.CommunicationTypeTO;
import org.sola.webservices.transferobjects.referencedata.GenderTypeTO;
import org.sola.webservices.transferobjects.referencedata.IdTypeTO;
import org.sola.webservices.transferobjects.referencedata.MortgageTypeTO;
import org.sola.webservices.transferobjects.referencedata.PartyTypeTO;
import org.sola.webservices.transferobjects.referencedata.PartyRoleTypeTO;
import org.sola.webservices.transferobjects.referencedata.RegistrationStatusTypeTO;
import org.sola.webservices.transferobjects.referencedata.RequestCategoryTypeTO;
import org.sola.webservices.transferobjects.referencedata.RequestTypeTO;
import org.sola.webservices.transferobjects.referencedata.ServiceActionTypeTO;
import org.sola.webservices.transferobjects.referencedata.ServiceStatusTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceTypeTO;
import org.sola.webservices.transferobjects.referencedata.RrrGroupTypeTO;
import org.sola.webservices.transferobjects.referencedata.TypeActionTO;
import org.sola.webservices.transferobjects.referencedata.RrrTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceBaUnitRelationTypeTO;

/**
 * Interface for the Reference Data Service. Implemented by {@linkplain ReferenceDataClientImpl}. To
 * obtain a reference to the Case Management Service, use {@linkplain WSManager#getReferenceDataService()}
 *
 * @see ReferenceDataClientImpl
 * @see WSManager#getReferenceDataService()
 */
public interface ReferenceDataClient extends AbstractWSClient {

    /**
     * ReferenceData. - Service name prefix for the Reference Data Web Service
     */
    public static final String SERVICE_NAME = "ReferenceData.";
    /**
     * ReferenceData.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * ReferenceData.getSourceTypes - Identifier for the getSourceTypes method
     */
    public static final String GET_SOURCE_TYPES = SERVICE_NAME + "getSourceTypes";
    /**
     * ReferenceData.getRequestCategoryTypes - Identifier for the getRequestCategoryTypes method
     */
    public static final String GET_REQUEST_CATEGORY_TYPES = SERVICE_NAME + "getRequestCategoryTypes";
    /**
     * ReferenceData.getRequestTypes - Identifier for the getRequestTypes method
     */
    public static final String GET_REQUEST_TYPES = SERVICE_NAME + "getRequestTypes";
    /**
     * ReferenceData.getCommunicationTypes - Identifier for the getCommunicationTypes method
     */
    public static final String GET_COMMUNICATION_TYPES = SERVICE_NAME + "getCommunicationTypes";
    /**
     * ReferenceData.getGenderTypes - Identifier for the getGenderTypes method
     */
    public static final String GET_GENDER_TYPES = SERVICE_NAME + "getGenderTypes";
    /**
     * ReferenceData.getApplicationStatusTypes - Identifier for the getApplicationStatusTypes method
     */
    public static final String GET_APPLICATION_STATUS_TYPES = SERVICE_NAME + "getApplicationStatusTypes";
    /**
     * ReferenceData.getApplicationActionTypes - Identifier for the getApplicationActionTypes method
     */
    public static final String GET_APPLICATION_ACTION_TYPES = SERVICE_NAME + "getApplicationActionTypes";
    /**
     * ReferenceData.getServiceStatusTypes - Identifier for the getServiceStatusTypes method
     */
    public static final String GET_SERVICE_STATUS_TYPES = SERVICE_NAME + "getServiceStatusTypes";
    /**
     * ReferenceData.getServiceActionTypes - Identifier for the getServiceActionTypes method
     */
    public static final String GET_SERVICE_ACTION_TYPES = SERVICE_NAME + "getServiceActionTypes";
    /**
     * ReferenceData.getPartyTypes - Identifier for the getPartyTypes method
     */
    public static final String GET_PARTY_TYPES = SERVICE_NAME + "getPartyTypes";
    /**
     * ReferenceData.getPartyRoles - Identifier for the getPartyRoles method
     */
    public static final String GET_PARTY_ROLES = SERVICE_NAME + "getPartyRoles";
    /**
     * ReferenceData.getIdTypes - Identifier for the getIdTypes method
     */
    public static final String GET_ID_TYPES = SERVICE_NAME + "getIdTypes";
    /**
     * ReferenceData.getChangeStatusTypes - Identifier for the getChangeStatusTypes method
     */
    public static final String GET_CHANGE_STATUS_TYPES = SERVICE_NAME + "getChangeStatusTypes";
    /**
     * ReferenceData.getBaUnitTypes - Identifier for the getBaUnitTypes method
     */
    public static final String GET_BA_UNIT_TYPES = SERVICE_NAME + "getBaUnitTypes";
    /**
     * ReferenceData.getMortgageTypes - Identifier for the getMortgageTypes method
     */
    public static final String GET_MORTGAGE_TYPES = SERVICE_NAME + "getMortgageTypes";
    /**
     * ReferenceData.getRrrGroupTypes - Identifier for the getRrrGroupTypes method
     */
    public static final String GET_RRR_GROUP_TYPES = SERVICE_NAME + "getRrrGroupTypes";
    /**
     * ReferenceData.getRrrTypes - Identifier for the getRrrTypes method
     */
    public static final String GET_RRR_TYPES = SERVICE_NAME + "getRrrTypes";
    /**
     * ReferenceData.getTypeActions - Identifier for the getTypeActions method
     */
    public static final String GET_TYPE_ACTIONS = SERVICE_NAME + "getTypeActions";
    /**
     * ReferenceData.getSourceBaUnitRelationTypes - Identifier for the getSourceBaUnitRelationTypes method
     */
    public static final String GET_SOURCE_BA_UNIT_RELATION_TYPES = SERVICE_NAME + "getSourceBaUnitRelationTypes";
    /**
     * ReferenceData.getRegistrationStatusTypes - Identifier for the getRegistrationStatusTypes method
     */
    public static final String GET_REGISTRATION_STATUS_TYPES = SERVICE_NAME + "getRegistrationStatusTypes";
    /**
     * ReferenceData.getCadastreObjectTypes - Identifier for the getCadastreObjectTypes method
     */
    public static final String GET_CADASTRE_OBJECT_TYPES = SERVICE_NAME + "getCadastreObjectTypes";
    /**
     * ReferenceData.saveReferenceData - Identifier for the saveReferenceData method
     */
    public static final String SAVE_REFERENCE_DATA = SERVICE_NAME + "saveReferenceData";
    /**
     * ReferenceData.getBrTechnicalTypes - Identifier for the getBrTechnicalTypes method
     */
    public static final String GET_BR_TECHNICAL_TYPES = SERVICE_NAME + "getBrTechnicalTypes";
    /**
     * ReferenceData.getBrValidationTargetTypes - Identifier for the getBrValidationTargetTypes method
     */
    public static final String GET_BR_VALIDATION_TARGET_TYPES = SERVICE_NAME + "getBrValidationTargetTypes";
    /**
     * ReferenceData.getBrSeverityTypes - Identifier for the getBrSeverityTypes method
     */
    public static final String GET_BR_SEVERITY_TYPES = SERVICE_NAME + "getBrSeverityTypes";
    /**
     * ReferenceData.getBaUnitRelTypes - Identifier for the getBaUnitRelTypes method
     */
    public static final String GET_BA_UNIT_REL_TYPES = SERVICE_NAME + "getBaUnitRelTypes";

    List<SourceTypeTO> getSourceTypes() throws WebServiceClientException;

    List<SourceTypeTO> getSourceTypes(String lang) throws WebServiceClientException;

    List<RequestCategoryTypeTO> getRequestCategoryTypes() throws WebServiceClientException;

    List<RequestCategoryTypeTO> getRequestCategoryTypes(String lang) throws WebServiceClientException;

    List<RequestTypeTO> getRequestTypes() throws WebServiceClientException;

    List<RequestTypeTO> getRequestTypes(String lang) throws WebServiceClientException;

    List<CommunicationTypeTO> getCommunicationTypes() throws WebServiceClientException;

    List<CommunicationTypeTO> getCommunicationTypes(String lang) throws WebServiceClientException;

    List<GenderTypeTO> getGenderTypes() throws WebServiceClientException;

    List<GenderTypeTO> getGenderTypes(String lang) throws WebServiceClientException;

    List<ApplicationStatusTypeTO> getApplicationStatusTypes() throws WebServiceClientException;

    List<ApplicationStatusTypeTO> getApplicationStatusTypes(String lang) throws WebServiceClientException;

    List<ApplicationActionTypeTO> getApplicationActionTypes() throws WebServiceClientException;

    List<ApplicationActionTypeTO> getApplicationActionTypes(String lang) throws WebServiceClientException;

    List<ServiceStatusTypeTO> getServiceStatusTypes() throws WebServiceClientException;

    List<ServiceStatusTypeTO> getServiceStatusTypes(String lang) throws WebServiceClientException;

    List<ServiceActionTypeTO> getServiceActionTypes() throws WebServiceClientException;

    List<ServiceActionTypeTO> getServiceActionTypes(String lang) throws WebServiceClientException;

    List<PartyTypeTO> getPartyTypes() throws WebServiceClientException;

    List<PartyTypeTO> getPartyTypes(String lang) throws WebServiceClientException;

    List<PartyRoleTypeTO> getPartyRoles() throws WebServiceClientException;

    List<PartyRoleTypeTO> getPartyRoles(String lang) throws WebServiceClientException;

    List<IdTypeTO> getIdTypes() throws WebServiceClientException;

    List<IdTypeTO> getIdTypes(String lang) throws WebServiceClientException;

    List<ChangeStatusTypeTO> getChangeStatusTypes() throws WebServiceClientException;

    List<ChangeStatusTypeTO> getChangeStatusTypes(String lang) throws WebServiceClientException;

    List<BaUnitTypeTO> getBaUnitTypes() throws WebServiceClientException;

    List<BaUnitTypeTO> getBaUnitTypes(String lang) throws WebServiceClientException;

    List<MortgageTypeTO> getMortgageTypes() throws WebServiceClientException;

    List<MortgageTypeTO> getMortgageTypes(String lang) throws WebServiceClientException;

    List<RrrGroupTypeTO> getRrrGroupTypes() throws WebServiceClientException;

    List<RrrGroupTypeTO> getRrrGroupTypes(String lang) throws WebServiceClientException;

    List<RrrTypeTO> getRrrTypes() throws WebServiceClientException;

    List<RrrTypeTO> getRrrTypes(String lang) throws WebServiceClientException;

    List<TypeActionTO> getTypeActions() throws WebServiceClientException;

    List<TypeActionTO> getTypeActions(String lang) throws WebServiceClientException;

    List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes()
            throws WebServiceClientException;

    List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes(String lang)
            throws WebServiceClientException;

    List<RegistrationStatusTypeTO> getRegistrationStatusTypes() throws WebServiceClientException;

    List<RegistrationStatusTypeTO> getRegistrationStatusTypes(String lang) throws WebServiceClientException;

    List<CadastreObjectTypeTO> getCadastreObjectTypes() throws WebServiceClientException;

    List<CadastreObjectTypeTO> getCadastreObjectTypes(String lang) throws WebServiceClientException;

    AbstractCodeTO saveReferenceData(AbstractCodeTO refDataTO) throws WebServiceClientException;

    List<BrTechnicalTypeTO> getBrTechnicalTypes() throws WebServiceClientException;

    List<BrTechnicalTypeTO> getBrTechnicalTypes(String lang) throws WebServiceClientException;

    List<BrValidationTargetTypeTO> getBrValidationTargetTypes() throws WebServiceClientException;

    List<BrValidationTargetTypeTO> getBrValidationTargetTypes(String lang) throws WebServiceClientException;

    List<BrSeverityTypeTO> getBrSeverityTypes() throws WebServiceClientException;

    List<BrSeverityTypeTO> getBrSeverityTypes(String lang) throws WebServiceClientException;

    List<BaUnitRelTypeTO> getBaUnitRelTypes(String lang) throws WebServiceClientException;

    List<BaUnitRelTypeTO> getBaUnitRelTypes() throws WebServiceClientException;
}
