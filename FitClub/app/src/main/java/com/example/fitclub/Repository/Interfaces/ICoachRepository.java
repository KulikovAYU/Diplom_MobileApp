package com.example.fitclub.Repository.Interfaces;

import android.content.Context;
import android.widget.ImageView;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;

import androidx.lifecycle.LiveData;

public interface ICoachRepository {

    //получить тренера,связанного с тренировкой
    LiveData<Employee> getCoach(Training currentTraining);

    //получить тренера,связанного с тренировкой
    void setPhoto(Integer Id, ImageView View);

    //устанавливает контекст
    void SetContext(Context currContext);

}
