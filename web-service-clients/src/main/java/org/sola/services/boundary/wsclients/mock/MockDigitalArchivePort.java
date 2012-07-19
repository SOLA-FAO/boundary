/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO). All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this list of conditions
 * and the following disclaimer. 2. Redistributions in binary form must reproduce the above
 * copyright notice,this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY
 * WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.DigitalArchiveClient;
import org.sola.webservices.digitalarchive.*;
import org.sola.webservices.transferobjects.digitalarchive.DocumentBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.DocumentTO;
import org.sola.webservices.transferobjects.digitalarchive.FileBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.FileInfoTO;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.digitalarchive.DigitalArchive} interface. Uses the
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method.
 * <p>Each method mocked by this class has a public constant defined that can be used to reference a
 * mock response object from the {@linkplain MockServiceManager}. To set a response object for a web
 * method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method referencing
 * the appropriate web method constant from {@linkplain org.sola.services.boundary.wsclients.DigitalArchiveClient}.</p>
 *
 * @see MockDigitalArchiveClient
 * @see DigitalArchiveClient
 * @see MockServiceManager
 * @see MockResponse
 *
 */
public class MockDigitalArchivePort implements DigitalArchive {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type.
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionBasic(Exception ex) throws SOLAFault, UnhandledFault {
        if (SOLAFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAFault) ex;
        } else if (UnhandledFault.class.isAssignableFrom(ex.getClass())) {
            throw (UnhandledFault) ex;
        } else if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
            throw (RuntimeException) ex;
        } else {
            // The type of the exception does not match any recognized exception type used by this 
            // web service. Throw a MockResponseException to fail the test. 
            throw new MockResponseException("Unable to convert the mocked response exception to "
                    + "recognized exception type: " + ex.getClass().getName());
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the SOLAAccessFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionAccess(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, MockResponseException {
        if (SOLAAccessFault.class.isAssignableFrom(ex.getClass())) {
            throw (SOLAAccessFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Processes the mock response exception and throws the appropriate service exception or a
     * MockResponseException if the response exception is not a recognized type. Extends {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
     * to include the OptimisticLockingFault;
     *
     * @param ex The Mock response exception to process
     */
    private void processExceptionUpdate(Exception ex) throws SOLAFault, SOLAAccessFault,
            UnhandledFault, OptimisticLockingFault, MockResponseException {
        if (OptimisticLockingFault.class.isAssignableFrom(ex.getClass())) {
            throw (OptimisticLockingFault) ex;
        } else {
            processExceptionBasic(ex);
        }
    }

    /**
     * Response Key = DigitalArchiveClient.DELETE_FILE
     *
     * @return default = true
     */
    @Override
    public boolean deleteFile(String fileName) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        boolean defaultResponse = true;
        try {
            return getManager().getResponse(DigitalArchiveClient.DELETE_FILE, Boolean.class,
                    defaultResponse, fileName);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return false;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.GET_DOCUMENT
     *
     * @return default = new DocumentBinaryTO()
     */
    @Override
    public DocumentBinaryTO getDocument(String documentId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        DocumentBinaryTO defaultResponse = new DocumentBinaryTO();
        defaultResponse.setId(documentId);
        try {
            return getManager().getResponse(DigitalArchiveClient.GET_DOCUMENT,
                    DocumentBinaryTO.class, defaultResponse, documentId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.SAVE_DOCUMENT
     *
     * @return default = documentTO param
     */
    @Override
    public DocumentTO saveDocument(DocumentTO documentTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        DocumentTO defaultResponse = documentTO;
        try {
            return getManager().getResponse(DigitalArchiveClient.SAVE_DOCUMENT,
                    DocumentTO.class, defaultResponse, documentTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.GET_ALL_FILES
     *
     * @return default = new ArrayList<FileInfoTO>()
     */
    @Override
    public List<FileInfoTO> getAllFiles() throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<FileInfoTO> defaultResponse = new ArrayList<FileInfoTO>();
        try {
            return getManager().getResponse(DigitalArchiveClient.GET_ALL_FILES,
                    List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.CHECK_CONNECTION
     *
     * @return default = true
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(DigitalArchiveClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = DigitalArchiveClient.CREATE_DOCUMENT
     *
     * @return default = documentTO param
     */
    @Override
    public DocumentTO createDocument(DocumentBinaryTO documentTO)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        DocumentTO defaultResponse = documentTO;
        try {
            return getManager().getResponse(DigitalArchiveClient.CREATE_DOCUMENT,
                    DocumentTO.class, defaultResponse, documentTO);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.CREATE_DOCUMENT_FROM_SERVER
     *
     * @return default = documentTO param
     */
    @Override
    public DocumentTO createDocumentFromServer(DocumentTO documentTO, String fileName)
            throws OptimisticLockingFault, SOLAAccessFault, SOLAFault, UnhandledFault {
        DocumentTO defaultResponse = documentTO;
        try {
            return getManager().getResponse(DigitalArchiveClient.CREATE_DOCUMENT_FROM_SERVER,
                    DocumentTO.class, defaultResponse, documentTO, fileName);
        } catch (Exception ex) {
            processExceptionUpdate(ex);
            return null;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.GET_FILE_BINARY
     *
     * @return default = new FileBinaryTO()
     */
    @Override
    public FileInfoTO getFileBinary(String fileName) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        FileInfoTO defaultResponse = new FileBinaryTO();
        defaultResponse.setName(fileName);
        try {
            return getManager().getResponse(DigitalArchiveClient.GET_FILE_BINARY,
                    FileInfoTO.class, defaultResponse, fileName);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = DigitalArchiveClient.GET_FILE_THUMBNAIL
     *
     * @return default = new FileBinaryTO()
     */
    @Override
    public FileBinaryTO getFileThumbnail(String fileName) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        FileBinaryTO defaultResponse = new FileBinaryTO();
        defaultResponse.setName(fileName);
        try {
            return getManager().getResponse(DigitalArchiveClient.GET_FILE_THUMBNAIL,
                    FileBinaryTO.class, defaultResponse, fileName);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }
}
