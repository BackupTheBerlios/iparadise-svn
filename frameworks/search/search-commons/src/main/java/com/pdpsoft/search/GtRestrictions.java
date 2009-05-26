package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:55:36 PM
 */
public class GtRestrictions implements SearchCriterion{
    private String propertyName;
    private Object value;

    public GtRestrictions(String propertyName, Object value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    public Criterion getExpression() {
        return Restrictions.gt(propertyName, value);
    }
}