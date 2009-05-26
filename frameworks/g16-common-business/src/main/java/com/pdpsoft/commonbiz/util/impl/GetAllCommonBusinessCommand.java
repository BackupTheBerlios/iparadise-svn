package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.hibernate.criterion.Criterion;

import java.util.Map;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 6:54:37 PM
 */
public class GetAllCommonBusinessCommand implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> map = (Map<Integer, Object>) obj;
        Class clazz = (Class) map.get(0);
        Object objectParam = map.get(1);

        if(objectParam == null)
            return CommonBusinessCommandUtility.getPersistenceManager().findAll(clazz);
        else {
            return CommonBusinessCommandUtility.getPersistenceManager().findByCondition(clazz, (Criterion) objectParam);
        }
    }
}
