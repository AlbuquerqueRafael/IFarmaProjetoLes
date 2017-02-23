package com.ifarma.ifarma.fragments.user;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnCurrentCustomerGetDataListener;
import com.ifarma.ifarma.model.Customer;

public class EditInfoUserFragment extends Fragment {

    private View rootView;
    private EditText _nameField;
    private EditText _cpfField;
    private EditText _addressField;
    private EditText _cepField;
    private EditText _houseNumberField;
    private Customer customer;
    public static final String FLAG_EMAIL = "currentEmail";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_user_register, container, false);

        ImageView _backButton = (ImageView) rootView.findViewById(R.id.back_btn);

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
                fragmentTransaction.replace(R.id.fragment_container, new UserFragment());
                fragmentTransaction.commit();
            }
        });

        AppCompatButton _saveButton = (AppCompatButton) rootView.findViewById(R.id.btn_save);
        _nameField = (EditText) rootView.findViewById(R.id.name_input);
        _cpfField = (EditText) rootView.findViewById(R.id.cpf_input);
        _addressField = (EditText) rootView.findViewById(R.id.address_input);
        _cepField = (EditText) rootView.findViewById(R.id.cep_input);
        _houseNumberField = (EditText) rootView.findViewById(R.id.house_number);

        loadUserInfo();

        _saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    customer.setName(_nameField.getText().toString());
                    customer.setCpf(_cpfField.getText().toString());
                    customer.setAddress(_addressField.getText().toString());
                    customer.setHouseNumber(_houseNumberField.getText().toString());
                    customer.setCep(_cepField.getText().toString());
                    FirebaseController.editCustomer(customer);
                    Toast.makeText(getContext(), "Atualização feita com sucesso!", Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    Toast.makeText(getContext(), "A edição falhou!", Toast.LENGTH_SHORT).show();
                }

                LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                _pagerLayout.setVisibility(View.VISIBLE);
                _frameLayout.setVisibility(View.GONE);

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, new UserFragment());
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    private void loadUserInfo(){
        customer = new Customer();

        final int TIME = 4000; //Timeout
        final ProgressDialog dialog = new ProgressDialog(getActivity());
        initDialog(dialog);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultState = "";
        String email = prefs.getString(FLAG_EMAIL, defaultState);
        email = email.replace(".", "dot");

        Toast.makeText(getActivity(), email, Toast.LENGTH_SHORT).show();

        FirebaseController.retrieveCurrentCustomer(email, new OnCurrentCustomerGetDataListener() {
            @Override
            public void onStart() {}

            @Override
            public void onSuccess(Customer currentCustomer) {
                customer = new Customer();
                customer = currentCustomer;
                _nameField.setText(customer.getName());
                _cpfField.setText(customer.getCpf());
                _addressField.setText(customer.getAddress());
                _houseNumberField.setText(customer.getHouseNumber());
                _cepField.setText(customer.getCep());

                dialog.dismiss();
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
}
