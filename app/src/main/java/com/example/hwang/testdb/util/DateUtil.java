package com.example.hwang.testdb.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hwang on 2016-06-26.
 */
public class DateUtil {

    public static String getTime()
    {
        SimpleDateFormat sd = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.KOREA);
        long time = System.currentTimeMillis();
        Date currentTime = new Date(time);
        return "[" + sd.format(currentTime) + "]";
    }

    public static String getDate()
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd. (E)", Locale.KOREA);
        long time = System.currentTimeMillis();
        Date currentTime = new Date(time);
        return sd.format(currentTime);
    }

    public static String getDayOfWeek(int dayOfWeek)
    {
        String[] week = {"일","월","화","수","목","금","토"};
        return week[dayOfWeek-1];
    }

    public static String convertDateForm(int year, int month, int day, int dayOfWeek) {

        String strMonth = String.valueOf(++month);
        String strDay = String.valueOf(day);

        if(strMonth.length() == 1){
            strMonth = "0" + strMonth;
        }

        if(strDay.length() == 1){
            strDay = "0" + strDay;
        }

        String strDayOfWeek = getDayOfWeek(dayOfWeek);
        String strDate = year + "." + strMonth + "." + strDay + ". " + "("+strDayOfWeek +")";

        return strDate;
    }
}
