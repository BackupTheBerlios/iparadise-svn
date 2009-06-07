package com.pdpsoft.report;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 4, 2009
 * Time: 3:34:01 PM
 * this inteface is in charge of decorating the dto parameter into forms
 */
public interface XmlFormDecorator {
    /**
     * it should change the decoration of the value
     * @param dtoValue this is original data
     * @return the decorated value should be return
     */
    Object decorate(Object dtoValue);
}
