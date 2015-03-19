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
import javax.jws.WebParam;
import org.sola.webservices.transferobjects.ValidationResult;
import org.sola.webservices.transferobjects.transaction.TransactionBulkOperationSpatialTO;
import org.sola.webservices.transferobjects.transaction.TransactionBulkOperationSourceTO;

/**
 *
 * @author Elton Manoku
 */
public interface BulkOperationsClient extends AbstractWSClient {

    /**
     * Bulkoperations. - Service name prefix for the Cadastre Web Service
     */
    public static final String SERVICE_NAME = "BulkOperations.";
    /**
     * BulkOperations.checkConnection - Identifier for the checkConnection
     * method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * BulkOperations.checkConnection - Identifier for the checkConnection
     * method
     */
    public static final String REJECT_TRANSACTION = SERVICE_NAME + "rejectTransaction";
    /**
     * BulkOperations.getTransactionBulkOperationSpatial - Identifier for the
     * getTransactionBulkOperationSpatial method
     */
    public static final String GET_TRANSACTION_BULK_OPERATION_SPATIAL =
            SERVICE_NAME + "getTransactionBulkOperationSpatial";
    /**
     * BulkOperations.saveTransactionBulkOperationSpatial - Identifier for the
     * saveTransactionBulkOperationSpatial method
     */
    public static final String SAVE_TRANSACTION_BULK_OPERATION_SPATIAL =
            SERVICE_NAME + "saveTransactionBulkOperationSpatial";
    /**
     * BulkOperations.saveTransactionBulkOperationSource - Identifier for the
     * saveTransactionBulkOperationSource method
     */
    public static final String SAVE_TRANSACTION_BULK_OPERATION_SOURCE =
            SERVICE_NAME + "saveTransactionBulkOperationSource";
    
    /**
     * It rejects a bulk operation by removing the uploaded records.
     */
    boolean rejectTransaction(String transactionId, int rowVersion);

    /**
     * It creates or updates a bulk operation spatial transaction.
     *
     * @param transactionTO The transaction to create/save.
     * @return A list of validation results.
     */
    List<ValidationResult> saveTransactionBulkOperationSpatial(
            TransactionBulkOperationSpatialTO transactionTO);

    /**
     * Gets a transaction of bulk operation.
     *
     * @param transactionId The identifier of the transaction
     */
    TransactionBulkOperationSpatialTO getTransactionBulkOperationSpatial(String transactionId);

    /**
     * It creates or updates a bulk operation source transaction.
     *
     * @param transactionTO The transaction to create/save.
     * @return A list of validation results.
     */
    List<ValidationResult> saveTransactionBulkOperationSource(
            TransactionBulkOperationSourceTO transactionTO);

}
