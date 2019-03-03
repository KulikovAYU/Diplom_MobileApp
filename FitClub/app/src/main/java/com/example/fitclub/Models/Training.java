package com.example.fitclub.Models;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A Models item representing a piece of content.
 */


public class Training implements Serializable {

    protected Date mStartTime;//время начала
    protected Date mEndDime;//время окончания тренировки
    protected String mTrainingName;//название тренировки
    protected boolean mbIsFinished = false;//признак законченной тренировки
    protected String mGymName;//название зала
    protected String mLevelName;//уровень
    protected String mCoachName;//имя инструктора
    protected String mDescription;// описание
    protected boolean mbIsReplaced;//заменена ли тренировка
    protected boolean mbIsMustPay = false; //платная
    protected boolean mbIsNewTraining; // признак новой тренировки
    protected String mProgramType; //тип программы
    protected boolean mbIspopular = false;//признак популярности


    Training(TrainingBuilder trainingBuilder) {

        mStartTime = trainingBuilder.GetStartTime();
        //mbIsMustPay = trainingBuilder.GetIsMustPayStatus();
        mTrainingName = trainingBuilder.GetTrainingName();
        mGymName = trainingBuilder.GetGymName();
        mLevelName = trainingBuilder.GetLevelName();
        mCoachName = trainingBuilder.GetCoachFullname();
        mDescription = trainingBuilder.GetDescription();
        mbIsReplaced = trainingBuilder.GetIsReplacedStatus();
        mbIsNewTraining = trainingBuilder.GetIsNewTraining();
        mEndDime = trainingBuilder.GetEndTime();
        mProgramType = trainingBuilder.GetProgramType();
        mbIsFinished = trainingBuilder.GetIsFinished();
        mbIspopular = trainingBuilder.GetIsPopular();
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

    public boolean getIsMustPay() {
        return mbIsMustPay;
    }

    public Date getStartTime() {
        return mStartTime;
    }

    public String getStartTimeHHmmSS() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        if (mStartTime == null) {
            throw new RuntimeException("mStartTime is null at getStartTimeHHmmSS()");
        }
        return sdf.format(mStartTime);
    }

    public String getStartTimeHHmm() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (mStartTime == null) {
            throw new RuntimeException("mStartTime is null at getStartTimeHHmm()");
        }
        return sdf.format(mStartTime);
    }

    public String getTimeDayDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d.");

        return sdf.format(mStartTime);
    }

    public String getEndTimeHHmm() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        if (mEndDime == null) {
            throw new RuntimeException("mEndDime is null at getEndTimeHHmm()");
        }

        return sdf.format(mEndDime);
    }

    public String getTrainingName() {
        return mTrainingName;
    }


    public String getCoachName() {
        return mCoachName;
    }


    public String getLevelName() {
        return mLevelName;
    }


    public String getDescription() {
        return mDescription;
    }


    public String getGymName() {
        return mGymName;
    }

    public boolean getIsNewTraining() {
        return mbIsNewTraining;
    }

    public String getProgramType() {
        return this.mProgramType;
    }

    public boolean getIsPopular(){return this.mbIspopular;}


    public static class TrainingBuilder {
        protected boolean mbIsReplaced = false;//заменена ли тренировка
        // protected boolean mbIsMustPay = false; //платная
        protected boolean mbIsNewTraining = false; // признак новой тренировки
        protected boolean mbIsFinished = false;//признак законченной тренировки
        protected Date mStartTime;//время начала
        protected Date mEndDime;//время окончания тренировки
        protected String mTrainingName;//название тренировки
        protected String mGymName;//название зала
        protected String mLevelName;//уровень
        protected String mDescription;//описание тренировки
        protected String mProgramType; //тип программы
        protected boolean mbIspopular = false;//признак популярности


        //вспомогательные поля
        private String mCoachName;//имя инструктора
        private String mCoachFamily;//имя инструктора


        public TrainingBuilder Replaced() {
            this.mbIsReplaced = true;
            return this;
        }

        public TrainingBuilder Capacity(int nplacesCount) {

            return this;
        }

        public TrainingBuilder FreePlacesCount(int nFreePlacesCount) {

            return this;
        }

        public TrainingBuilder BusyPlacesCount(int nBusyPlacesCount) {

            return this;
        }


//        public TrainingBuilder MustPay() {
//            this.mbIsMustPay = true;
//            return this;
//        }

        public TrainingBuilder StartTime(Date time) {
            this.mStartTime = time;
            return this;
        }

        public TrainingBuilder EndTime(Date time) {
            this.mEndDime = time;
            return this;
        }

        public TrainingBuilder Name(String trainingName) {
            this.mTrainingName = trainingName;
            return this;
        }

        public TrainingBuilder GymName(String gymName) {
            this.mGymName = gymName;
            return this;
        }

        public TrainingBuilder LevelName(String levelName) {
            this.mLevelName = levelName;
            return this;
        }

        public TrainingBuilder CoachName(String coachName) {
            this.mCoachName = coachName;
            return this;
        }

        public TrainingBuilder CoachFamily(String coachFamily) {
            this.mCoachFamily = coachFamily;
            return this;
        }

        public TrainingBuilder Description(String desc) {
            this.mDescription = desc;
            return this;
        }

        public TrainingBuilder IsNewTraining() {
            this.mbIsNewTraining = true;
            return this;
        }

        public TrainingBuilder ProgramType(String programType) {
            this.mProgramType = programType;
            return this;
        }

        private TrainingBuilder IsFinished() {
            mbIsFinished = true;
            return this;
        }

        public TrainingBuilder IsPopular() {
            mbIspopular = true;//признак популярности
            return this;
        }

        public String GetCoachFullname() {
            return this.mCoachFamily + " " + this.mCoachName;
        }

        public boolean GetIsReplacedStatus() {
            return this.mbIsReplaced;
        }

//        public boolean GetIsMustPayStatus() {
//            return this.mbIsMustPay;
//        }

        public boolean GetIsNewTraining() {
            return this.mbIsNewTraining;
        }

        public Date GetStartTime() {
            return this.mStartTime;
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

        public String GetDescription() {
            return this.mDescription;
        }

        public Date GetEndTime() {
            return this.mEndDime;
        }

        public String GetProgramType() {
            return this.mProgramType;
        }

        public boolean GetIsPopular() {
            return mbIspopular;
        }

        public boolean GetIsFinished() {
            if (mStartTime.before(new Date()))
                return true;
            else
                return mbIsFinished;
        }

        public Training Build() {
            return new Training(this);
        }
    }
}



