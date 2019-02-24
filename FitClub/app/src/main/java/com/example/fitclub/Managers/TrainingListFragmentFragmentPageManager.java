package com.example.fitclub.Managers;

import android.os.Bundle;


import com.example.fitclub.abstracts.AppFragmentManager;
import com.example.fitclub.abstracts.MainFactory;
import com.example.fitclub.Factories.TrainingListFragmentFactory;

import androidx.fragment.app.FragmentManager;

//класс для управления фрагментами приложения
public class TrainingListFragmentFragmentPageManager extends AppFragmentManager
{
    public TrainingListFragmentFragmentPageManager(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        mFactory = new TrainingListFragmentFactory(mFragmentManager);
    }

    private MainFactory mFactory;
   public void RefreshTrainingFragment(Bundle data)
    {
        ((TrainingListFragmentFactory)mFactory).RemoveTrainingFragment();

         ((TrainingListFragmentFactory) mFactory).AddTrainingFragment(data);
    }


}
