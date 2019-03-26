package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import com.example.fitclub.Models.Training;

import java.util.Date;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;

public interface ITrainingClientRepository {
    //проверить запись
    LiveData<Boolean> bIsAlereadyWriting(Integer userId,Integer trainingId);

    void setContext(Context currContext);

    //записаться на тренировку
    LiveData<Training> createRegistrationOnTraining(Integer userId, Integer trainingId, Date startTime, AlertDialog progressDlg);
}
