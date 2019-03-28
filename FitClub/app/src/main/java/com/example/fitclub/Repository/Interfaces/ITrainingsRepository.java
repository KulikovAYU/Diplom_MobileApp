package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import com.example.fitclub.Models.Training;

import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

//Репка для тренировок
public interface ITrainingsRepository
{
    //инициализация информации о тренировке
    MutableLiveData<Training> initializeTrainingInfo(Context context);

    //инициализация признака записи
    MutableLiveData<Boolean> initializeIsWriting(Context context);

    //инициализация для получения списка тренировок на день
    MutableLiveData<List<Training>> initializeTrainingList(Context context);

    //Получить тренировки на день
    void getTrainings(Date date);

    //получить информацию о тренировке
    void getTrainingInfo(Integer Id, Date date);

    //устанавливает контекст
    void setContext(Context currContext);

    //проверить запись
    void bIsAlereadyWriting(Integer userId,Integer trainingId);

    //записаться на тренировку
    void createRegistrationOnTraining(Integer userId, Integer trainingId, Date startTime, AlertDialog progressDlg);
}
