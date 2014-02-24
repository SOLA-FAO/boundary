/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
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
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package org.sola.services.boundary.wsclients;

import org.junit.*;
import org.sola.webservices.transferobjects.casemanagement.ApplicationTO;
import org.sola.webservices.transferobjects.transaction.TransactionCadastreChangeTO;

/**
 *
 * @author Manoku
 */
public class Development {
    public Development() {
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
    @Ignore
    public void testGetCaseManagementMethods() {
        System.out.println("test different case management methods for development");

        // Valid service connection
        System.out.println("Valid Service Connection - Case Management");
        String url = "http://localhost:8080/sola/webservices/casemanagement-service?wsdl";
        CaseManagementClient result = WSManager.getInstance().getWSClient(
                CaseManagementClientImpl.class, url, "test", "test".toCharArray());
        System.out.print("Checking connection:");
        System.out.println(result.checkConnection());

        ApplicationTO app = result.getApplication("3000");
        System.out.println("Application found: " + app.getId());
        
        System.out.println("Test Application actions");
        System.out.println("Test assign:" 
                + result.applicationActionAssign(app.getId(), "1000", app.getRowVersion()));
        //System.out.println("Validate result:" + result.validate("3009").size());
    }        

    @Test
    @Ignore
    public void testCadastre() {
        System.out.println("test cadastre");

        // Valid service connection
        System.out.println("Valid Service Connection - Case Management");
        String url = "http://localhost:8080/sola/webservices/cadastre-service?wsdl";
        CadastreClient result = WSManager.getInstance().getWSClient(
                CadastreClientImpl.class, url, "test", "test".toCharArray());
        System.out.print("Checking connection:");
                System.out.println(result.checkConnection());

        System.out.println("test GetCadastreObjectByParts");
        System.out.println("Validate result:" + result.getCadastreObjectByParts("Lot 59 32488").size());
        
        System.out.println("test getTransactionCadastreChange");
        TransactionCadastreChangeTO obj = result.getTransactionCadastreChange("4086");
        System.out.println("Result: " + obj.toString());
    }        
    
    @Test
    @Ignore
    public void testSearch() {
        System.out.println("test search");

        // Valid service connection
        System.out.println("Valid Service Connection - Case Management");
        String url = "http://localhost:8080/sola/webservices/search-service?wsdl";
        SearchClient result = WSManager.getInstance().getWSClient(
                SearchClientImpl.class, url, "test", "test".toCharArray());
        System.out.print("Checking connection:");
                System.out.println(result.checkConnection());

//               GenericResult res = result.test();
//        System.out.println("test");
//        System.out.println("result:" + res);
        
    }        

    @Ignore
    @Test
    public void testAdministrative() {
        System.out.println("test Administrative");

        // Valid service connection
        System.out.println("Valid Service Connection - Administrative");
        String url = "http://localhost:8080/sola/webservices/administrative-service?wsdl";
        AdministrativeClient result = WSManager.getInstance().getWSClient(
                AdministrativeClientImpl.class, url, "test", "test".toCharArray());
        System.out.print("Checking connection:");
                System.out.println(result.checkConnection());

    }        
}
