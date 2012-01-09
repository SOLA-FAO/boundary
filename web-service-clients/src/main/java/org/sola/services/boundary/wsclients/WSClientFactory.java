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

import org.sola.services.boundary.wsclients.mock.MockCaseManagementClient;
import org.sola.services.boundary.wsclients.mock.MockDigitalArchiveClient;
import org.sola.services.boundary.wsclients.mock.MockReferenceDataClient;
import org.sola.services.boundary.wsclients.mock.MockSearchClient;
import org.sola.services.boundary.wsclients.mock.MockAdminClient;
import org.sola.services.boundary.wsclients.mock.MockServiceManager;
import org.sola.services.boundary.wsclients.mock.MockSpatialClient;

/**
 * Provides factory methods for creating web service client classes. This includes creating
 * mock service clients if the mockService flag has been configured in
 * {@linkplain org.sola.services.boundary.wsclients.mock.MockServiceManager}.
 * <p>This class does not perform any caching of the ws clients it creates. To avoid re-creating
 * service connections for every web service method call, the client object returned from these
 * factory methods should be cached for subsequent use in a static or singleton class. </p>
 * @author amcdowell
 */
public class WSClientFactory {

    /** Creates a CaseManagementClient.
    @see WSClientFactory */
    public static CaseManagementClient getCaseManagementClient(String url) {
        CaseManagementClient result = null;
        if (MockServiceManager.getInstance().isMockService()) {
            result = new MockCaseManagementClient(url);
        } else {
            result = new CaseManagementClientImpl(url);
        }
        return result;
    }

    /** Creates a DigitalArchiveClient.
    @see WSClientFactory */
    public static DigitalArchiveClient getDigitalArchiveClient(String url){
        DigitalArchiveClient result = null;
        if(MockServiceManager.getInstance().isMockService()){
            result = new MockDigitalArchiveClient(url);
        }
        else{
            result = new DigitalArchiveClientImpl(url);
        }
        return result;
    }
    
    /** Creates a AdministrativeClient.
    @see WSClientFactory */
    public static AdministrativeClient getAdministrativeClient(String url){
        AdministrativeClient result = null;
        //TODO: Implement mocking client
        //if(MockServiceManager.getInstance().isMockService()){

        //}
        //else{
            result = new AdministrativeClientImpl(url);
        //}
        return result;
    }
    
    /** Creates a ReferenceDataClient.
    @see WSClientFactory */
    public static ReferenceDataClient getReferenceDataClient(String url) {
        ReferenceDataClient result = null;
        if (MockServiceManager.getInstance().isMockService()) {
            result = new MockReferenceDataClient(url);
        } else {
            result = new ReferenceDataClientImpl(url);
        }
        return result;
    }

    /** Creates a SecurityClient.
    @see WSClientFactory */
    public static AdminClient getSystemClient(String url) {
        AdminClient result = null;
        if (MockServiceManager.getInstance().isMockService()) {
            result = new MockAdminClient(url);
        } else {
            result = new AdminClientImpl(url);
        }
        return result;
    }

    /** Creates a SearchClient.
    @see WSClientFactory */
    public static SearchClient getSearchClient(String url) {
        SearchClient result = null;
        if (MockServiceManager.getInstance().isMockService()) {
            result = new MockSearchClient(url);
        } else {
            result = new SearchClientImpl(url);
        }
        return result;
    }
    
    /** Creates a SpatialClient.
    @see WSClientFactory */
    public static SpatialClient getSpatialClient(String url) {
        SpatialClient result = null;
        if (MockServiceManager.getInstance().isMockService()) {
            result = new MockSpatialClient(url);
        } else {
            result = new SpatialClientImpl(url);
        }
        return result;
    }

    /** Creates a CadastreClient.
    @see WSClientFactory */
    public static CadastreClient getCadastreClient(String url) {
        CadastreClient result = null;
        if (MockServiceManager.getInstance().isMockService()) {
            //result = new MockSpatialClient(url);
        } else {
            result = new CadastreClientImpl(url);
        }
        return result;
    }
}
