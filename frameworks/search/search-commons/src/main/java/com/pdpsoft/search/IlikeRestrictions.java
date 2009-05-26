package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:55:36 PM
 */
public class IlikeRestrictions implements SearchCriterion{
    private String propertyName;
    private Object value;

    public IlikeRestrictions(String propertyName, Object value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    public Criterion getExpression() {
        return Restrictions.ilike(propertyName, value);
    }
}