package com.example.fitclub.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.fitclub.Adapters.MyTrainingRecyclerViewAdapter;
import com.example.fitclub.R;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;
import com.example.fitclub.Lists.TrainingList;


import java.util.Calendar;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IOnListFragmentInteractionListener}
 * interface.
 */

//Фрагмент списка тренировок
public class TrainingFragment extends Fragment {

    public static final String TAG = "TrainingFragment";
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private IOnListFragmentInteractionListener mListener;

    SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    //иначе при альбомной ориентации приложение валится
    public TrainingFragment() {

    }
    Calendar curr;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) { //если есть что-то, что мы передали в буфер
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            curr = (Calendar)getArguments().getSerializable("training_date");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_list, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshTrainingId);

     //   mSwipeRefreshLayout.setRefreshing(true);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Записать получение всех тренировок

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                },1000);
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
        //    if (curr == null) // УБРАТЬ! Просто для отладки
                recyclerView.setAdapter(new MyTrainingRecyclerViewAdapter(TrainingList.ITEMS, mListener));
        }

        mSwipeRefreshLayout.setRefreshing(false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        },2000);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //здесь можно определить любой интерфейс из активити



        //если контекст (родительское окно реализует IOnListFragmentInteractionListener), тогда используем его
        if (context instanceof IOnListFragmentInteractionListener) {
            mListener = (IOnListFragmentInteractionListener) context;
        } else {
            //иначе создаем свой собственный слушатель
//            throw new RuntimeException(context.toString()
//                    + " must implement IOnListFragmentInteractionListener");
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



}


////слушатель при нажатии на тренировку
//class MyListener implements TrainingFragment.IOnListFragmentInteractionListener
//{
//   private Context mContext;
//   public MyListener(Context context)
//   {
//        mContext = context;
//   }
//
//    //событие при клике по элементу списка тренировки
//    @Override
//    public void onListFragmentInteraction(Training item) {
//        Toast.makeText(mContext,"Тренировка :"+ item.getTrainingName(),Toast.LENGTH_LONG).show();
//    }
//}
