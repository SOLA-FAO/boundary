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
package org.sola.services.boundary.wsclients;

import java.util.List;
import org.sola.webservices.transferobjects.digitalarchive.DocumentBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.FileBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.FileInfoTO;
import org.sola.webservices.transferobjects.digitalarchive.DocumentTO;

/**
 * Interface for the Digital Archive Service. Implemented by {@linkplain DigitalArchiveClientImpl}.
 * To obtain a reference to the Digital Archive Service, use {@linkplain WSManager#getDigitalArchive()}
 *
 * @see DigitalArchiveClientImpl
 * @see WSManager#getDigitalArchive()
 */
public interface DigitalArchiveClient extends AbstractWSClient {

    /**
     * DigitalArchive. - Service name prefix for the Digital Archive Web Service
     */
    public static final String SERVICE_NAME = "DigitalArchive.";
    /**
     * DigitalArchive.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * DigitalArchive.getDocument - Identifier for the getDocument method
     */
    public static final String GET_DOCUMENT = SERVICE_NAME + "getDocument";
    /**
     * DigitalArchive.saveDocument - Identifier for the saveDocument method
     */
    public static final String SAVE_DOCUMENT = SERVICE_NAME + "saveDocument";
    /**
     * DigitalArchive.createDocument - Identifier for the createDocument method
     */
    public static final String CREATE_DOCUMENT = SERVICE_NAME + "createDocument";
    /**
     * DigitalArchive.createDocumentFromServer - Identifier for the createDocumentFromServer method
     */
    public static final String CREATE_DOCUMENT_FROM_SERVER = SERVICE_NAME + "createDocumentFromServer";
    /**
     * DigitalArchive.getFileBinary - Identifier for the getFileBinary method
     */
    public static final String GET_FILE_BINARY = SERVICE_NAME + "getFileBinary";
    /**
     * DigitalArchive.getFileThumbnail - Identifier for the getFileThumbnail method
     */
    public static final String GET_FILE_THUMBNAIL = SERVICE_NAME + "getFileThumbnail";
    /**
     * DigitalArchive.getAllFiles - Identifier for the getAllFiles method
     */
    public static final String GET_ALL_FILES = SERVICE_NAME + "getAllFiles";
    /**
     * DigitalArchive.deleteFile - Identifier for the deleteFile method
     */
    public static final String DELETE_FILE = SERVICE_NAME + "deleteFile";

    public DocumentBinaryTO getDocument(String documentId);

    public DocumentTO saveDocument(DocumentTO documentTO);

    public DocumentTO createDocument(DocumentBinaryTO documentBinaryTO);

    public DocumentTO createDocumentFromServer(DocumentTO documentTO, String fileName);

    public FileBinaryTO getFileBinary(String fileName);

    public FileBinaryTO getFileThumbnail(String fileName);

    public List<FileInfoTO> getAllFiles();

    public boolean deleteFile(String fileName);
}
