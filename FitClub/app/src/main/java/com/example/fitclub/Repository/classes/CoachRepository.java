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
import androidx.lifecycle.MutableLiveData;


public class CoachRepository implements ICoachRepository {

    MutableLiveData<Employee> mCoach;
    //контекст активити
    Context mCurrContext;

    //получить тренера,связанного с тренировкой
    @Override
    public void getCoach(Training currentTraining) {

        try {
            new GetCoachAsyncTask().execute(currentTraining).get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Override
    public MutableLiveData<Employee> initializeCoachInfo(Context context) {
        mCurrContext = context;
        mCoach = new MutableLiveData<>();
        return mCoach;
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




    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private class GetCoachAsyncTask extends AsyncTask<Training, Void, LiveData<Employee>>
    {
        @Override
        protected LiveData<Employee> doInBackground(Training... trainings) {

            RetrofitAPI api = new RetrofitAPI(mCurrContext);
             api.getCoach(trainings[0],mCoach);
            return null;
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
