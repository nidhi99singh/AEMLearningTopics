package com.adobe.aem.guides.wknd.core.models.impl;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.models.DynamicComponent;

@Model(adaptables = SlingHttpServletRequest.class,

        adapters = DynamicComponent.class,

        resourceType = DynamicComponentImpl.RESOURCE_TYPE,

        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DynamicComponentImpl
        implements DynamicComponent {

    protected static final String RESOURCE_TYPE = "wknd/components/dynamiccomponent";

    @ValueMapValue
    public String title;
    @ValueMapValue
    public String text;

    @Override
    public String getTitle() {
        // TODO Auto-generated method stub
        return StringUtils.isNotBlank(title) ? title : " Default title";
        // return title;
    }

    @Override
    public String getText() {
        // TODO Auto-generated method stub
        return StringUtils.isNotBlank(text) ? text.toUpperCase() : "Default Text";
        // return text;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

}
