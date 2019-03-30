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

    //формат даты г-м-д 2012-12-31 12:30
    public static String convertDate_y_M_d_HH_mm(Date inputDate) {
        if (inputDate == null) {
            // throw new RuntimeException("inputDate is null");
            return new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        return sdf.format(inputDate);
    }

    //формат даты: чт, марта 28.
    public static String convertTimeEEEMMMd(Date inputDate) {
        if (inputDate == null) {
            //throw new RuntimeException("inputDate is null");
            return new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d");

        return sdf.format(inputDate);
    }

    //формат даты: чт,28 марта .
    public static String convertTimeEEEdMMM(Date inputDate) {
        if (inputDate == null) {
            //throw new RuntimeException("inputDate is null");
            return new String();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM");

        return sdf.format(inputDate);
    }

}
