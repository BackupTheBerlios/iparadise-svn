package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:55:36 PM
 */
public class IsNullRestrictions implements SearchCriterion{
    private String propertyName;

    public IsNullRestrictions(String propertyName) {
        this.propertyName = propertyName;
    }

    public Criterion getExpression() {
        return Restrictions.isNull(propertyName);
    }
}