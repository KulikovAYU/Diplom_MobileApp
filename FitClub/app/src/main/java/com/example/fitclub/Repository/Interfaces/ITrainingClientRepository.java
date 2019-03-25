package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import androidx.lifecycle.LiveData;

public interface ITrainingClientRepository {
    LiveData<Boolean> bIsAlereadyWriting(Integer userId,Integer trainingId);

    void setContext(Context currContext);
}
