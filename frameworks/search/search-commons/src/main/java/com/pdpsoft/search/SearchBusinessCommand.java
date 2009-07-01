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
import java.util.Set;
import java.util.HashMap;

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
    @SuppressWarnings({"unchecked"})
    public Object execute(Object o) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, Object> param = (Map<Integer, Object>) o;
        SearchParameter searchParameter = (SearchParameter) param.get(0);

        List result;
        if(searchParameter.nestedSize() == 0) {
            result = normalSearch(searchParameter);
        } else {
            result = nestedQuery(searchParameter);
        }
        if(result != null)
            LOG.info("Size of the object is ".concat(String.valueOf(result.size())));
        else
            LOG.info("There wasn't any record with the specified query.");
        return result;
    }

    /**
     * nested query will be occure
     * @param searchParameter
     * @return
     * @throws HibernateEXC
     */
    private List nestedQuery(SearchParameter searchParameter) throws HibernateEXC {
        final Map<String, Criterion> map = new HashMap<String, Criterion>();
        final Set<Map.Entry<String,SearchCriterion>> entries = searchParameter.getNestedQuery().entrySet();
        for (Map.Entry<String, SearchCriterion> entry : entries) {
            map.put(entry.getKey(), entry.getValue().getExpression());
        }
        return persistenceManager.findByCondition(searchParameter.getValueObjectClass(), searchParameter.getSearchCriteria().getExpression(), map);
    }

    /**
     * normal search without nested query
     * @param searchParameter
     * @return
     * @throws HibernateEXC
     */
    private List normalSearch(SearchParameter searchParameter) throws HibernateEXC {
        Criterion expression = searchParameter.getSearchCriteria().getExpression();
        return persistenceManager.findByCondition(searchParameter.getValueObjectClass(), expression);
    }
}
