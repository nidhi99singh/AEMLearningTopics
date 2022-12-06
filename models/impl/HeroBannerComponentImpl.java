package com.adobe.aem.guides.wknd.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.wknd.core.models.HeroBannerComponent;

@Model(adaptables = SlingHttpServletRequest.class, adapters = HeroBannerComponent.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroBannerComponentImpl implements HeroBannerComponent {

    @ValueMapValue
    private String herobannerimageurl;

    @ValueMapValue
    private String text;

    @ValueMapValue
    private String subtext;

    @ValueMapValue
    private boolean columnLast;

    @Override
    public String getImageUrl() {
        return herobannerimageurl;
    }

    @Override
    public String getText() {
        // TODO Auto-generated method stub
        return text;
    }

    @Override
    public String getSubText() {
        // TODO Auto-generated method stub
        return subtext;
    }

    @Override
    public boolean getColumnLast() {
        return columnLast;

    }

}
