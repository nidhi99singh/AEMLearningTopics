// custom workflow
package com.adobe.aem.guides.wknd.core.workflow;

import java.security.KeyStore;
import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
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
                "process.label" + "= Custom Workflow Process"
})
public class WorkflowStep implements WorkflowProcess {

        private static final Logger log = LoggerFactory.getLogger(WorkflowStep.class);

        @Override
        public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap dataMap)
                        throws WorkflowException {

                log.info("***********************************this is logging************************************");
                try {
                        WorkflowData workflowData = workItem.getWorkflowData();
                        Session session = workflowSession.adaptTo(Session.class);
                        String path = workflowData.getPayload().toString() + "/jcr:content";

                        Node node = (Node) session.getItem(path);
                        String[] processArgs = dataMap.get("PROCESS_ARGS", "string").toString().split(",");
                        for (String wfArgs : processArgs) {
                                String[] args = wfArgs.split(":");
                                String prop = args[0];
                                String value = args[1];
                                if (node != null) {
                                        node.setProperty(prop, value);
                                }

                        }
                        session.save();

                        MetaDataMap workflowDataMap = workItem.getWorkflow().getWorkflowData().getMetaDataMap();
                        Set<String> keyset = workflowDataMap.keySet();
                        Iterator<String> iterator = keyset.iterator();
                        while (iterator.hasNext()) {
                                String key = iterator.next();
                                log.info("Item Key-{}, value-{} ", key, workflowDataMap.get(key));
                        }

                } catch (Exception e) {

                        e.printStackTrace();
                }

        }

}
// workitem contains all information including payload
// workflowsession is used to create objects(like resourceResolver)