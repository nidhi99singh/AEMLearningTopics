package com.adobe.aem.guides.wknd.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.config.OSGiFactoryConfig;

@Component(immediate = true)
@Designate(ocd = OSGiFactoryConfig.class, factory = true)
public class OSGiFactoryConfigImpl {

    private String serviceName;
    private int serviceId;
    private String serviceURL;
    private List<OSGiFactoryConfig> configList;

    private static final Logger LOG = LoggerFactory.getLogger(OSGiFactoryConfigImpl.class);

    @Activate
    @Modified
    public void activate(OSGiFactoryConfig config) {
        LOG.info("******************* OSGi Factory Configuration ********************");
        serviceName = config.serviceName();
        LOG.info("Service Name :{}", serviceName);
        serviceId = config.serviceID();
        LOG.info("service ID : {}", serviceId);
        serviceURL = config.serviceURL();
        LOG.info("service url : {}", serviceURL);

    }

    @Reference(service = OSGiFactoryConfig.class, cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC)
    public void bindOSGiFactoryConfig(OSGiFactoryConfig config) {
        LOG.info("******************* Bind  OSGi Factory Configuration ********************");
        if (configList == null) {
            configList = new ArrayList<>();
        }
        configList.add(config);
        LOG.info(configList.toString());
    }

    public void unbindOSGiFactoryConfig(OSGiFactoryConfig config) {
        configList.remove(config);
    }
}
