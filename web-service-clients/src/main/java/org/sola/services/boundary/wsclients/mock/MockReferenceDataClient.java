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
package org.sola.services.boundary.wsclients.mock;

import java.util.List;
import org.sola.services.boundary.wsclients.ReferenceDataClient;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.AbstractCodeTO;
import org.sola.webservices.transferobjects.referencedata.ApplicationActionTypeTO;
import org.sola.webservices.transferobjects.referencedata.ApplicationStatusTypeTO;
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
import org.sola.webservices.transferobjects.referencedata.RrrGroupTypeTO;
import org.sola.webservices.transferobjects.referencedata.RrrTypeActionTO;
import org.sola.webservices.transferobjects.referencedata.RrrTypeTO;
import org.sola.webservices.transferobjects.referencedata.ServiceActionTypeTO;
import org.sola.webservices.transferobjects.referencedata.ServiceStatusTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceBaUnitRelationTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceTypeTO;

/**
 * Provides a mock implementation for the 
 * {@linkplain org.sola.services.boundary.wsclients.ReferenceDataClient} interface. Uses the 
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method. 
 * <p>Each method mocked by this class has a public constant defined that can be used to reference 
 * a mock response object from the {@linkplain MockServiceManager}. To set a response object
 * for a web method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from this class.</p>
 * @author amcdowell
 * @see MockResponse
 */
public class MockReferenceDataClient extends AbstractMockWSClient implements ReferenceDataClient {

    private static final String SERVICE_NAME = "ReferenceData.";
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    public static final String GET_COMMUNICATION_TYPES = SERVICE_NAME + "getCommunicationTypes";
   public static final String GET_GENDER_TYPES = SERVICE_NAME + "getGenderTypes";
    public static final String GET_REQUEST_TYPES = SERVICE_NAME + "getRequestTypes";
    public static final String GET_SOURCE_TYPES = SERVICE_NAME + "getSourceTypes";
    public static final String GET_APPLICATION_STATUS_TYPES = SERVICE_NAME 
            + "getApplicationStatusTypes";
    public static final String GET_APPLICATION_ACTION_TYPES = SERVICE_NAME 
            + "getApplicationActionTypes";
    public static final String GET_REQUEST_TYPE_SOURCE_TYPES =
            SERVICE_NAME + "getRequestTypeSourceTypes";
    public static final String GET_SERVICE_STATUS_TYPES = SERVICE_NAME 
            + "getServiceStatusTypes";
    public static final String GET_SERVICE_ACTION_TYPES = SERVICE_NAME 
            + "getServiceActionTypes";
    public static final String GET_PARTY_TYPES = SERVICE_NAME 
            + "getPartyTypes";
    public static final String GET_PARTY_ROLES = SERVICE_NAME 
            + "getPartyRoles";
    public static final String GET_ID_TYPES = SERVICE_NAME 
            + "getIdTypes";
    public static final String GET_BA_UNIT_TYPES = SERVICE_NAME 
            + "getBaUnitTypes";
    public static final String GET_CHANGE_STATUS_TYPES = SERVICE_NAME 
            + "getChangeStatusTypes";
    public static final String GET_MORTGAGE_TYPES = SERVICE_NAME 
            + "getMortgageTypes";
    public static final String GET_RRR_GROUP_TYPES = SERVICE_NAME 
            + "getRRRGroupTypes";
    public static final String GET_RRR_TYPES = SERVICE_NAME 
            + "GetRRRTypes";
    public static final String GET_SOURCE_BA_UNIT_RELATION_TYPES = SERVICE_NAME 
            + "GetSourceBaUnitRelationTypes";
    public static final String GET_REGISTRATION_STATUS_TYPES = SERVICE_NAME 
            + "GetRegistrationStatusTypes";
    public static final String GET_CADASTRE_OBJECT_TYPES = SERVICE_NAME 
            + "GetCadastreObjectTypes";
    public static final String GET_RRR_TYPE_ACTIONS = SERVICE_NAME 
            + "GetRrrTypeActions";
    
    public MockReferenceDataClient(String url) {
        super(url, null);
    }

    /** @return default = true */
    @Override
    public boolean checkConnection() throws WebServiceClientException {
        return getManager().getResponse(CHECK_CONNECTION, Boolean.class, true);
    }

    /** @return default = MockTOFactory.createCommunicationTypes()*/
    @Override
    public List<CommunicationTypeTO> getCommunicationTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_COMMUNICATION_TYPES, List.class,
                MockTOFactory.createCommunicationTypes());
    }
    
    /** @return default = MockTOFactory.createCommunicationTypes()*/
    @Override
    public List<CommunicationTypeTO> getCommunicationTypes(String lang) throws WebServiceClientException {
        return getManager().getResponse(GET_COMMUNICATION_TYPES, List.class,
                MockTOFactory.createCommunicationTypes());
    }
    
