package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.services.SearchService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/wknd/search")
public class SearchServiceServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceServlet.class);
    @Reference
    SearchService searchService;

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        JsonObject searchResult = null;
        try {
            String searchPath = request.getRequestParameter("searchPath").getString();
            searchResult = searchService.searchResultSQL2(searchPath);
        } catch (Exception e) {
            LOG.info("ERROR : {}", e.getMessage());
        }

        response.setContentType("application/json");

        // response.getWriter().print(searchResult);
        response.getWriter().write(searchResult.toString());
    }

}
