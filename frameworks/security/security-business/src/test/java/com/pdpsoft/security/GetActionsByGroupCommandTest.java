package com.pdpsoft.security;

import com.pdpsoft.commonbiz.util.G16AspectOrientedManagement;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 4:17:03 PM
 */
public class GetActionsByGroupCommandTest extends TestCase {

    final static Log LOG = LogFactory.getLog(GetActionsByGroupCommandTest.class);

    public GetActionsByGroupCommandTest(String s) {
        super(s);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetActionsByGroupCommand() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("GetActionsByGroupCommand");

        SystemGroupEntity argument = new SystemGroupEntity(1);
        Map<Integer, Object> paramMap = new HashMap<Integer, Object>(1);
        paramMap.put(0, argument);

        Map<Integer, Object> returnValue = (Map<Integer, Object>) management.process(paramMap);
        SystemGroupEntity groupEntity = (SystemGroupEntity) returnValue.get(0);        

        assertNotNull(groupEntity);
    }

    public void testGetAllActionAsTreeCommand() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("GetAllActionAsTreeCommand");

        List<SystemActionEntity> systemActionEntities = (List<SystemActionEntity>) management.process(null);

        assertNull(systemActionEntities);
    }

    public void testViewGroupService() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("DetailGroupService");

        SystemGroupEntity argument = new SystemGroupEntity(1);
        Map<Integer, Object> paramMap = new HashMap<Integer, Object>(1);
        paramMap.put(0, argument);

        Map<Integer, Object> returnValue = (Map<Integer, Object>) management.process(paramMap);
        SystemGroupEntity groupEntity = (SystemGroupEntity) returnValue.get(0);
        List<GroupActionEntity> groupActionEntities = (List<GroupActionEntity>) returnValue.get(1);
        List<SystemActionEntity> systemActionEntities = (List<SystemActionEntity>) returnValue.get(2);

        assertNotNull(groupEntity);
    }
}