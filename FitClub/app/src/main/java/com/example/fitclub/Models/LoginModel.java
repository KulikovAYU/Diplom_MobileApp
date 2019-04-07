package com.example.fitclub.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginModel implements Serializable {

    public LoginModel()
    {

    }
    public LoginModel(String mAbonementNumber,String mPasswordHash){
        this.abonementNumber = mAbonementNumber;
        this.passwordHash = mPasswordHash;

    }

    public void setAbonementNumber(String mAbonementNumber) {
        this.abonementNumber = mAbonementNumber;
    }

    public void setPasswordHash(String mPasswordHash) {
        this.passwordHash = mPasswordHash;
    }

    public String getAbonementNumber() {
        return abonementNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @SerializedName("abonementNumber")
    protected String abonementNumber;
    @SerializedName("passwordHash")
    protected String passwordHash;


}
