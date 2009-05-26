package com.pdpsoft.commons;

/**
 * Created by IntelliJ IDEA.
 * User: a_amini
 * Date: Jun 9, 2008
 * Time: 12:01:21 PM
 * Description: This class is going to use for jalili date, both for transient in web logic or persistent
 * created from version 1.4-SNAPSHOT
 */
public class G16JaliliDateValueObject extends G16ValueObject{
    private String day;
    private String month;
    private String year;

    public G16JaliliDateValueObject() {
    }

    public G16JaliliDateValueObject(String stringJaliliDate) {
        String[] strings = stringJaliliDate.split("/");
        if(strings[0].length() == 4){
            setDay(strings[2]);
            setMonth(strings[1]);
            setYear(strings[0]);
        }
        else{
            setDay(strings[0]);
            setMonth(strings[1]);
            setYear(strings[2]);
        }
    }


    public G16JaliliDateValueObject(String day, String month, String year) {
        setDay(day);
        setMonth(month);
        setYear(year);
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
