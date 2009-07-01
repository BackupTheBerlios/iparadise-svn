package com.pdpsoft.search;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.Map;
import java.util.HashMap;

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
    /*
        This property tries to handle the nested queries,
        in this map the key is the property that join will be occured;
        and the value is sort of operators should occure on this object. 
     */
    private Map<String, SearchCriterion> nestedQuery = new HashMap<String, SearchCriterion>();

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

    public Map<String, SearchCriterion> getNestedQuery() {
        return nestedQuery;
    }

    /**
     * this method adds nested query into the condition
     * @param propertyName the property that join will be occured;
     * @param criterion and the value is sort of operators should occure on this object.
     */
    public void addNestedQuery(final String propertyName, final SearchCriterion criterion) {
        if(nestedQuery == null)
            nestedQuery = new HashMap<String, SearchCriterion>();
        if(nestedQuery.containsKey(propertyName))
            throw new RuntimeException("there is a duplication on " + propertyName + " in the nested query!!!");
        nestedQuery.put(propertyName, criterion);
    }

    /**
     * returns the size of map
     * @return size of nested queries
     */
    public int nestedSize() {
        if(nestedQuery == null)
            return 0;
        return nestedQuery.size();
    }
}
