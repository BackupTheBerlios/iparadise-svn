package com.pdpsoft.security;

import fr.improve.struts.taglib.layout.policy.AbstractPolicy;

import javax.servlet.jsp.PageContext;

/**
 * Created by IntelliJ IDEA.
 * User: Saman Shojatalab
 * Date: Apr 28, 2009
 * Time: 4:18:19 PM
 */
public class SecurityWebPolicy extends AbstractPolicy {
    /**
     * @param policy The argument that the developer has been set in the layout:policy tag; such as
     *         <i> <layout:policy policy="policy" /> /<i>
     * @param name allows the developer to get the bean the user wants to modify (usually the struts ActionForm)
     * @param property allows the developer to know which property of the bean the user wants to modify.
     * @param pageContext Page Context
     * @return Decision that has happened, it is an instance of Boolean object which
     *          MODE_EDIT means yes, it has the permission; and
     *          MODE_NODISPLAY means no, it has NOT suffice permission.
     */
    public short getAuthorizedDisplayMode(String policy, String name, String property, PageContext pageContext) {
        Boolean decision = AccessDesicionMakerAffirmativeBased.decide(policy);
        if (decision)
            return MODE_EDIT;
        return MODE_NODISPLAY;
    }
}
