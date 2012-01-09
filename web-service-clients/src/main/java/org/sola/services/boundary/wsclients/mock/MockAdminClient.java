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

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.AdminClient;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.admin.BrTO;
import org.sola.webservices.admin.LanguageTO;
import org.sola.webservices.transferobjects.security.GroupSummaryTO;
import org.sola.webservices.transferobjects.security.GroupTO;
import org.sola.webservices.transferobjects.security.RoleTO;
import org.sola.webservices.transferobjects.security.UserTO;

/**
 * Provides a mock implementation for the 
 * {@linkplain org.sola.services.boundary.wsclients.SystemClient} interface. Uses the 
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method. 
 * <p>Each method mocked by this class has a public constant defined that can be used to reference 
 * a mock response object from the {@linkplain MockServiceManager}. To set a response object
 * for a web method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from this class.</p>
 * @author amcdowell
 * @see MockResponse
 */
public class MockAdminClient extends AbstractMockWSClient implements AdminClient {

    private static final String SERVICE_NAME = "Admin.";
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    public static final String GET_CURRENT_USER = SERVICE_NAME + "getCurrentUser";
    public static final String GET_GROUPS = SERVICE_NAME + "getGroups";
    public static final String GET_GROUP = SERVICE_NAME + "getGroup";
    public static final String GET_USER = SERVICE_NAME + "getUser";
    public static final String SAVE_USER = SERVICE_NAME + "saveUser";
    public static final String SAVE_GROUP = SERVICE_NAME + "saveGroup";
    public static final String SAVE_ROLE = SERVICE_NAME + "saveRole";
    public static final String GET_ROLES = SERVICE_NAME + "GetRoles";
    
    public MockAdminClient(String url) {
        super(url, null);
    }

    /** @return default = true */
    @Override
    public boolean checkConnection() throws WebServiceClientException {
        return getManager().getResponse(CHECK_CONNECTION, Boolean.class, true);
    }

    /** @return default = new user with id set */
    @Override
    public UserTO getCurrentUser() throws WebServiceClientException {
        return getManager().getResponse(GET_CURRENT_USER, UserTO.class,
                MockTOFactory.createUser("test", "test", "Bob", "Smith"), "test");
    }

    @Override
    public List<GroupTO> getGroups() throws WebServiceClientException {
        return getManager().getResponse(GET_GROUPS, List.class,
                MockTOFactory.createGroupList());
    }

    @Override
    public GroupTO getGroup(String groupId) throws WebServiceClientException {
        return getManager().getResponse(GET_GROUP, GroupTO.class,
                MockTOFactory.createGroup(), groupId);
    }

    @Override
    public UserTO getUser(String userName) throws WebServiceClientException {
        return getManager().getResponse(GET_USER, UserTO.class,
                MockTOFactory.createUser("test2", "test2", "Matthew", "Smith1"), "test2");
    }

    @Override
    public UserTO saveUser(UserTO userTO) throws WebServiceClientException {
        return userTO;
    }

    @Override
    public GroupTO saveGroup(GroupTO groupTO) throws WebServiceClientException {
        return groupTO;
    }

    @Override
    public List<RoleTO> getRoles() throws WebServiceClientException {
        return getManager().getResponse(GET_ROLES, List.class,
                MockTOFactory.createRoles());
    }

    @Override
    public RoleTO saveRole(RoleTO roleTO) throws WebServiceClientException {
        return roleTO;
    }

    @Override
    public List<GroupSummaryTO> getGroupsSummary() throws WebServiceClientException {
        return new ArrayList<GroupSummaryTO>();
    }

    @Override
    public boolean changePassword(String userName, String password) throws WebServiceClientException {
        return true;
    }

    @Override
    public List<RoleTO> getCurrentUserRoles() throws WebServiceClientException {
        return getManager().getResponse(GET_ROLES, List.class,
                MockTOFactory.createRoles());
    }

    @Override
    public boolean isUserAdmin() throws WebServiceClientException {
        return true;
    }

    @Override
    public List<LanguageTO> getLanguages() throws WebServiceClientException {
        return new ArrayList<LanguageTO>();
    }

    @Override
    public List<LanguageTO> getLanguages(String lang) throws WebServiceClientException {
        return new ArrayList<LanguageTO>();
    }

    @Override
    public BrTO getBr(String id, String lang) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BrTO getBr(String id) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public BrTO saveBr(BrTO brTO) throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
