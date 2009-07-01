package com.pdpsoft.hibernate.crud;

import com.pdpsoft.hibernate.util.HibernateEXC;
import org.hibernate.criterion.Criterion;
import org.hibernate.type.Type;

import javax.sql.RowSet;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author hasha
 */
public interface HibernatePersistenceManager {
    static final String OBJECT_ID = "id";

    // first part : select methods. for read operations.

    /**
     * finds domain object by its primary key
     *
     * @param clazz result domain object's type
     * @param pk    primary key of domain object
     * @return a domain object
     * @throws HibernateEXC
     */

    Object findByPrimaryKey(Class clazz, Serializable pk) throws HibernateEXC;

    /**
     * finds domain object of the same type with primary key equals to properties of the argument object.
     *
     * @param object an domain object with primary key fields set to proper values. if a primary key value is null a
     * @return found domain object.
     * @throws HibernateEXC
     */
    Object findByPrimaryKey(Object object) throws HibernateEXC;

    /**
     * finds a list of domain objects with their pks.
     *
     * @param clazz result domain object's type
     * @param pks   list of primary keys
     * @return list of founded domain objects
     * @throws HibernateEXC
     */
    List findByPrimaryKeys(Class clazz, List pks) throws HibernateEXC;

    /**
     * find all domain objects with specific type
     *
     * @param clazz result domain object's type
     * @return List of founded domain objects
     * @throws HibernateEXC
     */
    List findAll(Class clazz) throws HibernateEXC;

    /**
     * finds all order by a property ascending or descending
     *
     * @param clazz     result domain object's type
     * @param orderBy   property for order
     * @param ascending sort ascending or descending
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findAllOrdrBy(Class clazz, String orderBy, boolean ascending) throws HibernateEXC;

    /**
     * finds limited list (count) from start index
     *
     * @param clazz result domain object's type
     * @param start start index for find
     * @param count number of entities that should be reterived, or zero for no limit
     * @return List of founded domain objects
     * @throws HibernateEXC
     */
    List findByIndex(Class clazz, int start, int count) throws HibernateEXC;

    /**
     * finds limited list (count) from start index order by a property ascending of descending
     *
     * @param clazz     result domain object's type
     * @param start     start index for find
     * @param count     number of entities that should be reterived, or zero for no limit
     * @param ascending sort ascending or descending
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByIndexOrderBy(Class clazz, int start, int count, String orderBy, boolean ascending) throws HibernateEXC;

    /**
     * find a unigue domain object with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @return KafaObject founded domain object
     * @throws HibernateEXC
     * @since 1.1.5
     */
    Object findUniqueByCondition(Class clazz, Criterion criterion) throws HibernateEXC;

    /**
     * fidns all domain objects with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByCondition(Class clazz, Criterion criterion) throws HibernateEXC;
    /**
     * finds all domain objects with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByCondition(Class clazz, Criterion criterion, final Map<String, Criterion> nestedQueries) throws HibernateEXC;

    /**
     * fidns all domain objects with specific type validating <code>criterion</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion a condition for check
     * @param orderBy   order by property
     * @param ascending sort ascending or descending
     * @return List of all founded domain objects
     * @throws HibernateEXC
     * @since 1.1.5
     */
    List findByConditionOrderBy(Class clazz, Criterion criterion, String orderBy, boolean ascending) throws HibernateEXC;

    /**
     * finds domain objects with specific type validation <code>criterion</code>, with paging support according to
     * <code>start</code> and <code>count</code>.
     *
     * @param clazz     result domain object's type
     * @param criterion filtering criterion
     * @param start     start index for results
     * @param count     max results fetched
     * @return list of founded domain objects;
     * @throws HibernateEXC
     */
    List findByCondition(Class clazz, Criterion criterion, int start, int count) throws HibernateEXC;

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
     * @throws HibernateEXC
     * @since 1.1.5
     */
    List findByConditionOrderBy(Class clazz, Criterion criterion, int start, int count, String orderBy, boolean ascending) throws HibernateEXC;


    /**
     * finds all instances of <code>clazz</code> with property names and values in <code>properties</code>.
     * this method just add all crieterion. for other concatnation, you must create a composite criterion and pass it to
     * findByCondition method.
     *
     * @param clazz        result domain object's type
     * @param criteriaList a list of criterion
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByConditions(Class clazz, List criteriaList) throws HibernateEXC;

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
     * @throws HibernateEXC
     * @since 1.1.5
     */
    List findByConditionsOrderBy(Class clazz, List criteriaList, String orderBy, boolean ascending) throws HibernateEXC;

