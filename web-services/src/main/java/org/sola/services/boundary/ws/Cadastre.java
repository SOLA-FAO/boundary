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
package org.sola.services.boundary.ws;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.common.MappingManager;
import org.sola.services.boundary.transferobjects.cadastre.CadastreChangeTO;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectNodeTO;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectTO;
import org.sola.services.boundary.transferobjects.cadastre.PropertySummaryTO;
import org.sola.services.boundary.transferobjects.transaction.TransactionCadastreChangeTO;
import org.sola.services.boundary.transferobjects.transaction.TransactionCadastreRedefinitionTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.br.ValidationResult;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.OptimisticLockingFault;
import org.sola.services.common.faults.SOLAAccessFault;
import org.sola.services.common.faults.SOLAValidationFault;
import org.sola.services.ejb.cadastre.businesslogic.CadastreEJBLocal;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.transaction.businesslogic.TransactionEJBLocal;
import org.sola.services.ejb.transaction.repository.entities.TransactionCadastreChange;
import org.sola.services.ejb.transaction.repository.entities.TransactionCadastreRedefinition;
import org.sola.services.ejb.transaction.repository.entities.TransactionType;

/**
 *
 * @author soladev
 */
@WebService(serviceName = "cadastre-service", targetNamespace=ServiceConstants.CADASTRE_WS_NAMESPACE)
public class Cadastre extends AbstractWebService {

   @EJB
   private CadastreEJBLocal cadastreEJB;
   
   @EJB
   private TransactionEJBLocal transactionEJB;
   
   @Resource
   private WebServiceContext wsContext;


//    @EJB // Annotate a setter so the EJB can be manually created and injected for Unit Testing
//    public void setSpatialUnit(SpatialUnitEJB spatialUnit) {
//        this.spatialUnit = spatialUnit;
//    }
   
   /** Dummy method to check the web service instance is working */
   @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection(){
        return true;
    }

    @WebMethod(operationName = "SearchProperty")
    public PropertySummaryTO SearchProperty(
            @WebParam(name = "nameFirstPart") String nameFirstPart, 
            @WebParam(name = "nameLastPart") String nameLastPart)
            throws SOLAFault, UnhandledFault {
    
          //     FLOSS - 813 0        
            final String nameFirstPartTmp  = nameFirstPart;
            final String nameLastPartTmp  =  nameLastPart;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  PropertySummaryTO.class;
                        }
        });

        return (PropertySummaryTO) result[0];
        
        
        
//        try {
//            try {
//                beginTransaction();
//                PropertySummaryTO result = null;
//                commitTransaction();
//                return result;
//            } finally {
//                rollbackTransaction();
//            }
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
   
    
    }

    @WebMethod(operationName = "GetCadastreObjectByParts")
    public List<CadastreObjectTO> GetCadastreObjectByParts(
            @WebParam(name = "searchString") String searchString)
            throws SOLAFault, UnhandledFault {
   
        //     FLOSS - 813 1        
            final String searchStringTmp  = searchString;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectByParts(searchStringTmp),
                        CadastreObjectTO.class);
                        }
        });

        return (List<CadastreObjectTO>) result[0];
        
        
//        try {
//                List<CadastreObjectTO> result = GenericTranslator.toTOList(
//                        cadastreEJB.getCadastreObjectByParts(searchString),
//                        CadastreObjectTO.class);
//                return result;
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
    
    
    }

    @WebMethod(operationName = "GetCadastreObjectByPoint")
    public CadastreObjectTO GetCadastreObjectByPoint(
            @WebParam(name = "x") double x, 
            @WebParam(name = "y") double y,
            @WebParam(name = "srid") int srid)
            throws SOLAFault, UnhandledFault {
       
          
         //     FLOSS - 813 2        
            final double xTmp  = x;
            final double yTmp  = y;
            final int    sridTmp  = srid;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTO(
                        cadastreEJB.getCadastreObjectByPoint(xTmp,yTmp,sridTmp),
                        CadastreObjectTO.class);
                        }
        });

        return (CadastreObjectTO) result[0];
        
        
        
