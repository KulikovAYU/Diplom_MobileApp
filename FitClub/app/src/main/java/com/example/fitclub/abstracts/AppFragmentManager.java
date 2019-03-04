package com.example.fitclub.abstracts;

import android.content.Context;

import androidx.fragment.app.FragmentManager;

public abstract class AppFragmentManager {
    protected FragmentManager mFragmentManager;
    protected Context mContext;
    public AppFragmentManager(FragmentManager fragmentManager,Context context ) {
        mFragmentManager = fragmentManager;
        mContext = context;
    }
}
