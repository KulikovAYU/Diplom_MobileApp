package com.example.fitclub.Retrofit2;

import com.example.fitclub.Models.Training;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    //Получить тренировки на день
//    @GET("trainings/{date}") //trainings - в серверной части
//    Call<List<Training>> getTrainings(@Path("date") Date date);

//    @GET("trainings/{date}") //trainings - в серверной части
@GET("trainings")
Call<List<Training>> getTrainings();

    //Записаться на тренировку
    @GET("trainings")
    void SignUpOnTraining(@Query("nUserId") Integer nUserId,
                          @Query("training") Training training);
//
//    //Отменить запись на тренировку
    @GET("trainings")
   void RemoveSignUpOnTraining(@Query("nUserId")Integer nUserId,
                               @Query("training")Training training);
//
//    //Получить все тренировки пользователя
//    List<Training> GetUserTrainings();
//
//    //Получить тренировки пользователя на день
//    List<Training> GetUserTrainings(Date date);
}
