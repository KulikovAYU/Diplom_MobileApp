package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ICoachRepository;
import com.example.fitclub.Repository.Interfaces.IRepository;
import com.example.fitclub.Repository.Interfaces.ITrainingClientRepository;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.Repository;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SelectedTrainingViewModel extends AndroidViewModel {

    private ITrainingsRepository mTrainingsRepository;
    private ICoachRepository mCoachRepository;
    private ITrainingClientRepository mTrainingClientRepository;

    public SelectedTrainingViewModel(@NonNull Application application) {
        super(application);

        //получим наш репозиторий тренировок
        IRepository repo = new Repository();

        mTrainingsRepository = repo.getTrainingRepository();
        mCoachRepository = repo.getCoachRepository();
        mTrainingClientRepository = repo.getTrainingClientRepository();
    }

    public void SetContext(Context context) {
        mTrainingsRepository.setContext(context);//получим наш контекст
    }

    public void setImage (Integer Id, ImageView View ) {
        mCoachRepository.setPhoto(Id,View);
    }

    //получить информацию о тренировке
    public LiveData<Training> getTrainingInfo(Integer Id, Date date)
    {
        return mTrainingsRepository.getTrainingInfo(Id,date);
    }

    //проверить записался ли пользователь на тренировку
    public LiveData<Boolean> bIsAlereadyWriting(Integer userId,Integer trainingId)
    {
        //поставим пока заглушку. потом реализуем сущность пользователя
        return mTrainingClientRepository.bIsAlereadyWriting(userId,trainingId);
    }

}
