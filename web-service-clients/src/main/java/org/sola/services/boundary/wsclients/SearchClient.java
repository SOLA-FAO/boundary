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
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.search.GenericResult;
import org.sola.webservices.search.QueryForSelect;
import org.sola.webservices.search.ResultForSelectionInfo;
import org.sola.webservices.transferobjects.search.*;

/**
 * Interface for the Search Service. Implemented by {@linkplain SearchClientImpl}. To obtain a
 * reference to the Digital Archive Service, use {@linkplain WSManager#getSearchService()}
 *
 * @see SearchClientImpl
 * @see WSManager#getSearchService()
 */
public interface SearchClient extends AbstractWSClient {

    /**
     * Search. - Service name prefix for the Search Web Service
     */
    public static final String SERVICE_NAME = "Search.";
    /**
     * Search.checkConnection - Identifier for the checkConnection method
     */
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    /**
     * Search.getAssignedApplications - Identifier for the getAssignedApplications method
     */
    public static final String GET_ASSIGNED_APPLICATIONS = SERVICE_NAME + "getAssignedApplications";
    /**
     * Search.getUnassignedApplications - Identifier for the getUnassignedApplications method
     */
    public static final String GET_UNASSIGNED_APPLICATIONS = SERVICE_NAME + "getUnassignedApplications";
    /**
     * Search.searchApplications - Identifier for the searchApplications method
     */
    public static final String SEARCH_APPLICATIONS = SERVICE_NAME + "searchApplications";
    /**
     * Search.verifyApplicationProperty - Identifier for the verifyApplicationProperty method
     */
    public static final String VERIFY_APPLICATION_PROPERTY = SERVICE_NAME + "verifyApplicationProperty";
    /**
     * Search.select - Identifier for the select method
     */
    public static final String SELECT = SERVICE_NAME + "select";
    /**
     * Search.searchParties - Identifier for the searchParties method
     */
    public static final String SEARCH_PARTIES = SERVICE_NAME + "searchParties";
    /**
     * Search.searchSources - Identifier for the searchSources method
     */
    public static final String SEARCH_SOURCES = SERVICE_NAME + "searchSources";
    /**
     * Search.getActiveUsers - Identifier for the getActiveUsers method
     */
    public static final String GET_ACTIVE_USERS = SERVICE_NAME + "getActiveUsers";
    /**
     * Search.searchUsers - Identifier for the searchUsers method
     */
    public static final String SEARCH_USERS = SERVICE_NAME + "searchUsers";
    /**
     * Search.getApplicationLog - Identifier for the getApplicationLog method
     */
    public static final String GET_APPLICATION_LOG = SERVICE_NAME + "getApplicationLog";
    /**
     * Search.searchBr - Identifier for the searchBr method
     */
    public static final String SEARCH_BR = SERVICE_NAME + "searchBr";
    /**
     * Search.searchBaUnit - Identifier for the searchBaUnit method
     */
    public static final String SEARCH_BA_UNIT = SERVICE_NAME + "searchBaUnit";
    /**
     * Search.getSpatialSearchOptions - Identifier for the getSpatialSearchOptions method
     */
    public static final String GET_SPATIAL_SEARCH_OPTIONS = SERVICE_NAME + "getSpatialSearchOptions";
    /**
     * Search.searchSpatialObjects - Identifier for the searchSpatialObjects method
     */
    public static final String SEARCH_SPATIAL_OBJECTS = SERVICE_NAME + "searchSpatialObjects";

    List<ApplicationSearchResultTO> getAssignedApplications() throws WebServiceClientException;

    List<ApplicationSearchResultTO> getUnassignedApplications() throws WebServiceClientException;

    List<ApplicationSearchResultTO> searchApplications(ApplicationSearchParamsTO applicationSearchParamsTO) throws WebServiceClientException;

    PropertyVerifierTO verifyApplicationProperty(String applicationNumber, String firstPart, String lastPart) throws WebServiceClientException;

    public List<ResultForSelectionInfo> select(List<QueryForSelect> queries)
            throws WebServiceClientException;

    List<PartySearchResultTO> searchParties(PartySearchParamsTO searchParams) throws WebServiceClientException;

    List<SourceSearchResultTO> searchSources(SourceSearchParamsTO searchParams) throws WebServiceClientException;

    List<UserSearchResultTO> getActiveUsers() throws WebServiceClientException;

    List<UserSearchAdvancedResultTO> searchUsers(UserSearchParamsTO searchParams) throws WebServiceClientException;

    List<ApplicationLogResultTO> getApplicationLog(String applicationId);

    List<BrSearchResultTO> searchBr(BrSearchParamsTO searchParams) throws WebServiceClientException;

    List<BaUnitSearchResultTO> searchBaUnit(BaUnitSearchParamsTO searchParams) throws WebServiceClientException;

    List<SpatialSearchOptionTO> getSpatialSearchOptions() throws WebServiceClientException;

    /**
     * Executes a spatial search using the specified query name and search string
     *
     * @param queryName The name of the dynamic query to execute for the search
     * @param searchString The search string (e.g. the label of the spatial object to find)
     * @return The results of the search
     */
    List<SpatialSearchResultTO> searchSpatialObjects(
            String queryName, String searchString) throws WebServiceClientException;
}
