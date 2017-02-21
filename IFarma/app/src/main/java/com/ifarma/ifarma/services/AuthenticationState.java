package com.ifarma.ifarma.services;

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

    public String getEstadoMensagem(){
        return estado;
    }

    public AuthenticationState getEstadoCorrespondente(String estado){
        return AuthenticationState.valueOf(estado);
    }
}
