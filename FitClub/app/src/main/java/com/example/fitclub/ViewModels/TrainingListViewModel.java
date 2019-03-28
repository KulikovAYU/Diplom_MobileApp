package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.Repository;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TrainingListViewModel extends AndroidViewModel {

    private ITrainingsRepository mTrainingsRepository;


    public TrainingListViewModel(@NonNull Application application) {
        super(application);

        //получим наш репозиторий тренировок
        mTrainingsRepository = new Repository().getTrainingRepository();
    }

    //Получить тренировки на день
    public LiveData<List<Training>> initializeTrainingList(Context context) {

        return mTrainingsRepository.initializeTrainingList(context);

    }

    public void getTrainings(Date date){

        mTrainingsRepository.getTrainings(date);

    }

}
