package com.example.charbel.dota.Authenticator;

import android.content.Context;

import com.example.charbel.dota.Authenticator.AuthenticationApi;
import com.example.charbel.dota.__global__;
import com.example.charbel.dota.models.User;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fouad
 */

public class AuthenticationApiManager {

    private Retrofit retrofit;
    private AuthenticationApi authenticationApi;

    private static AuthenticationApiManager authenticationApiManager;

    private AuthenticationApiManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(__global__.BACKEND_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authenticationApi = retrofit.create(AuthenticationApi.class);
    }

    public static AuthenticationApiManager getInstance() {
        if (authenticationApiManager == null) {
            authenticationApiManager = new AuthenticationApiManager();
        }
        return authenticationApiManager;
    }

    public Call<User> login(User user) {
        return authenticationApi.login(user);
    }

    public Call<User> register(User user) {
        return authenticationApi.register(user);
    }
}
