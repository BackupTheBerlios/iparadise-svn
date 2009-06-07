package com.pdpsoft.hibernate.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.InstantiationException;
import org.hibernate.cache.CacheException;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.ValidationFailure;
import org.hibernate.exception.*;
import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.type.EntityType;
import org.hibernate.type.SerializationException;
import org.hibernate.type.Type;
import com.pdpsoft.security.LoginModuleContextHolder;

/**
 * @author hasha
 */
public final class HibernateUtility {
    private HibernateUtility() {
    }

    private static final Log logger = LogFactory.getLog("HibernateUtility");

    private static SessionFactory sessionFactory;

    //    private static final String CONFIGURATION_FILE = "/hibernate/paradise/cfg/Configuration.cfg.xml";
    private static final String CONFIGURATION_FILE = "Configuration.cfg.xml";

    private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<Session>();
    private static final ThreadLocal<Transaction> transactionThreadLocal =
            new ThreadLocal<Transaction>();
    private static final ThreadLocal sessionByFilterThreadLocal =
            new ThreadLocal();

    public static void createFactory() {
        Configuration cfg = new Configuration();
        configuration(cfg);


        sessionFactory = cfg.buildSessionFactory();
        logger.info("SessionFactory created.");
    }

    /**
     * configure the hibernate
     * @param cfg instance of Configuration
     */
    private static void configuration(Configuration cfg) {
        // if there was not any specific hibernate configuration hence we use default
        final String configurationName = LoginModuleContextHolder.getHibernateConfigurationName();
        if(configurationName == null) {
            logger.info(CONFIGURATION_FILE + " is initializing the database!!!");
            cfg.configure(CONFIGURATION_FILE);
        } else {
            logger.info(configurationName + " is initializing the database!!!");
            cfg.configure(configurationName);
        }
    }

