package com.pdpsoft.security;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 30, 2009
 * Time: 7:33:20 PM
 */
public class LoginModuleDataConvertor {
    private LoginModuleDataConvertor() {
        // it shouldn't be instantiated.
    }

    /**
     * Convert a map to List of LoginModuleContextDataCustomEntity
     * @param map the parameter
     * @return an instance of List<LoginModuleContextDataCustomEntity>
     */
    public static List<LoginModuleContextDataCustomEntity> getApplicationContextDataCustomEntities(Map<String, String> map) {
        List<LoginModuleContextDataCustomEntity> list = new ArrayList<LoginModuleContextDataCustomEntity>(map.size());
        Set<Map.Entry<String,String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            LoginModuleContextDataCustomEntity entity = new LoginModuleContextDataCustomEntity(
                    entry.getValue(), // this is the schema-name <schema ... schema-name="22" />
                    entry.getKey()    // this is the key name    <schema nameKey="11" ... />
            );
            list.add(entity);
        }
        return list;
    }

    /**
     * Convert a List of LoginModuleContextDataCustomEntity to a map
     * @param list list of LoginModuleContextDataCustomEntity
     * @return an instance of Map<String, String>
     */
    public static Map<String, String> getMap(List<LoginModuleContextDataCustomEntity> list) {
        Map<String, String> map = new HashMap<String, String>(list.size());
        for (LoginModuleContextDataCustomEntity entity : list) {
            map.put(
              entity.getNameKey(),
              entity.getSchemaName()
            );
        }
        return map;
    }
    

}
