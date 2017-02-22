package com.ifarma.ifarma.fragments.pharmacy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.decoration.DividerItemDecoration;
import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MedicinesFragment extends Fragment {

    private View rootView;
    private FloatingActionButton _fabAddProduct;
    private RecyclerView _listView;
    private ArrayList<Product> listItems;
    private MedicineSearchAdapter adapterMed;
    private EditText _searchInput;
    private int searchTextSize;
    public static final String FLAG_EMAIL = "currentEmail";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_medicines, container, false);

        _listView = (RecyclerView) rootView.findViewById(R.id.listview);
        _listView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        _searchInput = (EditText) rootView.findViewById(R.id.search_box);

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

        initMedList();

        _searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchInput.setFocusableInTouchMode(true);
                _searchInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        _searchInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int
                    after) {
                searchTextSize = s.toString().length();
            }



            @Override
            public void onTextChanged(CharSequence s, int start, int before, int
                    count) {

                if(s.toString().equals("")){
                    initMedList();

                }

                else{
                    adapterMed.getFilter().filter(s);
                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < searchTextSize) {
                    initMedList();
                    adapterMed.getFilter().filter(s);

                }
            }

        });

        return rootView;
    }


    public void initMedList(){
        listItems = new ArrayList<Product>();
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        initDialog(dialog);

        FirebaseController.retrieveProducts(new OnMedGetDataListener() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(List<Product> lista) {
                listItems = new ArrayList<Product>();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String defaultState = "";
                String email =  prefs.getString(FLAG_EMAIL, defaultState);
                email = email.replace(".", "dot");

                for (Product p : lista){{
                    if (email.equals(p.getPharmacyId()))
                        listItems.add(p);
                }}

                adapterMed = new MedicineSearchAdapter(getActivity(), listItems);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                _listView.setHasFixedSize(true);
                _listView.setLayoutManager(mLayoutManager);


                _listView.setAdapter(adapterMed);
                closeDialog(dialog);
            }

        });
    }

    private void initDialog(ProgressDialog dialog){
        dialog.setMessage("Carregando dados...");
        dialog.setCancelable(false);
        dialog.show();
    }

    private void closeDialog(ProgressDialog dialog){
        dialog.dismiss();
    }
}
