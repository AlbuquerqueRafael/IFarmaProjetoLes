package com.ifarma.ifarma.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ifarma.ifarma.R;

import static com.ifarma.ifarma.activities.MainActivity.FLAG_LOGGED;
import static com.ifarma.ifarma.activities.MainActivity.PREFS_NAME;
import static com.ifarma.ifarma.activities.MainActivity.USERNAME;

public class LoginActivity extends AppCompatActivity {

    private AppCompatButton _loginButton;
    private EditText _passwordInput;
    private EditText _usernameInput;
    private TextView _registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _registerButton = (TextView) findViewById(R.id.btn_register);
        _loginButton = (AppCompatButton) findViewById(R.id.btn_login);
        _passwordInput = (EditText) findViewById(R.id.input_password);
        _usernameInput = (EditText) findViewById(R.id.input_email);

        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Autenticando...");
        progressDialog.show();

        String username = _usernameInput.getText().toString();
        String password = _passwordInput.getText().toString();

        SharedPreferences sharedPref = this.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(FLAG_LOGGED, true);
        editor.putString(USERNAME, username);
        editor.commit();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login falhou! :(", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String username = _usernameInput.getText().toString();
        String password = _passwordInput.getText().toString();

        if (username.isEmpty()) {
            _usernameInput.setError("Nome de usuário inválido.");
            valid = false;
        } else {
            _usernameInput.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordInput.setError("Senha com tamanho entre 4 e 10 caracteres.");
            valid = false;
        } else {
            _passwordInput.setError(null);
        }

        return valid;
    }

}

