package com.example.fitclub.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//тренер
public class Employee implements Serializable {

    @SerializedName("id")
    private int Id;
    @SerializedName("name")
    private String mCoachName;
    @SerializedName("family")
    private String mCoachFamily;
    @SerializedName("desc")
    private String mCoachDesc;

    public int getId()
    {
        return Id;
    }

    public String getCoachName() {
        return mCoachName;
    }

    public String getCoachFamily() {
        return mCoachFamily;
    }

    public String getCoachDesc() {
        return mCoachDesc;
    }


}
