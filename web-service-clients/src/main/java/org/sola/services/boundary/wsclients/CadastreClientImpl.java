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
/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;
import org.sola.webservices.cadastre.Cadastre;
import org.sola.webservices.cadastre.CadastreService;
import org.sola.webservices.cadastre.OptimisticLockingFault;
import org.sola.webservices.cadastre.SOLAAccessFault;
import org.sola.webservices.cadastre.SOLAFault;
import org.sola.webservices.cadastre.SOLAValidationFault;
import org.sola.webservices.cadastre.UnhandledFault;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.cadastre.CadastreChangeTO;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectTO;

/**
 *
 * @author Manoku
 */
public class CadastreClientImpl extends AbstractWSClientImpl implements CadastreClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/cadastre";
    private static final String LOCAL_PART = "cadastre-service";
    private static final String SERVICE_NAME = "Cadastre.";

    public CadastreClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    private Cadastre getPort() {
        return getPort(Cadastre.class, CadastreService.class);
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
    public List<CadastreObjectTO> getCadastreObjectByParts(String searchString)
            throws WebServiceClientException {
        try {
            List<CadastreObjectTO> result = getPort().getCadastreObjectByParts(searchString);
            return result;
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "GetCadastreObjectByParts", t);
        }

    }

    @Override
    public CadastreObjectTO getCadastreObjectByPoint(double x, double y, int srid)
            throws WebServiceClientException {
        try {
            return getPort().getCadastreObjectByPoint(x, y, srid);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "GetCadastreObjectByPoint", t);
        }

    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectsByBaUnit(String baUnitId)
            throws WebServiceClientException {
        try {
            return getPort().getCadastreObjectsByBaUnit(baUnitId);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCadastreObjectsByBaUnit", t);
        }

    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectsByService(String serviceId)
            throws WebServiceClientException {
        try {
            return getPort().getCadastreObjectsByService(serviceId);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCadastreObjectsByService", t);
        }
    }

    @Override
    public List<ValidationResult>  saveCadastreChange(CadastreChangeTO cadastreChangeTO)
            throws WebServiceClientException {
        try {
            return getPort().saveCadastreChange(cadastreChangeTO, this.getLanguageCode());
        } catch (SOLAAccessFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.ACCESS_VIOLATION,
                    f.getFaultInfo());
        } catch (SOLAValidationFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SOLA_VALIDATION_FAILED,
                    f.getFaultInfo());
        } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "saveCadastreChange", t);
        }
    }
    
    @Override
    public CadastreChangeTO getCadastreChange(String serviceId)
            throws WebServiceClientException {
        try {
            return getPort().getCadastreChange(serviceId);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCadastreChange", t);
        }
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjects(List<String> Ids)
            throws WebServiceClientException {
        try {
            return getPort().getCadastreObjects(Ids);
        } catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getCadastreObjects", t);
        }

    }
}