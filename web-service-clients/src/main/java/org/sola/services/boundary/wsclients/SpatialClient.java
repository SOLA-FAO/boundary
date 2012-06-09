/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO). All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this list of conditions
 * and the following disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice,this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.wsclients;

import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.spatial.MapDefinitionTO;
import org.sola.webservices.spatial.QueryForNavigation;
import org.sola.webservices.spatial.ResultForNavigationInfo;

/**
 * Interface for the Spatial Service. Implemented by {@linkplain SpatialClientImpl}. To obtain a
 * reference to the Spatial Service, use {@linkplain WSManager#getSpatialService()}. <p>The Spatial
 * Service is used for retrieving navigation data. Communication with the Spatial Service is not
 * encrypted to ensure optimal performance of the map while navigating. </p>
 *
 * @see SpatialClientImpl
 * @see WSManager#getSpatialService()
 */
public interface SpatialClient extends AbstractWSClient {

    /**
     * Spatial. - Service name prefix for the Spatial Web Service
     */
    public static final String SERVICE_NAME = "Spatial.";
    /**
     * Spatial.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * Spatial.getSpatialForNavigation - Identifier for the getSpatialForNavigation method
     */
    public static final String GET_SPATIAL_FOR_NAVIGATION = SERVICE_NAME + "getSpatialForNavigation";
    /**
     * Spatial.getMapDefinition - Identifier for the getMapDefinition method
     */
    public static final String GET_MAP_DEFINITION = SERVICE_NAME + "getMapDefinition";

    /**
     * Used for navigation (i.e. pan and zoom) of the Map. Executes a dynamic layer query using the
     * bounding box details provided in the search parameters. The dynamic query to execute must be
     * one of the layer queries in system.query.
     *
     * @param query The parameters to use for the query including the name of the dynamic layer
     * query to execute.
     * @return A summary of all spatial objects intersecting the bounding box
     * @throws WebServiceClientException
     */
    ResultForNavigationInfo getSpatialForNavigation(QueryForNavigation query) throws WebServiceClientException;

    /**
     * Returns the map layer config details from system.config_map_layer table. Also retrieves the
     * default map settings (i.e. default extent for the map and srid) from the system.settings
     * table. Uses the default language code of the client locale to localize display values.
     *
     * @throws WebServiceClientException
     */
    MapDefinitionTO getMapDefinition() throws WebServiceClientException;
}
