package com.example.fitclub.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Training1 implements Serializable {

    @SerializedName("Id")
    private int mnTrtainingId;

    @SerializedName("startTime")
    protected Date mStartTime;//время начала

    @SerializedName("endTime")
    protected Date mEndTime;//время окончания тренировки

    @SerializedName("trainingName")
    protected String mTrainingName;//название тренировки

    @SerializedName("isFinished")
    protected boolean mbIsFinished = false;//признак законченной тренировки

    @SerializedName("gymName")
    protected String mGymName;//название зала

    @SerializedName("levelName")
    protected String mLevelName;//уровень

    @SerializedName("coachId")
    protected String coachId;//Id инструктора

    @SerializedName("coachName")
    protected String mCoachName;//имя инструктора

    @SerializedName("coachFamily")
    protected String mCoachFamily;//фамилия инструктора

    @SerializedName("description")
    protected String mDescription;// описание

    @SerializedName("isReplaced")
    protected boolean mbIsReplaced;//заменена ли тренировка

    @SerializedName("isMustPay")
    protected boolean mbIsMustPay = false; //платная

    @SerializedName("isNewTraining")
    protected boolean mbIsNewTraining; // признак новой тренировки

    @SerializedName("programType")
    protected String mProgramType; //тип программы

    @SerializedName("ispopular")
    protected boolean mbIspopular = false;//признак популярности

    @SerializedName("placesCount")
    protected int mnplacesCount;//количество записей (вместимость)

    @SerializedName("freePlacesCount")
    protected int mnFreePlacesCount;//количество свободных мест

    @SerializedName("busyPlacesCount")
    protected int mnBusyPlacesCount;// количество занятых мест


    //геттеры
    public Integer getTrtainingId() {

        return new Integer(mnTrtainingId);
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public String getTrainingName() {

        if (mbIsMustPay && !mTrainingName.contains(" (платная секция) по записи")) {
            mTrainingName += " (платная секция) по записи";
        }

        return mTrainingName;
    }

    public boolean getIsFinished() {
        return mbIsFinished;
    }

    public String getGymName() {
        return mGymName;
    }

    public String getLevelName() {
        return mLevelName;
    }

    public String getCoachName() {
        return mCoachName;
    }

    public String getCoachFamily() { return mCoachFamily;}

    public String getDescription() {
        return mDescription;
    }

    public boolean getIsReplaced() {
        return mbIsReplaced;
    }

    public boolean getIsMustPay() {
        return mbIsMustPay;
    }

    public boolean getIsNewTraining() {
        return mbIsNewTraining;
    }

    public String getProgramType() {
        return mProgramType;
    }

    public boolean getIsPopular() {
        return mbIspopular;
    }

    public int getPlacesCount() {
        return mnplacesCount;
    }

    public int getFreePlacesCount() {
        return mnFreePlacesCount;
    }

    public int getBusyPlacesCount() {
        return mnBusyPlacesCount;
    }


    //возможна ли запись
    public boolean getRecordingIsPossible() {
        return (mnFreePlacesCount != mnBusyPlacesCount) && !mbIsFinished || mStartTime.after(new Date()) || mStartTime.equals(new Date());
    }
}
