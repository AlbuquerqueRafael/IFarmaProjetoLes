package com.ifarma.ifarma.fragments.user;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnCurrentCustomerGetDataListener;
import com.ifarma.ifarma.model.Customer;
import com.ifarma.ifarma.model.OrderStatus;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AdapterService;
import com.ifarma.ifarma.services.CartService;
import com.ifarma.ifarma.util.BrPhoneNumberFormatter;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;


/**
 * Created by Gabriel on 04/03/2017.
 */

public class FinishBuyFragment extends Fragment {

    public static final String FLAG_LOGGED = "isLogged";
    public static final String FLAG_EMAIL = "currentEmail";
    private final String CEP_CAMPINA = "58000000";

    private final int TAMANHO_CEP_PADRAO = 8;

    private View rootView;
    private FloatingActionButton _backButton;
    private TextView _deliveryPrice;
    private TextView _totalPrice;
    private TextView _userAddress1;
    private TextView _userAddressNumber;
    private TextView _userAddressPostalCode;
    private EditText _userCelphone;
    private EditText _changeInput;
    private TextView _medicinesPrice;
    private Button _finishBuyButton;
    private Button _deliveryAddress;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private RadioButton radioDinheiro;
    private RadioButton radioCartao;
    private Dialog dialog;
    private String _customerName;
    private LinearLayout moneyLayout;
    private TextView creditCardMessage;
    private String numero, rua, cep, telefone;


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
        _userAddress1 = (TextView) rootView.findViewById(R.id.tv_address);
        _userAddressNumber = (TextView) rootView.findViewById(R.id.tv_number);
        _userAddressPostalCode = (TextView) rootView.findViewById(R.id.tv_cep);
        _medicinesPrice = (TextView) rootView.findViewById(R.id.medicines_price);
        _userCelphone = (EditText) rootView.findViewById(R.id.cel_input);

        // MASKING NUMBER FORMAT
        BrPhoneNumberFormatter addLineNumberFormatter = new BrPhoneNumberFormatter(new WeakReference<EditText>(_userCelphone));
        _userCelphone.addTextChangedListener(addLineNumberFormatter);

        radioDinheiro = (RadioButton) rootView.findViewById(R.id.radio_dinheiro);
        radioCartao = (RadioButton) rootView.findViewById(R.id.radio_cartao);

