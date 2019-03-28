package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ICoachRepository;
import com.example.fitclub.Repository.Interfaces.IRepository;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Repository.Repository;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class SelectedTrainingViewModel extends AndroidViewModel {

    private ITrainingsRepository mTrainingsRepository;
    private ICoachRepository mCoachRepository;

    public SelectedTrainingViewModel(@NonNull Application application) {
        super(application);

        //получим наш репозиторий тренировок
        IRepository repo = new Repository();
        mTrainingsRepository = repo.getTrainingRepository();
        mCoachRepository = repo.getCoachRepository();
    }


    public MutableLiveData<Training> initializeTrainingInfo(Context context){

      return   mTrainingsRepository.initializeTrainingInfo(context);

    }

    public MutableLiveData<Boolean> initializeIsWriting(Context context)
    {
        return   mTrainingsRepository.initializeIsWriting(context);
    }

    public void setImage (Integer Id, ImageView View ) {
        mCoachRepository.setPhoto(Id,View);
    }

    //получить информацию о тренировке
    public void getTrainingInfo(Integer Id, Date date){

        mTrainingsRepository.getTrainingInfo(Id,date);

    }

    //проверить записался ли пользователь на тренировку
    public void bIsAlereadyWriting(Integer userId,Integer trainingId){

        mTrainingsRepository.bIsAlereadyWriting(userId,trainingId);

    }
    //зарегистрироваться или отменить регистрацию
    public void createRegistrationOnTraining(Integer userId, Integer trainingId, Date startTime, AlertDialog progressDlg) {

        mTrainingsRepository.createRegistrationOnTraining(userId,trainingId,startTime,progressDlg);

    }
}
