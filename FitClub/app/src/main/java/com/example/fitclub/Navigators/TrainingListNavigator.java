package com.example.fitclub.Navigators;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fitclub.Activities.CoachInfoActivity;
import com.example.fitclub.Activities.TrainingInfoActivity;
import com.example.fitclub.Models.Training1;

import androidx.appcompat.app.AppCompatActivity;


public class TrainingListNavigator{


    AppCompatActivity mActivity;

    private TrainingListNavigator(Context context)
    {
      if (context instanceof  AppCompatActivity)
          mActivity = (AppCompatActivity)context;
    }

    //создает навигатор для перехода по тренировкам
    public static  TrainingListNavigator createInstance(Context context) {
        return new TrainingListNavigator(context);
    }


    public void GoToTrainingInfo(Training1 selectedTraining) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("selected_training",selectedTraining);
        mActivity.startActivity(new Intent(mActivity, TrainingInfoActivity.class).putExtra("ItemSelected_training", bundle));
    }

    public void GoToCoachInfo(Training1 selectedTraining) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("selected_training_coach",selectedTraining);
        mActivity.startActivity(new Intent(mActivity, CoachInfoActivity.class).putExtra("ItemSelected_training_coach", bundle));
    }
}
