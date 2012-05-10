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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.SearchClient;
import org.sola.services.boundary.wsclients.exception.WebServiceClientException;
import org.sola.webservices.search.GenericResult;
import org.sola.webservices.search.QueryForSelect;
import org.sola.webservices.search.ResultForSelectionInfo;
import org.sola.webservices.transferobjects.search.*;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.services.boundary.wsclients.SearchClient} interface. Uses the
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for each web method.
 * <p>Each method mocked by this class has a public constant defined that can be used to reference a
 * mock response object from the {@linkplain MockServiceManager}. To set a response object for a web
 * method, use the {@linkplain MockServiceManager#setResponse(String, Object)} method referencing
 * the appropriate web method constant from this class.</p>
 *
 * @author amcdowell
 * @see MockResponse
 */
public class MockSearchClient extends AbstractMockWSClient implements SearchClient {

    private static final String SERVICE_NAME = "Search.";
    public static final String CHECK_CONNECTION = SERVICE_NAME + "checkConnection";
    public static final String GET_ASSIGNED_APPLICATIONS = SERVICE_NAME + "getAssignedApplications";
    public static final String GET_UNASSIGNED_APPLICATIONS = SERVICE_NAME + "getUnassignedApplications";
    public static final String SEARCH_APPLICATIONS = SERVICE_NAME + "searchApplications";
    public static final String SEARCH_PARTIES = SERVICE_NAME + "searchParties";
    public static final String VERIFY_APPLICATION_PROPERTY = SERVICE_NAME + "verifyApplicationProperty";
    public static final String SELECT = SERVICE_NAME + "select";
    public static final String SEARCH_CADASTREOBJECTS_WITH_GEOMETRY =
            SERVICE_NAME + "searchCadastreObjectsWithGeometry";
    public static final String SEARCH_SOURCES = SERVICE_NAME + "searchSources";
    public static final String GET_ACTIVE_USERS = SERVICE_NAME + "getActiveUsers";
    public static final String SEARCH_USERS = SERVICE_NAME + "searchUsers";
    public static final String GET_APPLICATION_LOG = SERVICE_NAME + "getApplicationLog";
    public static final String GET_SPATIAL_SEARCH_OPTIONS = SERVICE_NAME + "getSpatialSearchOptions";
    public static final String SEARCH_SPATIAL_OBJECTS = SERVICE_NAME + "searchSpatialObjects";
    public static final String SEARCH_BA_UNIT = SERVICE_NAME + "searchBaUnit";
    public static final String SEARCH_BR = SERVICE_NAME + "searchBR";

    public MockSearchClient(String url) {
        super(url, null);
    }

    /**
     * @return default = true
     */
    @Override
    public boolean checkConnection() throws WebServiceClientException {
        return getManager().getResponse(CHECK_CONNECTION, Boolean.class, true);
    }

    /**
     * @return default = new ArrayList<ApplicationSummaryTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> getAssignedApplications() throws WebServiceClientException {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        return getManager().getResponse(GET_ASSIGNED_APPLICATIONS, List.class, defaultResponse);
    }

    /**
     * @return default = new ArrayList<ApplicationSummaryTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> getUnassignedApplications() throws WebServiceClientException {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        return getManager().getResponse(GET_UNASSIGNED_APPLICATIONS, List.class, defaultResponse);
    }

    /**
     * @return default = new ArrayList<ApplicationSummaryTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> searchApplications(
            ApplicationSearchParamsTO applicationSearchParamsTO) throws WebServiceClientException {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        return getManager().getResponse(SEARCH_APPLICATIONS, List.class, defaultResponse,
                applicationSearchParamsTO);
    }

    /**
     * @return default = new PropertyVerifierTO()
     */
    @Override
    public PropertyVerifierTO verifyApplicationProperty(String applicationNumber, String firstPart, String lastPart)
            throws WebServiceClientException {
        PropertyVerifierTO defaultResponse = new PropertyVerifierTO();
        return getManager().getResponse(VERIFY_APPLICATION_PROPERTY, PropertyVerifierTO.class,
                defaultResponse, firstPart, lastPart);
    }

    /**
     * @return default = new ArrayList<ResultForSelectionInfo>()
     */
    @Override
    public List<ResultForSelectionInfo> select(List<QueryForSelect> queries)
            throws WebServiceClientException {
        List<ResultForSelectionInfo> defaultResponse = new ArrayList<ResultForSelectionInfo>();
        return getManager().getResponse(SELECT, List.class, defaultResponse,
                queries);
    }

    /**
     * @return default = new ArrayList<PartySearchResultTO>()
     */
    @Override
    public List<PartySearchResultTO> searchParties(PartySearchParamsTO searchParams)
            throws WebServiceClientException {
        List<PartySearchResultTO> defaultResponse = new ArrayList<PartySearchResultTO>();
        return getManager().getResponse(SEARCH_PARTIES, List.class, defaultResponse,
                searchParams);
    }

    /**
     * @return default = new ArrayList<SourceSearchResultTO>()
     */
    @Override
    public List<SourceSearchResultTO> searchSources(SourceSearchParamsTO searchParams)
            throws WebServiceClientException {
        List<SourceSearchResultTO> defaultResponse = new ArrayList<SourceSearchResultTO>();
        return getManager().getResponse(SEARCH_SOURCES, List.class, defaultResponse,
                searchParams);
    }

    /**
     * @return default = new ArrayList<UserSearchResultTO>()
     */
    @Override
    public List<UserSearchResultTO> getActiveUsers() {
        List<UserSearchResultTO> defaultResponse = new ArrayList<UserSearchResultTO>();
        return getManager().getResponse(GET_ACTIVE_USERS, List.class, defaultResponse);
    }

    /**
     * @return default = new ArrayList<UserSearchAdvancedResultTO>()
     */
    @Override
    public List<UserSearchAdvancedResultTO> searchUsers(UserSearchParamsTO searchParams) throws WebServiceClientException {
        List<UserSearchAdvancedResultTO> defaultResponse = new ArrayList<UserSearchAdvancedResultTO>();
        return getManager().getResponse(SEARCH_USERS, List.class, defaultResponse, searchParams);
    }

    /**
     * @return default = new ArrayList<ApplicationLogResultTO>()
     */
    @Override
    public List<ApplicationLogResultTO> getApplicationLog(String applicationId) throws WebServiceClientException {
        List<ApplicationLogResultTO> defaultResponse = new ArrayList<ApplicationLogResultTO>();
        return getManager().getResponse(GET_APPLICATION_LOG, List.class, defaultResponse, applicationId);
    }
    /**
     * @return default = new ArrayList<BrSearchResultTO>()
     */
    @Override
    public List<BrSearchResultTO> searchBr(BrSearchParamsTO searchParams) throws WebServiceClientException {
        List<BrSearchResultTO> defaultResponse = new ArrayList<BrSearchResultTO>();
        return getManager().getResponse(SEARCH_BR, List.class, defaultResponse, searchParams);
    }

    @Override
    public GenericResult test() throws WebServiceClientException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * @return default = new ArrayList<BaUnitSearchResultTO>()
     */
    @Override
    public List<BaUnitSearchResultTO> searchBaUnit(BaUnitSearchParamsTO searchParams) throws WebServiceClientException {
        List<BaUnitSearchResultTO> defaultResponse = new ArrayList<BaUnitSearchResultTO>();
        return getManager().getResponse(SEARCH_BA_UNIT, List.class, defaultResponse, searchParams);
    }

    /**
     * @return default = new ArrayList<SpatialSearchOptionTO>()
     */
    @Override
    public List<SpatialSearchOptionTO> getSpatialSearchOptions() throws WebServiceClientException {
        List<SpatialSearchOptionTO> defaultResponse = new ArrayList<SpatialSearchOptionTO>();
        return getManager().getResponse(GET_SPATIAL_SEARCH_OPTIONS, List.class, defaultResponse);
    }

    /**
     * @return default = new ArrayList<SpatialSearchResultTO>()
     */
    @Override
    public List<SpatialSearchResultTO> searchSpatialObjects(
            String queryName, String searchString) throws WebServiceClientException {
        List<SpatialSearchResultTO> defaultResponse = new ArrayList<SpatialSearchResultTO>();
        return getManager().getResponse(SEARCH_SPATIAL_OBJECTS, List.class, defaultResponse,
                queryName, searchString);
    }
}
