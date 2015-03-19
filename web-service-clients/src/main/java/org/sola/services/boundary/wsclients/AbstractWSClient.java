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

import org.sola.services.boundary.wsclients.exception.WebServiceClientException;

/**
 * Provides a default interface for all web service client classes.
 *
 * @author soladev
 * @see AbstractWSClientImpl
 * @see CaseManagementClient
 * @see ReferenceDataClient
 * @see AdminClient
 * @see SecurityClient
 * @see AdministrativeClient
 * @see DigialArchiveClient
 * @see SpatialClient
 * @see CadastreClient
 */
public interface AbstractWSClient {

    /**
     * @return The username configured for the service
     */
    String getUserName();

    /**
     * Sets the login credentials on the web service client so that each web service request can
     * pass the appropriate credentials to the web service.
     *
     * @param userName The username to authenticate the client with the web service
     * @param password The password to authenticate the client with the web service
     */
    void setCredentials(String userName, char[] password);

    /**
     * @return The url of the web service WSDL.
     */
    String getUrl();

    /**
     * Sets the url to the web service WSDL
     *
     * @param url
     */
    void setUrl(String url);

    /**
     * Verifies the web service client has a valid connection to the web service.
     *
     * @return true if the test is successful. If the checkConnection fails, a 
     * WebServiceClientException will most likely get thrown. 
     * @throws WebServiceClientException
     */
    boolean checkConnection() throws WebServiceClientException;
}
