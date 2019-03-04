package com.example.fitclub.Factories;

import android.content.Context;

import com.example.fitclub.R;
import com.example.fitclub.abstracts.AppFragmentManager;
import com.example.fitclub.abstracts.MainFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class LeftPanelController {

    private AppCompatActivity mMainActivity;

    private LeftPanelCommandManager mBuilder;


    public LeftPanelController(AppCompatActivity mainActivity) {
        mMainActivity = mainActivity;
        mBuilder = new LeftPanelCommandManager(mMainActivity.getSupportFragmentManager(),mMainActivity);
    }

    public void InvokeCommand(int buttonCmdId) {
        if (buttonCmdId == R.id.gotoStartActivityId)
            return;

        mBuilder.InvokeCommand(buttonCmdId);

    }
}

//класс управляет созданием фрагментов при шелчке на панели управления
class LeftPanelCommandManager extends AppFragmentManager {
    MainFactory mainFactory;

    public LeftPanelCommandManager(FragmentManager fragmentManager, Context context) {
        super(fragmentManager,context);

    }

    void InvokeCommand(int buttonCmdId) {
        //создаем наши фабрики
        switch (buttonCmdId) {
            case R.id.gotoStartActivityId:
                return;

            case R.id.trainingListId:
                mainFactory = new TrainingListFragmentFactory(mFragmentManager,mContext);
                break;

            case R.id.myTrainingId:

                break;

            case R.id.myNotificationId:

                break;

            case R.id.myPersonalCabId:

                break;

            case R.id.nav_share:

                break;

            case R.id.nav_send:

                break;
        }

        mainFactory.AddFragments();
    }
}


