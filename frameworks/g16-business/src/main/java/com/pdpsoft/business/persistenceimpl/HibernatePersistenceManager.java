package com.pdpsoft.business.persistenceimpl;

import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.hibernate.crud.HibernatePersistenceManagerImpl;
import com.pdpsoft.hibernate.crud.JDBCCommand;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.hibernate.criterion.Criterion;
import org.hibernate.type.Type;

import javax.sql.RowSet;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Ashna Company, G16
 * User: d_farid, h_shayan
 * Date: Sep 18, 2006
 * Time: 12:51:40 PM
 */
public class HibernatePersistenceManager implements PersistenceManager {

    com.pdpsoft.hibernate.crud.HibernatePersistenceManager persistenceManager = HibernatePersistenceManagerImpl.getInstance();

    public HibernatePersistenceManager() {
    }


    /**
     * finds domain object by its primary key
     *
     * @param clazz result domain object's type
     * @param pk    primary key of domain object
     * @return a domain object
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */

    public Object findByPrimaryKey(Class clazz, Serializable pk) throws HibernateEXC {
        return persistenceManager.findByPrimaryKey(clazz, pk);
    }

    /**
     * finds domain object of the same type with primary key equals to properties of the argument object.
     *
     * @param object an domain object with primary key fields set to proper values. if a primary key value is null a
     * @return found domain object.
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public Object findByPrimaryKey(Object object) throws HibernateEXC {
        return persistenceManager.findByPrimaryKey(object);
    }

    /**
     * finds a list of domain objects with their pks.
     *
     * @param clazz result domain object's type
     * @param pks   list of primary keys
     * @return list of founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByPrimaryKeys(Class clazz, List pks) throws HibernateEXC {
        return persistenceManager.findByPrimaryKeys(clazz, pks);
    }

    /**
     * find all domain objects with specific type
     *
     * @param clazz result domain object's type
     * @return List of founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findAll(Class clazz) throws HibernateEXC {
        return persistenceManager.findAll(clazz);
    }

    /**
     * finds all order by a property ascending or descending
     *
     * @param clazz     result domain object's type
     * @param orderBy   property for order
     * @param ascending sort ascending or descending
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findAllOrdrBy(Class clazz, String orderBy, boolean ascending) throws HibernateEXC {
        return persistenceManager.findAllOrdrBy(clazz, orderBy, ascending);
    }

    /**
     * finds limited list (count) from start index
     *
     * @param clazz result domain object's type
     * @param start start index for find
     * @param count number of entities that should be reterived, or zero for no limit
     * @return List of founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByIndex(Class clazz, int start, int count) throws HibernateEXC {
        return persistenceManager.findByIndex(clazz, start, count);
    }

    /**
     * finds limited list (count) from start index order by a property ascending of descending
     *
     * @param clazz     result domain object's type
     * @param start     start index for find
     * @param count     number of entities that should be reterived, or zero for no limit
     * @param ascending sort ascending or descending
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByIndexOrderBy(Class clazz, int start, int count, String orderBy, boolean ascending) throws HibernateEXC {
        return persistenceManager.findByIndexOrderBy(clazz, start, count, orderBy, ascending);
    }

    /**
     * find a unigue domain object with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @return KafaObject founded domain object
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     * @since 1.1.5
     */
    public Object findUniqueByCondition(Class clazz, Criterion criterion) throws HibernateEXC {
        return persistenceManager.findUniqueByCondition(clazz, criterion);
    }

    /**
     * fidns all domain objects with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByCondition(Class clazz, Criterion criterion) throws HibernateEXC {
        return persistenceManager.findByCondition(clazz, criterion);
    }

    /**
     * finds all domain objects with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    public List findByCondition(Class clazz, Criterion criterion, Map<String, Criterion> nestedQueries) throws HibernateEXC {
        return persistenceManager.findByCondition(clazz, criterion, nestedQueries);
    }

    /**
     * fidns all domain objects with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @param orderBy   order by property
     * @param ascending sort ascending or descending
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     * @since 1.1.5
     */
    public List findByConditionOrderBy(Class clazz, Criterion criterion, String orderBy, boolean ascending) throws HibernateEXC {
        return persistenceManager.findByConditionOrderBy(clazz, criterion, orderBy, ascending);
    }

