package com.example.fitclub.abstracts;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class MainFactory {
    protected FragmentManager mFragmentManager;
    protected Context mContext;
    public MainFactory(FragmentManager fragmentManager, Context context) {
        mFragmentManager = fragmentManager;
        mContext = context;
    }

    public abstract void RemoveFragments();

    public abstract void AddFragments();

    public abstract void ReplaceFragment(@NonNull Fragment replacingFragment, @Nullable String TAG, @NonNull int containerId);

}
