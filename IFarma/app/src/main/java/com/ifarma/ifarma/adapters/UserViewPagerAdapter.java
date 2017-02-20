package com.ifarma.ifarma.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import com.ifarma.ifarma.fragments.AccountFragment;
import com.ifarma.ifarma.fragments.user.CartFragment;
import com.ifarma.ifarma.fragments.user.SearchFragment;

import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Gabriel on 10/02/2017.
 */

public class UserViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<NavigationTabBar.Model> models;

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
                return new AccountFragment();
            default:
                return new SearchFragment();
        }
    }

    @Override
    public int getCount() {
        return models.size();
    }

}

