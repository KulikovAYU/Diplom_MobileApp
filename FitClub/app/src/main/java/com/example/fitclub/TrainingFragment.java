package com.example.fitclub;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitclub.Lists.TrainingList;
import com.example.fitclub.Models.Training;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TrainingFragment extends Fragment {

    public static final String TAG = "TrainingFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    //иначе при альбомной ориентации приложение валится
    public TrainingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_list, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshTrainingId);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Записать получение всех тренировок

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },4000);
            }
        });
        View trainingListview = view.findViewById(R.id.TrainingListId);
        // Set the adapter
        if (trainingListview instanceof RecyclerView) {
            Context context = trainingListview.getContext();
            RecyclerView recyclerView = (RecyclerView) trainingListview;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyTrainingRecyclerViewAdapter(TrainingList.ITEMS, mListener));
        }
        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


        //если контекст (родительское окно реализует OnListFragmentInteractionListener), тогда используем его
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            //иначе создаем свой собственный слушатель
            mListener = new MyListener(context);
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Training item);
    }
}


//слушатель при нажатии на тренировку
class MyListener implements TrainingFragment.OnListFragmentInteractionListener
{
   private Context mContext;
   public MyListener(Context context)
   {
        mContext = context;
   }

    //событие при клике по элементу списка тренировки
    @Override
    public void onListFragmentInteraction(Training item) {
        Toast.makeText(mContext,"Тренировка :"+ item.getmTrainingName(),Toast.LENGTH_LONG).show();
    }
}
