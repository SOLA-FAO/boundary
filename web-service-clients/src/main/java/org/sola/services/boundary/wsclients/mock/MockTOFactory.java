/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.webservices.transferobjects.AbstractCodeTO;
import org.sola.webservices.transferobjects.referencedata.*;
import org.sola.webservices.transferobjects.security.GroupTO;
import org.sola.webservices.transferobjects.security.RoleTO;
import org.sola.webservices.transferobjects.security.UserTO;

/**
 * Factory to creates mock Transfer Objects that can be used for testing
 * purposes.
 *
 * @author amcdowell
 * @see MockServiceManager
 * @see MockResponse
 * @see MockCaseManagementClient
 * @see MockReferenceDataClient
 * @see MockSearchClient
 * @see MockSecurityClient
 */
public class MockTOFactory {

    //<editor-fold defaultstate="collapsed" desc="Reference Data TOs">
    /**
     * Creates an AbstractCode class using the data provided.
     */
    public static <T extends AbstractCodeTO> T createCode(Class<T> codeClass,
            String code, String displayValue) {
        T result = null;
        try {
            result = codeClass.newInstance();
        } catch (InstantiationException ex) {
            throw new MockResponseException("Could not create code class - Instantiation"
                    + codeClass.getSimpleName() + ".", ex);
        } catch (IllegalAccessException ex2) {
            throw new MockResponseException("Could not create code class - IllegalAccess"
                    + codeClass.getSimpleName() + ".", ex2);
        }
        result.setCode(code);
        result.setDisplayValue(displayValue);
        result.setStatus("c");
        return result;
    }

    /**
     * Creates a default list of communication types <p> SQL query to generate
     * list from DB: </p>
     * <pre>
     * select 'result.add(createCode(CommunicationTypeTO.class, "' || code || '", "' || display_value || '"));'
     * from party.communication_type
     * </pre>
     */
    public static List<CommunicationTypeTO> createCommunicationTypes() {
        List<CommunicationTypeTO> result = new ArrayList<CommunicationTypeTO>();
        result.add(createCode(CommunicationTypeTO.class, "courier", "Courier"));
        result.add(createCode(CommunicationTypeTO.class, "eMail", "e-Mail"));
        result.add(createCode(CommunicationTypeTO.class, "fax", "Fax"));
        result.add(createCode(CommunicationTypeTO.class, "phone", "Phone"));
        result.add(createCode(CommunicationTypeTO.class, "post", "Post"));
        return result;
    }

    /**
     * Creates a default list of gender types <p> SQL query to generate list
     * from DB: </p>
     * <pre>
     * select 'result.add(createCode(GenderTypeTO.class, "' || code || '", "' || display_value || '"));'
     * from party.gender_type
     * </pre>
     */
    public static List<GenderTypeTO> createGenderTypes() {
        List<GenderTypeTO> result = new ArrayList<GenderTypeTO>();
        result.add(createCode(GenderTypeTO.class, "female", "Female"));
        result.add(createCode(GenderTypeTO.class, "male", "Male"));
        return result;
    }

    /**
     * Creates a RequestTypeTO or a RequestTypeSourceTypesTO
     */
    public static <T extends RequestTypeTO> T createRequestType(Class<T> requestTypeClass,
            String code, String displayName,
            String requestCategoryCode, int daysToComplete, int numPropertiesReqd) {
        T result = createCode(requestTypeClass, code, displayName);
        result.setNrDaysToComplete(daysToComplete);
        result.setRequestCategoryCode(requestCategoryCode);
        result.setNrPropertiesRequired(numPropertiesReqd);
        return result;
    }

    /**
     * Creates a default list of request types
     */
    public static List<RequestTypeTO> createRequestTypes() {
        List<RequestTypeTO> result = new ArrayList<RequestTypeTO>();
        return createRequestTypes(RequestTypeTO.class, result);
    }

    /**
     * Creates a default list of application status codes
     */
    public static List<ApplicationStatusTypeTO> createApplicationStatusTypes() {
        List<ApplicationStatusTypeTO> result = new ArrayList<ApplicationStatusTypeTO>();
        result.add(createCode(ApplicationStatusTypeTO.class, "completed", "Completed"));
        result.add(createCode(ApplicationStatusTypeTO.class, "loaded", "Loaded"));
        result.add(createCode(ApplicationStatusTypeTO.class, "lodged", "Lodged"));
        result.add(createCode(ApplicationStatusTypeTO.class, "underAction", "Under Action"));

        return result;
    }

