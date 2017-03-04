package com.ifarma.ifarma.fragments.pharmacy;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.MedicineSearchAdapter;
import com.ifarma.ifarma.adapters.PharmaSearchAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnMedGetDataListener;
import com.ifarma.ifarma.controllers.OnPharmaGetDataListener;
import com.ifarma.ifarma.decoration.DividerItemDecoration;
import com.ifarma.ifarma.fragments.user.SearchFragment;
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
    private MedicineSearchAdapter adapterMed;
    private RecyclerView _listView;
    private TextView _textView;
    private ImageView _imageView;
    private EditText _searchInput;
    private int searchTextSize;
    private Pharma pharm;
    private FrameLayout _frameLayout;
    private ImageView _infoButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null) {
            container.removeAllViews();
        }

        savedInstanceState = getArguments();
        pharm = (Pharma) savedInstanceState.getSerializable("pharm");

        rootView = inflater.inflate(R.layout.fragment_pharma_view, container, false);
        _imageView = (ImageView) rootView.findViewById(R.id.pharmaLogo);
        _textView = (TextView) rootView.findViewById(R.id.pharmName);
        _searchInput = (EditText) rootView.findViewById(R.id.pharmaSearchMed);
        _searchInput = (EditText) rootView.findViewById(R.id.pharmaSearchMed);
        _listView = (RecyclerView) rootView.findViewById(R.id.listpharmaview);
        _infoButton = (ImageView) rootView.findViewById(R.id.filter_btn_pharma);
        _listView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));

        ImageView _backButton = (ImageView) rootView.findViewById(R.id.back_btn);

        _frameLayout = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
        _frameLayout.setVisibility(View.VISIBLE);

        _infoButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("Informações da Farmácia")
                        .setMessage("Contato: " + pharm.getEmail() + "\nEndereço: " + pharm.getAddress() + "\nCEP: " + pharm.getCep())
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                _pagerLayout.setVisibility(View.VISIBLE);
                _frameLayout.setVisibility(View.GONE);

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new SearchFragment());
                fragmentTransaction.commit();
            }
        });

        _textView.setText(pharm.getName());
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

        if(pharm.getProducts() != null){
            for(Map.Entry<String, Product> entry: pharm.getProducts().entrySet()){
                listItems.add(entry.getValue());
            }
        }

        adapterMed = new MedicineSearchAdapter(getActivity(), listItems, rootView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        _listView.setHasFixedSize(true);
        _listView.setLayoutManager(mLayoutManager);


        _listView.setAdapter(adapterMed);

    }


}
