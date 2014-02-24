/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.wsclients;

import java.util.List;
import org.sola.common.RolesConstants;
import org.sola.webservices.transferobjects.digitalarchive.DocumentBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.DocumentTO;
import org.sola.webservices.transferobjects.digitalarchive.FileInfoTO;

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
    /**
     * DigitalArchive.setFileStreamingService - Identifier for the setFileStreamingService method
     */
    public static final String SET_FILE_STREAMING_SERVICE = SERVICE_NAME + "setFileStreamingService";

    /**
     * Retrieves the document for the specified identifier. This includes the document content (i.e
     * the digital file). <p>Requires the {@linkplain RolesConstants#SOURCE_SEARCH} role.</p>
     *
     * @param documentId Identifier of the document to retrieve
     */
    public DocumentBinaryTO getDocument(String documentId);

    /**
     * Can be used to create a new document or save any updates to the details of an existing
     * document. <p>Requires the {@linkplain RolesConstants#SOURCE_SAVE} role.</p>
     *
     * @param documentTO The document to create/save.
     * @return The document after the save is completed.
     */
    public DocumentTO saveDocument(DocumentTO documentTO);

    /**
     * Can be used to create a new document. Also assigns the document number. <p>Requires the {@linkplain RolesConstants#SOURCE_SAVE}
     * role.</p>
     *
     * @param documentBinaryTO The document to create.
     * @return The document after the save is completed.
     */
    public DocumentTO createDocument(DocumentBinaryTO documentBinaryTO);

    /**
     * Can be used to create a new document with the digital content obtained from the specified
     * file. Used to create documents from the network scan folder. After the digital file is
     * loaded, it is deleted from the network scan folder. <p>Requires the {@linkplain RolesConstants#SOURCE_SAVE}
     * role.</p>
     *
     * @param documentTO The document to create.
     * @param fileName The filename of the digital file to save with the document.
     * @return The document after the save is completed.
     * @return
     */
    public DocumentTO createDocumentFromServer(DocumentTO documentTO, String fileName);

    /**
     * Loads the specified file from the Network Scan folder. <p>Requires the {@linkplain RolesConstants#SOURCE_SEARCH}
     * role.</p>
     *
     * @param fileName The name of the file to load
     * @return The binary file along with some attributes of the file
     */
    public FileInfoTO getFileBinary(String fileName);

    /**
     * Loads the specified file from the Network Scan folder then generates a thumbnail image of the
     * file if one does not already exist. <p>Requires the {@linkplain RolesConstants#SOURCE_SEARCH}
     * role.</p>
     *
     * @param fileName The name of the file to load
     * @return A thumbnail image of the file
     */
    public FileInfoTO getFileThumbnail(String fileName);

    /**
     * Retrieves the list of all files in the Network Scan Folder. Only meta data about the file is
     * returned. The content of the file is omitted to avoid transferring a large amount of file
     * data across the network. <p>Requires the {@linkplain RolesConstants#SOURCE_SEARCH} role.</p>
     */
    public List<FileInfoTO> getAllFiles();

    /**
     * Deletes the specified file from the Network Scan folder. Also attempts to delete any
     * thumbnail for the file if one exists. <p>Requires the {@linkplain RolesConstants#SOURCE_SEARCH}
     * role.</p>
     *
     * @param fileName The name of the file to delete.
     * @return true if the file is successfully deleted.
     */
    public boolean deleteFile(String fileName);

    /**
     * Sets the file Streaming Service used by the Digital Archive to upload and download files
     *
     * @param fsService The instance of the FileStreaming Service to use.
     */
    public void setFileStreamingService(FileStreamingClient fsService);
}
