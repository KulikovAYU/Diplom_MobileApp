package com.example.fitclub.utils;

import android.view.View;

import com.example.fitclub.R;

import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;

public class TrainingCalendar {

    static TrainingCalendar mCalendar;
    static HorizontalCalendar mHorizontalCalendar;

    public static TrainingCalendar getInstance(View view)
    {
      return (mCalendar == null) ? new TrainingCalendar(view) : mCalendar;
    }

    public HorizontalCalendar getHorizontalCalendar()
    {
        return mHorizontalCalendar;
    }
    private TrainingCalendar(View view)
    {
        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        if (mHorizontalCalendar == null)
        {
            mHorizontalCalendar = new HorizontalCalendar.Builder(view.findViewById(R.id.fragment_root_training_list), R.id.calendarView).range(startDate, endDate)
                    .datesNumberOnScreen(5)
                    .build();
        }


    }
}