//        try {
//                return GenericTranslator.toTO(
//                        cadastreEJB.getCadastreObjectByPoint(x,y,srid),
//                        CadastreObjectTO.class);
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
    }

    @WebMethod(operationName = "GetCadastreObjectsByBaUnit")
    public List<CadastreObjectTO> GetCadastreObjectsByBaUnit(
            @WebParam(name = "baUnitId") String baUnitId)
            throws SOLAFault, UnhandledFault {
      //     FLOSS - 813 3        
            final String baUnitIdTmp  = baUnitId;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectsByBaUnit(baUnitIdTmp),
                        CadastreObjectTO.class);
                        }
        });

        return (List<CadastreObjectTO>) result[0];
        
        
//        try {
//                List<CadastreObjectTO> result = GenericTranslator.toTOList(
//                        cadastreEJB.getCadastreObjectsByBaUnit(baUnitId),
//                        CadastreObjectTO.class);
//                return result;
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
    }

    @WebMethod(operationName = "GetCadastreObjectsByService")
    public List<CadastreObjectTO> GetCadastreObjectsByService(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault {
       
          //     FLOSS - 813 4        
            final String serviceIdTmp  = serviceId;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectsByService(serviceIdTmp),
                        CadastreObjectTO.class);
                        }
        });

        return (List<CadastreObjectTO>) result[0];
    
        
        
        
//        try {
//                List<CadastreObjectTO> result = GenericTranslator.toTOList(
//                        cadastreEJB.getCadastreObjectsByService(serviceId),
//                        CadastreObjectTO.class);
//                return result;
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
    }

    @WebMethod(operationName = "SaveCadastreChange")
    public List<ValidationResult> SaveTransactionCadastreChange(
            @WebParam(name = "transactionCadastreChangeTO") 
                    TransactionCadastreChangeTO transactionCadastreChangeTO,
            @WebParam(name = "languageCode") String languageCode)
            
            throws SOLAValidationFault, OptimisticLockingFault, 
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final TransactionCadastreChangeTO transactionTO  = transactionCadastreChangeTO;
        final String languageCodeTmp  = languageCode;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                TransactionCadastreChange transactionCadastreChange = GenericTranslator.fromTO(
                        transactionTO, TransactionCadastreChange.class, null);
                result[0] = transactionEJB.saveTransaction(
                        transactionCadastreChange, TransactionType.CADASTRE_CHANGE, languageCodeTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "GetCadastreChange")
    public TransactionCadastreChangeTO GetTransactionCadastreChange(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault {
    
        //     FLOSS - 813 5        
            final String serviceIdTmp  = serviceId;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTO(
                    transactionEJB.getTransactionByServiceId(serviceIdTmp, false, TransactionCadastreChange.class),
                    TransactionCadastreChangeTO.class);
                        }
        });

        return (TransactionCadastreChangeTO) result[0];
    
               
//        try {
//            return GenericTranslator.toTO(
//                    transactionEJB.getTransactionByServiceId(serviceId, false, TransactionCadastreChange.class),
//                    TransactionCadastreChangeTO.class);
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
    
    }

    @WebMethod(operationName = "GetCadastreObjects")
    public List<CadastreObjectTO> GetCadastreObjects(
            @WebParam(name = "ids") List<String> Ids)
            throws SOLAFault, UnhandledFault {
          
        //     FLOSS - 813 6        
            final List<String> IdsTmp  = Ids;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjects(IdsTmp), CadastreObjectTO.class);
                        }
        });

        return (List<CadastreObjectTO>) result[0];
        
        
