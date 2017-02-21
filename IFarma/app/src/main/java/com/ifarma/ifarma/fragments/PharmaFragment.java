package com.ifarma.ifarma.fragments;

import android.content.Context;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.adapters.PharmaMedSearchAdapter;
import com.ifarma.ifarma.adapters.PharmaSearchAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.decoration.DividerItemDecoration;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rafael on 2/20/2017.
 */

public class PharmaFragment extends Fragment {

    private View rootView;
    private ArrayList<Product> listItems;
    private PharmaMedSearchAdapter adapterMed;
    private RecyclerView _listView;
    private EditText _searchInput;
    private int searchTextSize;
    private Pharma pharm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        savedInstanceState = getArguments();


        rootView = inflater.inflate(R.layout.fragment_pharma_view, container, false);
        _searchInput = (EditText) rootView.findViewById(R.id.pharmaSearchMed);
        _listView = (RecyclerView) rootView.findViewById(R.id.listpharmaview);
        _listView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        _searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchInput.setFocusableInTouchMode(true);
                _searchInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        initMedList();

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
                    searchAdapter(s.toString());
                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < searchTextSize) {
                    initMedList();
                    searchAdapter(s.toString());
                }
            }

        });


        return rootView;
    }

    public void searchAdapter(String s){
        adapterMed.getFilter().filter(s);
    }

    public void initMedList(){
        listItems = new ArrayList<Product>();

        if(pharm != null){
            for(Map.Entry<String, Product> entry: pharm.getProducts().entrySet()){
                listItems.add(entry.getValue());
            }
        }

        adapterMed = new PharmaMedSearchAdapter(getActivity(), listItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        _listView.setHasFixedSize(true);
        _listView.setLayoutManager(mLayoutManager);


        _listView.setAdapter(adapterMed);

    }


}
