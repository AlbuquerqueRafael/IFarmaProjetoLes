package com.ifarma.ifarma.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.util.Validate;

public class AddProductFragment extends Fragment {

    private View rootView;
    private AppCompatButton _cadastrar;
    private EditText _nameProductInput;
    private EditText _priceProductInput;
    private EditText _labProductInput;
    private EditText _descriptionProductInput;
    private CheckBox _genericoCheckbox;
    private ImageView _backButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_addproduct, container, false);

        _cadastrar = (AppCompatButton) rootView.findViewById(R.id.btn_cadastrar);
        _nameProductInput = (EditText) rootView.findViewById(R.id.input_name_product);
        _priceProductInput = (EditText) rootView.findViewById(R.id.input_price_product);
        _labProductInput = (EditText) rootView.findViewById(R.id.input_lab_product);
        _descriptionProductInput = (EditText) rootView.findViewById(R.id.input_description_product);
        _genericoCheckbox = (CheckBox) rootView.findViewById(R.id.checkbox_generico);

        _backButton = (ImageView) rootView.findViewById(R.id.back_btn_cadastro_product);

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

        _cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Validate.isValidMedicine(_nameProductInput, _priceProductInput, _labProductInput, _descriptionProductInput)){
                    Toast.makeText(getContext(), "O cadastro falhou!", Toast.LENGTH_SHORT).show();
                } else {
                    Product product = new Product();
                    String name = _nameProductInput.getText().toString();
                    Double price = Double.parseDouble(_priceProductInput.getText().toString());
                    String lab = _labProductInput.getText().toString();
                    String description = _descriptionProductInput.getText().toString();
                    boolean generic = _genericoCheckbox.isChecked();
                    FirebaseController.saveProduct(name, price, lab, description, generic);
                }
            }
        });

        return rootView;
    }

}
