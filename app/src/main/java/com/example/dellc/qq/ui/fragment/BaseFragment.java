package com.example.dellc.qq.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by dellc on 2017/9/10.
 */

public abstract class BaseFragment extends Fragment {
    public static final String TAG="BaseFragment";
    private ProgressDialog mProgressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(getLayoutID(),null);
        ButterKnife.bind(this,root);
        init();
        return root;
    }

    protected void init() {
    }


    public abstract int getLayoutID();

    protected void toast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();

    }

    protected void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    protected void hideProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

    }
    protected void goTo(Class activity) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
        getActivity().finish();

    }

    protected void goTo(Class activity,Boolean isFinish ) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
        if(isFinish){
            getActivity().finish();
        }


    }
}
