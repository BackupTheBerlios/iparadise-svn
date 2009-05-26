package com.pdpsoft.search;

import com.pdpsoft.commons.G16ParentCustomEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 10:02:51 AM
 */
public class SearchParameter extends G16ParentCustomEntity {
    /*
        This is the class that the query should be run on this object,
        it is important to note that in this version of the component
        there is NOT any join, hence all searches will occure only in
        a single object.
     */
    private Object valueObject;
    /*
        It determines which sort of operators should occure on this object.
     */
    private SearchCriterion searchCriteria;

    public SearchParameter(Object valueObject, SearchCriterion searchCriteria) {
        this.valueObject = valueObject;
        this.searchCriteria = searchCriteria;
    }

    /*
        We need it to pass the Class to the Restrictions, to
        create the specified query.
     */
    public Class getValueObjectClass() {
        return getValueObject().getClass();
    }

    public Object getValueObject() {
        return valueObject;
    }

    public void setValueObject(Object valueObject) {
        this.valueObject = valueObject;
    }

    public SearchCriterion getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriterion searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}
