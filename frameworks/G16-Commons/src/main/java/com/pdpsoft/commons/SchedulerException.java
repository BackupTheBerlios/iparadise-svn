package com.pdpsoft.commons;

/**
 * Thrown in case exception in scheduling.
 * See <code>Scheduler</code> 
 *
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 15, 2006
 * Time: 9:28:35 AM
 */
public class SchedulerException extends G16Exception {
    public SchedulerException(String message) {
        super(message);
    }

    public SchedulerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SchedulerException(Throwable cause) {
        super(cause);
    }
}
