package com.ifarma.ifarma.activities;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.PharmViewPagerAdapter;
import com.ifarma.ifarma.adapters.UserViewPagerAdapter;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.services.AuthenticationState;

import java.util.ArrayList;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        try {
            FirebaseController.saveCustomer("Mafra", "jvmafra.at.gmail.com", "88662443mg", "Santa Catarina", "1353", "58414470", "70175610401");
            FirebaseController.saveProduct("Dipirona", 2.50, "BAYER", "10mg, para dor de cabeça", true);
        } catch (InvalidUserDataException e) {
            e.printStackTrace();
        }

        initUI();
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

        FragmentPagerAdapter adapter;

        if (isPharmacy()){
            models.add(
                    new NavigationTabBar.Model.Builder(
                            ContextCompat.getDrawable(this, R.drawable.ic_store_mall_directory_white_24dp),
                            Color.parseColor("#00897B"))
                            .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_store_mall_directory_white_24dp))
                            .title("Medicamentos")
                            .build()
            );

            models.add(
                    new NavigationTabBar.Model.Builder(
                            ContextCompat.getDrawable(this, R.drawable.ic_inbox_white_24dp),
                            Color.parseColor("#00897B"))
                            .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_inbox_white_24dp))
                            .title("Pedidos")
                            .build()
            );

            models.add(
                    new NavigationTabBar.Model.Builder(
                            ContextCompat.getDrawable(this, R.drawable.ic_account_circle_white_24dp),
                            Color.parseColor("#00897B"))
                            .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_white_24dp))
                            .title("Eu")
                            .build()
            );

            adapter = new PharmViewPagerAdapter(getSupportFragmentManager(), getApplicationContext(), models);
        } else {
            models.add(
                    new NavigationTabBar.Model.Builder(
                            ContextCompat.getDrawable(this, R.drawable.ic_search_white_24dp),
                            Color.parseColor("#00897B"))
                            .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_search_white_24dp))
                            .title("Buscar")
                            .build()
            );

            models.add(
                    new NavigationTabBar.Model.Builder(
                            ContextCompat.getDrawable(this, R.drawable.ic_add_shopping_cart_white_24dp),
                            Color.parseColor("#00897B"))
                            .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_add_shopping_cart_white_24dp))
                            .title("Carrinho")
                            .build()
            );

            models.add(
                    new NavigationTabBar.Model.Builder(
                            ContextCompat.getDrawable(this, R.drawable.ic_account_circle_white_24dp),
                            Color.parseColor("#00897B"))
                            .selectedIcon(ContextCompat.getDrawable(this, R.drawable.ic_account_circle_white_24dp))
                            .title("Eu")
                            .badgeTitle("Deslogado")
                            .build()
            );

            adapter = new UserViewPagerAdapter(getSupportFragmentManager(), getApplicationContext(), models);
        }


        viewPager.setAdapter(adapter);

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

        navigationTabBar.setBadgeBgColor(Color.parseColor("#691A99"));
        navigationTabBar.setBadgeTitleColor(Color.WHITE);

        navigationTabBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                navigationTabBar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        NavigationTabBar.Model model;
                        if (!isAuthenticated() && !isPharmacy()) {
                            model = navigationTabBar.getModels().get(2);
                            model.showBadge();
                        } else {
                            if (isPharmacy()) {
                                model = navigationTabBar.getModels().get(1);
                                if (getPharmRequestsCount() > 0) {
                                    model.setBadgeTitle("+ " + getPharmRequestsCount());
                                    model.showBadge();
                                }
                            }
                        }
                    }
                }, 100);
            }
        }, 500);

    }

    private int getPharmRequestsCount(){
        return 0;
    }

    private boolean isPharmacy(){
        return true;
    }

}
