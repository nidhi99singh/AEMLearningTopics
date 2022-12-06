package com.adobe.aem.guides.wknd.core.eventhandling;

import java.util.List;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ResourceChangeListener.class, immediate = true, property = {
        ResourceChangeListener.PATHS + "=/content/wknd/us/en/*",
        ResourceChangeListener.CHANGES + "=ADDED",
        ResourceChangeListener.CHANGES + "=CHANGED",
        ResourceChangeListener.CHANGES + "=REMOVED"
})
public class SlingEventHandling implements ResourceChangeListener {

    private static final Logger LOG = LoggerFactory.getLogger(SlingEventHandling.class);

    @Override
    public void onChange(List<ResourceChange> list) {
        LOG.info("********** Sling Event Handling Logs *******************");
        for (ResourceChange resourceChange : list) {
            LOG.info("Event : {}, Resource : {} ", resourceChange.getType(),
                    resourceChange.getPath());
        }

    }

}
