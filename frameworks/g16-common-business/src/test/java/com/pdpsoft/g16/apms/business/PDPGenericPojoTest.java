package com.pdpsoft.g16.apms.business;

import com.pdpsoft.commonbiz.GenericPojoCommand;
import com.pdpsoft.commonbiz.Table1Entity;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * GenericPojoCommand class Tester.
 *
 * @author h_shayan
 * @version 1.0
 * @since <pre>12/20/2006</pre>
 */
public class PDPGenericPojoTest extends TestCase {

    GenericPojoCommand command;

    public PDPGenericPojoTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
    	command = GenericPojoCommand.getInstance();
        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

     public static Test suite() {
        return new TestSuite(PDPGenericPojoTest.class);
    }
    
    private GenericPojoCommand lookUpejb() {
    	return command;
    }
     
    public void testGetAll() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(0);
        List<Table1Entity> entities = (List<Table1Entity>) lookUpejb().getAllObjects(Table1Entity.class, map, "g16");
        for (Table1Entity entity : entities) {
            System.out.println("entity.getCol2() = " + entity.getCol2());
        }
    }

/*    public void testGetAllWithCriterion() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(1);
        map.put(0, "Canada");
        List<Table1Entity> entities = (List<Table1Entity>) lookUpejb().getAllObjects(Table1Entity.class, map, "frameworkTest");
        for (Table1Entity entity : entities) {
            System.out.println("entity.getCountryName() = " + entity.getCountryName());
        }
    }
*/
    public void testPersist() throws Exception {
        Table1Entity entity = new Table1Entity(1, "Canada", 1);
        lookUpejb().persistObject(entity, "g16");
    }
/*    public void testPersistReturnPreFalse() throws Exception {
        Table1Entity entity = new Table1Entity("China");
        lookUpejb().persistObject(entity, "FrameworkTest");
    }
*/    public void testInsert() throws Exception {
        Table1Entity entity = new Table1Entity(2, "Canada", 2);
        lookUpejb().insertObject(entity, Boolean.FALSE, "g16");
    }
    public void testInsertWithUpdate() throws Exception {
        Table1Entity entity = new Table1Entity(2, "China", 2);
        lookUpejb().insertObject(entity, Boolean.TRUE, "g16");
    }
/*    public void testInsertWithUpdateAndReturnPreFalse() throws Exception {
        Table1Entity entity = new Table1Entity(4, "China");
        lookUpejb().insertObject(entity, Boolean.TRUE, "FrameworkTest");
    }
    public void testFindByPrimaryKeyTypeOne() throws Exception {
        Table1Entity entity = (Table1Entity) lookUpejb().findByPrimaryKey(Table1Entity.class, 1, "g16");
        System.out.println("entity.getCountryName() = " + entity.getCountryName());
    }
    public void testRemove() throws Exception {
        lookUpejb().removeObject(new Table1Entity(5), "g16");
    }
    public void testRemoveReturnPreFalse() throws Exception {
        lookUpejb().removeObject(new Table1Entity(5), "FrameworkTest");
    }
    public void testProcess() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(0);
        List<Table1Entity> entities = (List<Table1Entity>) lookUpejb().process(map, "FrameworkTest");
        for (Table1Entity entity : entities) {
            System.out.println("entity.getCountryName() = " + entity.getCountryName());
        }
    }
*/}
