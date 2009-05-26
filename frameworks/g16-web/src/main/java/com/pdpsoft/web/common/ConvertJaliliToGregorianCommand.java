package com.pdpsoft.web.common;

import com.pdpsoft.commons.persiancalendar.DateConvertor;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.Context;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini & s_sadjedy
 * Date: Apr 8, 2008
 * Time: 11:30:17 AM
 */
public class ConvertJaliliToGregorianCommand implements G16WebCommand {
    public boolean execute(Context context) throws Exception {

        G16ChainContext g16ChainContext = (G16ChainContext) context;

        if(g16ChainContext.getG16JaliliDateValueObjects() != null){
            g16ChainContext.setGregorianDates(new Date[g16ChainContext.getG16JaliliDateValueObjects().length]);
            for (int counter = 0; counter < g16ChainContext.getG16JaliliDateValueObjects().length; counter++) {
                g16ChainContext.getGregorianDates()[counter] =
                        convertToGregorian(g16ChainContext.getG16JaliliDateValueObjects()[counter].toString());
            }
        }
/*
        if(g16ChainContext.getJaliliDateEntities() != null){
            g16ChainContext.setGregorianDates(new Date[g16ChainContext.getJaliliDateEntities().length]);
            for (int counter = 0; counter < g16ChainContext.getJaliliDateEntities().length; counter++) {
                g16ChainContext.getGregorianDates()[counter] =
                        convertToGregorian(g16ChainContext.getJaliliDateEntities()[counter].toString());
            }
        }
*/
        return Command.CONTINUE_PROCESSING;
    }


    private String makeStringFromJaliliDateProperties(String jaliliDay, String jaliliMonth, String jaliliYear) {
        return jaliliDay + "/" + jaliliMonth + "/" + jaliliYear;
    }

    private Date convertToGregorian(final String date) {
        DateConvertor dateConvertor = new DateConvertor();
        return (Date) dateConvertor.convert(Date.class, date);
    }

    public static void main(String[] args) {
/*
        Date date = new Date();
        DateConvertor dateConvertor = new DateConvertor();
        String s = dateConvertor.convertDate(date);
        System.out.println("dateConvertor.convertDate(date) = " + s);
        JaliliDateEntity jaliliDateEntity = new JaliliDateEntity(s);
        System.out.println("jaliliDateEntity = " + jaliliDateEntity);
        System.out.println("jaliliDateEntity.getDay() = " + jaliliDateEntity.getDay());
        System.out.println("jaliliDateEntity.getMonth() = " + jaliliDateEntity.getMonth());
        System.out.println("jaliliDateEntity.getYear() = " + jaliliDateEntity.getYear());
*/
        Date date = new Date();
        System.out.println("date = " + date);
        DateConvertor dateConvertor = new DateConvertor();
        String s = dateConvertor.convertDate(date);
        System.out.println("s = " + s);
        String s2 = "05/03/1387";
        Date date1 = (Date) dateConvertor.convert(Date.class, s2);
        System.out.println("date1 = " + date1);
        String s1 = dateConvertor.convertDate(date1);
        System.out.println("s1 = " + s1);

    }


}
