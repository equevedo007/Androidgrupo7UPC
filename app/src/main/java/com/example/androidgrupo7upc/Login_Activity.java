package com.example.androidgrupo7upc;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static com.example.androidgrupo7upc.util.Constants.NOMBRE;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgrupo7upc.model.LoginRequest;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.LoginApi;
import com.example.androidgrupo7upc.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.json.JSONException;


public class Login_Activity extends AppCompatActivity {

    EditText txtUsuario, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.txtUsuario);
        txtClave = findViewById(R.id.txtClave);

        RESTManager.getInstance(this);
    }

    public void login(View view) {
        boolean error = false;
        String usuario = txtUsuario.getText().toString();
        String clave = txtClave.getText().toString();

        if (usuario.isEmpty()) {
            txtUsuario.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (clave.isEmpty()) {
            txtClave.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (error) {
            Toast.makeText(getBaseContext(), "Favor de registrar los campos requeridos.", LENGTH_SHORT).show();
        } else {
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsuario(usuario);
            loginRequest.setPassword(clave);
            try {
                LoginApi.login(loginResponse -> {
                    if (S_CERO.equals(loginResponse.getCodigoRespuesta())) {
                        Util.setSharedPreference(String.class, getApplicationContext(), NOMBRE, loginResponse.getUsuario().getNombres());
                        Util.setSharedPreference(String.class, getApplicationContext(), TOKEN, loginResponse.getUsuario().getToken());
                        startActivity(new Intent(this, menu_Activity.class));
                    } else {
                        Toast.makeText(Login_Activity.this, loginResponse.getMensajeRespuesta(), LENGTH_LONG).show();
                    }
                }, loginRequest);
            } catch (JsonProcessingException | JSONException e) {
                Toast.makeText(Login_Activity.this, String.valueOf(e), LENGTH_LONG).show();
            }
        }
    }
}


