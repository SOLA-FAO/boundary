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
import javax.xml.datatype.XMLGregorianCalendar;
import org.sola.common.ConfigConstants;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.admin.BrTO;
import org.sola.webservices.admin.ConfigPanelLauncherTO;
import org.sola.webservices.admin.LanguageTO;
import org.sola.webservices.admin.PanelLauncherGroupTO;
import org.sola.webservices.admin.SettingTO;
import org.sola.webservices.transferobjects.EntityTable;
import org.sola.webservices.transferobjects.security.GroupSummaryTO;
import org.sola.webservices.transferobjects.security.GroupTO;
import org.sola.webservices.transferobjects.security.RoleTO;
import org.sola.webservices.transferobjects.security.UserTO;

/**
 * Interface for the Admin Service. Implemented by {@linkplain AdminClientImpl}.
 * To obtain a reference to the Admin Service, use {@linkplain WSManager#getAdminService()}
 *
 * @see AdminClientImpl
 * @see WSManager#getAdminService()
 */
public interface AdminClient extends AbstractWSClient {

    /**
     * Admin. - Service name prefix for the Admin Web Service
     */
    public static final String SERVICE_NAME = "Admin.";
    /**
     * Admin.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * Admin.getCurrentUser - Identifier for the getCurrentUser method
     */
    public static final String GET_CURRENT_USER = SERVICE_NAME + "getCurrentUser";
    /**
     * Admin.getCurrentUserRoles - Identifier for the getCurrentUserRoles method
     */
    public static final String GET_CURRENT_USER_ROLES = SERVICE_NAME + "getCurrentUserRoles";
    /**
     * Admin.getGroups - Identifier for the getGroups method
     */
    public static final String GET_GROUPS = SERVICE_NAME + "getGroups";
    /**
     * Admin.getGroupsSummary - Identifier for the getGroupsSummary method
     */
    public static final String GET_GROUPS_SUMMARY = SERVICE_NAME + "getGroupsSummary";
    /**
     * Admin.getGroup - Identifier for the getGroup method
     */
    public static final String GET_GROUP = SERVICE_NAME + "getGroup";
    /**
     * Admin.getUser - Identifier for the getUser method
     */
    public static final String GET_USER = SERVICE_NAME + "getUser";
    /**
     * Admin.saveUser - Identifier for the saveUser method
     */
    public static final String SAVE_USER = SERVICE_NAME + "saveUser";
    /**
     * Admin.saveGroup - Identifier for the saveGroup method
     */
    public static final String SAVE_GROUP = SERVICE_NAME + "saveGroup";
    /**
     * Admin.saveRole - Identifier for the saveRole method
     */
    public static final String SAVE_ROLE = SERVICE_NAME + "saveRole";
    /**
     * Admin.getRoles - Identifier for the getRoles method
     */
    public static final String GET_ROLES = SERVICE_NAME + "getRoles";
    /**
     * Admin.changePassword - Identifier for the changePassword method
     */
    public static final String CHANGE_PASSWORD = SERVICE_NAME + "changePassword";
    /**
     * Admin.getBr - Identifier for the getBr method
     */
    public static final String GET_BR = SERVICE_NAME + "getBr";
    /**
     * Admin.getLanguages - Identifier for the getLanguages method
     */
    public static final String GET_LANGUAGES = SERVICE_NAME + "getLanguages";
    /**
     * Admin.isUserAdmin - Identifier for the isUserAdmin method
     */
    public static final String IS_USER_ADMIN = SERVICE_NAME + "isUserAdmin";
    /**
     * Admin.saveBr - Identifier for the saveBr method
     */
    public static final String SAVE_BR = SERVICE_NAME + "saveBr";
    /**
     * Admin.getAllSettings - Identifier for the getAllSettings method
     */
    public static final String GET_ALL_SETTINGS = SERVICE_NAME + "getAllSettings";
    /**
     * Admin.getSetting - Identifier for the getSetting method
     */
    public static final String GET_SETTING = SERVICE_NAME + "getSetting";
    /**
     * Admin.consolidationExtract - Identifier for the consolidationExtract
     * method
     */
    public static final String CONSOLIDATION_EXTRACT = SERVICE_NAME + "consolidationExtract";
    /**
     * Admin.consolidationConsolidate - Identifier for the
     * consolidationConsolidate method
     */
    public static final String CONSOLIDATION_CONSOLIDATE = SERVICE_NAME + "consolidationConsolidate";
    /**
     * Admin.getPanelLauncherConfiguration - Identifier for the
     * getPanelLauncherConfiguration method
     */
    public static final String GET_PANEL_LAUNCHER_CONFIG = SERVICE_NAME + "getPanelLauncherConfiguration";
    /**
     * Admin.getPanelLauncherGroups - Identifier for the getPanelLauncherGroups
     * method
     */
    public static final String GET_PANEL_LAUNCHER_GROUPS = SERVICE_NAME + "getPanelLauncherGroups";
    /**
     * Admin.startProcessProgress - Identifier for the startProcessProgress
     * method
     */
    public static final String PROCESS_PROGRESS_START = SERVICE_NAME + "startProcessProgress";
    /**
     * Admin.startProcessProgressUsingBr - Identifier for the
     * startProcessProgressUsingBr method
     */
    public static final String PROCESS_PROGRESS_USING_BR_START = SERVICE_NAME + "startProcessProgressUsingBr";
    /**
     * Admin.getProcessProgress - Identifier for the getProcessProgress method
     */
    public static final String PROCESS_PROGRESS_GET = SERVICE_NAME + "getProcessProgress";
    /**
     * Admin.setProcessProgress - Identifier for the setProcessProgress method
     */
    public static final String PROCESS_PROGRESS_SET = SERVICE_NAME + "setProcessProgress";
    /**
     * Admin.getProcessLog - Identifier for the getProcessLog method
     */
    public static final String PROCESS_LOG_GET = SERVICE_NAME + "getProcessLog";
    /**
     * Admin.saveSecurityClassifications - Identifier for the
     * saveSecurityClassifications method
     */
    public static final String SAVE_SECURITY_CLASSIFICATIONS = SERVICE_NAME + "saveSecurityClassifications";
    
