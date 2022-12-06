package com.adobe.aem.guides.wknd.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Custom OSGi Configuration", description = "fisrt custom osgi configuration")
public @interface OSGiConfig {

    @AttributeDefinition(name = "Service Name", description = "Enter Service Name", type = AttributeType.STRING)
    public String serviceName() default "Custom Service Name";

    @AttributeDefinition(name = "Service Count", description = "Enter Service Count", type = AttributeType.INTEGER)
    public int serviceCount() default 10;

    @AttributeDefinition(name = "Live Data", description = "check to get live data", type = AttributeType.BOOLEAN)
    public boolean liveData() default false;

    @AttributeDefinition(name = "Countries", description = "Add Countries ", type = AttributeType.STRING)
    public String[] countries() default { "IN", "US", "UK" };

    @AttributeDefinition(name = "Run Modes", description = "Select run modes", type = AttributeType.STRING, options = {
            @Option(label = "Author1", value = "author1"),
            @Option(label = "Author2", value = "author2")
    })
    public String getRunModes() default "Custom Run Mode";
}
