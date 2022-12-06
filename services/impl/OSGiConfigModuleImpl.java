package com.adobe.aem.guides.wknd.core.services.impl;

import java.util.Arrays;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.wknd.core.config.OSGiConfig;

@Component(immediate = true)
@Designate(ocd = OSGiConfig.class)
public class OSGiConfigModuleImpl {

    private static final Logger LOG = LoggerFactory.getLogger(OSGiConfigModuleImpl.class);

    private String serviceName;
    private int serviceCount;
    private boolean liveData;
    private String[] countries;
    private String runModes;

    @Activate
    protected void activate(OSGiConfig config) {

        serviceName = config.serviceName();
        LOG.info("Service Name : {}", serviceName);

        serviceCount = config.serviceCount();
        LOG.info("Service Count : {}", serviceCount);

        liveData = config.liveData();
        LOG.info("Live Data : {}", liveData);
        countries = config.countries();
        LOG.info(" Countries : {}", Arrays.toString(countries));
        runModes = config.getRunModes();
        LOG.info("Run Modes : {}", runModes);

    }

}
