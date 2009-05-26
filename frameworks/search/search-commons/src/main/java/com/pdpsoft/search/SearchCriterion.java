package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 10:28:40 AM
 * Inspired from Gavin King implementation of Expression in hibernate.
 *
 * 
 * An object-oriented representation of a query criterion that may be used
 * as a restriction in a <tt>Criteria</tt> query.
 * Built-in criterion types are provided by the <tt>Restrictions</tt> factory
 * class. This interface might be implemented by application classes that
 * define custom restriction criteria.
 *
 * @author Gavin King
 * @author ChrisShayan.com
 */

public interface SearchCriterion {
    Criterion getExpression();
}
