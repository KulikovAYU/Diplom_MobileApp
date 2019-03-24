package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ICoachRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;

import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;


public class CoachRepository implements ICoachRepository {

    LiveData<Employee> mCoach;
    //контекст активити
    Context mCurrContext;

    //получить тренера,связанного с тренировкой
    @Override
    public LiveData<Employee> getCoach(Training currentTraining) {

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

    public void setPhoto(Integer Id, ImageView View)
    {
        try {

            new GetPhotoAsyncTask().execute("employees",Id, View).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SetContext(Context currContext) {
        mCurrContext = currContext;
    }


    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private class GetCoachAsyncTask extends AsyncTask<Training, Void, LiveData<Employee>>
    {
        @Override
        protected LiveData<Employee> doInBackground(Training... trainings) {

            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            LiveData<Employee> coach = api.getCoach(trainings[0]);
            return coach;
        }
    }


    private class GetPhotoAsyncTask extends AsyncTask<Object, Void, Void>
    {
        @Override
        protected Void doInBackground(Object... values) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);

            api.getPhoto((String) values[0],(Integer)values[1],(ImageView)values[2]);
            return null;
//            LiveData<Employee> coach =
//            return coach;
        }
    }
}
