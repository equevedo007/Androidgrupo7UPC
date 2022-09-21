package com.example.androidgrupo7upc.network.impl;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;
import static com.example.androidgrupo7upc.network.RESTManager.getContext;
import static com.example.androidgrupo7upc.network.RESTManager.getRequestQueue;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.WS_OPEVISO_GENERAL_PATH;
import static com.example.androidgrupo7upc.util.Util.getValueJSON;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidgrupo7upc.model.LoginResponse;
import com.example.androidgrupo7upc.model.PatientRequest;
import com.example.androidgrupo7upc.model.PatientResponse;
import com.example.androidgrupo7upc.model.PatientType;
import com.example.androidgrupo7upc.model.ResponseType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PacienteApi {

    public static void getPatients(final RESTManager.RESTListener<PatientResponse> pacienteListener,
                                   String tipoDocumento, String numeroDocumento, String nombres, String token) {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/paciente/search?pageSize=10&sortBy=idPaciente&direction=desc";

        if (!isEmpty(tipoDocumento)) {
            url = url + "&tipoDoc=" + tipoDocumento;
        }
        if (!isEmpty(numeroDocumento)) {
            url = url + "&numDoc=" + numeroDocumento;
        }
        if (!isEmpty(nombres)) {
            url = url + "&nomComp=" + nombres;
        }

        JsonObjectRequest jsonArrayResponse = new JsonObjectRequest(GET, url, null, response -> {
            PatientResponse patientResponse = null;

            try {
                List<PatientType> datosPacienteLista = new ArrayList<>();
                JSONArray pacientesObject = response.getJSONArray("pacientes");

                if (pacientesObject.length() > 0) {
                    for (int i = 0; i < pacientesObject.length(); i++) {
                        JSONObject pacienteObject = pacientesObject.getJSONObject(i);
                        PatientType patientType = new PatientType();
                        patientType.setIdEstado(valueOf(getValueJSON(pacienteObject, "idEstado")));
                        patientType.setDescEstado(valueOf(getValueJSON(pacienteObject, "descEstado")));
                        patientType.setIdPaciente(valueOf(getValueJSON(pacienteObject, "idPaciente")));
                        patientType.setNombres(valueOf(getValueJSON(pacienteObject, "nombres")));
                        patientType.setApePaterno(valueOf(getValueJSON(pacienteObject, "apePaterno")));
                        patientType.setApeMaterno(valueOf(getValueJSON(pacienteObject, "apeMaterno")));
                        patientType.setTipoDocumento(valueOf(getValueJSON(pacienteObject, "tipoDocumento")));
                        patientType.setNumeroDocumento(valueOf(getValueJSON(pacienteObject, "numeroDocumento")));

                        datosPacienteLista.add(patientType);
                    }
                }
                patientResponse = new PatientResponse();
                patientResponse.setCodigoRespuesta(valueOf(getValueJSON(response, "codigoRespuesta")));
                patientResponse.setMensajeRespuesta(valueOf(getValueJSON(response, "mensajeRespuesta")));
                patientResponse.setTotalElementos(parseInt(
                        valueOf(getValueJSON(response, "totalElementos") != null
                                ? getValueJSON(response, "totalElementos")
                                : S_CERO)));
                patientResponse.setTotalPaginas(parseInt(valueOf(getValueJSON(response, "totalPaginas"))));
                patientResponse.setNumeroPagina(parseInt(valueOf(getValueJSON(response, "numeroPagina"))));
                patientResponse.setTamannoPagina(parseInt(valueOf(getValueJSON(response, "tamannoPagina"))));
                patientResponse.setNumeroElementos(parseInt(valueOf(getValueJSON(response, "numeroElementos"))));
                patientResponse.setPacientes(datosPacienteLista);
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            pacienteListener.onResult(patientResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        getRequestQueue().add(jsonArrayResponse);
    }

    public static void addPatient(final RESTManager.RESTListener<ResponseType> pacienteListener,
                                  final String token, final PatientRequest pacienteRequest) throws JsonProcessingException, JSONException {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/paciente/new";

        ObjectMapper mapper = new ObjectMapper();
        String requestString = mapper.writeValueAsString(pacienteRequest);
        final JSONObject jsonRequest = new JSONObject(requestString);

        JsonObjectRequest jsonResponse = new JsonObjectRequest(POST, url, jsonRequest, response -> {
            ResponseType patientResponse = null;

            try {
                patientResponse = new LoginResponse();
                patientResponse.setCodigoRespuesta(valueOf(getValueJSON(response, "codigoRespuesta")));
                patientResponse.setMensajeRespuesta(valueOf(getValueJSON(response, "mensajeRespuesta")));
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            pacienteListener.onResult(patientResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show()) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };
        getRequestQueue().add(jsonResponse);
    }
}
