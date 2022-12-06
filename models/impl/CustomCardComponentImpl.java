package com.adobe.aem.guides.wknd.core.models.impl;

import java.util.Date;

import org.apache.poi.ss.formula.functions.DateFunc;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.models.CustomCardComponent;

@Model(adaptables = SlingHttpServletRequest.class, adapters = CustomCardComponent.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomCardComponentImpl implements CustomCardComponent {

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String linkUrl;

    @ValueMapValue
    private String titleUrl;

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getTitle() {

        return title;
    }

    @Override
    public String getLinkUrl() {

        return linkUrl;
    }

    @Override
    public String getTitleUrl() {

        return titleUrl;
    }
}
