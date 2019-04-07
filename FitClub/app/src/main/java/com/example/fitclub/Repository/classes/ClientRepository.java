package com.example.fitclub.Repository.classes;

import android.content.Context;
import android.os.AsyncTask;

import com.example.fitclub.Models.Client;
import com.example.fitclub.Models.LoginModel;
import com.example.fitclub.Repository.Interfaces.IClientRepository;
import com.example.fitclub.Retrofit2.RetrofitAPI;

import java.util.concurrent.ExecutionException;

import androidx.lifecycle.MutableLiveData;

public class ClientRepository implements IClientRepository {

    MutableLiveData<Client> mClient;
    Context mContext;
    @Override
    public MutableLiveData<Client> initializeClientInfo(Context context) {
        mContext = context;
        mClient = new MutableLiveData<>();
        return mClient;
    }

    @Override
    public void getClientData(Integer id) {

        try {
            new getClientDataAsyncTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void autorize(LoginModel data) {
        try {
            new autorizeAsyncTask().execute(data).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private class getClientDataAsyncTask extends AsyncTask<Integer,Void,Void>
    {

        @Override
        protected Void doInBackground(Integer... val) {
            RetrofitAPI retrofit = new RetrofitAPI(mContext);
            retrofit.getClientInfo((Integer)val[0], mClient);
            return null;
        }
    }

    private class autorizeAsyncTask extends  AsyncTask<LoginModel,Void,Void>
    {

        @Override
        protected Void doInBackground(LoginModel... loginModels) {
            RetrofitAPI retrofit = new RetrofitAPI(mContext);
            retrofit.autorize((LoginModel)loginModels[0],mClient);
            return null;
        }
    }
}
