/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO). All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this list of conditions
 * and the following disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice,this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.wsclients;

import java.util.Date;
import java.util.List;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.administrative.*;
import org.sola.webservices.transferobjects.search.RightsExportParamsTO;
import org.sola.webservices.transferobjects.search.RightsExportResultTO;
/**
 * Interface for the Administrative Service. Implemented by {@linkplain AdministrativeClientImpl}.
 * To obtain a reference to the Administrative Service, use {@linkplain WSManager#getAdministrative()}
 *
 * @see AdministrativeClientImpl
 * @see WSManager#getAdministrative()
 */
public interface AdministrativeClient extends AbstractWSClient {

    /**
     * Administrative. - Service name prefix for the Administrative Web Service
     */
    public static final String SERVICE_NAME = "Administrative.";
    /**
     * Administrative.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * Administrative.createBaUnit - Identifier for the createBaUnit method
     */
    public static final String CREATE_BA_UNIT = SERVICE_NAME + "createBaUnit";
   
    /**
     * Administrative.createBaUnitArea - Identifier for the createBaUnitArea method
     */
    public static final String CREATE_BA_UNIT_AREA = SERVICE_NAME + "createBaUnitArea";
   
    /**
     * Administrative.saveBaUnit - Identifier for the saveBaUnit method
     */
    public static final String SAVE_BA_UNIT = SERVICE_NAME + "saveBaUnit";
    /**
     * Administrative.getBaUnitById - Identifier for the getBaUnitById method
     */
    public static final String GET_BA_UNIT_BY_ID = SERVICE_NAME + "getBaUnitById";
    /**
     * Administrative.getBaUnitByCode - Identifier for the getBaUnitByCode method
     */
    public static final String GET_BA_UNIT_BY_CODE = SERVICE_NAME + "getBaUnitByCode";
    /**
     * Administrative.getBaUnitsByServiceId - Identifier for the getBaUnitsByServiceId method
     */
    public static final String GET_BA_UNITS_BY_SERVICE_ID = SERVICE_NAME + "getBaUnitsByServiceId";
    /**
     * Administrative.cancelBaUnitTermination - Identifier for the cancelBaUnitTermination method
     */
    public static final String CANCEL_BA_UNIT_TERMINIATION = SERVICE_NAME + "cancelBaUnitTermination";
    /**
     * Administrative.terminateBaUnit - Identifier for the terminateBaUnit method
     */
    public static final String TERMINATE_BA_UNIT = SERVICE_NAME + "terminateBaUnit";
    
     /**
     * Administrative.getBaUnitAreas - Identifier for the getBaUnitAreas method
     */
    public static final String GET_BA_UNIT_AREAS = SERVICE_NAME + "getBaUnitAreas";
    
     /**
     * Administrative.getBaUnitWithCadObject - Identifier for the getBaUnitWithCadObject method
     */
    public static final String GET_BA_UNIT_WITH_CAD_OBJECT = SERVICE_NAME + "getBaUnitWithCadObject";
   
      /**
     * Administrative.getCadastreObjects - Identifier for the getCadastreObjects
     * method
     */
    public static final String GET_SYS_REG_REGIST_LISTING = SERVICE_NAME + "getSysRegPubDisParcelNameByLocation";
    /**
     * Administrative.getCadastreObjects - Identifier for the getCadastreObjects
     * method
     */
    public static final String GET_SYS_REG_OWNER_LISTING = SERVICE_NAME + "getSysRegPubDisOwnerNameByLocation";
    /**
     * Administrative.getCadastreObjects - Identifier for the getCadastreObjects
     * method
     */
    public static final String GET_SYS_REG_STATELAND_LISTING = SERVICE_NAME + "getSysRegPubDisStateLandByLocation";
    /**
     * Administrative.getCadastreObjects - Identifier for the getCadastreObjects
     * method
     */
    public static final String PUBLIC_DIPLAY = SERVICE_NAME + "publicDisplay";
    
    public static final String GET_SYS_REG_MANAGEMENT = SERVICE_NAME + "getSysRegManagement";
    
    public static final String GET_SYS_REG_STATUS = SERVICE_NAME + "getSysRegStatus";
    
    public static final String GET_SYS_REG_PROGRESS = SERVICE_NAME + "getSysRegProgress";
   
     /**
     * Creates a new BA Unit Area for a BaUnitId 
     *
     * <p>Requires the {@linkplain RolesConstants#ADMINISTRATIVE_BA_UNIT_SAVE} role.</p>
     *
     * @param baUnitId The identifier of the area the BA Unit is being created as part of
     * @param baUnitAreaTO The details of the BA Unit Area to create
     * @return The new BA Unit Area
     * org.sola.webservices.transferobjects.administrative.BaUnitAreaTO) createBaUnit
     * @throws WebServiceClientException
     */
    BaUnitAreaTO createBaUnitArea(String baUnitId, BaUnitAreaTO baUnitAreaTO) throws WebServiceClientException;

    
    
    /**
     * Creates a new BA Unit with a default status of pending and a default type of
     * basicPropertyUnit. Will also create a new Transaction record for the BA Unit if the Service
     * is not already associated to a Transaction.
     *
     * <p>Requires the {@linkplain RolesConstants#ADMINISTRATIVE_BA_UNIT_SAVE} role.</p>
     *
     * @param serviceId The identifier of the Service the BA Unit is being created as part of
     * @param baUnitTO The details of the BA Unit to create
     * @return The new BA Unit
     * @see #saveBaUnit(java.lang.String,
     * org.sola.webservices.transferobjects.administrative.BaUnitTO) saveBaUnit
     * @throws WebServiceClientException
     */
    BaUnitTO createBaUnit(String serviceId, BaUnitTO baUnitTO) throws WebServiceClientException;

