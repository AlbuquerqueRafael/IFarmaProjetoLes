package com.ifarma.ifarma.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnGetDataListener;
import com.ifarma.ifarma.model.Product;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.view.inputmethod.InputMethodManager;

public class SearchFragment extends Fragment {

    private View rootView;
    private Product[] items;
    private ArrayList<Product> listItems;
    private MedicineSearchAdapter adapter;
    private RecyclerView _listView;
    private EditText _searchInput;
    private int searchTextSize;
    private FloatingActionButton _filterButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        _searchInput = (EditText) rootView.findViewById(R.id.txtsearch);
        _listView = (RecyclerView) rootView.findViewById(R.id.listview);

        _searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchInput.setFocusableInTouchMode(true);
                _searchInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        });

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
                    adapter.getFilter().filter(s.toString());

                }

            }



            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() < searchTextSize) {
                    initList();
                    adapter.getFilter().filter(s.toString());
                }
            }

        });


        return rootView;
    }

    public void initList(){

        items=new Product[]
                {new Product("Dorflex", 20, "novaldina", "Para dor de cabeça", true, "dias@gmaildotcom"),
                        new Product("Dipirona", 30, "novaldina", "Para febre", true, "dias@gmaildotcom"),
                        new Product("Salompas", 40, "ultrafarma", "Para dor nas costas", true, "dias@gmaildotcom")};

        listItems = new ArrayList<Product>();

        FirebaseController.retrieveProducts(new OnGetDataListener() {

            @Override
            public void onStart() {
            }

            @Override
            public void onSuccess(final List<Product> lista) {

                for (Product p : lista){{
                    listItems.add(p);
                }}

                adapter = new MedicineSearchAdapter(getActivity(), listItems);

                _listView.setAdapter(adapter);
                _listView.setLayoutManager(new LinearLayoutManager(getActivity()));
                _listView.setHasFixedSize(true);

            }
        });

    }
}
