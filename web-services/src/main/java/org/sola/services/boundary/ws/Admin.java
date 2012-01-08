/**
 * ******************************************************************************************
 * Copyright (C) 2011 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.sola.common.SOLAException;
import org.sola.common.messaging.ServiceMessage;
import org.sola.services.boundary.transferobjects.security.RoleTO;
import org.sola.services.boundary.transferobjects.security.GroupSummaryTO;
import org.sola.services.boundary.transferobjects.security.GroupTO;
import org.sola.services.boundary.transferobjects.security.UserTO;
import org.sola.services.boundary.transferobjects.system.LanguageTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.SOLAAccessFault;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejbs.admin.businesslogic.AdminEJBLocal;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.Group;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.Role;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.User;

/**
 * Provides methods for administrators to manage users, reference data and system settings.
 */
@WebService(serviceName = "admin-service", targetNamespace = ServiceConstants.ADMIN_WS_NAMESPACE)
public class Admin extends AbstractWebService {

    @EJB
    AdminEJBLocal adminEJB;

    /** Dummy method to check the web service instance is working. */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    /** Returns list of all available languages.  */
    @WebMethod(operationName = "GetLanguages")
    public List<LanguageTO> GetLanguages(String lang)
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                List<LanguageTO> result = GenericTranslator.toTOList(
                        adminEJB.getLanguages(lang), LanguageTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }
    
    /** Returns list of all user groups.  */
    @WebMethod(operationName = "GetGroups")
    public List<GroupTO> GetGroups()
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                List<GroupTO> result = GenericTranslator.toTOList(
                        adminEJB.getGroups(), GroupTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Returns list of all groups summaries.  */
    @WebMethod(operationName = "GetGroupsSummary")
    public List<GroupSummaryTO> GetGroupsSummary()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        try {
            try {
                beginTransaction();
                List<GroupSummaryTO> result = GenericTranslator.toTOList(
                        adminEJB.getGroupsSummary(), GroupSummaryTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAAccessFault.class) {
                throw (SOLAAccessFault) fault;
            }

            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }

            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Returns currently logged user */
    @WebMethod(operationName = "GetCurrentUser")
    public UserTO GetCurrentUser()
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                UserTO currentUser = GenericTranslator.toTO(
                        adminEJB.getCurrentUser(), UserTO.class);
                commitTransaction();
                return currentUser;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Returns user */
    @WebMethod(operationName = "GetUser")
    public UserTO GetUser(@WebParam(name = "userName") String userName)
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                UserTO user = GenericTranslator.toTO(
                        adminEJB.getUser(userName), UserTO.class);
                commitTransaction();
                return user;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Saves user. */
    @WebMethod(operationName = "SaveUser")
    public UserTO SaveUser(@WebParam(name = "userTO") UserTO userTO) throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                User user = adminEJB.getUser(userTO.getUserName());
                if (user != null && userTO != null && user.getUserName() != null
                        && user.getUserName().equals(userTO.getUserName())
                        && !user.getId().equals(userTO.getId())) {
                    throw new SOLAException(ServiceMessage.ADMIN_WS_USER_EXISTS,
                            new String[]{user.getUserName()});

                }
                UserTO result = GenericTranslator.toTO(
                        adminEJB.saveUser(GenericTranslator.fromTO(userTO, User.class, user)), UserTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Returns group by ID. */
    @WebMethod(operationName = "GetGroup")
    public GroupTO GetGroup(@WebParam(name = "groupId") String groupId)
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                GroupTO result = GenericTranslator.toTO(adminEJB.getGroup(groupId), GroupTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Creates/saves group. */
    @WebMethod(operationName = "SaveGroup")
    public GroupTO SaveGroup(@WebParam(name = "groupTO") GroupTO groupTO) throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                Group group = adminEJB.getGroup(groupTO.getId());
                GroupTO result = GenericTranslator.toTO(
                        adminEJB.saveGroup(GenericTranslator.fromTO(groupTO, Group.class, group)), GroupTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetRoles")
    public List<RoleTO> GetRoles()
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                List<RoleTO> result = GenericTranslator.toTOList(
                        adminEJB.getRoles(), RoleTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetCurrentUserRoles")
    public List<RoleTO> GetCurrentUserRoles() throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                List<RoleTO> result = GenericTranslator.toTOList(
                        adminEJB.getCurrentUserRoles(), RoleTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Update role. */
    @WebMethod(operationName = "SaveRole")
    public RoleTO SaveRole(@WebParam(name = "roleTO") RoleTO roleTO) throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                Role role = adminEJB.getRole(roleTO.getCode());
                RoleTO result = GenericTranslator.toTO(
                        adminEJB.saveRole(GenericTranslator.fromTO(roleTO, Role.class, role)), RoleTO.class);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    /** Update role. */
    @WebMethod(operationName = "ChangePassword")
    public boolean ChangePassword(@WebParam(name = "userName") String userName,
            @WebParam(name = "password") String password) throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                boolean result = adminEJB.changePassword(userName, password);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "IsUserAdmin")
    public boolean IsUserAdmin() throws SOLAFault, UnhandledFault {
        try {
            return adminEJB.isUserAdmin();
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }
}
