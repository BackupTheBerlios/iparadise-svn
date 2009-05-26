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
public class NotRestrictions implements SearchCriterion{
    private SearchCriterion expression;

    public NotRestrictions(SearchCriterion expression) {
        this.expression = expression;
    }

    public Criterion getExpression() {
        return Restrictions.not(expression.getExpression());
    }
}