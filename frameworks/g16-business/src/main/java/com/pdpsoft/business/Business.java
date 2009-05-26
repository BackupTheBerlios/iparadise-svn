package com.pdpsoft.business;

import com.pdpsoft.business.persistenceimpl.HibernatePersistenceManager;
import com.pdpsoft.hibernate.util.HibernateEXC;

/**
 * @deprecated The implementation of a business.
 *             Note that someone may use one instance of this class for multiple threads,
 *             so it is recommended that do not use non static fields in this class.
 *             <p/>
 *             Ashna Company, G16
 *             User: h_shayan
 *             Date: Aug 31, 2006
 *             Time: 1:46:08 PM
 */
public abstract class Business {

    protected static PersistenceManager persistenceManager = new HibernatePersistenceManager();

    protected Business() {
        //empty constructor
    }

    public static PersistenceManager getHibernatePersistenceManager() {
        return persistenceManager;
    }

    public static BusinessException handleException(final Exception exception) {
        BusinessException businessException;
        if (exception instanceof HibernateEXC) {
            businessException = new BusinessException(exception.getMessage(), exception.getCause(), ((HibernateEXC) exception).getErrNum());
        } else if (exception instanceof BusinessException) {
            businessException = (BusinessException) exception;
        } else {
            businessException = new BusinessException(exception.getMessage(), exception.getCause());
        }
        return businessException;
    }

    public abstract Object process(Object obj) throws BusinessException;
}
