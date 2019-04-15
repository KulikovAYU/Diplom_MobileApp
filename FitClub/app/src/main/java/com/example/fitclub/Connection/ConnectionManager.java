package com.example.fitclub.Connection;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.annotation.NonNull;

import static com.example.fitclub.Connection.ConnectionMode.eGenymotion;
import static com.example.fitclub.Connection.ConnectionMode.ePhoneNotebook;
import static com.example.fitclub.Connection.ConnectionMode.ePhonePC;

public final class ConnectionManager {

    private static String PORT = "56073"; //номер порта

    private static String BASEURL_PHONE_PC = "http://192.168.0.19"; //ip для отладки через телефон (с ПК)
    private static String BASEURL_PHONE_NOTEBOOK = "http://192.168.43.98"; //ip для отладки через телефон (с Ноута)
    private static String BASEURL_GENYMOTION = "http://10.0.3.2"; //ip для отладки через genymotion
    private static String BASEURL_ANDROIDSTUDIO = "http://10.0.2.2"; //ip для отладки через android studio

    //static String BASEURL = "http://192.168.0.19:56073/api/"; //сюда ввести URL //для genymotion - 10.0.3.2// для android studio - 10.0.2.2 //192.168.56.1
    static ConnectionManager mManager;

    private static String mStrConnection; //строка подключения

    //по умолчанию поставим с телефона
    public static ConnectionManager Instance() {
        //УСТАНОВКА СТРОКИ ПОДКЛЮЧЕНИЯ!
        GetConnString(ePhonePC);


        if (mManager == null)
            mManager = new ConnectionManager();

        return mManager;
    }

    @NonNull
    @Override
    public String toString() {
        return mStrConnection;
    }


    private static String GetConnString(ConnectionMode mode) {
        StringBuilder builder = new StringBuilder();

        switch (mode) {
            case ePhonePC:
                builder.append(BASEURL_PHONE_PC).append(":").append(PORT).append("/api/");
                break;
            case ePhoneNotebook:
                builder.append(BASEURL_PHONE_NOTEBOOK).append(":").append(PORT).append("/api/");
                break;
            case eGenymotion:
                builder.append(BASEURL_GENYMOTION).append(":").append(PORT).append("/api/");
                break;
            case eAndroidStudio:
                builder.append(BASEURL_ANDROIDSTUDIO).append(":").append(PORT).append("/api/");
                break;
        }

        mStrConnection = builder.toString();
        return mStrConnection;
    }

    //вернуть URL подключения
    static String GetConnectionURL() {
        return mStrConnection;
    }

    //проверка подключения
    public static boolean IsConnected(Context ctx) {
        ConnectivityManager connectionManager = (ConnectivityManager)
                ctx.getSystemService(Context.CONNECTIVITY_SERVICE);

        BroadcastReceiver connectivityStatusReceiver = new NetworkConnectionStatusReceiver(connectionManager);

        return ((NetworkConnectionStatusReceiver) connectivityStatusReceiver).hasConnection();
    }

    private ConnectionManager() {

    }
}

class NetworkConnectionStatusReceiver extends BroadcastReceiver {

    ConnectivityManager connectivityManager;

    public NetworkConnectionStatusReceiver(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (hasConnection()) {
            int n = 1;
            // network available
        } else {
            // no network
        }
    }

    protected boolean hasConnection() {
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            switch (netInfo.getType()) {
                case ConnectivityManager.TYPE_MOBILE:
                case ConnectivityManager.TYPE_WIFI:
                case ConnectivityManager.TYPE_WIMAX:
                    return true;
                default:
                    return false;
            }
        }

        return false;
    }
}
