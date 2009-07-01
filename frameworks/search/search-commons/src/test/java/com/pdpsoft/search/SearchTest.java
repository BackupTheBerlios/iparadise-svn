package com.pdpsoft.search;

import junit.framework.TestCase;
import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 4:17:03 PM
 */
public class SearchTest extends TestCase {

    PersistenceManager persistenceManager;
    final static Log LOG = LogFactory.getLog(SearchTest.class);

    public SearchTest(String s) {
        super(s);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        persistenceManager = null;
    }

    public void testSearch() throws Exception {
        LOG.info(SearchRestrictions.eq("name", "Hamed").getExpression());        
        LOG.info(SearchRestrictions.ne("name", "Hamed").getExpression());
        LOG.info(SearchRestrictions.like("name", "Hamed").getExpression());        
        LOG.info(SearchRestrictions.like("name", "Hamed", SearchRestrictions.SearchMatchMode.ANYWHERE).getExpression());        
        LOG.info(SearchRestrictions.ilike("name", "Hamed").getExpression());
        LOG.info(SearchRestrictions.ilike("name", "Hamed", SearchRestrictions.SearchMatchMode.ANYWHERE).getExpression());
        LOG.info(SearchRestrictions.gt("age", 35).getExpression());
        LOG.info(SearchRestrictions.lt("age", 35).getExpression());
        LOG.info(SearchRestrictions.le("age", 35).getExpression());
        LOG.info(SearchRestrictions.ge("age", 35).getExpression());
        LOG.info(SearchRestrictions.between("age", 35, 40).getExpression());
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(35);arrayList.add(40);arrayList.add(438);
        LOG.info(SearchRestrictions.in("age", arrayList).getExpression());
        LOG.info(SearchRestrictions.isNull("city").getExpression());
        LOG.info(SearchRestrictions.isNotNull("city").getExpression());

        SearchCriterion notNull = SearchRestrictions.isNotNull("city");
        SearchCriterion isNull = SearchRestrictions.isNull("city");
        SearchCriterion searchCriterion = SearchRestrictions.and(notNull, isNull);
        LOG.info(searchCriterion.getExpression());

        SearchCriterion between = SearchRestrictions.between("age", 35, 40);
        LOG.info(SearchRestrictions.or(
                SearchRestrictions.not(searchCriterion),
                between
        ).getExpression());
    }
}