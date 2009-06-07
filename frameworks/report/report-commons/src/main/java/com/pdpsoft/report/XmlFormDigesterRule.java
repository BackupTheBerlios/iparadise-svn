package com.pdpsoft.report;

import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import java.io.IOException;

import com.pdpsoft.commons.ResourceLocator;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 4, 2009
 * Time: 11:56:16 AM
 */
public class XmlFormDigesterRule {

    /**
     * this method is in charge of parsing the xml file
     *
     * @param xmlFormPath path of xml file
     * @return an instance of XmlFormCustomEntity with its associated parameters
     */
    public static XmlFormsCustomEntity parseXmlForms(final String xmlFormPath) {
        Digester digester = new Digester();

        // this is the root of the object
        digester.addObjectCreate("forms", XmlFormsCustomEntity.class);
        // the inner level of xml
        digester.addObjectCreate("forms/form", XmlFormCustomEntity.class);
        // bind the <form id="" dto="" filePath="" > properties
        digester.addSetProperties("forms/form");
        // the inner level of xml
        digester.addObjectCreate("forms/form/parameter", XmlFormParameterCustomEntity.class);
        // bind the <parameter dtoParameter="" fileParameter="" decorator="" />
        digester.addSetProperties("forms/form/parameter");

        digester.addSetNext("forms/form/parameter", "addParameter");
        digester.addSetNext("forms/form", "addForm");
        try {

            return (XmlFormsCustomEntity) digester.parse(
// Only for local test
//         new java.io.File(xmlFormPath)
                    ResourceLocator.getResourceAsStream(xmlFormPath)
            );
        } catch (IOException e) {
            throw new RuntimeException("IO Error occured in XmlForm processing", e);
        } catch (SAXException e) {
            throw new RuntimeException("SAX Error occured in XmlForm processing", e);
        }
    }

    public static void main(String[] args) {
        final String path = "C:\\Java\\pdp-svn2\\frameworks\\trunk\\report\\report-commons\\src\\main\\java\\com\\pdpsoft\\report\\forms.xml";
        final XmlFormsCustomEntity customEntity = XmlFormDigesterRule.parseXmlForms(path);
        System.out.println("customEntity = " + customEntity);
    }
}
