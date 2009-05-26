package com.pdpsoft.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.beanutils.PropertyUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 9:36:34 PM
 * the code has been moved from KAFA project, but unfortunately
 * there was not any author specified, but i think it has been
 * developed by Mohammad Shams. I've changed some naming conventions
 * and the packaging.
 *
 * @author M. H. Shamsi
 * @version 1.0.0
 * @date May 7, 2005
 * @see
 */
public class SolarDateLabelTag extends TagSupport {
    private static final String DEFAULT_BEAN = "org.apache.struts.taglib.html.BEAN";
    private static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    private static Log logger = LogFactory.getLog(SolarDateLabelTag.class);

    private String name;
    private String property;
    private String format;

    public SolarDateLabelTag() {
        release();
    }

    public void release() {
        super.release();

        format = DEFAULT_FORMAT;
        name = DEFAULT_BEAN;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        renderLabel(out);

        return EVAL_PAGE;
    }

    public int doEndTag() {
        release();
        return EVAL_PAGE;
    }

    private void renderLabel(JspWriter out) throws JspException {
        try {
            out.print(convertDate());
        }
        catch (IOException e) {
            logger.error(e);
        }
    }

    private String convertDate() throws JspException {
        Object attribute;
        String date = "";

        attribute = findAttribute(getName());

        if (attribute == null) {
            throw new JspException("DataLabelTag : Bean not found in any scope.");
        }

        try {
            Date temp = (Date) PropertyUtils.getProperty(attribute, property);
            if (temp != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                dateFormat.setCalendar(new JalaliCalendar());
                date = dateFormat.format(temp);
            }
        } catch (IllegalAccessException e) {
            logger.error(e);
        } catch (NoSuchMethodException e) {
            logger.error(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return date;
    }

    private Object findAttribute(String name) {
        if (org.apache.commons.lang.StringUtils.isEmpty(name)) {
            return null;
        }

        int scope = pageContext.getAttributesScope(name);
        if (scope == 0) {
            return null;
        }

        return pageContext.getAttribute(name, scope);

    }
}
