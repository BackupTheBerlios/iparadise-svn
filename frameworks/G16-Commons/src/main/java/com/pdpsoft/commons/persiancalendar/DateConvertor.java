package com.pdpsoft.commons.persiancalendar;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Ashna Company, G16
 * User: h_shayan
 * Date: Sep 9, 2006
 * Time: 6:21:55 PM
 */
public class DateConvertor implements Converter, Serializable {
    public DateConvertor() {
    }

    public Object convert(Class inClazz, Object inObject) {

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
        Calendar c =
                new JalaliCalendar(Integer.valueOf(strDate.substring(6, 10)),
                        Integer.valueOf(strDate.substring(3, 5)) - 1,
                        Integer.valueOf(strDate.substring(0, 2)));

        return c.getTime();
    }

    public String convertDate(Date temp) {
        String date = "";
        if (temp != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            dateFormat.setCalendar(new JalaliCalendar());
            date = dateFormat.format(temp);
        }

        return date;
    }

    public static void main(String[] args) {
        DateConvertor cvr = new DateConvertor();
        Date date = (Date) cvr.convert(Date.class, "18/06/1385");

        System.out.println("Date : " + date);
        System.out.println(cvr.convertDate(date));
    }
}

