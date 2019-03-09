package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fitclub.Models.Coach;
import com.example.fitclub.Models.Training1;

import com.example.fitclub.Repository.Interfaces.ICoachRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;

import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;


public class CoachRepository implements ICoachRepository {

    LiveData<Coach> mCoach;
    //контекст активити
    Context mCurrContext;

    //получить тренера,связанного с тренировкой
    @Override
    public LiveData<Coach> getCoach(Training1 currentTraining) {

        try {
            mCoach = new GetCoachAsyncTask().execute(currentTraining).get();
            return mCoach;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void SetContext(Context currContext) {
        mCurrContext = currContext;
    }


    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private class GetCoachAsyncTask extends AsyncTask<Training1, Void, LiveData<Coach>>
    {
        @Override
        protected LiveData<Coach> doInBackground(Training1... trainings) {

            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            api.getCoach(trainings[0]);
            return null;
        }
    }
}
