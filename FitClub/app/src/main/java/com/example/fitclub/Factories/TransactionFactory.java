package com.example.fitclub.Factories;

import android.os.Bundle;

import com.example.fitclub.R;
import com.example.fitclub.TrainingFragment;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TransactionFactory<T extends AppCompatActivity, T1 extends Bundle>
{

    private FragmentManager mFragmentManager;
    private int mParentRes;
    T mActivity;
    T1 mBundle;

    public TransactionFactory(T activity,T1 bundle,@IdRes int parentRes)
    {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
        mBundle = bundle;
        int fragmentId = bundle.getInt("Item");
        mParentRes = parentRes;
        Add(fragmentId);
    }

    public void Invoke(int fragmentId)
    {
        String fragmentTag = GetTagFromClass(FragmentFactory.Create(fragmentId));

        if (mFragmentManager.findFragmentByTag(fragmentTag) == null)
        {
            Add(fragmentId);
        }
        else
            Show(fragmentTag);
    }

    private void Add(@IdRes int fragmentId)
    {
        Fragment fragment = FragmentFactory.Create(fragmentId);
        Add(fragment);
    }

    private void Add(@NonNull Fragment addingFragment)
    {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragments_content,addingFragment,GetTagFromClass(addingFragment));
        fragmentTransaction.commit();
    }

    private void  Show(@NonNull String tag)
    {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        if (fragment == null)
            return;
        Show(fragment);
    }

    private void  Show(@NonNull Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }


    //замена одного фрагмента на другой
    private void Replace(@IdRes int fragmentId)
    {
        Fragment fragment = FragmentFactory.Create(fragmentId);
        Replace(fragment);
    }

    //замена одного фрагмента на другой
    private void Replace(@NonNull Fragment replacedFragment)
    {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mFragmentManager.findFragmentByTag(GetTagFromClass(replacedFragment)) != null)
        {
            fragmentTransaction.replace(R.id.fragments_content,replacedFragment,GetTagFromClass(replacedFragment));
        }
        fragmentTransaction.commit();
    }

    private void Remove(@IdRes int fragmentId)
    {
        Fragment fragment = FragmentFactory.Create(fragmentId);
        Remove(fragment);
    }

    private void Remove(@NonNull Fragment removingFragment)
    {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        if (mFragmentManager.findFragmentByTag(GetTagFromClass(removingFragment)) != null)
        {
            fragmentTransaction.remove(removingFragment);
        }
        fragmentTransaction.commit();
    }

    //получить тэг из типа класса
    private String GetTagFromClass(Fragment current){
        if (current instanceof TrainingFragment) {
            return ((TrainingFragment) current).TAG;
        }
        return "";
    }

}

class FragmentFactory
{
    public static Fragment Create(int id)
    {
        switch (id)
        {
            case R.id.trainingListId:
                return new TrainingFragment();


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
        return null;
    }
}