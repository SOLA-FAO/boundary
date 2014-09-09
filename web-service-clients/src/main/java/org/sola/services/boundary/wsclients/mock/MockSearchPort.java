/**
 * ******************************************************************************************
 * Copyright (C) 2014 - Food and Agriculture Organization of the United Nations
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
package org.sola.services.boundary.wsclients.mock;

import java.util.ArrayList;
import java.util.List;
import org.sola.services.boundary.wsclients.SearchClient;
import org.sola.webservices.search.*;
import org.sola.webservices.search.MapDefinitionTO;
import org.sola.webservices.transferobjects.search.*;

/**
 * Provides a mock implementation for the
 * {@linkplain org.sola.webservices.search.Search} interface. Uses the
 * {@linkplain MockServiceManager} to obtain the appropriate mock response for
 * each web method.
 * <p>
 * Each method mocked by this class has a public constant defined that can be
 * used to reference a mock response object from the
 * {@linkplain MockServiceManager}. To set a response object for a web method,
 * use the {@linkplain MockServiceManager#setResponse(String, Object)} method
 * referencing the appropriate web method constant from
 * {@linkplain org.sola.services.boundary.wsclients.SearchClient}.</p>
 *
 * @see MockSearchClient
 * @see SearchClient
 * @see MockServiceManager
 * @see MockResponse
 *
 */
public class MockSearchPort implements Search {

    /**
     *
     * @return A reference to the MockServiceManager
     */
    private MockServiceManager getManager() {
        return MockServiceManager.getInstance();
    }