    /**
     * Creates a default list of application action codes
     */
    public static List<ApplicationActionTypeTO> createApplicationActionTypes() {
        List<ApplicationActionTypeTO> result = new ArrayList<ApplicationActionTypeTO>();
        result.add(createCode(ApplicationActionTypeTO.class, "lodged", "Lodged"));
        result.add(createCode(ApplicationActionTypeTO.class, "archived", "Archived"));
        result.add(createCode(ApplicationActionTypeTO.class, "approved", "Approved"));

        return result;
    }

    /**
     * Creates a default list of service status codes
     */
    public static List<ServiceStatusTypeTO> createServiceStatusTypes() {
        List<ServiceStatusTypeTO> result = new ArrayList<ServiceStatusTypeTO>();
        result.add(createCode(ServiceStatusTypeTO.class, "completed", "Completed"));
        result.add(createCode(ServiceStatusTypeTO.class, "loaded", "Loaded"));
        result.add(createCode(ServiceStatusTypeTO.class, "lodged", "Lodged"));
        result.add(createCode(ServiceStatusTypeTO.class, "underAction", "Under Action"));

        return result;
    }

    /**
     * Creates a default list of party types
     */
    public static List<PartyTypeTO> createPartyTypes() {
        List<PartyTypeTO> result = new ArrayList<PartyTypeTO>();
        result.add(createCode(PartyTypeTO.class, "naturalPerson", "Natural Person"));
        result.add(createCode(PartyTypeTO.class, "nonNaturalPerson", "Non-natural Person"));
        result.add(createCode(PartyTypeTO.class, "group", "Group"));
        result.add(createCode(PartyTypeTO.class, "baunit", "Basic Administrative Unit"));

        return result;
    }

    /**
     * Creates a default list of service action types
     */
    public static List<ServiceActionTypeTO> createServiceActionTypes() {
        List<ServiceActionTypeTO> result = new ArrayList<ServiceActionTypeTO>();
        result.add(createCode(ServiceActionTypeTO.class, "cancel", "Cancel"));
        result.add(createCode(ServiceActionTypeTO.class, "complete", "Complete"));
        result.add(createCode(ServiceActionTypeTO.class, "lodge", "Lodge"));
        result.add(createCode(ServiceActionTypeTO.class, "revert", "Revert"));
        result.add(createCode(ServiceActionTypeTO.class, "start", "Start"));

        return result;
    }

    /**
     * Creates a default list of party types
     */
    public static List<PartyRoleTypeTO> createPartyRoles() {
        List<PartyRoleTypeTO> result = new ArrayList<PartyRoleTypeTO>();
        result.add(createCode(PartyRoleTypeTO.class, "bank", "Bank"));
        result.add(createCode(PartyRoleTypeTO.class, "citizen", "Citizen"));
        result.add(createCode(PartyRoleTypeTO.class, "farmer", "Farmer"));
        result.add(createCode(PartyRoleTypeTO.class, "lodgingAgent", "Lodging Agent"));

        return result;
    }

    /**
     * Creates a default Id types
     */
    public static List<IdTypeTO> createIdTypes() {
        List<IdTypeTO> result = new ArrayList<IdTypeTO>();
        result.add(createCode(IdTypeTO.class, "nationalID", "National ID"));
        result.add(createCode(IdTypeTO.class, "nationalPassport", "National Passport"));
        result.add(createCode(IdTypeTO.class, "otherPassport", "Other Passport"));

        return result;
    }

    /**
     * Creates a default change status type
     */
    public static List<ChangeStatusTypeTO> createChangeStatusTypes() {
        List<ChangeStatusTypeTO> result = new ArrayList<ChangeStatusTypeTO>();
        result.add(createCode(ChangeStatusTypeTO.class, "pending", "Pending"));
        result.add(createCode(ChangeStatusTypeTO.class, "current", "Current"));

        return result;
    }

    /**
     * Creates a default BA unit types
     */
    public static List<BaUnitTypeTO> createBaUnitTypes() {
        List<BaUnitTypeTO> result = new ArrayList<BaUnitTypeTO>();
        result.add(createCode(BaUnitTypeTO.class, "parcel", "Parcel"));
        result.add(createCode(BaUnitTypeTO.class, "apartment", "Apartment"));

        return result;
    }

    /**
     * Creates a default Mortgage types
     */
    public static List<MortgageTypeTO> createMortgageTypes() {
        List<MortgageTypeTO> result = new ArrayList<MortgageTypeTO>();
        result.add(createCode(MortgageTypeTO.class, "mortgage1", "Morgage1"));
        result.add(createCode(MortgageTypeTO.class, "mortgage2", "Morgage2"));

        return result;
    }

