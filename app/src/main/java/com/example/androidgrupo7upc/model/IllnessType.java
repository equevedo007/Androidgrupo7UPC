package com.example.androidgrupo7upc.model;

import androidx.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class IllnessType extends ResponseType {

    @JsonProperty("idEnfermedad")
    private String illnessId;

    @JsonProperty("descripcion")
    private String illnessDesc;

    @JsonProperty("checkDerecho")
    private boolean isOD;

    @JsonProperty("checkIzquierdo")
    private boolean isOI;

    public IllnessType(String illnessId, String illnessDesc) {
        this.illnessId = illnessId;
        this.illnessDesc = illnessDesc;
    }

    public String getIllnessId() {
        return illnessId;
    }

    public void setIllnessId(String illnessId) {
        this.illnessId = illnessId;
    }

    public String getIllnessDesc() {
        return illnessDesc;
    }

    public void setIllnessDesc(String illnessDesc) {
        this.illnessDesc = illnessDesc;
    }

    public boolean isOD() {
        return isOD;
    }

    public void setOD(boolean OD) {
        isOD = OD;
    }

    public boolean isOI() {
        return isOI;
    }

    public void setOI(boolean OI) {
        isOI = OI;
    }

    @NonNull
    @Override
    public String toString() {
        return illnessDesc;
    }
}
