package com.example.charbel.dota;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.charbel.dota.fragments.BaseFragment;
import com.example.charbel.dota.fragments.LoginFragment;
import com.example.charbel.dota.fragments.RegisterFragment;
import com.example.charbel.dota.fragments.UserHome;
//import com.example.charbel.dota.fragments.UserHome$HeroListAdapter$ViewHolder_ViewBinding;
import com.example.charbel.dota.fragments.UserHome.HeroListAdapter;
import com.example.charbel.dota.models.Hero;
import com.example.charbel.dota.models.User;
import com.example.charbel.dota.services.DeviceStorageManager;
import com.example.charbel.dota.services.HeroClient;
import com.example.charbel.dota.services.HeroFragAdapter;

import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity{
    private DeviceStorageManager deviceStorageManager;
    private HeroListAdapter mSections;
    private RecyclerView mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        deviceStorageManager = new DeviceStorageManager(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_frag);
//        displayHeroes();
//        NavigationView navigationView1 = findViewById(R.id.nav_view);
//        View header = navigationView1.getHeaderView(0);
////        profilePictureImageView = header.findViewById(R.id.profile_picture);
////        nameHeaderTextView = header.findViewById(R.id.name);
////        emailHeaderTextView = header.findViewById(R.id.email);
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
////        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
////                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
////        drawer.addDrawerListener(toggle);
////        toggle.syncState();
//
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if(deviceStorageManager.getUser()==null){
//            setContentView(R.layout.activity_user);
//            UserHome details = new UserHome();
//            details.setArguments(getIntent().getExtras());
//            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
//
//        }
//        else{
//            setContentView(R.layout.content_user);
//        }

    }
//    @OnClick(R.id.login_frag_button)
//    public void goToReg() {
//        RegisterFragment fragment = new RegisterFragment();
//
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container_fragments, fragment)
//                .commit();
//    }

    public void displayHeroes(){

        setContentView(R.layout.hero_frag);

        deviceStorageManager = DeviceStorageManager.getInstance(this);
        mView = findViewById(R.id.list);
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(__global__.API_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        HeroClient client = retrofit.create(HeroClient.class);

        Call<List<Hero>> call =  client.getDetailedHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                List<Hero> heroes = response.body();
                TextView name;
//                addHeroesList(heroes);
                name = (TextView) findViewById(R.id.hero_name);
//                name.setText(heroes.get(2).getLocalized_name());
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);

                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(false);

                // use a linear layout manager
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getParent());
                mRecyclerView.setLayoutManager(mLayoutManager);

                // specify an adapter (see also next example)
                RecyclerView.Adapter mAdapter = new RecyclerAdapter(heroes);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                setContentView(R.layout.error_no_connection);
            }
        });
    }
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_heroes:
//                addHeroesList();
                break;

            case R.id.nav_signout:
                logout();
                break;

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void addHeroesList(List<Hero> heroes) {
        setTitle("Heroes");
        HeroListAdapter adapter = new HeroListAdapter(heroes);
        UserHome fragment = new UserHome();
        mSections = new HeroListAdapter(heroes);
        ViewPager pager = new ViewPager(this);
         pager.setAdapter(new HeroFragAdapter(getSupportFragmentManager()));
        Intent intent = new Intent(this,UserHome.class);
        startActivity(intent);
//        getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void logout() {
        deviceStorageManager.getInstance(this).deleteUser();
        Intent intent = new Intent(this, DeviceStorageManager.class);
        startActivity(intent);
        finish();
    }


}