    /**
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type.
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
     * Processes the mock response exception and throws the appropriate service
     * exception or a MockResponseException if the response exception is not a
     * recognized type. Extends
     * {@linkplain #processExceptionBasic(java.lang.Exception) processExceptionBasic}
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
     * Response Key = SearchClient.VERIFY_APPLICATION_PROPERTY
     *
     * @return default = new PropertyVerifierTO()
     */
    @Override
    public PropertyVerifierTO verifyApplicationProperty(String applicationNumber, String firstPart,
            String lastPart) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        PropertyVerifierTO defaultResponse = new PropertyVerifierTO();
        try {
            return getManager().getResponse(SearchClient.VERIFY_APPLICATION_PROPERTY,
                    PropertyVerifierTO.class, defaultResponse, applicationNumber, firstPart, lastPart);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_UNASSIGNED_APPLICATIONS
     *
     * @return default = new ArrayList<ApplicationSearchResultTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> getUnassignedApplications(String locale)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_UNASSIGNED_APPLICATIONS,
                    List.class, defaultResponse, locale);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_ASSIGNED_APPLICATIONS
     *
     * @return default = new ArrayList<ApplicationSearchResultTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> getAssignedApplications(String locale)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_ASSIGNED_APPLICATIONS,
                    List.class, defaultResponse, locale);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_APPLICATIONS
     *
     * @return default = new ArrayList<ApplicationSearchResultTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> searchApplications(ApplicationSearchParamsTO applicationSearchParamsTO)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_APPLICATIONS,
                    List.class, defaultResponse, applicationSearchParamsTO);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_PARTIES
     *
     * @return default = new ArrayList<PartySearchResultTO>()
     */
    @Override
    public List<PartySearchResultTO> searchParties(PartySearchParamsTO searchParams)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<PartySearchResultTO> defaultResponse = new ArrayList<PartySearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_PARTIES,
                    List.class, defaultResponse, searchParams);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_SOURCES
     *
     * @return default = new ArrayList<SourceSearchResultTO>()
     */
    @Override
    public List<SourceSearchResultTO> searchSources(SourceSearchParamsTO searchParams)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SourceSearchResultTO> defaultResponse = new ArrayList<SourceSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_SOURCES,
                    List.class, defaultResponse, searchParams);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_ACTIVE_USERS
     *
     * @return default = new ArrayList<UserSearchResultTO>()
     */
    @Override
    public List<UserSearchResultTO> getActiveUsers()
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<UserSearchResultTO> defaultResponse = new ArrayList<UserSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_ACTIVE_USERS,
                    List.class, defaultResponse);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_APPLICATION_LOG
     *
     * @return default = new ArrayList<ApplicationLogResultTO>()
     */
    @Override
    public List<ApplicationLogResultTO> getApplicationLog(String applicationId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ApplicationLogResultTO> defaultResponse = new ArrayList<ApplicationLogResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_APPLICATION_LOG,
                    List.class, defaultResponse, applicationId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_SPATIAL_SEARCH_OPTIONS
     *
     * @return default = new ArrayList<SpatialSearchOptionTO>()
     */
    @Override
    public List<SpatialSearchOptionTO> getSpatialSearchOptions(String languageCode)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SpatialSearchOptionTO> defaultResponse = new ArrayList<SpatialSearchOptionTO>();
        try {
            return getManager().getResponse(SearchClient.GET_SPATIAL_SEARCH_OPTIONS,
                    List.class, defaultResponse, languageCode);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_SPATIAL_OBJECTS
     *
     * @return default = new ArrayList<SpatialSearchResultTO>()
     */
    @Override
    public List<SpatialSearchResultTO> searchSpatialObjects(String queryName, String searchString, int srid)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SpatialSearchResultTO> defaultResponse = new ArrayList<SpatialSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_SPATIAL_OBJECTS,
                    List.class, defaultResponse, queryName, searchString);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SELECT
     *
     * @return default = new ArrayList<ResultForSelectionInfo>()
     */
    @Override
    public List<ResultForSelectionInfo> select(List<QueryForSelect> arg0)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ResultForSelectionInfo> defaultResponse = new ArrayList<ResultForSelectionInfo>();
        try {
            return getManager().getResponse(SearchClient.SELECT,
                    List.class, defaultResponse, arg0);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_USERS
     *
     * @return default = new ArrayList<UserSearchAdvancedResultTO>()
     */
    @Override
    public List<UserSearchAdvancedResultTO> searchUsers(UserSearchParamsTO searchParams)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<UserSearchAdvancedResultTO> defaultResponse = new ArrayList<UserSearchAdvancedResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_USERS,
                    List.class, defaultResponse, searchParams);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_BR
     *
     * @return default = new ArrayList<BrSearchResultTO>()
     */
    @Override
    public List<BrSearchResultTO> searchBr(BrSearchParamsTO searchParams, String lang)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<BrSearchResultTO> defaultResponse = new ArrayList<BrSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_BR,
                    List.class, defaultResponse, searchParams, lang);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_BA_UNIT
     *
     * @return default = new ArrayList<BaUnitSearchResultTO>()
     */
    @Override
    public List<BaUnitSearchResultTO> searchBaUnit(BaUnitSearchParamsTO searchParams)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<BaUnitSearchResultTO> defaultResponse = new ArrayList<BaUnitSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_BA_UNIT,
                    List.class, defaultResponse, searchParams);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.CHECK_CONNECTION
     *
     * @return default = true
     */
    @Override
    public boolean checkConnection() {
        try {
            return getManager().getResponse(SearchClient.CHECK_CONNECTION, Boolean.class, true);
        } catch (Exception ex) {
            if (RuntimeException.class.isAssignableFrom(ex.getClass())) {
                throw (RuntimeException) ex;
            } else {
                return false;
            }
        }
    }

    /**
     * Response Key = SpatialClient.GET_MAP_DEFINITION
     *
     * @return default = new MapDefinitionTO()
     */
    @Override
    public MapDefinitionTO getMapDefinition(String languageCode)
            throws org.sola.webservices.search.SOLAFault, org.sola.webservices.search.UnhandledFault {
        MapDefinitionTO defaultResponse = new MapDefinitionTO();
        try {
            return getManager().getResponse(SearchClient.GET_MAP_DEFINITION,
                    MapDefinitionTO.class, defaultResponse, languageCode);
        } catch (Exception ex) {
            processExceptionBasic(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.SEARCH_SOURCES
     *
     * @return default = new ArrayList<SourceSearchResultTO>()
     */
    @Override
    public List<PowerOfAttorneySearchResultTO> searchPowerOfAttorney(PowerOfAttorneySearchParamsTO searchParams)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<PowerOfAttorneySearchResultTO> defaultResponse = new ArrayList<PowerOfAttorneySearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.SEARCH_POWER_OF_ATTORNEY,
                    List.class, defaultResponse, searchParams);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public List<RightsExportResultTO> searchRightsForExport(RightsExportParamsTO searchParams) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public byte[] getExtentOfPublicDisplayMap(String nameLastPart) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        byte[] defaultResponse = new byte[0];
        try {
            return getManager().getResponse(SearchClient.GET_EXTENT_OF_PUBLIC_DISPLAY_MAP,
                    byte[].class, defaultResponse, nameLastPart);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public String getMapCenterLabel(byte[] mapCenterPoint) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        String defaultResponse = null;
        try {
            return getManager().getResponse(SearchClient.GET_MAP_CENTER_LABEL,
                    String.class, defaultResponse, mapCenterPoint);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    @Override
    public List<SpatialResult> getPlanCadastreObjects(String cadastreObjectId) throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<SpatialResult> defaultResponse = new ArrayList<SpatialResult>();
        try {
            return getManager().getResponse(SearchClient.GET_PLAN_CADASTRE_OBJECTS,
                    List.class, defaultResponse, cadastreObjectId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_ASSIGNED_JOBS
     *
     * @return default = new ArrayList<ApplicationSearchResultTO>()
     */
    @Override
    public List<ApplicationSearchResultTO> getAssignedJobs(String locale)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<ApplicationSearchResultTO> defaultResponse = new ArrayList<ApplicationSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_ASSIGNED_JOBS,
                    List.class, defaultResponse, locale);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

    /**
     * Response Key = SearchClient.GET_PROPERTIES_TO_ACTION
     *
     * @return default = new ArrayList<BaUnitSearchResultTO>()
     */
    @Override
    public List<BaUnitSearchResultTO> getPropertiesToAction(String locale)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<BaUnitSearchResultTO> defaultResponse = new ArrayList<BaUnitSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_PROPERTIES_TO_ACTION,
                    List.class, defaultResponse, locale);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }
    
      /**
     * Response Key = SearchClient.GET_UNDERLYING_TITLES
     *
     * @return default = new ArrayList<BaUnitSearchResultTO>()
     */
    @Override
    public List<BaUnitSearchResultTO> getUnderlyingTitles(String parcelId)
            throws SOLAAccessFault, SOLAFault, UnhandledFault {
        List<BaUnitSearchResultTO> defaultResponse = new ArrayList<BaUnitSearchResultTO>();
        try {
            return getManager().getResponse(SearchClient.GET_UNDERLYING_TITLES,
                    List.class, defaultResponse, parcelId);
        } catch (Exception ex) {
            processExceptionAccess(ex);
            return null;
        }
    }

}
