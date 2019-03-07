package com.example.fitclub.Navigators;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.fitclub.Activities.StartActivity;
import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Fragments.FragmentConnectionError;
import com.example.fitclub.Fragments.FragmentMainTrainingList;
import com.example.fitclub.R;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LeftPanelNavigator {


    private Context mContext;
    FragmentManager manager;

    private LeftPanelNavigator(Context context) {
        mContext = context;
        if (mContext instanceof AppCompatActivity) {
            manager = ((AppCompatActivity) mContext).getSupportFragmentManager();
        }
    }

    //создает навигатор для левой панели
    public static LeftPanelNavigator createInstance(Context context) {
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

                }
                break;


                ///и т.д.
            }
        } else {
            Toast.makeText(mContext, "Нет сети", Toast.LENGTH_LONG).show(); //выведем сообщение

            //если к сети не подключен
            if (manager.findFragmentByTag(FragmentConnectionError.TAG) == null) //если нет фрагмента отсутствия подключения к сети
            {
                Calendar selectedDate = null;
                if (manager.findFragmentByTag(FragmentMainTrainingList.TAG) != null)//если есть дата
                {
                    selectedDate = FragmentMainTrainingList.mSelectedDate;//получим дату
                }
                fragmentTransaction.replace(R.id.fragments_content, FragmentConnectionError.newInstance(nItemId, selectedDate), FragmentConnectionError.TAG);
            }
        }

        fragmentTransaction.commit();

    }
}
