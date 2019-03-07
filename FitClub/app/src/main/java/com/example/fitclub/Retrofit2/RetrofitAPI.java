package com.example.fitclub.Retrofit2;
import com.example.fitclub.Connection.ConnectionManager;

import android.content.Context;
import android.view.View;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Models.Training1;
import com.example.fitclub.R;
import com.example.fitclub.utils.TimeFormatter;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitAPI {


   // static String BASEURL = "http://192.168.0.19:56073/api/"; //сюда ввести URL //для genymotion - 10.0.3.2// для android studio - 10.0.2.2 //192.168.56.1
   static String BASEURL = ConnectionManager.Instance().toString();

   JsonPlaceHolderApi mjsonPlaceHolderApi;

    View mCurrentview; //текущий вид


    public RetrofitAPI(Context currContext)
    {

        if (currContext instanceof AppCompatActivity)
        {
            mCurrentview = ((AppCompatActivity)currContext).findViewById(R.id.fragment_container);
//            AppCompatActivity mCurrContext = ((AppCompatActivity)currContext);
//            mCurrentview = mCurrContext.getCurrentFocus();

        }


        Initialize();
    }


    void Initialize()
    {

        //Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        mjsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    List<Training1> mTrainingList;

    //Получить тренировки на день
   public List<Training1> getTrainings(Date date)
    {

        Call<List<Training1>> call = mjsonPlaceHolderApi.getTrainings( TimeFormatter.convertDate_y_M_d(date));

        call.enqueue(new Callback<List<Training1>>() {
            @Override
            public void onResponse(Call<List<Training1>> call, Response<List<Training1>> response) {

                if (!response.isSuccessful() && mCurrentview != null){
                    Snackbar.make(mCurrentview, "Code: " + response.code(), Snackbar.LENGTH_LONG)
                            .setAction("Code: " + response.code(), null).show();
                    return;
                }
//                if (response.code() == 403)
//                {неверный логин и пароьл}

                mTrainingList = response.body();
            }

            @Override
            public void onFailure(Call<List<Training1>> call, Throwable t) {

               if (mCurrentview != null)
               {
                   Snackbar.make(mCurrentview, "Неудачный запрос: " + t.getMessage(), Snackbar.LENGTH_LONG)
                           .setAction("Неудачный запрос: " + t.getMessage(), null).show();
               }

            }
        });

        return mTrainingList;
    }
}
