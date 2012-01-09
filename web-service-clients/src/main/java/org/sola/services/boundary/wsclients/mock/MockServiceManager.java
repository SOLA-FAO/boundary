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
package org.sola.services.boundary.wsclients.mock;

import org.sola.services.boundary.wsclients.exception.MockResponseException;
import java.util.HashMap;
import java.util.Map;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;

/**
 * This singleton can be used to provide mock responses for web service methods when testing client
 * code. To setup, use {@linkplain #setMockService(boolean) setMockService(true)} or set a mock 
 * response for a web method using the {@linkplain #setResponse(String, Object) setResponse} method.
 * This will cause the WSClientFactory to create mock service client objects that implement the 
 * appropriate web service client interface. 
 * <p> Every mocked web method is configured with a default mock response. If a different response
 * is required, the developer/tester can set the response for a method explicitly using the 
 * {@linkplain #setResponse(String, Object) setResponse} method. The mock response can be an object 
 * that matches the return type of the web method, a {@linkplain WebServiceClientException} or an 
 * anonymous inner class that implements the {@linkplain MockResponse} interface. The anonymous 
 * inner class can be used to perform complex processing for the mock response including 
 * manipulation of the arguments passed to the web method during the test run.</p>
 * <p>Example usage:</p>
 * <blockquote><pre>
 * ... Unit test setup
 * MockServiceManager mockMan = MockServiceManager.getInstance();
 * mockMan.setMockService(true);
 * mockMan.setResponse(MockCaseManagementClient.CHECK_CONNECTION, false);
 * ... The code that calls the CaseManagementClient.checkConnection web method
 * </pre></blockquote>
 * @see #setMockService(boolean)
 * @see #setResponse(String, Object)
 * @see #getResponse(String, Class, Object, Object[]) 
 * @see WebServiceClientException
 * @see MockResponse
 * @author amcdowell
 */
public class MockServiceManager {

    private boolean mockService = false;
    private Map<String, Object> responseData;

    private MockServiceManager() {
    }

    /** @return Instantiated singleton instance of {@linkplain MockServiceManager}. */
    public static MockServiceManager getInstance() {
        return MockServiceManagerHolder.INSTANCE;
    }

    private static class MockServiceManagerHolder {
        private static final MockServiceManager INSTANCE = new MockServiceManager();
    }

    /**
     * Processes the mock response object to determine if the object should be returned as is, 
     * an exception thrown or the anonymous inner class executed. 
     * @param <T> Represents the return type of the web method
     * @param responseClass The class of the return type. 
     * @param defaultResponse The default mock response. 
     * @param response The response object to process
     * @param methodArgs The web method arguments. These are passed in location order. 
     * @return The appropriate mock response object
     * @throws WebServiceClientException If the mock response object is set to this exception type.
     * @throws MockResponseException When the mock response object does not match the type represented
     * by {@code responseClass}
     */
    private <T> T processResponse(Class<T> responseClass, T defaultResponse, Object response,
            Object... methodArgs) throws WebServiceClientException, MockResponseException {
        T result = defaultResponse;
        if (responseClass.isAssignableFrom(response.getClass())) {
            // Return the mock response object
            result = (T) response;
        } else if (MockResponse.class.isAssignableFrom(response.getClass())) {
            // Obtain the mock response by calling the anonymous inner class. Need to process the
            // response object as it could be an exception or potentially an invalid type. 
            Object derivedResponse = ((MockResponse) response).getResponse(
                    defaultResponse, methodArgs);
            result = processResponse(responseClass, defaultResponse, derivedResponse, methodArgs);
        } else if (WebServiceClientException.class.isAssignableFrom(response.getClass())) {
            // Throw a WebServiceClientException exception as the response
            throw ((WebServiceClientException) response);
        } else {
            // The response type does not match the return type for the web method and is not one
            // of the recognised types (e.g. MockResponse or WebServiceClientException). Throw a 
            // MockResponseException to fail the test. 
            throw new MockResponseException("Unable to convert the mocked response object to "
                    + "the correct class type. Expected response type: " + responseClass.getName()
                    + ", actual response type: " + response.getClass().getName());
        }
        return result;
    }

    /** @return Flag indicating that a mock service should be used. */
    public boolean isMockService() {
        return mockService;
    }

