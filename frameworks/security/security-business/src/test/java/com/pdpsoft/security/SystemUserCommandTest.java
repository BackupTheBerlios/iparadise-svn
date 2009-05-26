package com.pdpsoft.security;

import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.pdpsoft.commonbiz.util.G16AspectOrientedManagement;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sajedi
 * Date: Apr 28, 2009
 * Time: 10:44:39 AM
  */
public class SystemUserCommandTest extends TestCase {

    final static Log LOG = LogFactory.getLog(SystemUserCommandTest.class);

     public SystemUserCommandTest(String s) {
        super(s);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetUserEntityWithRelatedGroupCommandWithASystemUserEntityParam() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("GetUserEntityWithRelatedGroupCommand");

        SystemUserEntity argument = new SystemUserEntity(1);

        Map<Integer, Object> paramMap = new HashMap<Integer, Object>(1);
        paramMap.put(0, argument);

         List<SystemUserCustomEntity> systemUserCustomEntities = (List<SystemUserCustomEntity>) management.process(paramMap);

        //assertNotNull(groupEntity);
    }
     public void testGetUserEntityWithRelatedGroupCommandWithNullParam() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("GetUserEntityWithRelatedGroupCommand");


         List<SystemUserCustomEntity> systemUserCustomEntities = (List<SystemUserCustomEntity>) management.process(null);

    }
}
