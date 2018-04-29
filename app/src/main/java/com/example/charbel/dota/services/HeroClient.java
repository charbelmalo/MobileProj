package com.example.charbel.dota.services;

import com.example.charbel.dota.models.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Charbel on 28/04/2018.
 */

public interface HeroClient {

    @GET("heroes")
    Call<List<Hero>> getHeroes();

    @GET("heroStats")
    Call<List<Hero>> getDetailedHeroes();
}
