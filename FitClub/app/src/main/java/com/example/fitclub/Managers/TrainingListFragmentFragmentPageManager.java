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
    }

   public void RefreshFragment(Bundle data)
    {
        MainFactory factory = new TrainingListFragmentFactory(mFragmentManager);
        ((TrainingListFragmentFactory)factory).RemoveTrainingFragment();

         ((TrainingListFragmentFactory) factory).AddTrainingFragment(data);
    }
}
