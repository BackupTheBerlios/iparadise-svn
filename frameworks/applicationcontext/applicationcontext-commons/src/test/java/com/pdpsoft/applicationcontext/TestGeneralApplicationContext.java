package com.pdpsoft.applicationcontext;

import junit.framework.TestCase;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 10:51:49 AM
 */
public class TestGeneralApplicationContext extends TestCase {
    private static final Log LOG = LogFactory.getLog(TestGeneralApplicationContext.class);

    public TestGeneralApplicationContext(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGeneral() throws Exception {
        List<AttributeCustomEntity> attributeCustomEntities = new ArrayList<AttributeCustomEntity>(2);
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("0", "message.inactive");map1.put("1", "message.active");
        AttributeCustomEntity attributeCustomEntity1 = new AttributeCustomEntity("status", map1);

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("0", "message.step0");map2.put("1", "message.step1");map2.put("2", "message.step2");map2.put("3", "message.step3");
        AttributeCustomEntity attributeCustomEntity2 = new AttributeCustomEntity("workflow", map2);

        attributeCustomEntities.add(attributeCustomEntity1);
        attributeCustomEntities.add(attributeCustomEntity2);

        List<AttributeCustomEntity> attributeCustomEntities2 = new ArrayList<AttributeCustomEntity>(2);
        HashMap<String, String> map11 = new HashMap<String, String>();
        map11.put("0", "message.inactive");map11.put("1", "message.active");
        AttributeCustomEntity attributeCustomEntity11 = new AttributeCustomEntity("status", map11);

        HashMap<String, String> map22 = new HashMap<String, String>();
        map22.put("0", "message.step0");map22.put("1", "message.step1");map22.put("2", "message.step2");map22.put("3", "message.step3");
        AttributeCustomEntity attributeCustomEntity22 = new AttributeCustomEntity("workflow", map22);

        attributeCustomEntities2.add(attributeCustomEntity11);
        attributeCustomEntities2.add(attributeCustomEntity22);

        List<DataTransferObjectCustomEntity> dataTransferObjectCustomEntities = new ArrayList<DataTransferObjectCustomEntity>(2);
        dataTransferObjectCustomEntities.add(new DataTransferObjectCustomEntity(
                "com.pdpsoft.entity.UserEntity", attributeCustomEntities
        ));
        dataTransferObjectCustomEntities.add(new DataTransferObjectCustomEntity(
                "com.pdpsoft.entity.UserTypeEntity", attributeCustomEntities2
        ));

        ApplicationDataCustomEntity applicationDataCustomEntity = new ApplicationDataCustomEntity(
                dataTransferObjectCustomEntities
        );

        ApplicationContextHolder.getApplicationContextHolder().setApplicationContext(applicationDataCustomEntity);

        List<ApplicationContextDataCustomEntity> contextDataCustomEntities = ApplicationContextHolder
                .getApplicationContextHolder()
                .getObjects("com.pdpsoft.entity.UserEntity", "status");
        for (ApplicationContextDataCustomEntity contextDataCustomEntity : contextDataCustomEntities) {
            LOG.info(contextDataCustomEntity.getValue() + " the bunle is " + contextDataCustomEntity.getBundleKey());
        }

        assertTrue(true);
    }
}
