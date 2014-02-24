/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
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

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.AdminClient;
import org.sola.webservices.admin.*;
import org.sola.webservices.transferobjects.security.GroupSummaryTO;
import org.sola.webservices.transferobjects.security.GroupTO;
import org.sola.webservices.transferobjects.security.RoleTO;
import org.sola.webservices.transferobjects.security.UserTO;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.admin} interface. Uses the
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method.
 * <p>Each method mocked by this class has a public constant defined that can be used to reference a
 * mock response object from the {@linkplain MockServiceManager}. To set a response object for a web
 * method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method referencing
 * the appropriate web method constant from {@linkplain org.sola.services.boundary.wsclients.AdminClient}.</p>
 *
 * @see MockAdminClient
 * @see AdminClient
 * @see MockServiceManager
 * @see MockResponse
 *
 */
public class MockAdminPort implements Admin {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type.
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionBasic(Exception ex) throws SOLAFault, UnhandledFault {
        if (SOLAFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAFault) ex;
        } else if (UnhandledFault.class.isAssignableFrom(ex.getClass())) {
            throw (UnhandledFault) ex;
        } else if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
            throw (RuntimeException) ex;
        } else {
            // The type of the exception does not match any recognized exception type used by this 
            // web service. Throw a MockResponseException to fail the test. 
            throw new MockResponseException("Unable to convert the mocked response exception to "
                    + "recognized exception type: " + ex.getClass().getName());
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the SOLAAccessFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAccess(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, MockResponseException {
        if (SOLAAccessFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAAccessFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the OptimisticLockingFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionUpdate(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, OptimisticLockingFault, MockResponseException {
        if (OptimisticLockingFault.class.isAssignableFrom(ex.getClass())) {
            throw (OptimisticLockingFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionUpdate(java.lang.Exception) processExceptionUpdate}
     * to include the OptimisticLockingFault and SOLAValidationFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAll(Exception ex) throws OptimisticLockingFault, SOLAAccessFault,
            SOLAFault, SOLAValidationFault, UnhandledFault, MockResponseException {
        if (SOLAValidationFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAValidationFault) ex;
        } else {
            processExceptionUpdate(ex);
        }
    }

    /**
     * Response Key = AdminClient.CHANAGE_PASSWORD
     *
     * @return default = true
     */
    @Override
    public boolean changePassword(String userName, String password) throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault {
        try {
            return getManager().getResponse(AdminClient.CHANGE_PASSWORD, Boolean.class, true, userName, password);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return false;
        }
    }

    /**
     * Response Key = AdminClient.CHECK_CONNECTION
     *
     * @return default = true.
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(AdminClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = AdminClient.GET_BR
     *
     * @return default = new BrTO()
     */
    @Override
    public BrTO getBr(String id, String lang) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        BrTO defaultResponse = new BrTO();
        try {
            return getManager().getResponse(AdminClient.GET_BR, BrTO.class, defaultResponse, id, lang);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_CURRENT_USER
     *
     * @return default = MockTOFactory.createUser("test", "test", "Bob", "Smith")
     */
    @Override
    public UserTO getCurrentUser() throws SOLAFault, UnhandledFault {
        UserTO defaultResponse = MockTOFactory.createUser("test", "test", "Bob", "Smith");
        try {
            return getManager().getResponse(AdminClient.GET_CURRENT_USER, UserTO.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_CURRENT_USER_ROLES
     *
     * @return default = MockTOFactory.createRoles()
     */
    @Override
    public List<RoleTO> getCurrentUserRoles() throws SOLAFault, UnhandledFault {
        List<RoleTO> defaultResponse = MockTOFactory.createRoles();
        try {
            return getManager().getResponse(AdminClient.GET_CURRENT_USER_ROLES, List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_GROUP
     *
     * @return default = MockTOFactory.createGroup()
     */
    @Override
    public GroupTO getGroup(String groupId) throws SOLAFault, UnhandledFault, SOLAAccessFault {
        GroupTO defaultResponse = MockTOFactory.createGroup();
        try {
            return getManager().getResponse(AdminClient.GET_GROUP, GroupTO.class, defaultResponse, groupId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_GROUPS
     *
     * @return default = MockTOFactory.createGroup()
     */
    @Override
    public List<GroupTO> getGroups() throws SOLAFault, UnhandledFault, SOLAAccessFault {
        List<GroupTO> defaultResponse = MockTOFactory.createGroupList();
        try {
            return getManager().getResponse(AdminClient.GET_GROUPS, List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_GROUPS_SUMMARY
     *
     * @return default = new ArrayList<GroupSummaryTO>()
     */
    @Override
    public List<GroupSummaryTO> getGroupsSummary() throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<GroupSummaryTO> defaultResponse = new ArrayList<GroupSummaryTO>();
        try {
            return getManager().getResponse(AdminClient.GET_GROUPS_SUMMARY, List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_LANGUAGES
     *
     * @return default = new ArrayList<LanguageTO>()
     */
    @Override
    public List<LanguageTO> getLanguages(String arg0) throws SOLAFault, UnhandledFault {
        List<LanguageTO> defaultResponse = new ArrayList<LanguageTO>();
        try {
            return getManager().getResponse(AdminClient.GET_LANGUAGES, List.class, defaultResponse, arg0);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_ROLES
     *
     * @return default = MockTOFactory.createRoles()
     */
    @Override
    public List<RoleTO> getRoles() throws SOLAFault, UnhandledFault, SOLAAccessFault {
        List<RoleTO> defaultResponse = MockTOFactory.createRoles();
        try {
            return getManager().getResponse(AdminClient.GET_ROLES, List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_USER
     *
     * @return default = MockTOFactory.createUser("test2", "test2", "Matthew", "Smith1")
     */
    @Override
    public UserTO getUser(String userName) throws SOLAFault, UnhandledFault, SOLAAccessFault {
        UserTO defaultResponse = MockTOFactory.createUser("test2", "test2", "Matthew", "Smith1");
        try {
            return getManager().getResponse(AdminClient.GET_USER, UserTO.class, defaultResponse, userName);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.IS_USER_ADMIN
     *
     * @return default = false
     */
    @Override
    public boolean isUserAdmin() throws SOLAFault, UnhandledFault {
        try {
            return getManager().getResponse(AdminClient.IS_USER_ADMIN, Boolean.class, false);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return false;
        }
    }

    /**
     * Response Key = AdminClient.SAVE_BR
     *
     * @return default = br parameter
     */
    @Override
    public BrTO saveBr(BrTO br) throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        BrTO defaultResponse = br;
        try {
            return getManager().getResponse(AdminClient.SAVE_BR, BrTO.class, defaultResponse, br);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.SAVE_GROUP
     *
     * @return default = groupTO parameter
     */
    @Override
    public GroupTO saveGroup(GroupTO groupTO) throws SOLAFault, UnhandledFault, SOLAAccessFault, OptimisticLockingFault {
        GroupTO defaultResponse = groupTO;
        try {
            return getManager().getResponse(AdminClient.SAVE_GROUP, GroupTO.class, defaultResponse, groupTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.SAVE_ROLE
     *
     * @return default = roleTO parameter
     */
    @Override
    public RoleTO saveRole(RoleTO roleTO) throws SOLAFault, UnhandledFault, SOLAAccessFault, OptimisticLockingFault {
        RoleTO defaultResponse = roleTO;
        try {
            return getManager().getResponse(AdminClient.SAVE_ROLE, RoleTO.class, defaultResponse, roleTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.SAVE_USER
     *
     * @return default = userTO parameter
     */
    @Override
    public UserTO saveUser(UserTO userTO) throws SOLAFault, UnhandledFault, SOLAAccessFault,
            OptimisticLockingFault {
        UserTO defaultResponse = userTO;
        try {
            return getManager().getResponse(AdminClient.SAVE_USER, UserTO.class, defaultResponse, userTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_ALL_SETTINGS
     *
     * @return default = new ArrayList<SettingTO>()
     */
    @Override
    public List<SettingTO> getAllSettings() throws SOLAFault, UnhandledFault {
        List<SettingTO> defaultResponse = new ArrayList<SettingTO>();
        try {
            return getManager().getResponse(AdminClient.GET_ALL_SETTINGS, List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdminClient.GET_SETTING
     *
     * @return default = defaultValue
     */
    @Override
    public String getSetting(String name, String defaultValue) throws SOLAFault, UnhandledFault {
        String defaultResponse = defaultValue;
        try {
            return getManager().getResponse(AdminClient.GET_SETTING, String.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    @Override
    public String consolidationExtract(boolean everything, String password) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String consolidationConsolidate(String languageCode, String fileInServer, String password) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
