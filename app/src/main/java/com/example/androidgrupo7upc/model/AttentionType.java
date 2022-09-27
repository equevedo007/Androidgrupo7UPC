package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class AttentionType {

    @JsonProperty("idHistoria")
    private String idHistoria;

    @JsonProperty("idAtencion")
    private String idAtencion;

    @JsonProperty("codHistoria")
    private String codHistoria;

    @JsonProperty("idPaciente")
    private String idPaciente;

    @JsonProperty("nombres")
    private String nombres;

    @JsonProperty("apePaterno")
    private String apePaterno;

    @JsonProperty("apeMaterno")
    private String apeMaterno;

    @JsonProperty("numeroDocumento")
    private String numeroDocumento;

    @JsonProperty("edad")
    private String edad;

    @JsonProperty("direccion")
    private String direccion;

    public String getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(String idHistoria) {
        this.idHistoria = idHistoria;
    }

    public String getCodHistoria() {
        return codHistoria;
    }

    public void setCodHistoria(String codHistoria) {
        this.codHistoria = codHistoria;
    }

    public String getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(String idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
