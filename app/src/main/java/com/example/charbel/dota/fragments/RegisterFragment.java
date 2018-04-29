package com.example.charbel.dota.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.charbel.dota.R;

/**
 * Created by Charbel on 29/04/2018.
 */

public class RegisterFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.register_frag,container,false);


    }
}
