package com.pdpsoft.web;

import org.displaytag.decorator.ColumnDecorator;
import org.displaytag.exception.DecoratorException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ChrisShayan.com
 * Date: May 16, 2009
 * Time: 10:11:58 PM
 *
 * @author Reza Ghaffaripour
 * @see
 */
public class SolarDateDecorator implements ColumnDecorator {
    public String decorate(Object o) throws DecoratorException {
        if (o == null) {
            return "";
        }

        if (o instanceof Date) {
            Date date = (Date) o;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            format.setCalendar(new JalaliCalendar());
            return format.format(date);
        }

        return o.toString();
    }
}
