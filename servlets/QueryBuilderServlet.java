package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.guides.wknd.core.services.QueryBuilderSearchService;
import com.google.gson.JsonObject;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/testquery")
public class QueryBuilderServlet extends SlingSafeMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(QueryBuilderServlet.class);

    @Reference
    QueryBuilderSearchService builderSearchService;

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        LOG.info("----------------------Query Builder Search Result 1-------------------------");

        JsonObject searchResult = null;
        try {
            LOG.info("----------------------Query Builder Search Result try block-------------------------");

            String path = request.getRequestParameter("path").getString();
            searchResult = builderSearchService.searchResult(path);

        } catch (Exception e) {

            LOG.error("Error: {}", e.getMessage());
        }

        response.setContentType("application/json");
        response.getWriter().write("1");
        response.getWriter().write(searchResult.toString());

    }

}
