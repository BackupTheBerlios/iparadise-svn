package com.pdpsoft.business;

import com.pdpsoft.business.persistenceimpl.HibernatePersistenceManager;

/**
 * Ashna Company, G16
 * User: h_shayan, d_farid
 * Date: Nov 5, 2006
 * Time: 2:22:18 PM
 */
public final class PersistenceManagerFactory {

    /**
     * Default PM is hibernate
     */
    static PersistenceManager hibernatePersistenceManager;

    private static class PersistenceManagerHolder {
        private static PersistenceManager hibernatePersistenceManager = new HibernatePersistenceManager();
    }

    private PersistenceManagerFactory() {
    }

    /**
     * PersistenceManagerEnum lists the PMs
     */
    public static enum PersistenceManagerEnum {
        HIBERNATE
    }

    /**
     * @param hibernatePersistenceManager Change the default PM.
     *                                    Please just use it in TEST.
     */
    public static void setPersistenceManager(PersistenceManager hibernatePersistenceManager) {
        PersistenceManagerFactory.hibernatePersistenceManager = hibernatePersistenceManager;
    }

    /**
     * @return It returns an instance of hibernate PM
     */
    public static PersistenceManager getDefault() {
        return PersistenceManagerHolder.hibernatePersistenceManager;
    }

    /**
     * @param managerEnum Determine which PM to use
     * @return An instance of PM
     * @throws NullPointerException when managerEnum is null.
     */
    public static PersistenceManager getPersistenceManager(final PersistenceManagerEnum managerEnum)
            throws NullPointerException {
        if (managerEnum == null) {
            throw new NullPointerException("The PersistenceManagerEnum enum is null");
        }
        if (managerEnum.equals(PersistenceManagerEnum.HIBERNATE)) {
            return PersistenceManagerHolder.hibernatePersistenceManager;
        }
        return null;
    }
}
