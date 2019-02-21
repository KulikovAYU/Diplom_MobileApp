package com.example.fitclub.abstracts;

import androidx.fragment.app.FragmentManager;

public abstract class AppFragmentManager {
    protected FragmentManager mFragmentManager;

    public AppFragmentManager(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }
}
