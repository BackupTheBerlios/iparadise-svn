package com.pdpsoft.security;

import com.pdpsoft.commons.G16ParentCustomEntity;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 28, 2009
 * Time: 3:29:44 PM
 */
public class LoginSchemasCustomEntity extends G16ParentCustomEntity {
    /*
        This schema handles all schemas take a look at:
        <schema nameKey="" schema-name="" />;
        the nameKey is the key of the map;
        the schema-name is value of the map
     */
    @Deprecated
    private Map<String, String> schemas;
    private List<LoginModuleContextDataCustomEntity> customEntities;

    public LoginSchemasCustomEntity() {
        schemas = new HashMap<String, String>();
        customEntities = new ArrayList<LoginModuleContextDataCustomEntity>();
    }

    /**
     * this method is in charge of adding to shcemas
     * the caller is digester rule
     * @param key the nameKey
     * @param value the schema-name
     * @param cityCode city code
     */
    public void addParameter(final String key, final String value, final String cityCode) {
        if(schemas.containsKey(key))
            throw new RuntimeException("In login-modules there is a duplicate on key " .concat(key));
        schemas.put(key, value);

        // Since version of adding city code
        LoginModuleContextDataCustomEntity entity = new LoginModuleContextDataCustomEntity(
                value, key, cityCode
        );
        customEntities.add(entity);
    }

    @Deprecated
    public Map<String, String> getSchemas() {
        return schemas;
    }

    @Deprecated
    public void setSchemas(Map<String, String> schemas) {
        this.schemas = schemas;
    }

    public List<LoginModuleContextDataCustomEntity> getCustomEntities() {
        return customEntities;
    }
}
