package com.adobe.aem.guides.wknd.core.eventhandling;

import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED",
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/REMOVED",
        EventConstants.EVENT_FILTER + "=(path=/content/wknd/us/en/*)"
})
public class OSGiEventHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(OSGiEventHandler.class);

    @Override
    public void handleEvent(Event event) {

        LOG.info("******* OSGi Event Handling Logs *******************");

        // event.getTopic() will return what type of event is happening/ handling
        // event.getProperty() to get the path of that resource
        try {
            LOG.info("\n Resource event : {} at {} ", event.getTopic(),
                    event.getProperty(SlingConstants.PROPERTY_PATH));

        } catch (Exception e) {

            LOG.error("Error : {}", e.getMessage());
        }

    }

}