//        try {
//                List<CadastreObjectTO> result = GenericTranslator.toTOList(
//                        cadastreEJB.getCadastreObjects(Ids), CadastreObjectTO.class);
//                return result;
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
    
    
    }

    @WebMethod(operationName = "GetCadastreObjectNode")
    public CadastreObjectNodeTO GetCadastreObjectNode(
            @WebParam(name = "xMin") double xMin, 
            @WebParam(name = "yMin") double yMin,
            @WebParam(name = "xMax") double xMax, 
            @WebParam(name = "yMax") double yMax,
            @WebParam(name = "srid") int srid)
            throws SOLAFault, UnhandledFault {
        final double xMinTmp  = xMin;
        final double yMinTmp  = yMin;
        final double xMaxTmp  = xMax;
        final double yMaxTmp  = yMax;
        final int sridTmp  = srid;
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTO(cadastreEJB.getCadastreObjectNode(
                        xMinTmp, yMinTmp, xMaxTmp, yMaxTmp, sridTmp),
                        CadastreObjectNodeTO.class);
                        }
        });

        return (CadastreObjectNodeTO) result[0];
    }

    @WebMethod(operationName = "GetCadastreObjectNodePotential")
    public CadastreObjectNodeTO GetCadastreObjectNodePotential(
            @WebParam(name = "xMin") double xMin, 
            @WebParam(name = "yMin") double yMin,
            @WebParam(name = "xMax") double xMax, 
            @WebParam(name = "yMax") double yMax,
            @WebParam(name = "srid") int srid)
            throws SOLAFault, UnhandledFault {
        final double xMinTmp  = xMin;
        final double yMinTmp  = yMin;
        final double xMaxTmp  = xMax;
        final double yMaxTmp  = yMax;
        final int sridTmp  = srid;
        final Object[] result = {null};

        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTO(cadastreEJB.getCadastreObjectNodePotential(
                        xMinTmp, yMinTmp, xMaxTmp, yMaxTmp, sridTmp),
                        CadastreObjectNodeTO.class);
                        }
        });

        return (CadastreObjectNodeTO) result[0];
    }

    @WebMethod(operationName = "SaveCadastreRedefinition")
    public List<ValidationResult> SaveCadastreRedefinition(
            @WebParam(name = "transactionCadastreRedefinitionTO") 
                    TransactionCadastreRedefinitionTO transactionTO,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault, 
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final TransactionCadastreRedefinitionTO transactionTOTmp  = transactionTO;
        final String languageCodeTmp  = languageCode;
        final Object[] result = {null};

        runUpdateMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                TransactionCadastreRedefinition transactionCadastreRedefinition = 
                        GenericTranslator.fromTO(
                        transactionTOTmp, TransactionCadastreRedefinition.class, null);
                result[0] = transactionEJB.saveTransaction(transactionCadastreRedefinition, 
                        TransactionType.REDEFINE_CADASTRE, languageCodeTmp);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    @WebMethod(operationName = "GetCadastreRedefinition")
    public TransactionCadastreRedefinitionTO GetTransactionCadastreRedefinition(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault {
        
         //     FLOSS - 813 7        
            final String serviceIdTmp  = serviceId;
            final Object[] result = {null};
  
        runGeneralMethod(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] =  GenericTranslator.toTO(
                    transactionEJB.getTransactionByServiceId(
                        serviceIdTmp, false, TransactionCadastreRedefinition.class),
                    TransactionCadastreRedefinitionTO.class);
                        }
        });

        return (TransactionCadastreRedefinitionTO) result[0];
    
      
        
//        try {
//            return GenericTranslator.toTO(
//                    transactionEJB.getTransactionByServiceId(
//                        serviceId, false, TransactionCadastreRedefinition.class),
//                    TransactionCadastreRedefinitionTO.class);
//        } catch (Throwable t) {
//            Throwable fault = FaultUtility.ProcessException(t);
//            if (fault.getClass() == SOLAFault.class) {
//                throw (SOLAFault) fault;
//            }
//            throw (UnhandledFault) fault;
//        }
//        
        
        
    }
}
