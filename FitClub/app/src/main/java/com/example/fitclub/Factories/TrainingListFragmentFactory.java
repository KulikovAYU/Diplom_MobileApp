package com.example.fitclub.Factories;

import android.os.Bundle;

import com.example.fitclub.Fragments.FragmentMainTrainingList;
import com.example.fitclub.R;
import com.example.fitclub.Fragments.TrainingFragment;
import com.example.fitclub.abstracts.MainFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

//фабрика для создания фрагментов списка тренировок
public class TrainingListFragmentFactory extends MainFactory {

    public TrainingListFragmentFactory(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public void RemoveFragments() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();


        TrainingFragment findingTrainFrag = (TrainingFragment) mFragmentManager.findFragmentByTag(TrainingFragment.TAG);

        if (findingTrainFrag != null) {
            fragmentTransaction.remove(findingTrainFrag);
        }

        FragmentMainTrainingList findingMainTrainingList = (FragmentMainTrainingList) mFragmentManager.findFragmentByTag(FragmentMainTrainingList.TAG);
        if (findingMainTrainingList != null) {
            fragmentTransaction.remove(findingMainTrainingList);
        }

        fragmentTransaction.commit();
    }


    public void RemoveTrainingFragment() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        TrainingFragment findingTrainFrag = (TrainingFragment) mFragmentManager.findFragmentByTag(TrainingFragment.TAG);

        if (findingTrainFrag != null) {
            fragmentTransaction.remove(findingTrainFrag);
        }
        fragmentTransaction.commit();
    }

    public void AddTrainingFragment(Bundle data) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(TrainingFragment.TAG) != null) {
            TrainingFragment trainingFragment = new TrainingFragment();
            if (data != null)
                trainingFragment.setArguments(data);

            fragmentTransaction.add(R.id.fragment_root_training_list1, trainingFragment, trainingFragment.TAG);
        }

        fragmentTransaction.commit();

    }

    @Override
    public void AddFragments() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(FragmentMainTrainingList.TAG) == null) {
            FragmentMainTrainingList mainTrainingListFragment = new FragmentMainTrainingList();

            fragmentTransaction.add(R.id.fragments_content, mainTrainingListFragment, mainTrainingListFragment.TAG);

            if (mFragmentManager.findFragmentByTag(TrainingFragment.TAG) == null) {
                TrainingFragment trainingFragment = new TrainingFragment();
                fragmentTransaction.add(R.id.fragment_root_training_list1, trainingFragment, trainingFragment.TAG);
            }
        }

        fragmentTransaction.commit();
    }

    @Override
    public void ReplaceFragment(@NonNull Fragment replacingFragment, @Nullable String TAG, @NonNull int containerId) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        Fragment findEl = mFragmentManager.findFragmentByTag(TAG);
        if (findEl != null) {
            fragmentTransaction.replace(containerId, findEl, TAG);
        }
        fragmentTransaction.commit();
    }
}
