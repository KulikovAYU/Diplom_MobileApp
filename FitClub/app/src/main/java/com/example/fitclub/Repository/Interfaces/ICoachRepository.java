package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import com.example.fitclub.Models.Coach;
import com.example.fitclub.Models.Training1;

import androidx.lifecycle.LiveData;

public interface ICoachRepository {

    //получить тренера,связанного с тренировкой
    LiveData<Coach> getCoach(Training1 currentTraining);

    //устанавливает контекст
    void SetContext(Context currContext);

}
