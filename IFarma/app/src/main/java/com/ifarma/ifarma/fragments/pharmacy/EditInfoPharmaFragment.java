package com.ifarma.ifarma.fragments.pharmacy;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnCurrentPharmaGetDataListener;
import com.ifarma.ifarma.fragments.AccountFragment;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AuthenticationService;

import java.util.List;

public class EditInfoPharmaFragment extends Fragment {

    private View rootView;
    private EditText _namePharmaInput;
    private EditText _cnpjPharmaInput;
    private EditText _addressPharmaInput;
    private EditText _houseNumberPharmaInput;
    private EditText _cepPharmaInput;
    private Button _saveButton;
    private Pharma pharma;

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_EMAIL = "currentEmail";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_edit_info_pharma, container, false);

        _namePharmaInput = (EditText) rootView.findViewById(R.id.input_name_pharma);
        _cnpjPharmaInput = (EditText) rootView.findViewById(R.id.input_cnpj_pharma);
        _addressPharmaInput = (EditText) rootView.findViewById(R.id.input_address_pharma);
        _houseNumberPharmaInput = (EditText) rootView.findViewById(R.id.input_house_number_pharma);
        _cepPharmaInput = (EditText) rootView.findViewById(R.id.input_cep_pharma);
        _saveButton = (Button) rootView.findViewById(R.id.btn_salvar);

        ImageView _backButton = (ImageView) rootView.findViewById(R.id.back_btn_info_pharma);

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
                fragmentTransaction.replace(R.id.fragment_container, new AccountFragment());
                fragmentTransaction.commit();
            }
        });
        initPharma();

        _saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                try {
                    pharma.setName(_namePharmaInput.getText().toString());
                    pharma.setCnpj(_cnpjPharmaInput.getText().toString());
                    pharma.setAddress(_addressPharmaInput.getText().toString());
                    pharma.setHouseNumber(_houseNumberPharmaInput.getText().toString());
                    pharma.setCep(_cepPharmaInput.getText().toString());
                    FirebaseController.editPharmacy(pharma);
                    Toast.makeText(getContext(), "Atualização feita com sucesso!", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getContext(), "A edição falhou!", Toast.LENGTH_SHORT).show();
                }

                LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                _pagerLayout.setVisibility(View.VISIBLE);
                _frameLayout.setVisibility(View.GONE);

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new AccountFragment());
                fragmentTransaction.commit();
            }
        });

        return rootView;

    }


    public void initPharma(){
        pharma = new Pharma();

        final int TIME = 4000; //Timeout
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        initDialog(dialog);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultState = "";
        String email =  prefs.getString(FLAG_EMAIL, defaultState);
        email = email.replace(".", "dot");

        FirebaseController.retrieveCurrentPharma(email, new OnCurrentPharmaGetDataListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(Pharma currentPharma) {
                pharma = new Pharma();
                pharma = currentPharma;
                _namePharmaInput.setText(pharma.getName());
                _cnpjPharmaInput.setText(pharma.getCnpj());
                _addressPharmaInput.setText(pharma.getAddress());
                _houseNumberPharmaInput.setText(pharma.getHouseNumber());
                _cepPharmaInput.setText(pharma.getCep());
                closeDialog(dialog);
            }
        });

        new Handler().postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, TIME);

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
