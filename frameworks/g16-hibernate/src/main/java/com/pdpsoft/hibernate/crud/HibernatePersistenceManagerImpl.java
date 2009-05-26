package com.pdpsoft.hibernate.crud;

import com.pdpsoft.hibernate.util.HibernateEXC;
import com.pdpsoft.hibernate.util.HibernateUtility;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.hibernate.criterion.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import javax.sql.RowSet;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/** @author hasha */
public class HibernatePersistenceManagerImpl implements HibernatePersistenceManager {
    // static instance for singleton pattern
    private static HibernatePersistenceManager instance;
    private static final Log logger = LogFactory.getLog("HibernatePersistenceManagerImpl");

    /** private constructor (singleton pattern) */
    private HibernatePersistenceManagerImpl() {
    }

    static class HibernatePersistenceManagerHolder {
        static HibernatePersistenceManager hibernatePersistenceManager;

        static {
            HibernateUtility.createFactory();
            hibernatePersistenceManager = new HibernatePersistenceManagerImpl();
        }
    }

    /**
     * static method for getting an instance of PersistanceManager (singleton pattern)
     *
     * @return static instance of PersistanceManager
     */
    public static HibernatePersistenceManager getInstance() {
        if (instance == null) {
            instance = HibernatePersistenceManagerHolder.hibernatePersistenceManager;
        }
        return instance;
    }

    /**
     * execute an HibernateCommand. (Command design pattern.)
     * Operations performed:
     * <ul>
     * <li>current <code>Session</code> and <code>Transaction</code> which are assigned to current thread are retrieved.</li>
     * <li>tries to execute command with custom. If no proper one is found the fallback
     * one (@link DefaultCommandExecutor} is used.</li>
     * <li>commits current <code>Transaction</code> if no exception occures or rolls it back if an exception occures.</li>
     * <li>translates the exception to a proper one if one is thrown.</li>
     * <li>closes current <code>Session</code> if it is not marked to close by <code>HibernatePersistenceManagerFilter</code>.</li>
     * <ul>
     *
     * @param command that must be executed
     *
     * @return result of execute command
     *
     * @throws HibernateEXC
     */
    private Object executeCommand(HibernateCommand command) throws HibernateEXC {
        try {
            Session session = HibernateUtility.getCurrentSession();
            HibernateUtility.currentTransaction();

            Object result = null;
            result = command.execute(session);

            HibernateUtility.commitTransaction();

            return result;
        } catch (HibernateException e) {
            try {
                HibernateUtility.rollbackTransaction();
            } catch (HibernateException he) {
                throw HibernateUtility.translateException(e);
            }
            logger.error(e, e);
            throw HibernateUtility.translateException(e);
        } finally {
            if (!HibernateUtility.isSessionByFilter()) {
                HibernateUtility.closeSession();
            }
        }
    }

    /* PersistencManager Implementation */

