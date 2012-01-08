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
package org.sola.services.boundary.ws;

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.sola.common.MappingManager;
import org.sola.services.boundary.transferobjects.cadastre.CadastreChangeTO;
import org.sola.services.boundary.transferobjects.cadastre.CadastreObjectTO;
import org.sola.services.boundary.transferobjects.cadastre.PropertySummaryTO;
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
import org.sola.services.ejb.cadastre.repository.entities.CadastreChange;

/**
 *
 * @author soladev
 */
@WebService(serviceName = "cadastre-service", targetNamespace=ServiceConstants.CADASTRE_WS_NAMESPACE)
public class Cadastre extends AbstractWebService {

   @EJB
   private CadastreEJBLocal cadastreEJB;

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
        try {
            try {
                beginTransaction();
                PropertySummaryTO result = null;
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    @WebMethod(operationName = "GetCadastreObjectByParts")
    public List<CadastreObjectTO> GetCadastreObjectByParts(
            @WebParam(name = "searchString") String searchString)
            throws SOLAFault, UnhandledFault {
        try {
                List<CadastreObjectTO> result = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectByParts(searchString),
                        CadastreObjectTO.class);
                return result;
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    @WebMethod(operationName = "GetCadastreObjectByPoint")
    public CadastreObjectTO GetCadastreObjectByPoint(
            @WebParam(name = "x") double x, 
            @WebParam(name = "y") double y,
            @WebParam(name = "srid") int srid)
            throws SOLAFault, UnhandledFault {
        try {
                return GenericTranslator.toTO(
                        cadastreEJB.getCadastreObjectByPoint(x,y,srid),
                        CadastreObjectTO.class);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    @WebMethod(operationName = "GetCadastreObjectsByBaUnit")
    public List<CadastreObjectTO> GetCadastreObjectsByBaUnit(
            @WebParam(name = "baUnitId") String baUnitId)
            throws SOLAFault, UnhandledFault {
        try {
                List<CadastreObjectTO> result = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectsByBaUnit(baUnitId),
                        CadastreObjectTO.class);
                return result;
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    @WebMethod(operationName = "GetCadastreObjectsByService")
    public List<CadastreObjectTO> GetCadastreObjectsByService(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault {
        try {
                List<CadastreObjectTO> result = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjectsByService(serviceId),
                        CadastreObjectTO.class);
                return result;
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    @WebMethod(operationName = "saveCadastreChange")
    public List<ValidationResult> saveCadastreChange(
            @WebParam(name = "cadastreChangeTO") CadastreChangeTO cadastreChangeTO,
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault, 
            SOLAFault, UnhandledFault, SOLAAccessFault {
        try {
            try {
                beginTransaction();
                CadastreChange cadastreChange = new CadastreChange();
                MappingManager.getMapper().map(cadastreChangeTO, cadastreChange);
                List<ValidationResult> result = 
                        cadastreEJB.saveCadastreChange(cadastreChange, languageCode);
                commitTransaction();
                return result;
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            
            if (fault.getClass() == SOLAAccessFault.class) {
                throw (SOLAAccessFault) fault;
            }
            
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }

            if (fault.getClass() == OptimisticLockingFault.class) {
                throw (OptimisticLockingFault) fault;
            }

            if (fault.getClass() == SOLAValidationFault.class) {
                throw (SOLAValidationFault) fault;
            }
            throw (UnhandledFault) fault;
        } finally {
            cleanUp();
        }
    }

    @WebMethod(operationName = "GetCadastreChange")
    public CadastreChangeTO GetCadastreChange(
            @WebParam(name = "serviceId") String serviceId)
            throws SOLAFault, UnhandledFault {
        try {
            return GenericTranslator.toTO(
                    cadastreEJB.getCadastreChange(serviceId), CadastreChangeTO.class);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    @WebMethod(operationName = "GetCadastreObjects")
    public List<CadastreObjectTO> GetCadastreObjects(
            @WebParam(name = "ids") List<String> Ids)
            throws SOLAFault, UnhandledFault {
        try {
                List<CadastreObjectTO> result = GenericTranslator.toTOList(
                        cadastreEJB.getCadastreObjects(Ids), CadastreObjectTO.class);
                return result;
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }
}
