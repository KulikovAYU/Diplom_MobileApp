package com.example.fitclub.Factories;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fitclub.Connection.ConnectionManager;
import com.example.fitclub.Fragments.FragmentConnectionError;
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



    public TrainingListFragmentFactory(FragmentManager fragmentManager,Context context) {
        super(fragmentManager,context);
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

    public void AddTrainingFragment(Bundle data ) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();


        if (ConnectionManager.Instance().IsConnected(mContext))
        {

            TrainingFragment   trainingFragment = new TrainingFragment();
                trainingFragment.onAttach(mContext);



                if (data != null)
                {
                    trainingFragment.setArguments(data);
                }

            //проверим нет ли фрагмента с ошибкой подключения к сети
            if (mFragmentManager.findFragmentByTag(FragmentConnectionError.TAG) != null)
            {
                fragmentTransaction.replace(R.id.fragment_container,trainingFragment, trainingFragment.TAG);
            }
            else
            {
                fragmentTransaction.add(R.id.fragment_container, trainingFragment, trainingFragment.TAG);
            }

        }
        else
        {
            //иначе выведем ошибку подключения
            if (mFragmentManager.findFragmentByTag(FragmentConnectionError.TAG) == null)
            {
                FragmentConnectionError connectionErrorFragment = new FragmentConnectionError();
                connectionErrorFragment.onAttach(mContext);
                fragmentTransaction.add(R.id.fragment_container, connectionErrorFragment, connectionErrorFragment.TAG);

            }
            Toast.makeText(mContext,"Нет сети:",Toast.LENGTH_SHORT).show();
        }

        fragmentTransaction.commit();
    }

    @Override
    public void AddFragments() {


        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if (mFragmentManager.findFragmentByTag(FragmentMainTrainingList.TAG) == null) {
            FragmentMainTrainingList mainTrainingListFragment = new FragmentMainTrainingList();

            fragmentTransaction.add(R.id.fragments_content, mainTrainingListFragment, mainTrainingListFragment.TAG);
        }
        if (ConnectionManager.Instance().IsConnected(mContext))
        {
                if (mFragmentManager.findFragmentByTag(TrainingFragment.TAG) == null) {

                    TrainingFragment trainingFragment = new TrainingFragment();
                    trainingFragment.onAttach(mContext);
                    //проверим не подключен ли FragmentConnectionError
                    if (mFragmentManager.findFragmentByTag(FragmentConnectionError.TAG) != null )
                    {
                        fragmentTransaction.replace(R.id.fragment_container,trainingFragment,TrainingFragment.TAG);
                    }
                    else
                    {
                        fragmentTransaction.add(R.id.fragment_container, trainingFragment, trainingFragment.TAG);
                    }
                   // fragmentTransaction.add(R.id.fragment_container, trainingFragment, trainingFragment.TAG);
                }
            }
         else
        {
            if (mFragmentManager.findFragmentByTag(FragmentConnectionError.TAG) == null)
            {
                FragmentConnectionError connectionErrorFragment = new FragmentConnectionError();
                connectionErrorFragment.onAttach(mContext);
                //проверим не подключен ли TrainingFragment
                if (mFragmentManager.findFragmentByTag(TrainingFragment.TAG) != null )
                {
                    fragmentTransaction.replace(R.id.fragment_container,connectionErrorFragment,FragmentConnectionError.TAG);
                }
                else
                {
                    fragmentTransaction.add(R.id.fragment_container, connectionErrorFragment, connectionErrorFragment.TAG);
                }
            }

            Toast.makeText(mContext,"Нет сети:",Toast.LENGTH_SHORT).show();
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
