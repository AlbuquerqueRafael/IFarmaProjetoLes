package com.ifarma.ifarma.adapters;


/*
 * Copyright 2015 Worldline.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.controllers.AuthenticationController;
import com.ifarma.ifarma.libs.FoldableLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginFoldableAdapter extends RecyclerView.Adapter<LoginFoldableAdapter.FolderHolder>{

    private Map<Integer, Boolean> mFoldStates = new HashMap<>();
    private static Context mContext;
    private static LayoutInflater inflater;

    public static final String PREFS_NAME = "Preferences";
    public static final String FLAG_LOGGED = "isLogged";
    public static final String FLAG_EMAIL = "currentEmail";

    private AuthenticationController authCtrl;

    public LoginFoldableAdapter(Context context) {
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        authCtrl = new AuthenticationController();

        return new FolderHolder(new FoldableLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final FolderHolder holder, int position) {

        holder._loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateLogin(holder._loginEmailInput, holder._loginPasswordInput)) {
                    Toast.makeText(mContext, "Login falhou! :(", Toast.LENGTH_LONG).show();
                    holder._loginButton.setEnabled(true);
                    return;
                }

                holder._loginButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(mContext);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Autenticando...");
                progressDialog.show();

                //TODO tirar os logs.
                Log.e("InputEmail - ADAPTER", holder._loginEmailInput.getText().toString());
                Log.e("InputSenha - ADAPTER", holder._loginPasswordInput.getText().toString());

                final String email = holder._loginEmailInput.getText().toString();
                String senha = holder._loginPasswordInput.getText().toString();
                Task<AuthResult> result = authCtrl.signIn(email, senha);

                new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            progressDialog.dismiss();

                            holder._loginButton.setEnabled(true);
                        }
                    }, 2000);

                result.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("sign IN - SERVICE", "signInWithEmail:onFailure:" + e.getMessage());
                        Toast.makeText(mContext, "Email ou senha inválidos",
                                Toast.LENGTH_LONG).show();
                    }
                });

                result.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(FLAG_LOGGED, true);
                        editor.putString(FLAG_EMAIL, email);
                        editor.commit();

                        Intent mIntent = new Intent(mContext, MainActivity.class);
                        mContext.startActivity(mIntent);
                    }
                });
            }
        });

        holder._coverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithAnimation();
                } else {
                    holder.mFoldableLayout.foldWithAnimation();
                }
            }
        });

        if (mFoldStates.containsKey(position)) {
            if (mFoldStates.get(position) == Boolean.TRUE) {
                if (!holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.foldWithoutAnimation();
                }
            } else if (mFoldStates.get(position) == Boolean.FALSE) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithoutAnimation();
                }
            }
        } else {
            holder.mFoldableLayout.foldWithoutAnimation();
        }

        holder.mFoldableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithAnimation();
                } else {
                    holder.mFoldableLayout.foldWithAnimation();
                }
            }
        });
        holder.mFoldableLayout.setFoldListener(new FoldableLayout.FoldListener() {
            @Override
            public void onUnFoldStart() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(5);
                }
            }

            @Override
            public void onUnFoldEnd() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
                mFoldStates.put(holder.getAdapterPosition(), false);
            }

            @Override
            public void onFoldStart() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(5);
                }
            }

            @Override
            public void onFoldEnd() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
                mFoldStates.put(holder.getAdapterPosition(), true);
            }
        });
    }

    private static boolean validateLogin(EditText emailInput, EditText passwordInput){
        boolean valid = true;

        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("E-mail inválido.");
            valid = false;
        } else {
            emailInput.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordInput.setError("Senha deve ter entre 4 e 10 caracteres.");
            valid = false;
        } else {
            passwordInput.setError(null);
        }

        return valid;

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class FolderHolder extends RecyclerView.ViewHolder {

        protected FoldableLayout mFoldableLayout;

        // login
        @Bind(R.id.login_email_input)
        protected EditText _loginEmailInput;

        @Bind(R.id.login_password_input)
        protected EditText _loginPasswordInput;

        @Bind(R.id.login_btn)
        protected Button _loginButton;

        @Bind(R.id.cover_login_btn)
        protected Button _coverLoginButton;

        public FolderHolder(FoldableLayout foldableLayout) {
            super(foldableLayout);
            mFoldableLayout = foldableLayout;
            foldableLayout.setupViews(R.layout.login_cover, R.layout.login_detail, R.dimen.card_cover_height, itemView.getContext());
            ButterKnife.bind(this, foldableLayout);

        }
    }
}
