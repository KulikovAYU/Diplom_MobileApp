package com.example.fitclub.Repository.Interfaces;

import android.content.Context;

import com.example.fitclub.Models.Client;

import androidx.lifecycle.MutableLiveData;

public interface IClientRepository {

    //инициализация информации о клиенте (пользователе системы)
    MutableLiveData<Client> initializeClientInfo(Context context);
    //загрузить данные о клиенте и его абонементе
    void getClientData(Integer id);
}
