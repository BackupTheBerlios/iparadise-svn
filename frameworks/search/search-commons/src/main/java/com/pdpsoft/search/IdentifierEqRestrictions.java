package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:55:36 PM
 */
public class IdentifierEqRestrictions implements SearchCriterion{
    private Object value;

    public IdentifierEqRestrictions(Object value) {
        this.value = value;
    }

    public Criterion getExpression() {
        return Restrictions.idEq(value);
    }
}
