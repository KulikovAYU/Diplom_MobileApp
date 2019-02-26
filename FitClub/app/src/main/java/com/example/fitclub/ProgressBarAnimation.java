package com.example.fitclub;

import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fitclub.Activities.StartActivity;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarAnimation extends Animation {

    private Context mContext;
    private ProgressBar mProgressBar;
    private TextView mTextView;
    private float from;
    private float to;

    public ProgressBarAnimation(Context context,ProgressBar progressBar, TextView textView, float from, float to)
    {
        this.mContext = context;
        this.mProgressBar = progressBar;
        this.mTextView = textView;
        this.from = from;
        this.to = to;

        mProgressBar.setMax(100);
        mProgressBar.setScaleY(1f);
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        mProgressBar.setProgress((int)value);
        mTextView.setText((int)value + " %");

        if (Math.abs(value - to) < 15f)
        {
            Intent intent = new Intent(mContext, StartActivity.class);
            mContext.startActivity(intent);
            if (mContext instanceof AppCompatActivity)
                ((AppCompatActivity) mContext).finish();
        }
    }
}
