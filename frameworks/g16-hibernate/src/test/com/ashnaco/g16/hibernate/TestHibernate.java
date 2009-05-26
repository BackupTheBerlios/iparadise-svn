package com.ashnaco.g16.hibernate;

import com.ashnaco.g16.hibernate.cfg.DataObject;
import com.ashnaco.g16.hibernate.crud.HibernatePersistenceManager;
import com.ashnaco.g16.hibernate.crud.HibernatePersistenceManagerImpl;
import com.ashnaco.g16.hibernate.TestNozzleInfo;
import com.ashnaco.g16.hibernate.TestPTInfo;

import java.util.Date;

/**
 * @author hasha
 * @version
 */
public class TestHibernate {
    public static final int INSERT_COUNT = 1;
    
    
    public TestHibernate() {        
    }
    
    public static class Insert {
        private static Object getValueObjectForInsert() {
//            Test test = new Test();
//            test.setBuildDate(new Date());
//            test.setDesc("TEST10CHAR");
//            test.setsName("TEST10CHAR");
//            return test;
            return null;
        }
        public static void insert() throws HibernateEXC {
            HibernatePersistenceManager pm = HibernatePersistenceManagerImpl.getInstance();
            for (int counter = 0; counter < INSERT_COUNT; counter++)  {
                pm.save(Insert.getValueObjectForInsert());
            }
        }
    }
    public static class Nozzle {
        public static void insert() throws HibernateEXC {
            HibernatePersistenceManager pm = HibernatePersistenceManagerImpl.getInstance();
            TestNozzleInfo dto = null;
            for (int counter = 2; counter < 19; counter++)  {
                 dto = new TestNozzleInfo();
                for (int insert = 1; insert < 33; insert++)  {
                    if(counter < 10)
                        dto.setGsId("000" + counter);
                    else
                        dto.setGsId("00" + counter);
                    
                    if (insert < 10) {
                        dto.setNozzleId("0" + insert);   
                        dto.setNozzleName("n0" + insert);
                    }                        
                    else {
                        dto.setNozzleId(String.valueOf(insert));
                        dto.setNozzleName("n" + String.valueOf(insert));
                    }
                    if(insert < 17)
                        dto.setOilCanId("01");
                    else
                        dto.setOilCanId("02");
                    
                    dto.setState("1");
                    dto.setInitStatus("1");
                    
                    pm.save(dto);
                }                                            
            }
        }
    }
    public static class PT {
        public static void insert() throws HibernateEXC {
            HibernatePersistenceManager pm = HibernatePersistenceManagerImpl.getInstance();
            TestPTInfo dto = null;
            for (int counter = 2; counter < 19; counter++)  {
                 dto = new TestPTInfo();
                for (int insert = 1; insert < 33; insert++)  {
                    if(counter < 10)
                        dto.setGsId("000" + counter);
                    else
                        dto.setGsId("00" + counter);
                    
                    if (insert < 10) {
                        dto.setPtId("0" + insert);
                        dto.setNozzleId("0" + insert);
                    }                        
                    else {
                        dto.setPtId(String.valueOf(insert));
                        dto.setNozzleId(String.valueOf(insert));
                    }
                    if(insert < 17) {
                        dto.setFuelType("01");
                        dto.setOilcanId("01");
                    }                        
                    else {
                        dto.setFuelType("02");
                        dto.setOilcanId("02");
                    }                        
                    if(insert < 16) {
                        // One Digits
                        dto.setFuelSamId(dto.getGsId() + "0000000" + Integer.toString(insert, 16));
                        dto.setPaySamId(dto.getGsId() + "0000000" + Integer.toString(insert, 16));
                    } else {
                        // Two Digits
                        dto.setFuelSamId(dto.getGsId() + "0000000" + Integer.toString(insert, 16));
                        dto.setPaySamId(dto.getGsId() + "000000" + Integer.toString(insert, 16));
                    }
                    
                    dto.setValidity("1");
                    dto.setInitStatus("1");
                    dto.setIpcIpAddr("10.85.10.1");
                    dto.setPtIpAddr("10.85.10.1" + (30 + insert));
                    
                    pm.save(dto);
                }                                            
            }
        }
    }
    public static void template() throws HibernateEXC {
        HibernateUtility.init();
        Date begin = new Date();
        TestHibernate.Insert.insert();
        Date end = new Date();
        System.out.println(end.getTime() - begin.getTime());
    }
    public static void templateNozzle() throws HibernateEXC {
        TestHibernate.Nozzle.insert();
    }
    public static void templateTestMySql() throws HibernateEXC {
//        Test t = new Test();
//        t.setsName("TestFromCode");
//        HibernatePersistenceManager pm = HibernatePersistenceManagerImpl.getInstance();
//        pm.save(t);
    }
    public static void main(String[] args) throws HibernateEXC {
        HibernateUtility.init();
        templateTestMySql();
    }
}
