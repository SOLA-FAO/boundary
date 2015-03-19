/**
 * ******************************************************************************************
 * Copyright (C) 2015 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.wsclients;

import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.spatial.QueryForNavigation;
import org.sola.webservices.spatial.QueryForPublicDisplayMap;
import org.sola.webservices.spatial.ResultForNavigationInfo;
import org.sola.webservices.spatial.Spatial;
import org.sola.webservices.spatial.SpatialService;

/**
 * Implementation class for the {@linkplain SpatialClient} interface.
 */
public class SpatialClientImpl extends AbstractWSClientImpl implements SpatialClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/spatial";
    private static final String LOCAL_PART = "spatial-service";

    /**
     * Creates a web service client class for the web service hosted at the specified URL
     *
     * @param url The location of the service WSDL
     */
    public SpatialClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected Spatial getPort() {
        return getPort(Spatial.class, SpatialService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = SpatialClient.CHECK_CONNECTION;
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
    public ResultForNavigationInfo getSpatialForNavigation(QueryForNavigation query)
            throws WebServiceClientException {
        ResultForNavigationInfo result = null;
        final String methodName = SpatialClient.GET_SPATIAL_FOR_NAVIGATION;
        try {
            beforeWebMethod(methodName, query);
            result = getPort().getSpatialForNavigation(query);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, query);
        }
        return result;
    }

    @Override
    public ResultForNavigationInfo getSpatialForPublicDisplay(QueryForPublicDisplayMap query)
            throws WebServiceClientException {
        ResultForNavigationInfo result = null;
        final String methodName = SpatialClient.GET_SPATIAL_FOR_PUBLIC_DISPLAY;
        try {
            beforeWebMethod(methodName, query);
            result = getPort().getSpatialForPublicDisplay(query);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, query);
        }
        return result;
    }
}
