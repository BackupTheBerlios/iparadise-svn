package com.pdpsoft.security;

import junit.framework.TestCase;
import com.pdpsoft.commonbiz.util.G16AspectOrientedManagement;
import com.pdpsoft.security.SystemUserEntity;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Closure;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Apr 19, 2009
 * Time: 4:17:03 PM
 */
public class AuthenticationCriterionCommandTest extends TestCase {

    final static Log LOG = LogFactory.getLog(AuthenticationCriterionCommandTest.class);

    public AuthenticationCriterionCommandTest(String s) {
        super(s);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetAllUserEntities() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("AuthenticationCriterionCommand");

        SystemUserEntity argument = new SystemUserEntity();
        argument.setUserName("admin");
        argument.setPassword("1");
        Map<Integer, Object> paramMap = new HashMap<Integer, Object>(1);
        paramMap.put(0, argument);

        Map<Integer, Object> mapAll = new HashMap<Integer, Object>(3);
        mapAll.put(0, SystemUserEntity.class);
        mapAll.put(1, paramMap);
        mapAll.put(2, null);

        List<SystemUserEntity> systemUserEntities = (List<SystemUserEntity>) management.getAllCommand(mapAll);
        assertNotNull(systemUserEntities);
        assertFalse("User and pass are wrong ", systemUserEntities.size() == 0);
        LOG.info( "systemUserEntities.size = ".concat(String.valueOf(systemUserEntities.size())));
    }

    public void testAuthorization() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("AuthorizationCommand");

        SystemUserEntity argument = new SystemUserEntity();
        argument.setSystemUserId(1);
        argument.setUserName("shayan");
        argument.setPassword("1");
        argument.setStatus(SystemUserEntity.StatusTypeEnumeration.ACTIVE);
        Map<Integer, Object> paramMap = new HashMap<Integer, Object>(1);
        paramMap.put(0, argument);

        List<SystemActionEntity> systemUserEntities = (List<SystemActionEntity>) management.process(paramMap);
        assertNotNull(systemUserEntities);
        assertEquals(systemUserEntities.size(), 3);
        CollectionUtils.forAllDo(systemUserEntities, new Closure() {
            public void execute(Object o) {
                SystemActionEntity entity = (SystemActionEntity) o;
                LOG.debug(entity.getSystemActionId());
                LOG.debug(entity.getCode());
                LOG.debug(entity.getDescription());
            }
        });
    }

    public void testAAService() throws Exception {
        G16AspectOrientedManagement management = new G16AspectOrientedManagement("ParadiseAuthenticationAuthorizationService");

        SystemUserEntity argument = new SystemUserEntity();
        argument.setUserName("admin");
        argument.setPassword("1");
        Map<Integer, Object> paramMap = new HashMap<Integer, Object>(1);
        paramMap.put(0, argument);

        AuthorizationCustomEntity customEntity = (AuthorizationCustomEntity) management.process(paramMap);
        argument = customEntity.getSystemUserEntity();
        assertNotNull(argument);
        assertEquals(argument.getSystemUserId(), Integer.valueOf(1));
        LOG.debug(argument.getSurname());

        List<SystemActionEntity> actionEntities = customEntity.getSystemActionEntities();
        assertNotNull(actionEntities);
        assertEquals(actionEntities.size(), 3);
        CollectionUtils.forAllDo(actionEntities, new Closure() {
            public void execute(Object o) {
                SystemActionEntity entity = (SystemActionEntity) o;
                LOG.debug(entity.getSystemActionId());
                LOG.debug(entity.getCode());
                LOG.debug(entity.getDescription());
            }
        });
    }
}