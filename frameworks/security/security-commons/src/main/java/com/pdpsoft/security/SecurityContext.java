package com.pdpsoft.security;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 8:58:23 AM
 * Interface defining the minimum security information associated with the current thread of execution.
 */
public interface SecurityContext {

    /**
     * Returns all user information
     * @return All information of user in AuthorizationCustomEntity type.
     */
    AuthorizationCustomEntity getAllUserInformation();

    /**
     * Obtains the currently authenticated principal, or an authentication request token.
     *
     * @return the Authentication or null if no authentication information is available
     */
    SystemUserEntity getAuthentication();

    /**
     *  Obtains the user authorizations, which it means the whole accesses that he has.
     * @return List of SystemActionEntity that the current user has them.
     */
    List<SystemActionEntity> getAuthorizations();

    /**
     * Changes the currently authenticated principal, or removes the authentication information.
     *
     * @param authentication the new Authentication token, or null if no further authentication information should be stored
     */
    void setAuthentication(AuthorizationCustomEntity authentication);
}
