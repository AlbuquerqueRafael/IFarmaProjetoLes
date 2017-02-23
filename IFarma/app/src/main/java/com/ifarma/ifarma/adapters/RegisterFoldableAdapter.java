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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ifarma.ifarma.R;
import com.ifarma.ifarma.activities.MainActivity;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.fragments.pharmacy.EditInfoPharmaFragment;
import com.ifarma.ifarma.fragments.user.SearchFragment;
import com.ifarma.ifarma.fragments.user.UserAccountFragment;
import com.ifarma.ifarma.libs.FoldableLayout;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RegisterFoldableAdapter extends RecyclerView.Adapter<RegisterFoldableAdapter.FolderHolder>{

    private Map<Integer, Boolean> mFoldStates = new HashMap<>();
    private static Context mContext;
    private static LayoutInflater inflater;
    private FirebaseAuth firebaseAuth;
    public static final String FLAG_LOGGED = "isLogged";
    public static final String FLAG_EMAIL = "currentEmail";

    public RegisterFoldableAdapter(Context context) {
        mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public FolderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FolderHolder(new FoldableLayout(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(final FolderHolder holder, int position) {

        holder._registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateLogin(holder._registerEmailInput, holder._registerPasswordInput)) {
                    Toast.makeText(mContext, "Registro falhou! :(", Toast.LENGTH_LONG).show();
                    holder._registerButton.setEnabled(true);
                    return;
                }

                holder._registerButton.setEnabled(false);

                final ProgressDialog progressDialog = new ProgressDialog(mContext);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Registrando...");
                progressDialog.show();

                firebaseAuth = FirebaseAuth.getInstance();

                firebaseAuth.createUserWithEmailAndPassword(holder._registerEmailInput.getText().toString().trim(), holder._registerPasswordInput.getText().toString().trim())
                        .addOnCompleteListener((MainActivity) mContext, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    if (holder._checkBoxPharmacy.isChecked()){
                                        try {
                                            FirebaseController.savePharmacy("", holder._registerEmailInput.getText().toString(),
                                                                            holder._registerPasswordInput.getText().toString(), "", "", "", "");
                                        } catch (InvalidUserDataException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        try {
                                            FirebaseController.saveCustomer("", holder._registerEmailInput.getText().toString(),
                                                                            holder._registerPasswordInput.getText().toString(), "", "", "", "");
                                        } catch (InvalidUserDataException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    new android.os.Handler().postDelayed(
                                            new Runnable() {
                                                public void run() {
                                                    ((MainActivity) mContext).finish();
                                                    Intent mIntent = new Intent(mContext, MainActivity.class);
                                                    Bundle mBundle = new Bundle();
                                                    mBundle.putBoolean("isPharmacy", holder._checkBoxPharmacy.isChecked());
                                                    mIntent.putExtras(mBundle);
                                                    mContext.startActivity(mIntent);
                                                }
                                            }, 3000);


                                } else {
                                    progressDialog.dismiss();
                                    holder._registerButton.setEnabled(true);
                                    Toast.makeText(mContext, "Registro falhou! :(", Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }
        });

        holder._coverRegisterButton.setOnClickListener(new View.OnClickListener() {
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

    private void showDialog(final FolderHolder holder){
        new AlertDialog.Builder(mContext)
                .setTitle("Alerta")
                .setMessage("O seu perfil está sendo criado com informações em branco. Deseja terminar o seu cadastro?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Fragment fragment;

                        if (holder._checkBoxPharmacy.isSelected()){
                            fragment = new EditInfoPharmaFragment();
                        } else {
                            fragment = new UserAccountFragment();
                        }

                        android.support.v4.app.FragmentTransaction fragmentTransaction =
                                ((MainActivity)mContext).getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.commit();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity) mContext).finish();
                        Intent mIntent = new Intent(mContext, MainActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putBoolean("isPharmacy", holder._checkBoxPharmacy.isSelected());
                        mIntent.putExtras(mBundle);
                        mContext.startActivity(mIntent);

                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putBoolean(FLAG_LOGGED, true);
                        editor.putString(FLAG_EMAIL, holder._registerEmailInput.getText().toString().trim());
                        editor.commit();

                        dialog.dismiss();

                    }
                })
                .show();

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class FolderHolder extends RecyclerView.ViewHolder {

        protected FoldableLayout mFoldableLayout;

        @Bind(R.id.register_email_input)
        protected EditText _registerEmailInput;

        @Bind(R.id.register_password_input)
        protected EditText _registerPasswordInput;

        @Bind(R.id.register_btn)
        protected Button _registerButton;

        @Bind(R.id.is_pharmacy)
        protected CheckBox _checkBoxPharmacy;

        @Bind(R.id.cover_register_btn)
        protected Button _coverRegisterButton;

        public FolderHolder(FoldableLayout foldableLayout) {
            super(foldableLayout);
            mFoldableLayout = foldableLayout;
            foldableLayout.setupViews(R.layout.register_cover, R.layout.register_detail, R.dimen.card_cover_height, itemView.getContext());
            ButterKnife.bind(this, foldableLayout);
        }
    }
}