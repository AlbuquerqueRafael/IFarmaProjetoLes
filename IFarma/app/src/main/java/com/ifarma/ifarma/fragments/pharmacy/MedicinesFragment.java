package com.ifarma.ifarma.fragments.pharmacy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifarma.ifarma.R;

public class MedicinesFragment extends Fragment {

    private View rootView;
    private FloatingActionButton _fabAddProduct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_medicines, container, false);

        _fabAddProduct = (FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
        _fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new AddProductFragment());
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}
