package com.example.fitclub.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fitclub.Models.Training;
import com.example.fitclub.R;
import com.example.fitclub.abstracts.IOnListFragmentInteractionListener;
import com.example.fitclub.utils.TimeFormatter;

import java.util.List;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyMyFavTrainingRecyclerViewAdapter extends RecyclerView.Adapter<MyMyFavTrainingRecyclerViewAdapter.ViewHolder> {

    private List<Training> mValues;
    private final IOnListFragmentInteractionListener mListener;

    public MyMyFavTrainingRecyclerViewAdapter(IOnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myfavtraining, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.mTimeIdView.setText(TimeFormatter.convertTimeHHmm(holder.mItem.getStartTime()));
        holder.mTrainingNameView.setText(holder.mItem.getTrainingName());
        holder.mGymView.setText(holder.mItem.getGymName());
        holder.mLevelView.setText(holder.mItem.getLevelName());
        holder.mCoachNameView.setText(holder.mItem.getCoachName() + " " + holder.mItem.getCoachFamily());
        holder.mIsReplacedView.setVisibility(holder.mItem.getIsReplaced() ? View.VISIBLE : View.GONE);
        holder.mIsMustPayView.setVisibility(holder.mItem.getIsMustPay() ? View.VISIBLE : View.GONE);
        holder.mDifficultView.setBackgroundColor(ContextCompat.getColor(holder.mView.getContext(), MyTrainingRecyclerViewAdapter.TrainingDifficult.GetColorIndex(holder.mItem.getLevelName())));
        holder.mIsNewTrainingView.setVisibility(holder.mItem.getIsNewTraining() ? View.VISIBLE : View.GONE);
        holder.mbIsPopularView.setVisibility(holder.mItem.getIsPopular() ? View.VISIBLE : View.GONE);
        holder.mTrainingDateView.setText(TimeFormatter.convertTimeEEEdMMM(holder.mItem.getStartTime()));
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
        return (mValues != null) ? mValues.size() : 0;
    }

    public void setTrainings(List<Training> items) {
        mValues = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mTimeIdView; //время начала
        public final TextView mTrainingNameView;//название тренировки
        public final TextView mGymView;//название зала
        public final TextView mLevelView;//уровень
        public final TextView mCoachNameView;//имя инструктора
        public final TextView mTrainingDateView;//дата начала
        public final ImageView mIsReplacedView;// тренировка заменена на другую
        public final LinearLayout mIsMustPayView;// тренировка заменена на другую item_mustPayId
        public final LinearLayout mDifficultView;// полоска сложности тренировки
        public final LinearLayout mIsNewTrainingView; //признак нового занятия (желтая надпись Новое занятие)
        public final LinearLayout mbIsPopularView;//признак популярного занятия

        public Training mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTimeIdView = (TextView) view.findViewById(R.id.item_timeId);
            mTrainingNameView = (TextView) view.findViewById(R.id.item_trainingNameId);
            mGymView = (TextView) view.findViewById(R.id.item_gymId);
            mLevelView = (TextView) view.findViewById(R.id.item_levelId);
            mCoachNameView = (TextView) view.findViewById(R.id.item_CoachNameId);
            mTrainingDateView = (TextView) view.findViewById(R.id.item_timeId1);
            mIsReplacedView = (ImageView) view.findViewById(R.id.item_isReplacedId);
            mIsMustPayView = (LinearLayout) view.findViewById(R.id.item_mustPayId);
            mDifficultView = (LinearLayout) view.findViewById(R.id.item_difficultId);
            mIsNewTrainingView = (LinearLayout) view.findViewById(R.id.item_newTrainingId);
            mbIsPopularView = (LinearLayout) view.findViewById(R.id.item_isPopularId);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTrainingNameView.getText() + "'";
        }
    }
}
