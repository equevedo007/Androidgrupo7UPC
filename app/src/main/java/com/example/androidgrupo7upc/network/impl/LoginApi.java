package com.example.androidgrupo7upc.network.impl;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.android.volley.Request.Method.POST;
import static com.example.androidgrupo7upc.network.RESTManager.getContext;
import static com.example.androidgrupo7upc.network.RESTManager.getRequestQueue;
import static com.example.androidgrupo7upc.util.Constants.WS_OPEVISO_GENERAL_PATH;
import static java.lang.String.valueOf;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.androidgrupo7upc.model.LoginRequest;
import com.example.androidgrupo7upc.model.LoginResponse;
import com.example.androidgrupo7upc.model.UserType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

//Edinson Quevedo}
public class LoginApi {

    public static void login(final RESTManager.RESTListener<LoginResponse> loginListener,
                             final LoginRequest loginRequest) throws JsonProcessingException, JSONException {
        String url = WS_OPEVISO_GENERAL_PATH + "/v1/login";

        ObjectMapper mapper = new ObjectMapper();
        String requestString = mapper.writeValueAsString(loginRequest);
        final JSONObject jsonRequest = new JSONObject(requestString);

        JsonObjectRequest jsonResponse = new JsonObjectRequest(POST, url, jsonRequest, response -> {
            LoginResponse loginResponse = null;

            try {
                UserType datosUsuario = new UserType();
                JSONObject usuarioObject = (JSONObject) Util.getValueJSON(response, "usuario");

                if (usuarioObject != null) {
                    datosUsuario.setIdEstado(valueOf(Util.getValueJSON(usuarioObject, "idEstado")));
                    datosUsuario.setDescEstado(valueOf(Util.getValueJSON(usuarioObject, "descEstado")));
                    datosUsuario.setIdUsuario(valueOf(Util.getValueJSON(usuarioObject, "idUsuario")));
                    datosUsuario.setUsuario(valueOf(Util.getValueJSON(usuarioObject, "usuario")));
                    datosUsuario.setClave(valueOf(Util.getValueJSON(usuarioObject, "clave")));
                    datosUsuario.setNombres(valueOf(Util.getValueJSON(usuarioObject, "nombres")));
                    datosUsuario.setApePaterno(valueOf(Util.getValueJSON(usuarioObject, "apePaterno")));
                    datosUsuario.setApeMaterno(valueOf(Util.getValueJSON(usuarioObject, "apeMaterno")));
                    datosUsuario.setTipoDocumento(valueOf(Util.getValueJSON(usuarioObject, "tipoDocumento")));
                    datosUsuario.setNumeroDocumento(valueOf(Util.getValueJSON(usuarioObject, "numeroDocumento")));
                    datosUsuario.setEmail(valueOf(Util.getValueJSON(usuarioObject, "email")));
                    datosUsuario.setDireccion(valueOf(Util.getValueJSON(usuarioObject, "direccion")));
                    datosUsuario.setToken(valueOf(Util.getValueJSON(usuarioObject, "token")));
                    datosUsuario.setFlagSesion(valueOf(Util.getValueJSON(usuarioObject, "flagSesion")));
                    datosUsuario.setIdPerfil(valueOf(Util.getValueJSON(usuarioObject, "idPerfil")));
                    datosUsuario.setDescPerfil(valueOf(Util.getValueJSON(usuarioObject, "descPerfil")));
                }

                loginResponse = new LoginResponse();
                loginResponse.setCodigoRespuesta(valueOf(Util.getValueJSON(response, "codigoRespuesta")));
                loginResponse.setMensajeRespuesta(valueOf(Util.getValueJSON(response, "mensajeRespuesta")));
                loginResponse.setUsuario(datosUsuario);
            } catch (JSONException e) {
                makeText(getContext(), valueOf(e), LENGTH_LONG).show();
            }
            loginListener.onResult(loginResponse);
        }, error -> makeText(getContext(), valueOf(error), LENGTH_LONG).show());

        getRequestQueue().add(jsonResponse);
    }
}
