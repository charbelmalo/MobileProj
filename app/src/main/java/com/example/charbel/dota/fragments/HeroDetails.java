package com.example.charbel.dota.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charbel.dota.R;
import com.example.charbel.dota.__global__;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.services.DeviceStorageManager;
import com.example.charbel.dota.services.HeroClient;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charbel on 29/04/2018.
 */

public class HeroDetails extends AppCompatActivity {
    private DeviceStorageManager deviceStorageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        deviceStorageManager = new DeviceStorageManager(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_detailed);
        Hero hero = deviceStorageManager.getHero();
        String attri = hero.getPrimary_attribute();
        int att_color = 0;
        if(attri.equalsIgnoreCase("int")){
            attri="Intelligence";
        att_color=Color.BLUE;}
        else if(attri.equalsIgnoreCase("agi")){
            attri="Agility";
            att_color=Color.GREEN;}
        else if(attri.equalsIgnoreCase("str")){
            attri="Strength";
            att_color=Color.RED;}
        TextView name,attribute,att,role;
        name = findViewById(R.id.hero_name_detailed);
        attribute = findViewById(R.id.hero_attribute_detailed);
        att = findViewById(R.id.hero_att_type);
        name.setText(hero.getLocalized_name());
        att.setText(hero.getAttack_type());
        attribute.setText(attri);
        attribute.setTextColor(att_color);

        String roles=hero.getRoles()[0];
        for(int i=1;i<hero.getRoles().length;i++){
            roles=roles+", "+hero.getRoles()[i];
        }
        role = findViewById(R.id.hero_roles_detailed);
        role.setText(roles);
        Picasso.get().load("https://api.opendota.com"+hero.getImg()).into((ImageView) findViewById(R.id.hero_icon_detailed));
    }

}