    /**
     * Admin.flushCache - Identifier for the flushCache method
     */
    public static final String FLUSH_CACHE = SERVICE_NAME + "flushCache";

    /**
     * Returns the details for the currently authenticated user. <p>No role is
     * required to execute this method.</p>
     *
     * @throws WebServiceClientException
     */
    UserTO getCurrentUser() throws WebServiceClientException;

    /**
     * Returns the list of all user groups supported by SOLA. <p>Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role.</p>
     *
     * @throws WebServiceClientException
     */
    List<GroupTO> getGroups() throws WebServiceClientException;

    /**
     * Returns a summary list of all user groups supported by SOLA. <p>Requires
     * the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY} role.</p>
     *
     * @throws WebServiceClientException
     */
    List<GroupSummaryTO> getGroupsSummary() throws WebServiceClientException;

    /**
     * Returns the details for the specified group. <p>Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role.</p>
     *
     * @param groupId The identifier of the group to retrieve from the SOLA
     * database
     * @throws WebServiceClientException
     */
    GroupTO getGroup(String groupId) throws WebServiceClientException;

    /**
     * Returns the details of the user with the specified user name. <p>Requires
     * the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY} role.</p>
     *
     * @param userName The user name of the user to search for.
     * @throws WebServiceClientException
     */
    UserTO getUser(String userName) throws WebServiceClientException;

    /**
     * Can be used to create a new user or save any updates to the details of an
     * existing user. Cannot be used to change the users password. This can only
     * be done using
     * {@linkplain #changePassword(java.lang.String, java.lang.String) changePassword}
     * method. <p> Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role. </p>
     *
     * @param userTO The details of the user to save
     * @return The user details after the save is completed
     * @throws WebServiceClientException
     */
    UserTO saveUser(UserTO userTO) throws WebServiceClientException;

