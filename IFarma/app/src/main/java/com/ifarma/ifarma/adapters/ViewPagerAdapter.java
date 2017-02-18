package com.ifarma.ifarma.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.fragments.AccountFragment;
import com.ifarma.ifarma.fragments.AddProductFragment;
import com.ifarma.ifarma.fragments.CartFragment;
import com.ifarma.ifarma.fragments.SearchFragment;

import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Gabriel on 10/02/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<NavigationTabBar.Model> models;

    private static LayoutInflater inflater = null;

    public ViewPagerAdapter(FragmentManager fragmentManager, Context context, List<NavigationTabBar.Model> models) {
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
                return new AddProductFragment();
            default:
                return new SearchFragment();
        }
    }

    @Override
    public int getCount() {
        return models.size();
    }

}

