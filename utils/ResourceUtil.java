package com.adobe.aem.guides.wknd.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public class ResourceUtil {

    public ResourceUtil() {
    }

    public static final String SERVICE_USER = "testSystemUser";

    public static ResourceResolver newResolver(ResourceResolverFactory resolverFactory) throws LoginException {

        final Map<String, Object> map = new HashMap<String, Object>();
        map.put(ResourceResolverFactory.SUBSERVICE, SERVICE_USER);

        ResourceResolver resolver = resolverFactory.getServiceResourceResolver(map);
        return resolver;
    }
}
