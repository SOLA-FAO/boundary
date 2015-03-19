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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.wsclients;

import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sola.common.messaging.ServiceMessage;
import static org.junit.Assert.*;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;

/**
 *
 * @author soladev
 */
public class WSClientFactoryIT {

    public WSClientFactoryIT() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Check that a valid connection can be made to the web service. Also includes failure tests
     */
    @Test
    public void testGetCaseManagementClient_ServiceConnection() {
        System.out.println("getCaseManagementClient_ServiceConnection");

        // Valid service connection
        System.out.println("Valid Service Connection - Case Management");
        String url = "http://localhost:8080/sola/webservices/casemanagement-service?wsdl";
        CaseManagementClient result = WSManager.getInstance().getWSClient(
                CaseManagementClientImpl.class, url, "test", "test".toCharArray());
        // result.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result.checkConnection());


        // Failed to Authenticate message should display
        result.setCredentials("test", new char[]{'f', 'a', 'i', 'l'});
        try {
            result.checkConnection();
            fail("Did not raise failed to authenticate exception!");
        } catch (WebServiceClientException ex) {
            assertEquals(WebServiceClientExceptionType.AUTHENTICATION_FAILED, ex.getType());
        }

        // Check the service connection is still valid dispite the failure above. 
        result.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result.checkConnection());

        // Reset the URL to a malformed version and check the expected error is displayed i.e. no :
        result.setUrl("http//localhost:8080/sola/webservices/casemanagement-service?wsdl");
        try {
            result.checkConnection();
            fail("Did not raise url format exception!");
        } catch (WebServiceClientException ex) {
            assertEquals(ServiceMessage.EXCEPTION_MALFORMED_URL, ex.getMessageCode());
            System.out.println("Exception information 0: " + ex.toString());
        }

        // Reset the URL to an invalid host
        result.setUrl("http://localho:8080/sola/webservices/casemanagement-service?wsdl");
        try {
            result.checkConnection();
            fail("Did not raise service connection exception 1!");
        } catch (WebServiceClientException ex) {
            assertEquals(ServiceMessage.EXCEPTION_SERVICE_CONNECTION, ex.getMessageCode());
            System.out.println("Exception information 1: " + ex.toString());
        }

        // Reset the URL to an invalid service
        result.setUrl("http://localhost:8080/sola/webservices/casemanagem-service?wsdl");
        try {
            result.checkConnection();
            fail("Did not raise service connection exception 2");
        } catch (WebServiceClientException ex) {
            assertEquals(ServiceMessage.EXCEPTION_SERVICE_CONNECTION, ex.getMessageCode());
            System.out.println("Exception information 2: " + ex.toString());
        }

        // Reset the URL to not reference wsdl - this should reload the service and succeed
        result.setUrl("http://localhost:8080/sola/webservices/casemanagement-service");
        assertTrue(result.checkConnection());

        // Valid service connection - Security
        System.out.println("Valid Service Connection - Admin");
        url = "http://localhost:8080/sola/webservices/admin-service?wsdl"; 
        AdminClient result2 = WSManager.getInstance().getWSClient(
                AdminClientImpl.class, url, "test", "test".toCharArray());
        assertTrue(result2.checkConnection());

        // Valid service connection - Reference data
        System.out.println("Valid Service Connection - Reference Data");
        url = "http://localhost:8080/sola/webservices/referencedata-service?wsdl";
        ReferenceDataClient result3 = WSManager.getInstance().getWSClient(
                ReferenceDataClientImpl.class, url, "test", "test".toCharArray());
        assertTrue(result3.checkConnection());

        // Valid service connection - Search
        System.out.println("Valid Service Connection - Search");
        url =  "http://localhost:8080/sola/webservices/search-service?wsdl";
        SearchClient result4 = WSManager.getInstance().getWSClient(
                SearchClientImpl.class, url, "test", "test".toCharArray());
        assertTrue(result4.checkConnection());

        // Valid service connection - DigitalArchive
        System.out.println("Valid Service Connection - Digital Archive");
        url = "http://localhost:8080/sola/webservices/digitalarchive-service?wsdl";
        DigitalArchiveClient result5 = WSManager.getInstance().getWSClient(
                DigitalArchiveClientImpl.class, url, "test", "test".toCharArray());
        assertTrue(result5.checkConnection());

        // Valid service connection - Spatial
        System.out.println("Valid Service Connection - Spatial");
        url = "http://localhost:8080/sola/webservices/spatial-service?wsdl";
        SpatialClient result6 = WSManager.getInstance().getWSClient(
                SpatialClientImpl.class, url, null, null);
        assertTrue(result6.checkConnection());

        // Valid service connection - Administrative
        System.out.println("Valid Service Connection - Administrative");
        url = "http://localhost:8080/sola/webservices/administrative-service?wsdl";
        AdministrativeClient result7 = WSManager.getInstance().getWSClient(
                AdministrativeClientImpl.class, url, "test", "test".toCharArray());
        assertTrue(result7.checkConnection());

        // Valid service connection - Spatial
        System.out.println("Valid Service Connection - Cadastre");
        url = "http://localhost:8080/sola/webservices/cadastre-service?wsdl";
        CadastreClient result8 = WSManager.getInstance().getWSClient(
                CadastreClientImpl.class, url, "test", "test".toCharArray());
        assertTrue(result8.checkConnection());
    }
}