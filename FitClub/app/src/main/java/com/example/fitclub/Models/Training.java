package com.example.fitclub.Models;



import android.graphics.Color;


import com.example.fitclub.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import androidx.core.content.ContextCompat;

/**
 * A Models item representing a piece of content.
 */
public class Training {

    private Date mTime;//время начала
    private String mTrainingName;//название тренировки
    private String mGymName;//название зала
    private String mLevelName;//уровень
    private String mCoachName;//имя инструктора
    private String mDescription;// описание
    private boolean mbIsReplaced = false;//заменена ли тренировка
    private boolean mbIsMustPay = false; //платная


//    public Training(Date time, String trainingName, String gymName, String levelName, String coachName) {
//         this.mTime = time;
//         this.mTrainingName = trainingName;
//         this.mGymName = gymName;
//        this.mLevelName = levelName;
//        this.mCoachName = coachName;
//      //   this.mDescription = description;
//    }

     Training(TrainingBuilder trainingBuilder) {

        mTime = trainingBuilder.GetTime();
        mbIsMustPay = trainingBuilder.GetIsMustPayStatus();
        mTrainingName = trainingBuilder.GetTrainingName();
        mGymName = trainingBuilder.GetGymName();
        mLevelName = trainingBuilder.GetLevelName();
        mCoachName = trainingBuilder.GetCoachFullname();
        mDescription = trainingBuilder.GetDescription();
        mbIsReplaced = trainingBuilder.GetIsReplacedStatus();

        if (mbIsMustPay)
            mTrainingName += " (платная секция) по записи";
    }


//    @Override
//    public String toString() {
//        return content;
//    }

    public boolean getIsReplaced() {
        return mbIsReplaced;
    }

    public void setMbIsReplaced(boolean mbIsReplaced) {
        this.mbIsReplaced = mbIsReplaced;
    }

    public boolean getIsMustPay() {
        return mbIsMustPay;
    }

    public void setMbIsMustPay() {
        this.mbIsMustPay = true;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }


    public String getTimeHHmmSS()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       return sdf.format(mTime);
    }

    public String getTimeHHmm()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(mTime);
    }

    public String getmTrainingName() {
        return mTrainingName;
    }

    public void setmTrainingName(String mTrainingName) {
        this.mTrainingName = mTrainingName;
    }

    public String getmCoachName() {
        return mCoachName;
    }

    public void setmCoachName(String mCoachName) {
        this.mCoachName = mCoachName;
    }

    public String getmLevelName() {
        return mLevelName;
    }

    public void setmLevelName(String mLevelName) {
        this.mLevelName = mLevelName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmGymName() {
        return mGymName;
    }

    public void setmGymName(String mGymName) {
        this.mGymName = mGymName;
    }


    public static class TrainingBuilder
    {
        private boolean mbIsReplaced = false;//заменена ли тренировка
        private boolean mbIsMustPay = false; //платная
        private Date mTime;//время начала
        private String mTrainingName;//название тренировки
        private String mGymName;//название зала
        private String mLevelName;//уровень
        private String mDescription;//описание тренировки


        //вспомогательные поля
        private String mCoachName;//имя инструктора
        private String mCoachFamily;//имя инструктора


        public TrainingBuilder SetReplaced()
        {
            this.mbIsReplaced = true;
            return this;
        }

        public TrainingBuilder SetMustPay()
        {
            this.mbIsMustPay = true;
            return this;
        }

        public TrainingBuilder SetTime(Date time)
        {
            this.mTime = time;
            return this;
        }

        public TrainingBuilder SetName(String trainingName)
        {
            this.mTrainingName = trainingName;
            return this;
        }

        public TrainingBuilder SetGymName(String gymName)
        {
            this.mGymName = gymName;
            return this;
        }

        public TrainingBuilder SetLevelName(String levelName)
        {
            this.mLevelName = levelName;
            return this;
        }

        public TrainingBuilder SetCoachName(String coachName)
        {
            this.mCoachName = coachName;
            return this;
        }

        public TrainingBuilder SetCoachFamily(String coachFamily)
        {
            this.mCoachFamily = coachFamily;
            return this;
        }

        public TrainingBuilder SetDescription(String desc)
        {
            this.mDescription = desc;
            return this;
        }

        public String GetCoachFullname()
        {
            return  this.mCoachFamily +" "+ this.mCoachName;
        }

        public boolean GetIsReplacedStatus() {
            return this.mbIsReplaced;
        }

        public boolean GetIsMustPayStatus() {
            return this.mbIsMustPay;
        }

        public Date GetTime() {
            return this.mTime;
        }

        public String GetTrainingName() {
            return this.mTrainingName;
        }

        public String GetGymName() {
            return this.mGymName;
        }

        public String GetLevelName() {
            return this.mLevelName;
        }

        public String GetDescription()
        {
            return this.mDescription;
        }

        public Training Build()
        {
            return new Training(this);
        }
    }



}

