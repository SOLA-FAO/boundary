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
package org.sola.services.boundary.wsclients.exception;

/**
 * The type of exception used by {@linkplain WebServiceClientException}. 
 * <li> {@linkplain  WebServiceClientExceptionType#SERVICE_GENERAL SERVICE_GENERAL} - A general service
 * fault. Mapped from fault type {@code SOLAFault}. </li>
 * <li> {@linkplain  WebServiceClientExceptionType#SERVICE_UNHANDLED SERVICE_UNHANDLED} - A fault
 * that did not have any specific error handling in the services. Mapped from fault type 
 * {@code UnhandledFault}. </li>
 * <li> {@linkplain  WebServiceClientExceptionType#OPTIMISTIC_LOCK OPTIMISTIC_LOCK} - A fault 
 * representing an optimistic locking fault on the services. Mapped from fault type 
 * {@code OptimisticLockingFault}.</li>
 * <li> {@linkplain  WebServiceClientExceptionType#CLIENT CLIENT} - A service client exception. This 
 * type of exception may or may not have been caught by the web service client. Refer to the message
 * code and message parameters for specific error information.</li>
 * <li> {@linkplain  WebServiceClientExceptionType#AUTHENTICATION_FAILED AUTHENTICATION_FAILED} 
 * - The web service client was unable to connect to the web service because the security credentials
 * provided where not valid. </li>
 * @author amcdowell
 * @see WebServiceClientException
 */
public enum WebServiceClientExceptionType {
    SERVICE_GENERAL, 
    SERVICE_UNHANDLED, 
    OPTIMISTIC_LOCK, 
    CLIENT, 
    AUTHENTICATION_FAILED, 
    SOLA_VALIDATION_FAILED,
    ACCESS_VIOLATION;
}
