package com.example.fitclub.Retrofit2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.fitclub.Activities.TrainingInfoActivity;
import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;
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

    protected static String BASEURL = ConnectionManager.Instance().toString();

    protected JsonPlaceHolderApi mjsonPlaceHolderApi;

    protected AppCompatActivity mCurrentview; //текущий вид

    protected Retrofit mRetrofit;

    protected MutableLiveData<List<Training>> mTrainingList;

    protected MutableLiveData<Employee> mCoach;

    protected MutableLiveData<Training> mTraining;

    MutableLiveData<Boolean> mCheckingTraining;

//    protected MutableLiveData<Boolean> mCheckingTraining;

    public RetrofitAPI(Context currContext) {

        if (currContext instanceof AppCompatActivity) {
            mCurrentview = ((AppCompatActivity) currContext);
        }
    }



    //////////////////Методы доступа из Repository\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //Получить тренировки на день
    public LiveData<List<Training>> getTrainings(Date date) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);

        getTrainingsRetrofit(date);
        return mTrainingList;
    }

    //получить тренера на конкретную тренировку
    public MutableLiveData<Employee> getCoach(Training currentTraining) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getCoachRetrofit(currentTraining);
        return mCoach;
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
    public LiveData<Training> getTrainingInfo(Integer Id, Date date){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getTrainingInfoRetrofit(Id,date);
        return mTraining;
    }

    //проверить записан ли клиент на тренировку
    public LiveData<Boolean> checkWriting(Integer userId,Integer trainingId)
    {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        checkWritingRetrofit(userId,trainingId);
        return mCheckingTraining;
    }

    //оформить предварительную запись на тренировку
    public LiveData<Training> CreateRegistrationOnTraining(Integer userId, Integer trtainingId, Date startTime, AlertDialog progressDlg)
    {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        createRegistrationOnTrainingRetrofit(userId,trtainingId,startTime,progressDlg);
        return mTraining;
    }


    //////////////////Методы Retrofit\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //Получить тренировки на день (список тренировок)
    protected void getTrainingsRetrofit(Date date) {

        mTrainingList = new MutableLiveData<>();
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
    protected void getCoachRetrofit(Training currentTraining) {
        if (currentTraining == null)
            return;
        mCoach = new MutableLiveData<>();

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
    protected void getPhotoRetrofit(String role, Integer Id, final ImageView View) {

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
//                ImageView image =  ((ImageView) mCurrentview.findViewById(R.id.item_coachPhotoId));
//                image.setImageBitmap(BitmapFactory.decodeStream(response.body().byteStream()));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FailureReqouestMessage(t);
            }
        });

    }

    //получить ифнормацию о тренировке
    protected void getTrainingInfoRetrofit(Integer Id, Date date){
        mTraining = new MutableLiveData<>();

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
        mCheckingTraining = new MutableLiveData<>();
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

    private void createRegistrationOnTrainingRetrofit(Integer userId, Integer trtainingId, Date startTime, final AlertDialog progressDlg)
    {
        if (mTraining == null)
            mTraining = new MutableLiveData<>();


        Call<Training> trainingCall = mjsonPlaceHolderApi.CreateRegistrationOnTrainingRetrofit(userId,trtainingId,TimeFormatter.convertDate_y_M_d_HH_mm(startTime));
        trainingCall.enqueue(new Callback<Training>() {
            @Override
            public void onResponse(Call<Training> call, Response<Training> response) {
                progressDlg.dismiss();
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                if (response.code() == 200)
                {
                    mTraining.setValue(response.body());
                    //mTraining.notifyAll();
                }

            }

            @Override
            public void onFailure(Call<Training> call, Throwable t)  {
                FailureReqouestMessage(t);
            }

        });

    }

}
