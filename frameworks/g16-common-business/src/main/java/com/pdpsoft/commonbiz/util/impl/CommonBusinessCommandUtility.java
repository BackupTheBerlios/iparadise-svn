package com.pdpsoft.commonbiz.util.impl;

import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Apr 29, 2008
 * Time: 7:07:26 PM
 */
public abstract class CommonBusinessCommandUtility {

     public interface CommandEnumeration {
     }
     public interface InsertCommandEnumeration extends CommandEnumeration {
         public static final int OBJECT = 0;
         public static final int IS_UPDATE = 1;
     }
     public interface FindByPrimaryKeyCommandEnumeration extends CommandEnumeration {
         public static final int CLASS = 0;
         public static final int PK_VALUE = 1;
     }

    /**
     *
     * @return Instance of PersistenceManager
     */
    public static PersistenceManager getPersistenceManager() {
        return PersistenceManagerFactory.getDefault();   
    }
}
