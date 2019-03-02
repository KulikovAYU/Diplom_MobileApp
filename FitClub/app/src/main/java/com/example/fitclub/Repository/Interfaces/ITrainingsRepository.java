package com.example.fitclub.Repository.Interfaces;

import com.example.fitclub.Models.Training;

import java.util.Date;
import java.util.List;

import androidx.lifecycle.LiveData;

//Репка для тренировок
public interface ITrainingsRepository
{
    //Получить тренировки на день
    LiveData<List<Training>> GetTrainings(Date date);

    //Записаться на тренировку
    void SignUpOnTraining(Integer nUserId,Training training);

    //Отменить запись на тренировку
    void RemoveSignUpOnTraining(Integer nUserId,Training training);

    //Получить все тренировки пользователя
    LiveData<List<Training>> GetUserTrainings();

    //Получить тренировки пользователя на день
    LiveData<List<Training>> GetUserTrainings(Date date);

}
