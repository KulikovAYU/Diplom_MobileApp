package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import com.example.fitclub.Models.Training;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

//Репка для тренировок
public interface ITrainingsRepository
{
    //Получить тренировки на день
    LiveData<List<Training>> getTrainings(Date date);

    //Записаться на тренировку
    void signUpOnTraining(Integer nUserId, Training training);

    //Отменить запись на тренировку
    void removeSignUpOnTraining(Integer nUserId, Training training);

    //Получить все тренировки пользователя
    LiveData<List<Training>> getUserTrainings();

    //Получить тренировки пользователя на день
    LiveData<List<Training>> getUserTrainings(Date date);

    //получить информацию о тренировке
    LiveData<Training> getTrainingInfo(Integer Id, Date date);

    //устанавливает контекст
    void setContext(Context currContext);
}
