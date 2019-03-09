package com.example.fitclub.Models;

import android.graphics.Bitmap;

import java.io.Serializable;

//тренер
public class Coach implements Serializable {

    private String mCoachName;
    private String mCoachFamily;
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
