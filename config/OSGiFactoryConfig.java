package com.adobe.aem.guides.wknd.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Custom Factory OSGI Configuration")
public @interface OSGiFactoryConfig {

    @AttributeDefinition(name = "Servide Name", type = AttributeType.STRING)
    public String serviceName()

    default "default name";

    @AttributeDefinition(name = "Servide Id", type = AttributeType.INTEGER)
    public int serviceID() default 1;

    @AttributeDefinition(name = "Servide URl", type = AttributeType.STRING)
    public String serviceURL() default "default URL";
}
