package com.ifarma.ifarma.fragments.pharmacy;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnCurrentPharmaGetDataListener;
import com.ifarma.ifarma.model.Pharma;


public class EditProductFragment extends Fragment {

    private View rootView;
    private EditText _nameProductInput;
    private EditText _priceProductInput;
    private EditText _labProductInput;
    private EditText _descriptionProductInput;
    private CheckBox _genericoCheckbox;
    private AppCompatButton _salvar;
    private Pharma pharma;
    private ImageView _backButton;

    private String nameProductActual;
    private String pharmaActual;

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_EMAIL = "currentEmail";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_edit_product, container, false);

        _nameProductInput = (EditText) rootView.findViewById(R.id.input_edit_name_product);
        _priceProductInput = (EditText) rootView.findViewById(R.id.input_edit_price_product);
        _labProductInput = (EditText) rootView.findViewById(R.id.input_edit_lab_product);
        _descriptionProductInput = (EditText) rootView.findViewById(R.id.input_edit_description_product);
        _genericoCheckbox = (CheckBox) rootView.findViewById(R.id.checkbox_edit_generico);

        Bundle bundle = getArguments();
        initProduct(bundle);

        _salvar = (AppCompatButton) rootView.findViewById(R.id.btn_edit);

        _backButton = (ImageView) rootView.findViewById(R.id.back_btn_edit_product);

        final FrameLayout _frameLayout = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
        _frameLayout.setVisibility(View.VISIBLE);

        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                _pagerLayout.setVisibility(View.VISIBLE);
                _frameLayout.setVisibility(View.GONE);

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new MedicinesFragment());
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    private void initProduct(Bundle b) {
        nameProductActual = b.getString("nameProduct");
        pharmaActual = b.getString("pharmacyID");

        _nameProductInput.setText(nameProductActual.trim());
        _priceProductInput.setText(Double.toString(b.getDouble("priceProduct")));
        _labProductInput.setText(b.getString("labProduct"));
        _descriptionProductInput.setText(b.getString("descriptionProduct"));
        _genericoCheckbox.setChecked(b.getBoolean("genericProduct"));

    }
}
