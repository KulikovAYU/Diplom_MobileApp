package com.example.fitclub.Models;


import java.text.SimpleDateFormat;
import java.util.Date;

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
    private boolean mbIsNewTraining = false; // признак новой тренировки

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
        mbIsNewTraining = trainingBuilder.GetIsNewTraining();
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

    public Date getTime() {
        return mTime;
    }

    public String getTimeHHmmSS() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(mTime);
    }

    public String getTimeHHmm() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(mTime);
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


    public String getmDescription() {
        return mDescription;
    }


    public String getGymName() {
        return mGymName;
    }

    public boolean getIsNewTraining() {
        return mbIsNewTraining;
    }


    public static class TrainingBuilder {
        private boolean mbIsReplaced = false;//заменена ли тренировка
        private boolean mbIsMustPay = false; //платная
        private boolean mbIsNewTraining = false; // признак новой тренировки
        private Date mTime;//время начала
        private String mTrainingName;//название тренировки
        private String mGymName;//название зала
        private String mLevelName;//уровень
        private String mDescription;//описание тренировки


        //вспомогательные поля
        private String mCoachName;//имя инструктора
        private String mCoachFamily;//имя инструктора


        public TrainingBuilder Replaced() {
            this.mbIsReplaced = true;
            return this;
        }

        public TrainingBuilder MustPay() {
            this.mbIsMustPay = true;
            return this;
        }

        public TrainingBuilder StartTime(Date time) {
            this.mTime = time;
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


        public String GetCoachFullname() {
            return this.mCoachFamily + " " + this.mCoachName;
        }

        public boolean GetIsReplacedStatus() {
            return this.mbIsReplaced;
        }

        public boolean GetIsMustPayStatus() {
            return this.mbIsMustPay;
        }

        public boolean GetIsNewTraining() {
            return this.mbIsNewTraining;
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

        public String GetDescription() {
            return this.mDescription;
        }


        public Training Build() {
            return new Training(this);
        }


    }


}

