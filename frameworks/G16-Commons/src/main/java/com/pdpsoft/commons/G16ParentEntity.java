package com.pdpsoft.commons;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Jul 15, 2006
 * Time: 5:04:15 PM
 * Description: All of Data Transfer Objects which are persistent would
 * inherit from G16ParentEntity.java ; In the other
 * hand all of custom entities are going to inherit from
 * this class. This class, itself, is inherited from
 * G16ValueObject.java, which is realized the following
 * well-known interfaces: Serializable, Cloneable
 * 1) Serializable : Because we can send it throw network.
 * 2) Cloneable : For having shallow copy for all objects.
 */
public class G16ParentEntity extends G16ValueObject {
    public G16ParentEntity() {
    }
}
