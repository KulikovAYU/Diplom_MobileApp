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
   @SerializedName("endTime")
   protected Date mEndTime;
   @SerializedName("abonementNumber")
   protected Integer mNumberAbonement;
   @SerializedName("abonementType")
   protected String mAbonementType;
   @SerializedName("abonementdateOfRegistration")
   protected Date mAbonementDateOfRegistration;//дата регистрации
   @SerializedName("abonementActionTime")
   protected Date mAbonementActionTime;//кол-во месяцев
   @SerializedName("isActive")
   protected boolean mIsActive;//активен
   @SerializedName("abonementDaysFreezeCount")
   protected Date mDaysFreezeCount;//кол-во дней заморозки


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

   public boolean IsActive() {
      return mIsActive;
   }

   public Date getDaysFreezeCount() {
      return mDaysFreezeCount;
   }

   public Date getEndTime() {
      return mEndTime;
   }

}
