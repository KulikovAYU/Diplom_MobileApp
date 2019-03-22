package com.example.fitclub.Retrofit2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Models.Coach;
import com.example.fitclub.Models.Training1;
import com.example.fitclub.R;
import com.example.fitclub.utils.TimeFormatter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.List;

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

    protected MutableLiveData<List<Training1>> mTrainingList;

    protected MutableLiveData<Coach> mCoach;

    public RetrofitAPI(Context currContext) {

        if (currContext instanceof AppCompatActivity) {
            mCurrentview = ((AppCompatActivity) currContext);
        }
    }

    //////////////////Методы доступа из Repository\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //Получить тренировки на день
    public LiveData<List<Training1>> getTrainings(Date date) {
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
    public MutableLiveData<Coach> getCoach(Training1 currentTraining) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getCoachRetrofit(currentTraining);
        return mCoach;
    }

    //получить тренера на конкретную тренировку
    public void getPhoto(String trainerId) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(new OkHttpClient())
                .build();
        mjsonPlaceHolderApi = mRetrofit.create(JsonPlaceHolderApi.class);
        getPhotoRetrofit(trainerId);

    }

    //////////////////Методы Retrofit\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    //Получить тренировки на день
    protected void getTrainingsRetrofit(Date date) {

        mTrainingList = new MutableLiveData<>();
        Call<List<Training1>> call = mjsonPlaceHolderApi.getTrainingsRetrofit(TimeFormatter.convertDate_y_M_d(date));

        call.enqueue(new Callback<List<Training1>>() {
            @Override
            public void onResponse(Call<List<Training1>> call, Response<List<Training1>> response) {

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
            public void onFailure(Call<List<Training1>> call, Throwable t) {

                if (mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Неудачный запрос: " + t.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Неудачный запрос: " + t.getMessage(), null).show();
                }
            }
        });
    }


    protected void getCoachRetrofit(Training1 currentTraining) {
        if (currentTraining == null)
            return;
        mCoach = new MutableLiveData<>();

        Call<Coach> call = mjsonPlaceHolderApi.getCoachOnTrainingRetrofit(currentTraining);

        call.enqueue(new Callback<Coach>() {
            @Override
            public void onResponse(Call<Coach> call, Response<Coach> response) {
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                mCoach.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Coach> call, Throwable t) {
                if (mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Неудачный запрос: " + t.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Неудачный запрос: " + t.getMessage(), null).show();
                }
            }
        });
    }

    protected void getPhotoRetrofit(String trainerId) {
//        if (currentTraining == null)
//            return;


        Call<ResponseBody> call = mjsonPlaceHolderApi.getCoachPhotoRetrofit(trainerId);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful() && mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
                writeResponseBodyToDisk(response.body());

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (mCurrentview != null) {
                    Snackbar.make(mCurrentview.getCurrentFocus(), "Неудачный запрос: " + t.getMessage(), Snackbar.LENGTH_LONG)
                            .setAction("Неудачный запрос: " + t.getMessage(), null).show();
                }
            }
        });

    }


    private boolean writeResponseBodyToDisk(ResponseBody body) {

       Bitmap bmp = BitmapFactory.decodeStream(body.byteStream());
        ((ImageView) mCurrentview.findViewById(R.id.item_coachPhotoId)).setImageBitmap(bmp);
//       if (bmp != null)
//         Picasso.with(mCurrentview).load("http://localhost:56073/api/Employees/getcoachPhoto/1").into((ImageView) mCurrentview.findViewById(R.id.item_coachPhotoId));
return true;
    }


}
