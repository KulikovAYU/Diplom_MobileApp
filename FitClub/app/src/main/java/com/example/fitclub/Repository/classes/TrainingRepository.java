package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import androidx.lifecycle.LiveData;

public class TrainingRepository implements ITrainingsRepository {

    //Тренировки на день
    LiveData<List<Training>> mTrainings = null;

    //Тренировки пользователя
    LiveData<List<Training>> mUserTrainings = null;

    //контекст активити
    Context mCurrContext;

    //Получить тренировки на день
    @Override
    public LiveData<List<Training>> getTrainings(Date date) {
        try {
            mTrainings = new GetTrainingsAsyncTask().execute(date).get();
            return mTrainings;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void signUpOnTraining(Integer nUserId, Training training) {
        try {
            new SignUpOnTrainingAsyncTask().execute(nUserId, training).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeSignUpOnTraining(Integer nUserId, Training training) {
        try {
            new RemoveSignUpOnTrainingAsyncTask().execute(nUserId, training).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LiveData<List<Training>> getUserTrainings() {

        try {
            mUserTrainings = new GetUserTrainingsAsyncTask().execute().get();
            return mUserTrainings;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LiveData<List<Training>> getUserTrainings(Date date) {
        try {
            return new GetUserTrainingsOnDayAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LiveData<Training> getTrainingInfo(Integer Id, Date date) {
        try {
            return new GetTrainingInfoAsyncTask().execute(Id,date).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void setContext(Context currContext) {
        mCurrContext = currContext;
    }


    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private class GetTrainingsAsyncTask extends AsyncTask<Date, Void, LiveData<List<Training>>> {
        @Override
        protected LiveData<List<Training>> doInBackground(Date... dates) {

            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            LiveData<List<Training>> data_res =  api.getTrainings(dates[0]);
            return data_res;
        }
    }

    private class  GetTrainingInfoAsyncTask extends AsyncTask<Object,Void,LiveData<Training>>{

        @Override
        protected LiveData<Training> doInBackground(Object... datas) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            LiveData<Training> training = api.getTrainingInfo((Integer) datas[0],(Date)datas[1]);
            return training;
        }
    }

    private static class SignUpOnTrainingAsyncTask extends AsyncTask<Object, Void, LiveData<Training>> {

        @Override
        protected LiveData<Training> doInBackground(Object... voids) {

            if ((voids[0] instanceof Integer) && (voids[1] instanceof Training)) {
                //получить Id
                //получить тренировку
                //Запустить ретрофит
            }

           return null;
        }
    }

    private static class RemoveSignUpOnTrainingAsyncTask extends AsyncTask<Object, Void, LiveData<Training>> {

        @Override
        protected LiveData<Training> doInBackground(Object... voids) {

            if (voids[0] instanceof Integer) {
                //получить Id
            }
            if (voids[1] instanceof Training) {
                //получить тренировку
            }

            //Запустить ретрофит
            return null;
        }
    }


    private static class GetUserTrainingsAsyncTask extends AsyncTask<Void, Void, LiveData<List<Training>>> {

        @Override
        protected LiveData<List<Training>> doInBackground(Void... voids) {

            //Запустить ретрофит
            return null;
        }
    }


    private static class GetUserTrainingsOnDayAsyncTask extends AsyncTask<Date, Void, LiveData<List<Training>>> {

        @Override
        protected LiveData<List<Training>> doInBackground(Date... voids) {

            //Запустить ретрофит
            return null;
        }
    }


}
