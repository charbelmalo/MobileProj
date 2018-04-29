package com.example.charbel.dota.fragments;

/**
 * Created by Charbel on 28/04/2018.
 */

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.widget.Toast;

import com.example.charbel.dota.services.ApiError;
import com.google.gson.Gson;

import android.app.Fragment;
import android.widget.Toast;

import com.google.gson.Gson;
import android.annotation.TargetApi;
import android.os.Build;





public class BaseFragment extends android.support.v4.app.Fragment {

    private Gson gson = new Gson();

    @TargetApi(Build.VERSION_CODES.M)
    public void showToastMessage(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public ApiError parseApiErrorString(String error) {
        return gson.fromJson(error, ApiError.class);
    }
}


