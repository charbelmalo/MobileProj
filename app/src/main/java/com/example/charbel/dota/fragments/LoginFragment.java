package com.example.charbel.dota.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.charbel.dota.Authenticator.AuthenticationApiManager;
import com.example.charbel.dota.MainActivity;
import com.example.charbel.dota.R;
import com.example.charbel.dota.RecyclerAdapter;
import com.example.charbel.dota.__global__;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.models.User;
import com.example.charbel.dota.services.ApiError;
import com.example.charbel.dota.services.DeviceStorageManager;
import com.example.charbel.dota.services.HeroClient;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private LoginFragmentListener listener;
    private AuthenticationApiManager authenticationApiManager;
    private DeviceStorageManager localStorageManager;

    @BindView(R.id.email_holder)
    TextInputLayout emailHolder;

    @BindView(R.id.email)
    EditText emailEditText;

    @BindView(R.id.password_holder)
    TextInputLayout passwordHolder;

    @BindView(R.id.password)
    EditText passwordEditText;

    @BindView(R.id.login_frag_button)
    Button loginButton;

    @BindView(R.id.register_text)
    TextView registerTextView;

    @BindView(R.id.progressBar_login)
    ProgressBar progressBar;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authenticationApiManager = AuthenticationApiManager.getInstance();
        deviceStorageManager = DeviceStorageManager.getInstance(getActivity().getApplicationContext());
        showProgressBar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_frag, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LoginFragmentListener) {
            listener = (LoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement LoginFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @OnClick(R.id.login_frag_button)
    public void attemptLogin() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        boolean flag = true;

        if (TextUtils.isEmpty(email)) {
            emailHolder.setError("email is required");
            flag = false;
        } else {
            emailHolder.setErrorEnabled(false);
        }

        if (TextUtils.isEmpty(password)) {
            passwordHolder.setError("password is required");
            flag = false;
        } else {
            passwordHolder.setErrorEnabled(false);
        }

        if (flag) {
            showProgressBar();
            User loginUser = new User(email, password);
            authenticationApiManager.login(loginUser).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    hideProgressBar();
                    if (response.isSuccessful()) {
                        User apiUser = response.body();
                        localStorageManager.saveUser(apiUser);
                        listener.onLoginSuccess();
                    } else {
                        try {
                            String errorString = response.errorBody().string();
                            ApiError error = parseApiErrorString(errorString);
                            showToastMessage(error.getMessage());
                            listener.onLoginFailure();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                private ApiError parseApiErrorString(String errorString) {
                    return new ApiError();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    hideProgressBar();
                    listener.onLoginFailure();
                    showToastMessage(t.getMessage());
                }
            });
        }
    }

    private void showToastMessage(String message) {
    }
//
//    @OnClick(R.id.register_text)
//    public void requestRegister() {
//        listener.onRequestRegister();
//    }

    private void showProgressBar() {

    }

    private void hideProgressBar() {
    }

    public interface LoginFragmentListener {

        void onLoginSuccess();

        void onLoginFailure();

        void onRequestRegister();
    }





}
