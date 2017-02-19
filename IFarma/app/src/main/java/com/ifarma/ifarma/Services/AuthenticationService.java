package com.ifarma.ifarma.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by gustavooliveira on 2/18/17.
 */
public final class AuthenticationService {

    private static AuthenticationService authService;
    private static FirebaseUser usuarioLogado;
    private static FirebaseAuth fbAuth;

    private AuthenticationState authState;



    private AuthenticationService(){
        fbAuth = FirebaseAuth.getInstance();
        authState = AuthenticationState.DESLOGADO;
    }


    public Task<AuthResult> signIn(String email, String senha) {
        this.setAuthState(AuthenticationState.TENTANDO_LOGIN);
        Task<AuthResult> result = fbAuth.signInWithEmailAndPassword(email, senha);

        result.addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.e("sign IN - SERVICE", "signInWithEmail:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Log.e("sign IN - SERVICE", "signInWithEmail:failed");
                    setAuthState(AuthenticationState.DESLOGADO);
                }
            }
        });

        return result;

    }

    public void signOut(){
        fbAuth.signOut();
    }

    public static void setUsuarioLogado(FirebaseUser usuarioLogado) {
        AuthenticationService.usuarioLogado = usuarioLogado;
    }

    public AuthenticationState getAuthState() {
        return authState;
    }

    public void setAuthState(AuthenticationState authState) {
        this.authState = authState;
    }

    public FirebaseUser getUsuarioLogado(){
        return usuarioLogado;
    }

    /**
     * Verifica se o usuario está logado
     * @param email String contendo email do usuário
     * @param senha String contendo senha do usuário
     * @return true caso usuário esteja logado.
     */
    public boolean isUserSignedIn(String email,String senha){
        usuarioLogado = fbAuth.getCurrentUser();
        boolean logged = false;
        if(usuarioLogado != null) {
            logged = true;
        }
        return logged;
    }

    public static AuthenticationService getInstance(){
        if(authService == null){
            authService = new AuthenticationService();
        }
        return authService;
    }
}