    public Object findByPrimaryKey(final Class clazz, final Serializable pk) throws HibernateEXC {
        return (Object) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                return session.createCriteria(clazz).add(Expression.eq(OBJECT_ID, pk)).uniqueResult();
            }
        });
    }

    public Object findByPrimaryKey(final Object object) throws HibernateEXC {
        return (Object) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Class clazz = object.getClass();
                return session.createCriteria(clazz).add(Expression.eq(OBJECT_ID, object)).uniqueResult();
            }
        });
    }

    public List findByPrimaryKeys(final Class clazz, final List pks) throws HibernateEXC {
        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                return session.createCriteria(clazz)
                        .add(Expression.in(OBJECT_ID, pks))
                        .list();
            }

            public Class getResultClass(Session session) {
                return clazz;
            }
        });
    }

    public List findAll(final Class clazz) throws HibernateEXC {
        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                return session.createCriteria(clazz)
                        .list();
            }

            public Class getResultClass(Session session) {
                return clazz;
            }
        });
    }

    public List findAllOrdrBy(final Class clazz, final String orderBy, final boolean ascending)
            throws HibernateEXC {

        return findByIndexOrderBy(clazz, 0, 0, orderBy, ascending);
    }

    public List findByIndex(final Class clazz, final int start, int count) throws HibernateEXC {
        return findByIndexOrderBy(clazz, start, count, null, true);
    }

    public List findByIndexOrderBy(final Class clazz, final int start, final int count,
                                   final String orderBy, final boolean ascending) throws HibernateEXC {

        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(clazz)
                        .setFirstResult(start);

                if (count != 0) {
                    criteria.setMaxResults(count);
                }

                if (orderBy != null) {
                    criteria.addOrder(ascending ? Order.asc(orderBy) : Order.desc(orderBy));
                }

                return criteria.list();
            }

            public Class getResultClass(Session session) {
                return clazz;
            }
        });
    }

    public Object findUniqueByCondition(final Class clazz, final Criterion criterion)
            throws HibernateEXC {

        return (Object) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(clazz);
                if (criterion != null) {
                    criteria.add(criterion);
                }
                return criteria.uniqueResult();
            }
        });
    }

    public List findByCondition(final Class clazz, final Criterion criterion) throws HibernateEXC {
        return findByConditionOrderBy(clazz, criterion, 0, 0, null, false);
    }

    public List findByConditionOrderBy(Class clazz, Criterion criterion, String orderBy, boolean ascending)
            throws HibernateEXC {

        return findByConditionOrderBy(clazz, criterion, 0, 0, orderBy, ascending);
    }

    public List findByCondition(final Class clazz, final Criterion criterion, final int start,
                                final int count) throws HibernateEXC {

        return findByConditionOrderBy(clazz, criterion, start, count, null, false);
    }

    public List findByConditionOrderBy(final Class clazz, final Criterion criterion, final int start,
                                       final int count, final String orderBy, final boolean ascending)
            throws HibernateEXC {

        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(clazz).
                        setFirstResult(start);

                if (criterion != null) {
                    criteria.add(criterion);
                }

                if (count > 0) {
                    criteria.setMaxResults(count);
                }

                if (orderBy != null) {
                    Order order = ascending ? Order.asc(orderBy) : Order.desc(orderBy);
                    criteria.addOrder(order);
                }

                return criteria.list();
            }

            public Class getResultClass(Session session) {
                return clazz;
            }
        });
    }

    public List findByConditions(final Class clazz, final List criteriaList) throws HibernateEXC {
        return findByConditions(clazz, criteriaList, 0, 0);
    }

    public List findByConditionsOrderBy(Class clazz, List criteriaList, String orderBy, boolean ascending)
            throws HibernateEXC {

        return findByConditionsOrderBy(clazz, criteriaList, 0, 0, orderBy, ascending);
    }

    public List findByConditions(final Class clazz, final List criteriaList, final int start, final int count)
            throws HibernateEXC {

        return findByConditionsOrderBy(clazz, criteriaList, start, count, null, false);
    }

    public List findByConditionsOrderBy(final Class clazz, final List criteriaList, final int start,
                                        final int count, final String orderBy, final boolean ascending)
            throws HibernateEXC {

        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {

                Criteria criteria = session.createCriteria(clazz).
                        setFirstResult(start);
                for (Iterator itr = criteriaList.iterator(); itr.hasNext();) {
                    Criterion criterion = (Criterion) itr.next();
                    if (criterion != null) {
                        criteria.add(criterion);
                    }
                }

                if (count > 0) {
                    criteria.setMaxResults(count);
                }

                if (orderBy != null) {
                    Order order = ascending ? Order.asc(orderBy) : Order.desc(orderBy);
                    criteria.addOrder(order);
                }

                return criteria.list();
            }

            public Class getResultClass(Session session) {
                return clazz;
            }
        });
    }

    public List findByExample(final Object object) throws HibernateEXC {
        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                return session.createCriteria(object.getClass()).
                        add(Example.create(object).
                                enableLike(MatchMode.ANYWHERE).
                                ignoreCase().
                                excludeZeroes()).
                        list();
            }

            public Class getResultClass(Session session) {
                return object.getClass();
            }
        });
    }

    public Object findUniqueByQuery(final String hql, final Object[] parameters) throws HibernateEXC {
        return (Object) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (parameters != null) {
                    for (int i = 0; i < parameters.length; i++) {
                        Object parameter = parameters[(i)];
                        query.setParameter(i, parameter);
                    }
                }
                return query.uniqueResult();
            }
        });
    }

    public List findByQuery(final String hql, final Object[] parameters) throws HibernateEXC {
        return findByQuery(hql, parameters, 0);
    }

    public List findByQuery(final String hql, final Object[] parameters, final int maxResults)
            throws HibernateEXC {

        return findByQuery(hql, parameters, maxResults);
    }

    public List findByQuery(String hql, Map parameters) throws HibernateEXC {
        return findByQuery(hql, parameters, 0);
    }

    public List findByQuery(final String hql, final Map parameters, final int maxResults)
            throws HibernateEXC {

        return (List) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if (parameters != null && parameters.size() != 0) {
                    Set entrySet = parameters.entrySet();
                    for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        query.setParameter((String) entry.getKey(), entry.getValue());
                    }
                }

                if (maxResults > 0) {
                    query.setMaxResults(maxResults);
                }

                return query.list();
            }

            public Class getResultClass(Session session) throws HibernateException {
                return HibernateUtility.getHqlResultType(hql, session);
            }
        });
    }

    public int count(final Class clazz) throws HibernateEXC {
        Integer result = (Integer) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                return session.createQuery("select count(object) from " + clazz.getName() + " as object").
                        uniqueResult();
            }
        });

        return result;
    }
    public int count(final Class clazz, final Criterion criterion) throws HibernateEXC {
        Integer result = (Integer) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Criteria criteria = session.createCriteria(clazz);
                assert criterion != null;
                criteria.add(criterion);
                return criteria.list().size();
            }
        });

        return result;
    }

    public void persist(final Object kafaObject) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                session.persist(kafaObject);
                session.flush();
                return null;
            }
        });
    }

    public void save(final Object kafaObject) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                session.save(kafaObject);
                session.flush();
                //session.refresh(kafaObject);
                return null;
            }
        });
    }

    public Serializable saveAndReturnPrimaryKey(final Object kafaObject)
            throws HibernateEXC {

        return (Serializable) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Object key = session.save(kafaObject);
                session.flush();
                session.refresh(kafaObject);
                return key;
            }
        });
    }

    public void update(final Object kafaObject) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                session.update(kafaObject);
                session.flush();
                session.refresh(kafaObject);
                return null;
            }
        });
    }

    public void saveOrUpdate(final Object kafaObject) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                session.saveOrUpdate(kafaObject);
                session.flush();
                return null;
            }
        });
    }

    public Integer updateOrDeleteStatement(final String hql, final Map<String, Object> paramMaps) throws HibernateEXC {
        return (Integer) executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Query q = session.createQuery(hql);
                if (paramMaps != null)
                  for (Map.Entry<String, Object> param : paramMaps.entrySet()) {
                    if (param.getValue() instanceof Collection)
                      q.setParameterList(param.getKey(), ((Collection) param.getValue()));
                    else {
                      q.setParameter(param.getKey(), param.getValue());
                    }
                  }
                int affectedValueCount = q.executeUpdate();
                session.flush();
                return affectedValueCount;
            }
        });
    }

    public void updateOrDeleteStatement(final String hql, final Object[] objects, final Type[] types) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Query query = session.createQuery(hql);
                if(objects != null && types != null ) {
					query.setParameters(objects, types);
				}
                int count = query.executeUpdate();
                session.flush();
                return count;
            }
        });
    }

    public void delete(final Class clazz, final Serializable pk) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                session.delete("from " + clazz.getName() + " as object where object.id = " + pk);
                session.flush();
                return null;
            }
        });
    }

    public void delete(final Object kafaObject) throws HibernateEXC {
        executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                session.delete(kafaObject);
                session.flush();
                return null;
            }
        });
    }

    public Object execute(final JDBCCommand command) throws HibernateEXC {
        return executeCommand(new HibernateCommand() {
            public Object execute(Session session) throws HibernateException {
                Connection connection;
                try {
                    connection = session.connection();
                    return command.execute(connection);
                } catch (SQLException e) {
                    //throw session.getSessionFactory().getSQLExceptionConverter().convert(e, null);
                }
                return null;
            }
        });
    }

    public RowSet executeJDBC(final String query, final Object[] parameters) throws HibernateEXC {
        return (RowSet) execute(new JDBCCommand() {
            public Object execute(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;

                try {
                    preparedStatement = connection.prepareStatement(query);

                    if (parameters != null) {
                        for (int i = 0; i < parameters.length; i++) {
                            Object parameter = parameters[i];
                            preparedStatement.setObject(i + 1, parameter);
                        }
                    }

                    logger.info("query: " + query);

                    resultSet = preparedStatement.executeQuery();

//                    OracleCachedRowSet oracleCachedRowSet = new OracleCachedRowSet();
//                    oracleCachedRowSet.populate(resultSet);
//
//                    return oracleCachedRowSet;
                    return null;
                } finally {
                    if (resultSet != null) {
                        resultSet.close();
                    }

                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                }
            }
        });
    }

    protected void finalize() throws Throwable {
        super.finalize();
        HibernateUtility.destroy();
    }
}
