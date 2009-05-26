package com.pdpsoft.g16.business;

import com.pdpsoft.commons.G16Exception;


/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Aug 31, 2006
 * Time: 1:47:28 PM
 */
public class BusinessException extends G16Exception {

    private int errorNumber;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String string, int errorNumber) {
        super(string, errorNumber);
        this.errorNumber = errorNumber;
    }

    public BusinessException(String string, Throwable throwable, int errorNumber) {
        super(string, throwable, errorNumber);
        this.errorNumber = errorNumber;
    }

    public BusinessException(Throwable throwable, int errorNumber) {
        super(throwable, errorNumber);
        this.errorNumber = errorNumber;
    }


    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(final int errorNumber) {
        this.errorNumber = errorNumber;
    }
}
