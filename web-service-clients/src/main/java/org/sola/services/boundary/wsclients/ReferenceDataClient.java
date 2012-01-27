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
import org.sola.webservices.transferobjects.referencedata.RrrTypeActionTO;
import org.sola.webservices.transferobjects.referencedata.RrrTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceBaUnitRelationTypeTO;

/**
 * Interface for the ReferenceData Service. Implemented by {@linkplain ReferenceDataClientImpl}
 * and {@linkplain mock.MockReferenceDataClient}
 * @author amcdowell
 */
public interface ReferenceDataClient extends AbstractWSClient {
    
    boolean checkConnection() throws WebServiceClientException;

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
    
    List<RrrTypeActionTO> getRrrTypeActions() throws WebServiceClientException;
    
    List<RrrTypeActionTO> getRrrTypeActions(String lang) throws WebServiceClientException;
    
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
