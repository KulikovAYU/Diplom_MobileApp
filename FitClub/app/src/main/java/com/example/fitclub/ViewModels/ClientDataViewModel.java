package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;


import com.example.fitclub.Models.Client;
import com.example.fitclub.Repository.Interfaces.IClientRepository;
import com.example.fitclub.Repository.Repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ClientDataViewModel extends AndroidViewModel {

    private IClientRepository mClientRepository;

    public ClientDataViewModel(@NonNull Application application) {

        super(application);
        mClientRepository = new Repository().getClientRepository();
    }

    //проинициализировать информацию о клиенте
    public MutableLiveData<Client> initializeClientInfo(Context context)
    {
        return mClientRepository.initializeClientInfo(context);
    }

    //получить данные клиента
    public void getClientData(Integer id)
    {
        mClientRepository.getClientData(id);
    }




}
