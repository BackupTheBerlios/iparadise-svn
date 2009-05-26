package com.pdpsoft.g16.apms.business.testcommands;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 28, 2008
 * Time: 4:23:15 PM
 */
public class PreAddReturnFalse implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        return Boolean.FALSE;
    }
}