    /**
     * Can be used to create a new user group or save any updates to the details
     * of an existing user group. <p> Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role. </p>
     *
     * @param groupTO The details of the user group to save
     * @return The user group after the save is completed
     * @throws WebServiceClientException
     */
    GroupTO saveGroup(GroupTO groupTO) throws WebServiceClientException;

    /**
     * Can be used to create a new security role or save any updates to the
     * details of an existing security role. <p> Note that security roles are
     * linked to the SOLA code base. Adding a new role also requires updating
     * code before SOLA will recognize the role</p> <p> Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role. </p>
     *
     * @param roleTO The details of the security role to save
     * @return The security role after the save is completed
     * @throws WebServiceClientException
     */
    RoleTO saveRole(RoleTO roleTO) throws WebServiceClientException;

    /**
     * Returns the list of all security roles in SOLA. <p>Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role.</p>
     *
     * @throws WebServiceClientException
     */
    List<RoleTO> getRoles() throws WebServiceClientException;

    /**
     * Returns the list of all security roles assigned to the current user.
     * <p>No role is required to execute this method.</p>
     *
     * @throws WebServiceClientException
     */
    List<RoleTO> getCurrentUserRoles() throws WebServiceClientException;

    /**
     * Allows the users password to be changed. <p> Requires the {@linkplain RolesConstants.ADMIN_CHANGE_PASSWORD}
     * role. </p>
     *
     * @param userName The username to change the password for
     * @param password The users new password
     * @return true if the change is successful.
     * @throws WebServiceClientException
     */
    boolean changePassword(String userName, String password) throws WebServiceClientException;

    /**
     * Checks if the current user has been assigned one or more of the
     * {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY},
     * {@linkplain RolesConstants.ADMIN_MANAGE_REFDATA} or {@linkplain RolesConstants.ADMIN_MANAGE_SETTINGS}
     * security roles. <p> No role is required to execute this method.</p>
     *
     * @return true if the user is assigned one of the Admin security roles
     * @throws WebServiceClientException
     */
    boolean isUserAdmin() throws WebServiceClientException;

    /**
     * Returns the list of languages supported by SOLA for localization in
     * priority order. The display value for each language is based on the
     * default locale of the client application. <p>No role is required to
     * execute this method.</p>
     *
     * @return See description
     * @throws WebServiceClientException
     */
    List<LanguageTO> getLanguages() throws WebServiceClientException;

    /**
     * Returns the list of languages supported by SOLA for localization in
     * priority order. <p>No role is required to execute this method.</p>
     *
     * @param lang The language code to use to localize the display value for
     * each language.
     * @throws WebServiceClientException
     */
    List<LanguageTO> getLanguages(String lang) throws WebServiceClientException;

    /**
     * Returns the SOLA business rule matching the id. <p>Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role.</p>
     *
     * @param id Identifier for the business rule to return
     * @param lang The language code to use to localize the display value for
     * each Br.
     * @throws WebServiceClientException
     */
    BrTO getBr(String id, String lang) throws WebServiceClientException;

    /**
     * Returns the SOLA business rule matching the id. The display value for
     * each Br is based on the default locale for the client application.
     * <p>Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role.</p>
     *
     * @param id Identifier for the business rule to return
     * @throws WebServiceClientException
     */
    BrTO getBr(String id) throws WebServiceClientException;

    /**
     * Can be used to create a new business rule or save any updates to the
     * details of an existing business role. <p> Requires the {@linkplain RolesConstants.ADMIN_MANAGE_SECURITY}
     * role. </p>
     *
     * @param brTO The business rule to save.
     * @return The updated/new business rule.
     * @throws WebServiceClientException
     */
    BrTO saveBr(BrTO brTO) throws WebServiceClientException;

    /**
     * Returns all configuration settings in the system.setting table.<p>No role
     * is required to execute this method.</p>
     *
     * @return The settings from the system.setting table
     * @throws WebServiceClientException
     */
    List<SettingTO> getAllSettings() throws WebServiceClientException;

