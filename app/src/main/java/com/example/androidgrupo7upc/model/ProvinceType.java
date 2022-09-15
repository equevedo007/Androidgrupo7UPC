package com.example.androidgrupo7upc.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class ProvinceType {

    @JsonProperty("idProvincia")
    private String idProvincia;

    @JsonProperty("descProvincia")
    private String descProvincia;

    public ProvinceType() {
    }

    public ProvinceType(String idProvincia, String descProvincia) {
        this.idProvincia = idProvincia;
        this.descProvincia = descProvincia;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getDescProvincia() {
        return descProvincia;
    }

    public void setDescProvincia(String descProvincia) {
        this.descProvincia = descProvincia;
    }

    @NonNull
    @Override
    public String toString() {
        return descProvincia;
    }
}
