/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
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
import org.sola.webservices.cadastre.Cadastre;
import org.sola.webservices.cadastre.CadastreService;
import org.sola.webservices.cadastre.NewCadastreObjectIdentifier;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectNodeTO;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectTO;
import org.sola.webservices.transferobjects.cadastre.SpatialValueAreaTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreChangeTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreRedefinitionTO;

/**
 * Implementation class for the {@linkplain CadastreClient} interface.
 */
public class CadastreClientImpl extends AbstractWSClientImpl implements CadastreClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/cadastre";
    private static final String LOCAL_PART = "cadastre-service";

    /**
     * Creates a web service client class for the web service hosted at the
     * specified URL
     *
     * @param url The location of the service WSDL
     */
    public CadastreClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected Cadastre getPort() {
        return getPort(Cadastre.class, CadastreService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = CadastreClient.CHECK_CONNECTION;
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
    public List<CadastreObjectTO> getCadastreObjectByParts(String searchString)
            throws WebServiceClientException {
        List<CadastreObjectTO> result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECT_BY_PARTS;
        try {
            beforeWebMethod(methodName, searchString);
            result = getPort().getCadastreObjectByParts(searchString);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString);
        }
        return result;
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectByAllParts(String searchString)
            throws WebServiceClientException {
        List<CadastreObjectTO> result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECT_BY_ALL_PARTS;
        try {
            beforeWebMethod(methodName, searchString);
            result = getPort().getCadastreObjectByAllParts(searchString);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, searchString);
        }
        return result;
    }

    @Override
    public CadastreObjectTO getCadastreObjectByPoint(
            double x, double y, int srid, String typeCode)
            throws WebServiceClientException {
        CadastreObjectTO result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECT_BY_POINT;
        try {
            beforeWebMethod(methodName, x, y, srid, typeCode);
            result = getPort().getCadastreObjectByPoint(x, y, srid, typeCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, x, y, srid);
        }
        return result;
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectsByBaUnit(String baUnitId)
            throws WebServiceClientException {
        List<CadastreObjectTO> result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECTS_BY_BA_UNIT;
        try {
            beforeWebMethod(methodName, baUnitId);
            result = getPort().getCadastreObjectsByBaUnit(baUnitId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, baUnitId);
        }
        return result;
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjectsByService(String serviceId)
            throws WebServiceClientException {
        List<CadastreObjectTO> result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECTS_BY_SERVICE;
        try {
            beforeWebMethod(methodName, serviceId);
            result = getPort().getCadastreObjectsByService(serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId);
        }
        return result;
    }

    @Override
    public List<ValidationResult> saveTransactionCadastreChange(
            TransactionCadastreChangeTO transactionCadastreChangeTO)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CadastreClient.SAVE_TRANSACTION_CADASTRE_CHANGE;
        String languageCode = this.getLanguageCode();
        try {
            beforeWebMethod(methodName, transactionCadastreChangeTO, languageCode);
            result = getPort().saveCadastreChange(transactionCadastreChangeTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, transactionCadastreChangeTO, languageCode);
        }
        return result;
    }

    @Override
    public TransactionCadastreChangeTO getTransactionCadastreChange(String serviceId)
            throws WebServiceClientException {
        TransactionCadastreChangeTO result = null;
        final String methodName = CadastreClient.GET_TRANSACTION_CADASTRE_CHANGE;
        try {
            beforeWebMethod(methodName, serviceId);
            result = getPort().getCadastreChange(serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId);
        }
        return result;
    }

    @Override
    public TransactionCadastreChangeTO getTransactionCadastreChangeById(String id)
            throws WebServiceClientException {
        TransactionCadastreChangeTO result = null;
        final String methodName = CadastreClient.GET_TRANSACTION_CADASTRE_CHANGE_BYID;
        try {
            beforeWebMethod(methodName, id);
            result = getPort().getCadastreChangeById(id);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, id);
        }
        return result;
    }

    @Override
    public List<CadastreObjectTO> getCadastreObjects(List<String> Ids)
            throws WebServiceClientException {
        List<CadastreObjectTO> result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECTS;
        try {
            beforeWebMethod(methodName, Ids);
            result = getPort().getCadastreObjects(Ids);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, Ids);
        }
        return result;
    }

    @Override
    public CadastreObjectNodeTO getCadastreObjectNode(
            double xMin, double yMin, double xMax, double yMax, int srid, String cadastreObjectType)
            throws WebServiceClientException {
        CadastreObjectNodeTO result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECT_NODE;
        try {
            beforeWebMethod(methodName, xMin, yMin, xMax, yMax, srid, cadastreObjectType);
            result = getPort().getCadastreObjectNode(
                    xMin, yMin, xMax, yMax, srid, cadastreObjectType);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, xMin, yMin, xMax, yMax, srid);
        }
        return result;
    }

    @Override
    public CadastreObjectNodeTO getCadastreObjectNodePotential(
            double xMin, double yMin, double xMax, double yMax, int srid, String cadastreObjectType)
            throws WebServiceClientException {
        CadastreObjectNodeTO result = null;
        final String methodName = CadastreClient.GET_CADASTRE_OBJECT_NODE_POTENTIAL;
        try {
            beforeWebMethod(methodName, xMin, yMin, xMax, yMax, srid, cadastreObjectType);
            result = getPort().getCadastreObjectNodePotential(
                    xMin, yMin, xMax, yMax, srid, cadastreObjectType);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, xMin, yMin, xMax, yMax, srid);
        }
        return result;
    }

    @Override
    public List<ValidationResult> saveTransactionCadastreRedefinition(
            TransactionCadastreRedefinitionTO transactionTO)
            throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = CadastreClient.SAVE_TRANSACTION_CADASTRE_REDFN;
        String languageCode = this.getLanguageCode();
        try {
            beforeWebMethod(methodName, transactionTO, languageCode);
            result = getPort().saveCadastreRedefinition(transactionTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, transactionTO, languageCode);
        }
        return result;
    }

    @Override
    public TransactionCadastreRedefinitionTO getTransactionCadastreRedefinition(String serviceId)
            throws WebServiceClientException {
        TransactionCadastreRedefinitionTO result = null;
        final String methodName = CadastreClient.GET_TRANSACTION_CADASTRE_REDFN;
        try {
            beforeWebMethod(methodName, serviceId);
            result = getPort().getCadastreRedefinition(serviceId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, serviceId);
        }
        return result;
    }
    
    @Override
    public SpatialValueAreaTO getSpatialValueArea(String colist ) throws WebServiceClientException {
        SpatialValueAreaTO result = null;
        final String methodName = CadastreClient.GET_SPATIAL_VALUE_AREA;
        try {
            beforeWebMethod(methodName,  colist);
            result = getPort().getSpatialValueArea(colist);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, colist);
        }
        return result;
    }

    @Override
    public NewCadastreObjectIdentifier getNewCadastreObjectIdentifier(
            byte[] geom, String cadastreObjectType) throws WebServiceClientException {
        NewCadastreObjectIdentifier result = null;
        final String methodName = CadastreClient.GET_NEW_CADASTRE_OBJECT_IDENTIFIER;
        try {
            beforeWebMethod(methodName,  geom, cadastreObjectType);
            result = getPort().getNewCadastreObjectIdentifier(geom, cadastreObjectType);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, geom, cadastreObjectType);
        }
        return result;
    }

}
