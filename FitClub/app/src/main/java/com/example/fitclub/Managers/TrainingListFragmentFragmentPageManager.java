package com.example.fitclub.Managers;

import android.content.Context;
import android.os.Bundle;


import com.example.fitclub.abstracts.AppFragmentManager;
import com.example.fitclub.abstracts.MainFactory;
import com.example.fitclub.Factories.TrainingListFragmentFactory;

import androidx.fragment.app.FragmentManager;

//класс для управления фрагментами приложения
public class TrainingListFragmentFragmentPageManager extends AppFragmentManager
{
    public TrainingListFragmentFragmentPageManager(FragmentManager fragmentManager, Context context)
    {
        super(fragmentManager,context);
        mFactory = new TrainingListFragmentFactory(mFragmentManager,context);
    }

    private MainFactory mFactory;
   public void RefreshTrainingFragment(Bundle data)
    {
        ((TrainingListFragmentFactory)mFactory).RemoveTrainingFragment();

         ((TrainingListFragmentFactory) mFactory).AddTrainingFragment(data);
    }


}
