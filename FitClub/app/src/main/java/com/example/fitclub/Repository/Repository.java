package com.example.fitclub.Repository;

import com.example.fitclub.Repository.Interfaces.IClientRepository;
import com.example.fitclub.Repository.Interfaces.ICoachRepository;
import com.example.fitclub.Repository.Interfaces.IRepository;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.classes.ClientRepository;
import com.example.fitclub.Repository.classes.CoachRepository;
import com.example.fitclub.Repository.classes.TrainingRepository;

//главный репозиторий, через который можно получить другие репозитории
public class Repository implements IRepository {

    public Repository()
    {
        //создаем наши репозитории
        mTrainingRepository = new TrainingRepository();
        mCoachRepository = new CoachRepository();
        mClientRepository = new ClientRepository();

    }
    ITrainingsRepository mTrainingRepository;
    ICoachRepository mCoachRepository;
    IClientRepository mClientRepository;

    @Override
    public ITrainingsRepository getTrainingRepository() {
        return mTrainingRepository;
    }

    @Override
    public ICoachRepository getCoachRepository() {
        return mCoachRepository;
    }

    @Override
    public IClientRepository getClientRepository() {
        return mClientRepository;
    }


}
