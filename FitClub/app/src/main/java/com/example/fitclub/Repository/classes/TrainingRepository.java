package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;

public class TrainingRepository implements ITrainingsRepository {

    //Тренировки на день
    MutableLiveData<List<Training>> mTrainings = null;

    //Тренировки пользователя
    MutableLiveData<Training> mSelectedTraining = null;

    //Признак записи пользователя
    MutableLiveData<Boolean> mIswriting = null;

    //контекст активити
    Context mCurrContext;

    @Override
    public MutableLiveData<Training> initializeTrainingInfo(Context context) {
        mCurrContext = context;
        mSelectedTraining = new MutableLiveData<>();
        return mSelectedTraining;
    }


    public MutableLiveData<Boolean> initializeIsWriting(Context context)
    {
        mCurrContext = context;
        mIswriting = new MutableLiveData<>();
        return mIswriting;
    }

    public MutableLiveData<List<Training>> initializeTrainingList(Context context)
    {
        mCurrContext = context;
        mTrainings = new MutableLiveData<>();
        return mTrainings;
    }


    //Получить тренировки на день
    @Override
    public void getTrainings(Date date) {
        try {
             new GetTrainingsAsyncTask().execute(date).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTrainingInfo(Integer Id, Date date) {
        try {
             new GetTrainingInfoAsyncTask().execute(Id,date).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setContext(Context currContext) {
        mCurrContext = currContext;
    }

    @Override
    public void createRegistrationOnTraining(Integer userId, Integer trainingId, Date startTime, AlertDialog progressDlg) {
        try {
            new CreateRegistrationOnTrainingAsyncTask().execute(userId,trainingId,startTime,progressDlg).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bIsAlereadyWriting(Integer userId, Integer trainingId) {
        try {
            new CheckWritingAsyncTask().execute(userId,trainingId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private class GetTrainingsAsyncTask extends AsyncTask<Date, Void, Void> {
        @Override
        protected Void doInBackground(Date... dates) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            api.getTrainings(dates[0], mTrainings);
            return null;
        }
    }

    private class  GetTrainingInfoAsyncTask extends AsyncTask<Object,Void,Void>{
        @Override
        protected Void doInBackground(Object... datas) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            api.getTrainingInfo((Integer) datas[0],(Date)datas[1], mSelectedTraining);
            return null;
        }
    }

    private class CheckWritingAsyncTask extends AsyncTask<Integer,Void, Void>
    {
        @Override
        protected Void doInBackground(Integer... datas) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            api.checkWriting((Integer)datas[0],(Integer)datas[1],mIswriting);
           return null;
        }
    }

    private class CreateRegistrationOnTrainingAsyncTask extends AsyncTask<Object,Void,Void>
    {
        @Override
        protected Void doInBackground(Object... datas) {
            RetrofitAPI api = new RetrofitAPI(mCurrContext);
            api.CreateRegistrationOnTraining((Integer)datas[0],(Integer)datas[1],(Date)datas[2],(AlertDialog)datas[3], mSelectedTraining);
            return null;
        }
    }
}
