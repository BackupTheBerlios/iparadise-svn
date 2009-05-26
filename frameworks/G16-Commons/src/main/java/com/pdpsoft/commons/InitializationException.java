package com.pdpsoft.commons;

/**
 * A runtime exception reserved for Runtime Exceptions in Initialization of objects.
 * For example if you have a RuntimeException in the static initializer.
 * <p/>
 * Ashnaco - G16
 * User: d_farid
 * Date: Oct 14, 2006
 * Time: 8:07:51 AM
 */
public class InitializationException extends RuntimeException {

    public InitializationException(java.lang.String message) {
        super(message);
    }

    public InitializationException(java.lang.String message, java.lang.Throwable cause) {
        super(message, cause);
    }

    public InitializationException(java.lang.Throwable cause) {
        super(cause);
    }
}
