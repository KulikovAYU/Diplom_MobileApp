package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ICoachRepository;
import com.example.fitclub.Repository.Repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CoachViewModel extends AndroidViewModel {

    private ICoachRepository mCoachRepository;

    public CoachViewModel(@NonNull Application application) {
        super(application);
        mCoachRepository = new Repository().getCoachRepository();
    }

    public void SetContext(Context context) {
        mCoachRepository.SetContext(context);//получим наш контекст
    }

    public LiveData<Employee> getCoach(Training currentTraining) {
        return mCoachRepository.getCoach(currentTraining);
    }

    public void setImage (int Id, ImageView View ) {
        mCoachRepository.setPhoto(Id,View);
    }

}
