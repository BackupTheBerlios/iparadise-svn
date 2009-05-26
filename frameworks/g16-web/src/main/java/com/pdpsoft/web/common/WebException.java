package com.pdpsoft.web.common;

import com.pdpsoft.commons.G16Exception;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Sep 4, 2006
 * Time: 2:52:00 PM
 */
public class WebException extends G16Exception {

    private int errorNumber;
    private Object[] messageParameters;

    public WebException(String s) {
        super(s);
    }

    public WebException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WebException(Throwable throwable) {
        super(throwable);
    }

    public WebException(String string, int errorNumber, Object[] messageParameters) {
        super(string);
        this.errorNumber = errorNumber;
        this.messageParameters = messageParameters;
    }

    public WebException(String string, Throwable throwable, int errorNumber, Object[] messageParameters) {
        super(string, throwable);
        this.errorNumber = errorNumber;
        this.messageParameters = messageParameters;
    }

    public WebException(Throwable throwable, int errorNumber, Object[] messageParameters) {
        super(throwable);
        this.errorNumber = errorNumber;
        this.messageParameters = messageParameters;
    }

    public WebException(String string, int i, int errorNumber, Object[] messageParameters) {
        super(string, i);
        this.errorNumber = errorNumber;
        this.messageParameters = messageParameters;
    }

    public WebException(String string, Throwable throwable, int i, int errorNumber, Object[] messageParameters) {
        super(string, throwable, i);
        this.errorNumber = errorNumber;
        this.messageParameters = messageParameters;
    }

    public WebException(Throwable throwable, int i, int errorNumber, Object[] messageParameters) {
        super(throwable, i);
        this.errorNumber = errorNumber;
        this.messageParameters = messageParameters;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(int errorNumber) {
        this.errorNumber = errorNumber;
    }

    public Object[] getMessageParameters() {
        return messageParameters;
    }

    public void setMessageParameters(Object[] messageParameters) {
        this.messageParameters = messageParameters;
    }
}
