package com.example.fitclub.Retrofit2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fitclub.Activities.TrainingInfoActivity;
import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Models.Client;
import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;
import com.example.fitclub.R;
import com.example.fitclub.utils.TimeFormatter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class RetrofitAPI {

    public RetrofitAPI(Context currContext) {

        if (currContext instanceof AppCompatActivity) {
            mCurrentview = ((AppCompatActivity) currContext);
        }
    }

    protected static String BASEURL = ConnectionManager.Instance().toString();

    protected JsonPlaceHolderApi mjsonPlaceHolderApi;

    protected AppCompatActivity mCurrentview; //текущий вид

    protected Retrofit mRetrofit;

    protected MutableLiveData<List<Training>> mTrainingList;

    protected MutableLiveData<Employee> mCoach;

    protected MutableLiveData<Training> mTraining;

    protected MutableLiveData<Boolean> mCheckingTraining;

    protected MutableLiveData<Client> mClient;

    //////////////////Методы доступа из Repository\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //Получить тренировки на день
    public void getTrainings(Date date,MutableLiveData<List<Training>> trainings) {

        mTrainingList = trainings;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);

        getTrainingsRetrofit(date);
    }

    //получить тренера на конкретную тренировку
    public void getCoach(Training currentTraining, MutableLiveData<Employee> employeeLiveData) {
        mCoach = employeeLiveData;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getCoachRetrofit(currentTraining);

    }

    //получить фото
    public void getPhoto(String role,Integer Id, ImageView View) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(new OkHttpClient())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getPhotoRetrofit(role,Id,View);
    }

    //получить информацию о тренировке
    public void getTrainingInfo(Integer Id, Date date,MutableLiveData<Training> training){

        mTraining = training;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getTrainingInfoRetrofit(Id,date);
    }

    //проверить записан ли клиент на тренировку
    public void checkWriting(Integer userId,Integer trainingId,MutableLiveData<Boolean> iswriting){

        mCheckingTraining = iswriting;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        checkWritingRetrofit(userId,trainingId);
    }

    //оформить предварительную запись на тренировку
    public void CreateRegistrationOnTraining(Integer userId, Integer trtainingId, Date startTime, AlertDialog progressDlg,MutableLiveData<Training> training){

        mTraining = training;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        createRegistrationOnTrainingRetrofit(userId,trtainingId,startTime,progressDlg);
    }

    //получить список тренировок, на который записан пользователь
    public void getMyTrainings(Integer userId,MutableLiveData<List<Training>> training) {
        mTrainingList = training;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);

        getMyTrainingsRetrofit(userId);
    }


    public void getClientInfo(Integer clientId, MutableLiveData<Client> client) {
        mClient = client;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);

        getClientInfoRetrofit(clientId);
    }




    //////////////////Методы Retrofit\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //Получить тренировки на день (список тренировок)
    private void getTrainingsRetrofit(Date date) {

        Call<List<Training>> call = mjsonPlaceHolderApi.getTrainingsRetrofit(TimeFormatter.convertDate_y_M_d(date));

        call.enqueue(new Callback<List<Training>>() {
            @Override
            public void onResponse(Call<List<Training>> call, Response<List<Training>> response) {

                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
//                if (response.code() == 403)
//                {неверный логин и пароьл}

                mTrainingList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Training>> call, Throwable t) {

                FailureReqouestMessage(t);
            }
        });
    }

    //получить информацию о тренере
    private void getCoachRetrofit(Training currentTraining) {
        if (currentTraining == null)
            return;


        Call<Employee> call = mjsonPlaceHolderApi.getCoachOnTrainingRetrofit(currentTraining);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                mCoach.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                FailureReqouestMessage(t);
            }
        });
    }

    //показать неудачный запрос
    private void FailureReqouestMessage(Throwable t) {
        if (mCurrentview != null) {
            Snackbar.make(mCurrentview.getCurrentFocus(), "Неудачный запрос: " + t.getMessage(), Snackbar.LENGTH_LONG)
                    .setAction("Неудачный запрос: " + t.getMessage(), null).show();
        }
    }

    //получить(заполнить) фото
    private void getPhotoRetrofit(String role, Integer Id, final ImageView View) {

        Call<ResponseBody> call = mjsonPlaceHolderApi.getPhotoRetrofit(role,String.valueOf(Id));

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }

                if (response.code() == 200)
                {
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    if (bmp != null)
                        View.setImageBitmap(bmp);

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FailureReqouestMessage(t);
            }
        });

    }

    //получить ифнормацию о тренировке
    private void getTrainingInfoRetrofit(Integer Id, Date date){

        Call<Training> training1Call = mjsonPlaceHolderApi.getTrainingInfoRetrofit(Id.toString(),TimeFormatter.convertDate_y_M_d_HH_mm(date));
        training1Call.enqueue(new Callback<Training>() {
            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                if (response.code() == 200)
                {
                    mTraining.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Training> call, Throwable t) {
                FailureReqouestMessage(t);
            }
        });
    }

    //проверка записи клиента на тренировку
    private void checkWritingRetrofit(Integer userId, Integer trainingId) {

        Call<Boolean> call = mjsonPlaceHolderApi.checkWriting(userId,trainingId);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                if (response.code() == 200)
                {
                    (mCheckingTraining).setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                FailureReqouestMessage(t);
            }
        });
    }

    //записаться / отменить запись на тренировку
    private void createRegistrationOnTrainingRetrofit(Integer userId, Integer trtainingId, Date startTime, final AlertDialog progressDlg){
        Call<Training> trainingCall = mjsonPlaceHolderApi.CreateRegistrationOnTrainingRetrofit(userId,trtainingId,TimeFormatter.convertDate_y_M_d_HH_mm(startTime));
        trainingCall.enqueue(new Callback<Training>() {
            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {

                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    {
                        progressDlg.dismiss();
                        return;
                    }
                }

                //в случае записи на тренировку
                if (response.code() == 201)
                {
                    mTraining.setValue(response.body());

                    Snackbar.make(mCurrentview.getCurrentFocus(), "Вы успешно записаны", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                } else if (response.code() == 202) //в случае отмены записи
                {
                    mTraining.setValue(response.body());
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Запись отменена", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                progressDlg.dismiss();
            }

            @Override
            public void onFailure(Call<Training> call, Throwable t)  {
                progressDlg.dismiss();
                FailureReqouestMessage(t);
            }
        });
    }

    //получить список тренировок, на которые записан клиент
    private void getMyTrainingsRetrofit(Integer userId){

        Call<List<Training>> myTrainingCall = mjsonPlaceHolderApi.getMyTrainingsRetrofit(userId);

        myTrainingCall.enqueue(new Callback<List<Training>>() {
            @Override
            public void onResponse(Call<List<Training>> call, Response<List<Training>> response) {
                if (!response.isSuccessful() && mCurrentview != null)
                {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                if (response.code() == 200)
                {
                    mTrainingList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Training>> call, Throwable t) {
                    FailureReqouestMessage(t);
            }
        });
    }

    private void getClientInfoRetrofit(Integer clientId) {
        Call<Client> clientCall = mjsonPlaceHolderApi.getClientDataRetrofit(clientId);

        clientCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (!response.isSuccessful() && mCurrentview != null)
                {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                if (response.code() == 200)
                {
                    mClient.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                FailureReqouestMessage(t);
            }
        });
    }

}
