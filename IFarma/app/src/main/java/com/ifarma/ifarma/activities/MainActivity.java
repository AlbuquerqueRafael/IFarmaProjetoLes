package com.ifarma.ifarma.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.authentication.Constants;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.adapters.PharmViewPagerAdapter;
import com.ifarma.ifarma.adapters.UserViewPagerAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.exceptions.InvalidProductDataException;
import com.ifarma.ifarma.fragments.pharmacy.EditInfoPharmaFragment;
import com.ifarma.ifarma.fragments.user.EditInfoUserFragment;
import com.ifarma.ifarma.model.OrdenationType;
import com.ifarma.ifarma.model.OrderStatus;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AdapterService;
import com.ifarma.ifarma.util.Utils;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String FLAG_LOGGED = "isLogged";
    public static final String FLAG_EMAIL = "currentEmail";
    private boolean isPharmacy = false;
    private boolean incompleteRegister = false;
    private MainActivity mainActivity;
    private FragmentPagerAdapter adapter;
    private ViewPager viewPager;

    public void clearApplicationData() {
        File cacheDirectory = getCacheDir();
        File applicationDirectory = new File(cacheDirectory.getParent());
        if (applicationDirectory.exists()) {
            String[] fileNames = applicationDirectory.list();
            for (String fileName : fileNames) {
                if (!fileName.equals("lib")) {
                    deleteFile(new File(applicationDirectory, fileName));
                }
            }
        }
    }

    public static boolean deleteFile(File file) {
        boolean deletedAll = true;
        if (file != null) {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    deletedAll = deleteFile(new File(file, children[i])) && deletedAll;
                }
            } else {
                deletedAll = file.delete();
            }
        }

        return deletedAll;
    }

    public void criaFarmacia(String nome){
        Firebase firebaseRef = FirebaseController.getFirebase();
        Firebase pharmarciesReference = firebaseRef.child("pharmacies");

        Pharma pharma = new Pharma(nome, nome+"@gmail.com", "Rua", "12", "5815");

        String emailNode = Utils.convertEmail(pharma.getEmail());

        pharmarciesReference.child(emailNode).setValue(pharma);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        clearApplicationData();



        Product p = null;
        for (int i = 0; i < 10; i++){
            try {
                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia Dias");
            } catch (InvalidProductDataException e) {
                e.printStackTrace();
            }

            FirebaseController.newProduct("farmaciadias@gmaildotcom", p);
        }



//        criaFarmacia("farmaciax");
//        for (int i = 0; i < 150; i++){
//            try {
//                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia X");
//            } catch (InvalidProductDataException e) {
//                e.printStackTrace();
//            }
//
//            FirebaseController.newProduct("farmaciax@gmaildotcom", p);
//        }
//
//        criaFarmacia("farmaciay");
//        for (int i = 0; i < 150; i++){
//            try {
//                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia Y");
//            } catch (InvalidProductDataException e) {
//                e.printStackTrace();
//            }
//
//            FirebaseController.newProduct("farmaciay@gmaildotcom", p);
//        }
//
//        criaFarmacia("farmaciaoi");
//        for (int i = 0; i < 150; i++){
//            try {
//                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia Oi");
//            } catch (InvalidProductDataException e) {
//                e.printStackTrace();
//            }
//
//            FirebaseController.newProduct("farmaciaoi@gmaildotcom", p);
//        }
//
//        criaFarmacia("farmaciaa");
//        for (int i = 0; i < 150; i++){
//            try {
//                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia A");
//            } catch (InvalidProductDataException e) {
//                e.printStackTrace();
//            }
//
//            FirebaseController.newProduct("farmaciaa@gmaildotcom", p);
//        }
//
//        criaFarmacia("farmaciab");
//        for (int i = 0; i < 150; i++){
//            try {
//                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia B");
//            } catch (InvalidProductDataException e) {
//                e.printStackTrace();
//            }
//
//            FirebaseController.newProduct("farmaciab@gmaildotcom", p);
//        }
//
//        criaFarmacia("farmaciaz");
//        for (int i = 0; i < 150; i++){
//            try {
//                p = new Product("Nome" + i, 2.9, "LSD", "Descricao", true, "farmaciadias@gmaildotcom", "Farmacia Z");
//            } catch (InvalidProductDataException e) {
//                e.printStackTrace();
//            }
//
//            FirebaseController.newProduct("farmaciaz@gmaildotcom", p);
//        }


//        for (int i = 0; i < 1000; i++){
//            System.out.println(i);
//            FirebaseController.retrieveProducts(new OnMedGetDataListener() {
//
//                @Override
//                public void onStart() {
//                }
//
//                @Override
//                public void onSuccess(List<Product> lista) {
//                    System.out.println("OnSuccessActivity");
//
//                }
//
//            });
//        }

        isPharmacy = false;

        if (getIntent().hasExtra("isPharmacy")) {
            isPharmacy = getIntent().getExtras().getBoolean("isPharmacy");
        }

        if (getIntent().hasExtra("incompleteRegister")) {
            if (getIntent().getExtras().getBoolean("incompleteRegister")){
                incompleteRegister = getIntent().getExtras().getBoolean("incompleteRegister");

                Fragment fragment;

                if (isPharmacy){
                    fragment = new EditInfoPharmaFragment();
                } else {
                    fragment = new EditInfoUserFragment();
                }

                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
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
        viewPager = (ViewPager) findViewById(R.id.vp_horizontal_ntb);
        ArrayList<NavigationTabBar.Model> models;

        final NavigationTabBar navigationTabBar = (NavigationTabBar) findViewById(R.id.ntb_horizontal);

        if (isPharmacy()){
            models = new ArrayList<>();
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
            models = new ArrayList<>();
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

        AdapterService.setViewPager(viewPager);
        AdapterService.setNavigationTabBar(navigationTabBar);
        AdapterService.setPagerAdapter(adapter);

        navigationTabBar.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {}

            @Override
            public void onPageSelected(final int position) {
                viewPager.setAdapter(adapter);
                navigationTabBar.setViewPager(viewPager, position);
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
                            model = navigationTabBar.getModels().get(navigationTabBar.getModels().size() - 1);
                            model.showBadge();
                        }
                    }
                }, 100);
            }
        }, 500);

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

    @Override
    public void onBackPressed() {
        Fragment f = this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (f instanceof EditInfoPharmaFragment || f instanceof  EditInfoUserFragment){
            Toast.makeText(this, "Você precisa terminar o cadastro", Toast.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
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
