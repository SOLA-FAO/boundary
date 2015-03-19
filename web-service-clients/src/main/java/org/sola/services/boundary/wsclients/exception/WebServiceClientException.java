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

import java.util.List;
import org.sola.webservices.faults.FaultInfoBean;
import org.sola.webservices.transferobjects.ValidationResult;

/**
 * This exception wraps the faults and exceptions returned from the SOLA Services to 
 * simplify the handling of service related exceptions in SOLA client applications. 
 * <p>When a service fault is caught by the web service client, the type of fault (e.g.  {@code SOLAFault}, 
 * {@code OptimisticLockingFault} or {@code UnhandledFault}) is mapped to the appropriate  
 * {@linkplain WebServiceClientExceptionType} type i.e. 
 * {@linkplain  WebServiceClientExceptionType#SERVICE_GENERAL SERVICE_GENERAL},
 * {@linkplain  WebServiceClientExceptionType#OPTIMISTIC_LOCK OPTIMISTIC_LOCK} and
 * {@linkplain  WebServiceClientExceptionType#SERVICE_UNHANDLED SERVICE_UNHANDLED}. The details of 
 * the fault are also copied to the new {@linkplain WebServiceClientException}. 
 * Exceptions that occur on the client end are also mapped to an appropriate 
 * {@linkplain WebServiceClientExceptionType} e.g. 
 * {@linkplain  WebServiceClientExceptionType#CLIENT CLIENT} or
 * {@linkplain  WebServiceClientExceptionType#AUTHENTICATION_FAILED AUTHENTICATION_FAILED}</p>
 * <p> This is an unchecked exception. If a client application has a default exception handler in 
 * place, further error handling in the client application will be not required unless the client
 * application needs to undertake specific actions in response to certain service faults.</p>
 * <p>This exception uses a message code. To display the actual message for this exception, 
 * it is necessary to map the message code using the 
 * {@linkplain org.sola.common.messaging.MessageUtility}.
 * @see org.sola.common.messaging.MessageUtility
 * @see WebServiceClientExceptionType
 * @see org.sola.common.messaging.ServiceMessage
 * @see org.sola.services.boundary.wsclients.AbstractWSClientImpl
 * @see org.sola.services.boundary.wsclients.CaseManagementClientImpl
 * @author amcdowell
 */
public class WebServiceClientException extends RuntimeException {

    private Object[] messageParameters;
    private String errorNumber;
    private WebServiceClientExceptionType type;
    private List<ValidationResult> validationResult;

    /**
     * Constructs an instance of <code>WebServiceClientException</code> with the specified message 
     * code. The message code must be mapped to the appropriate message using the 
     * {@linkplain org.sola.common.messaging.MessageUtility}. 
     * Defaults the type of exception to {@linkplain  WebServiceClientExceptionType#CLIENT CLIENT}. 
     * @param msg The code for the message.
     * @see org.sola.common.messaging.ServiceMessage
     * @see org.sola.common.messaging.MessageUtility
     */
    public WebServiceClientException(String messageCode) {
        super(messageCode);
        this.type = WebServiceClientExceptionType.CLIENT;
    }

    /**
     * Constructs an instance of <code>WebServiceClientException</code> with the specified message 
     * code and message parameters. The message code must be mapped to the appropriate message 
     * using the {@linkplain org.sola.common.messaging.MessageUtility}.
     * Defaults the type of exception to {@linkplain  WebServiceClientExceptionType#CLIENT CLIENT}.
     * @param messageCode The code for the message.
     * @param messageParameters The message parameters. 
     * @see org.sola.common.messaging.ServiceMessage
     * @see org.sola.common.messaging.MessageUtility
     */
    public WebServiceClientException(String messageCode, Object[] messageParameters) { //NOSONAR
        super(messageCode);
        this.type = WebServiceClientExceptionType.CLIENT;
        this.messageParameters = messageParameters; //NOSONAR
    }
    
    /**
     * Constructs an instance of <code>WebServiceClientException</code> with the specified message 
     * code and exception type. The message code must be mapped to the appropriate message 
     * using the {@linkplain org.sola.common.messaging.MessageUtility}.
     * @param messageCode The code for the message. 
     * @param type The type of the ws client exception. 
     * @see org.sola.common.messaging.ServiceMessage
     * @see WebServiceClientExceptionType
     * @see org.sola.common.messaging.MessageUtility
     */
    public WebServiceClientException(String messageCode, WebServiceClientExceptionType type) {
        super(messageCode);
        this.type = type;
    }

    /**
     * Constructs an instance of <code>WebServiceClientException</code> using the specified 
     * exception type and fault bean. The fault bean may include an error number and/or message
     * parameters along with the message code. The message code must be mapped to the 
     * appropriate message using the {@linkplain org.sola.common.messaging.MessageUtility}.
     * @param type The type of the web service client exception. 
     * @param faultBean The fault bean returned by the web service. 
     * @see org.sola.common.messaging.ServiceMessage
     * @see WebServiceClientExceptionType
     * @see org.sola.webservices.faults.FaultInfoBean
     * @see org.sola.common.messaging.MessageUtility
     */
    public WebServiceClientException(WebServiceClientExceptionType type, FaultInfoBean faultBean) {
        super(faultBean.getMessageCode());
        this.type = type;
        this.errorNumber = faultBean.getFaultId();
        this.messageParameters = faultBean.getMessageParameters().toArray();
        this.validationResult = faultBean.getValidationResultList();
    }

    /** Returns the message code for the exception */
    public String getMessageCode() {
        return this.getMessage();
    }

    /** Returns the error number for the exception. <p>The error number is generated by the SOLA 
     * services to uniquely identify the error and simplify tracking of the error in the SOLA Services
     * log file.</p> */
    public String getErrorNumber() {
        return errorNumber;
    }

    /** Returns the array of message parameters */
    public Object[] getMessageParameters() {
        return messageParameters;
    }

    /** Returns the type of exception */
    public WebServiceClientExceptionType getType() {
        return type;
    }

    /** Returns validation result in case of violation of business rules. */
    public List<ValidationResult> getValidationResult() {
        return validationResult;
    }

    /** Sets validation result. */
    public void setValidationResult(List<ValidationResult> validationResult) {
        this.validationResult = validationResult;
    }

    /**
     * Returns a string containing the message code and the values of the message parameters. 
     */
    @Override
    public String toString() {
        String result = "Message code: " + this.getMessageCode();
        if (this.messageParameters != null && this.messageParameters.length > 0) {
            int idx = 1;
            for (Object obj : this.messageParameters) {
                result = result + System.getProperty("line.separator")
                        + "Param " + idx + ": " + (obj == null ? "empty" : obj.toString()); 
                idx++;
            }
        }
        return result; 
    }
}
