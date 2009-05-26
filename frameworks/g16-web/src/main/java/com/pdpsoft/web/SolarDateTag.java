package com.pdpsoft.web;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 9:36:34 PM
 * the code has been moved from KAFA project, but unfortunately
 * there was not any author specified, but i think it has been
 * developed by Mohammad Shams. I've changed some naming conventions
 * and the packaging.

   saman: 
		Updated doStartTag, don't include solarcalendar.js
		Updated setSrcKey to don't read from Resource Bundle

 */
public class SolarDateTag extends TagSupport {
    /*
    tag attribute: srcKey
    */
    private String srcKey = "";
    /*
    tag attribute: title
    */
    private String title = "";
    /*
    tag attribute: altKey
    */
    private String altKey = "";
    /*
    tag attribute: name
    */
    private String name = "";

    private String value = "";

    ResourceBundle theResourceBundle = ResourceBundle.getBundle("resources.ApplicationResources");


    /**
     * Method called at start of tag.
     *
     * @return SKIP_BODY
     */
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            
            //out.println("<script language='javascript' type='text/javascript' src='com/js/solarcalendar.js'></script>");
            out.println("<a class='CALENDRIER'>" + title + "</a>");
            // I(Reza Ghaffaripour) moved the text field out of this component.user should have a layout:text for date. property of this layout:text should be the name of calendar:solar_dare tag.
            //      out.println("<input type='text' id='"+name+"' class='clsDateBox' name='"+name+"'  />");
            out.println("<a href=javascript:NewCal('" + name + "')><img border=0 src='" + srcKey + "' alt='" + altKey + "'></a>");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    /**
     * Method called at end of tag.
     *
     * @return EVAL_PAGE
     */
    public int doEndTag() {
        return EVAL_PAGE;
    }

    public void setSrcKey(String value) {
		//remove this line,
        //srcKey = theResourceBundle.getString(value);
		srcKey = value;
    }

    public String getSrcKey() {
        return srcKey;
    }

    public void setTitle(String value) {
        title = theResourceBundle.getString(value);
    }

    public String getTitle() {
        return title;
    }

    public void setAltKey(String value) {
        altKey = theResourceBundle.getString(value);
    }

    public String getAltKey() {
        return altKey;
    }

    public void setName(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }

    public void setValue(String val) {
        value = val;
    }

    public String getValue() {
        return value;
    }

}
