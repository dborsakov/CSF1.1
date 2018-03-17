package com.example.shadow.csf11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 * Created by Shadow on 15.03.2018.
 * Update by Daniil on 17.03.2018.
 */

public class Time {

    private int numOfWeek, sNumOfWeek;
    private int sDay, sMounth, sYear;
    //private int chis;
    private int dayOfWeek;
    private int typeOfWeek;


     Time(){}

     Time(int start_day, int start_mounth, int start_year){
        this.sDay = start_day;
        this.sMounth = start_mounth;
        this.sYear = start_year;

        Calendar c = new GregorianCalendar(this.sYear, this.sMounth, this.sDay) ;
        this.sNumOfWeek = c.get(Calendar.WEEK_OF_YEAR);

        int chis = this.sNumOfWeek%2;

        Calendar c_now = new GregorianCalendar();

        this.numOfWeek = c_now.get(Calendar.WEEK_OF_YEAR);
        this.dayOfWeek = c_now.get(Calendar.DAY_OF_WEEK);

        int a = this.numOfWeek%2;

        if(a==chis)
        {
            typeOfWeek = 0;
        }
        else
        {
            typeOfWeek = 1;
        }

    }

    int getDayOfWeek(){
        return dayOfWeek;
    }

    int getTypeOfWeek(){
        return typeOfWeek;
    }

    public int main(){
        String datanow = ""; // считать дату
        String datastart = "12.03.2018";


        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

        Date date1 = new Date();
        Date date2 = null;

        String date12 = format.format(date1);
        try {
            date2 = format.parse(datastart);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long difference = date1.getTime() - date2.getTime();
        int days = (int)(difference/(24*60*60*1000));
        return days;

        // если больше семи то сменить на числитель/знаменатель
    }

}