    /**
     * {@link #findByConditions(Class,java.util.List)} with paging support.
     *
     * @param clazz
     * @param criteriaList
     * @param start
     * @param count
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByConditions(Class clazz, List criteriaList, int start, int count) throws HibernateEXC;

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
     * @throws HibernateEXC
     * @since 1.1.5
     */
    List findByConditionsOrderBy(Class clazz, List criteriaList, int start, int count, String orderBy, boolean ascending) throws HibernateEXC;

    /**
     * search domain objects with a template domain object.
     *
     * @param object object that have data for search match
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByExample(Object object) throws HibernateEXC;

    /**
     * @param hql        a hibernate query language
     * @param parameters array of objects for hql parameters
     * @return a domain object
     * @throws HibernateEXC
     */
    Object findUniqueByQuery(String hql, Object[] parameters) throws HibernateEXC;

    /**
     * @param hql        a hibernate query language
     * @param parameters array of objects for hql parameters
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByQuery(String hql, Object[] parameters) throws HibernateEXC;

    /**
     * @param hql        a hibernate query language
     * @param parameters array of objects for hql parameters
     * @param maxResults number of maximum return results
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByQuery(String hql, Object[] parameters, int maxResults) throws HibernateEXC;

    /**
     * @param hql        a hibernate query language
     * @param parameters map of hql parameters
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByQuery(String hql, Map parameters) throws HibernateEXC;

    /**
     * @param hql        a hibernate query language
     * @param parameters map of hql parameters
     * @param maxResults number of maximum return results
     * @return List of all founded domain objects
     * @throws HibernateEXC
     */
    List findByQuery(String hql, Map parameters, int maxResults) throws HibernateEXC;

    /**
     * return number of domain objects
     *
     * @param clazz domain object type to find
     * @return number of domain objects
     * @throws HibernateEXC
     */
    int count(Class clazz) throws HibernateEXC;
    int count(final Class clazz, final Criterion criterion) throws HibernateEXC;

    // second part : update methods. for insert and update operations.


    /**
     * inserts a domain object to database
     *
     * @param kafaObject domain object that must be inserted
     * @throws HibernateEXC
     */
    void persist(Object kafaObject) throws HibernateEXC;

    /**
     * inserts a domain object to database
     *
     * @param kafaObject domain object that must be inserted
     * @throws HibernateEXC
     */
    void save(Object kafaObject) throws HibernateEXC;

    /**
     * @param kafaObject
     * @return
     * @throws HibernateEXC
     * @deprecated don't use this method. User {@link #save(KafaObject)} instead.
     */
    Serializable saveAndReturnPrimaryKey(Object kafaObject) throws HibernateEXC;

    /**
     * update an existing domain object
     *
     * @param kafaObject object that must be updated
     * @throws HibernateEXC
     */
    void update(Object kafaObject) throws HibernateEXC;

    /**
     * insert or update a domain object
     *
     * @param kafaObject object that must be inserted or updated
     * @throws HibernateEXC
     */
    void saveOrUpdate(Object kafaObject) throws HibernateEXC;

    // third part : for remove operations

    /**
     * 
     * @param hql the HQL statement
     * @param paramMaps the aruments that it should be past to the query.
     * @return the number of affected reecords
     * @throws HibernateEXC
     */
    Integer updateOrDeleteStatement(String hql, Map<String, Object> paramMaps) throws HibernateEXC;

    /**
     *
     * @param hql the update or delete hql
     * @param objects it's the value
     * @param types It's org.hibernate.Hibernate argument
     * @throws HibernateEXC HibernateEXC
     */
    void updateOrDeleteStatement(final String hql, final Object[] objects, final Type[] types) throws HibernateEXC;

    /**
     * remove a domain object with its pk
     *
     * @param clazz domain object's type
     * @param pk    primary key of domain object
     * @throws HibernateEXC
     */
    void delete(Class clazz, Serializable pk) throws HibernateEXC;

    /**
     * remove a domain object
     *
     * @param kafaObject object that must be removed
     * @throws HibernateEXC
     */
    void delete(Object kafaObject) throws HibernateEXC;

    /**
     * Executes a JDBCCommand within current transaction, and translates SQLException to a proper HibernateEXC.
     *
     * @param command JDBCCommand which will be run
     * @return result of the command;
     * @throws HibernateEXC
     */
    Object execute(JDBCCommand command) throws HibernateEXC;

    /**
     * Executes a SQL query directly using and returns the result of the query as a CachedRowSet. Result must be
     * treated unmodifiable and disconnected.
     * Internally calls {@link #execute(ir.nsy.dlib.com.util.pm.JDBCCommand)} for translating exceptions.
     *
     * @param query      SQL query.
     * @param parameters parameters within query {@link java.sql.PreparedStatement}.
     * @return result of the query;
     * @throws HibernateEXC
     */
    RowSet executeJDBC(String query, Object[] parameters) throws HibernateEXC;

}
