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
package org.sola.services.boundary.wsclients;

import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sola.common.messaging.ServiceMessage;
import static org.junit.Assert.*;

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
        CaseManagementClient result = WSClientFactory.getCaseManagementClient(url);
        result.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result.checkConnection());
        

        // Failed to Authenticate message should display
        result.setCredentials("test", new char[]{'f', 'a', 'i', 'l'});
        try {
            result.checkConnection();
            fail("Did not raise failed to authenticate exception!");
        } catch (WebServiceClientException ex) {
            assertEquals(ServiceMessage.EXCEPTION_AUTHENTICATION_FAILED, ex.getMessageCode());
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
        System.out.println("Valid Service Connection - Security");
        AdminClient result2 = WSClientFactory.getSystemClient(
                "http://localhost:8080/sola/webservices/admin-service?wsdl");
        result2.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result2.checkConnection());

        // Valid service connection - Reference data
        System.out.println("Valid Service Connection - Reference Data");
        ReferenceDataClient result3 = WSClientFactory.getReferenceDataClient(
                "http://localhost:8080/sola/webservices/referencedata-service?wsdl");
        result3.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result3.checkConnection());

        // Valid service connection - Search
        System.out.println("Valid Service Connection - Search");
        SearchClient result4 = WSClientFactory.getSearchClient(
                "http://localhost:8080/sola/webservices/search-service?wsdl");
        result4.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result4.checkConnection());

        // Valid service connection - DigitalArchive
        System.out.println("Valid Service Connection - Digital Archive");
        DigitalArchiveClient result5 = WSClientFactory.getDigitalArchiveClient(
                "http://localhost:8080/sola/webservices/digitalarchive-service?wsdl");
        result5.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result5.checkConnection());
        
        // Valid service connection - Spatial
        System.out.println("Valid Service Connection - Spatial");
        SpatialClient result6 = WSClientFactory.getSpatialClient(
                "http://localhost:8080/sola/webservices/spatial-service?wsdl");
        //result6.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result6.checkConnection());    
        
                // Valid service connection - Administrative
        System.out.println("Valid Service Connection - Administrative");
        AdministrativeClient result7 = WSClientFactory.getAdministrativeClient(
                "http://localhost:8080/sola/webservices/administrative-service?wsdl");
        result7.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result7.CheckConnection());
        
                        // Valid service connection - Spatial
        System.out.println("Valid Service Connection - Cadastre");
        CadastreClient result8 = WSClientFactory.getCadastreClient(
                "http://localhost:8080/sola/webservices/cadastre-service?wsdl");
        result8.setCredentials("test", new char[]{'t', 'e', 's', 't'});
        assertTrue(result8.checkConnection());
    }

}