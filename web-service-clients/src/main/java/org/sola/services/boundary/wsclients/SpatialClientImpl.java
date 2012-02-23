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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;
import org.sola.webservices.spatial.MapDefinitionTO;
import org.sola.webservices.spatial.QueryForNavigation;
import org.sola.webservices.spatial.ResultForNavigationInfo;
import org.sola.webservices.spatial.SOLAFault;
import org.sola.webservices.spatial.Spatial;
import org.sola.webservices.spatial.SpatialService;
import org.sola.webservices.spatial.UnhandledFault;

/**
 * Implementation class for the {@linkplain SpatialClient} interface. 
 * @author amcdowell
 */
public class SpatialClientImpl extends AbstractWSClientImpl implements SpatialClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/spatial";
    private static final String LOCAL_PART = "spatial-service";
    private static final String SERVICE_NAME = "Spatial.";

    public SpatialClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    private Spatial getPort() {
        return getPort(Spatial.class, SpatialService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "checkConnection";
        try {
            boolean result = getPort().checkConnection();
            return result;
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return false;
        }
    }

    @Override
    public ResultForNavigationInfo getSpatialForNavigation(QueryForNavigation query)
            throws WebServiceClientException {
        final String inputService = SERVICE_NAME + "getSpatialForNavigation";
        try {
            ResultForNavigationInfo result = getPort().getSpatialForNavigation(query);
            return result;
        } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }

    }
    
    @Override
    public MapDefinitionTO getMapDefinition() throws WebServiceClientException{
        final String inputService = SERVICE_NAME + "getSpatialForNavigation";
        try {
            return getPort().getMapDefinition(this.getLanguageCode());
         } catch (Throwable e) {
           handleExceptionsMethod(inputService,e);
           return null;
        }

    }
}
 