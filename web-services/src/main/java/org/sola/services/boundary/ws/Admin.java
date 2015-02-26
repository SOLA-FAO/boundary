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
package org.sola.services.boundary.ws;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.common.SOLAException;
import org.sola.common.messaging.ServiceMessage;
import org.sola.services.boundary.transferobjects.security.GroupSummaryTO;
import org.sola.services.boundary.transferobjects.security.GroupTO;
import org.sola.services.boundary.transferobjects.security.RoleTO;
import org.sola.services.boundary.transferobjects.security.UserTO;
import org.sola.services.boundary.transferobjects.system.BrTO;
import org.sola.services.boundary.transferobjects.system.ConfigPanelLauncherTO;
import org.sola.services.boundary.transferobjects.system.LanguageTO;
import org.sola.services.boundary.transferobjects.system.PanelLauncherGroupTO;
import org.sola.services.boundary.transferobjects.system.SettingTO;
import org.sola.services.common.EntityTable;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.*;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.system.businesslogic.SystemEJBLocal;
import org.sola.services.ejb.system.repository.entities.Br;
import org.sola.services.ejb.system.repository.entities.ConfigPanelLauncher;
import org.sola.services.ejb.system.repository.entities.PanelLauncherGroup;
import org.sola.services.ejbs.admin.businesslogic.AdminEJBLocal;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.Group;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.Role;
import org.sola.services.ejbs.admin.businesslogic.repository.entities.User;

/**
 * Provides methods for administrators to manage users, reference data and
 * system settings.
 */
@WebService(serviceName = "admin-service", targetNamespace = ServiceConstants.ADMIN_WS_NAMESPACE)
public class Admin extends AbstractWebService {

    @EJB
    AdminEJBLocal adminEJB;
    @EJB
    SystemEJBLocal systemEJB;
    @Resource
    private WebServiceContext wsContext;

