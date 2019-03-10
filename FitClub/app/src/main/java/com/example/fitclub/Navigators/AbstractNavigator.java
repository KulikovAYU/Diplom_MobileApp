package com.example.fitclub.Navigators;

import android.content.Context;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;

public abstract class AbstractNavigator {

    protected Context mContext;
    protected FragmentManager manager;
    protected boolean mbIsconnected = true;

    public boolean IsConnected()
    {
        return mbIsconnected;
    }

    public abstract void GoTo(int nItemId);

    protected void ShowNoConnectMessage()
    {
        Toast.makeText(mContext, "Нет сети", Toast.LENGTH_LONG).show(); //выведем сообщение
    }
}
