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
package org.sola.services.boundary.wsclients.mock;

/**
 * Interface that defines a method that can be implemented by an anonymous inner class
 * to create a customized mock response for a web method. Note that the arguments passed
 * to the web method are available for manipulation via the methodArgs varargs parameter. 
 * <p>Use of a anonymous inner class is only required if the creation of the mock response object
 * requires manipulation of method arguments or selection/iteration logic. Simple sceanrios that
 * only require a specific object to be returned can be satisfied by setting the approrprate mock
 * response object directly using the {@linkplain MockServiceManager#setResponse(String, Object)}
 * method. </p>
 * <p>
 * Example usage (manipulate method argument):
 * </p>
 * <blockquote><pre>
 *  MockServiceManager.getInstance().setResponse(MockCaseManagementClient.ADD_APPLICATION_ACTION, 
 *  new MockResponse() {
 *      public Object getResponse(Object defaultResponse, Object... methodArgs) {
 *          return ((Integer) methodArgs[3]) - 2;
 *      }
 *  });
 * </pre></blockqoute>
 * <p>
 * Example usage (selection logic):
 * </p>
 * <blockquote><pre>
 * MockServiceManager.getInstance().setResponse(MockCaseManagementClient.ADD_APPLICATION_ACTION, 
 *      new MockResponse() {
 *      // Can only have final fields on an anonymous class, however it is possible
 *      // to create a final array that has mutable (i.e changable) elements
 *      // as is the case here. This particular array allows the test to keep track 
 *      // of the number of times this method has been called in the test and respond 
 *      // accordingly. This field is optional as it is not defined on the interface
 *      // but illustrates how to persist data accross multiple calls to the same web
 *      // method during one test. 
 *      final int[] count = {0}; // Default the first element of the array to 0. Could 
 *                               // also new the array e.g. new int[1];
 *      public Object getResponse(Object defaultResponse, Object... methodArgs) {
 *          if (count[0] = 0) {
 *              count[0]++; 
 *              return ((Integer) methodArgs[3]) - 2;
 *          }
 *          else {
 *              throw new WebServiceClientException((ServiceMessage.GENERAL_CLIENT_UNEXPECTED);
 *          }
 *      }
 *  });
 * </pre></blockqoute>
 * @see WSClientFactoryTest
 * @see MockServiceManager
 * @see MockServiceManager#setResponse(String, Object) 
 * @see MockServiceManager#getResponse(String, Class, Object, Object[]) 
 * @author amcdowell
 */
public interface MockResponse {
    
    Object getResponse(Object defaultResponse, Object... methodArgs);   
}
