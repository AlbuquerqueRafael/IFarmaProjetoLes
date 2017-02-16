package com.ifarma.ifarma.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.ifarma.ifarma.R;

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
}
