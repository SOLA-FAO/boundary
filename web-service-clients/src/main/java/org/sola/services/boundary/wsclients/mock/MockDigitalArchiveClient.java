/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations (FAO).
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
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.DigitalArchiveClient;
import org.sola.webservices.transferobjects.digitalarchive.DocumentBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.DocumentTO;
import org.sola.webservices.transferobjects.digitalarchive.FileBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.FileInfoTO;

/**
 * Provides a mock implementation for the 
 * {@linkplain org.sola.services.boundary.wsclients.DigitalArchiveClient} interface. Uses the 
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method. 
 * <p>Each method mocked by this class has a public constant defined that can be used to reference 
 * a mock response object from the {@linkplain MockServiceManager}. To set a response object
 * for a web method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from this class.</p>
 * @author amcdowell
 * @see MockResponse
 */
public class MockDigitalArchiveClient extends AbstractMockWSClient { // implements DigitalArchiveClient {

    private static final String SERVICE_NAME = "DigitalArchive.";
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    public static final String GET_DOCUMENT = SERVICE_NAME + "getDocument";
    public static final String SAVE_DOCUMENT = SERVICE_NAME + "saveDocument";
    public static final String CREATE_DOCUMENT = SERVICE_NAME + "createDocument";
    public static final String CREATE_DOCUMENT_FROM_SERVER = SERVICE_NAME + "createDocumentFromServer";
    public static final String GET_FILE_BINARY = SERVICE_NAME + "getFileBinary";
    public static final String GET_FILE_THUMBNAIL = SERVICE_NAME + "getFileThumbnail";
    public static final String GET_ALL_FILES = SERVICE_NAME + "getAllFiles";
    public static final String DELETE_FILE = SERVICE_NAME + "deleteFile";
    public static final String ROTATE_IMAGE = SERVICE_NAME + "rotateImage";

    public MockDigitalArchiveClient(String url) {
        super(url, null);
    }

//    /** @return default = true */
//    @Override
//    public boolean checkConnection() {
//        return getManager().getResponse(CHECK_CONNECTION, Boolean.class, true);
//    }
//
//    /** @return default = new DocumentTO with id set */
//    @Override
//    public DocumentBinaryTO getDocument(String documentId) {
//        DocumentBinaryTO defaultResponse = new DocumentBinaryTO();
//        defaultResponse.setId(documentId);
//        return getManager().getResponse(GET_DOCUMENT, DocumentBinaryTO.class, defaultResponse, documentId);
//    }
//
//    /** @return default = true */
//    @Override
//    public DocumentTO saveDocument(DocumentTO documentTO) {
//        DocumentTO defaultResponse = new DocumentTO();
//        return getManager().getResponse(SAVE_DOCUMENT, DocumentTO.class, defaultResponse, documentTO);
//    }
//
//    /** @return default = newDocumentId */
//    @Override
//    public DocumentTO createDocument(DocumentBinaryTO documentBinaryTO) {
//        DocumentTO defaultResponse = new DocumentTO();
//        return getManager().getResponse(CREATE_DOCUMENT, DocumentTO.class, defaultResponse, documentBinaryTO);
//    }
//
//    /** @return default = newDocumentId */
//    @Override
//    public DocumentTO createDocumentFromServer(DocumentTO documentTO, String fileName) {
//        DocumentTO defaultResponse = new DocumentTO();
//        return getManager().getResponse(CREATE_DOCUMENT_FROM_SERVER, DocumentTO.class, defaultResponse,
//                documentTO, fileName);
//    }
//
//    /** @return default = new FileBinaryTO */
//    @Override
//    public FileBinaryTO getFileBinary(String fileName) {
//        FileBinaryTO defaultResponse = new FileBinaryTO();
//        return getManager().getResponse(GET_FILE_BINARY, FileBinaryTO.class, defaultResponse,
//                fileName);
//
//    }
//
//    /** @return default = new FileBinaryTO */
//    @Override
//    public FileBinaryTO getFileThumbnail(String fileName) {
//        FileBinaryTO defaultResponse = new FileBinaryTO();
//        return getManager().getResponse(GET_FILE_THUMBNAIL, FileBinaryTO.class, defaultResponse,
//                fileName);
//    }
//
//    /** @return default = new ArrayList<FileInfoTO> */
//    @Override
//    public List<FileInfoTO> getAllFiles() {
//        List<FileInfoTO> defaultResponse = new ArrayList<FileInfoTO>();
//        return getManager().getResponse(GET_ALL_FILES, List.class, defaultResponse);
//    }
//
//    /** @return default = true */
//    @Override
//    public boolean deleteFile(String fileName) {
//        return getManager().getResponse(DELETE_FILE, Boolean.class, true, fileName);
//    }
//
//    /** @return default = true */
//    @Override
//    public boolean rotateImage(String fileName, int angle) {
//        return getManager().getResponse(ROTATE_IMAGE, Boolean.class, true, fileName, angle);
//    }
}
