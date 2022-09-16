package com.example.androidgrupo7upc.network.impl;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.android.volley.Request.Method.POST;
import static com.example.androidgrupo7upc.network.RESTManager.getContext;
import static com.example.androidgrupo7upc.network.RESTManager.getRequestQueue;
import static com.example.androidgrupo7upc.util.Constants.WS_OPEVISO_GENERAL_PATH;
import static java.lang.String.valueOf;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidgrupo7upc.model.LoginResponse;
import com.example.androidgrupo7upc.model.PatientRequest;
import com.example.androidgrupo7upc.model.ResponseType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//Edinson Quevedo}
public class PacienteApi {

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
                patientResponse.setCodigoRespuesta(valueOf(Util.getValueJSON(response, "codigoRespuesta")));
                patientResponse.setMensajeRespuesta(valueOf(Util.getValueJSON(response, "mensajeRespuesta")));
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            pacienteListener.onResult(patientResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show()) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        getRequestQueue().add(jsonResponse);
    }
}
