package com.ifarma.ifarma.fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ifarma.ifarma.R;

import java.util.ArrayList;
import java.util.List;

public class AddProductFragment extends Fragment {

    private View rootView;
    private AppCompatButton _cadastrar;
    private EditText _nomeProdutoInput;
    private EditText _precoProdutoInput;
    private EditText _pesoProdutoInput;
    private EditText _quantidadeProdutoInput;
    private EditText _indicacoesProdutoInput;
    private EditText _contraindicacoesProdutoInput;
    private CheckBox _genericoCheckbox;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_addproduct, container, false);



        _cadastrar = (AppCompatButton) rootView.findViewById(R.id.btn_cadastrar);
        _nomeProdutoInput = (EditText) rootView.findViewById(R.id.input_nome_produto);
        _precoProdutoInput = (EditText) rootView.findViewById(R.id.input_preco_produto);
        _pesoProdutoInput = (EditText) rootView.findViewById(R.id.input_peso_produto);
        _quantidadeProdutoInput = (EditText) rootView.findViewById(R.id.input_quantidade_produto);
        _indicacoesProdutoInput = (EditText) rootView.findViewById(R.id.input_indicacoes);
        _contraindicacoesProdutoInput = (EditText) rootView.findViewById(R.id.input_contra_indicacoes);
        _genericoCheckbox = (CheckBox) rootView.findViewById(R.id.checkbox_generico);

        setUpSpinner();

        ImageView _backButton = (ImageView) rootView.findViewById(R.id.back_btn_cadastro_medicamento);

        /*final FrameLayout _frameLayout = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
        _frameLayout.setVisibility(View.VISIBLE);

        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                _pagerLayout.setVisibility(View.VISIBLE);
                _frameLayout.setVisibility(View.GONE);

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new AccountFragment());
                fragmentTransaction.commit();
            }
        });*/

        return rootView;
    }

    private void setUpSpinner() {
        spinner = (Spinner) rootView.findViewById(R.id.spinner);

        List<String> pesos = new ArrayList<String>();
        pesos.add("mg");
        pesos.add("g");
        pesos.add("ml");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.simplespinneritem, pesos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);


        AdapterView.OnItemSelectedListener adapter = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinner.setOnItemSelectedListener(adapter);
    }

}
