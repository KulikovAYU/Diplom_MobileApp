package com.example.fitclub;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.fitclub.Models.Training;
import com.example.fitclub.TrainingFragment.OnListFragmentInteractionListener;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Training} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTrainingRecyclerViewAdapter extends RecyclerView.Adapter<MyTrainingRecyclerViewAdapter.ViewHolder> {

    private final List<Training> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyTrainingRecyclerViewAdapter(List<Training> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_training, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mTimeIdView.setText( holder.mItem.getTimeHHmm());
        holder.mTrainingNameView.setText( holder.mItem.getmTrainingName());
        holder.mGymView.setText( holder.mItem.getmGym());
        holder.mLevelView.setText( holder.mItem.getmLevel());
        holder.mCoachNameView.setText( holder.mItem.getmCoachName());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  Toast.makeText(this,"OnProfileClick",Toast.LENGTH_LONG).show();

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.

                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTimeIdView; //время начала
        public final TextView mTrainingNameView;//название тренировки
        public final TextView mGymView;//название зала
        public final TextView mLevelView;//уровень
        public final TextView mCoachNameView;//имя инструктора

        public Training mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTimeIdView = (TextView) view.findViewById(R.id.item_timeId);
            mTrainingNameView = (TextView) view.findViewById(R.id.item_trainingNameId);
            mGymView = (TextView) view.findViewById(R.id.item_gymId);
            mLevelView = (TextView) view.findViewById(R.id.item_levelId);
            mCoachNameView = (TextView) view.findViewById(R.id.item_CoachNameId);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTrainingNameView.getText() + "'";
        }
    }
}
