package com.pdpsoft.commons;

/**
 *
 * Thrown in case of exception in executation of job.
 * See <code>ScheduledJob</code>
 *
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 15, 2006
 * Time: 9:16:59 AM
 */
public class JobExecutionException extends G16Exception {
    public JobExecutionException(String message) {
        super(message);
    }

    public JobExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobExecutionException(Throwable cause) {
        super(cause);
    }
}
