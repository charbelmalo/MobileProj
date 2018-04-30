package com.example.charbel.dota;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.charbel.dota.fragments.BaseFragment;
import com.example.charbel.dota.fragments.HeroDetails;
import com.example.charbel.dota.fragments.UserHome;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.services.DeviceStorageManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Charbel on 29/04/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static final String TAG = "recycler";
    private List<Hero> heroes;
    private RecyclerView.ViewHolder holder;
    private int position;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView HeroName;
        public TextView HeroAttribute;
        public TextView HeroAttack;
        public ImageView HeroIcon;
        public Button HeroDetails;

        public ViewHolder(View x) {
            super(x);
            HeroName = x.findViewById(R.id.hero_name);
            HeroAttribute = x.findViewById(R.id.hero_attr);
            HeroAttack = x.findViewById(R.id.hero_attack);
            HeroIcon = x.findViewById(R.id.hero_icon);
            HeroDetails = x.findViewById(R.id.details);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(List<Hero> heroes) {
        this.heroes = heroes;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.hero_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Hero hero = heroes.get(position);
        holder.HeroName.setText(heroes.get(position).getLocalized_name());
        holder.HeroAttribute.setText(heroes.get(position).getPrimary_attribute());
        holder.HeroAttack.setText(heroes.get(position).getAttack_type());
        holder.HeroDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeviceStorageManager deviceStorageManager = new DeviceStorageManager(view.getContext());
                deviceStorageManager.saveHero(hero);
                Intent i = new Intent(view.getContext(),HeroDetails.class);
                view.getContext().startActivity(i);
            }
        });
        Picasso.get().load("https://api.opendota.com"+heroes.get(position).getIcon()).into(holder.HeroIcon);

//        Log.d(TAG, "onBindViewHolder: called.");
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return heroes.size();
    }
}