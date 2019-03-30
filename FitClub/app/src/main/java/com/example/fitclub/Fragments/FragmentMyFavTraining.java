package com.example.fitclub.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitclub.Models.Training;
import com.example.fitclub.Adapters.MyMyFavTrainingRecyclerViewAdapter;
import com.example.fitclub.Models.User;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.MyFavouriteTrainingListViewModel;
import com.example.fitclub.abstracts.IOnConnectionListener;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FragmentMyFavTraining extends Fragment {

    public static final String TAG = "FragmentMyFavTraining";

    private IOnListFragmentInteractionListener mListener;

    private MyFavouriteTrainingListViewModel myFavouriteTrainingListViewModel;
    private MyMyFavTrainingRecyclerViewAdapter myMyFavTrainingRecyclerViewAdapter;

    private IOnConnectionListener mConnectionListener;


    public FragmentMyFavTraining() {
    }

    // TODO: Customize parameter initialization

    public static FragmentMyFavTraining newInstance() {
        FragmentMyFavTraining fragment = new FragmentMyFavTraining();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myFavouriteTrainingListViewModel = ViewModelProviders.of(this).get(MyFavouriteTrainingListViewModel.class);

        myFavouriteTrainingListViewModel.initializeMyTrainingList(this.getActivity()).observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(List<Training> trainings) {
                if (myMyFavTrainingRecyclerViewAdapter != null)
                    myMyFavTrainingRecyclerViewAdapter.setTrainings(trainings);
            }
        });

        myFavouriteTrainingListViewModel.setContext(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_favourite_trainings, container, false);

        View trainingListview = view.findViewById(R.id.TrainingListId);

        // Set the adapter
        if (trainingListview instanceof RecyclerView) {
            Context context = trainingListview.getContext();

            RecyclerView recyclerView = (RecyclerView) trainingListview;

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            myMyFavTrainingRecyclerViewAdapter = new MyMyFavTrainingRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(myMyFavTrainingRecyclerViewAdapter);

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IOnListFragmentInteractionListener) {
            mListener = (IOnListFragmentInteractionListener) context;

        if (context instanceof IOnConnectionListener) {
            mConnectionListener = (IOnConnectionListener) context;

      //      mConnectionListener.CheckConnection(R.id.myTrainingId);
            } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        myFavouriteTrainingListViewModel.getMyTrainings(User.USERID);
    }
}
