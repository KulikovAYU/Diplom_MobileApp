package com.example.fitclub.Adapters;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.fitclub.R;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;
import com.example.fitclub.Models.Training;


import java.util.HashMap;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Training} and makes a call to the
 * specified {@link IOnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTrainingRecyclerViewAdapter extends RecyclerView.Adapter<MyTrainingRecyclerViewAdapter.ViewHolder> {

    private final List<Training> mValues;
    private final IOnListFragmentInteractionListener mListener;

    public MyTrainingRecyclerViewAdapter(List<Training> items, IOnListFragmentInteractionListener listener) {
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
        holder.mTrainingNameView.setText( holder.mItem.getTrainingName());
        holder.mGymView.setText( holder.mItem.getGymName());
        holder.mLevelView.setText( holder.mItem.getLevelName());
        holder.mCoachNameView.setText( holder.mItem.getCoachName());
        holder.mIsReplacedView.setVisibility(holder.mItem.getIsReplaced() ?  View.VISIBLE : View.GONE);
        holder.mIsMustPayView.setVisibility(holder.mItem.getIsMustPay() ? View.VISIBLE : View.GONE);
        holder.mDifficultView.setBackgroundColor( ContextCompat.getColor(holder.mView.getContext(), TrainingDifficult.GetColorIndex(holder.mItem.getLevelName())));
        holder.mIsNewTrainingView.setVisibility(holder.mItem.getIsNewTraining() ? View.VISIBLE : View.GONE);

        //Color.alpha(TrainingDifficult.GetColorIndex(holder.mItem.getLevelName()))



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
        public final ImageView mIsReplacedView;// тренировка заменена на другую
        public final LinearLayout mIsMustPayView;// тренировка заменена на другую item_mustPayId
        public final LinearLayout mDifficultView;// полоска сложности тренировки
        public final LinearLayout mIsNewTrainingView;


        public Training mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTimeIdView = (TextView) view.findViewById(R.id.item_timeId);
            mTrainingNameView = (TextView) view.findViewById(R.id.item_trainingNameId);
            mGymView = (TextView) view.findViewById(R.id.item_gymId);
            mLevelView = (TextView) view.findViewById(R.id.item_levelId);
            mCoachNameView = (TextView) view.findViewById(R.id.item_CoachNameId);
            mIsReplacedView = (ImageView)view.findViewById(R.id.item_isReplacedId);
            mIsMustPayView = (LinearLayout)view.findViewById(R.id.item_mustPayId);
            mDifficultView = (LinearLayout)view.findViewById(R.id.item_difficultId);
            mIsNewTrainingView = (LinearLayout)view.findViewById(R.id.item_newTrainingId);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTrainingNameView.getText() + "'";
        }
    }

    public static class TrainingDifficult
    {
       private static HashMap<String, Integer> map = new HashMap<>();

        //Вовзрат индекса в зависимости от сложности
        public static int GetColorIndex(String difficult)
        {
            //интенсивности
            map.put("Высокая интенсивность", R.color.md_orange_300);
            map.put("Для всех уровней подготовки", R.color.md_light_green_300);
            map.put("Низкая интенсивность", R.color.md_teal_100);

            if (map.containsKey(difficult))
            {
                int ress = map.get(difficult);
                return map.get(difficult);
            }


            return R.color.md_white_1000;
        }

    }
}
