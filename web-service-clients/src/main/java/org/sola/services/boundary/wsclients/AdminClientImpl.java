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
import org.sola.webservices.admin.BrTO;
import org.sola.webservices.admin.LanguageTO;
import org.sola.webservices.transferobjects.security.GroupSummaryTO;
import org.sola.webservices.transferobjects.security.GroupTO;
import org.sola.webservices.transferobjects.security.UserTO;
import org.sola.webservices.admin.UnhandledFault;
import org.sola.webservices.admin.SOLAFault;
import org.sola.webservices.admin.Admin;
import org.sola.webservices.admin.AdminService;
import org.sola.webservices.transferobjects.security.RoleTO;

/**
 * Implementation class for the {@linkplain AdminClient} interface. 
 */
public class AdminClientImpl extends AbstractWSClientImpl implements AdminClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/admin";
    private static final String LOCAL_PART = "admin-service";
    private static final String SERVICE_NAME = "Admin.";

    public AdminClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    private Admin getPort() {
        return getPort(Admin.class, AdminService.class);
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
    public UserTO getCurrentUser() throws WebServiceClientException {
        try {
            UserTO result = getPort().getCurrentUser();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCurrentUser", t);
        }
    }

    @Override
    public List<GroupTO> getGroups() throws WebServiceClientException {
        try {
            List<GroupTO> result = getPort().getGroups();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "GetGroups", t);
        }
    }

    @Override
    public GroupTO getGroup(String groupId) throws WebServiceClientException {
        try {
            GroupTO result = getPort().getGroup(groupId);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "GetGroup", t);
        }
    }

    @Override
    public UserTO getUser(String userName) throws WebServiceClientException {
        try {
            UserTO result = getPort().getUser(userName);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "GetUser", t);
        }
    }

    @Override
    public UserTO saveUser(UserTO userTO) throws WebServiceClientException {
        try {
            UserTO result = getPort().saveUser(userTO);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "SaveUser", t);
        }
    }

    @Override
    public GroupTO saveGroup(GroupTO groupTO) throws WebServiceClientException {
        try {
            GroupTO result = getPort().saveGroup(groupTO);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "SaveGroup", t);
        }
    }
    
    @Override
    public List<RoleTO> getRoles() throws WebServiceClientException {
        try {
            List<RoleTO> result = getPort().getRoles();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getRoles", t);
        }
    }
    
    @Override
    public RoleTO saveRole(RoleTO roleTO) throws WebServiceClientException {
        try {
            RoleTO result = getPort().saveRole(roleTO);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "SaveRole", t);
        }
    }

    @Override
    public List<GroupSummaryTO> getGroupsSummary() throws WebServiceClientException {
        try {
            List<GroupSummaryTO> result = getPort().getGroupsSummary();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "GetGroupsSummary", t);
        }
    }

    @Override
    public boolean changePassword(String userName, String password) throws WebServiceClientException {
        try {
            return getPort().changePassword(userName, password);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "changePasswords", t);
        }
    }

    @Override
    public List<RoleTO> getCurrentUserRoles() throws WebServiceClientException {
        try {
            List<RoleTO> result = getPort().getCurrentUserRoles();
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCurrentUserRoles", t);
        }
    }

    @Override
    public boolean isUserAdmin() throws WebServiceClientException {
        try {
            return getPort().isUserAdmin();
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "isUserAdmin", t);
        }
    }

    @Override
    public List<LanguageTO> getLanguages() throws WebServiceClientException {
        return getLanguages(getLanguageCode());
    }

    @Override
    public List<LanguageTO> getLanguages(String lang) throws WebServiceClientException {
        try {
            return getPort().getLanguages(lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getLanguages", t);
        }
    }

    @Override
    public BrTO getBr(String id, String lang) throws WebServiceClientException {
        try {
            return getPort().getBr(id, lang);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getBr", t);
        }
    }

    @Override
    public BrTO getBr(String id) throws WebServiceClientException {
        return getBr(id, getLanguageCode());
    }
    
    @Override
    public BrTO saveBr(BrTO brTO) throws WebServiceClientException {
        try {
            return getPort().saveBr(brTO);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "saveBr", t);
        }
    }
}
