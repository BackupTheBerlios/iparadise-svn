package com.pdpsoft.g16.apms.business;

import com.pdpsoft.commonbiz.util.applicationcontex.G16ApplicationContext;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: May 25, 2008
 * Time: 3:27:11 PM
 */
public class ApplicationContextTest extends TestCase {

    public ApplicationContextTest(String string) {
        super(string);
    }

    public void testG16ApplicationContext() throws Exception {
        G16ApplicationContext.getInstance();
    }

    public static Test suite() {
        return new TestSuite(ApplicationContextTest.class);
    }

}
