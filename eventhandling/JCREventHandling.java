package com.adobe.aem.guides.wknd.core.eventhandling;

import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = EventListener.class)
public class JCREventHandling implements EventListener {

    private Session session;
    @Reference
    SlingRepository slingRepository;

    @Activate
    public void activate() throws Exception {
        session = slingRepository.loginService("testSystemUser", null);
        // loginService(String subServiceName, String workspace) : subservicename=
        // system user created by admin,
        // workspace= null

        session.getWorkspace().getObservationManager().addEventListener(this,
                Event.NODE_ADDED, "/content/wknd/us/en",
                true, null, null, false);
    }

    private static final Logger LOG = LoggerFactory.getLogger(JCREventHandling.class);

    @Override
    public void onEvent(EventIterator eventIterator) {

        // eventIterator will have all the info like nodes added
        LOG.info("******************************** JCR Event Handling***************************");

        try {

            while (eventIterator.hasNext()) {
                LOG.info("Type : {}, Path : {} ", eventIterator.nextEvent().getType(),
                        eventIterator.nextEvent().getPath());

            }

        } catch (Exception e) {
            LOG.error("Error Occured : ", e.getMessage());
        }
    }

}
