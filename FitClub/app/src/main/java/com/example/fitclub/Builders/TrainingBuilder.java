//package com.example.fitclub.Builders;
//
//import com.example.fitclub.Models.Training;
//
//import java.util.Date;
//
//public class TrainingBuilder
//{
//    private boolean mbIsReplaced = false;//заменена ли тренировка
//    private boolean mbIsMustPay = false; //платная
//    private Date mTime;//время начала
//    private String mTrainingName;//название тренировки
//    private String mGymName;//название зала
//    private String mLevelName;//уровень
//    private String mDescription;//описание тренировки
//
//
//    //вспомогательные поля
//    private String mCoachName;//имя инструктора
//    private String mCoachFamily;//имя инструктора
//
//
//    public TrainingBuilder Replaced()
//    {
//        this.mbIsReplaced = true;
//        return this;
//    }
//
//    public TrainingBuilder MustPay()
//    {
//        this.mbIsMustPay = true;
//        return this;
//    }
//
//    public TrainingBuilder StartTime(Date time)
//    {
//        this.mTime = time;
//        return this;
//    }
//
//    public TrainingBuilder Name(String trainingName)
//    {
//        this.mTrainingName = trainingName;
//        return this;
//    }
//
//    public TrainingBuilder GymName(String gymName)
//    {
//        this.mGymName = gymName;
//        return this;
//    }
//
//    public TrainingBuilder LevelName(String levelName)
//    {
//        this.mLevelName = levelName;
//        return this;
//    }
//
//    public TrainingBuilder CoachName(String coachName)
//    {
//        this.mCoachName = coachName;
//        return this;
//    }
//
//    public TrainingBuilder CoachFamily(String coachFamily)
//    {
//        this.mCoachFamily = coachFamily;
//        return this;
//    }
//
//    public TrainingBuilder Description(String desc)
//    {
//        this.mDescription = desc;
//        return this;
//    }
//
//    public String GetCoachFullname()
//    {
//        return  this.mCoachFamily +" "+ this.mCoachName;
//    }
//
//    public boolean GetIsReplacedStatus() {
//        return this.mbIsReplaced;
//    }
//
//    public boolean GetIsMustPayStatus() {
//        return this.mbIsMustPay;
//    }
//
//    public Date GetTime() {
//        return this.mTime;
//    }
//
//    public String GetTrainingName() {
//        return this.mTrainingName;
//    }
//
//    public String GetGymName() {
//        return this.mGymName;
//    }
//
//    public String GetLevelName() {
//        return this.mLevelName;
//    }
//
//    public String GetDescription()
//    {
//        return this.mDescription;
//    }
//
//    public Training Build()
//    {
//        return new Training(this);
//    }
//}
