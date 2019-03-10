package com.example.fitclub.Retrofit2;

import com.example.fitclub.Models.Coach;
import com.example.fitclub.Models.Training1;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface JsonPlaceHolderApi {

    //Получить тренировки на день
//    @GET("trainings/{date}") //trainings - в серверной части
//    Call<List<Training>> getTrainings(@Path("date") Date date);

    //    @GET("trainings/{date}") //trainings - в серверной части
    //получить список тренировок на день
    @GET("trainings/gettrainingsList/{date}")
    Call<List<Training1>> getTrainingsRetrofit(@Path("date") String date);


    //получить тренера конкретной тренировки
//    @GET("trainings")
//    Call<Coach> getCoachOnTrainingRetrofit(@QueryMap Map<String,String> parametrs);


    //получить тренера конкретной тренировки
//    @GET("trainings/{currTraining}")
//    Call<Coach> getCoachOnTrainingRetrofit(@Path("currTraining") Training1 currentTraining);

    @POST("trainings")
    Call<Coach> getCoachOnTrainingRetrofit(@Body Training1 currentTraining);

    //Записаться на тренировку
//    @GET("trainings")
//    void SignUpOnTraining(@Query("nUserId") Integer nUserId,
//                          @Query("training") Training training);
//
//    //
////    //Отменить запись на тренировку
//    @GET("trainings")
//    void RemoveSignUpOnTraining(@Query("nUserId") Integer nUserId,
//                                @Query("training") Training training);
//
//    //Получить все тренировки пользователя
//    List<Training> GetUserTrainings();
//
//    //Получить тренировки пользователя на день
//    List<Training> GetUserTrainings(Date date);
}
