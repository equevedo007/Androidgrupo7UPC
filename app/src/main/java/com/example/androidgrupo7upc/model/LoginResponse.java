package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class LoginResponse extends ResponseType {

    @JsonProperty("usuario")
    private UserType usuario;

    public UserType getUsuario() {
        return usuario;
    }

    public void setUsuario(UserType usuario) {
        this.usuario = usuario;
    }
}
