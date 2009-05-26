package com.pdpsoft.applicationcontext;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 17, 2009
 * Time: 6:12:59 PM
 * This is the interface that handles the manner of data accessings.
 */
public interface ApplicationContext {
    /**
     * This method handles the access to application data
     * @return an instance of ApplicationDataCustomEntity
     */
    ApplicationDataCustomEntity getApplicationContext();

    /**
     * This method used to put the context to the thread.
     * @param context it's an instance of ApplicationDataCustomEntity that
     *                we ought to keep it via threadlocal pattern.
     */
    void setApplicationContext(ApplicationDataCustomEntity context);
}
