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
