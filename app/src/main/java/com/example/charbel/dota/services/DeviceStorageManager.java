package com.example.charbel.dota.services;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.models.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Charbel on 28/04/2018.
 */

public class DeviceStorageManager {


        private Gson gson;
        private final String USER_KEY = "PROFILE";
        private final String HERO_DETAIL = "HERO";
        private static DeviceStorageManager deviceStorageManager;
        private SharedPreferences sharedPreferences;

        public DeviceStorageManager(Context context) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            gson = new Gson();
        }

        public static DeviceStorageManager getInstance(Context context) {
            if (deviceStorageManager == null) {
                deviceStorageManager = new DeviceStorageManager(context);
            }
            return deviceStorageManager;
        }

        public User getUser() {
            String userJson = sharedPreferences.getString(USER_KEY, null);
            if (userJson == null) {
                return null;
            }
            try {
                return gson.fromJson(userJson, User.class);
            } catch (Exception e) {
                return null;
            }
        } public Hero getHero() {
            String userJson = sharedPreferences.getString(HERO_DETAIL, null);
            if (userJson == null) {
                return null;
            }
            try {
                return gson.fromJson(userJson, Hero.class);
            } catch (Exception e) {
                return null;
            }
        }

        public void saveUser(User user) {
            String userJson = gson.toJson(user);
            sharedPreferences
                    .edit()
                    .putString(USER_KEY, userJson)
                    .commit();
        }

        public void deleteUser() {
            sharedPreferences
                    .edit()
                    .putString(USER_KEY, null)
                    .commit();
        }


    public void saveHero(Hero hero) {
        String userJson = gson.toJson(hero);
        sharedPreferences
                .edit()
                .putString(HERO_DETAIL, userJson)
                .commit();
    }
}
