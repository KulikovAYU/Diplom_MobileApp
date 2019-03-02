package com.example.fitclub.Repository;

import com.example.fitclub.Repository.Interfaces.IRepository;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.classes.TrainingRepository;

//главный репозиторий, через который можно получить другие репозитории
public class Repository implements IRepository {

    public Repository()
    {
        //создаем наши репозитории
        mTrainingRepository = new TrainingRepository();
    }
    ITrainingsRepository mTrainingRepository;

    @Override
    public ITrainingsRepository GetTrainingRepository() {
        return mTrainingRepository;
    }


}
