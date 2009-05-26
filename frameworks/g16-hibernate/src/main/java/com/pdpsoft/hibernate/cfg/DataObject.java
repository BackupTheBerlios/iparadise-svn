package com.pdpsoft.hibernate.cfg;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

/**
 * @author hasha
 * @depricated
 */
public class DataObject implements Serializable, Cloneable {
    public static final String TERMINATOR_STRING = "\n";
    public static final String INNER_START = "[";
    public static final String INNER_END = "]";

    public String toString() {
        Field[] thisClassFields = this.getClass().getDeclaredFields();
        String thisClassName =
                this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") +
                        1);

        String thisToString =
                "{" + TERMINATOR_STRING + ":" + this.getClass().getName() +
                        TERMINATOR_STRING;

        for (int fieldIndex = 0; fieldIndex < thisClassFields.length;
             fieldIndex++) {
            try {
                Field thisField = thisClassFields[fieldIndex];
                String getterMethodName =
                        "get" + String.valueOf(thisField.getName().charAt(0)).toUpperCase() +
                                thisField.getName().substring(1);
                Object thisFieldValue =
                        this.getClass().getMethod(getterMethodName,
                                new Class[]{}).invoke(this,
                                new Object[]{});
                thisToString +=
                        INNER_START + thisClassName + "." + thisField.getName() +
                                " = " +
                                ((thisFieldValue != null) ? thisFieldValue.toString() :
                                        "NULL") + INNER_END + TERMINATOR_STRING;
            } catch (Exception exception) {
                thisToString +=
                        "<" + exception.getMessage() + ">" + INNER_END +
                                TERMINATOR_STRING;
            }
        }

        thisToString += "}";

        return thisToString;
    }

    public DataObject() {
    }


    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setKafaDataObjects(List kafaDataObjects) {
        this.kafaDataObjects = kafaDataObjects;
    }


    public List getKafaDataObjects() {
        return kafaDataObjects;
    }

    private List kafaDataObjects;
}
