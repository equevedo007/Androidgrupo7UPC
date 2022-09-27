package com.example.androidgrupo7upc.network.impl;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.android.volley.Request.Method.DELETE;
import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;
import static com.example.androidgrupo7upc.network.RESTManager.getContext;
import static com.example.androidgrupo7upc.network.RESTManager.getRequestQueue;
import static com.example.androidgrupo7upc.util.Constants.WS_OPEVISO_GENERAL_PATH;
import static com.example.androidgrupo7upc.util.Util.getValueJSON;
import static java.lang.String.valueOf;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidgrupo7upc.model.AntecedentType;
import com.example.androidgrupo7upc.model.AttentionRequest;
import com.example.androidgrupo7upc.model.AttentionResponse;
import com.example.androidgrupo7upc.model.AttentionType;
import com.example.androidgrupo7upc.model.PatientType;
import com.example.androidgrupo7upc.model.PendingAttentionResponse;
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

public class AntecedentesApi {

    public static void getClinicHistory(final RESTManager.RESTListener<List<AttentionType>> atencionListener,
                                        final String token, final PatientType atencionRequest) throws JsonProcessingException, JSONException {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/atencion/paciente?tipoDocumento=" + atencionRequest.getTipoDocumento() +
                "&numeroDocumento=" + atencionRequest.getNumeroDocumento();

        ObjectMapper mapper = new ObjectMapper();
        String requestString = mapper.writeValueAsString(atencionRequest);
        final JSONObject jsonRequest = new JSONObject(requestString);

        JsonObjectRequest jsonResponse = new JsonObjectRequest(GET, url, jsonRequest, response -> {
            List<AttentionType> atencionResponse = new ArrayList<>();

            try {
                JSONArray historiasObject = response.getJSONArray("historias");

                if (historiasObject.length() > 0) {
                    for (int i = 0; i < historiasObject.length(); i++) {
                        JSONObject historiaObject = historiasObject.getJSONObject(i);
                        AttentionType attentionType = new AttentionType();
                        attentionType.setIdHistoria(valueOf(getValueJSON(historiaObject, "idHistoria")));
                        attentionType.setCodHistoria(valueOf(getValueJSON(historiaObject, "codHistoria")));
                        attentionType.setIdPaciente(valueOf(getValueJSON(historiaObject, "idPaciente")));
                        attentionType.setNombres(valueOf(getValueJSON(historiaObject, "nombres")));
                        attentionType.setApePaterno(valueOf(getValueJSON(historiaObject, "apePaterno")));
                        attentionType.setApeMaterno(valueOf(getValueJSON(historiaObject, "apeMaterno")));
                        attentionType.setNumeroDocumento(valueOf(getValueJSON(historiaObject, "numeroDocumento")));
                        attentionType.setEdad(valueOf(getValueJSON(historiaObject, "edad")));
                        attentionType.setDireccion(valueOf(getValueJSON(historiaObject, "direccion")));

                        atencionResponse.add(attentionType);
                    }
                }
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            atencionListener.onResult(atencionResponse);
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

    public static void getPendingAttention(final RESTManager.RESTListener<PendingAttentionResponse> atencionListener,
                                           final String token, final String clinicHistoryId) {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/atencion/pendiente?idHistoria=" + clinicHistoryId;

        JsonObjectRequest jsonResponse = new JsonObjectRequest(GET, url, null, response -> {
            PendingAttentionResponse atencionResponse = null;
            List<AttentionType> attentionTypes = new ArrayList<>();

            try {
                JSONArray historiasObject = response.getJSONArray("atenciones");
                if (historiasObject.length() > 0) {
                    for (int i = 0; i < historiasObject.length(); i++) {
                        JSONObject historiaObject = historiasObject.getJSONObject(i);
                        AttentionType attentionType = new AttentionType();
                        attentionType.setIdHistoria(valueOf(getValueJSON(historiaObject, "idHistoria")));
                        attentionType.setIdAtencion(valueOf(getValueJSON(historiaObject, "idAtencion")));

                        attentionTypes.add(attentionType);
                    }
                }

                atencionResponse = new PendingAttentionResponse();
                atencionResponse.setCodigoRespuesta(valueOf(getValueJSON(response, "codigoRespuesta")));
                atencionResponse.setMensajeRespuesta(valueOf(getValueJSON(response, "mensajeRespuesta")));
                atencionResponse.setAtenciones(attentionTypes);
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            atencionListener.onResult(atencionResponse);
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

    public static void deletePreviousAttention(final RESTManager.RESTListener<ResponseType> atencionListener,
                                               final String token, final String attentionId) {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/atencion/pendiente?idAtencion=" + attentionId;

        JsonObjectRequest jsonResponse = new JsonObjectRequest(DELETE, url, null, response -> {
            ResponseType atencionResponse = null;

            try {
                atencionResponse = new ResponseType();
                atencionResponse.setCodigoRespuesta(valueOf(getValueJSON(response, "codigoRespuesta")));
                atencionResponse.setMensajeRespuesta(valueOf(getValueJSON(response, "mensajeRespuesta")));
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            atencionListener.onResult(atencionResponse);
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

    public static void addAttention(final RESTManager.RESTListener<AttentionResponse> atencionListener,
                                    final String token, final AttentionRequest atencionRequest) throws JsonProcessingException, JSONException {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/atencion/new";

        ObjectMapper mapper = new ObjectMapper();
        String requestString = mapper.writeValueAsString(atencionRequest);
        final JSONObject jsonRequest = new JSONObject(requestString);

        JsonObjectRequest jsonResponse = new JsonObjectRequest(POST, url, jsonRequest, response -> {
            AttentionResponse atencionResponse = null;

            try {
                atencionResponse = new AttentionResponse();
                atencionResponse.setCodigoRespuesta(valueOf(getValueJSON(response, "codigoRespuesta")));
                atencionResponse.setMensajeRespuesta(valueOf(getValueJSON(response, "mensajeRespuesta")));
                atencionResponse.setIdAtencion(valueOf(getValueJSON(response, "idAtencion")));
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            atencionListener.onResult(atencionResponse);
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


    public static void addAntecedent(final RESTManager.RESTListener<ResponseType> antecedenteListener,
                                     final String token, final AntecedentType antecedenteRequest) throws JsonProcessingException, JSONException {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/historia/antecedentes";

        ObjectMapper mapper = new ObjectMapper();
        String requestString = mapper.writeValueAsString(antecedenteRequest);
        final JSONObject jsonRequest = new JSONObject(requestString);

        JsonObjectRequest jsonResponse = new JsonObjectRequest(POST, url, jsonRequest, response -> {
            ResponseType antedenteResponse = null;

            try {
                antedenteResponse = new ResponseType();
                antedenteResponse.setCodigoRespuesta(valueOf(getValueJSON(response, "codigoRespuesta")));
                antedenteResponse.setMensajeRespuesta(valueOf(getValueJSON(response, "mensajeRespuesta")));
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            antecedenteListener.onResult(antedenteResponse);
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
