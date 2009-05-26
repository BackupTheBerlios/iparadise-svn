package com.pdpsoft.commonbiz.util;

import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 5:41:43 PM
 */
public interface G16CommonBusinessCommand extends PojoBusinessCommand {
    Object execute(Object obj) throws G16CommonBusinessException, HibernateEXC;
}
