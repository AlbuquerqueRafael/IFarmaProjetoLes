package com.ifarma.ifarma.fragments.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.adapters.PharmaSearchAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.decoration.DividerItemDecoration;
import com.ifarma.ifarma.decoration.RecyclerItemClickListener;
import com.ifarma.ifarma.model.OrdenationType;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.SingleShotLocationProvider;
import com.ifarma.ifarma.util.Utils;

import android.support.design.widget.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import android.view.inputmethod.InputMethodManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    private String option;
    private FloatingActionButton _filterButton;
    private OrdenationType ordernType;
    private LocationManager locationManager;
    private LocationListener locationListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_search, container, false);
        _searchInput = (EditText) rootView.findViewById(R.id.txtsearch);
        _listView = (RecyclerView) rootView.findViewById(R.id.listview);
        _listView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        _radioGroup = (RadioGroup) rootView.findViewById(R.id.radiogroup);
       _filterButton = (FloatingActionButton) rootView.findViewById(R.id.filter_btn);

        RadioButton initButton = (RadioButton) rootView.findViewById(R.id.medicineButton);
        initButton.setChecked(true);
        ordernType = OrdenationType.UNDEFIN;

        _searchInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _searchInput.setFocusableInTouchMode(true);
                _searchInput.requestFocus();
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(_searchInput, InputMethodManager.SHOW_IMPLICIT);
            }
        });




        _filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                CharSequence[] array;
                if(option == "Medicine"){
                    array = new CharSequence[]{"Ordenação por preço", "Sem ordenação"};
                }else{
                    array = new CharSequence[]{"Ordenação por distancia", "Sem ordenação"};
                }


                builder.setTitle("Escolha o tipo de ordenação: ");

                builder.setItems(array,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which == 0){
                                    ordernType = OrdenationType.PRICE;
                                }else if(which == 1){
                                    ordernType = OrdenationType.UNDEFIN;
                                }


                                dialog.dismiss();
                                initList();

                                //rest of your implementation
                            }
                        });

                builder.show();

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
       try{
            if(option.equals("Medicine")){
                adapterMed.getFilter().filter(s);
            }else{
                adapterPharma.getFilter().filter(s);
            }
        }catch(NullPointerException e){

        }
    }

    public void initMedList(){
        listItems = new ArrayList<Product>();

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

                if(ordernType == OrdenationType.PRICE){
                    Utils.orderByPrice(listItems);
                }

                adapterMed = new MedicineSearchAdapter(getActivity(), listItems, rootView, true);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                _listView.setHasFixedSize(true);
                _listView.setLayoutManager(mLayoutManager);


                _listView.setAdapter(adapterMed);
                searchAdapter(_searchInput.getText().toString());

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
                final ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                        "Carregando. Espere, por favor...", true);
                dialog.show();

                for (Pharma p : lista){{
                    listPharmaItems.add(p);
                }}

                if(ordernType == OrdenationType.PRICE){
                    SingleShotLocationProvider.requestSingleUpdate(getContext(),
                            new SingleShotLocationProvider.LocationCallback() {
                                @Override
                                public void onNewLocationAvailable(Location location) {
                                    Log.v("tag", "erROOOOO");
                                    orderByLocalization(listPharmaItems, location);
                                    adapterPharma = new PharmaSearchAdapter(getActivity(), listPharmaItems);
                                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                                    _listView.setHasFixedSize(true);
                                    _listView.setLayoutManager(mLayoutManager);
                                    _listView.setAdapter(adapterPharma);
                                    dialog.dismiss();
                                }
                            });
                }else{
                    adapterPharma = new PharmaSearchAdapter(getActivity(), listPharmaItems);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    _listView.setHasFixedSize(true);
                    _listView.setLayoutManager(mLayoutManager);
                    _listView.setAdapter(adapterPharma);
                    dialog.dismiss();
                }


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

                ordernType = OrdenationType.UNDEFIN;
                _searchInput.setText("");
                initList();
            }

        });
    }

    private void orderByLocalization(List<Pharma> pharma, Location location){
        List<Double> distancia = new ArrayList<Double>();
        calculaDistancia(distancia, pharma, location);

        for(int i = 0; i < distancia.size(); i++){
            for(int j = 0; j < distancia.size()-1; j++){
                if(distancia.get(j) > distancia.get(j+1)){
                    Collections.swap(pharma, j, j+1);
                    Collections.swap(distancia, j, j+1);
                }
            }
        }

    }

    private void calculaDistancia(List<Double> distancia, List<Pharma> pharma, Location location){
        for(int i = 0; i < pharma.size(); i++){
            Geocoder geocoder = new Geocoder(getContext());
            try {
                List<Address> enderecos = geocoder.getFromLocationName(pharma.get(i).getAddress() + " Campina grande" , 1);
                if (enderecos.size() > 0) {
                    Double longitude = enderecos.get(0).getLongitude();
                    Double latitude = enderecos.get(0).getLatitude();
                    Log.v("tag", "coordenadas " + enderecos.get(0).getLatitude() + ", " + enderecos.get(0).getLongitude());
                    Double dist = Math.sqrt(Math.pow(longitude - location.getLongitude(), 2) +
                                  Math.pow(latitude - location.getLatitude(), 2));
                    Log.v("tag", pharma.get(i).getName() + " " + dist + "");
                    distancia.add(dist);
                } else {
                    Log.v("tag", "erROOOOO");
                    distancia.add(Double.MAX_VALUE);
                }
            }catch (IOException e){
                Log.v("tag", "erROOOssssssssssssssssssOO");
            }
        }


    }


}


