package com.example.fitclub.abstracts;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class MainFactory {
    protected FragmentManager mFragmentManager;

    public MainFactory(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public abstract void RemoveFragments();

    public abstract void AddFragments();

    public abstract void ReplaceFragment(@NonNull Fragment replacingFragment, @Nullable String TAG, @NonNull int containerId);

}
