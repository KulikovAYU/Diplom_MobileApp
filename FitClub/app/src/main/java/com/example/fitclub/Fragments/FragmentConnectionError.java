package com.example.fitclub.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.fitclub.R;
import com.example.fitclub.abstracts.IOnConnectionListener;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FragmentConnectionError#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentConnectionError extends Fragment implements View.OnClickListener {

    public static final String TAG = "FragmentConnectionError";
    private IOnConnectionListener mConnectionListener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "Id";
    private static final String ARG_PARAM2 = "Date";


    // TODO: Rename and change types of parameters
    private int mParamId; //текущий id фрагмента

    private Calendar mSelectedDate;



    public FragmentConnectionError() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentConnectionError newInstance(int nId, Calendar selectedDate) {
        FragmentConnectionError fragment = new FragmentConnectionError();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1,nId);
        if (selectedDate != null)
        {
            args.putSerializable(ARG_PARAM2,selectedDate);
        }

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParamId = getArguments().getInt(ARG_PARAM1);
            if (getArguments().getSerializable(ARG_PARAM2) != null)
            mSelectedDate = (Calendar) getArguments().getSerializable(ARG_PARAM2);
       }
    }

    public Calendar GetSaveDate()
    {
        return mSelectedDate;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connection_error, container, false);

        Button button = (Button) view.findViewById(R.id.btn_CheckConnectionId);

        button.setOnClickListener(this);

        // Inflate the layout for this fragment
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnConnectionListener)
            mConnectionListener = (IOnConnectionListener)context;

        else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mConnectionListener = null;
    }

    @Override
    public void onClick(View v) {
        if (mConnectionListener != null)
            mConnectionListener.CheckConnection(mParamId);
    }

}
