package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

import java.util.Map;
import java.io.Serializable;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 7:52:35 PM
 */
public class FindByPrimaryKeyTypeOneCommonBusinessCommand implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> map = (Map<Integer, Object>) obj;
        Class clazz = (Class) map.get(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.CLASS);
        Serializable pkValue = (Serializable) map.get(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.PK_VALUE);
        return CommonBusinessCommandUtility.getPersistenceManager().findByPrimaryKey(clazz, pkValue);
    }
}