    /**
     * Creates a default RRR group types
     */
    public static List<RrrGroupTypeTO> createRRRGroupTypes() {
        List<RrrGroupTypeTO> result = new ArrayList<RrrGroupTypeTO>();
        result.add(createCode(RrrGroupTypeTO.class, "rrrGroup1", "RRRGroup1"));
        result.add(createCode(RrrGroupTypeTO.class, "rrrGroup2", "RRRGroup2"));

        return result;
    }

    /**
     * Creates a default RRR types
     */
    public static List<RrrTypeTO> createRRRTypes() {
        List<RrrTypeTO> result = new ArrayList<RrrTypeTO>();
        RrrTypeTO r = new RrrTypeTO();
        r.setCode("rrrType1");
        r.setRrrGroupTypeCode("rrrGroup1");
        r.setDisplayValue("RrrType1");
        r.setPrimary(Boolean.TRUE);
        r.setShareCheck(Boolean.FALSE);
        r.setPartyRequired(Boolean.FALSE);
        r.setDescription("");
        r.setStatus("c");

        result.add(r);

        r = new RrrTypeTO();
        r.setCode("rrrType2");
        r.setRrrGroupTypeCode("rrrGroup2");
        r.setDisplayValue("RrrType2");
        r.setPrimary(Boolean.TRUE);
        r.setShareCheck(Boolean.FALSE);
        r.setPartyRequired(Boolean.FALSE);
        r.setDescription("");
        r.setStatus("c");

        result.add(r);
        return result;
    }

    /**
     * Creates a default RRR types
     */
    public static List<SourceBaUnitRelationTypeTO> createSourceBaUnitRelationTypes() {
        List<SourceBaUnitRelationTypeTO> result = new ArrayList<SourceBaUnitRelationTypeTO>();
        result.add(createCode(SourceBaUnitRelationTypeTO.class, "sourceBaUnitRelationType1",
                "SourceBaUnitRelationType1"));
        result.add(createCode(SourceBaUnitRelationTypeTO.class, "sourceBaUnitRelationType2",
                "SourceBaUnitRelationType2"));

        return result;
    }

    /**
     * Creates a default registration status types
     */
    public static List<RegistrationStatusTypeTO> createRegistrationStatusTypes() {
        List<RegistrationStatusTypeTO> result = new ArrayList<RegistrationStatusTypeTO>();
        result.add(createCode(RegistrationStatusTypeTO.class, "registrationStatusType1",
                "RegistrationStatusType1"));
        result.add(createCode(RegistrationStatusTypeTO.class, "registrationStatusType2",
                "RegistrationStatusType2"));

        return result;
    }

    /**
     * Creates a default rrr type actions.
     */
    public static List<TypeActionTO> createRrrTypeActions() {
        List<TypeActionTO> result = new ArrayList<TypeActionTO>();
        result.add(createCode(TypeActionTO.class, "new",
                "New"));
        result.add(createCode(TypeActionTO.class, "cancel",
                "Cancel"));

        return result;
    }

    /**
     * Creates a default roles.
     */
    public static List<RoleTO> createRoles() {
        List<RoleTO> result = new ArrayList<RoleTO>();
        result.add(createCode(RoleTO.class, "Approver", "Approver"));
        result.add(createCode(RoleTO.class, "Archive", "Archive"));
        result.add(createCode(RoleTO.class, "Admin", "Admin"));

        return result;
    }

    /**
     * Creates a default list of cadastre object types
     */
    public static List<CadastreObjectTypeTO> createCadastreObjectTypes() {
        List<CadastreObjectTypeTO> result = new ArrayList<CadastreObjectTypeTO>();
        result.add(createCode(CadastreObjectTypeTO.class, "type1", "Type1"));
        result.add(createCode(CadastreObjectTypeTO.class, "type2", "Type2"));
        result.add(createCode(CadastreObjectTypeTO.class, "type3", "Type3"));

        return result;
    }

    /**
     * Creates a default list of land use types
     */
    public static List<LandUseTypeTO> createLandUseTypes() {
        List<LandUseTypeTO> result = new ArrayList<LandUseTypeTO>();
        result.add(createCode(LandUseTypeTO.class, "type1", "Type1"));
        result.add(createCode(LandUseTypeTO.class, "type2", "Type2"));
        result.add(createCode(LandUseTypeTO.class, "type3", "Type3"));

        return result;
    }

