package com.example.fitclub.Navigators;

import android.content.Context;
import android.content.Intent;

import com.example.fitclub.Activities.StartActivity;
import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Fragments.FragmentConnectionError;
import com.example.fitclub.Fragments.FragmentMainTrainingList;
import com.example.fitclub.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LeftPanelNavigator {


    private Context mContext;
    FragmentManager manager;



    private LeftPanelNavigator(Context context) {
        mContext = context;
     if (mContext instanceof AppCompatActivity)
     {
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

//                        if (manager.findFragmentByTag(FragmentMainTrainingList.TAG) == null) {
                            fragmentTransaction.replace(R.id.fragments_content, FragmentMainTrainingList.newInstance(), FragmentMainTrainingList.TAG);
                    //    }


                }
                break;
                case R.id.myTrainingId:
                {

                }
                break;


                ///и т.д.
            }
        } else {
            //если к сети не подключен
            if (manager.findFragmentByTag(FragmentConnectionError.TAG) == null) //если нет фрагмента отсутствия подключения к сети
            {
                fragmentTransaction.replace(R.id.fragments_content,FragmentConnectionError.newInstance(nItemId), FragmentConnectionError.TAG);
            }

        }

        fragmentTransaction.commit();


    }
}
