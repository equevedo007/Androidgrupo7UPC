package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class PendingAttentionResponse extends ResponseType {

    @JsonProperty("atenciones")
    private List<AttentionType> atenciones;

    public List<AttentionType> getAtenciones() {
        return atenciones;
    }

    public void setAtenciones(List<AttentionType> atenciones) {
        this.atenciones = atenciones;
    }
}
