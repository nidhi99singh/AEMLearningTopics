package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/testservlet")
public class TestServlet extends SlingSafeMethodsServlet {
    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        ResourceResolver resolver = request.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);
        Resource sampleResource = resolver.getResource("/apps/wknd/components/dynamiccomponent");
        Node sampleNode = sampleResource.adaptTo(Node.class);
        response.setHeader("Content-type", "text/html");
        response.getWriter().println("<h1>Welocome user...</h1>");

        LOG.info("---------------------- Result-------------------------");

        try {

            response.getWriter().println(sampleNode);
            String nodePath = sampleNode.getPath();
            response.getWriter().println("Node Path: " + nodePath);

            if (sampleNode.hasProperty("jcr:title")) {

                // display title before update
                response.getWriter().println("Title: " +
                        sampleNode.getProperty("jcr:title").getString());

                // update title property
                sampleNode.setProperty("jcr:title", "default");
                response.getWriter().println("successful");

                // set property
                sampleNode.setProperty("message", "this is a sample message");
                response.getWriter().println(sampleNode);
                // sampleNode.addNode("newNode1");
                // sampleNode.addNode("newNode4");

                // remove node
                if (sampleNode.hasNode("newNode2")) {
                    response.getWriter().println("node exist");
                    sampleNode.getNode("newNode2").remove();
                    response.getWriter().println("node removed");
                }

            } else {
                response.getWriter().println("unsuccessful");
            }
            // print message property
            response.getWriter().println("Message: " +
                    sampleNode.getProperty("message").getString());
            // print title after update
            response.getWriter().println("Title: " +
                    sampleNode.getProperty("jcr:title").getString());

            response.getWriter().close();
            session.save();
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

}