package com.adobe.aem.guides.wknd.core.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.QueryBuilderSearchService;
import com.adobe.aem.guides.wknd.core.utils.ResourceUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = QueryBuilderSearchService.class, immediate = true)
public class QueryBuilderSearchServiceImpl implements
        QueryBuilderSearchService {

    private static final Logger LOG = LoggerFactory.getLogger(QueryBuilderSearchServiceImpl.class);

    @Reference
    QueryBuilder queryBuilder;
    @Reference
    ResourceResolverFactory resolverFactory;

    @Activate
    public void activate() {
        LOG.info("----------------------Query Builder Running-------------------------");
    }

    public Map<String, String> createSearchQuery(String path) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("path", path);
        queryMap.put("type", "cq:Page");
        return queryMap;
    }

    @Override
    public JsonObject searchResult(String path) {

        LOG.info("----------------------Query Builder Search Result -------------------------");

        JsonObject searchResult = new JsonObject();
        // Map<String,Long> searchResult = new HashMap<>();
        try {

            ResourceResolver resolver = ResourceUtil.newResolver(resolverFactory);
            Session session = resolver.adaptTo(Session.class);

            Query query = queryBuilder.createQuery(PredicateGroup.create(createSearchQuery(path)),
                    session);

            SearchResult result = query.getResult();

            long totalResults = result.getTotalMatches();
            LOG.info("total results : {}", totalResults);

            // searchResult.put("TotalResults",totalResults);

            List<Hit> hits = result.getHits();
            // JsonArray resultArray = new JsonArray();
            JsonArray resultArray = new JsonArray();

            for (Hit hit : hits) {
                Page page = hit.getResource().adaptTo(Page.class);
                // Map<String, String> resultObject = new HashMap<>();
                JsonObject resultObject = new JsonObject();
                resultObject.add("title", new Gson().toJsonTree(page.getTitle()));
                // resultObject.add("path", new Gson().toJsonTree(page.getDesignPath()));
                resultArray.add(resultObject);
            }
            searchResult.add("results", resultArray);

        } catch (Exception e) {
            LOG.error("error: {}", e.getMessage());
        }
        return searchResult;
    }

}
