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
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.sola.services.boundary.wsclients.AdministrativeClient;
import org.sola.webservices.administrative.*;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.administrative.*;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.administrative.Administrative} interface.
 * Uses the {@linkplain MockServiceManager} to obtain the appropriate mock
 * response for each web method.
 * <p>
 * Each method mocked by this class has a public constant defined that can be
 * used to reference a mock response object from the
 * {@linkplain MockServiceManager}. To set a response object for a web method,
 * use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from
 * {@linkplain org.sola.services.boundary.wsclients.AdministrativeClient}.</p>
 *
 * @see MockAdministrativeClient
 * @see AdministrativeClient
 * @see MockServiceManager
 * @see MockResponse
 *
 */
public class MockAdministrativePort implements Administrative {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type.
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
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
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
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
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
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionUpdate(java.lang.Exception) processExceptionUpdate}
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
     * Response Key = AdministrativeClient.CHECK_CONNECTION
     *
     * @return default = true
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(AdministrativeClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = AdministrativeClient.CREATE_BA_UNIT
     *
     * @return default = baUnitTO param
     */
    @Override
    public BaUnitTO createBaUnit(String serviceId, BaUnitTO baUnitTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        BaUnitTO defaultResponse = baUnitTO;
        try {
            return getManager().getResponse(AdministrativeClient.CREATE_BA_UNIT,
                    BaUnitTO.class, defaultResponse, serviceId, baUnitTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.CREATE_BA_UNIT_AREA
     *
     * @return default = baUnitTO param
     */
    @Override
    public BaUnitAreaTO createBaUnitArea(String baUnitId, BaUnitAreaTO baUnitAreaTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        BaUnitAreaTO defaultResponse = baUnitAreaTO;
        try {
            return getManager().getResponse(AdministrativeClient.CREATE_BA_UNIT_AREA,
                    BaUnitAreaTO.class, defaultResponse, baUnitId, baUnitAreaTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.SAVE_BA_UNIT
     *
     * @return default = baUnitTO param
     */
    @Override
    public BaUnitTO saveBaUnit(String serviceId, BaUnitTO baUnitTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        BaUnitTO defaultResponse = baUnitTO;
        try {
            return getManager().getResponse(AdministrativeClient.SAVE_BA_UNIT,
                    BaUnitTO.class, defaultResponse, serviceId, baUnitTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.GET_BA_UNITS_BY_SERVICE_ID
     *
     * @return default = new ArrayList<BaUnitTO>()
     */
    @Override
    public List<BaUnitTO> getBaUnitsByServiceId(String serviceId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<BaUnitTO> defaultResponse = new ArrayList<BaUnitTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_BA_UNITS_BY_SERVICE_ID,
                    List.class, defaultResponse, serviceId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.GET_BA_UNIT_BY_CODE
     *
     * @return default = new BaUnitTO()
     */
    @Override
    public BaUnitTO getBaUnitByCode(String nameFirstpart, String nameLastpart) throws SOLAFault, UnhandledFault {
        BaUnitTO defaultResponse = new BaUnitTO();
        try {
            return getManager().getResponse(AdministrativeClient.GET_BA_UNIT_BY_CODE,
                    BaUnitTO.class, defaultResponse, nameFirstpart, nameLastpart);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.GET_BA_UNIT_BY_ID
     *
     * @return default = new BaUnitTO()
     */
    @Override
    public BaUnitTO getBaUnitById(String id) throws SOLAFault, UnhandledFault {
        BaUnitTO defaultResponse = new BaUnitTO();
        try {
            return getManager().getResponse(AdministrativeClient.GET_BA_UNIT_BY_ID,
                    BaUnitTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.CANCEL_BA_UNIT_TERMINIATION
     *
     * @return default = new BaUnitTO()
     */
    @Override
    public BaUnitTO cancelBaUnitTermination(String baUnitId)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        BaUnitTO defaultResponse = new BaUnitTO();
        try {
            return getManager().getResponse(AdministrativeClient.CANCEL_BA_UNIT_TERMINIATION,
                    BaUnitTO.class, defaultResponse, baUnitId);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.TERMINATE_BA_UNIT
     *
     * @return default = new BaUnitTO()
     */
    @Override
    public BaUnitTO terminateBaUnit(String baUnitId, String serviceId)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        BaUnitTO defaultResponse = new BaUnitTO();
        try {
            return getManager().getResponse(AdministrativeClient.TERMINATE_BA_UNIT,
                    BaUnitTO.class, defaultResponse, baUnitId, serviceId);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.GET_BA_UNIT_AREAS
     *
     * @return default = new BaUnitAreaTO()
     */
    @Override
    public BaUnitAreaTO getBaUnitAreas(String baUnitId) throws SOLAFault, UnhandledFault {
        BaUnitAreaTO defaultResponse = new BaUnitAreaTO();
        try {
            return getManager().getResponse(AdministrativeClient.GET_BA_UNIT_AREAS,
                    BaUnitAreaTO.class, defaultResponse, baUnitId);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.GET_BA_UNIT_WITH_CAD_OBJECT
     *
     * @return default = new BaUnitTO()
     */
    @Override
    public BaUnitTO getBaUnitWithCadObject(String nameFirstPart, String nameLastPart, String colist) throws SOLAFault, UnhandledFault {
        BaUnitTO defaultResponse = new BaUnitTO();
        try {
            return getManager().getResponse(AdministrativeClient.GET_BA_UNIT_WITH_CAD_OBJECT,
                    BaUnitTO.class, defaultResponse, nameFirstPart, nameLastPart, colist);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_BY_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<SysRegPubDisParcelNameTO> getSysRegPubDisParcelNameByLocation(String searchString, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegPubDisParcelNameTO> defaultResponse = new ArrayList<SysRegPubDisParcelNameTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_REGIST_LISTING,
                    List.class, defaultResponse, searchString, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_BY_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<SysRegPubDisOwnerNameTO> getSysRegPubDisOwnerNameByLocation(String searchString, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegPubDisOwnerNameTO> defaultResponse = new ArrayList<SysRegPubDisOwnerNameTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_OWNER_LISTING,
                    List.class, defaultResponse, searchString, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_BY_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<SysRegPubDisStateLandTO> getSysRegPubDisStateLandByLocation(String searchString, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegPubDisStateLandTO> defaultResponse = new ArrayList<SysRegPubDisStateLandTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_STATELAND_LISTING,
                    List.class, defaultResponse, searchString, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CaseManagementClient.APPLICATION_ACTION_WITHDRAW
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> publicDisplay(String params, String languageCode)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(AdministrativeClient.PUBLIC_DIPLAY,
                    List.class, defaultResponse, params, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    @Override
    public List<SysRegManagementTO> getSysRegManagement(SysRegManagementParamsTO sysRegManagementParamsTO, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegManagementTO> defaultResponse = new ArrayList<SysRegManagementTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_MANAGEMENT,
                    List.class, defaultResponse, sysRegManagementParamsTO, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public List<SysRegStatusTO> getSysRegStatus(SysRegManagementParamsTO sysRegManagementParamsTO, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegStatusTO> defaultResponse = new ArrayList<SysRegStatusTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_STATUS,
                    List.class, defaultResponse, sysRegManagementParamsTO, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public List<SysRegGenderTO> getSysRegGender(String params, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegGenderTO> defaultResponse = new ArrayList<SysRegGenderTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_GENDER,
                    List.class, defaultResponse, params, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public List<SysRegProgressTO> getSysRegProgress(SysRegManagementParamsTO sysRegManagementParamsTO, String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SysRegProgressTO> defaultResponse = new ArrayList<SysRegProgressTO>();
        try {
            return getManager().getResponse(AdministrativeClient.GET_SYS_REG_PROGRESS,
                    List.class, defaultResponse, sysRegManagementParamsTO, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.saveNotation
     *
     * @return default = notationTO param
     */
    @Override
    public BaUnitNotationTO saveNotation(BaUnitNotationTO notationTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        BaUnitNotationTO defaultResponse = notationTO;
        try {
            return getManager().getResponse(AdministrativeClient.SAVE_NOTATION,
                    BaUnitNotationTO.class, defaultResponse, notationTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = AdministrativeClient.assignTeam
     *
     */
    @Override
    public void assignTeam(List<BaUnitBasicTO> properties, String teamId)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        try {
            getManager().getResponse(AdministrativeClient.ASSIGN_TEAM,
                    BaUnitNotationTO.class, null, properties, teamId);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
        }
    }

}
