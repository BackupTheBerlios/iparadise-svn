package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 8:03:24 PM
 * @deprecated 
 */
public class FindByPrimaryKeyTypeTwoCommonBusinessCommand implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        return CommonBusinessCommandUtility.getPersistenceManager().findByPrimaryKey(obj);
    }
}
