package com.adobe.aem.guides.wknd.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.SearchService;
import com.adobe.aem.guides.wknd.core.utils.ResourceUtil;
import com.day.cq.search.QueryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = SearchService.class, immediate = true)
public class SearchServiceImpl implements SearchService {

    @Reference
    ResourceResolverFactory resourceResolverFactory;
    @Reference
    QueryBuilder queryBuilder;
    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Override
    public com.google.gson.JsonObject searchResult(String searchText, int startResult, int resultPage) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public JsonObject searchResultSQL2(String searchPath) {

        JsonObject searchResult = new JsonObject();
        // * *** */
        // Map<String, Object> searchResult1 = new HashMap<String, Object>();
        try {
            String SQL2Query = "SELECT * FROM [cq:PageContent] AS node WHERE ISDESCENDANTNODE ("
                    + searchPath
                    + ") ORDER BY [cq:lastCreated] DESC";

            ResourceResolver resolver = ResourceUtil.newResolver(resourceResolverFactory);
            final Session session = resolver.adaptTo(Session.class);

            LOG.info("____________ working fine ________________________");
            LOG.info(SQL2Query);

            final Query query = session.getWorkspace().getQueryManager().createQuery(SQL2Query,
                    Query.JCR_SQL2);

            final QueryResult result = query.execute();
            NodeIterator pages = result.getNodes();
            LOG.info("Pages : {}", pages);

            JsonArray resultArray = new JsonArray();

            // * *** */
            // Map<String, Object> resultArray1 = new HashMap<String, Object>();

            while (pages.hasNext()) {
                LOG.info("0");
                Node page = pages.nextNode();

                if (page.hasProperty("jcr:title")) {
                    LOG.info("1");
                    JsonObject resultObject = new JsonObject();
                    resultObject.add("title", new Gson().toJsonTree(page.getProperty("jcr:title").toString()));
                    resultArray.add(resultObject);

                    // * *** */
                    // Map<String, Object> resultObject1 = new HashMap<String, Object>();
                    // Map<String, Object> resultArray1 = new HashMap<String, Object>();

                    // * *** */
                    // resultObject1.put("title", new
                    // Gson().toJsonTree(page.getProperty("jcr:title").toString()));
                    // LOG.info("2 :{}", resultObject1);

                    // resultArray1.put("node :", resultObject1);

                    // LOG.info("3 : {}", resultArray1);
                } else {
                    LOG.info("property does not exist.");
                }

            }
            searchResult.add("Pages ", resultArray);

        } catch (Exception e) {

            LOG.error("ERROR :", e.getMessage());
        }
        return searchResult;
    }

}
