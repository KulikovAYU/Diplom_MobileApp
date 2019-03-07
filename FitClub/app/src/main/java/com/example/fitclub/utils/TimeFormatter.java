package com.example.fitclub.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//класс для работы с датами
public class TimeFormatter {


    public static String convertTimeHHmm(Date inputDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(inputDate);
    }

     //формат даты г-м-д 2012-12-31
    public static String convertDate_y_M_d(Date inputDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return sdf.format(inputDate);
    }

}
