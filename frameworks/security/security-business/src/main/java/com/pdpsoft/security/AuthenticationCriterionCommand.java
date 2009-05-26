package com.pdpsoft.security;

import com.pdpsoft.commonbiz.util.G16CommonBusinessCommand;
import com.pdpsoft.commonbiz.util.G16CommonBusinessException;
import com.pdpsoft.hibernate.util.HibernateEXC;
import org.hibernate.criterion.Expression;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 4:44:50 PM
 */
public class AuthenticationCriterionCommand implements G16CommonBusinessCommand {
    final static Log LOG = LogFactory.getLog(AuthenticationCriterionCommand.class);

    public Object execute(Object o) throws G16CommonBusinessException, HibernateEXC {
        Map<Integer, SystemUserEntity> map = (Map<Integer, SystemUserEntity>) o;
        SystemUserEntity argument = map.get(0);
        /**
         * the username should be ACTIVE
         */
        argument.setStatus(SystemUserEntity.StatusTypeEnumeration.ACTIVE);

        LOG.info("An attemp for login occured by username=".concat(argument.getUserName())
                .concat("; and status is=").concat(argument.getStatus())
            );

        /**
         * Checks the
         * 1) username should be the same as user has been entered
         * 2) password should be the same as user has been entered
         * 3) status should be active.
         */
        return
                Expression.and(
                        Expression.and(
                                Expression.eq("userName", argument.getUserName()),
                                Expression.eq("password", argument.getPassword())
                        ),
                        Expression.eq("status", argument.getStatus())
                );
    }
}