        creditCardMessage = (TextView) rootView.findViewById(R.id.cartao_mensagem);
        moneyLayout = (LinearLayout) rootView.findViewById(R.id.dinheiro_layout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioCartao.isChecked()){
                    creditCardMessage.setVisibility(View.VISIBLE);
                    moneyLayout.setVisibility(View.GONE);
                } else {
                    moneyLayout.setVisibility(View.VISIBLE);
                    creditCardMessage.setVisibility(View.GONE);
                }
            }
        };

        radioCartao.setOnClickListener(listener);
        radioDinheiro.setOnClickListener(listener);

        _deliveryPrice.setText("R$ 3,00");
        _medicinesPrice.setText(CartService.getTotalPrice(0.00));
        _totalPrice.setText(CartService.getTotalPrice(3.00));

        numero = "";
        rua = "";
        cep = "";


        if (isAuthenticated())
            getCustomerName();
        else
            _customerName = "Usuário anônimo";

        _deliveryAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.address_custom_dialog);
                dialog.setCanceledOnTouchOutside(false);

                Button _GPSAddress = (Button) dialog.findViewById(R.id.gps_address);
                Button _newAddress = (Button) dialog.findViewById(R.id.other_address);
                Button _registerAddress = (Button) dialog.findViewById(R.id.register_address);

                if (isAuthenticated()){
                    _registerAddress.setVisibility(View.VISIBLE);
                }

                _GPSAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        checkPermission();

                        Toast.makeText(getContext(), "Aguarde enquanto pegamos sua localização!", Toast.LENGTH_SHORT).show();

                        if (checkGPSEnable())
                            getCurrentLocation();
                    }
                });

                _newAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showInputDialog();
                    }
                });

                _registerAddress.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setUserAddress();
                    }
                });

                dialog.show();
            }
        });

        _finishBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Product, Integer> cartList = CartService.getCartList();
                final HashMap<String, String> pharmacysOrder = new HashMap<String, String>();

                for (Map.Entry<Product, Integer> entry : cartList.entrySet()) {
                    String previousData = "";
                    if (pharmacysOrder.get(entry.getKey().getPharmacyId()) != null){
                        previousData = pharmacysOrder.get(entry.getKey().getPharmacyId());
                    }

                    pharmacysOrder.put(entry.getKey().getPharmacyId(), previousData + entry.getValue() + " - " + entry.getKey().getNameProduct() +
                            " - " + "Valor: R$ " + (entry.getKey().getPrice() * entry.getValue()) + "\n");
                }

                if (isEnderecoInvalido() ){
                    new AlertDialog.Builder(getContext())
                            .setTitle("Alerta")
                            .setMessage("Preencher todos os campos de endereço, você deve!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
                else if (isPhoneValido(_userCelphone)) {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Alerta")
                            .setMessage("Informar o telefone, você deve!")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
                else {
                    telefone = _userCelphone.getText().toString();
                    if (pharmacysOrder.size() > 1) {
                        new AlertDialog.Builder(getContext())
                                .setTitle("Alerta")
                                .setMessage("Você está fazendo pedidos para mais de uma farmácia.\n\nLeve isto em consideração ao preencher o troco.")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                        saveOrder(pharmacysOrder);

                                        dialog.dismiss();
                                    }
                                }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    } else {
                        saveOrder(pharmacysOrder);
                    }

                }

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

    private boolean isEnderecoInvalido() {
        boolean userStreet = _userAddress1.getText().toString().isEmpty();
        boolean userNumber = _userAddressNumber.getText().toString().isEmpty();
        boolean userPostal = _userAddressPostalCode.getText().toString().isEmpty();
        return userStreet || userNumber || userPostal;
    }

    private double getTotalPrice(HashMap<String, String> pharmacysOrder, String pharmacyId) {
        double soma = 3;

        String totalPriceText = pharmacysOrder.get(pharmacyId);
        
        for (String compra: totalPriceText.split("\n")) {
            soma += Double.parseDouble(compra.split("-")[2].split(" ")[3]);
        }
        
        return soma;
    }

    private void saveOrder(HashMap<String, String> pharmacysOrder){
        String telephone = this.telefone;
        String comment = "";

        String formaDePagamento = "Cartão de Crédito/Débito";
        if (radioDinheiro.isChecked()) {
            formaDePagamento = "Troco para: " + _changeInput.getText().toString();
        }

        for (Map.Entry<String, String> entry : pharmacysOrder.entrySet()) {
            double totalPrice = getTotalPrice(pharmacysOrder, entry.getKey());

            FirebaseController.saveOrder(entry.getKey(),
                    telephone,
                    comment, totalPrice, _customerName,
                    entry.getValue() + formaDePagamento,
                    montaEndereço(),
                    OrderStatus.WAITING_ORDER,
                    FirebaseInstanceId.getInstance().getToken());
        }

        Toast.makeText(getContext(), "Compra finalizada! Aguarde a aprovação.", Toast.LENGTH_SHORT).show();
        CartService.closeCart();

        final FrameLayout _frameLayout = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
        _frameLayout.setVisibility(View.VISIBLE);

        LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
        _pagerLayout.setVisibility(View.VISIBLE);
        _frameLayout.setVisibility(View.GONE);

        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new SearchFragment());
        fragmentTransaction.commit();

        AdapterService.reloadAdapter(0);

    }

    private String montaEndereço() {
        String endereco = _userAddress1.getText().toString() +
                ", " + _userAddressNumber.getText().toString() +
                ". " + _userAddressPostalCode.getText().toString();
        return endereco;
    }

    private boolean checkGPSEnable(){
        boolean isGPSenabled = false;
        try {
            isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }

        return isGPSenabled;
    }

    private void checkPermission() {

        locationManager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSenabled = checkGPSEnable();

        if (!isGPSenabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());
            dialog.setMessage("Por favor, ative a Localização");
            dialog.setPositiveButton("Ativar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    getActivity().startActivity(myIntent);
                }
            });
            dialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Toast.makeText(getActivity(), "Para usar o serviço, é necessário ativar a Localização", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.show();
        }

        if (isGPSenabled) {
            getCurrentLocation();
        }
    }

    private void changeLocation() {
        if (ActivityCompat.checkSelfPermission(this.getActivity(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getActivity(), ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION, INTERNET}
                        ,10);
            }
            return;
        }

        locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null);

    }

    private void showInputDialog(){
        final Dialog inputDialog = new Dialog(getContext());
        inputDialog.setTitle("Inserir Endereço");
        inputDialog.setContentView(R.layout.address_input_dialog);

        final EditText addressInput = (EditText) inputDialog.findViewById(R.id.address_input);
        addressInput.setText(this.rua);
        final EditText cepInput = (EditText) inputDialog.findViewById(R.id.cep_input);
        cepInput.setText(this.cep);
        final EditText numberInput = (EditText) inputDialog.findViewById(R.id.number_input);
        numberInput.setText(this.numero);
        final Button okButton = (Button) inputDialog.findViewById(R.id.ok_button);
        final Button cancelButton = (Button) inputDialog.findViewById(R.id.cancel_button);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rua = addressInput.getText().toString();
                String numero = numberInput.getText().toString();
                String cep = cepInput.getText().toString();

                boolean enderecoValido = validaAddress(addressInput, numberInput, cepInput);

                if(enderecoValido){
                    setAddress(rua, numero, cep);

                    String address = "Rua/Av: " + rua  +
                                    "\nNúmero: " + numero +
                                    "\nCEP: " + cep;

                    _userAddress1.setText(rua);
                    _userAddressNumber.setText(numero);
                    _userAddressPostalCode.setText(cep);
                    inputDialog.dismiss();
                    dialog.dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputDialog.dismiss();
                dialog.dismiss();
            }
        });

        inputDialog.show();
    }

    private boolean validaAddress(EditText addressInput, EditText numberInput, EditText cepInput) {
        boolean valido = true;

        boolean isRuaVazio = addressInput.getText().toString().trim().isEmpty();
        boolean isNumeroVazio = numberInput.getText().toString().trim().isEmpty();
        boolean isCEPVazio = cepInput.getText().toString().trim().isEmpty();
        int CEPtamanho = cepInput.getText().toString().trim().length();
        boolean hasCEPTamanhoSeis = CEPtamanho == TAMANHO_CEP_PADRAO;

        Log.e("ENDERECO", ">>>>>>>>> cep tamanho : " + CEPtamanho);

        if(isRuaVazio){
            addressInput.setError("O Logradouro é obrigatório");
            valido = false;
        }
        else if(isNumeroVazio){
            numberInput.setError("O número é obrigatório");
            valido = false;
        }
        else if(isCEPVazio ){
            cepInput.setError("O CEP é obrigatório");
            valido = false;
        } else if(!hasCEPTamanhoSeis) {
            cepInput.setError("O CEP é inválido");
            valido = false;
        }

        return valido;
    }

    /**
     * Verifica se o campo de telefone é valido, ou seja se é vazio ou não.
     *
     * @param phone
     *          Campo de edição do telefone
     * @return True caso seja inválido.
     */
    private boolean isPhoneValido(EditText phone) {
        boolean isRuaVazio = phone.getText().toString().trim().isEmpty();

        if(isRuaVazio){
            phone.setError("O Telefone é obrigatório");
        }

        return isRuaVazio;
    }

    private void setAddress(String rua, String numero,String cep) {

        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
    }

    private void getCurrentLocation() {

        locationManager = (LocationManager) this.getActivity().getSystemService(this.getActivity().LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                try {
                    Geocoder geo = new Geocoder(getContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresses.isEmpty()) {
                        Toast.makeText(getContext(), "Aguardando a localização...", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (addresses.size() > 0) {
                            String rua = addresses.get(0).getAddressLine(0).split(",")[0];
                            Log.e("GPS-LOCATION", ">>>>>> rua: " + rua);
                            String cep = addresses.get(0).getPostalCode();

                            if(cep != null) {
                                cep = cep.replace("-", "");
                                Log.e("GPS-LOCATION", ">>>>>> cep: " + cep);
                            } else {
                                cep = CEP_CAMPINA;
                            }
                            setAddress(rua, "", cep);

                            String address = "Rua/Av: " + rua +
                                            "\nNúmero: " +
                                             "\nCEP: " + cep;

                            _userAddress1.setText(rua);
                            _userAddressPostalCode.setText(cep);
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace(); // getFromLocation() may sometimes fail
                }

                dialog.dismiss();

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                getActivity().startActivity(myIntent);
            }
        };

        changeLocation();

    }

    private void getCustomerName(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultState = "";
        String email = prefs.getString(FLAG_EMAIL, defaultState);
        email = email.replace(".", "dot");

        FirebaseController.retrieveCurrentCustomer(email, new OnCurrentCustomerGetDataListener() {
            @Override
            public void onStart() {}

            @Override
            public void onSuccess(Customer currentCustomer) {
                _customerName = currentCustomer.getName();
            }
        });
    }

    private void setUserAddress(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String defaultState = "";
        String email = prefs.getString(FLAG_EMAIL, defaultState);
        email = email.replace(".", "dot");

        FirebaseController.retrieveCurrentCustomer(email, new OnCurrentCustomerGetDataListener() {
            @Override
            public void onStart() {}

            @Override
            public void onSuccess(Customer currentCustomer) {
                _userAddress1.setText(currentCustomer.getAddress());
                _userAddressNumber.setText(currentCustomer.getHouseNumber());
                _userAddressPostalCode.setText(currentCustomer.getCep());
                dialog.dismiss();
            }
        });
    }

    private boolean isAuthenticated(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean defaultState = false;
        return prefs.getBoolean(FLAG_LOGGED, defaultState);
    }

}
