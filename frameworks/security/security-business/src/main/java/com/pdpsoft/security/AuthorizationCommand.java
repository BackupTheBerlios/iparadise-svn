package com.pdpsoft.security;

import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;
import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Closure;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 4:44:50 PM
 */
public class AuthorizationCommand implements G16CommonBusinessCommand {
    PersistenceManager persistenceManager = PersistenceManagerFactory.getDefault();
    final static Log LOG = LogFactory.getLog(AuthorizationCommand.class);

    /**
     * @param o is an instance of Map<Integer, SystemUserEntity>, which by SystemUserEntity.id we are
     *          going to fetch all his related actions.
     * @return List of SystemActionEntity which are assigned to the specified SystemUserEntity
     * @throws G16CommonBusinessException
     * @throws HibernateEXC
     */
    public Object execute(Object o) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, SystemUserEntity> map = (Map<Integer, SystemUserEntity>) o;
        SystemUserEntity argument = map.get(0);

        LOG.info("An attemp for authorization occured by username=".concat(argument.getUserName())
                .concat("; and status is=").concat(argument.getStatus())
        );

        return divideData(
                runHql(argument)
        );

    }

    /**
     *
     * @param objects This is the value of executed hql command
     * @return list of SystemActionEntity
     */
    private List<SystemActionEntity> divideData(List<Object[]> objects) {
        final List<SystemActionEntity> actionsOfaSpecifiedUser = new ArrayList<SystemActionEntity>();
        CollectionUtils.forAllDo(objects, new Closure() {
            public void execute(Object o) {
                Object[] elements = (Object[]) o;
                SystemActionEntity entity = new SystemActionEntity(
                        // elements 0 means: UserActionEntity.id
                        Integer.valueOf(String.valueOf(elements[0])),
                        // elements 1 means: UserActionEntity.code
                        String.valueOf(elements[1]),
                        // elements 2 means: UserActionEntity.description
                        String.valueOf(elements[2]),
                        // the parent of UserActionEntity which is not essential
                        null
                );
              actionsOfaSpecifiedUser.add(entity);
            }
        });
        return actionsOfaSpecifiedUser;
    }

    /**
     *
     * @param argument An instance of SystemUserEntity
     * @throws HibernateEXC
     */
    private List<Object[]> runHql(SystemUserEntity argument) throws HibernateEXC {
        /*
           StringBuilder class has much more better performance from + pattern
        */
        final String hql = new StringBuilder().append(
                (new StringBuilder()
                        /*
                            In this append I made the join among UserActionEntity and SystemActionEntity, the query that
                            made this join possible as follow you can see:
                            <B>
                                UserActionEntity usract inner join usract.systemActionEntity
                            </B>
                         */
                        .append("select act.id, act.code, act.description from UserActionEntity usract inner join usract.systemActionEntity act ")
                        /*
                            In this wehere clause we fetch those actions which are associated
                            to the specific user.
                         */
                        .append("where usract.systemUserEntity=")).append(
                            argument.getSystemUserId()
                )).toString();


        return persistenceManager.findByQuery(hql, (Map) null);
    }
}