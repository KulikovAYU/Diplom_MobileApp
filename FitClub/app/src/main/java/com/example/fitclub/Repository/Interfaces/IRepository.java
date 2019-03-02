package com.example.fitclub.Repository.Interfaces;

//интерфейс главного репозитория

public interface IRepository {

    //получить репозиторий для работы с тренировками
    ITrainingsRepository GetTrainingRepository();

}



