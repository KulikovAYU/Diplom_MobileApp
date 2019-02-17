package com.example.fitclub.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Models item representing a piece of content.
 */
public class Training {

    private Date mTime;//время начала
    private String mTrainingName;//название тренировки
    private String mGym;//название зала
    private String mLevel;//уровень
    private String mCoachName;//имя инструктора
    private String mDescription;// описание



    public Training(Date time, String trainingName, String gym, String level, String coachName) {
         this.mTime = time;
         this.mTrainingName = trainingName;
         this.mGym = gym;
        this.mLevel = level;
        this.mCoachName = coachName;
      //   this.mDescription = description;
    }




//    @Override
//    public String toString() {
//        return content;
//    }

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

    public String getmLevel() {
        return mLevel;
    }

    public void setmLevel(String mLevel) {
        this.mLevel = mLevel;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmGym() {
        return mGym;
    }

    public void setmGym(String mGym) {
        this.mGym = mGym;
    }
}