package com.pdpsoft.security;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 4, 2009
 * Time: 9:26:18 AM
 * It handles the access decision making based on Affirmative Model
 */
public class AccessDesicionMakerAffirmativeBased {

    /**
     * Resolves an access control decision for the passed parameters.
     *
     * @param action the secured object being called, it is an instace of String object
     * @return Decision that has happened, it is an instance of Boolean object which
     *          TRUE means yes, it has the permission; and
     *          FALSE means no, it has NOT suffice permission.
     */
    public static Boolean decide(String action) {
        AccessDecisionManager accessDecisionManager = new AffirmativeBased();
        return (Boolean) accessDecisionManager.decide(SecurityContextHolder.getContext().getAllUserInformation(), action);
    }
}