    private static SessionFactory getValidSessionFactory() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            createFactory();
        }
        return sessionFactory;
    }

    private static Session getValidSession() {
        Session session = sessionThreadLocal.get();
        if (session == null || !session.isConnected() || !session.isOpen()) {
            if (session != null) {
                try {
                    session.close();
                } catch (HibernateException e) {
                    //failed to close the session, may be already closed.
                }
            }
            /*
                Try to use multi database
                TODO: I should handle different multi database
             */
            session = getValidSessionFactory().openSession(new PadidpardazHibernateMultiDatabaseInterceptor());
            logger.debug("Session created");
            sessionThreadLocal.set(session);
        }
        return session;
    }

    public static Session getCurrentSession() {
        return getValidSession();
    }

    public static Transaction currentTransaction() throws HibernateException {
        Transaction transaction = transactionThreadLocal.get();
        if (transaction == null) {
            Session session = getCurrentSession();
            transaction = session.beginTransaction();
            transactionThreadLocal.set(transaction);
        }
        return transaction;
    }

    public static void commitTransaction() throws HibernateException {
        Transaction transaction = transactionThreadLocal.get();
        if (transaction != null) {
            transaction.commit();
        }
        transactionThreadLocal.set(null);
    }

    public static void destroy() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
                sessionFactory = null;
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rollbackTransaction() throws HibernateException {
        Transaction transaction = transactionThreadLocal.get();
        if (transaction != null) {
            transaction.rollback();
        }
        transactionThreadLocal.set(null);
    }

    public static void closeSession() {
        Session session = sessionThreadLocal.get();

        sessionThreadLocal.set(null);
        transactionThreadLocal.set(null);

        if (session != null) {
            try {
                session.close();
            } catch (HibernateException e) {
                logger.error(e, e);
            }
            logger.debug("Session closed");
        }
    }

    @SuppressWarnings({"unchecked"})
    public static void setSessionByFilter(boolean b) {
        sessionByFilterThreadLocal.set(b);
    }

    public static boolean isSessionByFilter() {
        Boolean b = (Boolean) sessionByFilterThreadLocal.get();
        return b != null && b;
    }

    public static void main(String[] args) {
        HibernateUtility.createFactory();
    }

    public static Class getHqlResultType(String hql, Session session) throws HibernateException {
        Type[] returnTypes = session.createQuery(hql).getReturnTypes();
        if ((returnTypes.length == 1) &&
                (returnTypes[0] instanceof EntityType)) {
            EntityType entityType = (EntityType) returnTypes[0];
            return entityType.getReturnedClass();
        }

        // it is unknown
        return Object.class;
    }


    @SuppressWarnings("ThrowableInstanceNeverThrown")
    public static HibernateEXC translateException(HibernateException e) {
        if (e == null) {
            return null;
        } else if (e instanceof CacheException) {
            return new HibernateEXC(null, HibernateEXC.CACHE_ERROR, e);
        } else if (e instanceof CallbackException) {
            return new HibernateEXC(null, HibernateEXC.HIBERNATE_CALLBACK_ERROR, e);
        } else if (e instanceof IdentifierGenerationException) {
            return new HibernateEXC(null, HibernateEXC.ID_GENERATOR_ERROR, e);
        } else if (e instanceof InstantiationException) {
            return new HibernateEXC(null, HibernateEXC.INSTANTIATION_ERROR, e);
        } else if (e instanceof JDBCException) {
            if (e instanceof ConstraintViolationException) {
                return new HibernateEXC(null, HibernateEXC.CONSTRAINT_VIOLATION_ERROR, e);
            } else if (e instanceof GenericJDBCException) {
                return new HibernateEXC(null, HibernateEXC.GENERIC_SQL_EXECEPTION, e);
            } else if (e instanceof JDBCConnectionException) {
                return new HibernateEXC(null, HibernateEXC.JDBC_CONNECTION_ERROR, e);
            } else if (e instanceof LockAcquisitionException) {
                return new HibernateEXC(null, HibernateEXC.LOCKING_ERROR, e);
            } else if (e instanceof SQLGrammarException) {
                return new HibernateEXC(null, HibernateEXC.SQL_GRAMMER_ERROR, e);
            } else {
                return new HibernateEXC(null, HibernateEXC.JDBC_ERROR, e);
            }
        } else if (e instanceof MappingException) {
            if (e instanceof PropertyNotFoundException) {
                return new HibernateEXC(null, HibernateEXC.PROPERTY_NOT_FOUND_ERROR, e);
            } else {
                return new HibernateEXC(null, HibernateEXC.MAPPING_ERROR, e);
            }
        } else if (e instanceof NonUniqueObjectException) {
            return new HibernateEXC(null, HibernateEXC.NON_UNIQUE_OBJECT, e);
        } else if (e instanceof NonUniqueResultException) {
            return new HibernateEXC(null, HibernateEXC.NON_UNIQUE_RESULT, e);
        } else if (e instanceof PersistentObjectException) {
            return new HibernateEXC(null, HibernateEXC.PERSISTENT_OBJECT, e);
        } else if (e instanceof PropertyAccessException) {
            return new HibernateEXC(null, HibernateEXC.PROPERTY_ACCESS_ERROR, e);
        } else if (e instanceof PropertyValueException) {
            return new HibernateEXC(null, HibernateEXC.PROPERTY_VALUE_ERROR, e);
        } else if (e instanceof QueryException) {
            return new HibernateEXC(null, HibernateEXC.QUERY_ERROR, e);
        } else if (e instanceof SerializationException) {
            return new HibernateEXC(null, HibernateEXC.SERIALIZING_ERROR, e);
        } else if (e instanceof StaleObjectStateException) {
            return new HibernateEXC(null, HibernateEXC.STALE_OBJECT_STATE, e);
        } else if (e instanceof TransactionException) {
            return new HibernateEXC(null, HibernateEXC.TRANSIENT_OBJECT, e);
        } else if (e instanceof TransientObjectException) {
            return new HibernateEXC(null, HibernateEXC.TRANSACTION_ERROR, e);
        } else if (e instanceof UnresolvableObjectException) {
            if (e instanceof ObjectDeletedException) {
                return new HibernateEXC(null, HibernateEXC.OBJECT_DELETED, e);
            } else if (e instanceof ObjectNotFoundException) {
                return new HibernateEXC(null, HibernateEXC.OBJECT_NOT_FOUND, e);
            } else {
                return new HibernateEXC(null, HibernateEXC.UNRESORVABLE_OBJECT, e);
            }
        } else if (e instanceof ValidationFailure) {
            return new HibernateEXC(null, HibernateEXC.VALIDATION_FAILURE, e);
        } else if (e instanceof WrongClassException) {
            return new HibernateEXC(null, HibernateEXC.WRONG_CLASS_ERROR, e);
        }

        return new HibernateEXC();
    }
}
