package com.ifarma.ifarma.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ifarma.ifarma.R;

public class RegisterActivity extends AppCompatActivity {

    private AppCompatButton _confirmButton;
    private TextView _backButton;
    private EditText _nameInput;
    private EditText _usernameInput;
    private EditText _passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        _backButton = (TextView) findViewById(R.id.btn_back);
        _confirmButton = (AppCompatButton) findViewById(R.id.btn_confirm);
        _nameInput = (EditText) findViewById(R.id.input_name);
        _usernameInput = (EditText) findViewById(R.id.input_username);
        _passwordInput = (EditText) findViewById(R.id.input_password);

        _backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        _confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(_nameInput.getText().toString(), _usernameInput.getText().toString(), _passwordInput.getText().toString());
                finish();
            }
        });

    }

    private void registerUser(String name, String username, String password){
        if (!validate()){
            onRegisterFailed();
            return;
        }

        _confirmButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Registrando...");
        progressDialog.show();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    private void onRegisterFailed() {
        Toast.makeText(getBaseContext(), "Registro falhou! :(", Toast.LENGTH_LONG).show();

        _confirmButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String name = _nameInput.getText().toString();
        String username = _usernameInput.getText().toString();
        String password = _passwordInput.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameInput.setError("Nome deve conter mais de 3 caracteres.");
            valid = false;
        } else {
            _nameInput.setError(null);
        }

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

