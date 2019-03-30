package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.IRepository;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.Repository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class MyFavouriteTrainingListViewModel extends AndroidViewModel {

    private ITrainingsRepository mTrainingsRepository;

    public MyFavouriteTrainingListViewModel(@NonNull Application application) {
        super(application);

        //получим наш репозиторий тренировок
        IRepository repo = new Repository();
        mTrainingsRepository = repo.getTrainingRepository();
    }

    public MutableLiveData<List<Training>> initializeMyTrainingList(Context context){

        return   mTrainingsRepository.initializeMyTrainingList(context);

    }

      //получить тренировки, на которые записан пользователь
    //Id - id пользователя
    public void getMyTrainings(Integer Id) {

        mTrainingsRepository.getMyTrainings(Id);
    }

    public void setContext(Context context) {
        mTrainingsRepository.setContext(context);//получим наш контекст
    }
}
