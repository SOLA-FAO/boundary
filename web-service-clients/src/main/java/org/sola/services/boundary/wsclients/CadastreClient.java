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
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.cadastre.NewCadastreObjectIdentifier;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectNodeTO;
import org.sola.webservices.transferobjects.cadastre.CadastreObjectTO;
import org.sola.webservices.transferobjects.cadastre.SpatialValueAreaTO;
import org.sola.webservices.transferobjects.transaction.TransactionBulkOperationSpatialTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreChangeTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreRedefinitionTO;

/**
 * Interface for the Cadastre Service. Implemented by {@linkplain CadastreClientImpl}.
 * To obtain a reference to the Cadastre Service, use {@linkplain WSManager#getCadastreService()}
 *
 * @see CadastreClientImpl
 * @see WSManager#getCadastreService()
 */
public interface CadastreClient extends AbstractWSClient {

    /**
     * Cadastre. - Service name prefix for the Cadastre Web Service
     */
    public static final String SERVICE_NAME = "Cadastre.";
    /**
     * Cadastre.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * Cadastre.getCadastreObjectByParts - Identifier for the
     * getCadastreObjectByParts method
     */
    public static final String GET_CADASTRE_OBJECT_BY_PARTS = SERVICE_NAME + "getCadastreObjectByParts";
    /**
     * Cadastre.getCadastreObjectByParts - Identifier for the
     * getCadastreObjectByParts method
     */
    public static final String GET_CADASTRE_OBJECT_BY_ALL_PARTS = SERVICE_NAME + "getCadastreObjectByAllParts";
    /**
     * Cadastre.getCadastreObjectByPoint - Identifier for the
     * getCadastreObjectByPoint method
     */
    public static final String GET_CADASTRE_OBJECT_BY_POINT = SERVICE_NAME + "getCadastreObjectByPoint";
    /**
     * Cadastre.getCadastreObjectsByBaUnit - Identifier for the
     * getCadastreObjectsByBaUnit method
     */
    public static final String GET_CADASTRE_OBJECTS_BY_BA_UNIT = SERVICE_NAME + "getCadastreObjectsByBaUnit";
    /**
     * Cadastre.getCadastreObjectsByService - Identifier for the
     * getCadastreObjectsByService method
     */
    public static final String GET_CADASTRE_OBJECTS_BY_SERVICE = SERVICE_NAME + "getCadastreObjectsByService";
    /**
     * Cadastre.saveTransactionCadastreChange - Identifier for the
     * saveTransactionCadastreChange method
     */
    public static final String SAVE_TRANSACTION_CADASTRE_CHANGE = SERVICE_NAME + "saveTransactionCadastreChange";
    /**
     * Cadastre.getTransactionCadastreChange - Identifier for the
     * getTransactionCadastreChange method
     */
    public static final String GET_TRANSACTION_CADASTRE_CHANGE = SERVICE_NAME + "getTransactionCadastreChange";
    /**
     * Cadastre.getTransactionCadastreChangeById - Identifier for the
     * getTransactionCadastreChangeById method
     */
    public static final String GET_TRANSACTION_CADASTRE_CHANGE_BYID = SERVICE_NAME + "getTransactionCadastreChangeById";
    /**
     * Cadastre.getCadastreObjects - Identifier for the getCadastreObjects
     * method
     */
    public static final String GET_CADASTRE_OBJECTS = SERVICE_NAME + "getCadastreObjects";
    /**
     * Cadastre.getCadastreObjectNode - Identifier for the getCadastreObjectNode
     * method
     */
    public static final String GET_CADASTRE_OBJECT_NODE = SERVICE_NAME + "getCadastreObjectNode";
    /**
     * Cadastre.getCadastreObjectNodePotential - Identifier for the
     * getCadastreObjectNodePotential method
     */
    public static final String GET_CADASTRE_OBJECT_NODE_POTENTIAL = SERVICE_NAME + "getCadastreObjectNodePotential";
    /**
     * Cadastre.saveTransactionCadastreRedefinition - Identifier for the
     * saveTransactionCadastreRedefinition method
     */
    public static final String SAVE_TRANSACTION_CADASTRE_REDFN = SERVICE_NAME + "saveTransactionCadastreRedefinition";
    /**
     * Cadastre.getTransactionCadastreRedefinition - Identifier for the
     * getTransactionCadastreRedefinition method
     */
    public static final String GET_TRANSACTION_CADASTRE_REDFN = SERVICE_NAME + "getTransactionCadastreRedefinition";
    
    
    public static final String GET_SPATIAL_VALUE_AREA = SERVICE_NAME + "getSpatialValueArea";
 
    public static final String GET_NEW_CADASTRE_OBJECT_IDENTIFIER = SERVICE_NAME + "getNewCadastreObjectIdentifier";

