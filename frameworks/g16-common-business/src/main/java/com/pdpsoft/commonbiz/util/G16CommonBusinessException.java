package com.pdpsoft.commonbiz.util;

import com.pdpsoft.business.BusinessException;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 5:48:00 PM
 */
public class G16CommonBusinessException extends BusinessException {
    public G16CommonBusinessException(String s) {
        super(s);
    }

    public G16CommonBusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public G16CommonBusinessException(Throwable throwable) {
        super(throwable);
    }

    public G16CommonBusinessException(String s, int i) {
        super(s, i);
    }

    public G16CommonBusinessException(String s, Throwable throwable, int i) {
        super(s, throwable, i);
    }

    public G16CommonBusinessException(Throwable throwable, int i) {
        super(throwable, i);
    }
}
