package com.example.fitclub.Models;

import android.graphics.Bitmap;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//тренер
public class Coach implements Serializable {

    @SerializedName("name")
    private String mCoachName;
    @SerializedName("family")
    private String mCoachFamily;
    @SerializedName("desc")
    private String mCoachDesc;
    private Bitmap mCoachPhoto;


    public String getCoachName() {
        return mCoachName;
    }

    public String getCoachFamily() {
        return mCoachFamily;
    }

    public String getCoachDesc() {
        return mCoachDesc;
    }

    public Bitmap getCoachPhoto() {
        return mCoachPhoto;
    }
}
