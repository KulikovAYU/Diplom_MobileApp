package com.example.fitclub.Managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.fitclub.Activities.StartActivity;
import com.example.fitclub.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Manager {

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
            GoToActivity(mContext, StartActivity.class);
        }
    }

    void GoToTrainingListPage() {

    }

    //Метод возвращает пользователя на стартовую страницу
    public static void GoToActivity(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        packageContext.startActivity(intent);
    }

    //Метод переводит поьльзовтеля на указанную страницу
    public static void GoToActivity(Context packageContext, Class<?> cls, Bundle buf, String strBunleName) {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra(strBunleName,buf);



        if (packageContext instanceof AppCompatActivity)
        {
            packageContext.startActivity(intent);

        }



       //

    }

    //запускает Activity, определенной в переменной cls типа Class<?>
    //ожидая от нее результата. Результат ловить в void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) (переменная packageContext)
    //nResultCode - код результата (например Activity.RESULT_OK)
    public static void GoToActivityForResult(Context packageContext, Class<?> cls, Bundle buf, String strBunleName, int nResultCode)
    {
        Intent intent = new Intent(packageContext, cls);
        intent.putExtra(strBunleName,buf);

        if (packageContext instanceof AppCompatActivity)
        {
            ((AppCompatActivity)packageContext).setResult(nResultCode,intent );
             packageContext.startActivity(intent);
        }
    }

}
