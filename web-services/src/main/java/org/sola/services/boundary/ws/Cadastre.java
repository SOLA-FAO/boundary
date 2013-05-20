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
package org.sola.services.boundary.ws;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectNodeTO;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectTO;
import org.sola.services.boundary.transferobjects.cadastre.SpatialValueAreaTO;
import org.sola.services.boundary.transferobjects.transaction.TransactionBulkOperationSpatialTO;
import org.sola.services.boundary.transferobjects.transaction.TransactionCadastreChangeTO;
import org.sola.services.boundary.transferobjects.transaction.TransactionCadastreRedefinitionTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.br.ValidationResult;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.*;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.cadastre.businesslogic.CadastreEJBLocal;
import org.sola.services.ejb.cadastre.repository.entities.NewCadastreObjectIdentifier;
import org.sola.services.ejb.transaction.businesslogic.TransactionEJBLocal;
import org.sola.services.ejb.transaction.repository.entities.TransactionBulkOperationSpatial;
import org.sola.services.ejb.transaction.repository.entities.TransactionCadastreChange;
import org.sola.services.ejb.transaction.repository.entities.TransactionCadastreRedefinition;
import org.sola.services.ejb.transaction.repository.entities.TransactionType;

/**
 * Web Service Boundary class to expose {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB}
 * methods.
 */
@WebService(serviceName = "cadastre-service", targetNamespace = ServiceConstants.CADASTRE_WS_NAMESPACE)
public class Cadastre extends AbstractWebService {

