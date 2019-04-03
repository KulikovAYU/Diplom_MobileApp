package com.example.fitclub.Retrofit2;

import com.example.fitclub.Models.Client;
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

    //проверить записан ли клиент предварительно на тренировку
    @GET("trainingclients/checkWriting")
    Call<Boolean> checkWriting(@Query("userId")Integer userId,@Query("trainingId")Integer trainingId);

    //оформить предварительную запись
    @GET("trainingclients/createregistrationtraining")
    Call<Training> CreateRegistrationOnTrainingRetrofit(@Query("userId")Integer userId,@Query("trainingId")Integer trainingId,@Query("date") String date);

    //получить список тренировок, на которые записан пользователь
    @GET("trainingclients/{userId}")
    Call<List<Training>> getMyTrainingsRetrofit(@Path("userId") Integer userId);

    //получить информацию о клиенте
    @GET("clients/{clientId}")
    Call<Client> getClientDataRetrofit(@Path("clientId") Integer id);

}
