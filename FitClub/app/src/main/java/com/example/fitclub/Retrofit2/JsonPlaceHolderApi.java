package com.example.fitclub.Retrofit2;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training1;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface JsonPlaceHolderApi {

    //Получить тренировки на день
//    @GET("trainings/{date}") //trainings - в серверной части
//    Call<List<Training>> getTrainings(@Path("date") Date date);

    //    @GET("trainings/{date}") //trainings - в серверной части
    //получить список тренировок на день
    @GET("trainings1/gettrainingsList/{date}")
    Call<List<Training1>> getTrainingsRetrofit(@Path("date") String date);

   @GET("{role}/getPhoto/{Id}")
    Call<ResponseBody> getPhotoRetrofit(@Path("role") String role, @Path("Id") String Id);

    //получить тренера конкретной тренировки
//    @GET("trainings")
//    Call<Employee> getCoachOnTrainingRetrofit(@QueryMap Map<String,String> parametrs);


    //получить тренера конкретной тренировки
//    @GET("trainings/{currTraining}")
//    Call<Employee> getCoachOnTrainingRetrofit(@Path("currTraining") Training1 currentTraining);

    @PUT("employees")
    Call<Employee> getCoachOnTrainingRetrofit(@Body Training1 currentTraining);

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
