package com.pdpsoft.security;

import com.pdpsoft.commonbiz.util.G16CommonBusinessException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 30, 2009
 * Time: 4:11:58 PM
 * @deprecated
 */
public class SecurityException extends G16CommonBusinessException{
    public SecurityException(String s) {
        super(s);
    }

    public SecurityException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public SecurityException(Throwable throwable) {
        super(throwable);
    }

    public SecurityException(String s, int i) {
        super(s, i);
    }

    public SecurityException(String s, Throwable throwable, int i) {
        super(s, throwable, i);
    }

    public SecurityException(Throwable throwable, int i) {
        super(throwable, i);
    }
}
