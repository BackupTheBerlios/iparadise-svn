package com.pdpsoft.web;

import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 9:44:18 PM
 * the code has been moved from KAFA project, but unfortunately
 * there was not any author specified, but i think it has been
 * developed by Mohammad Shams. I've changed some naming conventions
 * and the packaging; and some few code styles.
 *
 * @author M. H. Shamsi
 * @version 1.0.0
 * @date Jun 19, 2005
 */
public class ELSolarDateTagBeanInfo extends SimpleBeanInfo {
    public PropertyDescriptor[] getPropertyDescriptors() {
        List<PropertyDescriptor> propList = new ArrayList<PropertyDescriptor>();

        try {
            propList.add(new PropertyDescriptor("name", ELSolarDateTag.class, null, "setNameExpr"));
        } catch (java.beans.IntrospectionException e) {
            e.printStackTrace();
        }

        return propList.toArray(new PropertyDescriptor[propList.size()]);
    }
}