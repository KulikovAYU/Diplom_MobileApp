package com.example.fitclub.Navigators;

import android.content.Context;
import android.content.Intent;

import com.example.fitclub.Activities.StartActivity;
import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Fragments.FragmentMyFavTraining;
import com.example.fitclub.Fragments.FragmentConnectionError;
import com.example.fitclub.Fragments.FragmentMainTrainingList;
import com.example.fitclub.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class LeftPanelNavigator extends AbstractNavigator {

    private LeftPanelNavigator(Context context) {
        mContext = context;
        if (mContext instanceof AppCompatActivity) {
            manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        }
    }

    //создает навигатор для левой панели
    public static AbstractNavigator createInstance(Context context) {
        return new LeftPanelNavigator(context);
    }

    //переход на фрагмент
    public void GoTo(int nItemId) {

        //для главной страницы не требуется проверка подключения к сети
        if (nItemId == R.id.gotoStartActivityId) {
            Intent intent = new Intent(mContext, StartActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(intent);
            return;
        }

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //проверка подключения к сети
        if (ConnectionManager.IsConnected(mContext)) {
            switch (nItemId) {
                case R.id.trainingListId: {

                    if (manager.findFragmentByTag(FragmentMainTrainingList.TAG) == null) {
                        Calendar selectedDate = null;
                        if (manager.findFragmentByTag(FragmentConnectionError.TAG) != null)
                            selectedDate = ((FragmentConnectionError) manager.findFragmentByTag(FragmentConnectionError.TAG)).GetSaveDate();

                        FragmentMainTrainingList fragmentMainTrainingList = FragmentMainTrainingList.newInstance(selectedDate);

                        fragmentTransaction.replace(R.id.fragments_content, fragmentMainTrainingList, FragmentMainTrainingList.TAG);
                    }
                }
                break;
                case R.id.myTrainingId: {
                    if (manager.findFragmentByTag(FragmentMyFavTraining.TAG) == null)
                    {
                        FragmentMyFavTraining fragmentMyFavTraining = FragmentMyFavTraining.newInstance();
                        fragmentTransaction.replace(R.id.fragments_content, fragmentMyFavTraining, FragmentMyFavTraining.TAG);
                    }

                }
                break;


                ///и т.д.
            }
        } else {
            mbIsconnected = false;

            ShowNoConnectMessage(); //выведем сообщение
            Calendar selectedDate = null;


            //если к сети не подключен
            if (manager.findFragmentByTag(FragmentConnectionError.TAG) == null) //если нет фрагмента отсутствия подключения к сети
            {

                if (manager.findFragmentByTag(FragmentMainTrainingList.TAG) != null)//если есть дата
                {
                    selectedDate = FragmentMainTrainingList.mSelectedDate;//получим дату
                }
                connError = FragmentConnectionError.newInstance(nItemId, selectedDate);
                fragmentTransaction.replace(R.id.fragments_content,connError, FragmentConnectionError.TAG);
            }
        }

        fragmentTransaction.commit();
    }

    FragmentConnectionError connError;
}
