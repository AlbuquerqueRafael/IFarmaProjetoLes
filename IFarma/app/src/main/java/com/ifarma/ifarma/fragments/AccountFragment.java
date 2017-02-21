package com.ifarma.ifarma.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.services.AuthenticationState;

public class AccountFragment extends Fragment {
    private String PREFS_NAME = "Preferences";
    private String FLAG_LOGGED = "isLogged";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    private final String MSG_LOGADO = "Você está conectado com ";

    private View rootView;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth fbAuth;

    private AuthenticationController authCtrl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_account, container, false);

        this.authCtrl = new AuthenticationController();

        FirebaseUser user = authCtrl.getCurrentUser();

        Log.e("TESTE VIEW", "VIEW CADASTRAR OU ENTRAR");

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        fbAuth = FirebaseAuth.getInstance();
        if (fbAuth.getCurrentUser() != null) {
            //TODO tirar LOGS
            Log.e("CURRENT USER",fbAuth.getCurrentUser().getEmail());
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
//                sharedPref = Prefer.edit();

                Button _actionButton = (Button) rootView.findViewById(R.id.sign_register_btn);
                TextView msgLogado = (TextView) rootView.findViewById(R.id.alert_msg);


                if (user != null) {
//                    editor.putString(FLAG_LOGGED, AuthenticationState.LOGADO.getEstadoMensagem());
//                    String defaultState = AuthenticationState.DESLOGADO.getEstadoMensagem();
//                    String estadoAtual = sharedPref.getString(FLAG_LOGGED, defaultState);


//                    authCtrl.setAuthState(AuthenticationState.valueOf(estadoAtual));

                    authCtrl.setCurrentUser(user);

                    if(authCtrl.getEstadoAtual() == AuthenticationState.TENTANDO_LOGIN) {
                        //TODO retirar quando tiver transiçao
                        Toast.makeText(getActivity(), "Login Efetuado com Sucesso",
                                Toast.LENGTH_LONG).show();

                    }
                    authCtrl.setAuthState(AuthenticationState.LOGADO);
                    String mensagem = MSG_LOGADO + user.getEmail();
                    msgLogado.setText(mensagem);

                    _actionButton.setText(R.string.sign_register_out);
                    _actionButton.setBackgroundResource(R.drawable.rounded_erro_button);

                    _actionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                            _pagerLayout.setVisibility(View.GONE);

                            Log.e("ESTADO DO AUTHSERVICE",authCtrl.getEstadoAtual().getEstadoMensagem());
                            Log.e("USUARIO",authCtrl.getCurrentUser().getEmail());

                            authCtrl.signOut();

                            android.support.v4.app.FragmentTransaction fragmentTransaction =
                                    getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new RegisterFragment());
                            fragmentTransaction.commit();
                        }
                    });
                } else {
                    // User is signed out
                    _actionButton.setBackgroundResource(R.drawable.rounded_button);
                    _actionButton.setText(R.string.sign_register_in);
                    msgLogado.setText(R.string.register_msg);

                    if (authCtrl.getEstadoAtual() == AuthenticationState.LOGADO) {
                        Toast.makeText(getActivity(), "Você foi desconectado com sucesso!",
                                Toast.LENGTH_LONG).show();
                        authCtrl.setAuthState(AuthenticationState.DESLOGADO);
                    }

                    _actionButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                            _pagerLayout.setVisibility(View.GONE);

                            android.support.v4.app.FragmentTransaction fragmentTransaction =
                                    getActivity().getSupportFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, new RegisterFragment());
                            fragmentTransaction.commit();
                        }
                    });

                }
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        fbAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            fbAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
