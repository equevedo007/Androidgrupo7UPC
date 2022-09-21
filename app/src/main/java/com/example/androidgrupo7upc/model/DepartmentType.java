package com.example.androidgrupo7upc.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class DepartmentType {

    @JsonProperty("idDepartamento")
    private String idDepartamento;

    @JsonProperty("descDepartamento")
    private String descDepartamento;

    public DepartmentType() {
    }

    public DepartmentType(String idDepartamento, String descDepartamento) {
        this.idDepartamento = idDepartamento;
        this.descDepartamento = descDepartamento;
    }

    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getDescDepartamento() {
        return descDepartamento;
    }

    public void setDescDepartamento(String descDepartamento) {
        this.descDepartamento = descDepartamento;
    }

    @NonNull
    @Override
    public String toString() {
        return descDepartamento;
    }
}
