package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fitclub.Repository.Interfaces.ITrainingClientRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;

import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

public class TrainingClientRepository implements ITrainingClientRepository {

    private Context mCurrContext;

    //проверка записи на тренировку
    @Override
    public  LiveData<Boolean> bIsAlereadyWriting(Integer userId,Integer trainingId) {

        try {
            return new CheckWritingAsyncTask().execute(userId,trainingId).get();
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

    private class CheckWritingAsyncTask extends AsyncTask<Integer,Void, LiveData<Boolean>>
    {

        @Override
        protected LiveData<Boolean> doInBackground(Integer... datas) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            LiveData<Boolean> bIsWriting = api.checkWriting((Integer)datas[0],(Integer)datas[1]);
            return bIsWriting;
        }
    }

}
