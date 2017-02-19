package com.ifarma.ifarma.controllers;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.ifarma.ifarma.Services.AuthenticationService;
import com.ifarma.ifarma.Services.AuthenticationState;

/**
 * Created by gustavooliveira on 2/18/17.
 */
public class AuthenticationController {
    private AuthenticationService authService;

    public AuthenticationController(){
        authService = AuthenticationService.getInstance();
    }

    public Task<AuthResult> signIn(String email, String senha) {
           return authService.signIn(email,senha);
    }

    public void signOut() {
        authService.signOut();
    }

    public void setCurrentUser(FirebaseUser user){
        authService.setUsuarioLogado(user);
    }

    public AuthenticationState getEstadoAtual(){
        return authService.getAuthState();
    }

    public void setAuthState(AuthenticationState state){
        authService.setAuthState(state);
    }
}
