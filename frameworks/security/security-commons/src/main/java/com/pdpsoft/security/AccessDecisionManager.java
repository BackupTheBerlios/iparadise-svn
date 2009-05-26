package com.pdpsoft.security;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 9:10:06 AM
 * Makes a final access control (authorization) decision
 */
public interface AccessDecisionManager {
    /**
     * Resolves an access control decision for the passed parameters.
     * @param entity the caller invoking the method
     * @param object the secured object being called
     * @return Decision that has happened.
     */
    Object decide(AuthorizationCustomEntity entity, Object object);
}
