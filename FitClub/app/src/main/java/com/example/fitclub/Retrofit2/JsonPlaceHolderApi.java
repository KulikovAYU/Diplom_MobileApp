package com.example.fitclub.Retrofit2;

import com.example.fitclub.Models.Employee;
import com.example.fitclub.Models.Training;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface JsonPlaceHolderApi {

    //Получить тренировки на день
//    @GET("trainings/{date}") //trainings - в серверной части
//    Call<List<Training>> getTrainings(@Path("date") Date date);

    //    @GET("trainings/{date}") //trainings - в серверной части

    //получить список тренировок на день
    @GET("trainings/gettrainingsList/{date}")
    Call<List<Training>> getTrainingsRetrofit(@Path("date") String date);

    //получить фотографию сотрудника
    @GET("{role}/getPhoto/{Id}")
    Call<ResponseBody> getPhotoRetrofit(@Path("role") String role, @Path("Id") String Id);

    //получить ифнформацию о тренере
    @PUT("employees")
    Call<Employee> getCoachOnTrainingRetrofit(@Body Training currentTraining);

    //получить информацию о тренировке
    @GET("trainings/gettraining")
    Call<Training> getTrainingInfoRetrofit(@Query("trainingId") String id, @Query("trainingDate") String date);

    //получить тренера конкретной тренировки
//    @GET("trainings")
//    Call<Employee> getCoachOnTrainingRetrofit(@QueryMap Map<String,String> parametrs);


    //получить тренера конкретной тренировки
//    @GET("trainings/{currTraining}")
//    Call<Employee> getCoachOnTrainingRetrofit(@Path("currTraining") Training currentTraining);



    //Записаться на тренировку
//    @GET("trainings")
//    void signUpOnTraining(@Query("nUserId") Integer nUserId,
//                          @Query("training") Training training);
//
//    //
////    //Отменить запись на тренировку
//    @GET("trainings")
//    void removeSignUpOnTraining(@Query("nUserId") Integer nUserId,
//                                @Query("training") Training training);
//
//    //Получить все тренировки пользователя
//    List<Training> getUserTrainings();
//
//    //Получить тренировки пользователя на день
//    List<Training> getUserTrainings(Date date);
}
