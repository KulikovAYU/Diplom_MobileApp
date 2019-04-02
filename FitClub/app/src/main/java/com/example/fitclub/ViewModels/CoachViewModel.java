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
import androidx.lifecycle.MutableLiveData;

public class CoachViewModel extends AndroidViewModel {

    private ICoachRepository mCoachRepository;

    public CoachViewModel(@NonNull Application application) {
        super(application);
        mCoachRepository = new Repository().getCoachRepository();
    }

    public MutableLiveData<Employee> initializeCoachInfo(Context context)
    {
        return mCoachRepository.initializeCoachInfo(context);
    }


    public void getCoach(Training currentTraining) {
        mCoachRepository.getCoach(currentTraining);
    }

    public void setImage (int Id, ImageView View ) {
        mCoachRepository.setPhoto(Id,View);
    }

}
