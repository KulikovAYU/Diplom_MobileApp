package com.example.fitclub.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.fitclub.Adapters.MyTrainingRecyclerViewAdapter;


import com.example.fitclub.Models.Training1;
import com.example.fitclub.R;
import com.example.fitclub.ViewModels.TrainingViewModel;
import com.example.fitclub.abstracts.IOnConnectionListener;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentMainTrainingList.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentMainTrainingList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentMainTrainingList extends Fragment {


    public static final String TAG = "FragmentMainTrainingList";


    private TrainingViewModel mTrainingViewModel;

    private IOnListFragmentInteractionListener mListener;

    private IOnConnectionListener mConnectionListener;

    public static Date mDate;//выбранная дата

    public static Calendar mSelectedDate;

    SwipeRefreshLayout mSwipeRefreshLayout;
    private int mColumnCount = 1;
    private static final String ARG_PARAM1 = "Date";

    public FragmentMainTrainingList() {
        // Required empty public constructor
    }

    //Создает экземпляр фрагмента
    public static FragmentMainTrainingList newInstance(Calendar selectedDate) {
        FragmentMainTrainingList fragment = new FragmentMainTrainingList();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, selectedDate);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().getSerializable(ARG_PARAM1) != null) {
            mSelectedDate = (Calendar) getArguments().getSerializable(ARG_PARAM1);
            //можно получить параметры
        }

        mTrainingViewModel = ViewModelProviders.of(this).get(TrainingViewModel.class);
        mTrainingViewModel.SetContext(this.getActivity());
    }

    HorizontalCalendar mHorizontalCalendar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_training_list, container, false);

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        if (mSelectedDate != null)
            mHorizontalCalendar = new HorizontalCalendar.Builder(view.findViewById(R.id.fragment_root_training_list), R.id.calendarView).range(startDate, endDate)
                    .datesNumberOnScreen(5).defaultSelectedDate(mSelectedDate)
                    .build();
        else {
            mHorizontalCalendar = new HorizontalCalendar.Builder(view.findViewById(R.id.fragment_root_training_list), R.id.calendarView).range(startDate, endDate)
                    .datesNumberOnScreen(5)
                    .build();
        }

        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshTrainingId);

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


//            mDate = FragmentMainTrainingList.mDate == null ? new Date() : FragmentMainTrainingList.mDate;


            if (mSelectedDate != null) {
                mDate = mSelectedDate.getTime();
            } else {
                mDate = new Date();
            }


            //наш адаптер
            final MyTrainingRecyclerViewAdapter myTrainingRecyclerViewAdapter = new MyTrainingRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(myTrainingRecyclerViewAdapter);

            mHorizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
                @Override
                public void onDateSelected(Calendar date, int position) {

                    FragmentMainTrainingList.this.SetSelectedDate(date);
                    mConnectionListener.CheckConnection(R.id.trainingListId);
                    GetTrainingsOnSelectedData(myTrainingRecyclerViewAdapter);
                    //необходимо переопределить адаптер
                }

                @Override
                public void onCalendarScroll(HorizontalCalendarView calendarView,
                                             int dx, int dy) {

                }

                @Override
                public boolean onDateLongClicked(Calendar date, int position) {
                    return true;
                }
            });


            GetTrainingsOnSelectedData(myTrainingRecyclerViewAdapter);

            mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);

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
                    }, 1000);
                }
            });
        }

        mSwipeRefreshLayout.setRefreshing(false);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void GetTrainingsOnSelectedData(final MyTrainingRecyclerViewAdapter myTrainingRecyclerViewAdapter) {
        mSwipeRefreshLayout.setRefreshing(true);
        mTrainingViewModel.GetTrainings(mDate).observe(this, new Observer<List<Training1>>() {
            @Override
            public void onChanged(List<Training1> trainings) {
                myTrainingRecyclerViewAdapter.setTrainings(trainings);
                //update recycler view
                SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d."); //пока просто для отладки

                String strRes = sdf.format(mDate);

                Toast.makeText(getContext(), "onChanged date:" + strRes, Toast.LENGTH_SHORT).show();
            }
        });
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void SetSelectedDate(Calendar date) {
        mDate = date.getTime();
        mSelectedDate = date;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnListFragmentInteractionListener) {
            mListener = (IOnListFragmentInteractionListener) context;
        }

        if (context instanceof IOnConnectionListener) {
            mConnectionListener = (IOnConnectionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
            //          + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
