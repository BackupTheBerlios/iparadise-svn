package com.pdpsoft.g16.apms.business.testcommands;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.commonbiz.util.applicationcontex.G16ApplicationContextBeans;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Jun 3, 2008
 * Time: 4:07:35 PM
 */
public class GetAllAzades extends G16ApplicationContextBeans implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        System.out.println("GetAllAzades.execute");
        return "Azade Test Sample";
    }
}
