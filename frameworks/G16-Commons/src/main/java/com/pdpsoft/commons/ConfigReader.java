package com.pdpsoft.commons;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The helper class for reading tags of a config file
 * <p/>
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 16, 2006
 * Time: 8:39:32 AM
 */
public class ConfigReader {

    public String configFileName;

    private List<String> values;
//    private Class objectClass;

    public ConfigReader() {
    }

    public ConfigReader(String configFileName) {
        this.configFileName = configFileName;
    }

    public String[] readValue(String pattern, int count) {
        values = new ArrayList<String>();
        Digester digester = new Digester();
        digester.setValidating(false);
        digester.addCallMethod(pattern, "setValue", count);
        try {
            digester.push(this);
            InputStream stream = ResourceLocator.getResourceAsStream(configFileName);
            if (stream == null)
                throw new FileNotFoundException("Config file " + configFileName + " not found!!");
            ConfigReader reader = (ConfigReader) digester.parse(stream);
            return reader.values.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readValue(String pattern) {
        String[] array = readValue(pattern, 0);
        if (array.length > 0)
            return array[0];
        else
            return null;
    }

/*
    Digester digester;

    public Object[] readValue(String pattern, Class aClass) {
        values = new ArrayList<String>();
        objectClass = aClass;
        digester = new Digester();
        digester.setValidating(false);
        digester.addCallMethod(pattern, "setObjectValue");
        for (Method method : aClass.getMethods()) {
            if ((method.getName().startsWith("set") || method.getName().startsWith("add")) &&
                    method.getParameterTypes().length == 1) {
                String str = (method.getName().charAt(3) + "").toLowerCase() +
                        method.getName().substring(4);
                digester.addCallMethod(pattern + "/" + str, method.getName());
            }
        }
        try {
            digester.push(this);
            InputStream stream = ResourceLocator.getResourceAsStream(configFileName);
            if (stream == null)
                throw new FileNotFoundException("Config file " + configFileName + " not found!!");
            ConfigReader reader = (ConfigReader) digester.parse(stream);
            ArrayList<Object> objects = new ArrayList<Object>();
            Object obj;
            while((obj = digester.pop()) != null)
                objects.add(obj);
            return objects.toArray(new Object[0]);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

*/
/*
    private boolean isCollection(Class<?> aClass) {
        if (aClass.equals(Collection.class))
            return true;
        while (aClass.getSuperclass() != null)
            if ((aClass = aClass.getSuperclass()).equals(Collection.class))
                return true;
        return false;
    }
*/

/*
    public void setObjectValue(String s) throws IllegalAccessException, InstantiationException {
        Object obj = objectClass.newInstance();
        digester.push(obj);
    }
*/

    public void setValue(String s) {
        if (values == null)
            values = new ArrayList<String>();
        values.add(s);
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public void setConfigFileName(String configFileName) {
        this.configFileName = configFileName;
    }
}
