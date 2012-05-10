/**
 * ******************************************************************************************
 * Copyright (C) 2012 - Food and Agriculture Organization of the United Nations
 * (FAO). All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,this
 * list of conditions and the following disclaimer. 2. Redistributions in binary
 * form must reproduce the above copyright notice,this list of conditions and
 * the following disclaimer in the documentation and/or other materials provided
 * with the distribution. 3. Neither the name of FAO nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT,STRICT LIABILITY,OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * *********************************************************************************************
 */
package org.sola.services.boundary.wsclients;

import java.util.HashMap;
import org.sola.common.messaging.ClientMessage;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;

/**
 * Encapsulates Web-service clients instances, providing initialization and
 * instances access. <br />This class is singleton.
 */
public class WSManager {

    private CaseManagementClient caseManagementWS;
    private AdminClient adminWS;
    private ReferenceDataClient referenceWS;
    private SearchClient searchWS;
    private DigitalArchiveClient digitalArchiveWS;
    private AdministrativeClient administrativeWS;
    private CadastreClient cadastreWS;
    private SpatialClient spatialWS;

    private WSManager() {
    }

    /**
     * Private class to hold singleton instance.
     */
    private static class WSManagerHolder {

        private static final WSManager INSTANCE = new WSManager();
    }

    /**
     * Returns a singleton instance of {@link WSManager}.
     */
    public static WSManager getInstance() {
        return WSManagerHolder.INSTANCE;
    }

    /**
     * Initializes Web-service clients instances. All Web-services clients are
     * supposed to be secured and accessed with username and password. While
     * initialization
     * <code>checkConnection()</code> method is called on the Web-services
     * clients to authenticate user.
     *
     * @param userName The login name of the user.
     * @param userPassword The password of the user.
     * @throws WebServiceClientException
     */
    public boolean initWebServices(String userName, char[] userPassword, HashMap<String, String> config)
            throws WebServiceClientException {

        String url = config.get(WSConfig.SOLA_WS_CASE_MANAGEMENT_SERVICE_URL.toString());

        if (url != null && !url.isEmpty()) {
            caseManagementWS = WSClientFactory.getCaseManagementClient(url);
            caseManagementWS.setCredentials(userName, userPassword);
            //caseManagementWS.checkConnection();
        }

        url = config.get(WSConfig.SOLA_WS_ADMIN_SERVICE_URL.toString());

        if (url != null && !url.isEmpty()) {
            adminWS = WSClientFactory.getSystemClient(url);
            adminWS.setCredentials(userName, userPassword);
            //adminWS.checkConnection();
        }

        url = config.get(WSConfig.SOLA_WS_REFERENCE_DATA_SERVICE_URL.toString());
        
        if (url != null && !url.isEmpty()) {
            referenceWS = WSClientFactory.getReferenceDataClient(url);
            referenceWS.setCredentials(userName, userPassword);
            //referenceWS.checkConnection();
        }

        url = config.get(WSConfig.SOLA_WS_SEARCH_SERVICE_URL.toString());
             
        if (url != null && !url.isEmpty()) {
            searchWS = WSClientFactory.getSearchClient(url);
            searchWS.setCredentials(userName, userPassword);
        }

        url = config.get(WSConfig.SOLA_WS_DIGITAL_ARCHIVE_URL.toString());
        
        if (url != null && !url.isEmpty()) {
            digitalArchiveWS = WSClientFactory.getDigitalArchiveClient(url);
            digitalArchiveWS.setCredentials(userName, userPassword);
            //digitalArchiveWS.checkConnection();
        }

        url = config.get(WSConfig.SOLA_WS_ADMINISTRATIVE_SERVICE_URL.toString());
        
        if (url != null && !url.isEmpty()) {
            administrativeWS = WSClientFactory.getAdministrativeClient(url);
            administrativeWS.setCredentials(userName, userPassword);
            //administrativeWS.CheckConnection();
        }

        url = config.get(WSConfig.SOLA_WS_CADASTRE_SERVICE_URL.toString());
        
        if (url != null && !url.isEmpty()) {
            cadastreWS = WSClientFactory.getCadastreClient(url);
            cadastreWS.setCredentials(userName, userPassword);
            //cadastreWS.checkConnection();
        }

        url = config.get(WSConfig.SOLA_WS_SPATIAL_SERVICE_URL.toString());
        
        if (url != null && !url.isEmpty()) {
            spatialWS = WSClientFactory.getSpatialClient(url);
            spatialWS.setCredentials(userName, userPassword);
            //spatialWS.checkConnection();
        }

        return searchWS.checkConnection();
    }

    /**
     * Returns administrative Web-service client instance.
     */
    public AdministrativeClient getAdministrative() {
        if(administrativeWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Administrative"});
        }
        return administrativeWS;
    }

    /**
     * Returns digital archive Web-service client instance.
     */
    public DigitalArchiveClient getDigitalArchive() {
        if(digitalArchiveWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Digital Archive"});
        }
        return digitalArchiveWS;
    }

    /**
     * Returns case management Web-service client instance.
     */
    public CaseManagementClient getCaseManagementService() {
        if(caseManagementWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Case Management"});
        }
        return caseManagementWS;
    }

    /**
     * Returns admin Web-service client instance.
     */
    public AdminClient getAdminService() {
        if(adminWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Admin"});
        }
        return adminWS;
    }

    /**
     * Returns reference data Web-service client instance.
     */
    public ReferenceDataClient getReferenceDataService() {
        if(referenceWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Reference Data"});
        }
        return referenceWS;
    }

    /**
     * Returns search Web-service client instance.
     */
    public SearchClient getSearchService() {
        if(searchWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Search"});
        }
        return searchWS;
    }

    /**
     * Returns search Web-service client instance.
     */
    public CadastreClient getCadastreService() {
        if(cadastreWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Cadastre"});
        }
        return cadastreWS;
    }

    /**
     * Returns spatial Web-service client instance.
     */
    public SpatialClient getSpatialService() {
        if(spatialWS == null){
            throw new WebServiceClientException(
                    ClientMessage.GENERAL_ERRORS_WEBSERVICE_NOT_INITIALIZED, 
                    new Object[]{"Spatial"});
        }
        return spatialWS;
    }
}
