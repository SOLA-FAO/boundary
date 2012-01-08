/**
 * ******************************************************************************************
 * Copyright (C) 2011 - Food and Agriculture Organization of the United Nations (FAO).
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

import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.sola.services.boundary.transferobjects.digitalarchive.DocumentBinaryTO;
import org.sola.services.boundary.transferobjects.digitalarchive.DocumentTO;
import org.sola.services.boundary.transferobjects.digitalarchive.FileBinaryTO;
import org.sola.services.boundary.transferobjects.digitalarchive.FileInfoTO;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.FaultUtility;
import org.sola.services.common.faults.OptimisticLockingFault;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.digitalarchive.businesslogic.DigitalArchiveEJBLocal;
import org.sola.services.digitalarchive.repository.entities.Document;

/**
 * Implementation class of the digital archive web service. 
 * @see DigitalArchiveEJBLocal
 */
@WebService(serviceName = "digitalarchive-service", targetNamespace = ServiceConstants.DIGITAL_ARCHIVE_WS_NAMESPACE)
public class DigitalArchive extends AbstractWebService {

    @EJB
    private DigitalArchiveEJBLocal digitalArchiveEJB;

    /** Dummy method to check the web service instance is working */
   @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection(){
        return true;
    }
   
    /**
     * Returns document from digital archive.
     * @param documentId Document id in digital archive.
     * @return
     * @throws SOLAFault 
     * @throws UnhandledFault  
     */
    @WebMethod(operationName = "GetDocument")
    public DocumentBinaryTO GetDocument(@WebParam(name = "documentId") String documentId) throws SOLAFault, UnhandledFault {
        try {
            return GenericTranslator.toTO(digitalArchiveEJB.getDocument(documentId), DocumentBinaryTO.class);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Saves document into digital archive.
     * @param documentTO Transfer object, representing document.
     * @return Saved document.
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws OptimisticLockingFault
     */
    @WebMethod(operationName = "SaveDocument")
    public DocumentTO SaveDocument(@WebParam(name = "documentTO") DocumentTO documentTO)
            throws SOLAFault, UnhandledFault, OptimisticLockingFault {
        try {
            try {
                beginTransaction();
                
                Document document = digitalArchiveEJB.saveDocument(GenericTranslator.fromTO(documentTO,
                        Document.class, digitalArchiveEJB.getDocument(documentTO.getId())));
                DocumentTO result = GenericTranslator.toTO(document, DocumentTO.class);
                
                commitTransaction();
                return result;
                
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            if (fault.getClass() == OptimisticLockingFault.class) {
                throw (OptimisticLockingFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Creates document in the digital archive.
     * @param documentBinaryTO Transfer object, representing document with binary content.
     * @return Created document
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "CreateDocument")
    public DocumentTO CreateDocument(@WebParam(name = "documentTO") DocumentBinaryTO documentBinaryTO)
            throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                
                Document document = digitalArchiveEJB.createDocument(GenericTranslator
                        .fromTO(documentBinaryTO, Document.class, null));
                DocumentTO result = GenericTranslator.toTO(document, DocumentTO.class);
                
                commitTransaction();
                return result;
                
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Creates document in the digital archive from the file in shared folder.
     * @param documentTO Transfer object, representing document data excluding binary content.
     * @param fileName
     * @return Created document
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "CreateDocumentFromServer")
    public DocumentTO CreateDocumentFromServer(@WebParam(name = "documentTO") DocumentTO documentTO,
            @WebParam(name = "fileName") String fileName) throws SOLAFault, UnhandledFault {
        try {
            try {
                beginTransaction();
                
                Document document = digitalArchiveEJB.createDocument(GenericTranslator
                        .fromTO(documentTO, Document.class, null), fileName);
                DocumentTO result = GenericTranslator.toTO(document, DocumentTO.class);
                
                commitTransaction();
                return result;
                
            } finally {
                rollbackTransaction();
            }
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Returns binary file and meta data on it from the shared folder.
     * @param fileName File name in the shared folder.
     * @return
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetFileBinary")
    public FileBinaryTO GetFileBinary(@WebParam(name = "fileName") String fileName)
        throws SOLAFault, UnhandledFault{
        try {
            return GenericTranslator.toTO(digitalArchiveEJB.getFileBinary(fileName), FileBinaryTO.class);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Returns binary file and meta data of thumbnail.
     * @param fileName File name of the file in the shared folder.
     * @return
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetFileThumbnail")
    public FileBinaryTO GetFileThumbnail(@WebParam(name = "fileName") String fileName) 
            throws SOLAFault, UnhandledFault{
        
        try {
            return GenericTranslator.toTO(digitalArchiveEJB.getFileThumbnail(fileName), FileBinaryTO.class);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Returns the list of all files from the shared folder.
     * @return
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "GetAllFiles")
    public List<FileInfoTO> GetAllFiles() throws SOLAFault, UnhandledFault{
        try {
            return GenericTranslator.toTOList(digitalArchiveEJB.getAllFiles(), FileInfoTO.class);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Deletes file and corresponding thumbnail from the shared folder.
     * @param fileName File name in the shared folder.
     * @return
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "DeleteFile")
    public boolean DeleteFile(@WebParam(name = "fileName") String fileName) 
            throws SOLAFault, UnhandledFault{
        try {
            return digitalArchiveEJB.deleteFile(fileName);
        } catch (Throwable t) {
            Throwable fault = FaultUtility.ProcessException(t);
            if (fault.getClass() == SOLAFault.class) {
                throw (SOLAFault) fault;
            }
            throw (UnhandledFault) fault;
        }
    }

    /**
     * Rotates image file in the shared folder.
     * @param fileName File name in the shared folder.
     * @param angle Rotation angle.
     * @return
     * @throws SOLAFault
     * @throws UnhandledFault
     */
    @WebMethod(operationName = "RotateImage")
    public boolean RotateImage(@WebParam(name = "fileName") String fileName, @WebParam(name = "angle") int angle) 
            throws SOLAFault, UnhandledFault{
        throw new SOLAFault("Not yet implemented", null);
    }
}
