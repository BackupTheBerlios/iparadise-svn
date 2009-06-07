package com.pdpsoft.report;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 4, 2009
 * Time: 3:38:20 PM
 */
public class SimpleXmlFormDecorator implements XmlFormDecorator {
    /**
     * it should change the decoration of the value
     *
     * @param dtoValue this is original data
     * @return the decorated value should be return
     */
    public Object decorate(Object dtoValue) {
        return dtoValue;
    }
}
