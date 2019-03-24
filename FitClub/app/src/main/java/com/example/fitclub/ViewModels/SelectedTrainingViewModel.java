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
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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
}
