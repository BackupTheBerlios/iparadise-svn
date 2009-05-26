package com.pdpsoft.g16.apms.business;

import com.pdpsoft.commonbiz.G16CommonBusinessSession;
import com.pdpsoft.commonbiz.G16CommonBusinessSessionBean;
import com.pdpsoft.commonbiz.G16CommonBusinessSessionHome;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.mockejb.MockContainer;
import org.mockejb.SessionBeanDescriptor;
import org.mockejb.jndi.MockContextFactory;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * G16CommonBusinessSessionBean Tester.
 *
 * @author hamed, danial
 * @version 1.0
 * @since <pre>12/20/2006</pre>
 */
public class APMSSessionBeanTest extends TestCase {

    private Context context;

    public APMSSessionBeanTest(String name) {
        super(name);
    }

    public void setUp() throws Exception {
        /* We need to set MockContextFactory as our JNDI provider.
        * This method sets the necessary system properties.
        */
        MockContextFactory.setAsInitial();

        // create the initial context that will be used for binding EJBs
        context = new InitialContext();

        // Create an instance of the MockContainer
        MockContainer mockContainer = new MockContainer(context);

        /* Create deployment descriptor of our sample bean.
        * MockEjb uses it instead of XML deployment descriptors
        */
        SessionBeanDescriptor sampleServiceDescriptor =
                new SessionBeanDescriptor("G16CommonBusinessSessionEJB",
                        G16CommonBusinessSessionHome.class, G16CommonBusinessSession.class, new G16CommonBusinessSessionBean());
        // Deploy operation creates Home and binds it to JNDI
        mockContainer.deploy(sampleServiceDescriptor);

        super.setUp();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testSetSessionContext() throws Exception {
        //TODO: Test goes here...
    }

    private G16CommonBusinessSession lookUpejb() throws NamingException, RemoteException, CreateException {
        // Lookup the home
        Object leaverRequest = context.lookup("G16CommonBusinessSessionEJB");

        // PortableRemoteObject does not do anything in MockEJB but it does no harm to call it
        G16CommonBusinessSessionHome home =
                (G16CommonBusinessSessionHome) PortableRemoteObject.narrow(leaverRequest,
                        G16CommonBusinessSessionHome.class);

        // create the bean
        return home.create();
    }

    public static Test suite() {
        return new TestSuite(APMSSessionBeanTest.class);
    }

/*    public void testGetAll() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(0);
        List<CountryEntity> entities = (List<CountryEntity>) lookUpejb().getAllObjects(CountryEntity.class, map, "g16");
        for (CountryEntity entity : entities) {
            System.out.println("entity.getCountryName() = " + entity.getCountryName());
        }
    }
    public void testGetAllWithCriterion() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(1);
        map.put(0, "Canada");
        List<CountryEntity> entities = (List<CountryEntity>) lookUpejb().getAllObjects(CountryEntity.class, map, "frameworkTest");
        for (CountryEntity entity : entities) {
            System.out.println("entity.getCountryName() = " + entity.getCountryName());
        }
    }
    public void testPersist() throws Exception {
        CountryEntity entity = new CountryEntity("Canada");
        lookUpejb().persistObject(entity, "g16");
    }
    public void testPersistReturnPreFalse() throws Exception {
        CountryEntity entity = new CountryEntity("China");
        lookUpejb().persistObject(entity, "FrameworkTest");
    }
    public void testInsert() throws Exception {
        CountryEntity entity = new CountryEntity(4, "Canada");
        lookUpejb().insertObject(entity, Boolean.FALSE, "g16");
    }
    public void testInsertWithUpdate() throws Exception {
        CountryEntity entity = new CountryEntity(4, "China");
        lookUpejb().insertObject(entity, Boolean.TRUE, "g16");
    }
    public void testInsertWithUpdateAndReturnPreFalse() throws Exception {
        CountryEntity entity = new CountryEntity(4, "China");
        lookUpejb().insertObject(entity, Boolean.TRUE, "FrameworkTest");
    }
    public void testFindByPrimaryKeyTypeOne() throws Exception {
        CountryEntity entity = (CountryEntity) lookUpejb().findByPrimaryKey(CountryEntity.class, 1, "g16");
        System.out.println("entity.getCountryName() = " + entity.getCountryName());
    }
    public void testRemove() throws Exception {
        lookUpejb().removeObject(new CountryEntity(5), "g16");
    }
    public void testRemoveReturnPreFalse() throws Exception {
        lookUpejb().removeObject(new CountryEntity(5), "FrameworkTest");
    }
    public void testProcess() throws Exception {
        Map<Integer, Object> map = new HashMap<Integer, Object>(0);
        List<CountryEntity> entities = (List<CountryEntity>) lookUpejb().process(map, "FrameworkTest");
        for (CountryEntity entity : entities) {
            System.out.println("entity.getCountryName() = " + entity.getCountryName());
        }
    }
*/}
