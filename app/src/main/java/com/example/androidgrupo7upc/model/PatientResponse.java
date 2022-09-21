package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class PatientResponse extends PaginationType {

    @JsonProperty("pacientes")
    private List<PatientType> pacientes;

    public List<PatientType> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<PatientType> pacientes) {
        this.pacientes = pacientes;
    }
}
