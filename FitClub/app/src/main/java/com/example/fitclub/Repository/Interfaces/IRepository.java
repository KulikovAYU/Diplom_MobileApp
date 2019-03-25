package com.example.fitclub.Repository.Interfaces;

//интерфейс главного репозитория

public interface IRepository {

    //получить репозиторий для работы с тренировками
    ITrainingsRepository getTrainingRepository();

    //получить репозиторий для работы с тренерами
    ICoachRepository getCoachRepository();

    //получить репозиторий с предварительными записями клиентов на тренировку
    ITrainingClientRepository getTrainingClientRepository();

}



