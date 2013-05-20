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
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.CadastreClient;
import org.sola.webservices.cadastre.*;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectNodeTO;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectTO;
import org.sola.webservices.transferobjects.cadastre.SpatialValueAreaTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreChangeTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreRedefinitionTO;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.cadastre.Cadastre} interface. Uses the
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for
 * each web method. <p>Each method mocked by this class has a public constant
 * defined that can be used to reference a mock response object from the {@linkplain MockServiceManager}.
 * To set a response object for a web method, use the {@linkplain MockServiceManager#setResponse(String, Object)}
 * method referencing the appropriate web method constant from {@linkplain org.sola.services.boundary.wsclients.CadastreClient}.</p>
 *
 * @see MockCadastreClient
 * @see CadastreClient
 * @see MockServiceManager
 * @see MockResponse
 *
 */
public class MockCadastrePort implements Cadastre {

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
     * recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
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
     * recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
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
     * recognized type. Extends {@linkplain #processExceptionUpdate(java.lang.Exception) processExceptionUpdate}
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
     * Response Key = CadastreClient.CHECK_CONNECTION
     *
     * @return default = true
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(CadastreClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_BY_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<CadastreObjectTO> getCadastreObjectByParts(String searchString)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<CadastreObjectTO> defaultResponse = new ArrayList<CadastreObjectTO>();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECT_BY_PARTS,
                    List.class, defaultResponse, searchString);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_BY_ALL_PARTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<CadastreObjectTO> getCadastreObjectByAllParts(String searchString)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<CadastreObjectTO> defaultResponse = new ArrayList<CadastreObjectTO>();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECT_BY_ALL_PARTS,
                    List.class, defaultResponse, searchString);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_BY_POINT
     *
     * @return default = new CadastreObjectTO()
     */
    @Override
    public CadastreObjectTO getCadastreObjectByPoint(double x, double y, int srid, String typeCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        CadastreObjectTO defaultResponse = new CadastreObjectTO();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECT_BY_POINT,
                    CadastreObjectTO.class, defaultResponse, x, y, srid, typeCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECTS_BY_BA_UNIT
     *
     * @return default = new CadastreObjectTO()
     */
    @Override
    public List<CadastreObjectTO> getCadastreObjectsByBaUnit(String baUnitId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<CadastreObjectTO> defaultResponse = new ArrayList<CadastreObjectTO>();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECTS_BY_BA_UNIT,
                    List.class, defaultResponse, baUnitId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECTS_BY_SERVICE
     *
     * @return default = new CadastreObjectTO()
     */
    @Override
    public List<CadastreObjectTO> getCadastreObjectsByService(String serviceId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<CadastreObjectTO> defaultResponse = new ArrayList<CadastreObjectTO>();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECTS_BY_SERVICE,
                    List.class, defaultResponse, serviceId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_TRANSACTION_CADASTRE_CHANGE
     *
     * @return default = new TransactionCadastreChangeTO()
     */
    @Override
    public TransactionCadastreChangeTO getCadastreChange(String serviceId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        TransactionCadastreChangeTO defaultResponse = new TransactionCadastreChangeTO();
        try {
            return getManager().getResponse(CadastreClient.GET_TRANSACTION_CADASTRE_CHANGE,
                    TransactionCadastreChangeTO.class, defaultResponse, serviceId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_TRANSACTION_CADASTRE_CHANGE_BYID
     *
     * @return default = new TransactionCadastreChangeTO()
     */
    @Override
    public TransactionCadastreChangeTO getCadastreChangeById(String id) 
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        TransactionCadastreChangeTO defaultResponse = new TransactionCadastreChangeTO();
        try {
            return getManager().getResponse(CadastreClient.GET_TRANSACTION_CADASTRE_CHANGE_BYID,
                    TransactionCadastreChangeTO.class, defaultResponse, id);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }
    
    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECTS
     *
     * @return default = new ArrayList<CadastreObjectTO>()
     */
    @Override
    public List<CadastreObjectTO> getCadastreObjects(List<String> ids)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<CadastreObjectTO> defaultResponse = new ArrayList<CadastreObjectTO>();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECTS,
                    List.class, defaultResponse, ids);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_NODE
     *
     * @return default = new CadastreObjectNodeTO()
     */
    @Override
    public CadastreObjectNodeTO getCadastreObjectNode(double xMin, double yMin, double xMax,
            double yMax, int srid, String cadastreObjectType)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        CadastreObjectNodeTO defaultResponse = new CadastreObjectNodeTO();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECT_NODE,
                    CadastreObjectNodeTO.class, defaultResponse, xMin, yMin, xMax, yMax, srid,
                    cadastreObjectType);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.SAVE_TRANSACTION_CADASTRE_REDFN
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> saveCadastreRedefinition(TransactionCadastreRedefinitionTO transactionCadastreRedefinitionTO,
            String languageCode) throws OptimisticLockingFault, SOLAAccessFault, SOLAFault,
            SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CadastreClient.SAVE_TRANSACTION_CADASTRE_REDFN,
                    List.class, defaultResponse, transactionCadastreRedefinitionTO, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.SAVE_TRANSACTION_CADASTRE_CHANGE
     *
     * @return default = new ArrayList<ValidationResult>()
     */
    @Override
    public List<ValidationResult> saveCadastreChange(TransactionCadastreChangeTO transactionCadastreChangeTO,
            String languageCode) throws OptimisticLockingFault, SOLAAccessFault, SOLAFault,
            SOLAValidationFault, UnhandledFault {
        List<ValidationResult> defaultResponse = new ArrayList<ValidationResult>();
        try {
            return getManager().getResponse(CadastreClient.SAVE_TRANSACTION_CADASTRE_CHANGE,
                    List.class, defaultResponse, transactionCadastreChangeTO, languageCode);
        } catch (Exception ex) {
            processExceptionAll(ex);
            return null;
        }
    }

    /**
     * Response Key = CadastreClient.GET_CADASTRE_OBJECT_NODE_POTENTIAL
     *
     * @return default = new CadastreObjectNodeTO()
     */
    @Override
    public CadastreObjectNodeTO getCadastreObjectNodePotential(double xMin, double yMin,
            double xMax, double yMax, int srid, String cadastreObjectType)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        CadastreObjectNodeTO defaultResponse = new CadastreObjectNodeTO();
        try {
            return getManager().getResponse(CadastreClient.GET_CADASTRE_OBJECT_NODE_POTENTIAL,
                    CadastreObjectNodeTO.class, defaultResponse, xMin, yMin, xMax, yMax, srid,
                    cadastreObjectType);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }
    
          /**
     * Response Key = AdministrativeClient.GET_SPATIAL_VALUE_AREA
     *
     * @return default = new SpatialValueAreaTO()
     */
    @Override
    public SpatialValueAreaTO getSpatialValueArea(String colist) throws SOLAFault, UnhandledFault {
        SpatialValueAreaTO defaultResponse = new SpatialValueAreaTO();
        try {
            return getManager().getResponse(CadastreClient.GET_SPATIAL_VALUE_AREA,
                    SpatialValueAreaTO.class, defaultResponse, colist);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }
    
    /**
     * Response Key = CadastreClient.GET_TRANSACTION_CADASTRE_REDFN
     *
     * @return default = new TransactionCadastreRedefinitionTO()
     */
    @Override
    public TransactionCadastreRedefinitionTO getCadastreRedefinition(String serviceId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        TransactionCadastreRedefinitionTO defaultResponse = new TransactionCadastreRedefinitionTO();
        try {
            return getManager().getResponse(CadastreClient.GET_TRANSACTION_CADASTRE_REDFN,
                    TransactionCadastreRedefinitionTO.class, defaultResponse, serviceId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

          /**
     * Response Key = AdministrativeClient.GET_NEW_CADASTRE_OBJECT_IDENTIFIER
     *
     * @return default = new NewCadastreObjectIdentifier()
     */
    @Override
    public NewCadastreObjectIdentifier getNewCadastreObjectIdentifier(byte[] geom, String cadastreObjectType) 
            throws SOLAFault, UnhandledFault {
        NewCadastreObjectIdentifier defaultResponse = new NewCadastreObjectIdentifier();
        try {
            return getManager().getResponse(CadastreClient.GET_NEW_CADASTRE_OBJECT_IDENTIFIER,
                    NewCadastreObjectIdentifier.class, defaultResponse, geom, cadastreObjectType);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

  
}
