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
package org.sola.services.boundary.wsclients;

/**
 * Interface for the File Streaming Service. This service supports efficient upload and download of
 * large files to and from the SOLA Application Server. Files larger than 4MB are associated as
 * attachments to the request/response messages using MTOM feature of Metro.
 *
 * <p>Due to a bug in Metro (see http://java.net/jira/browse/WSIT-1081) Metro does not support
 * streaming of large attachments if the web service connection is secured. This significantly
 * limits the size of the files that can be uploaded /downloaded (i.e. < 20Mb). To avoid this issue,
 * this FileStreaming Service has been created as an unsecured connection so that efficent streaming
 * of large files is possible.</p>
 *
 * <p>This service does not update the SOLA Database - it only uploads and downloads files to and
 * from the SOLA Application File Server. This ensures that a file can only be loaded into the SOLA
 * database if it is part of a legitimate transaction.</p>.
 *
 * Implemented by {@linkplain FileStreamingClientImpl}. To obtain a reference to the FileStreaming
 * Service, use {@linkplain WSManager#getFileStreaming()}
 *
 * @see FileStreamingClientImpl
 * @see WSManager#getFileStreaming()
 */
public interface FileStreamingClient extends AbstractWSClient {

    /**
     * FileStreaming. - Service name prefix for the File Streaming Web Service
     */
    public static final String SERVICE_NAME = "FileStreaming.";
    /**
     * FileStreaming.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * FileStreaming.upload - Identifier for the upload method
     */
    public static final String UPLOAD = SERVICE_NAME + "upload";
    /**
     * FileStreaming.download - Identifier for the download method
     */
    public static final String DOWNLOAD = SERVICE_NAME + "download";

    /**
     * Streams a file up to the SOLA Application Server and stores it on the file system in the
     * documents cache.
     *
     * @param pathFileName - The full file name including path of the file to upload to the SOLA
     * Application Server.
     * @return The file name (excluding path) of the file saved in the documents cache of the SOLA
     * Application Server.
     */
    public String upload(String pathFileName);

    /**
     * Streams a file down from the SOLA Application Server to the clients local documents cache.
     * The file must be accessible from the SOLA Application Server file system.
     *
     * @param serverPathFileName If the file to download is in the documents change on the SOLA Application
     * Server, only the file name is required. If the document is located elsewhere, the full file and
     * path name are required. 
     * @param localFileName The name to give the file when saved in the local documents change. If
     * null, a random file name will be assigned. The file name will be sanitized to ensure it does
     * not redirect the file to a different location.
     * @return The file name (excluding path) of the file saved in the local documents cache. This
     * may not be the same as localFileName if localFileName is null or localFileName requires
     * sanitization.
     */
    public String download(String serverPathFileName, String localFileName);
}