/** @return default = MockTOFactory.createGenderTypes()*/
    @Override
    public List<GenderTypeTO> getGenderTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_GENDER_TYPES, List.class,
                MockTOFactory.createGenderTypes());
    }
    
    /** @return default = MockTOFactory.createRequestTypes()*/
    @Override
    public List<RequestTypeTO> getRequestTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_REQUEST_TYPES, List.class,
                MockTOFactory.createRequestTypes());
    }

    /** @return default = MockTOFactory.createSourceTypes()*/
    @Override
    public List<SourceTypeTO> getSourceTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_SOURCE_TYPES, List.class,
                MockTOFactory.createSourceTypes());
    }

    /** @return default = MockTOFactory.getApplicationStatusTypes()*/
    @Override
    public List<ApplicationStatusTypeTO> getApplicationStatusTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_APPLICATION_STATUS_TYPES, List.class,
                MockTOFactory.createApplicationStatusTypes());
    }

    @Override
    public List<ApplicationActionTypeTO> getApplicationActionTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_APPLICATION_ACTION_TYPES, List.class,
                MockTOFactory.createApplicationActionTypes());
    }
    
    /** @return default = MockTOFactory.getServiceStatusTypes()*/
    @Override
    public List<ServiceStatusTypeTO> getServiceStatusTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_SERVICE_STATUS_TYPES, List.class,
                MockTOFactory.createServiceStatusTypes());
    }

    /** @return default = MockTOFactory.getServiceActionTypes()*/
    @Override
    public List<ServiceActionTypeTO> getServiceActionTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_SERVICE_ACTION_TYPES, List.class,
                MockTOFactory.createServiceStatusTypes());
    }

    @Override
    public List<PartyTypeTO> getPartyTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_PARTY_TYPES, List.class,
                MockTOFactory.createPartyTypes());
    }
    
    @Override
    public List<PartyRoleTypeTO> getPartyRoles() throws WebServiceClientException {
        return getManager().getResponse(GET_PARTY_ROLES, List.class,
                MockTOFactory.createPartyRoles());
    }

    @Override
    public List<IdTypeTO> getIdTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_ID_TYPES, List.class,
                MockTOFactory.createIdTypes());
    }

    @Override
    public List<ChangeStatusTypeTO> getChangeStatusTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_CHANGE_STATUS_TYPES, List.class,
                MockTOFactory.createChangeStatusTypes());
    }

    @Override
    public List<BaUnitTypeTO> getBaUnitTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_BA_UNIT_TYPES, List.class,
                MockTOFactory.createBaUnitTypes());
    }
    
    @Override
    public List<BaUnitTypeTO> getBaUnitTypes(String lang) throws WebServiceClientException {
        return getManager().getResponse(GET_BA_UNIT_TYPES, List.class,
                MockTOFactory.createBaUnitTypes());
    }

    @Override
    public List<MortgageTypeTO> getMortgageTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_MORTGAGE_TYPES, List.class,
                MockTOFactory.createMortgageTypes());
    }

    @Override
    public List<RrrGroupTypeTO> getRrrGroupTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_RRR_GROUP_TYPES, List.class,
                MockTOFactory.createRRRGroupTypes());
    }

    @Override
    public List<RrrTypeTO> getRrrTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_RRR_TYPES, List.class,
                MockTOFactory.createRRRTypes());
    }

    @Override
    public List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_SOURCE_BA_UNIT_RELATION_TYPES, List.class,
                MockTOFactory.createSourceBaUnitRelationTypes());
    }

    @Override
    public List<RegistrationStatusTypeTO> getRegistrationStatusTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_REGISTRATION_STATUS_TYPES, List.class,
                MockTOFactory.createRegistrationStatusTypes());
    }

    @Override
    public List<CadastreObjectTypeTO> getCadastreObjectTypes() throws WebServiceClientException {
        return getManager().getResponse(GET_CADASTRE_OBJECT_TYPES, List.class,
                MockTOFactory.createRegistrationStatusTypes());
    }

    @Override
    public List<RrrTypeActionTO> getRrrTypeActions() throws WebServiceClientException {
        return getManager().getResponse(GET_RRR_TYPE_ACTIONS, List.class,
                MockTOFactory.createRrrTypeActions());
    }

    @Override
    public AbstractCodeTO saveReferenceData(AbstractCodeTO refDataTO) {
        return refDataTO;
    }

    @Override
    public List<SourceTypeTO> getSourceTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RequestTypeTO> getRequestTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<GenderTypeTO> getGenderTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ApplicationStatusTypeTO> getApplicationStatusTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ApplicationActionTypeTO> getApplicationActionTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ServiceStatusTypeTO> getServiceStatusTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ServiceActionTypeTO> getServiceActionTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PartyTypeTO> getPartyTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PartyRoleTypeTO> getPartyRoles(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<IdTypeTO> getIdTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<ChangeStatusTypeTO> getChangeStatusTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<MortgageTypeTO> getMortgageTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RrrGroupTypeTO> getRrrGroupTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RrrTypeTO> getRrrTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RrrTypeActionTO> getRrrTypeActions(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RegistrationStatusTypeTO> getRegistrationStatusTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CadastreObjectTypeTO> getCadastreObjectTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RequestCategoryTypeTO> getRequestCategoryTypes() throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<RequestCategoryTypeTO> getRequestCategoryTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BrTechnicalTypeTO> getBrTechnicalTypes() throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BrTechnicalTypeTO> getBrTechnicalTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BrValidationTargetTypeTO> getBrValidationTargetTypes() throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BrValidationTargetTypeTO> getBrValidationTargetTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BrSeverityTypeTO> getBrSeverityTypes() throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BrSeverityTypeTO> getBrSeverityTypes(String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
