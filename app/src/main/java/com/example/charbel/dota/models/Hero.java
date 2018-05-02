package com.example.charbel.dota.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 28/04/2018.
 */

public class Hero {
    private String name;
    private String localized_name;
    private String primary_attr;
    private String attack_type;
    private String[] roles;
    private String legs;
    private String icon;
    private String img;



    public String getLocalized_name() {
        return localized_name;
    }

    public String getPrimary_attribute() {
        return primary_attr;
    }

    public String getAttack_type() {
        return attack_type;
    }

    public String[] getRoles() {
        return roles;
    }

    public String getLegs() {
        return legs;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getImg() {
        return img;
    }
}
