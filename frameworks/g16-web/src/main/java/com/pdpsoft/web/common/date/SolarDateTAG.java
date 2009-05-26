package com.pdpsoft.web.common.date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.ResourceBundle;


public class SolarDateTAG extends TagSupport {
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
//            out.println("<script language='javascript' type='text/javascript' src='/com/js/solarcalendar.js'></script>");
            out.println("<a class='CALENDRIER'>" + title + "</a>");
            // lI(Reza Ghaffaripour) moved the text fied out of this component.user should have a layout:text for date. property of this layout:text should be the name of calendar:solar_dare tag.
            //      out.println("<input type='text' id='"+name+"' class='clsDateBox' name='"+name+"'  />");
            out.println("<a href=javascript:NewCal('" + name + "') ><img border=0 src='" + srcKey + "' alt='" + altKey + "' /></a>");

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
        srcKey = theResourceBundle.getString(value);
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