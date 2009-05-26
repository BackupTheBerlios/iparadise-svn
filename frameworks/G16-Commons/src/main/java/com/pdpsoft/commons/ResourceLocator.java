package com.pdpsoft.commons;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Common utilities for finding resource and config files.
 * <p/>
 * Ashnaco - G16
 * User: d_farid
 * Date: Aug 14, 2006
 * Time: 10:47:24 AM
 */
public class ResourceLocator {

    /**
     * Try to find the resource from current thread classloader,
     * Classloader, or in the system property, basedire, path.
     *
     * @param name resource name
     * @return URL of the resource
     */
    public static URL getResource(String name) {
        URL resource = null;
        try {
            File file = new File(System.getProperty("basedir", ".") + File.separator + name);
            if (file.exists())
                resource = file.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (resource == null)
            resource = URLClassLoader.getSystemResource(name);
        if (resource == null)
            resource = URLClassLoader.getSystemClassLoader().getSystemResource(name);
        if (resource == null)
            resource = ClassLoader.getSystemResource(name);
        if (resource == null)
            resource = ClassLoader.getSystemClassLoader().getSystemResource(name);
        if (resource == null)
            resource = Thread.currentThread().getContextClassLoader().getResource(name);
        return resource;
    }

    /**
     * Get the inputStream from <code> URL getResource(name) </code>
     *
     * @param name resource name
     * @return resource input stream
     * @throws IOException
     */
    public static InputStream getResourceAsStream(String name) throws IOException {
        URL resource = getResource(name);
        if (resource != null)
            return resource.openStream();
        else
            return null;
    }

}
