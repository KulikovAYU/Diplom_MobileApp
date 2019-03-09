package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import com.example.fitclub.Models.Training1;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

//Репка для тренировок
public interface ITrainingsRepository
{
    //Получить тренировки на день
    LiveData<List<Training1>> GetTrainings(Date date);

    //Записаться на тренировку
    void SignUpOnTraining(Integer nUserId,Training1 training);

    //Отменить запись на тренировку
    void RemoveSignUpOnTraining(Integer nUserId,Training1 training);

    //Получить все тренировки пользователя
    LiveData<List<Training1>> GetUserTrainings();

    //Получить тренировки пользователя на день
    LiveData<List<Training1>> GetUserTrainings(Date date);

    //устанавливает контекст
    void SetContext(Context currContext);

}
