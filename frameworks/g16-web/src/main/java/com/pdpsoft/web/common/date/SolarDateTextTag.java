package com.pdpsoft.web.common.date;

/**
 * @author M. H. Shamsi
 * @version 1.0.0
 * @
 * @see
 */

public class SolarDateTextTag { //extends TextFieldTag {
/*    private static final String DEFAULT_BEAN = "org.apache.struts.taglib.html.BEAN";
    private static final String TODAY_DATE = "now";
    private static final String DEFAULT_FORMAT = "dd/MM/yyyy";

    private static Logger logger = Logger.getLogger(SolarDateTextTag.class);


    private String format;

    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        return format;
    }

    public String getValue() {
        try {
            return convertDate();
        } catch (JspException e) {
            logger.error(e, e);
        }

        return "";
    }

    private String convertDate() throws JspException {
        Object attribute;

        if (!StringUtils.isEmpty(name)) {
            attribute = findAttribute(name);
        } else {
            attribute = findAttribute(DEFAULT_BEAN);

        }

        if (attribute == null) {
            throw new JspException(" DateTextTag : Bean not found in any scope.");
        }

        Date temp = null;
        try {
            temp = (Date) PropertyUtils.getProperty(attribute, property);
        } catch (IllegalAccessException e) {
            logger.error(e, e);
        } catch (InvocationTargetException e) {
            logger.error(e, e);
        } catch (NoSuchMethodException e) {
            logger.error(e, e);
        }

        if ((temp == null) && (TODAY_DATE.equals(value))) {
            temp = new Date();

        }

        if (temp != null) {
            return formatDate(temp);
        }

        return value;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(getFormat());
        dateFormat.setCalendar(new JalaliCalendar());
        return dateFormat.format(date);
    }

    private Object findAttribute(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        int scope = pageContext.getAttributesScope(name);
        if (scope == 0) {
            return null;
        }

        return pageContext.getAttribute(name, scope);
    }
*/}