    /** Sets the flag indicating that a mock service should be used (<code>true</code>) 
     * or not (<code>false</code>)
     * @param mockService */
    public void setMockService(boolean mockService) {
        this.mockService = mockService;
    }

    /**
     * Used to set a mock response for the specified web method. A mock response can be an object
     * that matches the return type of the web method, a {@linkplain WebServiceClientException} 
     * or an anonymous inner class that implements the {@linkplain MockResponse} interface. 
     * Setting a mock response for a web method will also set the {@linkplain #mockService mockService} 
     * flag to ensure all service calls are mocked. 
     * <p>A method can only have one mock response. Calling the 
     * {@linkplain setResponse(String, Object) setResponse} method more than once during a test will 
     * replace the mock response with the new value. If {@code null} is passed as the response, 
     * any mock response previously set for the web method  will be cleared and the default mock 
     * response will be used for any subsequent calls to the web method.</p>
     * <p>Note that primitive data types (int, boolean, byte, etc) will get automatically "boxed" by
     * the JVM when calling this method. Any mock service class using the 
     * {@linkplain #getResponse(String, Class, Object, Object[]) getResponse} method will 
     * need to pass the appropriate boxed data type as the {@code responseClass} parameter. 
     * If a primitive type is used with {@linkplain #getResponse(String, Class, Object, Object[]) getResponse}, 
     * a {@linkplain MockResponseException} will be raised during the test.</p>
     * @param responseKey The identifier for the web method. The identifier is available as a 
     * constant on the mock web service client class. 
     * @param response The mock response for the web service method or {@code null} to clear 
     * any mock response that was previously set. 
     * @see MockResponse
     * @see WebServiceClientException
     * @see MockResponseException
     * @see #getResponse(String, Class, Object, Object[])
     */
    public void setResponse(String responseKey, Object response) {
        if (!mockService) {
            mockService = true;
        }
        if (responseData == null) {
            responseData = new HashMap<String, Object>();
        }
        if (responseData.containsKey(responseKey)) {
            responseData.remove(responseKey);
        }
        if (response != null) {
            responseData.put(responseKey, response);
        }
    }

    /**
     * Obtains the mock response to use for the a web method based on the {@code responseKey} value.  
     * <p>If the web method returns a primitive type, the {@code responseClass} parameter must be 
     * the "boxed" version of the type. E.g. for a web method that returns {@code int}, 
     * {@code responseClass} it should be {@code Integer.class}, for {@code boolean} it should be 
     * {@code Boolean.class}, etc. </p>
     * @param <T> Represents the return type of the web method.
     * @param responseKey The key value identifying the web method. Constants for each web method
     * can be found on the appropriate mock web service client class. 
     * @param responseClass The class of the response to be returned. Must be a boxed type if the
     * web method returns a primitive type. 
     * @param defaultResponse The default response to use for the web method. This must be the same
     * type as {@code T}. 
     * @param methodArgs The parameters of the web method. These are passed in location order, so the
     * {@code methodArgs[0]} is the first parameter, {@code methodArg[1]} the second parameter, etc. 
     * If the web method has no parameters, {@code methodArgs} will be {@code null} or empty. 
     * @return The mock response object.
     * @throws WebServiceClientException If the mock response object is set to this exception type. 
     * @throws MockResponseException When the mock response object does not match the type represented
     * by {@code responseClass}
     * @see #setResponse(String, Object)
     */
    public <T> T getResponse(String responseKey, Class<T> responseClass,
            T defaultResponse, Object... methodArgs)
            throws WebServiceClientException, MockResponseException {
        T result = defaultResponse;
        if (responseData != null) {
            if (responseData.containsKey(responseKey)) {
                Object response = responseData.get(responseKey);
                result = processResponse(responseClass, defaultResponse, response, methodArgs);
            }
        }
        return result;
    }

    /**
     * Clears the list of response objects and resets the {@linkplain #mockService mockService} flag
     * to false. 
     * <p>This method should be called at the completion of every test to ensure the mock response
     * configured for the test data does not adversely impact subsequent tests.</p>
     */
    public void reset() {
        if (responseData != null) {
            responseData.clear();
        }
        mockService = false;
    }
}
