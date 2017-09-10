package com.example.dellc.qq;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dellc on 2017/9/10.
 */

public abstract class BaseFragment extends Fragment {
    public static final String TAG="BaseFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(getLayoutID(),null);
        init();

        return root;
    }

    private void init() {
    }


    public abstract int getLayoutID();


}
