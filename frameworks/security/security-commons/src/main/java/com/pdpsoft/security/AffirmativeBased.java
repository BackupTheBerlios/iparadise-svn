package com.pdpsoft.security;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 9:23:25 AM
 */
public class AffirmativeBased implements AccessDecisionManager {
    /**
     * Resolves an access control decision for the passed parameters.
     *
     * @param entity the caller invoking the method
     * @param object the secured object being called, it is an instace of String object
     * @return Decision that has happened, it is an instance of Boolean object which
     *         TRUE means yes, it has the permission; and
     *         FALSE means no, it has NOT suffice permission.
     */
    public Object decide(AuthorizationCustomEntity entity, Object object) {
        final String userEntityDescription = (String) object;
        List<SystemActionEntity> systemActionEntities = SecurityContextHolder.getContext().getAuthorizations();
        return
                CollectionUtils.exists(systemActionEntities, new Predicate() {
                    public boolean evaluate(Object o) {
                        SystemActionEntity entity = (SystemActionEntity) o;
                        return entity.getCode().equals(userEntityDescription);
                    }
                });
    }
}
