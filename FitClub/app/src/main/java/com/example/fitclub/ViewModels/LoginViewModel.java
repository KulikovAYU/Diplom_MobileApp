package com.example.fitclub.ViewModels;

import android.app.Application;
import android.content.Context;

import com.example.fitclub.Models.Client;
import com.example.fitclub.Models.LoginModel;
import com.example.fitclub.Repository.Interfaces.IClientRepository;
import com.example.fitclub.Repository.classes.ClientRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel  extends AndroidViewModel {

    private IClientRepository mClientRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mClientRepository = new ClientRepository();
    }

    //проинициализировать информацию о клиенте
    public MutableLiveData<Client> initializeClientInfo(Context context)
    {
        return mClientRepository.initializeClientInfo(context);
    }

    public void autorize(LoginModel data)
    {
        mClientRepository.autorize(data);
    }
}
