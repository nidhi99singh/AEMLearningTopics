// package com.adobe.aem.guides.wknd.core.eventhandling;

// import java.util.Iterator;

// import org.osgi.service.component.annotations.Component;
// import org.osgi.service.event.Event;
// import org.osgi.service.event.EventConstants;
// import org.osgi.service.event.EventHandler;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// import com.day.cq.wcm.api.PageEvent;
// import com.day.cq.wcm.api.PageModification;

// @Component(service = EventHandler.class, immediate = true, property = {
// EventConstants.EVENT_TOPIC + "=" + PageEvent.EVENT_TOPIC,
// EventConstants.EVENT_FILTER + "=(path=/content/wknd/us/en/*)"
// })
// public class PageEventHandling implements EventHandler {

// private static final Logger LOG =
// LoggerFactory.getLogger(PageEventHandling.class);

// @Override
// public void handleEvent(Event event) {
// LOG.info("***************Page Event Handling Logs****************");

// Iterator<PageModification> pageInfo =
// PageEvent.fromEvent(event).getModifications();
// LOG.info("Page Info : ", pageInfo);
// while (pageInfo.hasNext()) {
// PageModification pageModification = pageInfo.next();
// LOG.info("Type : {}, Page : {}", pageModification.getType(),
// pageModification.getPath());
// pageModification.getEventProperties()
// .forEach((key, value) -> LOG.info("key : {}, Value : {}", key, value));
// }
// try {

// } catch (Exception e) {
// LOG.error("Error", e.getMessage());
// }

// }

// }
