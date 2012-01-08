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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.SpatialClient;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.spatial.MapDefinitionTO;
import org.sola.webservices.spatial.QueryForNavigation;
import org.sola.webservices.search.QueryForSelect;
import org.sola.webservices.spatial.ResultForNavigationInfo;
import org.sola.webservices.search.ResultForSelectionInfo;

/**
 * Provides a mock implementation for the 
 * {@linkplain org.sola.services.boundary.wsclients.SpatialClient} interface. Uses the 
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method. 
 * <p>Each method mocked by this class has a public constant defined that can be used to reference 
 * a mock response object from the {@linkplain MockServiceManager}. To set a response object
 * for a web method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from this class.</p>
 * @author amcdowell
 * @see MockResponse
 */
public class MockSpatialClient extends AbstractMockWSClient implements SpatialClient {

    private static final String SERVICE_NAME = "Spatial.";
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    public static final String GET_CONFIG_MAP_LAYER_LIST = SERVICE_NAME + "getConfigMapLayerList";
    public static final String GET_SPATIAL_FOR_NAVIGATION = SERVICE_NAME + "getSpatialForNavigation";

    public MockSpatialClient(String url) {
        super(url, null);
    }

    /** @return default = true */
    @Override
    public boolean checkConnection() throws WebServiceClientException {
        return getManager().getResponse(CHECK_CONNECTION, Boolean.class, true);
    }

    /** @return default = new ResultForNavigationInfo() */
    @Override
    public ResultForNavigationInfo getSpatialForNavigation(QueryForNavigation query)
            throws WebServiceClientException {
        ResultForNavigationInfo defaultResponse = new ResultForNavigationInfo();
        return getManager().getResponse(GET_SPATIAL_FOR_NAVIGATION,
                ResultForNavigationInfo.class, defaultResponse, query);
    }

    @Override
    public MapDefinitionTO getMapDefinition() throws WebServiceClientException{
        MapDefinitionTO defaultResponse = new MapDefinitionTO();
        return getManager().getResponse(GET_SPATIAL_FOR_NAVIGATION,
                MapDefinitionTO.class, defaultResponse);
    }
}
