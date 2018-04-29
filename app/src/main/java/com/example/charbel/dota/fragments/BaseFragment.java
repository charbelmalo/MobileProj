package com.example.charbel.dota.fragments;

/**
 * Created by Charbel on 28/04/2018.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.charbel.dota.R;
import com.example.charbel.dota.services.ApiError;
import com.google.gson.Gson;

import android.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;
import android.annotation.TargetApi;
import android.os.Build;

import butterknife.OnClick;


public class BaseFragment extends Activity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_frag);

    }




}


