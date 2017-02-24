package com.ifarma.ifarma.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;

import com.ifarma.ifarma.fragments.pharmacy.DemandFragment;
import com.ifarma.ifarma.fragments.pharmacy.MedicinesFragment;
import com.ifarma.ifarma.fragments.pharmacy.PharmacyFragment;

import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Gabriel on 10/02/2017.
 */

public class PharmViewPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<NavigationTabBar.Model> models;

    private static LayoutInflater inflater = null;

    public PharmViewPagerAdapter(FragmentManager fragmentManager, Context context, List<NavigationTabBar.Model> models) {
        super(fragmentManager);

        this.models = models;
        this.context = context;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MedicinesFragment();
//            case 1:
//                return new DemandFragment();
            case 1:
                return new PharmacyFragment();
            default:
                return new MedicinesFragment();
        }
    }

    @Override
    public int getCount() {
        return models.size();
    }

}

