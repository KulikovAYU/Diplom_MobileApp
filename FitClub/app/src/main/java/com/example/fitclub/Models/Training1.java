package com.example.fitclub.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Training1 implements Serializable {

    @SerializedName("mnTrtainingId")
    private int mnTrtainingId;

    @SerializedName("mStartTime")
    protected Date mStartTime;//время начала

    @SerializedName("mEndTime")
    protected Date mEndTime;//время окончания тренировки

    @SerializedName("mTrainingName")
    protected String mTrainingName;//название тренировки

    @SerializedName("mbIsFinished")
    protected boolean mbIsFinished = false;//признак законченной тренировки

    @SerializedName("mGymName")
    protected String mGymName;//название зала

    @SerializedName("mLevelName")
    protected String mLevelName;//уровень

    @SerializedName("mCoachName")
    protected String mCoachName;//имя инструктора

    @SerializedName("mCoachFamily")
    protected String mCoachFamily;//фамилия инструктора

    @SerializedName("mDescription")
    protected String mDescription;// описание

    @SerializedName("mbIsReplaced")
    protected boolean mbIsReplaced;//заменена ли тренировка

    @SerializedName("mbIsMustPay")
    protected boolean mbIsMustPay = false; //платная

    @SerializedName("mbIsNewTraining")
    protected boolean mbIsNewTraining; // признак новой тренировки

    @SerializedName("mProgramType")
    protected String mProgramType; //тип программы

    @SerializedName("mbIspopular")
    protected boolean mbIspopular = false;//признак популярности

    @SerializedName("mnplacesCount")
    protected int mnplacesCount;//количество записей (вместимость)

    @SerializedName("mnFreePlacesCount")
    protected int mnFreePlacesCount;//количество свободных мест

    @SerializedName("mnBusyPlacesCount")
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


        if (mbIsMustPay && !mTrainingName.contains(" (платная секция) по записи"))
        {
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

    public String getCoachFamily(){return mCoachFamily;}

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
    public boolean getRecordingIsPossible()
    {
        return (mnFreePlacesCount != mnBusyPlacesCount) && !mbIsFinished || mStartTime.after(new Date()) || mStartTime.equals(new Date());
    }
}
