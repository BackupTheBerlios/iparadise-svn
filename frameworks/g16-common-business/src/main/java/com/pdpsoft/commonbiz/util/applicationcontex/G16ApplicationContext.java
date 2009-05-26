/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 25, 2008
 * Time: 3:19:53 PM
 */
package com.pdpsoft.commonbiz.util.applicationcontex;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.PropertyResourceBundle;



public class G16ApplicationContext {
    private static G16ApplicationContext ourInstance = new G16ApplicationContext();
    private static ApplicationContext context;

    private Log LOGGER = LogFactory.getLog( G16ApplicationContext.class );

    public static G16ApplicationContext getInstance() {
        return ourInstance;
    }

    private G16ApplicationContext() {
        configAppContext();
    }

    /**
     * Reading the /samane/G16ApplicationContexts.properties and then getting the files
     * which ought to parse as a application context files.
     */
    private void configAppContext() {
        String[] files;
        try {
            ClassPathResource bundleResource = new ClassPathResource("/iParadise/G16ApplicationContexts.properties");
            PropertyResourceBundle bundle = new PropertyResourceBundle(
                    bundleResource.getInputStream());
            Enumeration<String> keys = bundle.getKeys();
            List<String> appContextFiles = new ArrayList<String>();
            int appContextFilesLength = 0;
            while (keys.hasMoreElements()) {
                appContextFiles.add(bundle.getString(keys.nextElement()));
                appContextFilesLength++;
            }

            files = new String[appContextFilesLength];
            readingApplicationContextFiles(appContextFiles, files);
        } catch (IOException e) {
            throw new RuntimeException("IOException while reading G16ApplicationContexts.properties file ", e);
        }
        context = new ClassPathXmlApplicationContext(files);
    }

    /**
     *
     * @param appContextFiles List of application context files
     * @param files the path of applicationcontexts files
     */
    private void readingApplicationContextFiles(List<String> appContextFiles, String[] files) {
        Object[] objects = appContextFiles.toArray();
        for (int counter = 0; counter < objects.length; counter++) {
            String object = (String) objects[counter];
            files[counter] = object;
            LOGGER.info("Initializing application contect file:" + object);
        }
    }


    /**
     *
     * @return ApplicationContext
     */
    public ApplicationContext getContext() {
        if(context == null)
            throw new RuntimeException("The Application Context is null.");
        return context;
    }
}