    /**
     * Returns a maximum of 10 cadastre objects that have a name first part and/or name last part
     * that matches the specified search string. This method supports partial matches and is case
     * insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<CadastreObjectTO> getCadastreObjectByParts(String searchString)
            throws WebServiceClientException;

    /**
     * Returns a maximum of 10 cadastre objects with current or pending status
     * that have a name first part and/or name last part that matches the
     * specified search string. This method supports partial matches and is case
     * insensitive.
     *
     * @param searchString The search string to use
     * @return The list of cadastre objects matching the search string
     * @throws WebServiceClientException
     */
    List<CadastreObjectTO> getCadastreObjectByAllParts(String searchString)
            throws WebServiceClientException;

    /**
     * Returns the cadastre object that is located at the point specified or
     * null if there is no cadastre object at that location. Uses the PostGIS
     * ST_Intersects function to perform the comparison.
     *
     * @param x The x ordinate of the location
     * @param y The y ordinate of the location
     * @param srid The SRID identifying the coordinate system for the x,y
     * coordinate. Must match the SRID used by SOLA.
     * @throws WebServiceClientException
     */
    CadastreObjectTO getCadastreObjectByPoint(double x, double y, int srid, String typeCode)
            throws WebServiceClientException;

    /**
     * Retrieves all cadastre objects linked to the specified BA Unit.
     *
     * @param baUnitId Identifier of the BA Unit
     */
    List<CadastreObjectTO> getCadastreObjectsByBaUnit(String baUnitId);

    /**
     * Retrieves all cadastre objects linked to the specified Service through
     * transaction.
     *
     * @param serviceId Identifier of the Service
     */
    List<CadastreObjectTO> getCadastreObjectsByService(String serviceId);

    /**
     * Can be used to create a new cadastre object or save any updates to the
     * details of an existing cadastre object.
     *
     * @param cadastreObject The cadastre object to create/save.
     * @return The cadastre object after the save is completed.
     */
    List<ValidationResult> saveTransactionCadastreChange(TransactionCadastreChangeTO cadastreChangeTO);

    TransactionCadastreChangeTO getTransactionCadastreChange(String serviceId);

    TransactionCadastreChangeTO getTransactionCadastreChangeById(String id);
    /**
     * Retrieves a list of cadastre object matching the list of ids provided.
     *
     * @param Ids A list of cadaster object ids to use for retrieval.
     */
    List<CadastreObjectTO> getCadastreObjects(List<String> Ids);

    /**
     * Retrieves all node points from the underlying cadastre objects that
     * intersect the specified bounding box coordinates. All of the node points
     * within the bounding box are used to create a single geometry - {@linkplain CadastreObjectNode#geom}.
     * The cadastre objects used as the source of the node points are also
     * captured in the {@linkplain CadastreObjectNode#cadastreObjectList}.
     *
     * @param xMin The xMin ordinate of the bounding box
     * @param yMin The yMin ordinate of the bounding box
     * @param xMax The xMax ordinate of the bounding box
     * @param yMax The yMax ordinate of the bounding box
     * @param srid The SRID to use to create the bounding box. Must be the same
     * SRID as the one used by the cadastre_object table.
     * @return The CadastreObjectNode representing all node points within the
     * bounding box as well as the list of cadastre objects used to obtain the
     * node points.
     */
    CadastreObjectNodeTO getCadastreObjectNode(
            double xMin, double yMin, double xMax, double yMax, int srid, String cadastreObjectType);

    /**
     * Unknown
     *
     * @param xMin The xMin ordinate of the bounding box
     * @param yMin The yMin ordinate of the bounding box
     * @param xMax The xMax ordinate of the bounding box
     * @param yMax The yMax ordinate of the bounding box
     * @param srid The SRID to use to create the bounding box. Must be the same
     * SRID as the one used by the cadastre_object table.
     */
    CadastreObjectNodeTO getCadastreObjectNodePotential(
            double xMin, double yMin, double xMax, double yMax, int srid, String cadastreObjectType);

    List<ValidationResult> saveTransactionCadastreRedefinition(
            TransactionCadastreRedefinitionTO transactionTO);

    /**
     * Approves the changes to cadastre objects as a result of a cadastre
     * redefinition.
     *
     * @param serviceId The identifier of the transaction
     */
    TransactionCadastreRedefinitionTO getTransactionCadastreRedefinition(String serviceId);
    
    
     /**
     * Retrieves the BA Unit matching the supplied identifier.
     *
     * @param id The BA Unit identifier
     * * @param coluist list of cadastre objects
     * @return The BA Unit details or null if the identifier is invalid.
     * @throws WebServiceClientException
     */
   SpatialValueAreaTO getSpatialValueArea(String colist) throws WebServiceClientException;
    
    
   NewCadastreObjectIdentifier getNewCadastreObjectIdentifier(byte[] geom, String cadastreObjectType);
}
