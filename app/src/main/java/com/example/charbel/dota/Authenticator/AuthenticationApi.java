package com.example.charbel.dota.Authenticator;

import com.example.charbel.dota.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Charbel on 30/04/2018.
 */

public interface AuthenticationApi {

    @POST("register")
    Call<User> register(@Body User user);

    @POST("login")
    Call<User> login(@Body User user);
}
