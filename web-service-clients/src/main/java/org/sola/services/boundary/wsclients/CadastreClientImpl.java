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
import org.sola.webservices.transferobjects.cadastre.CadastreObjectNodeTO;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreChangeTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreRedefinitionTO;

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
        final String inputService = SERVICE_NAME + "checkConnection";
        try {
            boolean result = getPort().checkConnection();
            return result;
       } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return  false;
        }
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectByParts(String searchString)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "GetCadastreObjectByParts";
        try {
            List<CadastreObjectTO> result = getPort().getCadastreObjectByParts(searchString);
            return result;
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }

    }

    @Override
    public CadastreObjectTO getCadastreObjectByPoint(double x, double y, int srid)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "GetCadastreObjectByPoint";
        try {
            return getPort().getCadastreObjectByPoint(x, y, srid);
         } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }

    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectsByBaUnit(String baUnitId)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getCadastreObjectsByBaUnit";
        try {
            return getPort().getCadastreObjectsByBaUnit(baUnitId);
         } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }

    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectsByService(String serviceId)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getCadastreObjectsByService";
        try {
            return getPort().getCadastreObjectsByService(serviceId);
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }
    }

    @Override
    public List<ValidationResult>  saveTransactionCadastreChange(
            TransactionCadastreChangeTO transactionCadastreChangeTO)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "saveCadastreChange";
        try {
            return getPort().saveCadastreChange(transactionCadastreChangeTO, this.getLanguageCode());
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }
    }
    
    @Override
    public TransactionCadastreChangeTO getTransactionCadastreChange(String serviceId)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getCadastreChange";
        try {
            return getPort().getCadastreChange(serviceId);
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjects(List<String> Ids)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getCadastreObjects";
        try {
            return getPort().getCadastreObjects(Ids);
         } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }

    }

    @Override
    public CadastreObjectNodeTO getCadastreObjectNode(
            double xMin, double yMin, double xMax, double yMax, int srid)
    throws WebServiceClientException{
        final String inputService = SERVICE_NAME + "getCadastreObjects";
        try {
            return getPort().getCadastreObjectNode(xMin, yMin, xMax, yMax, srid);
       } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
       }
    }

    @Override
    public CadastreObjectNodeTO getCadastreObjectNodePotential(
            double xMin, double yMin, double xMax, double yMax, int srid)
    throws WebServiceClientException{
        final String inputService = SERVICE_NAME + "getCadastreObjects";
        try {
            return getPort().getCadastreObjectNodePotential(xMin, yMin, xMax, yMax, srid);
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
       }
    }

    @Override
    public List<ValidationResult>  saveTransactionCadastreRedefinition(
            TransactionCadastreRedefinitionTO transactionTO)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "saveTransactionCadastreRedefinition";
        try {
            return getPort().saveCadastreRedefinition(transactionTO, this.getLanguageCode());
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
       }
    }

    @Override
    public TransactionCadastreRedefinitionTO getTransactionCadastreRedefinition(String serviceId)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getTransactionCadastreRedefinition";
        try {
            return getPort().getCadastreRedefinition(serviceId);
         } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
       }
    }
}