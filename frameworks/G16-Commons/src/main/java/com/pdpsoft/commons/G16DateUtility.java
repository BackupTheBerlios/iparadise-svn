package com.pdpsoft.commons;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Hamed Shayan, Aria amini
 * Date: Feb 12, 2007
 * Time: 1:52:26 PM
 */
public abstract class G16DateUtility {

    private static Date g16DefaultDate = defaultDate();
    private static Calendar g16DefaultCalendar = defaultCalendar();


    private G16DateUtility() {
    }

    /**
     * set defult G16Calender to Mon Jan 01 00:00:00 2007
     *
     * @return Calendar: by specific time
     */
    private static Calendar defaultCalendar() {
        Calendar cal = Calendar.getInstance();
        cal.set(2007, 0, 1, 0, 0, 0);
        return cal;
    }

    /**
     * set defult G16Date to Mon Jan 01 00:00:00 2007
     *
     * @return Date :specific date
     */
    private static Date defaultDate() {
        return defaultCalendar().getTime();
    }

    /**
     * return a Date object which its year, month and day are the defult value and the hour and minute change
     * to what programmer whants
     *
     * @param hour
     * @param minute
     * @return Date
     */
    public static Date getG16Time(int hour, int minute) {
        Calendar cal = defaultCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    public enum DayOfWeeks {
        SUN, MON, TUE, WED, THU, FRI, SAT, UNKNOWN
    }

    /**
     * @param date Input date
     * @return DayOfWeeks which is determine the day of week
     *         giving a date as parameter, returning which day it is in the format of DayOfWeeks enum
     *         for example if the day of the input date is monday return MON
     */
    public static DayOfWeeks getDayOfWeek(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                return DayOfWeeks.SUN;
            case 2:
                return DayOfWeeks.MON;
            case 3:
                return DayOfWeeks.TUE;
            case 4:
                return DayOfWeeks.WED;
            case 5:
                return DayOfWeeks.THU;
            case 6:
                return DayOfWeeks.FRI;
            case 7:
                return DayOfWeeks.SAT;
        }
        return DayOfWeeks.UNKNOWN;
    }

    /**
     * @param date       To determine which day
     * @param dayOfWeeks Name of day
     * @return true|false
     *         this method is used in getElapsed method ; see if the day of one date is equal to one day of week
     */
    public static boolean isSameDay(final Date date, final DayOfWeeks dayOfWeeks) {
        DayOfWeeks day = getDayOfWeek(date);
        return day.equals(dayOfWeeks);
    }

