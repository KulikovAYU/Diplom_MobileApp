package com.example.fitclub.Managers;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

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

class Manager {

    protected int mId;
    protected AppCompatActivity mContext;


    public Manager(@NonNull Context packageContext) {
        mContext = (AppCompatActivity) packageContext;
    }

    public void Invoke(@NonNull int id) {


        mId = id;


        switch (id) {
            case R.id.gotoStartActivityId:
                GoToStartPage();
                break;

            case R.id.trainingListId: {
                GoToTrainingListPage();
            }
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
    }

    void GoToStartPage() {
        if (mContext != null) {
            GoToPage(mContext, StartActivity.class);
        }
    }

    void GoToTrainingListPage() {

    }

    //Метод возвращает пользователя на стартовую страницу
    public static void GoToPage(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        packageContext.startActivity(intent);
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


