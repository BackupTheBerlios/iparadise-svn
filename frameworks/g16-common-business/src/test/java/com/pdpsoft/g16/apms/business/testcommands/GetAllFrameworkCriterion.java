package com.pdpsoft.g16.apms.business.testcommands;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.hibernate.criterion.Expression;

import java.util.Map;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 28, 2008
 * Time: 4:06:29 PM
 */
public class GetAllFrameworkCriterion implements G16CommonBusinessCommand {

    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> map = (Map<Integer, Object>) obj;
        System.out.println("map.get(0) = " + map.get(0));
        return Expression.eq("countryName", map.get(0));
    }
}
