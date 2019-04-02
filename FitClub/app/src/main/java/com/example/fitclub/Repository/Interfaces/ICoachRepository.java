package com.example.fitclub.Repository.Interfaces;

import android.content.Context;
import android.widget.ImageView;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public interface ICoachRepository {

    //инициализация информации о тренировке
    MutableLiveData<Employee> initializeCoachInfo(Context context);
    //получить тренера,связанного с тренировкой
    void getCoach(Training currentTraining);

    //получить тренера,связанного с тренировкой
    void setPhoto(Integer Id, ImageView View);



}
