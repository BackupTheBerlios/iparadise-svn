package com.pdpsoft.commons;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.DirectSchedulerFactory;

import java.util.Date;

/**
 * For common scheduling facilities.
 * It use quartz for this purpose.
 * <p/>
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 15, 2006
 * Time: 9:11:14 AM
 */
public class Scheduler {

    protected static org.quartz.Scheduler sched;

    static {
        try {
            DirectSchedulerFactory.getInstance().createVolatileScheduler(1);
            sched = DirectSchedulerFactory.getInstance().getScheduler();
        } catch (org.quartz.SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * Schedule a continouos job that run every <code>secondInterval<code> seconds.
     *
     * @param scheduledJob The scheduledJob to be executed
     * @param secondInterval secondInterval
     * @throws SchedulerException
     */
    public static void scheduleContinouosJob(ScheduledJob scheduledJob, int secondInterval) throws SchedulerException {
        try {
            // computer a time that is on the next round minute
            Date runTime = org.quartz.TriggerUtils.getEvenSecondDate(new Date());
            String uniqueName = scheduledJob.toString() + System.currentTimeMillis();
            ScheduledJobWrapper.map.put(uniqueName, scheduledJob);
            org.quartz.JobDetail jobDetail = new JobDetail(uniqueName, null, ScheduledJobWrapper.class);
//            Trigger trigger = new SimpleTrigger(uniqueName, "g16", uniqueName, "g16",
//                    new Date(runTime.getTime()), null, SimpleTrigger.REPEAT_INDEFINITELY, period);
            Trigger trigger = TriggerUtils.makeSecondlyTrigger(uniqueName, secondInterval,
                    SimpleTrigger.REPEAT_INDEFINITELY);
            trigger.setStartTime(runTime);
            System.out.println(sched.scheduleJob(jobDetail, trigger));
            sched.start();
        } catch (org.quartz.SchedulerException e) {
            e.printStackTrace();
            throw new SchedulerException(e);
        }
    }

    public static void pauseAll() throws SchedulerException {
        try {
            sched.pauseAll();
        } catch (org.quartz.SchedulerException e) {
            e.printStackTrace();
            throw new SchedulerException(e);
        }
    }

    public static void shutdown() throws SchedulerException {
        try {
            sched.shutdown();
        } catch (org.quartz.SchedulerException e) {
            e.printStackTrace();
            throw new SchedulerException(e);
        }
    }

    public static void resumeAll() throws SchedulerException {
        try {
            sched.resumeAll();
        } catch (org.quartz.SchedulerException e) {
            e.printStackTrace();
            throw new SchedulerException(e);
        }
    }
}