    /**
     * Saves any updates to an existing BA Unit. Can also be used to create a new BA Unit, however
     * this method does not set any default values on the BA Unit like
     * {@linkplain #createBaUnit(java.lang.String, org.sola.webservices.transferobjects.administrative.BaUnitTO)
     * createBaUnit}. Will also create a new Transaction record for the BA Unit if the Service is
     * not already associated to a Transaction.
     *
     * <p>Requires the {@linkplain RolesConstants#ADMINISTRATIVE_BA_UNIT_SAVE} role</p>
     *
     * @param serviceId The identifier of the Service the BA Unit is being created as part of
     * @param baUnitTO The details of the BA Unit to create
     * @return The updated BA Unit
     * @throws WebServiceClientException
     */
    BaUnitTO saveBaUnit(String serviceId, BaUnitTO baUnitTO) throws WebServiceClientException;

    /**
     * Retrieves the BA Unit matching the supplied identifier.
     *
     * @param id The BA Unit identifier
     * @return The BA Unit details or null if the identifier is invalid.
     * @throws WebServiceClientException
     */
    BaUnitTO getBaUnitById(String id) throws WebServiceClientException;

    /**
     * Locates a BA Unit using by matching the first part and last part of the BA Unit name. First
     * part and last part must be an exact match.
     *
     * @param nameFirstpart The first part of the BA Unit name
     * @param nameLastpart The last part of the BA Unit name
     * @return The BA Unit matching the name
     * @throws WebServiceClientException
     */
    BaUnitTO getBaUnitByCode(String nameFirstpart, String nameLastpart)
            throws WebServiceClientException;

    /**
     * Retrieves the list of BA Unit associated with the specified Service.
     *
     * @param serviceId The Service identifier
     * @return The list of BA Unit associated with the service or an empty list if the service does
     * not have any BA Units associated with it.
     * @throws WebServiceClientException
     */
    List<BaUnitTO> getBaUnitsByServiceId(String serviceId) throws WebServiceClientException;

    /**
     * Reverses the cancellation / termination of a BA Unit by removing the BA Unit Target created
     * by {@linkplain #terminateBaUnit(java.lang.String, java.lang.String) terminateBaUnit}.
     * <p>Requires the {@linkplain RolesConstants#ADMINISTRATIVE_BA_UNIT_SAVE} role.</p>
     *
     * @param baUnitId The identifier of the BA Unit to reverse the cancellation for.
     * @return The details of the BA Unit that has had its termination canceled.
     * @throws WebServiceClientException
     */
    BaUnitTO cancelBaUnitTermination(String baUnitId) throws WebServiceClientException;

    /**
     * Identifies a BA Unit as subject to cancellation / termination by linking the BA Unit to a
     * Transaction via the administrative.ba_unit_target association. The BA Unit is not canceled /
     * terminated until the application canceling the BA Unit is approved.
     *
     * <p>Requires the {@linkplain RolesConstants#ADMINISTRATIVE_BA_UNIT_SAVE} role.</p>
     *
     * @param baUnitId The identifier of the BA Unit to be canceled / terminated
     * @param serviceId The identifier of the service that is canceling / terminating the BA Unit
     * @return The BA Unit that will be canceled / terminated.
     * @throws WebServiceClientException
     */
    BaUnitTO terminateBaUnit(String baUnitId, String serviceId) throws WebServiceClientException;
    
     /**
     * Retrieves the list of BA Unit Areas associated with the specified BA Unit Id.
     *
     * @param baUnitId The Service identifier
     * @return The list of BA Unit associated with the service or an empty list if the service does
     * not have any BA Units associated with it.
     * @throws WebServiceClientException
     */
    BaUnitAreaTO getBaUnitAreas(String baUnitId) throws WebServiceClientException;

    /**
     * Retrieves the BA Unit matching the supplied identifier.
     *
     * @param id The BA Unit identifier
     * * @param coluist list of cadastre objects
     * @return The BA Unit details or null if the identifier is invalid.
     * @throws WebServiceClientException
     */
    BaUnitTO getBaUnitWithCadObject(String nameFirstPart, String nameLastPart, String colist ) throws WebServiceClientException;
    
     /**
     * Returns a list of cadastre objects that have a name last part that
     * matches the specified search string. This method supports partial matches
     * and is case insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<SysRegPubDisParcelNameTO> getSysRegPubDisParcelNameByLocation(String searchString)
            throws WebServiceClientException;

    /**
     * Returns a list of cadastre objects that have a name last part that
     * matches the specified search string. This method supports partial matches
     * and is case insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<SysRegPubDisOwnerNameTO> getSysRegPubDisOwnerNameByLocation(String searchString)
            throws WebServiceClientException;

    /**
     * Returns a list of cadastre objects that have a name last part that
     * matches the specified search string. This method supports partial matches
     * and is case insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<SysRegPubDisStateLandTO> getSysRegPubDisStateLandByLocation(String searchString)
            throws WebServiceClientException;
    
    
     /* Verifies the brs
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<ValidationResult> publicDisplay(String params)
            throws WebServiceClientException;
    
    List<SysRegManagementTO> getSysRegManagement(SysRegManagementParamsTO sysRegManagementParamsTO)
            throws WebServiceClientException;

    List<SysRegStatusTO> getSysRegStatus(SysRegManagementParamsTO sysRegManagementParamsTO)
            throws WebServiceClientException;
    
    List<SysRegProgressTO> getSysRegProgress(SysRegManagementParamsTO sysRegManagementParamsTO)
            throws WebServiceClientException;
}
