package com.ifarma.ifarma.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import com.ifarma.ifarma.R;
import com.google.firebase.auth.FirebaseAuth;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.fragments.AccountFragment;
import com.ifarma.ifarma.fragments.pharmacy.AddProductFragment;
import com.ifarma.ifarma.fragments.user.CartFragment;
import com.ifarma.ifarma.fragments.user.SearchFragment;
import com.ifarma.ifarma.fragments.user.UserFragment;
import com.ifarma.ifarma.services.AuthenticationService;

import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Gabriel on 10/02/2017.
 */

public class UserViewPagerAdapter extends FragmentPagerAdapter {
    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";

    private Context context;
    private List<NavigationTabBar.Model> models;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private AuthenticationController authCtrl;
    private FirebaseAuth fbAuth;
    private AuthenticationService authService;
    boolean isLogged;

    private static LayoutInflater inflater = null;

    public UserViewPagerAdapter(FragmentManager fragmentManager, Context context, List<NavigationTabBar.Model> models) {
        super(fragmentManager);

        this.models = models;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new SearchFragment();
            case 1:
                return new CartFragment();
            case 2:
                if (isAuthenticated())
                    return new UserFragment();
                return new AccountFragment();
            default:
                return new SearchFragment();
        }
    }

    private boolean isAuthenticated(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        boolean defaultState = false;
        return prefs.getBoolean(FLAG_LOGGED, defaultState);
    }


    @Override
    public int getCount() {
        return models.size();
    }
}

