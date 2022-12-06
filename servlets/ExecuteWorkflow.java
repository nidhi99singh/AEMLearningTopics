package com.adobe.aem.guides.wknd.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/executeWorkflow")
public class ExecuteWorkflow extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        String status = "workflow executing..";
        ResourceResolver resolver = request.getResourceResolver();
        String payload = request.getRequestParameter("page").toString();

        try {
            WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
            WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/SampleModel");
            WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);

            // page is the payload so path of page is passed as payload
            status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
        } catch (WorkflowException e) {
            e.printStackTrace();
        }

        response.setContentType("application/json");
        response.getWriter().write(status);

    }

}