    /**
     * Creates a default list of request types using generics so that it is
     * possible to also generate RequestTypeSourceType using the same code. <p>
     * SQL query to generate list from DB: </p>
     * <pre>
     * select 'result.add(createRequestType(requestTypeClass, "' || code || '", "' || display_value
     * || '", "' || request_category_code || '", '|| nr_days_to_complete || ', '|| nr_properties_required
     * || '));' from application.request_type
     * </pre>
     */
    private static <T extends RequestTypeTO> List<T> createRequestTypes(Class<T> requestTypeClass,
            List<T> result) {
        result.add(createRequestType(requestTypeClass, "cadastreChange", "Change to Cadastre", "registrationServices", 30, 1));
        result.add(createRequestType(requestTypeClass, "documentCopy", "Document Copy", "informationServices", 1, 0));
        result.add(createRequestType(requestTypeClass, "mortgageCertificate", "Mortgage Certificate", "registrationServices", 1, 1));
        result.add(createRequestType(requestTypeClass, "newTitle", "New Title", "registrationServices", 5, 0));
        result.add(createRequestType(requestTypeClass, "serviceEnquiry", "Service Enquiry", "informationServices", 1, 0));
        result.add(createRequestType(requestTypeClass, "regnDeeds", "Deed Registration", "registrationServices", 3, 0));
        result.add(createRequestType(requestTypeClass, "regnOnTitle", "Registration on Title", "registrationServices", 5, 1));
        result.add(createRequestType(requestTypeClass, "regnPowerOfAttorney", "Registration of Power of Attorney", "registrationServices", 3, 0));
        result.add(createRequestType(requestTypeClass, "regnStandardDocument", "Registration of Standard Document", "registrationServices", 3, 0));
        result.add(createRequestType(requestTypeClass, "titleSearch", "Title Search", "informationServices", 1, 1));
        result.add(createRequestType(requestTypeClass, "surveyPlanCopy", "Survey Plan Copy", "informationServices", 1, 0));
        result.add(createRequestType(requestTypeClass, "cadastrePrint", "Cadastre Print", "informationServices", 1, 0));
        result.add(createRequestType(requestTypeClass, "cadastreExport", "Cadastre Export", "informationServices", 1, 0));
        result.add(createRequestType(requestTypeClass, "cadastreBulk", "Cadastre Bulk Export", "informationServices", 5, 0));
        return result;
    }

    /**
     * Creates a default list of source types <p> SQL query to generate list
     * from DB: </p>
     * <pre>
     * select 'result.add(createCode(SourceTypeTO.class, "' || code || '", "' || display_value || '"));'
     * from source.source_type
     * </pre>
     */
    public static List<SourceTypeTO> createSourceTypes() {
        List<SourceTypeTO> result = new ArrayList<SourceTypeTO>();
        result.add(createCode(SourceTypeTO.class, "documentDigital", "Digital Document"));
        result.add(createCode(SourceTypeTO.class, "documentHardcopy", "Hardcopy Document"));
        result.add(createCode(SourceTypeTO.class, "imageDigital", "Digital Image"));
        result.add(createCode(SourceTypeTO.class, "imageHardcopy", "Hardcopy Image"));
        result.add(createCode(SourceTypeTO.class, "mapDigital", "Digital Map"));
        result.add(createCode(SourceTypeTO.class, "mapHardcopy", "Hardcopy Map"));
        result.add(createCode(SourceTypeTO.class, "modelDigital", "Digital Model"));
        result.add(createCode(SourceTypeTO.class, "modelHardcopy", "Hardcopy Model"));
        result.add(createCode(SourceTypeTO.class, "profileDigital", "Digital Profile"));
        result.add(createCode(SourceTypeTO.class, "profileHardcopy", "Hardcopy Profile"));
        result.add(createCode(SourceTypeTO.class, "tableDigital", "Digital Table"));
        result.add(createCode(SourceTypeTO.class, "tableHardcopy", "Hardcopy Table"));
        result.add(createCode(SourceTypeTO.class, "videoDigital", "Digital Video"));
        result.add(createCode(SourceTypeTO.class, "videoHardcopy", "Hardcopy Video"));
        return result;
    }

    /**
     * Creates a default list of BR Technical types
     */
    public static List<BrTechnicalTypeTO> createBrTechnicalTypes() {
        List<BrTechnicalTypeTO> result = new ArrayList<BrTechnicalTypeTO>();
        result.add(createCode(BrTechnicalTypeTO.class, "drools", "Drools"));
        result.add(createCode(BrTechnicalTypeTO.class, "sql", "SQL"));

        return result;
    }