    /**
     * Web method that can be used to validate if the web service is available.
     *
     * @return Always true
     */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getLanguages(java.lang.String)
     * AdminEJB.getLanguages}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetLanguages")
    public List<LanguageTO> GetLanguages(String lang)
            throws SOLAFault, UnhandledFault {

        final String langTmp = lang;
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        adminEJB.getLanguages(langTmp), LanguageTO.class);
            }
        });

        return (List<LanguageTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getGroups()
     * AdminEJB.getGroups}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetGroups")
    public List<GroupTO> GetGroups()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        adminEJB.getGroups(), GroupTO.class);
            }
        });

        return (List<GroupTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getGroupsSummary()
     * AdminEJB.getGroupsSummary}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetGroupsSummary")
    public List<GroupSummaryTO> GetGroupsSummary()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        adminEJB.getGroupsSummary(), GroupSummaryTO.class);
            }
        });

        return (List<GroupSummaryTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getCurrentUser()
     * AdminEJB.getCurrentUser}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetCurrentUser")
    public UserTO GetCurrentUser()
            throws SOLAFault, UnhandledFault {

        //     FLOSS - 813 3      
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        adminEJB.getCurrentUser(), UserTO.class);
            }
        });

        return (UserTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getUser(java.lang.String)
     * AdminEJB.getUser}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetUser")
    public UserTO GetUser(@WebParam(name = "userName") String userName)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String userNameTmp = userName;
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        adminEJB.getUser(userNameTmp), UserTO.class);
            }
        });

        return (UserTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#saveUser(org.sola.services.ejbs.admin.businesslogic.repository.entities.User)
     * AdminEJB.saveUser}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveUser")
    public UserTO SaveUser(@WebParam(name = "userTO") UserTO userTO) throws
            SOLAFault, UnhandledFault, OptimisticLockingFault, SOLAAccessFault {

        final UserTO userTOTmp = userTO;
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {
            @Override
            public void run() {
                User user = adminEJB.getUser(userTOTmp.getUserName());
                if (user != null && userTOTmp != null && user.getUserName() != null
                        && user.getUserName().equals(userTOTmp.getUserName())
                        && !user.getId().equals(userTOTmp.getId())) {
                    throw new SOLAException(ServiceMessage.ADMIN_WS_USER_EXISTS,
                            new String[]{user.getUserName()});

                }
                result[0] = GenericTranslator.toTO(
                        adminEJB.saveUser(GenericTranslator.fromTO(userTOTmp, User.class, user)), UserTO.class);
            }
        });

        return (UserTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getGroup(java.lang.String)
     * AdminEJB.getGroup}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetGroup")
    public GroupTO GetGroup(@WebParam(name = "groupId") String groupId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String groupIdTmp = groupId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(adminEJB.getGroup(groupIdTmp),
                        GroupTO.class);
            }
        });

        return (GroupTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#saveGroup(org.sola.services.ejbs.admin.businesslogic.repository.entities.Group)
     * AdminEJB.saveGroup}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveGroup")
    public GroupTO SaveGroup(@WebParam(name = "groupTO") GroupTO groupTO) throws
            SOLAFault, UnhandledFault, OptimisticLockingFault, SOLAAccessFault {
        //     FLOSS - 813 6     
        final GroupTO groupTOTmp = groupTO;
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {
            @Override
            public void run() {
                Group group = adminEJB.getGroup(groupTOTmp.getId());
                result[0] = GenericTranslator.toTO(
                        adminEJB.saveGroup(GenericTranslator.fromTO(groupTOTmp, Group.class, group)), GroupTO.class);
            }
        });

        return (GroupTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getRoles()
     * AdminEJB.getRoles}
     *
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetRoles")
    public List<RoleTO> GetRoles()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        adminEJB.getRoles(), RoleTO.class);
            }
        });

        return (List<RoleTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getCurrentUserRoles()
     * AdminEJB.getCurrentUserRoles}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetCurrentUserRoles")
    public List<RoleTO> GetCurrentUserRoles() throws SOLAFault, UnhandledFault {

        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        adminEJB.getCurrentUserRoles(), RoleTO.class);
            }
        });

        return (List<RoleTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#saveRole(org.sola.services.ejbs.admin.businesslogic.repository.entities.Role)
     * AdminEJB.saveRole}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     */
    @WebMethod(operationName = "SaveRole")
    public RoleTO SaveRole(@WebParam(name = "roleTO") RoleTO roleTO)
            throws SOLAFault, UnhandledFault, SOLAAccessFault, OptimisticLockingFault {

        final RoleTO roleTOTmp = roleTO;
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {
            @Override
            public void run() {
                Role role = adminEJB.getRole(roleTOTmp.getCode());
                result[0] = GenericTranslator.toTO(
                        adminEJB.saveRole(GenericTranslator.fromTO(roleTOTmp, Role.class, role)), RoleTO.class);
            }
        });

        return (RoleTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#changePassword(java.lang.String, java.lang.String)
     * AdminEJB.changePassword}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     */
    @WebMethod(operationName = "ChangePassword")
    public boolean ChangePassword(@WebParam(name = "userName") String userName,
            @WebParam(name = "password") String password) throws SOLAFault, UnhandledFault,
            SOLAAccessFault, OptimisticLockingFault {

        final String userNameTmp = userName;
        final String passwordTmp = password;
        final boolean[] result = {false};

        runUpdate(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = adminEJB.changePassword(userNameTmp, passwordTmp);
            }
        });

        return result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#isUserAdmin()
     * AdminEJB.isUserAdmin}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "IsUserAdmin")
    public boolean IsUserAdmin() throws SOLAFault, UnhandledFault {

        final boolean[] result = {false};

        runOpenQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = adminEJB.isUserAdmin();
            }
        });

        return result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getBr(java.lang.String, java.lang.String)
     * SystemEJB.getBr}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetBr")
    public BrTO GetBr(@WebParam(name = "id") String id, @WebParam(name = "lang") String lang)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final Object[] params = {id, lang};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                String id = params[0] == null ? null : params[0].toString();
                String languageCode = params[1] == null ? null : params[1].toString();
                result[0] = GenericTranslator.toTO(systemEJB.getBr(
                        id, languageCode), BrTO.class);
            }
        });

        return (BrTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#saveBr(org.sola.services.ejb.system.repository.entities.Br)
     * SystemEJB.saveBr}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     * @throws SOLAValidationFault
     */
    @WebMethod(operationName = "SaveBr")
    public BrTO SaveBr(@WebParam(name = "br") BrTO br)
            throws SOLAFault, UnhandledFault, SOLAAccessFault, OptimisticLockingFault, SOLAValidationFault {
        final Object[] params = {br};
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                BrTO brTO = (BrTO) params[0];
                Br br = systemEJB.getBr(brTO.getId(), null);
                result[0] = GenericTranslator.toTO(
                        systemEJB.saveBr(GenericTranslator.fromTO(brTO, Br.class, br)),
                        BrTO.class);
            }
        });

        return (BrTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getAllSettings()
     * SystemEJB.getAllSettings}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAllSettings")
    public List<SettingTO> GetAllSettings()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        systemEJB.getAllSettings(), SettingTO.class);
            }
        });

        return (List<SettingTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.system.businesslogic.SystemEJB#getSetting()
     * SystemEJB.getSetting}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSetting")
    public String GetSetting(@WebParam(name = "name") String name,
            @WebParam(name = "defaultValue") String defaultValue)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String[] params = {name, defaultValue};
        final String[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = systemEJB.getSetting(params[0], params[1]);
            }
        });

        return result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#consolidationExtract()
     * AdminEJB.consolidationExtract}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "ConsolidationExtract")
    public String ConsolidationExtract(
            @WebParam(name = "processName") final boolean generateConsolidationSchema,
            @WebParam(name = "everything") final boolean everything,
            @WebParam(name = "password") final boolean dumpToFile)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = adminEJB.consolidationExtract(generateConsolidationSchema, everything, dumpToFile);
            }
        });

        return (String) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#consolidationConsolidate()
     * AdminEJB.consolidationConsolidate}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "ConsolidationConsolidate")
    public String ConsolidationConsolidate(
            @WebParam(name = "processName") final String extractedFile,
            @WebParam(name = "languageCode") final boolean mergeConsolidationSchema
            )
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};
        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = adminEJB.consolidationConsolidate(extractedFile, mergeConsolidationSchema);
            }
        });

        return (String) result[0];
    }

    /**
     * Returns the configuration information for the PanelLauncher
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPanelLauncherConfiguration")
    public List<ConfigPanelLauncherTO> GetPanelLauncherConfiguration()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        systemEJB.getCodeEntityList(ConfigPanelLauncher.class), ConfigPanelLauncherTO.class);
            }
        });

        return (List<ConfigPanelLauncherTO>) result[0];
    }
    
        /**
     * Returns the configuration information for the PanelLauncherGroup
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPanelLauncherGroups")
    public List<PanelLauncherGroupTO> GetPanelLauncherGroups()
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        systemEJB.getCodeEntityList(PanelLauncherGroup.class), PanelLauncherGroupTO.class);
            }
        });

        return (List<PanelLauncherGroupTO>) result[0];
    }
    
    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#startProcessProgressUsingBr(String, int)
     * AdminEJB.startProcessProgressUsingBr}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "StartProcessProgress")
    public void StartProcessProgress(
            @WebParam(name = "processName") final String processName,
            @WebParam(name = "maximumValue") final int maximumValue)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                adminEJB.startProcessProgress(processName, maximumValue);
            }
        });
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#startProcessProgressUsingBr(String, String)
     * AdminEJB.startProcessProgressUsingBr}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "StartProcessProgressUsingBr")
    public void StartProcessProgressUsingBr(
            @WebParam(name = "processName") final String processName,
            @WebParam(name = "brNameToGenerateMaximumValue") final String brNameToGenerateMaximumValue)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                adminEJB.startProcessProgressUsingBr(processName, brNameToGenerateMaximumValue);
            }
        });
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getProcessProgress()
     * AdminEJB.getProcessProgress}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetProcessProgress")
    public Integer GetProcessProgress(
            @WebParam(name = "processName") final String processName,
            @WebParam(name = "inPercentage") final Boolean inPercentage)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = adminEJB.getProcessProgress(processName, inPercentage);
            }
        });

        return Integer.parseInt(result[0].toString());
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#setProcessProgress(String, int)
     * AdminEJB.setProcessProgress}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SetProcessProgress")
    public void SetProcessProgress(
            @WebParam(name = "processName") final String processName,
            @WebParam(name = "progressValue") final int progressValue)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                adminEJB.setProcessProgress(processName, progressValue);
            }
        });
    }

    /**
     * See {@linkplain org.sola.services.ejbs.admin.businesslogic.AdminEJB#getProcessLog(String)
     * AdminEJB.getProcessLog}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetProcessLog")
    public String GetProcessLog(
            @WebParam(name = "processName") final String processName)
            throws SOLAValidationFault, OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {
            @Override
            public void run() {
                result[0] = adminEJB.getProcessLog(processName);
            }
        });

        return result[0].toString();
    }
   /**
     * Updates the security classifications for a list of entities and
     * identified by the entityTable and entity ids
     *
     * @param entityIds The ids of the entities to update
     * @param entityTable Enumeration indicating the entity table to update
     * @param classificationCode The new classification code to assign to the
     * entities
     * @param redactCode The new redactCode to assign to the entities
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     * @throws OptimisticLockingFault
     */
    @WebMethod(operationName = "SaveSecurityClassifications")
    public boolean SaveSecurityClassifications(
            @WebParam(name = "entityIds") final List<String> entityIds,
            @WebParam(name = "entityTable") final EntityTable entityTable,
            @WebParam(name = "classificationCode") final String classificationCode,
            @WebParam(name = "redactCode") final String redactCode)
            throws OptimisticLockingFault, SOLAFault, UnhandledFault, SOLAAccessFault {

        runUpdate(wsContext, new Runnable() {
            @Override
            public void run() {
                adminEJB.saveSecurityClassifications(entityIds, entityTable,
                        classificationCode, redactCode);
            }
        });

        return true;
    }
}

