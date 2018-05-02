package com.example.charbel.dota;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener {
    private DeviceStorageManager deviceStorageManager;
    private HeroListAdapter mSections;
    private RecyclerView mView;
    private LoginFragment mLoginfragment;
    private RegisterFragment mRegisterFragment;
    private TextView tvFirst;
    private TextView tvSecond;
    private TextView logout;
    private TextView register_go;
    private boolean tvFirstIsCheck=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        deviceStorageManager = new DeviceStorageManager(this);
        super.onCreate(savedInstanceState);
        if(deviceStorageManager.getUser()==null){
            setContentView(R.layout.base_frag);
            initFragment();
            showFirstFragment();

            tvFirst= findViewById(R.id.tvFirst);
            tvSecond= findViewById(R.id.tvSecond);
            register_go = findViewById(R.id.register_text);

            tvFirst.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firstSelected();
                    showFirstFragment();
                }
            });
            tvSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    secondSelected();
                    showSecondFragment();
                }
            });
        }
        else{
            setContentView(R.layout.hero_frag);
            displayHeroes();
        }
    }
    private void firstSelected(){

        tvFirst= findViewById(R.id.tvFirst);
        tvSecond= findViewById(R.id.tvSecond);
        tvFirst.setBackgroundResource(R.drawable.bg_selected);
        tvSecond.setBackgroundResource(R.drawable.bg_un_selected);
        tvFirstIsCheck=true;
    }

    private void secondSelected(){

        tvFirst= findViewById(R.id.tvFirst);
        tvSecond= findViewById(R.id.tvSecond);
        tvSecond.setBackgroundResource(R.drawable.bg_selected);
        tvFirst.setBackgroundResource(R.drawable.bg_un_selected);
        tvFirstIsCheck=false;
    }

    private void initFragment() {
        mLoginfragment= new LoginFragment();
        mLoginfragment.setDeviceStorageManager(deviceStorageManager);
        mRegisterFragment= new RegisterFragment();
    }


    private void showFirstFragment() {
        setTitle("Login");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, mLoginfragment)
                .commit();
        firstSelected();
    }

    private void showSecondFragment(){

        setTitle("Register");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.main_layout, mRegisterFragment)
                .commit();
        firstSelected();
        secondSelected();
    }

    @Override public void onBackPressed() {
        if(!tvFirstIsCheck) {
            firstSelected();
            tvFirstIsCheck=true;;
        }
        super.onBackPressed();
    }
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
                name = (TextView) findViewById(R.id.hero_name);
                RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.list);
                mRecyclerView.setHasFixedSize(false);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getParent());
                mRecyclerView.setLayoutManager(mLayoutManager);
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
    }

    private void logout() {
        deviceStorageManager.getInstance(this).deleteUser();
        Intent intent = new Intent(this, DeviceStorageManager.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onRequestRegister() {

    }
}
