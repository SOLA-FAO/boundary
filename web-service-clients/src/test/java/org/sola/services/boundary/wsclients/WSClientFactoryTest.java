/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO). All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this list of conditions
 * and the following disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice,this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.wsclients;

import org.sola.webservices.transferobjects.referencedata.RequestTypeTO;
import org.sola.services.boundary.wsclients.mock.MockResponse;
import org.sola.services.boundary.wsclients.mock.MockResponseException;
import org.sola.webservices.transferobjects.casemanagement.ApplicationTO;
import org.sola.webservices.transferobjects.casemanagement.PartySummaryTO;
import java.util.List;
import org.sola.webservices.transferobjects.casemanagement.PartyTO;
import org.sola.services.boundary.wsclients.mock.MockServiceManager;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sola.common.messaging.ServiceMessage;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;
import org.sola.services.boundary.wsclients.mock.MockCaseManagementClient;
import static org.junit.Assert.*;

/**
 *
 * @author soladev
 */
public class WSClientFactoryTest {

    public WSClientFactoryTest() {
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
     * Checks if a MockResponseException has been wrapped by a WebServiceClientException
     *
     * @param clientEx
     * @return true if a MockResponseExcpetion has been raised.
     */
    private boolean isMockResponseException(WebServiceClientException clientEx) {
        return (clientEx.getMessageParameters() != null && clientEx.getMessageParameters().length == 3
                && MockResponseException.class.isAssignableFrom(clientEx.getMessageParameters()[2].getClass()));

    }

    /**
     * Preforms tests against the MockService Class to ensure the mocking functionality is working
     * as expected.
     */
    @Test
    public void testGetCaseManagementClient_MockConnection() {
        System.out.println("getCaseManagementClient_MockConnection");
        MockServiceManager man = MockServiceManager.getInstance();
        man.setMockServices(true);
        CaseManagementClient result = WSManager.getInstance().getCaseManagementService();

        // Check the default value is returned (i.e. true)
        assertTrue(result.checkConnection());

        // Set the method to return false
        man.setResponse(MockCaseManagementClient.CHECK_CONNECTION, false);
        assertFalse(result.checkConnection());

        // Check the previous response value has been cleared and the default used
        man.setResponse(MockCaseManagementClient.CHECK_CONNECTION, null);
        assertTrue(result.checkConnection());

        // Test an object type that takes an id
        PartyTO p = result.getParty("SomeId");
        assertEquals("SomeId", p.getId());

        // Check a response can be set for an object type
        man.setResponse(MockCaseManagementClient.GET_PARTY, p);
        PartyTO p2 = result.getParty("AnotherId");
        assertEquals("SomeId", p2.getId());

        // Check a default response for a List type
        List<PartySummaryTO> plist = result.getAgents();
        assertNotNull(plist);
        assertEquals(0, plist.size());

        // Check a setResponse for a list type
        plist.add(new PartySummaryTO());
        man.setResponse(MockCaseManagementClient.GET_AGENTS, plist);
        List<PartySummaryTO> plist2 = result.getAgents();
        assertNotNull(plist2);
        assertEquals(1, plist2.size());

        // Set an exception as the response to the method call
        WebServiceClientException ex =
                new WebServiceClientException(ServiceMessage.GENERAL_UNEXPECTED,
                WebServiceClientExceptionType.SERVICE_UNHANDLED);
        man.setResponse(MockCaseManagementClient.CALCULATE_FEE, ex);
        try {
            ApplicationTO app = result.calculateFee(new ApplicationTO());
            fail("Should have raised a WebServiceClientException");
        } catch (WebServiceClientException e) {
            assertEquals(ServiceMessage.GENERAL_UNEXPECTED, e.getMessageCode());
            assertEquals(WebServiceClientExceptionType.SERVICE_UNHANDLED, e.getType());
        }

        // Check that a MockResponseException is returned if the type of the data does not match. 
        man.setResponse(MockCaseManagementClient.GET_PARTY, new ApplicationTO());
        try {
            PartyTO p3 = result.getParty("AnotherId");
            fail("Should have raised a MockResponseException");
        } catch (WebServiceClientException clientEx) {
            if (!isMockResponseException(clientEx)) {
                fail("Should have raised a MockResponseException");
            };
        }

        // Check a response can be generated from an anonymous inner class that
        // implements the MockResponse interface
        man.setResponse(MockCaseManagementClient.GET_AGENTS, new MockResponse() {
            // Can only have final fields on an anonymous class, however it is possible
            // to cheat and create a final array that has mutable (i.e changable) elements
            // as is the case here. This particular array allows the test to keep track 
            // of the number of times this method has been called in the test and respond 
            // accordingly. This field is optional as it is not defined on the interface
            // but illustrates how to persist data accross multiple calls to the same web
            // method during one test. 

            final int[] count = {0}; // Default the first element of the array to 0. Could 
            // also new the array e.g. new int[1];

            // Create a method that fulfills the MockResponse interface
            @Override
            public Object getResponse(Object defaultResponse, Object... methodArgs) {
                Object result = defaultResponse;
                if (count[0] == 0) {
                    // This response hasn't been called before, so response the first response 
                    PartySummaryTO party1 = new PartySummaryTO();
                    party1.setId("mymockpartyid");
                    ((List) result).add(party1);
                    PartySummaryTO party2 = new PartySummaryTO();
                    party2.setId("my2ndmockpartyid");
                    ((List) result).add(party2);
                    count[0]++;
                } else if (count[0] > 0) {
                    // This method has been called by this test before, so throw and exception
                    result = new WebServiceClientException(ServiceMessage.GENERAL_UNEXPECTED_ERROR_DETAILS);
                }
                return result;
            }
        });
        List<PartySummaryTO> plist3 = result.getAgents();
        assertNotNull(plist3);
        assertEquals(2, plist3.size());
        assertEquals("mymockpartyid", plist3.get(0).getId());
        assertEquals("my2ndmockpartyid", plist3.get(1).getId());

        // Attempt second call to web method during the same test, but this time an exception
        // will be raised. 
        try {
            List<PartySummaryTO> plist4 = result.getAgents();
            fail("Did not raise exception when testing anonymous inner class");
        } catch (WebServiceClientException e) {
            assertEquals(ServiceMessage.GENERAL_UNEXPECTED_ERROR_DETAILS, e.getMessageCode());
            assertEquals(WebServiceClientExceptionType.CLIENT, e.getType());
        }
    }

    /**
     * Preforms tests against the Reference Data MockService Class to ensure the mocking
     * functionality is working as expected.
     */
    @Test
    public void testGetReferenceDataClient_MockConnection() {
        System.out.println("getReferenceDataClient_MockConnection()");
        MockServiceManager man = MockServiceManager.getInstance();
        man.setMockServices(true);
        ReferenceDataClient result = WSManager.getInstance().getReferenceDataService();

        // Check the default value is returned (i.e. true)
        assertTrue(result.checkConnection());

        assertEquals(5, result.getCommunicationTypes().size());
        assertEquals(14, result.getSourceTypes().size());
        assertEquals(14, result.getRequestTypes().size());
        List<RequestTypeTO> out = result.getRequestTypes();
        assertEquals(14, out.size());
    }

    /**
     * Preforms tests against the Admin MockService Class to ensure the mocking functionality is
     * working as expected.
     */
    @Test
    public void testGetAdminClient_MockConnection() {
        System.out.println("getReferenceDataClient_MockConnection()");
        MockServiceManager man = MockServiceManager.getInstance();
        man.setMockServices(true);
        AdminClient result = WSManager.getInstance().getAdminService();

        // Check the default value is returned (i.e. true)
        //assertTrue(result.checkConnection());

        assertTrue(result.changePassword("bob", "smith"));

        //man.setResponse(MockAdminPort.CHANAGE_PASSWORD,
        //new org.sola.webservices.admin.SOLAFault("test", new FaultInfoBean()));
        man.setResponse(AdminClient.CHANGE_PASSWORD,
                new WebServiceClientException("Some Message", WebServiceClientExceptionType.SERVICE_GENERAL));
        try {
            result.changePassword("bob", "smith");
            fail("Should have raised a SOLAFault");
        } catch (WebServiceClientException wscex) {
            System.out.println("Correct Fault");
            assertEquals(WebServiceClientExceptionType.SERVICE_GENERAL, wscex.getType());
        }

    }
}
