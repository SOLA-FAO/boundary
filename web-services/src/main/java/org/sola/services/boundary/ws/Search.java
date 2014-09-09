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
package org.sola.services.boundary.ws;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.sola.services.boundary.transferobjects.configuration.ConfigMapLayerTO;
import org.sola.services.boundary.transferobjects.configuration.CrsTO;
import org.sola.services.boundary.transferobjects.configuration.MapDefinitionTO;
import org.sola.services.boundary.transferobjects.search.*;
import org.sola.services.common.ServiceConstants;
import org.sola.services.common.contracts.GenericTranslator;
import org.sola.services.common.faults.SOLAAccessFault;
import org.sola.services.common.faults.SOLAFault;
import org.sola.services.common.faults.UnhandledFault;
import org.sola.services.common.webservices.AbstractWebService;
import org.sola.services.ejb.search.businesslogic.SearchEJBLocal;
import org.sola.services.ejb.search.repository.entities.*;
import org.sola.services.ejb.search.spatial.QueryForSelect;
import org.sola.services.ejb.search.spatial.ResultForSelectionInfo;

/**
 * Web Service Boundary class to expose
 * {@linkplain org.sola.services.ejb.search.businesslogic.SearchEJB} methods.
 */
@WebService(serviceName = "search-service", targetNamespace = ServiceConstants.SEARCH_WS_NAMESPACE)
public class Search extends AbstractWebService {

    @EJB
    SearchEJBLocal searchEJB;
    @Resource
    private WebServiceContext wsContext;

