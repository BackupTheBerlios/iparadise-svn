package com.pdpsoft.applicationcontext;

import com.pdpsoft.commons.G16ParentCustomEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 10:15:55 AM
 * I used this class instead of to send map over tiers.
 *  This XML part is going to map to this class
 *  <expression value="1" bundleKey="Haghighi"/>
 */
public final class ApplicationContextDataCustomEntity extends G16ParentCustomEntity {
    /*
        This is the value part of XML, or it is the key parameter in Map<key, value)
     */
    private String value;
    /*
        This is the bundleKey of XML
     */
    private String bundleKey;
    /*
        This property should have value whenever we use it in web
     */
    private String message;
    /*
        this is the only sort item
     */
    private String sortItem;

    protected ApplicationContextDataCustomEntity(String value, String bundleKey, String sort) {
        this.value = value;
        this.bundleKey = bundleKey;
        this.sortItem = sort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getBundleKey() {
        return bundleKey;
    }

    public void setBundleKey(String bundleKey) {
        this.bundleKey = bundleKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSortItem() {
        return sortItem;
    }

    public void setSortItem(String sortItem) {
        this.sortItem = sortItem;
    }
}
