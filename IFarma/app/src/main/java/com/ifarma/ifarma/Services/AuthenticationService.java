package com.ifarma.ifarma.Services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Transaction;
import com.ifarma.ifarma.exceptions.SignInException;

/**
 * Created by gustavooliveira on 2/18/17.
 */
public final class AuthenticationService {

    private static AuthenticationService authService;

    private static FirebaseUser usuarioLogado;

    private static FirebaseAuth fbAuth;

    private AuthenticationService(){
        fbAuth = FirebaseAuth.getInstance();
    }


    public void signIn(String email, String senha) throws SignInException {
        Task<AuthResult> result = fbAuth.signInWithEmailAndPassword(email, senha);

        if(result.isComplete() && !result.isSuccessful()) {
            throw new SignInException(result.getException().getMessage());
        }
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