    /**
     * Web method that can be used to validate if the web service is available.
     *
     * @return Always true
     */
    @WebMethod(operationName = "CheckConnection")
    public boolean CheckConnection() {
        return true;
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getPropertyVerifier(java.lang.String, java.lang.String, java.lang.String)
     * SearchEJB.getPropertyVerifier}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "VerifyApplicationProperty")
    public PropertyVerifierTO VerifyApplicationProperty(
            @WebParam(name = "applicationNumber") final String applicationNumber,
            @WebParam(name = "firstPart") final String firstPart,
            @WebParam(name = "lastPart") final String lastPart) throws SOLAFault, UnhandledFault,
            SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                PropertyVerifier propertyVerifier
                        = searchEJB.getPropertyVerifier(applicationNumber, firstPart, lastPart);
                result[0] = GenericTranslator.toTO(
                        propertyVerifier, PropertyVerifierTO.class);
            }
        });

        return (PropertyVerifierTO) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getUnassignedApplications(java.lang.String)
     * SearchEJB.getUnassignedApplications}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetUnassignedApplications")
    public List<ApplicationSearchResultTO> GetUnassignedApplications(
            @WebParam(name = "locale") String locale) throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String localeTmp = locale;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<ApplicationSearchResult> appList = searchEJB.getUnassignedApplications(localeTmp);
                result[0] = GenericTranslator.toTOList(
                        appList, ApplicationSearchResultTO.class);
            }
        });

        return (List<ApplicationSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getAssignedApplications(java.lang.String)
     * SearchEJB.getAssignedApplications}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAssignedApplications")
    public List<ApplicationSearchResultTO> GetAssignedApplications(@WebParam(name = "locale") String locale)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String localeTmp = locale;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<ApplicationSearchResult> appList = searchEJB.getAssignedApplications(localeTmp);
                result[0] = GenericTranslator.toTOList(
                        appList, ApplicationSearchResultTO.class);
            }
        });

        return (List<ApplicationSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchApplications(org.sola.services.ejb.search.repository.entities.ApplicationSearchParams)
     * SearchEJB.searchApplications}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SearchApplications")
    public List<ApplicationSearchResultTO> SearchApplications(
            @WebParam(name = "ApplicationSearchParamsTO") ApplicationSearchParamsTO paramsTO)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final ApplicationSearchParamsTO paramsTOTmp = paramsTO;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                ApplicationSearchParams params = GenericTranslator.fromTO(paramsTOTmp, ApplicationSearchParams.class, null);
                List<ApplicationSearchResult> appList = searchEJB.searchApplications(params);
                result[0] = GenericTranslator.toTOList(
                        appList, ApplicationSearchResultTO.class);
            }
        });

        return (List<ApplicationSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getSpatialResultFromSelection(java.util.List)
     * SearchEJB.getSpatialResultFromSelection}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "Select")
    public List<ResultForSelectionInfo> Select(List<QueryForSelect> queries)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final List<QueryForSelect> queriesTmp = queries;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = searchEJB.getSpatialResultFromSelection(queriesTmp);
            }
        });

        return (List<ResultForSelectionInfo>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchParties(org.sola.services.ejb.search.repository.entities.PartySearchParams)
     * SearchEJB.searchParties}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SearchParties")
    public List<PartySearchResultTO> searchParties(
            @WebParam(name = "searchParams") PartySearchParamsTO searchParams)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final PartySearchParamsTO searchParamsTmp = searchParams;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                PartySearchParams params = GenericTranslator.fromTO(searchParamsTmp,
                        PartySearchParams.class, null);
                result[0] = GenericTranslator.toTOList(
                        searchEJB.searchParties(params), PartySearchResultTO.class);
            }
        });

        return (List<PartySearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchSources(org.sola.services.ejb.search.repository.entities.SourceSearchParams)
     * SearchEJB.searchSources}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SearchSources")
    public List<SourceSearchResultTO> searchSources(
            @WebParam(name = "searchParams") SourceSearchParamsTO searchParams)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final SourceSearchParamsTO searchParamsTmp = searchParams;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                SourceSearchParams params = GenericTranslator.fromTO(searchParamsTmp,
                        SourceSearchParams.class, null);
                result[0] = GenericTranslator.toTOList(
                        searchEJB.searchSources(params), SourceSearchResultTO.class);

            }
        });

        return (List<SourceSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchPowerOfAttorney(org.sola.services.ejb.search.repository.entities.PowerOfAttorneySearchParams)
     * SearchEJB.searchPowerOfAttorney}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "searchPowerOfAttorney")
    public List<PowerOfAttorneySearchResultTO> searchPowerOfAttorney(
            @WebParam(name = "searchParams") final PowerOfAttorneySearchParamsTO searchParams)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                PowerOfAttorneySearchParams params = GenericTranslator.fromTO(searchParams,
                        PowerOfAttorneySearchParams.class, null);
                result[0] = GenericTranslator.toTOList(
                        searchEJB.searchPowerOfAttorney(params), PowerOfAttorneySearchResultTO.class);

            }
        });

        return (List<PowerOfAttorneySearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getActiveUsers()
     * SearchEJB.getActiveUsers}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetActiveUsers")
    public List<UserSearchResultTO> getActiveUsers() throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<UserSearchResult> activeUsers = searchEJB.getActiveUsers();
                result[0] = GenericTranslator.toTOList(activeUsers, UserSearchResultTO.class);

            }
        });

        return (List<UserSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchUsers(org.sola.services.ejb.search.repository.entities.UserSearchParams)
     * SearchEJB.searchUsers}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "searchUsers")
    public List<UserSearchAdvancedResultTO> searchUsers(@WebParam(name = "searchParams") UserSearchParamsTO searchParams)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final UserSearchParamsTO searchParamsTmp = searchParams;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                UserSearchParams params = GenericTranslator.fromTO(searchParamsTmp,
                        UserSearchParams.class, null);
                List<UserSearchResult> users = searchEJB.searchUsers(params);
                result[0] = GenericTranslator.toTOList(users, UserSearchAdvancedResultTO.class);

            }
        });

        return (List<UserSearchAdvancedResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getApplicationLog(java.lang.String)
     * SearchEJB.getApplicationLog}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "getApplicationLog")
    public List<ApplicationLogResultTO> getApplicationLog(@WebParam(name = "applicationId") String applicationId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String applicationIdTmp = applicationId;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<ApplicationLogResult> log = searchEJB.getApplicationLog(applicationIdTmp);
                result[0] = GenericTranslator.toTOList(log, ApplicationLogResultTO.class);

            }
        });

        return (List<ApplicationLogResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchBr(org.sola.services.ejb.search.repository.entities.BrSearchParams, java.lang.String)
     * SearchEJB.searchBr}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SearchBr")
    public List<BrSearchResultTO> searchBr(@WebParam(name = "searchParams") BrSearchParamsTO searchParams,
            @WebParam(name = "lang") String lang)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] params = {searchParams, lang};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                BrSearchParamsTO searchParams = (BrSearchParamsTO) params[0];
                String lang = params[1] == null ? null : params[1].toString();
                if (searchParams != null) {
                    result[0] = GenericTranslator.toTOList(
                            searchEJB.searchBr(GenericTranslator.fromTO(searchParams, BrSearchParams.class, null), lang),
                            BrSearchResultTO.class);
                }
            }
        });

        return (List<BrSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchBaUnits(org.sola.services.ejb.search.repository.entities.BaUnitSearchParams)
     * SearchEJB.searchBaUnits}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "searchBaUnit")
    public List<BaUnitSearchResultTO> searchBaUnit(@WebParam(name = "searchParams") BaUnitSearchParamsTO searchParams)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] params = {searchParams};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                BaUnitSearchParamsTO searchParams = (BaUnitSearchParamsTO) params[0];
                if (searchParams != null) {
                    result[0] = GenericTranslator.toTOList(
                            searchEJB.searchBaUnits(GenericTranslator.fromTO(
                                            searchParams, BaUnitSearchParams.class, null)),
                            BaUnitSearchResultTO.class);
                }
            }
        });

        return (List<BaUnitSearchResultTO>) result[0];
    }

    /**
     * See {@link SearchEJBLocal#searchRightsForExport(org.sola.services.ejb.search.repository.entities.RightsExportParams)
     * }
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "searchRightsForExport")
    public List<RightsExportResultTO> searchRightsForExport(@WebParam(name = "searchParams") RightsExportParamsTO searchParams)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] params = {searchParams};
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        searchEJB.searchRightsForExport(GenericTranslator.fromTO(
                                        (RightsExportParamsTO) params[0], RightsExportParams.class, null)),
                        RightsExportResultTO.class);
            }
        });

        return (List<RightsExportResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getSpatialSearchOptions(java.lang.String)
     * SearchEJB.getSpatialSearchOptions}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetSpatialSearchOptions")
    public List<SpatialSearchOptionTO> GetSpatialSearchOptions(
            @WebParam(name = "languageCode") String languageCode)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String languageCodeTmp = languageCode;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        searchEJB.getSpatialSearchOptions(languageCodeTmp),
                        SpatialSearchOptionTO.class);
            }
        });
        return (List<SpatialSearchOptionTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#searchSpatialObjects(java.lang.String, java.lang.String)
     * SearchEJB.searchSpatialObjects}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "SearchSpatialObjects")
    public List<SpatialSearchResultTO> SearchSpatialObjects(
            @WebParam(name = "queryName") String queryName,
            @WebParam(name = "searchString") String searchString,
            @WebParam(name = "srid") int srid)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String queryNameTmp = queryName;
        final String searchStringTmp = searchString;
        final int sridTmp = srid;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = GenericTranslator.toTOList(
                        searchEJB.searchSpatialObjects(queryNameTmp, searchStringTmp, sridTmp),
                        SpatialSearchResultTO.class);
            }
        });
        return (List<SpatialSearchResultTO>) result[0];
    }

    /**
     * Retrieves the map layer configuration data
     *
     * @param languageCode The language code to use for localization of display
     * values
     * @return The configuration data for each map layer
     * @throws UnhandledFault
     * @throws SOLAFault
     * @see
     * org.sola.services.ejb.search.businesslogic.SearchEJB#getMapSettingList()
     * @see
     * org.sola.services.ejb.search.businesslogic.SearchEJB#getConfigMapLayerList(java.lang.String)
     */
    @WebMethod(operationName = "GetMapDefinition")
    public MapDefinitionTO GetMapDefinition(@WebParam(name = "languageCode") String languageCode)
            throws UnhandledFault, SOLAFault {

        final Object[] result = {null};
        final String languageCodeTmp = languageCode;
        runUnsecured(wsContext, new Runnable() {

            @Override
            public void run() {
                HashMap<String, String> mapSettings = searchEJB.getMapSettingList();
                List<ConfigMapLayer> configMapLayerList
                        = searchEJB.getConfigMapLayerList(languageCodeTmp);
                List<Crs> crsList = searchEJB.getCrsList();
                MapDefinitionTO mapDefinition = new MapDefinitionTO();
                mapDefinition.setCrsList(GenericTranslator.toTOList(crsList, CrsTO.class));
                mapDefinition.setWest(Double.parseDouble(mapSettings.get("map-west")));
                mapDefinition.setSouth(Double.parseDouble(mapSettings.get("map-south")));
                mapDefinition.setEast(Double.parseDouble(mapSettings.get("map-east")));
                mapDefinition.setNorth(Double.parseDouble(mapSettings.get("map-north")));
                mapDefinition.setSnapTolerance(Double.parseDouble(mapSettings.get("map-tolerance")));
                mapDefinition.setSurveyPointShiftRuralArea(
                        Double.parseDouble(mapSettings.get("map-shift-tolerance-rural")));
                mapDefinition.setSurveyPointShiftUrbanArea(
                        Double.parseDouble(mapSettings.get("map-shift-tolerance-urban")));
                for (ConfigMapLayer configMapLayer : configMapLayerList) {
                    mapDefinition.getLayers().add(
                            GenericTranslator.toTO(configMapLayer, ConfigMapLayerTO.class));
                }
                result[0] = mapDefinition;
            }
        });

        return (MapDefinitionTO) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SearchEJB#
     * getExtentOfPublicDisplayMap(
     * java.lang.String) SearchEJB.getExtentOfPublicDisplayMap}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetExtentOfPublicDisplayMap")
    public byte[] GetExtentOfPublicDisplayMap(
            @WebParam(name = "nameLastPart") final String nameLastPart)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = searchEJB.getExtentOfPublicDisplayMap(nameLastPart);
            }
        });

        return (byte[]) result[0];
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SearchEJB#
     * getMapCenterLabel(
     * byte[]) SearchEJB.getMapCenterLabel}
     *
     * @throws SOLAValidationFault
     * @throws OptimisticLockingFault
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetMapCenterLabel")
    public String GetMapCenterLabel(
            @WebParam(name = "mapCenterPoint") final byte[] mapCenterPoint)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = searchEJB.getMapCenterLabel(mapCenterPoint);
            }
        });

        return result[0].toString();
    }

    /**
     * See {@linkplain org.sola.services.ejb.source.businesslogic.SearchEJB#
     * getPlanCadastreObjects(
     * String) SearchEJB.getPlanCadastreObjects}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPlanCadastreObjects")
    public List<SpatialResult> GetPlanCadastreObjects(
            @WebParam(name = "cadastreObjectId") final String cadastreObjectId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                result[0] = searchEJB.getPlanCadastreObjects(cadastreObjectId);
            }
        });

        return (List<SpatialResult>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getAssignedJobs(java.lang.String)
     * SearchEJB.getAssignedJobs}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetAssignedJobs")
    public List<ApplicationSearchResultTO> GetAssignedJobs(@WebParam(name = "locale") String locale)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String localeTmp = locale;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<ApplicationSearchResult> appList = searchEJB.getAssignedJobs(localeTmp);
                result[0] = GenericTranslator.toTOList(
                        appList, ApplicationSearchResultTO.class);
            }
        });

        return (List<ApplicationSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getPropertiesToAction(java.lang.String)
     * SearchEJB.getPropertiesToAction}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetPropertiesToAction")
    public List<BaUnitSearchResultTO> GetPropertiesToAction(@WebParam(name = "locale") String locale)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final String localeTmp = locale;
        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<BaUnitSearchResult> appList = searchEJB.getPropertiesToAction(localeTmp);
                result[0] = GenericTranslator.toTOList(
                        appList, BaUnitSearchResultTO.class);
            }
        });

        return (List<BaUnitSearchResultTO>) result[0];
    }

    /**
     * See {@linkplain  org.sola.services.ejb.search.businesslogic.SearchEJB#getUnderlyingTitles(java.lang.String)
     * SearchEJB.getUnderlyingTitles}
     *
     * @throws SOLAFault
     * @throws UnhandledFault
     * @throws SOLAAccessFault
     */
    @WebMethod(operationName = "GetUnderlyingTitles")
    public List<BaUnitSearchResultTO> GetUnderlyingTitles(
            final @WebParam(name = "parcelId") String parcelId)
            throws SOLAFault, UnhandledFault, SOLAAccessFault {

        final Object[] result = {null};

        runGeneralQuery(wsContext, new Runnable() {

            @Override
            public void run() {
                List<BaUnitSearchResult> appList = searchEJB.getUnderlyingTitles(parcelId);
                result[0] = GenericTranslator.toTOList(
                        appList, BaUnitSearchResultTO.class);
            }
        });

        return (List<BaUnitSearchResultTO>) result[0];
    }
}
