package com.example.fitclub.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.fitclub.Adapters.MyTrainingRecyclerViewAdapter;
import com.example.fitclub.Models.Training;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.TrainingViewModel;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link IOnListFragmentInteractionListener}
 * interface.
 */

//Фрагмент списка тренировок
public class TrainingFragment extends Fragment {


    private TrainingViewModel mTrainingViewModel;

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
    Calendar mCurrCalendarDate;//выбранная дата (календарь)

    private  Date mDate; //текущая дата

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) { //если есть что-то, что мы передали в буфер
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mCurrCalendarDate = (Calendar)getArguments().getSerializable("training_date");
        }


        //Подключим вью модель здесь есть mCurrCalendarDate!!! надо вставмить

        mTrainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);

        mTrainingViewModel.SetFragment(mActivityContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_training_list, container, false);


        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshTrainingId);

     //   mSwipeRefreshLayout.setRefreshing(true);


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
        //    if (mCurrCalendarDate == null) // УБРАТЬ! Просто для отладки

           final MyTrainingRecyclerViewAdapter myTrainingRecyclerViewAdapter = new MyTrainingRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(myTrainingRecyclerViewAdapter);

            mDate = (mCurrCalendarDate == null) ? new Date() : mCurrCalendarDate.getTime();

            GetTrainingsOnSelectedData(myTrainingRecyclerViewAdapter);

            //подключим обновление
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    //Записать получение всех тренировок

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            GetTrainingsOnSelectedData(myTrainingRecyclerViewAdapter);

                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    },1000);
                }
            });
        }

        mSwipeRefreshLayout.setRefreshing(false);
        return view;
    }

    private void GetTrainingsOnSelectedData(final MyTrainingRecyclerViewAdapter myTrainingRecyclerViewAdapter) {
        mTrainingViewModel.GetTrainings(mDate).observe(this, new Observer<List<Training>>() {
            @Override
            public void onChanged(List<Training> trainings) {
                myTrainingRecyclerViewAdapter.setTrainings(trainings);
                //update recycler view
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d."); //пока просто для отладки

                String strRes =  sdf.format(mDate);

                Toast.makeText(getContext(),"onChanged date:" + strRes,Toast.LENGTH_SHORT).show();
            }
        });
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
Context mActivityContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //здесь можно определить любой интерфейс из активити
        mActivityContext = context;


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
