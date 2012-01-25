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
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;
import org.sola.webservices.referencedata.SOLAFault;
import org.sola.webservices.referencedata.UnhandledFault;
import org.sola.webservices.referencedata.ReferenceData;
import org.sola.webservices.referencedata.ReferencedataService;
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
import org.sola.webservices.transferobjects.referencedata.RrrGroupTypeTO;
import org.sola.webservices.transferobjects.referencedata.RrrTypeActionTO;
import org.sola.webservices.transferobjects.referencedata.RrrTypeTO;
import org.sola.webservices.transferobjects.referencedata.ServiceActionTypeTO;
import org.sola.webservices.transferobjects.referencedata.ServiceStatusTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceBaUnitRelationTypeTO;
import org.sola.webservices.transferobjects.referencedata.SourceTypeTO;

/**
 * Implementation class for the {@linkplain ReferenceDataClient} interface. 
 * @author amcdowell
 */
public class ReferenceDataClientImpl extends AbstractWSClientImpl implements ReferenceDataClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/referencedata";
    private static final String LOCAL_PART = "referencedata-service";
    private static final String SERVICE_NAME = "ReferenceData.";

    public ReferenceDataClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    private ReferenceData getPort() {
        return getPort(ReferenceData.class, ReferencedataService.class);
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
    public List<CommunicationTypeTO> getCommunicationTypes(String lang) throws WebServiceClientException {
        try {
            List<CommunicationTypeTO> result = getPort().getCommunicationTypes(lang);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCommunicationTypes", t);
        }
    }

    @Override
    public List<CommunicationTypeTO> getCommunicationTypes() throws WebServiceClientException {
        return getCommunicationTypes(this.getLanguageCode());
    }

    @Override
    public List<GenderTypeTO> getGenderTypes() throws WebServiceClientException {
        return getGenderTypes(this.getLanguageCode());
    }

    @Override
    public List<GenderTypeTO> getGenderTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getGenderTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getGenderTypes", t);
        }
    }

    @Override
    public List<RequestTypeTO> getRequestTypes() throws WebServiceClientException {
        return getRequestTypes(this.getLanguageCode());
    }

    @Override
    public List<RequestTypeTO> getRequestTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getRequestTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRequestTypes", t);
        }
    }

    @Override
    public List<SourceTypeTO> getSourceTypes() throws WebServiceClientException {
        return getSourceTypes(this.getLanguageCode());
    }

    @Override
    public List<SourceTypeTO> getSourceTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getSourceTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getSourceTypes", t);
        }
    }

    @Override
    public List<ApplicationStatusTypeTO> getApplicationStatusTypes() throws WebServiceClientException {
        return getApplicationStatusTypes(getLanguageCode());
    }
    
    @Override
    public List<ApplicationStatusTypeTO> getApplicationStatusTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getApplicationStatusTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getApplicationStatusTypes", t);
        }
    }

    @Override
    public List<ServiceStatusTypeTO> getServiceStatusTypes() throws WebServiceClientException {
        return getServiceStatusTypes(getLanguageCode());
    }
    
    @Override
    public List<ServiceStatusTypeTO> getServiceStatusTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getServiceStatusTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getServiceStatusTypes", t);
        }
    }

    @Override
    public List<ServiceActionTypeTO> getServiceActionTypes() throws WebServiceClientException {
        return getServiceActionTypes(getLanguageCode());
    }
    
    @Override
    public List<ServiceActionTypeTO> getServiceActionTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getServiceActionTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getServiceActionTypes", t);
        }
    }

    @Override
    public List<ApplicationActionTypeTO> getApplicationActionTypes() throws WebServiceClientException {
        return getApplicationActionTypes(getLanguageCode());
    }

    @Override
    public List<ApplicationActionTypeTO> getApplicationActionTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getApplicationActionTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getApplicationActionTypes", t);
        }
    }
    
    @Override
    public List<PartyTypeTO> getPartyTypes() throws WebServiceClientException {
        return getPartyTypes(getLanguageCode());
    }
    
    @Override
    public List<PartyTypeTO> getPartyTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getPartyTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getPartyTypes", t);
        }
    }

    @Override
    public List<PartyRoleTypeTO> getPartyRoles() throws WebServiceClientException {
        return getPartyRoles(getLanguageCode());
    }
    
    @Override
    public List<PartyRoleTypeTO> getPartyRoles(String lang) throws WebServiceClientException {
        try {
            return getPort().getPartyRoles(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getPartyRoles", t);
        }
    }

    @Override
    public List<IdTypeTO> getIdTypes() throws WebServiceClientException {
        return getIdTypes(getLanguageCode());
    }
    
    @Override
    public List<IdTypeTO> getIdTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getIdTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getIdTypes", t);
        }
    }

    @Override
    public List<ChangeStatusTypeTO> getChangeStatusTypes() throws WebServiceClientException {
        return getChangeStatusTypes(getLanguageCode());
    }
    
    @Override
    public List<ChangeStatusTypeTO> getChangeStatusTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getChangeStatuTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getChangeStatusTypes", t);
        }
    }

    @Override
    public List<BaUnitTypeTO> getBaUnitTypes() throws WebServiceClientException {
        return getBaUnitTypes(this.getLanguageCode());
    }

    @Override
    public List<BaUnitTypeTO> getBaUnitTypes(String lang) throws WebServiceClientException {
        try {
            List<BaUnitTypeTO> result = getPort().getBaUnitTypes(lang);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBaUnitTypes", t);
        }
    }

    @Override
    public List<MortgageTypeTO> getMortgageTypes() throws WebServiceClientException {
        return getMortgageTypes(getLanguageCode());
    }
    
    @Override
    public List<MortgageTypeTO> getMortgageTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getMortgageTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getMortgageTypes", t);
        }
    }

    @Override
    public List<RrrGroupTypeTO> getRrrGroupTypes() throws WebServiceClientException {
        return getRrrGroupTypes(getLanguageCode());
    }
    
    @Override
    public List<RrrGroupTypeTO> getRrrGroupTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getRRRGroupTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRrrGroupTypes", t);
        }
    }

    @Override
    public List<RrrTypeTO> getRrrTypes() throws WebServiceClientException {
        return getRrrTypes(getLanguageCode());
    }
    
    @Override
    public List<RrrTypeTO> getRrrTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getRRRTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRrrTypes", t);
        }
    }

    @Override
    public List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes()
            throws WebServiceClientException {
        return getSourceBaUnitRelationTypes(getLanguageCode());
    }
    
    @Override
    public List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes(String lang)
            throws WebServiceClientException {
        try {
            return getPort().getSourceBaUnitRelationTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getSourceBaUnitRelationTypes", t);
        }
    }

    @Override
    public List<RegistrationStatusTypeTO> getRegistrationStatusTypes()
            throws WebServiceClientException {
        return getRegistrationStatusTypes(getLanguageCode());
    }
    
    @Override
    public List<RegistrationStatusTypeTO> getRegistrationStatusTypes(String lang)
            throws WebServiceClientException {
        try {
            return getPort().getRegistrationStatusTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRegistrationStatusTypes", t);
        }
    }

    @Override
    public List<CadastreObjectTypeTO> getCadastreObjectTypes()
            throws WebServiceClientException {
        return getCadastreObjectTypes(getLanguageCode());
    }
    
    @Override
    public List<CadastreObjectTypeTO> getCadastreObjectTypes(String lang)
            throws WebServiceClientException {
        try {
            return getPort().getCadastreObjectTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCadastreObjectTypes", t);
        }
    }

    @Override
    public List<RrrTypeActionTO> getRrrTypeActions() throws WebServiceClientException {
        return getRrrTypeActions(getLanguageCode());
    }
    
    @Override
    public List<RrrTypeActionTO> getRrrTypeActions(String lang) throws WebServiceClientException {
        try {
            return getPort().getRrrTypeActions(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRrrTypeActions", t);
        }
    }

    @Override
    public AbstractCodeTO saveReferenceData(AbstractCodeTO refDataTO) {
        try {
            return getPort().saveReferenceData(refDataTO);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRrrTypeActions", t);
        }
    }

    @Override
    public List<RequestCategoryTypeTO> getRequestCategoryTypes() throws WebServiceClientException {
        return getRequestCategoryTypes(getLanguageCode());
    }

    @Override
    public List<RequestCategoryTypeTO> getRequestCategoryTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getRequestCategoryTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRequestCategoryTypes", t);
        }
    }

    @Override
    public List<BrTechnicalTypeTO> getBrTechnicalTypes() throws WebServiceClientException {
        return getBrTechnicalTypes(getLanguageCode());
    }

    @Override
    public List<BrTechnicalTypeTO> getBrTechnicalTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getBrTechnicalTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBrTechnicalTypes", t);
        }
    }

    @Override
    public List<BrValidationTargetTypeTO> getBrValidationTargetTypes() throws WebServiceClientException {
        return getBrValidationTargetTypes(getLanguageCode());
    }

    @Override
    public List<BrValidationTargetTypeTO> getBrValidationTargetTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getBrValidationTargetTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBrValidationTargetTypes", t);
        }
    }

    @Override
    public List<BrSeverityTypeTO> getBrSeverityTypes() throws WebServiceClientException {
        return getBrSeverityTypes(getLanguageCode());
    }

    @Override
    public List<BrSeverityTypeTO> getBrSeverityTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getBrSeverityTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBrSeverityTypes", t);
        }
    }

    @Override
    public List<BaUnitRelTypeTO> getBaUnitRelTypes(String lang) throws WebServiceClientException {
        try {
            return getPort().getBaUnitRelTypes(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBaUnitRelTypes", t);
        }
    }

    @Override
    public List<BaUnitRelTypeTO> getBaUnitRelTypes() throws WebServiceClientException {
        return getBaUnitRelTypes(getLanguageCode());
    }
}
