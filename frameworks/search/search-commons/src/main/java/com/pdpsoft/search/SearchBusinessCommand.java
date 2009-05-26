package com.pdpsoft.search;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 9:35:56 AM
 */
public class SearchBusinessCommand implements G16CommonBusinessCommand {
    PersistenceManager persistenceManager = PersistenceManagerFactory.getDefault();
    private final static Log LOG = LogFactory.getLog(SearchBusinessCommand.class);
    /**
     * 
     * @param o is an instance of Map<Integer, Object> where the zero one is
     *              SearchParameter.
     * @return the list of search
     * @throws G16CommonBusinessException
     * @throws HibernateEXC
     */
    public Object execute(Object o) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> param = (Map<Integer, Object>) o;
        SearchParameter searchParameter = (SearchParameter) param.get(0);
        LOG.info("Search is going to occur on object".concat(searchParameter.getValueObjectClass().getName()));
        Criterion expression = searchParameter.getSearchCriteria().getExpression();
        LOG.info(" and the query is".concat(expression.toString()));
        List list = persistenceManager.findByCondition(searchParameter.getValueObjectClass(), expression);
        if(list != null)
            LOG.info("Size of the object is ".concat(String.valueOf(list.size())));
        else
            LOG.info("There wasn't any record with the specified query.");
        return list;
    }
}
