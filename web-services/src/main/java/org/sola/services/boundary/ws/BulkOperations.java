/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.ws;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.boundary.transferobjects.transaction.TransactionBulkOperationSourceTO;
import org.sola.services.boundary.transferobjects.transaction.TransactionBulkOperationSpatialTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.br.ValidationResult;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.*;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.search.businesslogic.SearchEJBLocal;
import org.sola.services.ejb.source.businesslogic.SourceEJBLocal;
import org.sola.services.ejb.source.repository.entities.TransactionBulkOperationSource;
import org.sola.services.ejb.transaction.businesslogic.TransactionEJBLocal;
import org.sola.services.ejb.transaction.repository.entities.TransactionBulkOperationSpatial;
import org.sola.services.ejb.transaction.repository.entities.TransactionType;

/**
 *
 * @author Elton Manoku
 */
@WebService(serviceName = "bulkoperations-service",
targetNamespace = ServiceConstants.BULK_OPERATIONS_WS_NAMESPACE)
public class BulkOperations extends AbstractWebService {

    @EJB
    private TransactionEJBLocal transactionEJB;
    @EJB
    private SourceEJBLocal sourceEJB;
    @EJB
    private SearchEJBLocal searchEJB;
    @Resource
    private WebServiceContext wsContext;

    /**
     * Web method that can be used to validate if the web service is available.
     *
     * @return Always true
     */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#rejectTransactionById(org.sola.services.ejb.transaction.repository.entities.TransactionBasic,
     * java.lang.String, java.lang.String) TransactionEJB.rejectTransactionById}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "RejectTransaction")
    public boolean RejectTransaction(
            @WebParam(name = "transactionId") 
                    final String transactionId,
            @WebParam(name = "rowVersion") final int rowVersion)
            throws SOLAValidationFault, OptimisticLockingFault,
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = transactionEJB.rejectTransactionWithId(transactionId, rowVersion);
            }
        });

        return result[0].equals(Boolean.TRUE);
    }

    @WebMethod(operationName = "GetTransactionBulkOperationSpatial")
    public TransactionBulkOperationSpatialTO GetTransactionBulkOperationSpatial(
            @WebParam(name = "transactionId") final String transactionId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(
                        transactionEJB.getTransactionById(transactionId, TransactionBulkOperationSpatial.class),
                        TransactionBulkOperationSpatialTO.class);
            }
        });

        return (TransactionBulkOperationSpatialTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.transaction.businesslogic.TransactionEJB#saveTransaction(org.sola.services.ejb.transaction.repository.entities.TransactionBasic,
     * java.lang.String, java.lang.String) TransactionEJB.saveTransaction}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveTransactionBulkOperationSpatial")
    public List<ValidationResult> SaveTransactionBulkOperationSpatial(
            @WebParam(name = "transactionTO") 
                    final TransactionBulkOperationSpatialTO transactionTO,
            @WebParam(name = "languageCode") final String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault,
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                TransactionBulkOperationSpatial targetTransaction =
                        transactionEJB.getTransactionById(
                        transactionTO.getId(), TransactionBulkOperationSpatial.class);
                TransactionBulkOperationSpatial transaction = GenericTranslator.fromTO(
                        transactionTO, TransactionBulkOperationSpatial.class, targetTransaction);
                result[0] = transactionEJB.saveTransaction(
                        transaction, TransactionType.BULK_OPERATION_SPATIAL, languageCode);
            }
        });

        return (List<ValidationResult>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SourceEJB#
     * saveTransactionBulkOperation(
     * org.sola.services.ejb.source.repository.entities.TransactionBulkOperationSource,
     * java.lang.String) Source.saveTransactionBulkOperation}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveTransactionBulkOperationSource")
    public List<ValidationResult> SaveTransactionBulkOperationSource(
            @WebParam(name = "transactionTO") 
                    final TransactionBulkOperationSourceTO transactionTO,
            @WebParam(name = "languageCode") final String languageCode)
            throws SOLAValidationFault, OptimisticLockingFault,
            SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runUpdateValidation(wsContext, new Runnable() {

            @Override
            public void run() {
                TransactionBulkOperationSource targetTransaction =
                        sourceEJB.getTransactionBulkOperationById(transactionTO.getId());
                TransactionBulkOperationSource transaction = GenericTranslator.fromTO(
                        transactionTO, 
                        TransactionBulkOperationSource.class, 
                        targetTransaction);
                result[0] = sourceEJB.saveTransactionBulkOperation(transaction, languageCode);
            }
        });

        return (List<ValidationResult>) result[0];
    }

}
