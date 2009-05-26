package com.pdpsoft.web.common;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini
 * Date: Jan 16, 2008
 * Time: 4:50:19 PM
 */
public interface G16ChainManager {
    /*
        Since version 5.2 the return type change from void to boolean
     */
    boolean run(G16ChainContext context) throws Exception;
}
