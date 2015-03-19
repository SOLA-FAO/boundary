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

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.bulkoperations.BulkOperations;
import org.sola.webservices.bulkoperations.BulkoperationsService;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.transaction.TransactionBulkOperationSpatialTO;
import org.sola.webservices.transferobjects.transaction.TransactionBulkOperationSourceTO;

/**
 *
 * @author Elton Manoku
 */
public class BulkOperationsClientImpl  extends AbstractWSClientImpl implements BulkOperationsClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/bulkoperations";
    private static final String LOCAL_PART = "bulkoperations-service";

    /**
     * Creates a web service client class for the web service hosted at the
     * specified URL
     *
     * @param url The location of the service WSDL
     */
    public BulkOperationsClientImpl(String url) {
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }

    protected BulkOperations getPort() {
        return getPort(BulkOperations.class, BulkoperationsService.class);
    }

    @Override
    public boolean checkConnection() throws WebServiceClientException {
        boolean result = false;
        final String methodName = CadastreClient.CHECK_CONNECTION;
        try {
            beforeWebMethod(methodName);
            result = getPort().checkConnection();
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result);
        }
        return result;
    }
    
    @Override
    public boolean rejectTransaction(
            String transactionId, int rowVersion) throws WebServiceClientException {
        final String methodName = BulkOperationsClient.REJECT_TRANSACTION;
        try {
            beforeWebMethod(methodName, transactionId, rowVersion);
            return getPort().rejectTransaction(transactionId, rowVersion);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, boolean.class, transactionId, rowVersion);
        }
        return false;
    }

    @Override
    public List<ValidationResult> saveTransactionBulkOperationSpatial(
            TransactionBulkOperationSpatialTO transactionTO) 
    throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = BulkOperationsClient.SAVE_TRANSACTION_BULK_OPERATION_SPATIAL;
        String languageCode = this.getLanguageCode();
        try {
            beforeWebMethod(methodName, transactionTO, languageCode);
            result = getPort().saveTransactionBulkOperationSpatial(
                    transactionTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, transactionTO, languageCode);
        }
        return result;
    }

    @Override
    public TransactionBulkOperationSpatialTO getTransactionBulkOperationSpatial(
            String transactionId) throws WebServiceClientException {
        TransactionBulkOperationSpatialTO result = null;
        final String methodName = BulkOperationsClient.GET_TRANSACTION_BULK_OPERATION_SPATIAL;
        try {
            beforeWebMethod(methodName, transactionId);
            result = getPort().getTransactionBulkOperationSpatial(transactionId);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, transactionId);
        }
        return result;
    }
        
    @Override
    public List<ValidationResult> saveTransactionBulkOperationSource(
            TransactionBulkOperationSourceTO transactionTO) 
    throws WebServiceClientException {
        List<ValidationResult> result = null;
        final String methodName = BulkOperationsClient.SAVE_TRANSACTION_BULK_OPERATION_SOURCE;
        String languageCode = this.getLanguageCode();
        try {
            beforeWebMethod(methodName, transactionTO, languageCode);
            result = getPort().saveTransactionBulkOperationSource(
                    transactionTO, languageCode);
        } catch (Exception e) {
            processException(methodName, e);
        } finally {
            afterWebMethod(methodName, result, transactionTO, languageCode);
        }
        return result;
    }

}
