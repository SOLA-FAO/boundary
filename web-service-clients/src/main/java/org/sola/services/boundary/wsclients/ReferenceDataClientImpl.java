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
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.referencedata.ReferenceData;
import org.sola.webservices.referencedata.ReferencedataService;
import org.sola.webservices.transferobjects.AbstractCodeTO;
import org.sola.webservices.transferobjects.referencedata.*;

/**
 * Implementation class for the {@linkplain ReferenceDataClient} interface.
 */
public class ReferenceDataClientImpl extends AbstractWSClientImpl implements ReferenceDataClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/referencedata";
    private static final String LOCAL_PART = "referencedata-service";

    /**
     * Creates a web service client class for the web service hosted at the
     * specified URL
     *
     * @param url The location of the service WSDL
     */
    public ReferenceDataClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected ReferenceData getPort() {
        return getPort(ReferenceData.class, ReferencedataService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = ReferenceDataClient.CHECK_CONNECTION;
        try {
            beforeWebMethod(methodName);
            result = getPort().checkConnection();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public List<CommunicationTypeTO> getCommunicationTypes(String lang) throws WebServiceClientException {
        List<CommunicationTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_COMMUNICATION_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getCommunicationTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
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
        List<GenderTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_GENDER_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getGenderTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<RequestTypeTO> getRequestTypes() throws WebServiceClientException {
        return getRequestTypes(this.getLanguageCode());
    }

    @Override
    public List<RequestTypeTO> getRequestTypes(String lang) throws WebServiceClientException {
        List<RequestTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_REQUEST_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRequestTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<SourceTypeTO> getSourceTypes() throws WebServiceClientException {
        return getSourceTypes(this.getLanguageCode());
    }

    @Override
    public List<SourceTypeTO> getSourceTypes(String lang) throws WebServiceClientException {
        List<SourceTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_SOURCE_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getSourceTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ApplicationStatusTypeTO> getApplicationStatusTypes() throws WebServiceClientException {
        return getApplicationStatusTypes(getLanguageCode());
    }

    @Override
    public List<ApplicationStatusTypeTO> getApplicationStatusTypes(String lang) throws WebServiceClientException {
        List<ApplicationStatusTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_APPLICATION_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getApplicationStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ServiceStatusTypeTO> getServiceStatusTypes() throws WebServiceClientException {
        return getServiceStatusTypes(getLanguageCode());
    }

    @Override
    public List<ServiceStatusTypeTO> getServiceStatusTypes(String lang) throws WebServiceClientException {
        List<ServiceStatusTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_SERVICE_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getServiceStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ServiceActionTypeTO> getServiceActionTypes() throws WebServiceClientException {
        return getServiceActionTypes(getLanguageCode());
    }

    @Override
    public List<ServiceActionTypeTO> getServiceActionTypes(String lang) throws WebServiceClientException {
        List<ServiceActionTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_SERVICE_ACTION_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getServiceActionTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ApplicationActionTypeTO> getApplicationActionTypes() throws WebServiceClientException {
        return getApplicationActionTypes(getLanguageCode());
    }

    @Override
    public List<ApplicationActionTypeTO> getApplicationActionTypes(String lang) throws WebServiceClientException {
        List<ApplicationActionTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_APPLICATION_ACTION_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getApplicationActionTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<PartyTypeTO> getPartyTypes() throws WebServiceClientException {
        return getPartyTypes(getLanguageCode());
    }

    @Override
    public List<PartyTypeTO> getPartyTypes(String lang) throws WebServiceClientException {
        List<PartyTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_PARTY_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getPartyTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<PartyRoleTypeTO> getPartyRoles() throws WebServiceClientException {
        return getPartyRoles(getLanguageCode());
    }

    @Override
    public List<PartyRoleTypeTO> getPartyRoles(String lang) throws WebServiceClientException {
        List<PartyRoleTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_PARTY_ROLES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getPartyRoles(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<IdTypeTO> getIdTypes() throws WebServiceClientException {
        return getIdTypes(getLanguageCode());
    }

    @Override
    public List<IdTypeTO> getIdTypes(String lang) throws WebServiceClientException {
        List<IdTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_ID_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getIdTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ChangeStatusTypeTO> getChangeStatusTypes() throws WebServiceClientException {
        return getChangeStatusTypes(getLanguageCode());
    }

    @Override
    public List<ChangeStatusTypeTO> getChangeStatusTypes(String lang) throws WebServiceClientException {
        List<ChangeStatusTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_CHANGE_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getChangeStatuTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<BaUnitTypeTO> getBaUnitTypes() throws WebServiceClientException {
        return getBaUnitTypes(this.getLanguageCode());
    }

    @Override
    public List<BaUnitTypeTO> getBaUnitTypes(String lang) throws WebServiceClientException {
        List<BaUnitTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_BA_UNIT_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getBaUnitTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<MortgageTypeTO> getMortgageTypes() throws WebServiceClientException {
        return getMortgageTypes(getLanguageCode());
    }

    @Override
    public List<MortgageTypeTO> getMortgageTypes(String lang) throws WebServiceClientException {
        List<MortgageTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_MORTGAGE_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getMortgageTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<RrrGroupTypeTO> getRrrGroupTypes() throws WebServiceClientException {
        return getRrrGroupTypes(getLanguageCode());
    }

    @Override
    public List<RrrGroupTypeTO> getRrrGroupTypes(String lang) throws WebServiceClientException {
        List<RrrGroupTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_RRR_GROUP_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRRRGroupTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<RrrTypeTO> getRrrTypes() throws WebServiceClientException {
        return getRrrTypes(getLanguageCode());
    }

    @Override
    public List<RrrTypeTO> getRrrTypes(String lang) throws WebServiceClientException {
        List<RrrTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_RRR_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRRRTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes()
            throws WebServiceClientException {
        return getSourceBaUnitRelationTypes(getLanguageCode());
    }

    @Override
    public List<SourceBaUnitRelationTypeTO> getSourceBaUnitRelationTypes(String lang)
            throws WebServiceClientException {
        List<SourceBaUnitRelationTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_SOURCE_BA_UNIT_RELATION_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getSourceBaUnitRelationTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<RegistrationStatusTypeTO> getRegistrationStatusTypes()
            throws WebServiceClientException {
        return getRegistrationStatusTypes(getLanguageCode());
    }

    @Override
    public List<RegistrationStatusTypeTO> getRegistrationStatusTypes(String lang)
            throws WebServiceClientException {
        List<RegistrationStatusTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_REGISTRATION_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRegistrationStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<LandUseTypeTO> getLandUseTypes()
            throws WebServiceClientException {
        return getLandUseTypes(getLanguageCode());
    }

    @Override
    public List<LandUseTypeTO> getLandUseTypes(String lang)
            throws WebServiceClientException {
        List<LandUseTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_LAND_USE_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getLandUseTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<CadastreObjectTypeTO> getCadastreObjectTypes()
            throws WebServiceClientException {
        return getCadastreObjectTypes(getLanguageCode());
    }

    @Override
    public List<CadastreObjectTypeTO> getCadastreObjectTypes(String lang)
            throws WebServiceClientException {
        List<CadastreObjectTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_CADASTRE_OBJECT_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getCadastreObjectTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<TypeActionTO> getTypeActions() throws WebServiceClientException {
        return getTypeActions(getLanguageCode());
    }

    @Override
    public List<TypeActionTO> getTypeActions(String lang) throws WebServiceClientException {
        List<TypeActionTO> result = null;
        final String methodName = ReferenceDataClient.GET_TYPE_ACTIONS;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getTypeActions(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public AbstractCodeTO saveReferenceData(AbstractCodeTO refDataTO) {
        AbstractCodeTO result = null;
        final String methodName = ReferenceDataClient.SAVE_REFERENCE_DATA;
        try {
            beforeWebMethod(methodName, refDataTO);
            result = getPort().saveReferenceData(refDataTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, refDataTO);
        }
        return result;
    }

    @Override
    public List<RequestCategoryTypeTO> getRequestCategoryTypes() throws WebServiceClientException {
        return getRequestCategoryTypes(getLanguageCode());
    }

    @Override
    public List<RequestCategoryTypeTO> getRequestCategoryTypes(String lang) throws WebServiceClientException {
        List<RequestCategoryTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_REQUEST_CATEGORY_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRequestCategoryTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<BrTechnicalTypeTO> getBrTechnicalTypes() throws WebServiceClientException {
        return getBrTechnicalTypes(getLanguageCode());
    }

    @Override
    public List<BrTechnicalTypeTO> getBrTechnicalTypes(String lang) throws WebServiceClientException {
        List<BrTechnicalTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_BR_TECHNICAL_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getBrTechnicalTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<BrValidationTargetTypeTO> getBrValidationTargetTypes() throws WebServiceClientException {
        return getBrValidationTargetTypes(getLanguageCode());
    }

    @Override
    public List<BrValidationTargetTypeTO> getBrValidationTargetTypes(String lang) throws WebServiceClientException {
        List<BrValidationTargetTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_BR_VALIDATION_TARGET_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getBrValidationTargetTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<BrSeverityTypeTO> getBrSeverityTypes() throws WebServiceClientException {
        return getBrSeverityTypes(getLanguageCode());
    }

    @Override
    public List<BrSeverityTypeTO> getBrSeverityTypes(String lang) throws WebServiceClientException {
        List<BrSeverityTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_BR_SEVERITY_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getBrSeverityTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<BaUnitRelTypeTO> getBaUnitRelTypes(String lang) throws WebServiceClientException {
        List<BaUnitRelTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_BA_UNIT_REL_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getBaUnitRelTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<BaUnitRelTypeTO> getBaUnitRelTypes() throws WebServiceClientException {
        return getBaUnitRelTypes(getLanguageCode());
    }

    @Override
    public List<ConditionTypeTO> getConditionTypes() throws WebServiceClientException {
        return getConditionTypes(getLanguageCode());
    }

    @Override
    public List<ConditionTypeTO> getConditionTypes(String lang) throws WebServiceClientException {
        List<ConditionTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_CONDITION_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getConditionTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<HierarchyLevelTO> getHierarchyLevels() throws WebServiceClientException {
        return getHierarchyLevels(getLanguageCode());
    }

    @Override
    public List<HierarchyLevelTO> getHierarchyLevels(String lang) throws WebServiceClientException {
        List<HierarchyLevelTO> result = null;
        final String methodName = ReferenceDataClient.GET_HIERARCHY_LEVELS;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getHierarchyLevels(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<NotationStatusTypeTO> getNotationStatusTypes() throws WebServiceClientException {
        return getNotationStatusTypes(getLanguageCode());
    }

    @Override
    public List<NotationStatusTypeTO> getNotationStatusTypes(String lang) throws WebServiceClientException {
        List<NotationStatusTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_NOTATION_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getNotationStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<StateLandStatusTypeTO> getStateLandStatusTypes() throws WebServiceClientException {
        return getStateLandStatusTypes(getLanguageCode());
    }

    @Override
    public List<StateLandStatusTypeTO> getStateLandStatusTypes(String lang) throws WebServiceClientException {
        List<StateLandStatusTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_STATE_LAND_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getStateLandStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<RrrSubTypeTO> getRrrSubTypes() throws WebServiceClientException {
        return getRrrSubTypes(getLanguageCode());
    }

    @Override
    public List<RrrSubTypeTO> getRrrSubTypes(String lang) throws WebServiceClientException {
        List<RrrSubTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_RRR_SUB_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRrrSubTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ChecklistGroupTO> getChecklistGroups() throws WebServiceClientException {
        return getChecklistGroups(getLanguageCode());
    }

    @Override
    public List<ChecklistGroupTO> getChecklistGroups(String lang) throws WebServiceClientException {
        List<ChecklistGroupTO> result = null;
        final String methodName = ReferenceDataClient.GET_CHECKLIST_GROUPS;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getChecklistGroups(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ChecklistItemTO> getChecklistItems() throws WebServiceClientException {
        return getChecklistItems(getLanguageCode());
    }

    @Override
    public List<ChecklistItemTO> getChecklistItems(String lang) throws WebServiceClientException {
        List<ChecklistItemTO> result = null;
        final String methodName = ReferenceDataClient.GET_CHECKLIST_ITEMS;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getChecklistItems(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<PublicDisplayItemTypeTO> getPublicDisplayTypes() throws WebServiceClientException {
        return getPublicDisplayTypes(getLanguageCode());
    }

    @Override
    public List<PublicDisplayItemTypeTO> getPublicDisplayTypes(String lang) throws WebServiceClientException {
        List<PublicDisplayItemTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_PUBLIC_DISPLAY_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getPublicDisplayItemTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<PublicDisplayItemStatusTO> getPublicDisplayStatusTypes() throws WebServiceClientException {
        return getPublicDisplayStatusTypes(getLanguageCode());
    }

    @Override
    public List<PublicDisplayItemStatusTO> getPublicDisplayStatusTypes(String lang) throws WebServiceClientException {
        List<PublicDisplayItemStatusTO> result = null;
        final String methodName = ReferenceDataClient.GET_PUBLIC_DISPLAY_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getPublicDisplayItemStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ValuationTypeTO> getValuationTypes() throws WebServiceClientException {
        return getValuationTypes(getLanguageCode());
    }

    @Override
    public List<ValuationTypeTO> getValuationTypes(String lang) throws WebServiceClientException {
        List<ValuationTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_VALUATION_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getValuationTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<AuthorityTO> getAuthorityTypes() throws WebServiceClientException {
        return getAuthorityTypes(getLanguageCode());
    }

    @Override
    public List<AuthorityTO> getAuthorityTypes(String lang) throws WebServiceClientException {
        List<AuthorityTO> result = null;
        final String methodName = ReferenceDataClient.GET_AUTHORITY_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getAuthorityTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<ObjectionStatusTO> getObjectionStatusTypes() throws WebServiceClientException {
        return getObjectionStatusTypes(getLanguageCode());
    }

    @Override
    public List<ObjectionStatusTO> getObjectionStatusTypes(String lang) throws WebServiceClientException {
        List<ObjectionStatusTO> result = null;
        final String methodName = ReferenceDataClient.GET_OBJECTION_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getObjectionStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<NotifyRelationshipTypeTO> getNotifyRelationshipTypes() throws WebServiceClientException {
        return getNotifyRelationshipTypes(getLanguageCode());
    }

    @Override
    public List<NotifyRelationshipTypeTO> getNotifyRelationshipTypes(String lang) throws WebServiceClientException {
        List<NotifyRelationshipTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_NOTIFY_RELATIONSHIP_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getNotifyRelationshipTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<NegotiateStatusTO> getNegotiateStatusTypes() throws WebServiceClientException {
        return getNegotiateStatusTypes(getLanguageCode());
    }

    @Override
    public List<NegotiateStatusTO> getNegotiateStatusTypes(String lang) throws WebServiceClientException {
        List<NegotiateStatusTO> result = null;
        final String methodName = ReferenceDataClient.GET_NEGOTIATE_STATUS_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getNegotiateStatusTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<NegotiateTypeTO> getNegotiateTypes() throws WebServiceClientException {
        return getNegotiateTypes(getLanguageCode());
    }

    @Override
    public List<NegotiateTypeTO> getNegotiateTypes(String lang) throws WebServiceClientException {
        List<NegotiateTypeTO> result = null;
        final String methodName = ReferenceDataClient.GET_NEGOTIATE_TYPES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getNegotiateTypes(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public List<RequestDisplayGroupTO> getRequestDisplayGroups() throws WebServiceClientException {
        return getRequestDisplayGroups(getLanguageCode());
    }

    @Override
    public List<RequestDisplayGroupTO> getRequestDisplayGroups(String lang) throws WebServiceClientException {
        List<RequestDisplayGroupTO> result = null;
        final String methodName = ReferenceDataClient.GET_REQUEST_DISPLAY_GROUPS;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getRequestDisplayGroups(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }
}
