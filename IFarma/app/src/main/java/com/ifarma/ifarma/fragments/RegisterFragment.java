package com.ifarma.ifarma.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.ifarma.ifarma.Services.AuthenticationState;
import com.ifarma.ifarma.adapters.LoginFoldableAdapter;
import com.ifarma.ifarma.adapters.RegisterFoldableAdapter;
import com.ifarma.ifarma.controllers.AuthenticationController;

public class RegisterFragment extends Fragment {

    private View rootView;
    private RecyclerView loginRecycler;
    private RecyclerView registerRecycler;
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

                authCtrl.signOut();

                fragmentTransaction.replace(R.id.fragment_container, new AccountFragment());
                fragmentTransaction.commit();
            }
        });

        setUI();

        return rootView;

    }

    private void setUI(){
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
        registerRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        loginRecycler.addItemDecoration(itemDecoration);
        loginRecycler.setAdapter(loginAdapter);

        registerRecycler.addItemDecoration(itemDecoration);
        registerRecycler.setAdapter(registerAdapter);
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

//                authCtrl.signOut();

                authCtrl.setCurrentUser(user);

                if (user != null) {
                    // User is signed in
                    Log.e("SIGNED IN - FRAGMENT", "onAuthStateChanged:signed_in:" + user.getUid());
                    //TODO retirar quando tiver transiçao
                    Toast.makeText(getActivity(), "Login Efetuado com Sucesso",
                            Toast.LENGTH_LONG).show();

                    authCtrl.setAuthState(AuthenticationState.LOGADO);
                } else {
                    // User is signed out
                    Log.e("SIGNED OUT - FRAGMENT", "onAuthStateChanged:signed_out");
                    if (authCtrl.getEstadoAtual() == AuthenticationState.LOGADO){
                        Toast.makeText(getActivity(), "Você foi desconectado com sucesso!",
                                Toast.LENGTH_LONG).show();
                        authCtrl.setAuthState(AuthenticationState.DESLOGADO);
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
