package com.pdpsoft.search;

import org.hibernate.criterion.*;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:01:26 PM
 * Inspired from Gavin King implementation of Expression in hibernate.
 *
 * A sequence of a logical expressions combined by some
 * associative logical operator
 *
 * @author Gavin King
 */
public class SearchJunction implements SearchCriterion {
    private final List<SearchCriterion> criteria = new ArrayList<SearchCriterion>();
    private final String operator;

    protected SearchJunction(String operator) {
        this.operator = operator;
    }

    public SearchJunction add(SearchCriterion criterion) {
        criteria.add(criterion);
        return this;
    }

    public Criterion getExpression() {
        Junction junction = null;
        if(operator.equalsIgnoreCase("and"))
            junction = Restrictions.conjunction();
        if(operator.equalsIgnoreCase("or"))
            junction = Restrictions.disjunction();
        assert junction != null;
        for (SearchCriterion searchCriterion : criteria) {
            junction.add(searchCriterion.getExpression());
        }
        return junction;
    }
}
