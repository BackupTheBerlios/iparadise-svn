package com.pdpsoft.search;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.MatchMode;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 13, 2009
 * Time: 12:55:36 PM
 */
public class LikeMatchModeRestrictions implements SearchCriterion{
    private String propertyName;
    private String value;
    private SearchRestrictions.SearchMatchMode matchMode;

    public LikeMatchModeRestrictions(String propertyName, String value, SearchRestrictions.SearchMatchMode matchMode) {
        this.propertyName = propertyName;
        this.value = value;
        this.matchMode = matchMode;
    }

    public Criterion getExpression() {
        assert matchMode != null;
        if(matchMode.equals(SearchRestrictions.SearchMatchMode.START))
            return Restrictions.like(propertyName, value, MatchMode.START);
        if(matchMode.equals(SearchRestrictions.SearchMatchMode.ANYWHERE))
            return Restrictions.like(propertyName, value, MatchMode.ANYWHERE);
        if(matchMode.equals(SearchRestrictions.SearchMatchMode.END))
            return Restrictions.like(propertyName, value, MatchMode.END);
        if(matchMode.equals(SearchRestrictions.SearchMatchMode.EXACT))
            return Restrictions.like(propertyName, value, MatchMode.EXACT);
        throw new RuntimeException("There's not any MatchMode in the specified expression");
    }
}