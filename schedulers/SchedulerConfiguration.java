package com.adobe.aem.guides.wknd.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Scheduler configuration")
public @interface SchedulerConfiguration {

    @AttributeDefinition(name = "Scheduler name", description = "scheduler description", type = AttributeType.STRING)
    public String schedulerName() default "default custom sling scheduler configuration";

    @AttributeDefinition(name = "Cron Expression", description = "cron expression used by scheduler", type = AttributeType.STRING)
    public String cronExpression() default "0/20 * * * * ?";
}
