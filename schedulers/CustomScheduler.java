package com.adobe.aem.guides.wknd.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = SchedulerConfiguration.class)
public class CustomScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(CustomScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfiguration config) {
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);

    }

    @Deactivate
    protected void Deactivate(SchedulerConfiguration config) {
        removeScheduler(config);

    }

    private void addScheduler(SchedulerConfiguration config) {

        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduleOptions.canRunConcurrently(false);
        scheduler.schedule(this, scheduleOptions);
        LOG.info("***************** Scheduler Added*************");
    }

    private void removeScheduler(SchedulerConfiguration config) {
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    @Override
    public void run() {

        LOG.info("***************** Executing Run Method *************");
    }

}
