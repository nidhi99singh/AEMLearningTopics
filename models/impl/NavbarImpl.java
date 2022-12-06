package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.models.NavBar;

@Model(adaptables = SlingHttpServletRequest.class, adapters = NavBar.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class NavbarImpl implements NavBar {

    private static final Logger LOG = LoggerFactory.getLogger(NavbarImpl.class);
    @Inject
    Resource componentResource;

    @ValueMapValue
    private List<String> headlines;

    @ValueMapValue
    private String buttonText;

    @ValueMapValue
    private String buttonUrl;

    @Override
    public List<String> getHeadings() {
        if (headlines != null) {
            return new ArrayList<String>(headlines);
        } else {
            return Collections.emptyList();
        }

    }

    @Override
    public List<Map<String, String>> getNavs() {

        List<Map<String, String>> navbarDetails = new ArrayList<>();
        try {

            LOG.info("----------- Nav bar try block--------------");
            Resource navBars = componentResource.getChild("items");
            LOG.info("Resource : {}", navBars);
            if (navBars != null) {
                for (Resource nav : navBars.getChildren()) {
                    Map<String, String> navMap = new HashMap<>();
                    navMap.put("linkText", nav.getValueMap().get("linkText", String.class));
                    navMap.put("linkUrl", nav.getValueMap().get("linkUrl", String.class));
                    navbarDetails.add(navMap);
                    LOG.info("navbarDetails : {}", navbarDetails);
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return navbarDetails;
    }

    @Override
    public String getButtonText() {
        if (buttonText != null) {
            return buttonText;
        } else {
            return "Button Label";
        }

    }

    @Override
    public String getButtonUrl() {

        if (buttonUrl != null) {
            return buttonUrl;
        } else {
            return "#";
        }

    }

}
