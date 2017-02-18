package com.ifarma.ifarma.controllers;

import android.util.Log;

import com.ifarma.ifarma.Services.AuthenticationService;
import com.ifarma.ifarma.exceptions.SignInException;

/**
 * Created by gustavooliveira on 2/18/17.
 */
public class AuthenticationController {
    private AuthenticationService authService;

    public AuthenticationController(){
        authService = AuthenticationService.getInstance();
    }

    public void signIn(String email, String senha){
        try {
            authService.signIn(email,senha);
        } catch (SignInException e) {
            Log.e("ERRO-SIGNIN",e.getMessage());
        }
    }
}
