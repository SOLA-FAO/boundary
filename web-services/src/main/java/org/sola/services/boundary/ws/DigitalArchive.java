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
package org.sola.services.boundary.ws;

import java.io.File;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.common.FileUtility;
import org.sola.services.boundary.transferobjects.digitalarchive.DocumentBinaryTO;
import org.sola.services.boundary.transferobjects.digitalarchive.DocumentTO;
import org.sola.services.boundary.transferobjects.digitalarchive.FileBinaryTO;
import org.sola.services.boundary.transferobjects.digitalarchive.FileInfoTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.OptimisticLockingFault;
import org.sola.services.common.faults.SOLAAccessFault;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJBLocal;
import org.sola.services.digitalarchive.repository.entities.Document;

/**
 * Web Service Boundary class to expose {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB}
 * methods.
 */
@WebService(serviceName = "digitalarchive-service", targetNamespace = ServiceConstants.DIGITAL_ARCHIVE_WS_NAMESPACE)
public class DigitalArchive extends AbstractWebService {

    @EJB
    private DigitalArchiveEJBLocal digitalArchiveEJB;
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
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#getDocument(java.lang.String)
     * DigitalArchiveEJB.getDocument}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetDocument")
    public DocumentBinaryTO GetDocument(@WebParam(name = "documentId") String documentId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String documentIdTmp = documentId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                Document document = digitalArchiveEJB.getDocument(documentIdTmp);
                DocumentBinaryTO docTO = GenericTranslator.toTO(document, DocumentBinaryTO.class);
                // Write the fileContent to disk so that it can be efficiently streamed up to the client 
                // using the FileStreaming service. 
                if (document.getBody() != null) {
                    File file = FileUtility.writeFileToCache(document.getBody(), null);
                    if (file != null) {
                        docTO.setFileName(file.getAbsolutePath());
                    }
                }
                result[0] = docTO;
            }
        });

        return (DocumentBinaryTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#saveDocument(org.sola.services.digitalarchive.repository.entities.Document)
     * DigitalArchiveEJB.saveDocument}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SaveDocument")
    public DocumentTO SaveDocument(@WebParam(name = "documentTO") DocumentTO documentTO)
            throws SOLAFault, UnhandledFault, OptimisticLockingFault, SOLAAccessFault {

        final DocumentTO documentTOTmp = documentTO;
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {

            @Override
            public void run() {
                Document document = digitalArchiveEJB.saveDocument(GenericTranslator.fromTO(documentTOTmp,
                        Document.class, digitalArchiveEJB.getDocument(documentTOTmp.getId())));
                result[0] = GenericTranslator.toTO(document, DocumentTO.class);
            }
        });

        return (DocumentTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#createDocument(org.sola.services.digitalarchive.repository.entities.Document)
     * DigitalArchiveEJB.createDocument}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "CreateDocument")
    public DocumentTO CreateDocument(@WebParam(name = "documentTO") DocumentBinaryTO documentBinaryTO)
            throws SOLAFault, UnhandledFault, OptimisticLockingFault, SOLAAccessFault {

        final DocumentBinaryTO documentBinaryTOTmp = documentBinaryTO;
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {

            @Override
            public void run() {
                Document document = GenericTranslator.fromTO(documentBinaryTOTmp, Document.class, null);
                if (documentBinaryTOTmp.getFileName() != null) {
                    document.setBody(FileUtility.readFileFromCache(documentBinaryTOTmp.getFileName()));
                }
                document = digitalArchiveEJB.createDocument(document);
                result[0] = GenericTranslator.toTO(document, DocumentTO.class);
                FileUtility.deleteFileFromCache(documentBinaryTOTmp.getFileName());
            }
        });

        return (DocumentTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#createDocument(org.sola.services.digitalarchive.repository.entities.Document,
     * java.lang.String)
     * DigitalArchiveEJB.createDocument}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "CreateDocumentFromServer")
    public DocumentTO CreateDocumentFromServer(@WebParam(name = "documentTO") DocumentTO documentTO,
            @WebParam(name = "fileName") String fileName) throws SOLAFault, UnhandledFault,
            OptimisticLockingFault, SOLAAccessFault {

        final DocumentTO documentTOTmp = documentTO;
        final String fileNameTmp = fileName;
        final Object[] result = {null};

        runUpdate(wsContext, new Runnable() {

            @Override
            public void run() {
                Document document = digitalArchiveEJB.createDocument(GenericTranslator.fromTO(documentTOTmp, Document.class, null), fileNameTmp);
                result[0] = GenericTranslator.toTO(document, DocumentTO.class);
            }
        });

        return (DocumentTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#getFileBinary(java.lang.String)
     * DigitalArchiveEJB.getFileBinary}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetFileBinary")
    public FileInfoTO GetFileBinary(@WebParam(name = "fileName") String fileName)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String fileNameTmp = fileName;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(digitalArchiveEJB.getFileBinary(fileNameTmp), FileInfoTO.class);
            }
        });

        return (FileInfoTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#getFileThumbnail(java.lang.String)
     * DigitalArchiveEJB.getFileThumbnail}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetFileThumbnail")
    public FileInfoTO GetFileThumbnail(@WebParam(name = "fileName") String fileName)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {
        //     FLOSS - 813 4       

        final String fileNameTmp = fileName;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTO(digitalArchiveEJB.getFileThumbnail(fileNameTmp), FileInfoTO.class);
            }
        });

        return (FileInfoTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#getAllFiles()
     * DigitalArchiveEJB.getAllFiles}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAllFiles")
    public List<FileInfoTO> GetAllFiles() throws SOLAFault, UnhandledFault, SOLAAccessFault {

        //     FLOSS - 813 5      

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(digitalArchiveEJB.getAllFiles(), FileInfoTO.class);
            }
        });

        return (List<FileInfoTO>) result[0];
    }

    /**
     * See {@linkplain org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJB#deleteFile(java.lang.String)
     * DigitalArchiveEJB.deleteFile}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "DeleteFile")
    public boolean DeleteFile(@WebParam(name = "fileName") String fileName)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String fileNameTmp = fileName;
        final boolean[] result = {true};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = digitalArchiveEJB.deleteFile(fileNameTmp);
            }
        });

        return result[0];
    }
}
