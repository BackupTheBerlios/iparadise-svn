package com.pdpsoft.business;

import com.pdpsoft.commons.G16Exception;

/**
 * Ashnaco - G16
 * User: d_farid
 * Date: Sep 4, 2006
 * Time: 12:08:04 PM
 */
public class BusinessInvocationException extends G16Exception {
    public BusinessInvocationException(String message) {
        super(message);
    }

    public BusinessInvocationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessInvocationException(Throwable throwable) {
        super(throwable);
    }
}
