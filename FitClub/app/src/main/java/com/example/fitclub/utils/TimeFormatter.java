package com.example.fitclub.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//класс для работы с датами
public class TimeFormatter {


    public static String convertTimeHHmm(Date inputDate) {
        if (inputDate == null) {
            // throw new RuntimeException("inputDate is null");
            return new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(inputDate);
    }

    //формат даты г-м-д 2012-12-31
    public static String convertDate_y_M_d(Date inputDate) {
        if (inputDate == null) {
            // throw new RuntimeException("inputDate is null");
            return new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(inputDate);
    }


    public static String convertTimeEEEMMMd(Date inputDate) {
        if (inputDate == null) {
            //throw new RuntimeException("inputDate is null");
            return new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d.");

        return sdf.format(inputDate);
    }
}