    @EJB
    private CadastreEJBLocal cadastreEJB;
    @EJB
    private TransactionEJBLocal transactionEJB;
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
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectByParts(java.lang.String)
     * CadastreEJB.getCadastreObjectByParts}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectByParts")
    public List<CadastreObjectTO> GetCadastreObjectByParts(
            @WebParam(name = "searchString") String searchString)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String searchStringTmp = searchString;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectByParts(searchStringTmp),
                        CadastreObjectTO.class);
            }
        });

        return (List<CadastreObjectTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectByAllParts(java.lang.String)
     * CadastreEJB.getCadastreObjectByAllParts}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectByAllParts")
    public List<CadastreObjectTO> GetCadastreObjectByAllParts(
            @WebParam(name = "searchString") String searchString)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String searchStringTmp = searchString;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectByAllParts(searchStringTmp),
                        CadastreObjectTO.class);
            }
        });

        return (List<CadastreObjectTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectByPoint(double, double, int)
     * CadastreEJB.getCadastreObjectByPoint}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectByPoint")
    public CadastreObjectTO GetCadastreObjectByPoint(
            @WebParam(name = "x") double x,
            @WebParam(name = "y") double y,
            @WebParam(name = "srid") int srid,
            @WebParam(name = "cadastreObjectType") String typeCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final double xTmp = x;
        final double yTmp = y;
        final int sridTmp = srid;
        final String typeCodeTmp = typeCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        cadastreEJB.getCadastreObjectByPoint(xTmp, yTmp, sridTmp, typeCodeTmp),
                        CadastreObjectTO.class);
            }
        });

        return (CadastreObjectTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectsByBaUnit(java.lang.String)
     * CadastreEJB.getCadastreObjectsByBaUnit}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectsByBaUnit")
    public List<CadastreObjectTO> GetCadastreObjectsByBaUnit(
            @WebParam(name = "baUnitId") String baUnitId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String baUnitIdTmp = baUnitId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectsByBaUnit(baUnitIdTmp),
                        CadastreObjectTO.class);
            }
        });

        return (List<CadastreObjectTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectsByService(java.lang.String)
     * CadastreEJB.getCadastreObjectsByService}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectsByService")
    public List<CadastreObjectTO> GetCadastreObjectsByService(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String serviceIdTmp = serviceId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectsByService(serviceIdTmp),
                        CadastreObjectTO.class);
            }
        });

        return (List<CadastreObjectTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#saveTransaction(org.sola.services.ejb.transaction.repository.entities.TransactionBasic,
     * java.lang.String, java.lang.String) TransactionEJB.saveTransaction}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveCadastreChange")
    public List<ValidationResult> SaveTransactionCadastreChange(
            @WebParam(name = "transactionCadastreChangeTO") TransactionCadastreChangeTO transactionCadastreChangeTO,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault,
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final TransactionCadastreChangeTO transactionTO = transactionCadastreChangeTO;
        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                TransactionCadastreChange targetTransaction =
                        transactionEJB.getTransactionById(
                        transactionTO.getId(), TransactionCadastreChange.class);
                TransactionCadastreChange transactionCadastreChange = GenericTranslator.fromTO(
                        transactionTO, TransactionCadastreChange.class, targetTransaction);
                result[0] = transactionEJB.saveTransaction(
                        transactionCadastreChange, TransactionType.CADASTRE_CHANGE, languageCodeTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#getTransactionByServiceId(java.lang.String,
     * boolean, java.lang.Class)
     * TransactionEJB.getTransactionByServiceId}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreChange")
    public TransactionCadastreChangeTO GetTransactionCadastreChange(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String serviceIdTmp = serviceId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        transactionEJB.getTransactionByServiceId(serviceIdTmp, false, TransactionCadastreChange.class),
                        TransactionCadastreChangeTO.class);
            }
        });

        return (TransactionCadastreChangeTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#getTransactionById(java.lang.String,
     * java.lang.Class)
     * TransactionEJB.getTransactionById}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreChangeById")
    public TransactionCadastreChangeTO GetTransactionCadastreChangeById(
            @WebParam(name = "id") final String id)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        transactionEJB.getTransactionById(id, TransactionCadastreChange.class),
                        TransactionCadastreChangeTO.class);
            }
        });

        return (TransactionCadastreChangeTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjects(java.util.List)
     * CadastreEJB.getCadastreObjects}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjects")
    public List<CadastreObjectTO> GetCadastreObjects(
            @WebParam(name = "ids") List<String> Ids)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final List<String> IdsTmp = Ids;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjects(IdsTmp), CadastreObjectTO.class);
            }
        });

        return (List<CadastreObjectTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectNode(double, double, double, double, int)
     * CadastreEJB.getCadastreObjectNode}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectNode")
    public CadastreObjectNodeTO GetCadastreObjectNode(
            @WebParam(name = "xMin") double xMin,
            @WebParam(name = "yMin") double yMin,
            @WebParam(name = "xMax") double xMax,
            @WebParam(name = "yMax") double yMax,
            @WebParam(name = "srid") int srid,
            @WebParam(name = "cadastreObjectType") String cadastreObjectType)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final double xMinTmp = xMin;
        final double yMinTmp = yMin;
        final double xMaxTmp = xMax;
        final double yMaxTmp = yMax;
        final int sridTmp = srid;
        final String cadastreObjectTypeTmp = cadastreObjectType;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(cadastreEJB.getCadastreObjectNode(
                        xMinTmp, yMinTmp, xMaxTmp, yMaxTmp, sridTmp, cadastreObjectTypeTmp),
                        CadastreObjectNodeTO.class);
            }
        });

        return (CadastreObjectNodeTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.cadastre.businesslogic.CadastreEJB#getCadastreObjectNodePotential(double, double, double, double, int)
     * CadastreEJB.getCadastreObjectNodePotential}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreObjectNodePotential")
    public CadastreObjectNodeTO GetCadastreObjectNodePotential(
            @WebParam(name = "xMin") double xMin,
            @WebParam(name = "yMin") double yMin,
            @WebParam(name = "xMax") double xMax,
            @WebParam(name = "yMax") double yMax,
            @WebParam(name = "srid") int srid,
            @WebParam(name = "cadastreObjectType") String cadastreObjectType)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        final double xMinTmp = xMin;
        final double yMinTmp = yMin;
        final double xMaxTmp = xMax;
        final double yMaxTmp = yMax;
        final int sridTmp = srid;
        final String cadastreObjectTypeTmp = cadastreObjectType;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(cadastreEJB.getCadastreObjectNodePotential(
                        xMinTmp, yMinTmp, xMaxTmp, yMaxTmp, sridTmp, cadastreObjectTypeTmp),
                        CadastreObjectNodeTO.class);
            }
        });

        return (CadastreObjectNodeTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#saveTransaction(org.sola.services.ejb.transaction.repository.entities.TransactionBasic,
     * java.lang.String, java.lang.String) TransactionEJB.saveTransaction}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveCadastreRedefinition")
    public List<ValidationResult> SaveCadastreRedefinition(
            @WebParam(name = "transactionCadastreRedefinitionTO") TransactionCadastreRedefinitionTO transactionTO,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault,
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final TransactionCadastreRedefinitionTO transactionTOTmp = transactionTO;
        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                TransactionCadastreRedefinition targetTransaction =
                        transactionEJB.getTransactionById(
                        transactionTOTmp.getId(), TransactionCadastreRedefinition.class);
                TransactionCadastreRedefinition transactionCadastreRedefinition =
                        GenericTranslator.fromTO(
                        transactionTOTmp, TransactionCadastreRedefinition.class, targetTransaction);

                result[0] = transactionEJB.saveTransaction(transactionCadastreRedefinition,
                        TransactionType.REDEFINE_CADASTRE, languageCodeTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#getTransactionByServiceId(java.lang.String,
     * boolean, java.lang.Class)
     * TransactionEJB.getTransactionByServiceId}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetCadastreRedefinition")
    public TransactionCadastreRedefinitionTO GetTransactionCadastreRedefinition(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String serviceIdTmp = serviceId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        transactionEJB.getTransactionByServiceId(
                        serviceIdTmp, false, TransactionCadastreRedefinition.class),
                        TransactionCadastreRedefinitionTO.class);
            }
        });

        return (TransactionCadastreRedefinitionTO) result[0];
    }
    
     /**
     * See {{@linkplain CadastreEJB#getSpatialValueArea(java.lang.String)
     * CadastreEJB.getSpatialValueArea(String colist )}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetSpatialValueArea")
    public SpatialValueAreaTO GetSpatialValueArea(
            @WebParam(name = "colist") String colist)
            throws SOLAFault, UnhandledFault {

        final String colistTmp = colist;
        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        cadastreEJB.getSpatialValueArea(colistTmp), SpatialValueAreaTO.class);
            }
        });

        return (SpatialValueAreaTO) result[0];
    }

     /**
     * See {{@linkplain CadastreEJB#getNewCadastreObjectIdentifier(byte[], java.lang.String)
     * CadastreEJB.getNewCadastreObjectIdentifier(byte[], java.lang.String)}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetNewCadastreObjectIdentifier")
    public NewCadastreObjectIdentifier GetNewCadastreObjectIdentifier(
            @WebParam(name = "geom") final byte[] geom,
            @WebParam(name = "cadastreObjectType") final String cadastreObjectType)
            throws SOLAFault, UnhandledFault {

        final Object[] result = {null};

        runOpenQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = cadastreEJB.getNewCadastreObjectIdentifier(geom, cadastreObjectType);
            }
        });

        return (NewCadastreObjectIdentifier) result[0];
    }
    
}
