package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fitclub.Models.CommercialTraining;
import com.example.fitclub.Models.Training;
import com.example.fitclub.Repository.Interfaces.ITrainingsRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TrainingRepository implements ITrainingsRepository {

    //Тренировки на день
    LiveData<List<Training>> mTrainings = null;

    //Тренировки пользователя
    LiveData<List<Training>> mUserTrainings = null;

    @Override
    public LiveData<List<Training>> GetTrainings(Date date) {
        try {
            mTrainings = new GetTrainingsAsyncTask().execute(date).get();
            return mTrainings;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void SignUpOnTraining(Integer nUserId, Training training) {
        try {
            new SignUpOnTrainingAsyncTask().execute(nUserId, training).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void RemoveSignUpOnTraining(Integer nUserId, Training training) {
        try {
            new RemoveSignUpOnTrainingAsyncTask().execute(nUserId, training).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LiveData<List<Training>> GetUserTrainings() {

        try {
            mUserTrainings = new GetUserTrainingsAsyncTask().execute().get();
            return mUserTrainings;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public LiveData<List<Training>> GetUserTrainings(Date date) {
        try {
            return new GetUserTrainingsOnDayAsyncTask().execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    Context mCurrContext;

    @Override
    public void SetContext(Context currContext) {
        mCurrContext = currContext;
    }


    //////////////////////////////Классы для работы с ретрофит\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private class GetTrainingsAsyncTask extends AsyncTask<Date, Void, LiveData<List<Training>>> {
        @Override
        protected LiveData<List<Training>> doInBackground(Date... dates) {


            RetrofitAPI api = new RetrofitAPI(mCurrContext);

            api.getTrainings(dates[0]);

            LiveData<List<Training>> data = new MutableLiveData<List<Training>>();
            List<Training> list = new ArrayList<>();

            Training item1 = new Training.TrainingBuilder().Name("Hatha Yoga").
                    StartTime(new Date(2019, 2, 8, 7, 30)).
                    EndTime(new Date(2019, 2, 8, 8, 30)).
                    GymName("Большой зал").
                    ProgramType("Mind&Body (Мягкий фитнес)").
                    LevelName("Низкая интенсивность").
                    CoachName("Галина").
                    CoachFamily("Елизарова").
                    Description("Занятие, на котором помимо асан и пранаямы делается акцент на " +
                            "концентрацию внимания и медитацию. Урок рекомендован для всех уровней подготовки").
                    Build();

            list.add(item1);


        //addItem(item1);


            Training item2 = new CommercialTraining.CommercialTrainingBuilder().Name("TRX").
                    StartTime(new Date(2019, 2, 8, 8, 30)).
                    EndTime(new Date(2019, 2, 8, 9, 30)).
                    GymName("Тренажерный зал").
                    ProgramType("Специальные программы").
                    LevelName("Для всех уровней подготовки").
                    CoachName("Анастасия").
                    CoachFamily("Молькова").
                    Description("TRX - тренировка мышц всего тела с помощью уникального оборудования - " +
                            "TRX-петель. Это тренировка, которая позволяет не только развивать все мышечные группы, " +
                            "укреплять связки и сухожилия, но и развивать гибкость, ловкость, выносливость и многое " +
                            "другое. Данная тренировка имеет еще одно важное достоинство - эффективное развитие мышц так " +
                            "называемого кора(мышц-стабилизаторов). Упражнения подходят для всех возрастных групп, " +
                            "для мужчин и женщин, для лиц с отклонениями в состоянии здоровья, так как в этой тренировке " +
                            "нет никакой осевой (вертикальной) нагрузки на позвоночник").
                    Capacity(10).
                    Build();
            list.add(item2);

            Training item3 = new Training.TrainingBuilder().Name("New Body").
                    StartTime(new Date(2019, 2, 8, 10, 00)).
                    EndTime(new Date(2019, 2, 8, 10, 30)).
                    GymName("Большой зал").
                    ProgramType("Силовой и функциональный тренинг").
                    LevelName("Для всех уровней подготовки").
                    CoachName("Елена").
                    CoachFamily("Куликова").
                    IsNewTraining().
                    Description("NEW BODY (55 мин) («Новое тело») - силовой урок, направленный на тренировку всех " +
                            "групп мышц. Специально подобранные комплексы упражнений помогут скорректировать проблемные зоны, " +
                            "независимо от того, каким телосложением вы обладаете. Урок рекомендован как для среднего так и для " +
                            "продвинутого уровня подготовки").
                    Build();
            list.add(item3);

            Training item4 = new Training.TrainingBuilder().Name("ABS+Stretch").
                    StartTime(new Date(2019, 2, 8, 16, 00)).
                    EndTime(new Date(2019, 2, 8, 16, 30)).
                    GymName("Большой зал").
                    ProgramType("Mind&Body (Мягкий фитнес)").
                    LevelName("Для всех уровней подготовки").
                    CoachName("Елена").
                    CoachFamily("Куликова").
                    Replaced().
                    Description("Урок, направленный на развитие гибкости, с использованием специальных упражнений на растягивание. " +
                            "Увеличивает подвижность суставов, эластичность связок, дает общее расслабление и релаксацию.").
                    Build();
            list.add(item4);

            Training item5 = new Training.TrainingBuilder().Name("Pilates").
                    StartTime(new Date(2019, 2, 8, 17, 30)).
                    EndTime(new Date(2019, 2, 8, 18, 30)).
                    GymName("Большой зал").
                    LevelName("Для всех уровней подготовки").
                    CoachName("Полина").
                    CoachFamily("Соловьева").
                    ProgramType("Mind&Body (Мягкий фитнес)").
                    Description("Урок направлен на укрепление мышц-стабилизаторов, упражнгения пилатес " +
                            "способствуют снятию напряжению с позвоночника, восстановлению эластичности " +
                            "связочного аппарата и мышц. Урок рекомендован для всех уровней подготовки").
                    IsPopular().
                    Build();
            list.add(item5);

            Training item6 = new CommercialTraining.CommercialTrainingBuilder().Name("TRX").
                    StartTime(new Date(2019, 2, 8, 18, 30)).
                    EndTime(new Date(2019, 2, 8, 19, 30)).
                    GymName("Тренажерный зал").
                    LevelName("Для всех уровней подготовки").
                    CoachName("Анастасия").
                    ProgramType("Специальные программы").
                    CoachFamily("Молькова").
                    Description("TRX - тренировка мышц всего тела с помощью уникального оборудования - " +
                            "TRX-петель. Это тренировка, которая позволяет не только развивать все мышечные группы, " +
                            "укреплять связки и сухожилия, но и развивать гибкость, ловкость, выносливость и многое " +
                            "другое. Данная тренировка имеет еще одно важное достоинство - эффективное развитие мышц так " +
                            "называемого кора(мышц-стабилизаторов). Упражнения подходят для всех возрастных групп, " +
                            "для мужчин и женщин, для лиц с отклонениями в состоянии здоровья, так как в этой тренировке " +
                            "нет никакой осевой (вертикальной) нагрузки на позвоночник").
                    Capacity(15).
                    Build();
            list.add(item6);

             ((MutableLiveData<List<Training>>) data).postValue(list);
            //Запустить ретрофит
            return data;
        }
    }


    private static class SignUpOnTrainingAsyncTask extends AsyncTask<Object, Void, LiveData<Training>> {

        @Override
        protected LiveData<Training> doInBackground(Object... voids) {

            if ((voids[0] instanceof Integer) && (voids[1] instanceof Training)) {
                //получить Id
                //получить тренировку
                //Запустить ретрофит
            }

           return null;
        }
    }

    private static class RemoveSignUpOnTrainingAsyncTask extends AsyncTask<Object, Void, LiveData<Training>> {

        @Override
        protected LiveData<Training> doInBackground(Object... voids) {

            if (voids[0] instanceof Integer) {
                //получить Id
            }
            if (voids[1] instanceof Training) {
                //получить тренировку
            }

            //Запустить ретрофит
            return null;
        }
    }


    private static class GetUserTrainingsAsyncTask extends AsyncTask<Void, Void, LiveData<List<Training>>> {

        @Override
        protected LiveData<List<Training>> doInBackground(Void... voids) {

            //Запустить ретрофит
            return null;
        }
    }


    private static class GetUserTrainingsOnDayAsyncTask extends AsyncTask<Date, Void, LiveData<List<Training>>> {

        @Override
        protected LiveData<List<Training>> doInBackground(Date... voids) {

            //Запустить ретрофит
            return null;
        }
    }
}