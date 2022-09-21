package com.example.androidgrupo7upc.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataMapper {

    public static final Map<String, String> tipoDocumentoMap;
    public static final Map<String, String> tipoEstadoCivilMap;
    public static final Map<String, String> tipoGeneroMap;
    public static final Map<String, String> tipoGradoMap;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("0", "Selecciona un tipo de documento");
        map.put("1", "DNI");
        map.put("2", "Carnet");
        map.put("3", "RUC");
        map.put("4", "Pasaporte");
        map.put("5", "Partida");
        map.put("6", "Otros");

        tipoDocumentoMap = Collections.unmodifiableMap(map);
    }

    static {
        Map<String, String> map = new HashMap<>();
        map.put("0", "Selecciona un estado civil");
        map.put("1", "Soltero");
        map.put("2", "Casado");
        map.put("3", "Conviviente");
        map.put("4", "Divorciado/Separado");
        map.put("5", "Viudo");

        tipoEstadoCivilMap = Collections.unmodifiableMap(map);
    }

    static {
        Map<String, String> map = new HashMap<>();
        map.put("0", "Selecciona un género");
        map.put("1", "Masculino");
        map.put("2", "Femenino");

        tipoGeneroMap = Collections.unmodifiableMap(map);
    }

    static {
        Map<String, String> map = new HashMap<>();
        map.put("0", "Selecciona un grado de instrucción");
        map.put("1", "Primaria");
        map.put("2", "Secundaria");
        map.put("3", "Técnico Incompleto");
        map.put("4", "Técnico Completo");
        map.put("5", "Universitario Parcial");
        map.put("6", "Universitario Completo");
        map.put("7", "Otros");

        tipoGradoMap = Collections.unmodifiableMap(map);
    }
}
