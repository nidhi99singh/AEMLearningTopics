package com.adobe.aem.guides.wknd.core.workflow;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, immediate = true, property = {
        "process.label" + "= custom workflow step 2" })
public class CustomWorkflowStep implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(CustomWorkflowStep.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {

        log.info("***********************Custom Workflow Step***************************");

        WorkflowData workflowData = workItem.getWorkflowData();
        if (workflowData.getPayloadType().equals("JCR_PATH")) {
            Session session = workflowSession.adaptTo(Session.class);
            String path = workflowData.getPayload().toString() + "/jcr:content";
            try {
                Node node = (Node) session.getItem(path);
                String title = metaDataMap.get("title", "");
                String name = metaDataMap.get("text", "");
                log.info("Book Title : {},Author Name : {}", title, name);
                session.save();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

}
