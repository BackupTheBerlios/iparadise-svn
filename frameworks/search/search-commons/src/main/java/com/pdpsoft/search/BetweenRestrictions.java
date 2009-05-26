package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:55:36 PM
 */
public class BetweenRestrictions implements SearchCriterion{
    private String propertyName;
    private Object low;
    private Object high;

    public BetweenRestrictions(String propertyName, Object low, Object high) {
        this.propertyName = propertyName;
        this.low = low;
        this.high = high;
    }

    public Criterion getExpression() {
        return Restrictions.between(propertyName, low, high);
    }
}