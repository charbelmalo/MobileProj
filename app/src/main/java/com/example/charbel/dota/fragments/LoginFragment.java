package com.example.charbel.dota.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charbel.dota.R;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.services.DeviceStorageManager;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Charbel on 29/04/2018.
 */

public class LoginFragment extends Fragment {
    @BindView(R.id.login_frag_button)
    TextView register;

    public static LoginFragment newInstance() {
        return  new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.login_frag,container,false);


    }

}
