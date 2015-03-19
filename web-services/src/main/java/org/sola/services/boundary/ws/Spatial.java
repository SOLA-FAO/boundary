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
package org.sola.services.boundary.ws;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.search.businesslogic.SearchEJBLocal;
import org.sola.services.ejb.search.spatial.QueryForNavigation;
import org.sola.services.ejb.search.spatial.QueryForPublicDisplayMap;
import org.sola.services.ejb.search.spatial.ResultForNavigationInfo;

/**
 * Web Service Boundary class to expose methods for spatial navigation on the
 * {@linkplain org.sola.services.ejb.search.businesslogic.SearchEJB}. <p>To avoid impacting
 * performance while navigating the map, this service exposes its methods without securing /
 * authenticating the connection or encrypting the data.</p>
 */
@WebService(serviceName = "spatial-service", targetNamespace = ServiceConstants.SPATIAL_WS_NAMESPACE)
public class Spatial extends AbstractWebService {

    @EJB
    SearchEJBLocal searchEJB;
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
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getSpatialResult(org.sola.services.ejb.search.spatial.QueryForNavigation)
     * SearchEJB.getSpatialResult}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetSpatialForNavigation")
    public ResultForNavigationInfo GetSpatialForNavigation(QueryForNavigation spatialQuery)
            throws SOLAFault, UnhandledFault {

        final Object[] result = {null};
        final QueryForNavigation spatialQueryTmp = spatialQuery;

        runUnsecured(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = searchEJB.getSpatialResult(spatialQueryTmp);
            }
        });

        return (ResultForNavigationInfo) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getSpatialResult(org.sola.services.ejb.search.spatial.QueryForNavigation)
     * SearchEJB.getSpatialResult}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetSpatialForPublicDisplay")
    public ResultForNavigationInfo GetSpatialForPublicDisplay(QueryForPublicDisplayMap spatialQuery)
            throws SOLAFault, UnhandledFault {

        final Object[] result = {null};
        final QueryForPublicDisplayMap spatialQueryTmp = spatialQuery;

        runUnsecured(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = searchEJB.getSpatialResultForPublicDisplay(spatialQueryTmp);
            }
        });

        return (ResultForNavigationInfo) result[0];
    }
}
