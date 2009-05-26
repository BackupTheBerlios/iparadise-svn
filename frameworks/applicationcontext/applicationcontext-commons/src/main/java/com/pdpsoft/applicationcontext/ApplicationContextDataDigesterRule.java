package com.pdpsoft.applicationcontext;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.pdpsoft.commons.ResourceLocator;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 19, 2009
 * Time: 8:53:36 AM
 */
public class ApplicationContextDataDigesterRule {
    public static ApplicationDataCustomEntity xmlParsing(final String xmlPath) {
        Digester digester = new Digester();
        // This the root of XML <application-data>...</application-data>
        digester.addObjectCreate("application-data", ApplicationDataCustomEntity.class);
        // This one step inside <dto fullQualifiedClassName="Customer"></dto>
        digester.addObjectCreate("application-data/dto", DataTransferObjectCustomEntity.class);
        // This is the properties mapping
        digester.addSetProperties("application-data/dto");

        // This is the second step inside <attribute propertyName="customerType"></attribute>
        digester.addObjectCreate("application-data/dto/attribute", AttributeCustomEntity.class);
        // This is the properties mapping
        digester.addSetProperties("application-data/dto/attribute");

        // This is the third step inside <expression value="1" bundleKey="Haghighi"/>
        digester.addCallMethod("application-data/dto/attribute/expression", "addMap", 2);
        digester.addCallParam("application-data/dto/attribute/expression", 0, "value");
        digester.addCallParam("application-data/dto/attribute/expression", 1, "bundleKey" );

        // Next Attribute
        digester.addSetNext("application-data/dto/attribute", "addAttributeCustomEntity");
        // Next DataTransferObjectCustomEntity
        digester.addSetNext("application-data/dto", "addDataTransferObjectCustomEntity");

        try {
            return (ApplicationDataCustomEntity) digester.parse(
                    ResourceLocator.getResourceAsStream(xmlPath)
            );
        } catch (IOException e) {
            throw new RuntimeException("IO Error occured in application context data processing", e);
        } catch (SAXException e) {
            throw new RuntimeException("SAX Error occured in application context data processing", e);
        }
    }
}
