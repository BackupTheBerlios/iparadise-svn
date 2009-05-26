package com.pdpsoft.commons;

import java.io.Serializable;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Jul 15, 2006
 * Time: 5:00:45 PM
 * Description: This class is going to be wrapper of bwoth transient
 * and persistent objects of project.
 * Besides, this class is realized the following interfaces:
 * 1) Serializable : Because we can send it throw network.
 * 2) Cloneable : For having shallow copy for all objects.
 */
public class G16ValueObject implements Serializable, Cloneable {
    public G16ValueObject() {
    }

    /**
     * @return An instance of object which refers to shallow copy concept
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
