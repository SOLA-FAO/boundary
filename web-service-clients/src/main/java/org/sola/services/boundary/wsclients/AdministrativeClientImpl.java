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
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.administrative.Administrative;
import org.sola.webservices.administrative.AdministrativeService;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.administrative.*;
import org.sola.webservices.transferobjects.search.RightsExportParamsTO;
import org.sola.webservices.transferobjects.search.RightsExportResultTO;

/**
 * Implementation class for the {@linkplain AdministrativeClient} interface.
 */
public class AdministrativeClientImpl extends AbstractWSClientImpl
        implements AdministrativeClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/administrative";
    private static final String LOCAL_PART = "administrative-service";

    /**
     * Creates a web service client class for the web service hosted at the
     * specified URL
     *
     * @param url The location of the service WSDL
     */
    public AdministrativeClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected Administrative getPort() {
        return getPort(Administrative.class, AdministrativeService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = AdministrativeClient.CHECK_CONNECTION;
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
    public BaUnitTO createBaUnit(
            String serviceId, BaUnitTO baUnitTO) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.CREATE_BA_UNIT;
        try {
            beforeWebMethod(methodName, serviceId, baUnitTO);
            result = getPort().createBaUnit(serviceId, baUnitTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, baUnitTO);
        }
        return result;
    }

    @Override
    public BaUnitAreaTO createBaUnitArea(
            String baUnitId, BaUnitAreaTO baUnitAreaTO) throws WebServiceClientException {
        BaUnitAreaTO result = null;
        final String methodName = AdministrativeClient.CREATE_BA_UNIT_AREA;
        try {
            beforeWebMethod(methodName, baUnitId, baUnitAreaTO);
            result = getPort().createBaUnitArea(baUnitId, baUnitAreaTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, baUnitId, baUnitAreaTO);
        }
        return result;
    }

    @Override
    public BaUnitTO saveBaUnit(
            String serviceId, BaUnitTO baUnitTO) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.SAVE_BA_UNIT;
        try {
            beforeWebMethod(methodName, serviceId, baUnitTO);
            result = getPort().saveBaUnit(serviceId, baUnitTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId, baUnitTO);
        }
        return result;
    }

    @Override
    public BaUnitTO getBaUnitById(String id) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.GET_BA_UNIT_BY_ID;
        try {
            beforeWebMethod(methodName, id);
            result = getPort().getBaUnitById(id);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id);
        }
        return result;
    }

    @Override
    public BaUnitTO getBaUnitByCode(String nameFirstpart, String nameLastpart) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.GET_BA_UNIT_BY_CODE;
        try {
            beforeWebMethod(methodName, nameFirstpart, nameLastpart);
            result = getPort().getBaUnitByCode(nameFirstpart, nameLastpart);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, nameFirstpart, nameLastpart);
        }
        return result;
    }

    @Override
    public List<BaUnitTO> getBaUnitsByServiceId(String serviceId) throws WebServiceClientException {
        List<BaUnitTO> result = null;
        final String methodName = AdministrativeClient.GET_BA_UNITS_BY_SERVICE_ID;
        try {
            beforeWebMethod(methodName, serviceId);
            result = getPort().getBaUnitsByServiceId(serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId);
        }
        return result;
    }

    @Override
    public BaUnitTO cancelBaUnitTermination(String baUnitId) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.CANCEL_BA_UNIT_TERMINIATION;
        try {
            beforeWebMethod(methodName, baUnitId);
            result = getPort().cancelBaUnitTermination(baUnitId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, baUnitId);
        }
        return result;
    }

    @Override
    public BaUnitTO terminateBaUnit(String baUnitId, String serviceId) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.TERMINATE_BA_UNIT;
        try {
            beforeWebMethod(methodName, baUnitId, serviceId);
            result = getPort().terminateBaUnit(baUnitId, serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, baUnitId, serviceId);
        }
        return result;
    }

    @Override
    public BaUnitAreaTO getBaUnitAreas(String baUnitId) throws WebServiceClientException {
        BaUnitAreaTO result = null;
        final String methodName = AdministrativeClient.GET_BA_UNIT_AREAS;
        try {
            beforeWebMethod(methodName, baUnitId);
            result = getPort().getBaUnitAreas(baUnitId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, baUnitId);
        }
        return result;
    }

    @Override
    public BaUnitTO getBaUnitWithCadObject(String nameFirstPart, String nameLastPart, String colist) throws WebServiceClientException {
        BaUnitTO result = null;
        final String methodName = AdministrativeClient.GET_BA_UNIT_WITH_CAD_OBJECT;
        try {
            beforeWebMethod(methodName, nameFirstPart, nameLastPart, colist);
            result = getPort().getBaUnitWithCadObject(nameFirstPart, nameLastPart, colist);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, nameFirstPart, nameLastPart, colist);
        }
        return result;
    }

    @Override
    public List<SysRegPubDisParcelNameTO> getSysRegPubDisParcelNameByLocation(String searchString)
            throws WebServiceClientException {
        List<SysRegPubDisParcelNameTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_REGIST_LISTING;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, searchString, languageCode);
            result = getPort().getSysRegPubDisParcelNameByLocation(searchString, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString, languageCode);
        }
        return result;
    }

    @Override
    public List<SysRegPubDisOwnerNameTO> getSysRegPubDisOwnerNameByLocation(String searchString)
            throws WebServiceClientException {
        List<SysRegPubDisOwnerNameTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_OWNER_LISTING;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, searchString, languageCode);
            result = getPort().getSysRegPubDisOwnerNameByLocation(searchString, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString, languageCode);
        }
        return result;
    }

    @Override
    public List<SysRegPubDisStateLandTO> getSysRegPubDisStateLandByLocation(String searchString)
            throws WebServiceClientException {
        List<SysRegPubDisStateLandTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_STATELAND_LISTING;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, searchString, languageCode);
            result = getPort().getSysRegPubDisStateLandByLocation(searchString, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString, languageCode);
        }
        return result;
    }

    @Override
    public List<ValidationResult> publicDisplay(String params)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = AdministrativeClient.PUBLIC_DIPLAY;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, params, languageCode);
            result = getPort().publicDisplay(params, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, params, languageCode);
        }
        return result;
    }

    @Override
    public List<SysRegManagementTO> getSysRegManagement(SysRegManagementParamsTO sysRegManagementParamsTO)
            throws WebServiceClientException {
        List<SysRegManagementTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_MANAGEMENT;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, sysRegManagementParamsTO, languageCode);
            result = getPort().getSysRegManagement(sysRegManagementParamsTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sysRegManagementParamsTO, languageCode);
        }
        return result;
    }

    @Override
    public List<SysRegStatusTO> getSysRegStatus(SysRegManagementParamsTO sysRegManagementParamsTO)
            throws WebServiceClientException {
        List<SysRegStatusTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_STATUS;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, sysRegManagementParamsTO, languageCode);
            result = getPort().getSysRegStatus(sysRegManagementParamsTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sysRegManagementParamsTO, languageCode);
        }
        return result;
    }

    @Override
    public List<SysRegGenderTO> getSysRegGender(String params)
            throws WebServiceClientException {
        List<SysRegGenderTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_GENDER;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, params, languageCode);
            result = getPort().getSysRegGender(params, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, params, languageCode);
        }
        return result;
    }

    @Override
    public List<SysRegProgressTO> getSysRegProgress(SysRegManagementParamsTO sysRegManagementParamsTO)
            throws WebServiceClientException {
        List<SysRegProgressTO> result = null;
        final String methodName = AdministrativeClient.GET_SYS_REG_PROGRESS;
        String languageCode = getLanguageCode();
        try {
            beforeWebMethod(methodName, sysRegManagementParamsTO, languageCode);
            result = getPort().getSysRegProgress(sysRegManagementParamsTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, sysRegManagementParamsTO, languageCode);
        }
        return result;
    }

    @Override
    public BaUnitNotationTO saveNotation(BaUnitNotationTO notationTO) throws WebServiceClientException {
        BaUnitNotationTO result = null;
        final String methodName = AdministrativeClient.SAVE_NOTATION;
        try {
            beforeWebMethod(methodName, notationTO);
            result = getPort().saveNotation(notationTO);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, notationTO);
        }
        return result;
    }
}
