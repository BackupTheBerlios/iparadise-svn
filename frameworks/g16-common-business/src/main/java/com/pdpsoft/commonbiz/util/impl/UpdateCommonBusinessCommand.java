package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 6:54:06 PM
 */
public class UpdateCommonBusinessCommand implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        CommonBusinessCommandUtility.getPersistenceManager().saveOrUpdate(obj);
        return null;
    }
}
