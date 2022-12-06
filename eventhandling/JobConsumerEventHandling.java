package com.adobe.aem.guides.wknd.core.eventhandling;

import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = JobConsumer.class, immediate = true, property = {
        JobConsumer.PROPERTY_TOPICS + "=consumer/add"
})
public class JobConsumerEventHandling implements JobConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(JobConsumerEventHandling.class);

    @Override
    public JobResult process(Job job) {

        try {
            LOG.info("******************** JOB Consumer Logs*********************");
            String path = (String) job.getProperty("path");
            String event = (String) job.getProperty("event");
            LOG.info("******************** JOB Consumer status OK...*********************");
            return JobConsumer.JobResult.OK;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            return JobConsumer.JobResult.FAILED;
        }

    }

}
