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



//    private LiveData<List<Training>> mUserTrainings;

    public TrainingListViewModel(@NonNull Application application) {
        super(application);

        //получим наш репозиторий тренировок
        mTrainingsRepository = new Repository().getTrainingRepository();
    }


//    public LiveData<List<Training>> getTrainings(Date date)
//    {
//        return mTrainingsRepository.getTrainings(date);
//    }

    //Получить тренировки на день
    public LiveData<List<Training>> initializeTrainingList(Context context) {
        return mTrainingsRepository.initializeTrainingList(context);
    }

    public void getTrainings(Date date)
    {
        mTrainingsRepository.getTrainings(date);
    }

    //Записаться на тренировку
    void SignUpOnTraining(Integer nUserId, Training training) {
        mTrainingsRepository.signUpOnTraining(nUserId, training);
    }

    //Отменить запись на тренировку
    void RemoveSignUpOnTraining(Integer nUserId, Training training) {
        mTrainingsRepository.removeSignUpOnTraining(nUserId, training);
    }

    //Получить все тренировки пользователя
//    LiveData<List<Training>> getUserTrainings() {
//        mUserTrainings = mTrainingsRepository.getUserTrainings();
//        return mUserTrainings;
//    }

    //Получить тренировки пользователя на день
    LiveData<List<Training>> GetUserTrainings(Date date) {
        return mTrainingsRepository.getUserTrainings(date);
    }
}
