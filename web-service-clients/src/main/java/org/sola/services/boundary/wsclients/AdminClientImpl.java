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
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.admin.*;
import org.sola.webservices.transferobjects.security.GroupSummaryTO;
import org.sola.webservices.transferobjects.security.GroupTO;
import org.sola.webservices.transferobjects.security.UserTO;
import org.sola.webservices.transferobjects.security.RoleTO;

/**
 * Implementation class for the {@linkplain AdminClient} interface.
 */
public class AdminClientImpl extends AbstractWSClientImpl implements AdminClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/admin";
    private static final String LOCAL_PART = "admin-service";

    /**
     * Creates a web service client class for the web service hosted at the specified URL
     *
     * @param url The location of the service WSDL
     */
    public AdminClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected Admin getPort() {
        return getPort(Admin.class, AdminService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = AdminClient.CHECK_CONNECTION;
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
    public UserTO getCurrentUser() throws WebServiceClientException {
        UserTO result = null;
        final String methodName = AdminClient.GET_CURRENT_USER;
        try {
            beforeWebMethod(methodName);
            result = getPort().getCurrentUser();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public List<GroupTO> getGroups() throws WebServiceClientException {
        List<GroupTO> result = null;
        final String methodName = AdminClient.GET_GROUPS;
        try {
            beforeWebMethod(methodName);
            result = getPort().getGroups();
            return result;
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public GroupTO getGroup(String groupId) throws WebServiceClientException {
        GroupTO result = null;
        final String methodName = AdminClient.GET_GROUP;
        try {
            beforeWebMethod(methodName, groupId);
            result = getPort().getGroup(groupId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, groupId);
        }
        return result;
    }

    @Override
    public UserTO getUser(String userName) throws WebServiceClientException {
        UserTO result = null;
        final String methodName = AdminClient.GET_USER;
        try {
            beforeWebMethod(methodName, userName);
            result = getPort().getUser(userName);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, userName);
        }
        return result;
    }

    @Override
    public UserTO saveUser(UserTO userTO) throws WebServiceClientException {
        UserTO result = null;
        final String methodName = AdminClient.SAVE_USER;
        try {
            beforeWebMethod(methodName, userTO);
            result = getPort().saveUser(userTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, userTO);
        }
        return result;
    }

    @Override
    public GroupTO saveGroup(GroupTO groupTO) throws WebServiceClientException {
        GroupTO result = null;
        final String methodName = AdminClient.SAVE_GROUP;
        try {
            beforeWebMethod(methodName, groupTO);
            result = getPort().saveGroup(groupTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, groupTO);
        }
        return result;
    }

    @Override
    public List<RoleTO> getRoles() throws WebServiceClientException {
        List<RoleTO> result = null;
        final String methodName = AdminClient.GET_ROLES;
        try {
            beforeWebMethod(methodName);
            result = getPort().getRoles();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public RoleTO saveRole(RoleTO roleTO) throws WebServiceClientException {
        RoleTO result = null;
        final String methodName = AdminClient.SAVE_ROLE;
        try {
            beforeWebMethod(methodName, roleTO);
            result = getPort().saveRole(roleTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, roleTO);
        }
        return result;
    }

    @Override
    public List<GroupSummaryTO> getGroupsSummary() throws WebServiceClientException {
        List<GroupSummaryTO> result = null;
        final String methodName = AdminClient.GET_GROUPS_SUMMARY;
        try {
            beforeWebMethod(methodName);
            result = getPort().getGroupsSummary();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public boolean changePassword(String userName, String password) throws WebServiceClientException {
        boolean result = false;
        final String methodName = AdminClient.CHANGE_PASSWORD;
        try {
            // Do not allow tracing or logging of the users password
            beforeWebMethod(methodName, userName);
            result = getPort().changePassword(userName, password);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, userName);
        }
        return result;
    }

    @Override
    public List<RoleTO> getCurrentUserRoles() throws WebServiceClientException {
        List<RoleTO> result = null;
        final String methodName = AdminClient.GET_CURRENT_USER_ROLES;
        try {
            beforeWebMethod(methodName);
            result = getPort().getCurrentUserRoles();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public boolean isUserAdmin() throws WebServiceClientException {
        boolean result = false;
        final String methodName = AdminClient.IS_USER_ADMIN;
        try {
            beforeWebMethod(methodName);
            result = getPort().isUserAdmin();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public List<LanguageTO> getLanguages() throws WebServiceClientException {
        return getLanguages(getLanguageCode());
    }

    @Override
    public List<LanguageTO> getLanguages(String lang) throws WebServiceClientException {
        List<LanguageTO> result = null;
        final String methodName = AdminClient.GET_LANGUAGES;
        try {
            beforeWebMethod(methodName, lang);
            result = getPort().getLanguages(lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, lang);
        }
        return result;
    }

    @Override
    public BrTO getBr(String id, String lang) throws WebServiceClientException {
        BrTO result = null;
        final String methodName = AdminClient.GET_BR;
        try {
            beforeWebMethod(methodName, id, lang);
            result = getPort().getBr(id, lang);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id, lang);
        }
        return result;
    }

    @Override
    public BrTO getBr(String id) throws WebServiceClientException {
        return getBr(id, getLanguageCode());
    }

    @Override
    public BrTO saveBr(BrTO brTO) throws WebServiceClientException {
        BrTO result = null;
        final String methodName = AdminClient.SAVE_BR;
        try {
            beforeWebMethod(methodName, brTO);
            result = getPort().saveBr(brTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, brTO);
        }
        return result;
    }

    @Override
    public List<SettingTO> getAllSettings() throws WebServiceClientException {
        List<SettingTO> result = null;
        final String methodName = AdminClient.GET_ALL_SETTINGS;
        try {
            beforeWebMethod(methodName);
            result = getPort().getAllSettings();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public String getSetting(String name, String defaultValue) throws WebServiceClientException {
        String result = null;
        final String methodName = AdminClient.GET_SETTING;
        try {
            beforeWebMethod(methodName, name, defaultValue);
            result = getPort().getSetting(name, defaultValue);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, name, defaultValue);
        }
        return result;
    }

    @Override
    public String consolidationExtract(boolean everything, String password) throws WebServiceClientException {
        String result = null;
        final String methodName = AdminClient.CONSOLIDATION_EXTRACT;
        try {
            beforeWebMethod(methodName);
            result = getPort().consolidationExtract(everything, password);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }

    @Override
    public String consolidationConsolidate(String pathFileName, String password) throws WebServiceClientException {
        String result = null;
        final String methodName = AdminClient.CONSOLIDATION_CONSOLIDATE;
        try {
            beforeWebMethod(methodName, pathFileName);
            result = getPort().consolidationConsolidate(getLanguageCode(), pathFileName, password);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, void.class, pathFileName);
        }
        
        return result;
    }
    
    
}
