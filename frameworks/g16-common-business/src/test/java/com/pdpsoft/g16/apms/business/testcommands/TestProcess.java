package com.pdpsoft.g16.apms.business.testcommands;

import com.apsg.samane.g16.crm.CountryEntity;
import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.commonbiz.util.applicationcontex.G16ApplicationContextBeans;
import com.pdpsoft.hibernate.util.HibernateEXC;

import java.util.HashMap;
import java.util.Map;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 29, 2008
 * Time: 1:41:10 PM
 */
public class TestProcess extends G16ApplicationContextBeans implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> map = new HashMap<Integer, Object>(0);
        map.put(0, CountryEntity.class);
        map.put(1, null);
        return getG16Getall().execute(map);
    }
}
