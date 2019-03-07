package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Models.Training1;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.Repository;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TrainingViewModel extends AndroidViewModel {

    private ITrainingsRepository mTrainingsRepository;

    private LiveData<List<Training1>> mTrainingsOnDay;

    private LiveData<List<Training>> mUserTrainings;

    public TrainingViewModel(@NonNull Application application) {
        super(application);

        //получим наш репозиторий тренировок
        mTrainingsRepository = new Repository().GetTrainingRepository();
    }

    public void SetFragment(Context context)
    {
        mTrainingsRepository.SetContext(context);//получим наш контекст
    }
    //Получить тренировки на день
    public LiveData<List<Training1>> GetTrainings(Date date) {

        mTrainingsOnDay = mTrainingsRepository.GetTrainings(date);
        return mTrainingsOnDay;
    }

    //Записаться на тренировку
    void SignUpOnTraining(Integer nUserId, Training training) {
        mTrainingsRepository.SignUpOnTraining(nUserId, training);
    }

    //Отменить запись на тренировку
    void RemoveSignUpOnTraining(Integer nUserId, Training training) {
        mTrainingsRepository.RemoveSignUpOnTraining(nUserId, training);
    }

    //Получить все тренировки пользователя
    LiveData<List<Training>> GetUserTrainings() {
        mUserTrainings = mTrainingsRepository.GetUserTrainings();
        return mUserTrainings;
    }

    //Получить тренировки пользователя на день
    LiveData<List<Training>> GetUserTrainings(Date date) {
        return mTrainingsRepository.GetUserTrainings(date);
    }
}
