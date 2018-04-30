package com.example.charbel.dota.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charbel.dota.MainActivity;
import com.example.charbel.dota.R;
import com.example.charbel.dota.RecyclerAdapter;
import com.example.charbel.dota.__global__;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.models.User;
import com.example.charbel.dota.services.DeviceStorageManager;
import com.example.charbel.dota.services.HeroClient;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charbel on 29/04/2018.
 */

public class LoginFragment extends Fragment {
    private DeviceStorageManager deviceStorageManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

            return inflater.inflate(R.layout.login_frag,container,false);


    }





}
