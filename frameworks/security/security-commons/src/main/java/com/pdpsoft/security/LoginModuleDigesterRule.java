package com.pdpsoft.security;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.File;

import com.pdpsoft.commons.ResourceLocator;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 30, 2009
 * Time: 2:17:07 PM
 */
public class LoginModuleDigesterRule {
    /**
     * this method is in charge of parsing the xml file
     * @param loginModuleXmlFilePath path of xml file
     * @return an instance of LoginModulesCustomEntity with its associated parameters
     */
    public static LoginModulesCustomEntity parseLoginModules(final String loginModuleXmlFilePath) {
        Digester digester = new Digester();

        // this is the root of XML <login-modules> ... </login-modules>
        digester.addObjectCreate("login-modules", LoginModulesCustomEntity.class);
        // this is the inner level login-modules/login-module
        digester.addObjectCreate("login-modules/login-module", LoginModuleCustomEntity.class);
        // and in here we set the properties of login-modules/login-module; nameKey="" default-schema-name="" schema-password="" >
        digester.addSetProperties("login-modules/login-module");
        // it handles login-modules/login-module/schemas this XML part
        digester.addObjectCreate("login-modules/login-module/schemas", LoginSchemasCustomEntity.class);

        // in this time we should map the second-level catch; login-modules/login-module/schemas
        digester.addCallMethod("login-modules/login-module/schemas/schema", "addParameter", 3);
        digester.addCallParam("login-modules/login-module/schemas/schema", 0, "nameKey");
        digester.addCallParam("login-modules/login-module/schemas/schema", 1, "schema-name");
        digester.addCallParam("login-modules/login-module/schemas/schema", 2, "cityCode");


        digester.addSetNext("login-modules/login-module/schemas", "addLoginSchemasCustomEntity");
        digester.addSetNext("login-modules/login-module", "addLoginModuleCustomEntity");

        try {

            return (LoginModulesCustomEntity) digester.parse(
// Only for local test
//         new File(loginModuleXmlFilePath)
                    ResourceLocator.getResourceAsStream(loginModuleXmlFilePath)
            );
        } catch (IOException e) {
            throw new RuntimeException("IO Error occured in login module data processing", e);
        } catch (SAXException e) {
            throw new RuntimeException("SAX Error occured in login module data processing", e);
        }
    }

    public static void main(String[] args) {
        final String path = "C:\\Java\\pdp-svn2\\frameworks\\trunk\\security\\security-commons\\src\\main\\java\\com\\pdpsoft\\security\\loginModules.xml";
        LoginModuleDigesterRule rule = new LoginModuleDigesterRule();
        LoginModulesCustomEntity entity = rule.parseLoginModules(path);
        System.out.println("entity = " + entity);
    }
}
