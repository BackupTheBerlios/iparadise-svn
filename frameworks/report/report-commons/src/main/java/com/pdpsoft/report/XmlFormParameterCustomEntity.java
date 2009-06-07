package com.pdpsoft.report;

import com.pdpsoft.commons.G16ParentCustomEntity;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: Jun 3, 2009
 * Time: 8:11:07 PM
 * handles the <parameter dtoParameter="" fileParameter="" decorator="" /> mapping
 */
public class XmlFormParameterCustomEntity extends G16ParentCustomEntity {
    private String dtoParameter;
    private String fileParameter;
    private String decorator;
    private XmlFormDecorator xmlFormDecorator;

    public XmlFormParameterCustomEntity() {
    }

    public String getDtoParameter() {
        return dtoParameter;
    }

    public void setDtoParameter(String dtoParameter) {
        this.dtoParameter = dtoParameter;
    }

    public String getFileParameter() {
        return fileParameter;
    }

    public void setFileParameter(String fileParameter) {
        this.fileParameter = fileParameter;
    }

    public String getDecorator() {
        return decorator;
    }

    public void setDecorator(String decorator) {
        this.decorator = decorator;
    }

    /**
     * this method manage the instantitation of XmlFormDecorator;
     * on the other hand, somehow it is Factory.
     * @return the specific implementation of XmlFormDecorator and if
     *          there is not any decorator specified in XML file, hence
     *          it will return null. 
     */
    public XmlFormDecorator getXmlFormDecorator() {
        if (validation()) return null;
        /*
            the normal case that will be happen, hence
            the developer should use simple if they
            do not need any decoration happen.
         */
        if(getDecorator().equalsIgnoreCase("simple"))
            return new SimpleXmlFormDecorator();

        try {
            xmlFormDecorator = (XmlFormDecorator) Class.forName(getDecorator()).newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(getDecorator() + " is invalid xml form decorator", e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(getDecorator() + " is invalid xml form decorator", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(getDecorator() + " is invalid xml form decorator", e);
        }
        return xmlFormDecorator;
    }

    /**
     * handles the validation of decoration
     * @return true, if validation failed
     *         false, the validation is fine
     *              and the process should
     *              goes on.
     */
    private boolean validation() {
        return getDecorator() == null || getDecorator().equals("");
    }
}
