package com.example.fitclub.Models;

import java.util.Date;

//коммерческая тренировка
public class CommercialTraining extends Training {

    protected int mnplacesCount;//количество записей (вместимость)
    protected int mnFreePlacesCount;//количество свободных мест
    protected int mnBusyPlacesCount;// количество занятых мест


     CommercialTraining(CommercialTrainingBuilder trainingBuilder) {
        super(trainingBuilder);
        mbIsMustPay = true;
        mnplacesCount = trainingBuilder.GetCapacity();
        mnFreePlacesCount = trainingBuilder.GetFreePlacesCount();
        mnBusyPlacesCount = trainingBuilder.GetBusyPlacesCount();
    }

    //заполнена
    public boolean IsFilled() {
        return mnplacesCount - mnBusyPlacesCount == 0;
    }

    //получить количество мест
    private int getFreePlacesCount()
    {
        mnFreePlacesCount =  mnplacesCount - mnBusyPlacesCount;
        return mnFreePlacesCount;
    }


    public String getStrFreePlacesCount()
    {
        mnFreePlacesCount =  mnplacesCount - mnBusyPlacesCount;

        return  String.valueOf(mnFreePlacesCount);
    }

    public boolean getIsFinished(){return this.mbIsFinished;}



    //возможна ли запись
    public boolean getRecordingIsPossible()
    {
        return !IsFilled() && !mbIsFinished || mStartTime.before(new Date());
    }


    public static class CommercialTrainingBuilder extends TrainingBuilder {

        protected int mnplacesCount;//количество записей (вместимость)
        protected int mnFreePlacesCount;//количество свободных мест
        protected int mnBusyPlacesCount;// количество занятых мест


        // private boolean mbIsReplaced = false;//заменена ли тренировка
        //private boolean mbIsMustPay = true; //платная
        // private boolean mbIsNewTraining = false; // признак новой тренировки
        //private boolean mbIsFinished = false;//признак законченной тренировки
        // private Date mStartTime;//время начала
        //  private Date mEndDime;//время окончания тренировки
        // private String mTrainingName;//название тренировки
        //  private String mGymName;//название зала
        // private String mLevelName;//уровень
        //  private String mDescription;//описание тренировки
        // private String mProgramType; //тип программы

        @Override
        public CommercialTrainingBuilder Capacity(int nplacesCount) {
            this.mnplacesCount = nplacesCount;
            return this;
        }

        @Override
        public CommercialTrainingBuilder FreePlacesCount(int nFreePlacesCount) {
            this.mnFreePlacesCount = nFreePlacesCount;
            return this;
        }

        @Override
        public CommercialTrainingBuilder BusyPlacesCount(int nBusyPlacesCount) {
            this.mnBusyPlacesCount = nBusyPlacesCount;
            return this;
        }


        public int GetCapacity() {
            return this.mnplacesCount;
        }

        public int GetFreePlacesCount() {
            return this.mnFreePlacesCount;
        }

        public int GetBusyPlacesCount() {
            return this.mnBusyPlacesCount;
        }


        public Training Build() {
            return new CommercialTraining(this);
        }
    }
}
