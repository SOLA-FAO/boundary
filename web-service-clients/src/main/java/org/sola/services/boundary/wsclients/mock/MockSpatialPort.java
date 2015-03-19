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
package org.sola.services.boundary.wsclients.mock;

import org.sola.services.boundary.wsclients.SpatialClient;
import org.sola.webservices.spatial.*;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.spatial.Spatial} interface. Uses the
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method.
 * <p>Each method mocked by this class has a public constant defined that can be used to reference a
 * mock response object from the {@linkplain MockServiceManager}. To set a response object for a web
 * method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method referencing
 * the appropriate web method constant from {@linkplain org.sola.services.boundary.wsclients.SpatialClient}.</p>
 *
 * @see MockSpatialClient
 * @see SpatialClient
 * @see MockServiceManager
 * @see MockResponse
 *
 */
public class MockSpatialPort implements Spatial {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type.
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
     * Response Key = SpatialClient.CHECK_CONNECTION
     *
     * @return default = true
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(SpatialClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = SpatialClient.GET_SPATIAL_FOR_NAVIGATION
     *
     * @return default = new ResultForNavigationInfo()
     */
    @Override
    public ResultForNavigationInfo getSpatialForNavigation(QueryForNavigation arg0)
            throws SOLAFault, UnhandledFault {
        ResultForNavigationInfo defaultResponse = new ResultForNavigationInfo();
        try {
            return getManager().getResponse(SpatialClient.GET_SPATIAL_FOR_NAVIGATION,
                    ResultForNavigationInfo.class, defaultResponse, arg0);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = SpatialClient.GET_SPATIAL_FOR_PUBLIC_DISPLAY
     *
     * @return default = new ResultForNavigationInfo()
     */
    @Override
    public ResultForNavigationInfo getSpatialForPublicDisplay(QueryForPublicDisplayMap arg0)
            throws SOLAFault, UnhandledFault {
        ResultForNavigationInfo defaultResponse = new ResultForNavigationInfo();
        try {
            return getManager().getResponse(SpatialClient.GET_SPATIAL_FOR_PUBLIC_DISPLAY,
                    ResultForNavigationInfo.class, defaultResponse, arg0);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }
}
