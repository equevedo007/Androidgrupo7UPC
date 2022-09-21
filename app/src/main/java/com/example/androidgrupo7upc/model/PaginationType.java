package com.example.androidgrupo7upc.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class PaginationType extends ResponseType {

    @JsonProperty("totalElementos")
    private int totalElementos;

    @JsonProperty("totalPaginas")
    private int totalPaginas;

    @JsonProperty("numeroPagina")
    private int numeroPagina;

    @JsonProperty("tamannoPagina")
    private int tamannoPagina;

    @JsonProperty("numeroElementos")
    private int numeroElementos;

    public int getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(int totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getTamannoPagina() {
        return tamannoPagina;
    }

    public void setTamannoPagina(int tamannoPagina) {
        this.tamannoPagina = tamannoPagina;
    }

    public int getNumeroElementos() {
        return numeroElementos;
    }

    public void setNumeroElementos(int numeroElementos) {
        this.numeroElementos = numeroElementos;
    }
}
