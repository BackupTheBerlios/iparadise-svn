package com.pdpsoft.g16.apms.business.testcommands;

import com.apsg.samane.g16.crm.CountryEntity;
import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 29, 2008
 * Time: 1:41:10 PM
 */
public class PreTestProcess implements G16CommonBusinessCommand {
    public Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC {
        System.out.println("PreTestProcess.execute");
        return Boolean.TRUE;
    }
}