    /**
     * @param date   The main date
     * @param amount How many days will add to date?
     * @return Add amount day to date
     *         by giving a date change this date to some number(related to amount) days later
     *         for example if invoke addDay(new Date(2007-1900,0,1), 5) return value is Date(2007-1900, 0, 6)
     *         it means that 2007/1/1 change to 2007/1/6
     */
    public static Date addDay(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, amount);
        return cal.getTime();
    }

    /**
     * @param fromDate     Begining of set
     * @param toDate       End of set
     * @param dayOfWeekses Days of week.
     * @return a list that containe all days matched
     *         we want to see all the dates between the specific date
     *         that the day of them are one of day specifies in dayOfWeeks array list
     */
    public static List<Date> getDays(Date fromDate, final Date toDate, final List<DayOfWeeks> dayOfWeekses) {
        final List<Date> dates = new ArrayList<Date>();
        int daysDuration = getElapsed(fromDate, toDate);
        for (int counter = 0; counter < daysDuration; counter++) {
            for (DayOfWeeks day : dayOfWeekses) {
                if (isSameDay(fromDate, day))
                    dates.add(fromDate);
            }
            fromDate = addDay(fromDate, 1);
        }
        return dates;
    }

    /**
     *
     * @param fromDate
     * @param toDate
     * @return All daies betwean two dates in parameter
     */
    public static List<Date> getDays(Date fromDate, final Date toDate) {
        final List<Date> dates = new ArrayList<Date>();
        int daysDuration = getElapsed(fromDate, toDate);
        for (int counter = 0; counter <= daysDuration; counter++) {
            dates.add(fromDate);
            fromDate = addDay(fromDate, 1);
        }
        return dates;
    }

    /**
     * @param fromDate The beginig of this set.
     * @param toDate   The end of this set.
     * @return This is the duration of the fromDate and toDate.
     *         for example elapsed time between 2007/1/1 and 2007/1/6 is 5
     *         we invoke this methode by Date data type parameter
     */
    public static int getElapsed(Date fromDate, Date toDate) {
        GregorianCalendar fromGregorianCalendar = new GregorianCalendar();
        fromGregorianCalendar.setTime(fromDate);
        GregorianCalendar toGregorianCalendar = new GregorianCalendar();
        toGregorianCalendar.setTime(toDate);
        return getElapsed(fromGregorianCalendar, toGregorianCalendar);
    }

    /**
     * @param g1 The beginig of this set.
     * @param g2 The end of this set.
     * @return This is the duration of the fromDate and toDate.
     *         for example elapsed time between 2007/1/1 and 2007/1/6 is 5
     *         we invoke this methode by GregorianCalendar data type parameter
     */
    public static int getElapsed(GregorianCalendar g1, GregorianCalendar g2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        if (g2.after(g1)) {
            gc2 = (GregorianCalendar) g2.clone();
            gc1 = (GregorianCalendar) g1.clone();
        } else {
            gc2 = (GregorianCalendar) g1.clone();
            gc1 = (GregorianCalendar) g2.clone();
        }
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }

    private static final String DEFAULT_TIME_ZONE = "GMT";

    /**
     * @param timeZone Determine your GMT that you need
     * @param year     Year
     * @param month    Month - 0 for January
     * @param day      Day
     * @return Date
     */
    public static Date getG16Date(final String timeZone, final int year, final int month, final int day) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone((timeZone == null) ? DEFAULT_TIME_ZONE : timeZone));
        calendar.set(year, month, day, 0, 0, 0);
        return calendar.getTime();
    }

    /**
     * compute an instance of input date (from date) for search query
     * @param date
     * @return an instance of input date which refer to last hour, minute and second of the day before of input date
     */
    public static Date getG16FromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * compute an instance of input date (to date) for search query
     * @param date
     * @return an instance of input date which refer to last hour, minute and second of the input date 
     */
    public static Date getG16ToDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     * @param timeZone Determine your GMT that you need
     * @param year     Year
     * @param month    Month - 0 for January
     * @param day      Day
     * @param hour     the value used to set the <code>HOUR_OF_DAY</code> calendar field.
     * @param minute   the value used to set the <code>MINUTE</code> calendar field.
     * @return Date
     */
    public static Date getG16Date(final String timeZone, final int year, final int month, final int day
            , final int hour, final int minute) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone((timeZone == null) ? DEFAULT_TIME_ZONE : timeZone));
        calendar.set(year, month, day, hour, minute, 0);
        return calendar.getTime();
    }

    /**
     * @param timeZone Determine your GMT that you need
     * @return A date with GMT timezone
     */
    public static Date getG16Date(final String timeZone) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone((timeZone == null) ? DEFAULT_TIME_ZONE : timeZone));
        return calendar.getTime();
    }

    public static Date editTime(Date date, final int hour, final int minute, final int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    public static Date editTime(Date date, final int hour, final int minute, final int second, final int miliSecond) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, miliSecond);
        return calendar.getTime();
    }


    public static Date getG16DefaultDate() {
        return g16DefaultDate;
    }

    public static void setG16DefaultDate(Date g16DefaultDate) {
        G16DateUtility.g16DefaultDate = g16DefaultDate;
    }


    public static Calendar getG16DefaultCalendar() {
        return g16DefaultCalendar;
    }

    public static void setG16DefaultCalendar(Calendar g16DefaultCalendar) {
        G16DateUtility.g16DefaultCalendar = g16DefaultCalendar;
    }

    /**
     * check if two date palaced in one day or not
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static boolean isTwoDateInSameDay(Date firstDate, Date secondDate){
        firstDate = editTime(firstDate, 12, 0, 0, 0);
        secondDate = editTime(secondDate, 12, 0, 0, 0);
        return firstDate.getTime() == secondDate.getTime();
    }

    /**
     * millisecond differences between two date
     * @param date1
     * @param date2
     * @return
     */
    public static long getDifferenceMilliSeconds(Date date1, Date date2){
        return date1.getTime() - date2.getTime();
    }

    /**
     * hours differences between two date
     * @param date1
     * @param date2
     * @return
     */
    public static long getDifferenceHours(Date date1, Date date2){
        return getDifferenceMilliSeconds(date1, date2) / 3600000;
    }

    public static float getDifferenceHoursBaseOnFloat(Date date1, Date date2){
        float differenceMilliSeconds = getDifferenceMilliSeconds(date1, date2);
        return differenceMilliSeconds / 3600000;
    }

    /**
     * get a year in input and make and return a Date object whith those year and in 2 Jan
     * @param year int
     * @return date Date
     */
    public static Date getG16Year(int year){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
        calendar.set(year, 0, 2);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        Date date1 = editTime(getG16Date(null, 2007, 9, 9, 11, 59), 14, 0, 0);
        Date date2 = getG16Date(null);
        System.out.println("date2 = " + date2);
        System.out.println("date1 = " + date1);
        System.out.println("getDifferenceMilliSeconds(date2, date1) = " + getDifferenceMilliSeconds(date2, date1));
        System.out.println("getDifferenceHours(date2, date1) = " + getDifferenceHours(date2, date1));
        System.out.println("getDifferenceHoursBaseOnFloat(date1, date2) = " + getDifferenceHoursBaseOnFloat(date1, date2));

        System.out.println("getG16Year(1984) = " + getG16Year(1984));

    }
}
