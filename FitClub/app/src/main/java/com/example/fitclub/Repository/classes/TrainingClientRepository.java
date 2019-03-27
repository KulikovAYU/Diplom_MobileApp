//package com.example.fitclub.Repository.classes;
//
//import android.content.Context;
//import android.os.AsyncTask;
//
//import com.example.fitclub.Models.Training;
//import com.example.fitclub.Repository.Interfaces.ITrainingClientRepository;
//import com.example.fitclub.Retrofit2.RetrofitAPI;
//
//import java.util.Date;
//import java.util.concurrent.ExecutionException;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.lifecycle.LiveData;
//
//public class TrainingClientRepository implements ITrainingClientRepository {
//
//    private Context mCurrContext;
//
//    //проверка записи на тренировку
//    @Override
//    public  LiveData<Boolean> bIsAlereadyWriting(Integer userId,Integer trainingId) {
//
//        try {
//            return new CheckWritingAsyncTask().execute(userId,trainingId).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public void setContext(Context currContext) {
//
//        mCurrContext = currContext;
//    }
//
//    //предварительно записаться на тренировку
//    @Override
//    public LiveData<Training> createRegistrationOnTraining(Integer userId, Integer trainingId, Date startTime, AlertDialog progressDlg) {
//
//
//        try {
//            return new CreateRegistrationOnTrainingAsyncTask().execute(userId,trainingId,startTime,progressDlg).get();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//    return null;
//    }
//
//
//
//
//    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//
//    private class CheckWritingAsyncTask extends AsyncTask<Integer,Void, LiveData<Boolean>>
//    {
//
//        @Override
//        protected LiveData<Boolean> doInBackground(Integer... datas) {
//            RetrofitAPI api = new RetrofitAPI(mCurrContext);
//            LiveData<Boolean> bIsWriting = api.checkWriting((Integer)datas[0],(Integer)datas[1]);
//            return bIsWriting;
//        }
//    }
//
//    private class CreateRegistrationOnTrainingAsyncTask extends AsyncTask<Object,Void,LiveData<Training>>
//    {
//        @Override
//        protected LiveData<Training> doInBackground(Object... datas) {
//            RetrofitAPI api = new RetrofitAPI(mCurrContext);
//            LiveData<Training> trainingLiveData = api.CreateRegistrationOnTraining((Integer)datas[0],(Integer)datas[1],(Date)datas[2],(AlertDialog)datas[3],mCurrContext);
//
//            return trainingLiveData;
//        }
//    }
//
//
//
//}
