package com.pdpsoft.web;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Arrays;

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
 * @date Jun 19, 2005
 */
public class DateConvertor implements Converter {
    public DateConvertor() {
    }

    private final static String GOOD_CHAR = "0123456789/";

    public Object convert(Class inClazz, Object inObject) {
        if (inObject instanceof String) {
            String inObjectStr = (String) inObject;
            boolean loopChecking = true;

            for (int iCounter = 0; iCounter < inObjectStr.length(); iCounter++) {
                if (!loopChecking)
                    return new Date();
                loopChecking = false;
                for (int jCounter = 0; jCounter < GOOD_CHAR.length(); jCounter++)
                    if (inObjectStr.charAt(iCounter) == GOOD_CHAR.charAt(jCounter))
                        loopChecking = true;
            }
        }

        if (Date.class.equals(inClazz)) {
            return object2Date(inObject);
        }
        return (inObject);
    }

    private Date object2Date(Object inObject) throws ConversionException {

        if (inObject == null) {
            return null;
        }

        if (inObject instanceof Date) {
            return (Date) inObject;
        }

        if (!(inObject instanceof String)) {
            return null;
        }

        String strDate = (String) inObject;
        if (strDate.trim().length() == 0) {
            return null;
        }

        final String[] strings = StringUtils.split(strDate, '/');
        Calendar jalaliCalendar = new JalaliCalendar(Integer.valueOf(strings[0]), Integer.valueOf(strings[1]) - 1, Integer.valueOf(strings[2]));
        return jalaliCalendar.getTime();
    }

    public static void main(String[] args) {
        DateConvertor cvr = new DateConvertor();
        Object object = cvr.convert(String.class, new Date());
        System.out.println("object = " + object);
        Date date = (Date) cvr.convert(Date.class, "1384/02/17");
        System.out.println("Date : " + date);
    }
}