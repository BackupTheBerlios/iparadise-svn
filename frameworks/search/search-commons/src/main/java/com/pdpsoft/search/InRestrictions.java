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
public class InRestrictions implements SearchCriterion{
    private String propertyName;
    private Collection values;

    public InRestrictions(String propertyName, Collection values) {
        this.propertyName = propertyName;
        this.values = values;
    }

    public Criterion getExpression() {
        return Restrictions.in(propertyName, values);
    }
}