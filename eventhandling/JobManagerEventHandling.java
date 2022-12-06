package com.adobe.aem.guides.wknd.core.eventhandling;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
        EventConstants.EVENT_FILTER + "=(path=/content/wknd/us/en/*)"
})
public class JobManagerEventHandling implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(JobManagerEventHandling.class);

    @Reference
    JobManager JobManager;

    @Override
    public void handleEvent(Event event) {

        LOG.info("******************** JOB Mangaer Logs*********************");

        Map<String, Object> jobConsumer = new HashMap<String, Object>();

        jobConsumer.put("event", event.getTopic());
        jobConsumer.put("path", event.getProperty(SlingConstants.PROPERTY_PATH));
        Job job = JobManager.addJob("consumer/add", jobConsumer);

        // "consumer/add" property present in JOB consumer. it is used to find job
        // consumer
    }

}
