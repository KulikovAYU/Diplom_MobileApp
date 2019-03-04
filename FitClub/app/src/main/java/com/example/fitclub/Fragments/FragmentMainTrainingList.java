package com.example.fitclub.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.example.fitclub.Managers.TrainingListFragmentFragmentPageManager;

import com.example.fitclub.R;
import com.example.fitclub.abstracts.IOnConnectionListener;


import java.util.Calendar;



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

    // TODO: Rename parameter arguments, choose names that match


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TrainingListFragmentFragmentPageManager mManager;

    public FragmentMainTrainingList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentMainTrainingList.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentMainTrainingList newInstance(String param1, String param2) {
        FragmentMainTrainingList fragment = new FragmentMainTrainingList();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        mManager = new TrainingListFragmentFragmentPageManager(getFragmentManager(),mContext);
    }



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
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(view.findViewById(R.id.fragment_root_training_list), R.id.calendarView) .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

    //   horizontalCalendar.getSelectedDate();

//        if (horizontalCalendar.getSelectedDate() != null)
//        mBuf.putSerializable("selected_date",horizontalCalendar.getSelectedDate());

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                FragmentMainTrainingList.this.SetSelectedDate(date);
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

        return view;

    }

    private void SetSelectedDate(Calendar date) {
        Bundle  buf = new Bundle();

        buf.putSerializable("training_date",date);

        if (mManager != null)
            mManager.RefreshTrainingFragment(buf);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   private Context mContext;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }


        else {
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
