package com.example.androidgrupo7upc.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class DistrictType {

    @JsonProperty("idUbigeo")
    private String idUbigeo;

    @JsonProperty("idDistrito")
    private String idDistrito;

    @JsonProperty("descDistrito")
    private String descDistrito;

    public DistrictType() {
    }

    public DistrictType(String idUbigeo, String idDistrito, String descDistrito) {
        this.idUbigeo = idUbigeo;
        this.idDistrito = idDistrito;
        this.descDistrito = descDistrito;
    }

    public String getIdUbigeo() {
        return idUbigeo;
    }

    public void setIdUbigeo(String idUbigeo) {
        this.idUbigeo = idUbigeo;
    }

    public String getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(String idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getDescDistrito() {
        return descDistrito;
    }

    public void setDescDistrito(String descDistrito) {
        this.descDistrito = descDistrito;
    }

    @NonNull
    @Override
    public String toString() {
        return descDistrito;
    }
}
