package com.example.fitclub.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

//класс данных пользователя системы
public class Client {

   public static Integer USERID = 1; //id пользователя

   @SerializedName("id")
   protected int Id;
   @SerializedName("name")
   protected String mClientName;
   @SerializedName("family")
   protected String mClientFamily;
   @SerializedName("abonementEndTime")
   protected Date mEndTime;
   @SerializedName("abonementNumber")
   protected Integer mNumberAbonement;
   @SerializedName("abonementType")
   protected String mAbonementType;//тип абонемента
   @SerializedName("abonementdateOfRegistration")
   protected Date mAbonementDateOfRegistration;//дата регистрации
   @SerializedName("abonementActionTime")
   protected Date mAbonementActionTime;//кол-во месяцев
   @SerializedName("abonementDaysFreezeCount")
   protected Date mDaysFreezeCount;//кол-во дней заморозки
   @SerializedName("abonementStatus")
   protected String mAbonementStatus;//статус абонемента
   @SerializedName("isAсtive")
   protected boolean mIsAсtive;//активен
   @SerializedName("isFreeze")
   protected boolean mFreeze;//заморожен

   public boolean IsAсtive() {
      return mIsAсtive;
   }

   public boolean IsFreeze() {
      return mFreeze;
   }

   public String getAbonementStatus() {
      return mAbonementStatus;
   }

   public int getId() {
      return Id;
   }

   public String getClientName() {
      return mClientName;
   }

   public String getClientFamily() {
      return mClientFamily;
   }

   public Integer getNumberAbonement() {
      return mNumberAbonement;
   }

   public String getAbonementType() {
      return mAbonementType;
   }

   public Date getAbonementDateOfRegistration() {
      return mAbonementDateOfRegistration;
   }

   public Date getAbonementActionTime() {
      return mAbonementActionTime;
   }

   public Date getDaysFreezeCount() {
      return mDaysFreezeCount;
   }

   public Date getEndTime() {
      return mEndTime;
   }

}
