package com.pdpsoft.search;

import java.util.Collection;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 11:21:28 AM
 * Inspired from Gavin King implementation of Expression in hibernate.
 * <p/>
 * The <tt>criterion</tt> package may be used by applications as a framework for building
 * new kinds of <tt>Criterion</tt>. However, it is intended that most applications will
 * simply use the built-in criterion types via the static factory methods of this class.
 *
 * @author Gavin King
 * @author ChrisShayan.com
 * @see org.hibernate.Criteria
 */
public class SearchRestrictions {
    SearchRestrictions() {
        //cannot be instantiated
    }

    /**
     * Apply an "equal" constraint to the identifier property
     *
     * @param value
     * @return Criterion
     */
    public static SearchCriterion idEq(Object value) {
        return new IdentifierEqRestrictions(value);
    }

    /**
     * Apply an "equal" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion eq(String propertyName, Object value) {
        return new EqualRestrictions(propertyName, value);
    }

    /**
     * Apply a "not equal" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion ne(String propertyName, Object value) {
        return new NotEqualRestrictions(propertyName, value);
    }

    /**
     * Apply a "like" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion like(String propertyName, Object value) {
        return new LikeRestrictions(propertyName, value);
    }

    public enum SearchMatchMode {
        EXACT, START, END, ANYWHERE
    }

    /**
     * Apply a "like" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion like(String propertyName, String value, SearchMatchMode matchMode) {
        return new LikeMatchModeRestrictions(propertyName, value, matchMode);
    }

    /**
     * A case-insensitive "like", similar to Postgres <tt>ilike</tt>
     * operator
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion ilike(String propertyName, String value, SearchMatchMode matchMode) {
        return new IlikeMatchModeRestrictions(propertyName, value, matchMode);
    }

    /**
     * A case-insensitive "like", similar to Postgres <tt>ilike</tt>
     * operator
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion ilike(String propertyName, Object value) {
        return new IlikeRestrictions(propertyName, value);
    }

    /**
     * Apply a "greater than" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion gt(String propertyName, Object value) {
        return new GtRestrictions(propertyName, value);
    }

    /**
     * Apply a "less than" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion lt(String propertyName, Object value) {
        return new LtRestrictions(propertyName, value);
    }

    /**
     * Apply a "less than or equal" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion le(String propertyName, Object value) {
        return new LeRestrictions(propertyName, value);
    }

    /**
     * Apply a "greater than or equal" constraint to the named property
     *
     * @param propertyName
     * @param value
     * @return Criterion
     */
    public static SearchCriterion ge(String propertyName, Object value) {
        return new GeRestrictions(propertyName, value);
    }

    /**
     * Apply a "between" constraint to the named property
     *
     * @param propertyName
     * @param low value
     * @param high value
     * @return Criterion
     */
    public static SearchCriterion between(String propertyName, Object low, Object high) {
        return new BetweenRestrictions(propertyName, low, high);
    }

    /**
     * Apply an "in" constraint to the named property
     *
     * @param propertyName
     * @param values
     * @return Criterion
     */
    public static SearchCriterion in(String propertyName, Collection values) {
        return new InRestrictions(propertyName, values);
    }

    /**
     * Apply an "is null" constraint to the named property
     *
     * @return Criterion
     */
    public static SearchCriterion isNull(String propertyName) {
        return new IsNullRestrictions(propertyName);
    }

    /**
     * Apply an "is not null" constraint to the named property
     *
     * @return Criterion
     */
    public static SearchCriterion isNotNull(String propertyName) {
        return new IsNotNullRestrictions(propertyName);
    }

    /**
     * Return the conjuction of two expressions
     *
     * @param lhs
     * @param rhs
     * @return Criterion
     */
    public static SearchCriterion and(SearchCriterion lhs, SearchCriterion rhs) {
        SearchConjunction conjunction = conjunction();
        conjunction.add(lhs);
        conjunction.add(rhs);
        return conjunction;
    }

    /**
     * Return the disjuction of two expressions
     *
     * @param lhs
     * @param rhs
     * @return Criterion
     */
    public static SearchCriterion or(SearchCriterion lhs, SearchCriterion rhs) {
        SearchDisjunction disjunction = disjunction();
        disjunction.add(lhs);
        disjunction.add(rhs);
        return disjunction;
    }

    /**
     * Return the negation of an expression
     *
     * @param expression
     * @return Criterion
     */
    public static SearchCriterion not(SearchCriterion expression) {
        return new NotRestrictions(expression);
    }

    /**
     * Group expressions together in a single conjunction (A and B and C...)
     *
     * @return Conjunction
     */
    public static SearchConjunction conjunction() {
        return new SearchConjunction();
    }

    /**
     * Group expressions together in a single disjunction (A or B or C...)
     *
     * @return Conjunction
     */
    public static SearchDisjunction disjunction() {
        return new SearchDisjunction();
    }

    /**
     * Apply an "equals" constraint to each property in the
     * key set of a <tt>Map</tt>
     *
     * @param propertyNameValues a map from property names to values
     * @return Criterion
     */
    public static SearchCriterion allEq(Map propertyNameValues) {
        SearchConjunction conjunction = new SearchConjunction();
        for (Object o : propertyNameValues.entrySet()) {
            Map.Entry me = (Map.Entry) o;
            conjunction.add(eq((String) me.getKey(), me.getValue()));
        }
        return conjunction;
    }
}
