package com.ifarma.ifarma.services;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Adapter;
import android.widget.BaseAdapter;

import com.ifarma.ifarma.adapters.CartListAdapter;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Gabriel on 04/03/2017.
 */

public class AdapterService {

    private static ViewPager viewPager;
    private static PagerAdapter adapter;
    private static NavigationTabBar navigationTabBar;

    public static void setViewPager(ViewPager viewPager) {
        AdapterService.viewPager = viewPager;
    }

    public static void setNavigationTabBar(NavigationTabBar navigationTabBar) {
        AdapterService.navigationTabBar = navigationTabBar;
    }

    public static void setPagerAdapter(PagerAdapter pagerAdapter){
        AdapterService.adapter = pagerAdapter;
    }

    public static void reloadAdapter(int position){
        viewPager.setAdapter(adapter);
        navigationTabBar.setViewPager(viewPager, position);
    }

}
