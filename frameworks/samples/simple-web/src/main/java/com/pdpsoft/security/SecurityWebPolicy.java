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
    public short getAuthorizedDisplayMode(String s, String s1, String s2, PageContext pageContext) {
        return MODE_EDIT;
    }
}
