package com.ifarma.ifarma.fragments.pharmacy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.fragments.RegisterFragment;
import com.ifarma.ifarma.fragments.user.UserAccountFragment;
import com.ifarma.ifarma.services.AuthenticationState;

import java.util.ArrayList;
import java.util.List;

public class PharmacyFragment extends Fragment {

    private View rootView;
    private ListView _configList;
    private String FLAG_LOGGED = "isLogged";
    public static final String FLAG_EMAIL = "currentEmail";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_pharmacy, container, false);
        _configList = (ListView) rootView.findViewById(R.id.config_list);

        final List<String> configOptions = getConfigOptions();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.config_item, configOptions);
        _configList.setAdapter(adapter);

        _configList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        callFragment(new EditInfoPharmaFragment());
                        break;
                    case 1:
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Você foi desconectado com sucesso!",
                                Toast.LENGTH_LONG).show();
                        AuthenticationController authCtrl = new AuthenticationController();

                        authCtrl.setAuthState(AuthenticationState.DESLOGADO);
                        authCtrl.signOut();

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(FLAG_LOGGED, false);
                        editor.putString(FLAG_EMAIL, "");
                        editor.commit();

                        getActivity().finish();
                        Intent mIntent = new Intent(getContext(), MainActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putBoolean("isPharmacy", false);
                        mIntent.putExtras(mBundle);
                        startActivity(mIntent);
                        break;
                };
            }
        });

        return rootView;
    }

    private List<String> getConfigOptions(){
        List<String> list = new ArrayList<>();

        list.add("Editar informações");
        list.add("Editar conta");
        list.add("Deslogar");

        return list;
    }

    private void callFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
}
