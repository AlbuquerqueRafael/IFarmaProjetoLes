package com.ifarma.ifarma.fragments.user;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.services.CartService;


/**
 * Created by Gabriel on 04/03/2017.
 */

public class FinishBuyFragment extends Fragment {

    public static final String FLAG_LOGGED = "isLogged";

    private View rootView;
    private FloatingActionButton _backButton;
    private TextView _deliveryPrice;
    private TextView _totalPrice;
    private EditText _changeInput;
    private Button _finishBuyButton;
    private Button _deliveryAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_finish_buy, container, false);

        _backButton = (FloatingActionButton) rootView.findViewById(R.id.back_btn);
        _deliveryPrice = (TextView) rootView.findViewById(R.id.delivery_price);
        _totalPrice = (TextView) rootView.findViewById(R.id.total_price);
        _changeInput = (EditText) rootView.findViewById(R.id.input_change);
        _finishBuyButton = (Button) rootView.findViewById(R.id.finish_buy);
        _deliveryAddress = (Button) rootView.findViewById(R.id.address_delivery);

        _totalPrice.setText(CartService.getTotalPrice());
        _deliveryPrice.setText("R$ 3,00");

        _deliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.address_custom_dialog);

                Button _GPSAddress = (Button) dialog.findViewById(R.id.gps_address);
                Button _newAddress = (Button) dialog.findViewById(R.id.other_address);
                Button _registerAddress = (Button) dialog.findViewById(R.id.register_address);

                if (isAuthenticated()){
                    _registerAddress.setVisibility(View.VISIBLE);
                }

                _GPSAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "GPS", Toast.LENGTH_SHORT).show();
                    }
                });

                _newAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Novo", Toast.LENGTH_SHORT).show();
                    }
                });

                _registerAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "Registro", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();
            }
        });

        _finishBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Compra finalizada! Aguarde a aprovação.", Toast.LENGTH_SHORT).show();
            }
        });

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
                fragmentTransaction.replace(R.id.fragment_container, new CartFragment());
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }

    private boolean isAuthenticated(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean defaultState = false;
        return prefs.getBoolean(FLAG_LOGGED, defaultState);
    }

}
