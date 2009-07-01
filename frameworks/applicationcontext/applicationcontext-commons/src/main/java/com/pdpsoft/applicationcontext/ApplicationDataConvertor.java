package com.pdpsoft.applicationcontext;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 18, 2009
 * Time: 10:21:59 AM
 * It handles the conversion between Map and ApplicationContextDataCustomEntity
 */
public class ApplicationDataConvertor {
     ApplicationDataConvertor() {
         // it shouldn't be instantiated.
    }

    /**
     * Convert a map to List of ApplicationContextDataCustomEntity
     * @param map the parameter
     * @param sort the sorted map
     * @return an instance of List<ApplicationContextDataCustomEntity>
     */
    public static List<ApplicationContextDataCustomEntity> getApplicationContextDataCustomEntities(Map<String, String> map, Map<String, String> sort) {
        List<ApplicationContextDataCustomEntity> list = new ArrayList<ApplicationContextDataCustomEntity>(map.size());
        Set<Map.Entry<String,String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            ApplicationContextDataCustomEntity entity = new ApplicationContextDataCustomEntity(
                    entry.getKey(),  // This is the value part :: <expression value="2" bundleKey="Hoghoghi"/>
                    entry.getValue(), // This is the bundleKey part :: <expression value="2" bundleKey="Hoghoghi"/>
                    sort.get(entry.getKey()) // this is the sort value
            );
            list.add(entity);
        }
        Collections.sort(list, new Comparator<ApplicationContextDataCustomEntity>() {
            public int compare(ApplicationContextDataCustomEntity o1, ApplicationContextDataCustomEntity o2) {
                String firstValue  = o1.getSortItem();
                String secondValue = o2.getSortItem();
                if(firstValue == null || secondValue == null)
                    throw new RuntimeException("the sort item is null");
                return firstValue.compareTo(secondValue);
            }
        });
        return list;
    }

    /**
     * Convert a List of ApplicationContextDataCustomEntity to a map
     * @param list list of ApplicationContextDataCustomEntity
     * @return an instance of Map<String, String>
     */
    public static Map<String, String> getMap(List<ApplicationContextDataCustomEntity> list) {
        Map<String, String> map = new HashMap<String, String>(list.size());
        for (ApplicationContextDataCustomEntity entity : list) {
            map.put(
              entity.getValue(),
              entity.getBundleKey()
            );
        }
        return map;
    }
}
