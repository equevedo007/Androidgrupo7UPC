package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class AntecedentType {

    @JsonProperty("idHistoria")
    private String idHistoria;

    @JsonProperty("enfermedadesExistentes")
    private List<String> enfermedadesExistentes;

    @JsonProperty("otrasExistentes")
    private List<String> otrasExistentes;

    @JsonProperty("enfermedadesFamiliares")
    private List<String> enfermedadesFamiliares;

    @JsonProperty("otrasFamiliares")
    private List<String> otrasFamiliares;

    @JsonProperty("enfermedadesOculares")
    private List<IllnessType> enfermedadesOculares;

    @JsonProperty("erroresRefraccion")
    private List<IllnessType> erroresRefraccion;

    public String getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(String idHistoria) {
        this.idHistoria = idHistoria;
    }

    public List<String> getEnfermedadesExistentes() {
        return enfermedadesExistentes;
    }

    public void setEnfermedadesExistentes(List<String> enfermedadesExistentes) {
        this.enfermedadesExistentes = enfermedadesExistentes;
    }

    public List<String> getOtrasExistentes() {
        return otrasExistentes;
    }

    public void setOtrasExistentes(List<String> otrasExistentes) {
        this.otrasExistentes = otrasExistentes;
    }

    public List<String> getEnfermedadesFamiliares() {
        return enfermedadesFamiliares;
    }

    public void setEnfermedadesFamiliares(List<String> enfermedadesFamiliares) {
        this.enfermedadesFamiliares = enfermedadesFamiliares;
    }

    public List<String> getOtrasFamiliares() {
        return otrasFamiliares;
    }

    public void setOtrasFamiliares(List<String> otrasFamiliares) {
        this.otrasFamiliares = otrasFamiliares;
    }

    public List<IllnessType> getEnfermedadesOculares() {
        return enfermedadesOculares;
    }

    public void setEnfermedadesOculares(List<IllnessType> enfermedadesOculares) {
        this.enfermedadesOculares = enfermedadesOculares;
    }

    public List<IllnessType> getErroresRefraccion() {
        return erroresRefraccion;
    }

    public void setErroresRefraccion(List<IllnessType> erroresRefraccion) {
        this.erroresRefraccion = erroresRefraccion;
    }
}
