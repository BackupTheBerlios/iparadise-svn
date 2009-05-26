package com.pdpsoft;

import junit.framework.TestCase;
import com.pdpsoft.business.PersistenceManager;
import com.pdpsoft.business.PersistenceManagerFactory;
import com.pdpsoft.commonbiz.util.applicationcontex.G16ApplicationContextBeans;
import com.pdpsoft.security.SystemUserEntity;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 4:17:03 PM
 */
public class BillyardGeneralTest extends TestCase {

    PersistenceManager persistenceManager;
    final static Log LOG = LogFactory.getLog(BillyardGeneralTest.class);

    public BillyardGeneralTest(String s) {
        super(s);
    }

    protected void setUp() throws Exception {
        super.setUp();

        persistenceManager = PersistenceManagerFactory.getDefault();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
        persistenceManager = null;
    }

    public void testDatabaseMapping() throws Exception {
        assertNotNull(persistenceManager);
    }

    public void testGetAllUserEntities() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(2);
        map.put(0, SystemUserEntity.class);
        map.put(1, null);

        List<SystemUserEntity> systemUserEntities = (List<SystemUserEntity>) G16ApplicationContextBeans.getG16Getall().execute(map);
        assertNotNull(systemUserEntities);
        LOG.info( "systemUserEntities.size = ".concat(String.valueOf(systemUserEntities.size())));
    }
}
