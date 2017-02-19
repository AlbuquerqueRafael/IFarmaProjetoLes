package com.ifarma.ifarma.Services;

/**
 * Created by gustavooliveira on 2/19/17.
 */
public enum AuthenticationState {
    DESLOGADO("DESLOGADO"),
    TENTANDO_LOGIN("TENTANDO LOGIN"),
    LOGADO("LOGADO");


    private final  String estado;

    AuthenticationState(String mensagem){
        estado = mensagem;
    }
}