    /**
     * finds domain objects with specific type validation <code>criterion</code>, with paging support according to
     * <code>start</code> and <code>count</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion filtering criterion
     * @param start     start index for results
     * @param count     max results fetched
     * @return list of founded domain objects;
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByCondition(Class clazz, Criterion criterion, int start, int count) throws HibernateEXC {
        return persistenceManager.findByCondition(clazz, criterion, start, count);
    }

    /**
     * finds domain objects with specific type validation <code>criterion</code>, with paging support according to
     * <code>start</code> and <code>count</code>. order by <code>orderBy</code> ascending or descending
     *
     * @param clazz     result domain object's type
     * @param criterion filtering criterion
     * @param start     start index for results
     * @param count     max results fetched
     * @param orderBy   order by property
     * @param ascending sort ascending or descending
     * @return list of founded domain objects;
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     * @since 1.1.5
     */
    public List findByConditionOrderBy(Class clazz, Criterion criterion, int start, int count, String orderBy, boolean ascending) throws HibernateEXC {
        return persistenceManager.findByConditionOrderBy(clazz, criterion, start, count, orderBy, ascending);
    }

    /**
     * finds all instances of <code>clazz</code> with property names and values in <code>properties</code>.
     * this method just add all crieterion. for other concatnation, you must create a composite criterion and pass it to
     * findByCondition method.
     *
     * @param clazz        result domain object's type
     * @param criteriaList a list of criterion
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByConditions(Class clazz, List criteriaList) throws HibernateEXC {
        return persistenceManager.findByConditions(clazz, criteriaList);
    }

    /**
     * finds all instances of <code>clazz</code> with property names and values in <code>properties</code>.
     * this method just add all crieterion. for other concatnation, you must create a composite criterion and pass it to
     * findByCondition method.
     *
     * @param clazz        result domain object's type
     * @param criteriaList a list of criterion
     * @param orderBy      order by property
     * @param ascending    sort ascending or descending
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     * @since 1.1.5
     */
    public List findByConditionsOrderBy(Class clazz, List criteriaList, String orderBy, boolean ascending) throws HibernateEXC {
        return persistenceManager.findByConditionsOrderBy(clazz, criteriaList, orderBy, ascending);
    }

    /**
     * {@link #findByConditions(Class,java.util.List)} with paging support.
     *
     * @param clazz
     * @param criteriaList
     * @param start
     * @param count
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByConditions(Class clazz, List criteriaList, int start, int count) throws HibernateEXC {
        return persistenceManager.findByConditions(clazz, criteriaList, start, count);
    }

    /**
     * {@link #findByConditions(Class,java.util.List)} with paging support.
     *
     * @param clazz
     * @param criteriaList
     * @param start
     * @param count
     * @param orderBy      order by property
     * @param ascending    sort ascending or descending
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     * @since 1.1.5
     */
    public List findByConditionsOrderBy(Class clazz, List criteriaList, int start, int count, String orderBy, boolean ascending) throws HibernateEXC {
        return persistenceManager.findByConditionsOrderBy(clazz, criteriaList, start, count, orderBy, ascending);
    }

