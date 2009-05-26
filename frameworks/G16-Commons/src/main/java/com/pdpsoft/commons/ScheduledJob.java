package com.pdpsoft.commons;

/**
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 15, 2006
 * Time: 9:14:04 AM
 */
public interface ScheduledJob {
    void execute() throws JobExecutionException;
}
