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

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.sola.services.boundary.transferobjects.administrative.BaUnitTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.administrative.businesslogic.AdministrativeEJB;
import org.sola.services.ejb.administrative.businesslogic.AdministrativeEJBLocal;
import org.sola.services.ejb.administrative.repository.entities.BaUnit;
import org.sola.services.ejb.cadastre.businesslogic.CadastreEJBLocal;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;

/** Boundary class to expose {@link AdministrativeEJB} methods.*/
@WebService(serviceName = "administrative-service", targetNamespace = ServiceConstants.ADMINISTRATIVE_WS_NAMESPACE)
public class Administrative extends AbstractWebService {

    @EJB
    private AdministrativeEJBLocal administrativeEJB;
    @EJB
    private CadastreEJBLocal cadastreEJB;
    @EJB
    private SourceEJBLocal sourceEJB;
    
    /** Dummy method to check the web service instance is working */
   @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection(){
        return true;
    }
   
   @WebMethod(operationName = "CreateBaUnit")
    public BaUnitTO CreateBaUnit(
           @WebParam(name="serviceId") String serviceId,
           @WebParam(name = "baUnitTO") BaUnitTO baUnitTO)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                BaUnitTO result = null;
                if (baUnitTO != null) {
                    beginTransaction();
                    BaUnit newBaUnit = administrativeEJB.createBaUnit(
                            serviceId,
                            GenericTranslator.fromTO(baUnitTO, BaUnit.class,
                            administrativeEJB.getBaUnitById(baUnitTO.getId())));
                    result = GenericTranslator.toTO(newBaUnit, BaUnitTO.class);
                    commitTransaction();
                }
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
        } finally {
            cleanUp();
        }
    }
   
    @WebMethod(operationName = "SaveBaUnit")
    public BaUnitTO SaveBaUnit(
            @WebParam(name="serviceId") String serviceId,
            @WebParam(name = "baUnitTO") BaUnitTO baUnitTO)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                BaUnitTO result = null;
                if (baUnitTO != null) {
                    beginTransaction();
                    // TODO: Implement row version control
                    BaUnit newBaUnit = administrativeEJB.saveBaUnit(
                            serviceId,
                            GenericTranslator.fromTO(baUnitTO, BaUnit.class,
                            administrativeEJB.getBaUnitById(baUnitTO.getId())));
                    result = GenericTranslator.toTO(newBaUnit, BaUnitTO.class);
                    commitTransaction();
                }
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
        } finally {
            cleanUp();
        }
    }
    
    @WebMethod(operationName = "GetBaUnitById")
    public BaUnitTO GetBaUnitById(@WebParam(name = "id") String id)
            throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                BaUnitTO result = GenericTranslator.toTO(
                        administrativeEJB.getBaUnitById(id), BaUnitTO.class);
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
        } finally {
            cleanUp();
        }
    }
    
    @WebMethod(operationName = "GetBaUnitByCode")
    public BaUnitTO GetBaUnitByCode(
                        @WebParam(name = "nameFirstpart") String nameFirstpart, 
                        @WebParam(name = "nameLastpart") String nameLastpart)
                        throws SOLAFault, UnhandledFault {
        try {
            //initialize();
            try {
                beginTransaction();
                BaUnitTO result = GenericTranslator.toTO(
                        administrativeEJB.getBaUnitByCode(nameFirstpart, 
                        nameLastpart), BaUnitTO.class);
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
        } finally {
            cleanUp();
        }
    }
}
