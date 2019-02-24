package com.example.fitclub.Managers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fitclub.Factories.LeftPanelController;
import com.example.fitclub.Activities.MainActivity;
import com.example.fitclub.R;
import com.example.fitclub.Activities.StartActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//класс управляет навигацией (левой боковой панелью)
public class NavigationManager {


    static NavigationManager mNavManager;

    public static NavigationManager Instance() {

        if (mNavManager == null)
            mNavManager = new NavigationManager();

        return mNavManager;
    }


    public void Invoke(@NonNull int id, @NonNull Activity packageContext) throws Exception {
        Manager manager = null;
        if (packageContext instanceof StartActivity)
            manager = new StartActivityManager(packageContext);
        else if (packageContext instanceof MainActivity)
            manager = new MainActivityManager(packageContext);
        assert manager != null;
        manager.Invoke(id);
    }

    private NavigationManager() {

    }

}

//Менеджер меню для активити (Стартового окна)
class StartActivityManager extends Manager {
    public StartActivityManager(@NonNull Context packageContext) {
        super(packageContext);
    }

    @Override
    void GoToTrainingListPage() {
        super.GoToTrainingListPage();
        mContext.startActivity(new Intent(mContext, MainActivity.class).putExtra("Item", mId));
    }
}

//Менеджер меню для (Main Activity)
class MainActivityManager extends Manager {

    LeftPanelController mController;


    public MainActivityManager(@NonNull Context packageContext) {
        super(packageContext);

        mController = new LeftPanelController(mContext);
    }

    @Override
    void GoToTrainingListPage() {
        super.GoToTrainingListPage();

        mController.InvokeCommand(mId);
    }




}


