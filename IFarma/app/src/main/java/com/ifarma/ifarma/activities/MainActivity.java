package com.ifarma.ifarma.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.ViewPagerAdapter;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";
    public static final String USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isAuthenticated()) {
            initUI();
        } else {
            startLogin();
        }
    }

    private void startLogin(){
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private boolean isAuthenticated(){
        SharedPreferences sharedPref = getSharedPreferences(PREFS_NAME, 0);
        boolean defaultState = false;

        return sharedPref.getBoolean(FLAG_LOGGED, defaultState);
    }

    private void initUI(){
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);

        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_search_white_24dp),
                        Color.parseColor("#D32F2F"))
                        .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_search_white_24dp))
                        .title("Buscar")
                        .badgeTitle("Search")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_add_shopping_cart_white_24dp),
                        Color.parseColor("#D32F2F"))
                        .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_add_shopping_cart_white_24dp))
                        .title("Carrinho")
                        .badgeTitle("Car")
                        .build()
        );

        models.add(
                new NavigationTabBar.Model.Builder(
                        ContextCompat.getDrawable(this, R.drawable.ic_account_circle_white_24dp),
                        Color.parseColor("#D32F2F"))
                        .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_white_24dp))
                        .title("Eu")
                        .badgeTitle("Me")
                        .build()
        );

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext(), models));

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {}

            @Override
            public void onPageSelected(final int position) {}

            @Override
            public void onPageScrollStateChanged(final int state) {}
        });

    }

}
