package com.pdpsoft.web.common;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Sep 4, 2006
 * Time: 2:36:28 PM
 */
public class ErrorCatalogImpl implements ErrorCatalog {
    private static final String ERROR_CATALOG_NAME = ErrorCatalog.class.getClassLoader().getResource("exceptions/errorCatalog.xml").getPath();
    private static final String ERROR_CATALOG = "errorCatalog";

    private static final String ERROR_KEY = "errorKey";
    private static final String ERROR_ID = "errorId";
    private static final String ERROR_DESCRIPTION = "errorDescription";
    private static final String CUSTOMER_KEY = "customerKey";
    private static final String TECHNICAL_KEY = "technicalKey";
    private static final String DESTINATION_PAGE = "destinationPage";

    public ErrorCatalogImpl() {
    }

    public ErrorCatalogTransferObject getErrorCatalog(WebException e) {
        ErrorCatalogTransferObject to = new ErrorCatalogTransferObject();
        try {
            DocumentBuilderFactory docBuildFac = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docBuildFac.newDocumentBuilder();
            Document doc = docBuild.parse(new File(ERROR_CATALOG_NAME));

            doc.getDocumentElement().normalize();

            to = getErrorCatalogKey(e.getErrorNumber(), doc);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return to;
    }

    private NodeList getNodeList(Document doc) {
        NodeList listOfErrorCatalog = null;
        try {
            listOfErrorCatalog = doc.getElementsByTagName(ERROR_CATALOG);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return listOfErrorCatalog;
    }

    private Element getElement(Element errorCatElement, String tagName) {
        Element errorCatElementIn = null;
        try {
            NodeList tagList = errorCatElement.getElementsByTagName(tagName);
            errorCatElementIn = (Element) tagList.item(0);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return errorCatElementIn;
    }

    private ErrorCatalogTransferObject getErrorCatalogKey(int errorNumber, Document doc) {
        NodeList listOfErrorCatalog = getNodeList(doc);
        int nodeListSize = listOfErrorCatalog.getLength();
        ArrayList errorCatalogTransferObjectArray = new ArrayList();
        ErrorCatalogTransferObject to = new ErrorCatalogTransferObject();

        String errNumber = "" + errorNumber;

        try {
            for (int nodeIterator = 0; nodeIterator < nodeListSize; nodeIterator++) {
                ArrayList elementArray = new ArrayList();
                Node errorCatNode = listOfErrorCatalog.item(nodeIterator);

                if (errorCatNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element errorCatElement = (Element) errorCatNode;

                    Element errorKeyElement = getElement(errorCatElement, ERROR_KEY);
                    elementArray.add(errorKeyElement);

                    Element errorIdElement = getElement(errorCatElement, ERROR_ID);
                    elementArray.add(errorIdElement);

                    Element errorDescElement = getElement(errorCatElement, ERROR_DESCRIPTION);
                    elementArray.add(errorDescElement);

                    Element cusKeyElement = getElement(errorCatElement, CUSTOMER_KEY);
                    elementArray.add(cusKeyElement);

                    Element techKeyElement = getElement(errorCatElement, TECHNICAL_KEY);
                    elementArray.add(techKeyElement);

                    Element destElement = getElement(errorCatElement, DESTINATION_PAGE);
                    elementArray.add(destElement);


                    NodeList textEIList = errorIdElement.getChildNodes();

                    if (errNumber.equalsIgnoreCase(((Node) textEIList.item(0)).getNodeValue().trim())) {
                        errorCatalogTransferObjectArray.add(saveErrorCatalogTransferObject(elementArray));
                    }
                }
            }

            if (checkResult(errorCatalogTransferObjectArray).booleanValue()) {
                to = (ErrorCatalogTransferObject) errorCatalogTransferObjectArray.get(0);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return to;
    }

    public Boolean checkResult(ArrayList array) {
        Boolean vResult = new Boolean("false");

        if (array.size() == 1)
            vResult = new Boolean("true");
        if (array.size() > 1)
            System.out.println("WARN : In errorCatalog.xml file there's a DUPLICATE.");
        if (array.size() == 0)
            System.out.println("WARN : In errorCatalog.xml file there's NOT any matched.");

        return vResult;
    }

    public ErrorCatalogTransferObject saveErrorCatalogTransferObject(ArrayList array) {
        ErrorCatalogTransferObject to = new ErrorCatalogTransferObject();
        try {
            Element errorKey = (Element) array.get(0);
            NodeList textEKList = errorKey.getChildNodes();
            to.setErrorKey(((Node) textEKList.item(0)).getNodeValue().trim());


            Element errorId = (Element) array.get(1);
            NodeList textEIList = errorId.getChildNodes();
            to.setErrorId(((Node) textEIList.item(0)).getNodeValue().trim());


            Element errorDesc = (Element) array.get(2);
            NodeList textEDList = errorDesc.getChildNodes();
            to.setErrorDescription(((Node) textEDList.item(0)).getNodeValue().trim());


            Element customer = (Element) array.get(3);
            NodeList textCUList = customer.getChildNodes();
            to.setCustomerKey(((Node) textCUList.item(0)).getNodeValue().trim());


            Element technical = (Element) array.get(4);
            NodeList textTEList = technical.getChildNodes();
            to.setTechnicalKey(((Node) textTEList.item(0)).getNodeValue().trim());


            Element dest = (Element) array.get(5);
            NodeList textDSList = dest.getChildNodes();
            to.setDestination(((Node) textDSList.item(0)).getNodeValue().trim());

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return to;
    }


}
