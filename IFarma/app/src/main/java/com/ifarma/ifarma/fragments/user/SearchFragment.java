package com.ifarma.ifarma.fragments.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Context;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.adapters.PharmaSearchAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.decoration.DividerItemDecoration;
import com.ifarma.ifarma.decoration.RecyclerItemClickListener;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import devlight.io.library.ntb.NavigationTabBar;

public class SearchFragment extends Fragment {

    private View rootView;
    private Product[] items;
    private ArrayList<Product> listItems;
    private ArrayList<Pharma> listPharmaItems;
    private MedicineSearchAdapter adapterMed;
    private PharmaSearchAdapter adapterPharma;
    private RecyclerView _listView;
    private EditText _searchInput;
    private RadioGroup _radioGroup;
    private int searchTextSize;
    private FloatingActionButton _filterButton;
    private String option;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        _searchInput = (EditText) rootView.findViewById(R.id.txtsearch);
        _listView = (RecyclerView) rootView.findViewById(R.id.listview);
        _listView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        _radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroup);

        RadioButton initButton = (RadioButton) rootView.findViewById(R.id.medicineButton);
        initButton.setChecked(true);

        _searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchInput.setFocusableInTouchMode(true);
                _searchInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        option = "Medicine";
        radioGroupListener();
        initList();

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
                    initList();

                }

                else{
                    searchAdapter(s.toString());
                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < searchTextSize) {
                    initList();
                    searchAdapter(s.toString());
                }
            }

        });

        return rootView;
    }

    public void initList(){
        if(option.equals("Medicine")){
            initMedList();
        }else{
            initPharmaList();
        }
    }

    public void searchAdapter(String s){
        if(option.equals("Medicine")){
            adapterMed.getFilter().filter(s);
        }else{
            adapterPharma.getFilter().filter(s);
        }
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

                for (Product p : lista){{
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

    public void initPharmaList(){
        listPharmaItems = new ArrayList<Pharma>();

        FirebaseController.retrievePharmacies(new OnPharmaGetDataListener() {

            @Override
            public void onStart() {
            }


            @Override
            public void onSuccess(List<Pharma> lista) {
                listPharmaItems = new ArrayList<Pharma>();

                for (Pharma p : lista){{
                    listPharmaItems.add(p);
                }}

                adapterPharma = new PharmaSearchAdapter(getActivity(), listPharmaItems);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                _listView.setHasFixedSize(true);
                _listView.setLayoutManager(mLayoutManager);
                _listView.setAdapter(adapterPharma);
            }

        });

    }

    public void radioGroupListener(){
        _radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.medicineButton) {
                    option = "Medicine";
                } else {
                    option = "Pharma";
                }

                _searchInput.setText("");
                initList();
            }

        });
    }

    private void initDialog(final ProgressDialog dialog){
        dialog.setMessage("Carregando dados...");
        dialog.setCancelable(false);
        dialog.show();


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        dialog.dismiss();
                    }
                }, 3000);

    }

    private void closeDialog(ProgressDialog dialog){
        dialog.dismiss();
    }
}
