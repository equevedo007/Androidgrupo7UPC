package com.example.androidgrupo7upc.util.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataMapper {

    public static final Map<String, String> tipoDocumentoMap;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("1", "DNI");
        map.put("2", "Carnet");
        map.put("3", "RUC");
        map.put("4", "Pasaporte");
        map.put("5", "Partida");
        map.put("6", "Otros");

        tipoDocumentoMap = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> tipoEstadoCivilMap;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("1", "Soltero");
        map.put("2", "Casado");
        map.put("3", "Conviviente");
        map.put("4", "Divorciado/Separado");
        map.put("5", "Viudo");

        tipoEstadoCivilMap = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> tipoGeneroMap;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("1", "Masculino");
        map.put("2", "Femenino");

        tipoGeneroMap = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> tipoGradoMap;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("1", "Primaria");
        map.put("2", "Secundaria");
        map.put("3", "Técnico Incompleto");
        map.put("4", "Técnico Completo");
        map.put("5", "Universitario Parcial");
        map.put("6", "Universitario Completo");
        map.put("7", "Otros");

        tipoGradoMap = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> enfermedadExistentes;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("1", "Colesterol");
        map.put("2", "Diabetes");
        map.put("3", "Trigliceridos");
        map.put("4", "Alergias");
        map.put("5", "Presión Arterial");
        map.put("6", "Contrajo Enfermedad Viral");
        map.put("7", "Tiroides");
        map.put("8", "Intervención Quirurgica");

        enfermedadExistentes = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> enfermedadFamiliar;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("9", "Diabetes");
        map.put("10", "Presión Arterial");
        map.put("11", "Glaucoma");
        map.put("12", "Retinopatía Diabetica");
        map.put("13", "Retinopatía Diabetica");

        enfermedadFamiliar = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> enfermedadOcular;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("14", "Ulceras e Infección");
        map.put("15", "Conjuntivitis");
        map.put("16", "Ptosis Palpebral");
        map.put("17", "Pterigión");
        map.put("18", "Ectroprión");
        map.put("19", "Entropión");

        enfermedadOcular = Collections.unmodifiableMap(map);
    }

    public static final Map<String, String> errorRefraccion;

    static {
        Map<String, String> map = new HashMap<>();
        map.put("20", "Miopia");
        map.put("21", "Hipermetropia");
        map.put("22", "Presbicia");
        map.put("23", "Astigmatismo");

        errorRefraccion = Collections.unmodifiableMap(map);
    }


}
