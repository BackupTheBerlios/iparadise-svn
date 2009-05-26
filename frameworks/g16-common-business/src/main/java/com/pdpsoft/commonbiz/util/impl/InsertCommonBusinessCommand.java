package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

import java.util.Map;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 6:53:49 PM
 */
public class InsertCommonBusinessCommand implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> map = (Map<Integer, Object>) obj;
        Object transientObject = map.get(CommonBusinessCommandUtility.InsertCommandEnumeration.OBJECT);
        boolean isUpdate = (Boolean) map.get(CommonBusinessCommandUtility.InsertCommandEnumeration.IS_UPDATE);
        if (isUpdate)
            CommonBusinessCommandUtility.getPersistenceManager().saveOrUpdate(transientObject);
        else
            CommonBusinessCommandUtility.getPersistenceManager().save(transientObject);

        return null;
    }
}
