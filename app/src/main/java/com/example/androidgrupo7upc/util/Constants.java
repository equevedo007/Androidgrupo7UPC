package com.example.androidgrupo7upc.util;

/**
 * Created by wramos on 20/02/2019.
 */

public class Constants {

    public static final String WS_OPEVISO_GENERAL_PATH = "http://139.144.33.83:8080/opeviso-api";

    public static final String S_CERO = "0";



    public static final String DATETIME_KEY = "QVES2IKAU7BA";
    public static final String WS_DATETIME = "http://api.timezonedb.com/v2.1/get-time-zone?key=" + DATETIME_KEY + "&format=json&by=zone&zone=America/Lima";


    public static final String WS_FIREBASE = "https://fcm.googleapis.com/fcm/send";
    public static final String WS_WHATSAPP = "https://api.whatsapp.com/send?phone=";

    public static final String URI_REQUEST_NOM_SALA = "$nombreSala$";
    public static final String URI_REQUEST_DESC_SALA = "$descripcionSala$";
    public static final String URI_REQUEST_ESTADO = "$estado$";
    public static final String URI_REQUEST_ESTADO_ATENCION = "$estadoAtencion$";
    public static final String URI_REQUEST_ID_USUARIO = "$idUsuario$";
    public static final String URI_REQUEST_USUARIO = "$usuario$";
    public static final String URI_REQUEST_ID_MEDICO = "$idMedico$";
    public static final String URI_REQUEST_NOM_MEDICO = "$nombreMedico$";
    public static final String URI_REQUEST_AP_PAT_MEDICO = "$apePaternoMedico$";
    public static final String URI_REQUEST_AP_MAT_MEDICO = "$apeMaternoMedico$";
    public static final String URI_REQUEST_ESPECI_MEDICO = "$especialidadMedico$";
    public static final String URI_REQUEST_ID_SALA = "$idSala$";

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    public static final String SEP_COMA = ",";
    public static final String TEXTO_ESPACIO = " ";


    public static final String S_UNO = "1";
    public static final String S_DOS = "2";
    public static final String S_TRES = "3";
    public static final String S_SEIS = "6";

    public static final String USUARIO_DEMO = "demousuariotp";
    public static final String FLAG_SISTEMA = "2";


    public static final int BASE_TIEMPO = 1000;
    public static final int BASE_MINUTO = 60;

    public static final String ID_USUARIO = "idUsuario";
    public static final String ID_ATENCION = "idAtencion";
    public static final String ID_SALA = "idSala";
    public static final String URL_SALA = "urlSala";
    public static final String INTERVAL_MSG = "msgAtencion";
    public static final String INTERVAL_FIN = "finalAtencion";


    public static final String CLASE_STRING = "String";
    public static final String CLASE_INT = "int";
    public static final String CLASE_BOOLEAN = "boolean";
    public static final String CLASE_LONG = "Long";

    public static final String TELEFONO_CENATE = "51956592787";

    public static final String TEXTO_HORA_CERO = ":00";
    public static final String DOMINGO = "Domingo";
    public static final String LUNES = "Lunes";
    public static final String MARTES = "Martes";
    public static final String MIERCOLES = "Mi\u00e9rcoles";
    public static final String JUEVES = "Jueves";
    public static final String VIERNES = "Viernes";
    public static final String SABADO = "S\u00e1bado";


    public static final String FORMATO_FECHA_DEFAULT = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_US = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_HORA_24 = "HH:mm";
    public static final String FORMATO_HORA_12 = "hh:mm a";

    public static final String DNI_DEMO = "11111111";
}
