package com.ifarma.ifarma.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.model.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class SearchFragment extends Fragment {

    private View rootView;
    private Product[] items;
    private ArrayList<Product> listItems;
    private MedicineSearchAdapter adapter;
    private ListView listView;
    private EditText editText;
    private int searchTextSize;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);

        listView = (ListView) rootView.findViewById(R.id.listview);
        editText=(EditText)rootView.findViewById(R.id.txtsearch);
        initList();

        editText.addTextChangedListener(new TextWatcher() {

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
                {new Product("Dorflex", 20, "novaldina", "Para dor de cabeça", true),
                        new Product("Dipirona", 30, "novaldina", "Para febre", true)};

        listItems = new ArrayList<Product>(Arrays.asList(items));
        adapter = new MedicineSearchAdapter(getActivity(), listItems);

        listView.setAdapter(adapter);

    }
}
