package com.pdpsoft.commons;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A wapper used by <code>Scheduler</code> to schedule ScheduledJob instances.
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 19, 2006
 * Time: 10:55:01 AM
 */
public class ScheduledJobWrapper implements Job {
    public static Map<String, ScheduledJob> map = new ConcurrentHashMap<String, ScheduledJob>();
    public static Map<String, JobExecutionException> exceptions = new ConcurrentHashMap<String, JobExecutionException>();

    public void execute(final JobExecutionContext jobExecutionContext) throws org.quartz.JobExecutionException {
        final String name = jobExecutionContext.getJobDetail().getName();
        if (exceptions.get(name) != null)
            try {
                throw exceptions.get(name);
            } catch (JobExecutionException e) {
                throw new org.quartz.JobExecutionException(e);
            } finally {
                exceptions.remove(name);
            }
        new Thread(new Runnable() {
            public void run() {
                try {
                    map.get(name).execute();
                } catch (JobExecutionException e) {
                    e.printStackTrace();
                    exceptions.put(name, e);
                }
            }
        }).start();
    }
}