    /**
     * Retrieves the value for the named setting. Constants for each setting are
     * available in
     * {@linkplain  ConfigConstants}. If the setting does not exist, the default
     * value for the setting is returned. <p>No role is required to execute this
     * method.</p>
     *
     * @param name The name of the setting to retrieve
     * @param defaultValue The default value for the setting if it no override
     * value is recorded in the system.settings table.
     * @return The override value for the setting or the defaultValue.
     */
    String getSetting(String name, String defaultValue) throws WebServiceClientException;

    /**
     * It extracts the records from the database for the consolidation process.
     *
     * @param generateConsolidationSchema
     * @param everything
     * @param dumpToFile
     * @return
     * @throws WebServiceClientException
     */
    String consolidationExtract(boolean generateConsolidationSchema,
            boolean everything, boolean dumpToFile) throws WebServiceClientException;

    /**
     * It consolidate the records extracted from another system.
     *
     * @param extractedFile
     * @param mergeConsolidationSchema
     * @return
     * @throws WebServiceClientException
     */
    String consolidationConsolidate(String extractedFile, boolean mergeConsolidationSchema) throws WebServiceClientException;

    /**
     * Returns the configuration information for the PanelLauncher
     *
     * @return The settings from the system.config_panel_launcher table
     * @throws WebServiceClientException
     */
    List<ConfigPanelLauncherTO> getPanelLauncherConfiguration() throws WebServiceClientException;

    /**
     * Returns the Panel Launcher Groups
     *
     * @return The settings from the system.panel_launcher_group table
     * @throws WebServiceClientException
     */
    List<PanelLauncherGroupTO> getPanelLauncherGroups() throws WebServiceClientException;

    /**
     * It starts a process progress in the server.
     *
     * @param processName The name of the process to identify. If it is already
     * found it will be overridden.
     * @param maximumValue Maximum value the progress can get.
     *
     * @throws WebServiceClientException
     */
    void startProcessProgress(String processName, int maximumValue) throws WebServiceClientException;

    /**
     * It starts a process progress in the server.
     *
     * @param processName The name of the process to identify. If it is already
     * found it will be overridden.
     * @param brNameToGenerateMaximumValue The BR name that will generate the
     * maximum value the progress can get.
     *
     * @throws WebServiceClientException
     */
    void startProcessProgressUsingBr(String processName, String brNameToGenerateMaximumValue) throws WebServiceClientException;

    /**
     * Gets the value of the process progress.
     *
     * @param processName The name of the process.
     * @param inPercentage True: The value in percentage, otherwise the absolute
     * value.
     *
     * @return
     *
     * @throws WebServiceClientException
     */
    Integer getProcessProgress(String processName, Boolean inPercentage) throws WebServiceClientException;

    /**
     * Sets the process progress value.
     *
     * @param processName The name of the process.
     * @param progressValue The value to set.
     *
     * @throws WebServiceClientException
     */
    void setProcessProgress(String processName, int progressValue) throws WebServiceClientException;

    /**
     * Gets the process log.
     *
     * @param processName The name of the process.
     * @param fromTime From the moment the log has to be checked.
     * @param toTime Until the moment the log has to be checked. If not present,
     * the current time is used.
     *
     * @return
     *
     * @throws WebServiceClientException
     */
    String getProcessLog(String processName) throws WebServiceClientException;

    /**
     * Updates the security classifications for a list of entities and
     * identified by the entityTable and entity ids <p> Requires the {@linkplain RolesConstants#CLASSIFICATION_CHANGE_CLASS}
     * role. </p>
     *
     * @param entityIds The ids of the entities to update
     * @param entityTable Enumeration indicating the entity table to update
     * @param classificationCode The new classification code to assign to the
     * entities
     * @param redactCode The new redactCode to assign to the entities
     */
    boolean saveSecurityClassifications(List<String> entityIds, EntityTable entityTable,
            String classificationCode, String redactCode);
    
    /**
     * Clears / flushes the contents of the Repository Cache. Should be used if
     * the Administrator updates a reference code, setting or configuration
     * value directly in the database without using the SOLA Admin application.
     * <p>
     * Requires the {@linkplain RolesConstants#ADMIN_MANAGE_REFDATA} role. </p>
     */
    boolean flushCache() throws WebServiceClientException; 
}
