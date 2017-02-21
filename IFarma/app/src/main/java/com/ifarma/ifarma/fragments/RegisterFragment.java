package com.ifarma.ifarma.fragments;

import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.LoginFoldableAdapter;
import com.ifarma.ifarma.adapters.RegisterFoldableAdapter;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.services.AuthenticationState;

public class RegisterFragment extends Fragment {
    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";

    private View rootView;
    private RecyclerView loginRecycler;
    private RecyclerView registerRecycler;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private FrameLayout _frameLayout;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth fbAuth;

    private AuthenticationController authCtrl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        loginRecycler = (RecyclerView) rootView.findViewById(R.id.login_recycler_view);
        registerRecycler = (RecyclerView) rootView.findViewById(R.id.register_recycler_view);

        authCtrl = new AuthenticationController();

        ImageView _backButton = (ImageView) rootView.findViewById(R.id.back_btn);

        _frameLayout = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
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

        setUI();

        return rootView;

    }

    private void setUI() {
        LoginFoldableAdapter loginAdapter = new LoginFoldableAdapter(getActivity());
        RegisterFoldableAdapter registerAdapter = new RegisterFoldableAdapter(getActivity());

        RecyclerView.ItemDecoration itemDecoration = new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
            }
        };

        loginRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        loginRecycler.addItemDecoration(itemDecoration);
        loginRecycler.setAdapter(loginAdapter);

        registerRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        registerRecycler.addItemDecoration(itemDecoration);
        registerRecycler.setAdapter(registerAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fbAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                authCtrl.setCurrentUser(user);

                if (user != null) {
                    // User is signed in

                    Log.e("USER STATE", "onAuthStateChanged: " + authCtrl.getEstadoAtual().getEstadoMensagem());

                    if (authCtrl.getEstadoAtual() == AuthenticationState.LOGADO) {
                        //TODO retirar quando tiver transiçao
                        LinearLayout _pagerLayout = (LinearLayout) getActivity().findViewById(R.id.layout_pager);
                        _pagerLayout.setVisibility(View.VISIBLE);
                        _frameLayout.setVisibility(View.GONE);

                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                ((AppCompatActivity) getActivity()).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new AccountFragment());
                        fragmentTransaction.commit();
                    }
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
