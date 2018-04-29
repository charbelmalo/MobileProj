package com.example.charbel.dota.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.charbel.dota.R;
import com.example.charbel.dota.__global__;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.services.HeroClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Charbel on 28/04/2018.
 */

public class UserHome extends android.support.v4.app.Fragment{

    private RecyclerView hero_list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hero_frag, container, false);
        hero_list = (RecyclerView) view.findViewById(R.id.list);

        return view;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }
    public static class HeroListAdapter extends RecyclerView.Adapter<HeroListAdapter.ViewHolder> {

        private List<Hero> heroes;

        public HeroListAdapter(List<Hero> heroes) {
            this.heroes = heroes;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.hero_item, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Hero hero = heroes.get(position);
            holder.heroNameTextView.setText(hero.getName());
//            holder.heroAttackTextView.setText(hero.getAttack());
//            holder.heroAttTextView.setText(hero.getAttribute());
        }

        @Override
        public int getItemCount() {
            return heroes.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.hero_name)
            TextView heroNameTextView;

            @BindView(R.id.hero_attr)
            TextView heroAttTextView;

            @BindView(R.id.hero_attack)
            TextView heroAttackTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }

}
