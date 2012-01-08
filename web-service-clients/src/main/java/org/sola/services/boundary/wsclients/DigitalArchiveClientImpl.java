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
package org.sola.services.boundary.wsclients;

import java.util.List;
import javax.xml.namespace.QName;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.services.boundary.wsclients.exception.WebServiceClientExceptionType;
import org.sola.webservices.digitalarchive.DigitalArchive;
import org.sola.webservices.digitalarchive.DigitalarchiveService;
import org.sola.webservices.transferobjects.digitalarchive.FileBinaryTO;
import org.sola.webservices.transferobjects.digitalarchive.FileInfoTO;
import org.sola.webservices.transferobjects.digitalarchive.DocumentTO;
import org.sola.webservices.digitalarchive.OptimisticLockingFault;
import org.sola.webservices.digitalarchive.SOLAFault;
import org.sola.webservices.digitalarchive.UnhandledFault;
import org.sola.webservices.transferobjects.digitalarchive.DocumentBinaryTO;

public class DigitalArchiveClientImpl extends AbstractWSClientImpl implements DigitalArchiveClient {

    private static final String NAMESPACE_URI = "http://webservices.sola.org/digitalarchive";
    private static final String LOCAL_PART = "digitalarchive-service";
    private static final String SERVICE_NAME = "DigitalArchive.";
    
    public DigitalArchiveClientImpl(String url){
        super(url, new QName(NAMESPACE_URI, LOCAL_PART));
    }
    
     private DigitalArchive getPort() {
        return getPort(DigitalArchive.class, DigitalarchiveService.class);
    }
    
    @Override
    public boolean checkConnection() {
        try {
            boolean result = getPort().checkConnection();
            return result;
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "checkConnection", t);
        }
    }
     
    @Override
    public DocumentBinaryTO getDocument(String documentId) {
        try {
            return getPort().getDocument(documentId);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getDocument", t);
        }
    }

    @Override
    public DocumentTO saveDocument(DocumentTO documentTO) {
        try {
            return getPort().saveDocument(documentTO);
         } catch (OptimisticLockingFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.OPTIMISTIC_LOCK,
                    f.getFaultInfo());} 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "saveDocument", t);
        }
    }

    @Override
    public DocumentTO createDocument(DocumentBinaryTO documentBinaryTO) {
        try {
            return getPort().createDocument(documentBinaryTO);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "createDocument", t);
        }
    }

    @Override
    public DocumentTO createDocumentFromServer(DocumentTO documentTO, String fileName) {
        try {
            return getPort().createDocumentFromServer(documentTO, fileName);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "createDocumentFromServer", t);
        }
    }

    @Override
    public FileBinaryTO getFileBinary(String fileName) {
        try {
            return getPort().getFileBinary(fileName);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getFileBinary", t);
        }
    }

    @Override
    public FileBinaryTO getFileThumbnail(String fileName) {
        try {
            return getPort().getFileThumbnail(fileName);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getFileThumbnail", t);
        }
    }

    @Override
    public List<FileInfoTO> getAllFiles() {
        try {
            return getPort().getAllFiles();
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "getAllFiles", t);
        }
    }

    @Override
    public boolean deleteFile(String fileName) {
        try {
            return getPort().deleteFile(fileName);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "deleteFile", t);
        }
    }

    @Override
    public boolean rotateImage(String fileName, int angle) {
        try {
            return getPort().rotateImage(fileName, angle);
         } 
        catch (SOLAFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_GENERAL,
                    f.getFaultInfo());
        } catch (UnhandledFault f) {
            throw new WebServiceClientException(WebServiceClientExceptionType.SERVICE_UNHANDLED,
                    f.getFaultInfo());
        } catch (Throwable t) {
            throw processException(SERVICE_NAME + "rotateImage", t);
        }
    }
    
}