    /**
     * Creates a default list of BR Validation Target types
     */
    public static List<BrValidationTargetTypeTO> createBrValidationTargetTypes() {
        List<BrValidationTargetTypeTO> result = new ArrayList<BrValidationTargetTypeTO>();
        result.add(createCode(BrValidationTargetTypeTO.class, "application", "Application"));
        result.add(createCode(BrValidationTargetTypeTO.class, "ba_unit", "Administrative Unit"));
        result.add(createCode(BrValidationTargetTypeTO.class, "cadastre_object", "Cadastre Object"));
        result.add(createCode(BrValidationTargetTypeTO.class, "rrr", "Right or Restriction"));
        result.add(createCode(BrValidationTargetTypeTO.class, "service", "Service"));
        result.add(createCode(BrValidationTargetTypeTO.class, "source", "Source"));

        return result;
    }

    /**
     * Creates a default list of BR Severity types
     */
    public static List<BrSeverityTypeTO> createBrSeverityTypes() {
        List<BrSeverityTypeTO> result = new ArrayList<BrSeverityTypeTO>();
        result.add(createCode(BrSeverityTypeTO.class, "critical", "Critical"));
        result.add(createCode(BrSeverityTypeTO.class, "medium", "Medium"));
        result.add(createCode(BrSeverityTypeTO.class, "warning", "Warning"));

        return result;
    }

    /**
     * Creates a default list of Ba Unit Relationship types
     */
    public static List<BaUnitRelTypeTO> createBaUnitRelTypes() {
        List<BaUnitRelTypeTO> result = new ArrayList<BaUnitRelTypeTO>();
        result.add(createCode(BaUnitRelTypeTO.class, "priorTitle", "Prior Title"));
        result.add(createCode(BaUnitRelTypeTO.class, "rootTitle", "Root of Title"));

        return result;
    }

    /**
     * Creates a default list of Request Category types
     */
    public static List<RequestCategoryTypeTO> createRequestCategoryTypes() {
        List<RequestCategoryTypeTO> result = new ArrayList<RequestCategoryTypeTO>();
        result.add(createCode(RequestCategoryTypeTO.class, "cadastralServices", "Cadastral Services"));
        result.add(createCode(RequestCategoryTypeTO.class, "informationServices", "Information Services"));
        result.add(createCode(RequestCategoryTypeTO.class, "nonRegServices", "Non Registration Services"));
        result.add(createCode(RequestCategoryTypeTO.class, "registrationServices", "Registration Services"));

        return result;
    }
    /**
     * Creates a default list of hierarchy levels
     */
    public static List<HierarchyLevelTO> createHierarchyLevels() {
        List<HierarchyLevelTO> result = new ArrayList<HierarchyLevelTO>();
        result.add(createCode(HierarchyLevelTO.class, "0", "Hierarchy level 0"));
        result.add(createCode(HierarchyLevelTO.class, "1", "Hierarchy level 1"));

        return result;
    }

    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Security TOs">
    /**
     * Creates a default User, username test
     */
    public static UserTO createUser() {
        return createUser("test", "newUserId", "Bob", "Smith");
    }

    /**
     * Creates a userTO with the specified properties
     */
    public static UserTO createUser(String userName, String id, String firstName, String lastName) {
        UserTO result = new UserTO();
        result.setUserName(userName);
        result.setActive(true);
        result.setId(id);
        result.setFirstName(firstName);
        result.setLastName(lastName);
        return result;
    }

    /**
     * Creates a default UserTO list containing 2 users
     */
    public static List<UserTO> createUserList() {
        List<UserTO> result = new ArrayList<UserTO>();
        result.add(createUser());
        result.add(createUser("test2", "anotherId", "Ed", "Jones"));
        return result;
    }

    /**
     * Creates a default GroupTO list.
     */
    public static GroupTO createGroup() {
        GroupTO result = new GroupTO();
        result.setDescription("Description");
        result.setId("Group id");
        result.setName("Group name");
        result.setRowId("row-id-0");
        return result;
    }

    /**
     * Creates a default GroupTO list.
     */
    public static List<GroupTO> createGroupList() {
        List<GroupTO> result = new ArrayList<GroupTO>();
        GroupTO group = new GroupTO();
        group.setDescription("Description2");
        group.setId("Group id1");
        group.setName("Group name2");
        group.setRowId("row-id-1");

        result.add(createGroup());
        result.add(group);

        return result;
    }
    //</editor-fold>
}
