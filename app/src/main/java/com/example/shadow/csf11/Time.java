package com.example.shadow.csf11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shadow on 15.03.2018.
 */

public class Time {
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
