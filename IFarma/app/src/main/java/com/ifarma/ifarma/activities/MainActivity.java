package com.ifarma.ifarma.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.ViewPagerAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnGetDataListener;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

////        try {
//            //FirebaseController.savePharmacy("Dias", "dias@gmail.com", "88662443mg", "Santa Catarina", "1353", "58414470", "00000000000000");
//            Product product = new Product("Dipirona", 2.5, "A", "B", true);
//            FirebaseController.newProduct("dias@gmaildotcom", product);
//
////        } catch (InvalidUserDataException e) {
////            e.printStackTrace();
////        }


        initUI();

        FirebaseController.retrieveProducts(new OnGetDataListener() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(final List<Product> lista) {
                System.out.println(lista.size());
                int i = 0;

                for (Product p : lista){{
                    System.out.println("PRODUTO #" + i);
                    System.out.println(p.getNameProduct());
                    System.out.println(p.getPrice());
                    System.out.println("------------------------------------------------");
                    i++;
                }}
            }
        });
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

        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext(), models));

        navigationTabBar.setModels(models);
        navigationTabBar.setViewPager(viewPager, 0);
        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {}

            @Override
            public void onPageSelected(final int position) {
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
                final NavigationTabBar.Model model = navigationTabBar.getModels().get(2);
                navigationTabBar.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!isAuthenticated())
                            model.showBadge();
                    }
                }, 100);
            }
        }, 500);

    }

}
