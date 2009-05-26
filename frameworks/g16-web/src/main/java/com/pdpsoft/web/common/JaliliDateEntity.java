package com.pdpsoft.web.common;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini
 * Date: May 7, 2008
 * Time: 3:06:12 PM
 * this object is created from version 0.5.3 and used for converting jalil and gregorian dates to each other
 * @deprecated from version 0.5.4, instead of it use G16JaliliDateValueObject from g16-commons
 */
public class JaliliDateEntity {
    private String day;
    private String month;
    private String year;

    public JaliliDateEntity() {
    }

    public JaliliDateEntity(String stringJaliliDate) {
        String[] strings = stringJaliliDate.split("/");
        this.day = strings[2];
        this.month = strings[1];
        this.year = strings[0];
    }


    public JaliliDateEntity(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        if(day.length() == 1){
            day = "0" + day;
        }
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        if(month.length() == 1){
            month = "0" + month;
        }
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        return day + "/" + month + "/" + year;
    }

}
