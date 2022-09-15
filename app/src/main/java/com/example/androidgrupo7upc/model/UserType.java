package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class UserType {

    @JsonProperty("idEstado")
    private String idEstado;

    @JsonProperty("descEstado")
    private String descEstado;

    @JsonProperty("idUsuario")
    private String idUsuario;

    @JsonProperty("usuario")
    private String usuario;

    @JsonProperty("clave")
    private String clave;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apePaterno")
    private String apePaterno;

    @JsonProperty("apeMaterno")
    private String apeMaterno;

    @JsonProperty("tipoDocumento")
    private String tipoDocumento;

    @JsonProperty("numeroDocumento")
    private String numeroDocumento;

    @JsonProperty("email")
    private String email;

    @JsonProperty("direccion")
    private String direccion;

    @JsonProperty("token")
    private String token;

    @JsonProperty("flagSesion")
    private String flagSesion;

    @JsonProperty("idPerfil")
    private String idPerfil;

    @JsonProperty("descPerfil")
    private String descPerfil;

    public String getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(String idEstado) {
        this.idEstado = idEstado;
    }

    public String getDescEstado() {
        return descEstado;
    }

    public void setDescEstado(String descEstado) {
        this.descEstado = descEstado;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApePaterno() {
        return apePaterno;
    }

    public void setApePaterno(String apePaterno) {
        this.apePaterno = apePaterno;
    }

    public String getApeMaterno() {
        return apeMaterno;
    }

    public void setApeMaterno(String apeMaterno) {
        this.apeMaterno = apeMaterno;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFlagSesion() {
        return flagSesion;
    }

    public void setFlagSesion(String flagSesion) {
        this.flagSesion = flagSesion;
    }

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getDescPerfil() {
        return descPerfil;
    }

    public void setDescPerfil(String descPerfil) {
        this.descPerfil = descPerfil;
    }
}