    /**
     * search domain objects with a template domain object.
     *
     * @param object object that have data for search match
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByExample(Object object) throws HibernateEXC {
        return persistenceManager.findByExample(object);
    }

    /**
     * @param hql        a hibernate query language
     * @param parameters array of objects for hql parameters
     * @return a domain object
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public Object findUniqueByQuery(String hql, Object[] parameters) throws HibernateEXC {
        return persistenceManager.findUniqueByQuery(hql, parameters);
    }

    /**
     * @param hql        a hibernate query language
     * @param parameters array of objects for hql parameters
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByQuery(String hql, Object[] parameters) throws HibernateEXC {
        return persistenceManager.findByQuery(hql, parameters);
    }

    /**
     * @param hql        a hibernate query language
     * @param parameters array of objects for hql parameters
     * @param maxResults number of maximum return results
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByQuery(String hql, Object[] parameters, int maxResults) throws HibernateEXC {
        return persistenceManager.findByQuery(hql, parameters, maxResults);
    }

    /**
     * @param hql        a hibernate query language
     * @param parameters map of hql parameters
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByQuery(String hql, Map parameters) throws HibernateEXC {
        return persistenceManager.findByQuery(hql, parameters);
    }

    /**
     * @param hql        a hibernate query language
     * @param parameters map of hql parameters
     * @param maxResults number of maximum return results
     * @return List of all founded domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public List findByQuery(String hql, Map parameters, int maxResults) throws HibernateEXC {
        return persistenceManager.findByQuery(hql, parameters, maxResults);
    }

    /**
     * return number of domain objects
     *
     * @param clazz domain object type to find
     * @return number of domain objects
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public int count(Class clazz) throws HibernateEXC {
        return persistenceManager.count(clazz);
    }
    public int count(Class clazz, Criterion criterion) throws HibernateEXC {
        return persistenceManager.count(clazz, criterion);
    }

    /**
     * inserts a domain object to database
     *
     * @param clazz domain object that must be inserted
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public void persist(Object clazz) throws HibernateEXC {
        persistenceManager.persist(clazz);
    }

    /**
     * inserts a domain object to database
     *
     * @param clazz domain object that must be inserted
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public void save(Object clazz) throws HibernateEXC {
        persistenceManager.save(clazz);
    }

    /**
     * @param kafaObject
     * @return
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     * @deprecated don't use this method. User {@link } instead.
     */
    public Serializable saveAndReturnPrimaryKey(Object kafaObject) throws HibernateEXC {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * update an existing domain object
     *
     * @param clazz object that must be updated
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public void update(Object clazz) throws HibernateEXC {
        persistenceManager.update(clazz);
    }

    /**
     * insert or update a domain object
     *
     * @param clazz object that must be inserted or updated
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public void saveOrUpdate(Object clazz) throws HibernateEXC {
        persistenceManager.saveOrUpdate(clazz);
    }

    /**
     * @param hql       the HQL statement
     * @param paramMaps the aruments that it should be past to the query.
     * @return the number of affected reecords
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public Integer updateOrDeleteStatement(String hql, Map<String, Object> paramMaps) throws HibernateEXC {
        return persistenceManager.updateOrDeleteStatement(hql, paramMaps);
    }

    /**
     * @param hql     the update or delete hql
     * @param objects it's the value
     * @param types   It's org.hibernate.Hibernate argument
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *          HibernateEXC
     */
    public void updateOrDeleteStatement(String hql, Object[] objects, Type[] types) throws HibernateEXC {
        persistenceManager.updateOrDeleteStatement(hql, objects, types);
    }

    /**
     * remove a domain object with its pk
     *
     * @param clazz domain object's type
     * @param pk    primary key of domain object
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public void delete(Class clazz, Serializable pk) throws HibernateEXC {
        persistenceManager.delete(clazz, pk);
    }

    /**
     * remove a domain object
     *
     * @param clazz object that must be removed
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public void delete(Object clazz) throws HibernateEXC {
        persistenceManager.delete(clazz);
    }

    /**
     * Executes a JDBCCommand within current transaction, and translates SQLException to a proper HibernateEXC.
     *
     * @param command JDBCCommand which will be run
     * @return result of the command;
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public Object execute(JDBCCommand command) throws HibernateEXC {
        return persistenceManager.execute(command);
    }

    /**
     * Executes a SQL query directly using and returns the result of the query as a CachedRowSet. Result must be
     * treated unmodifiable and disconnected.
     * Internally calls {@link #execute(com.pdpsoft.hibernate.crud.JDBCCommand)} for translating exceptions.
     *
     * @param query      SQL query.
     * @param parameters parameters within query {@link java.sql.PreparedStatement}.
     * @return result of the query;
     * @throws com.pdpsoft.hibernate.util.HibernateEXC
     *
     */
    public RowSet executeJDBC(String query, Object[] parameters) throws HibernateEXC {
        return persistenceManager.executeJDBC(query, parameters);
    }
}
