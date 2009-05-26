package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

import java.util.List;
import java.util.Map;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 7:52:35 PM
 * @deprecated 
 */
public class FindByPrimaryKeyTypeThreeCommonBusinessCommand implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> map = (Map<Integer, Object>) obj;
        Class clazz = (Class) map.get(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.CLASS);
        List<Integer> pkValue = (List<Integer>) map.get(CommonBusinessCommandUtility.FindByPrimaryKeyCommandEnumeration.PK_VALUE);
        return CommonBusinessCommandUtility.getPersistenceManager().findByPrimaryKeys(clazz, pkValue);
    }
}
