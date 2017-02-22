package com.ifarma.ifarma.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.PharmViewPagerAdapter;
import com.ifarma.ifarma.adapters.PharmaSearchAdapter;
import com.ifarma.ifarma.adapters.UserViewPagerAdapter;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.fragments.user.SearchFragment;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AuthenticationService;
import com.ifarma.ifarma.services.AuthenticationState;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";
    public static final String FLAG_EMAIL = "currentEmail";
    public boolean isPharmacy = false;
    private MainActivity mainActivity;
    private int oldPage = 0;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        isPharmacy = false;
        
        if (getIntent().hasExtra("isPharmacy")) {
            isPharmacy = getIntent().getExtras().getBoolean("isPharmacy");
        }

        if (!isPharmacy)
            checkIsPharmacy();

        initUI();
    }


    private boolean isAuthenticated(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean defaultState = false;
        return prefs.getBoolean(FLAG_LOGGED, defaultState);
    }

    private void initUI(){
        final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);

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
            public void onPageSelected(final int position) {
                Fragment fragment = adapter.getItem(position);
                if(fragment instanceof SearchFragment){
                    android.support.v4.app.FragmentTransaction fragmentTransaction = mainActivity.getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.activity_main, fragment);
                    fragmentTransaction.commit();

                }

                navigationTabBar.getModels().get(position).hideBadge();
            }
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

    private void checkIsPharmacy(){
        final List<String> pharmList = new ArrayList<>();
        final String email = currentEmail();

        FirebaseController.retrievePharmacies(new OnPharmaGetDataListener() {

            @Override
            public void onStart() {}


            @Override
            public void onSuccess(List<Pharma> lista) {
                for (Pharma p : lista){
                    pharmList.add(p.getEmail());

                    if (p.getEmail().equals(email)){
                        isPharmacy = true;

                        finish();
                        Intent mIntent = new Intent(mainActivity, MainActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putBoolean("isPharmacy", true);
                        mIntent.putExtras(mBundle);
                        startActivity(mIntent);
                    }
                }
            }

        });
    }

    private boolean isPharmacy(){
        return this.isPharmacy;
    }

    private String currentEmail(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String defaultState = "";
        return prefs.getString(FLAG_EMAIL, defaultState);
    }

}
