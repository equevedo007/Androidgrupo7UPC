package com.example.androidgrupo7upc.ui.activity;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static com.example.androidgrupo7upc.util.Constants.NOMBRE;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;
import static com.example.androidgrupo7upc.util.UiUtil.findViewsWithType;
import static com.example.androidgrupo7upc.util.Util.setSharedPreference;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.LoginRequest;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.LoginApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText txtUsuario, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsuario = findViewById(R.id.txtUser);
        txtClave = findViewById(R.id.txtPass);

        RESTManager.getInstance(this);
    }

    public void toHome(View view) {
        boolean error = false;

        ViewGroup root = findViewById(R.id.lytContent);
        List<TextInputLayout> textInputLayouts = findViewsWithType(root, TextInputLayout.class);

        for (TextInputLayout textInputLayout : textInputLayouts) {
            String value = requireNonNull(textInputLayout.getEditText()).getText().toString();
            if (StringUtils.isEmpty(value)) {
                textInputLayout.setError(getString(R.string.required_field));
                error = true;
            } else {
                textInputLayout.setError(null);
            }
        }
        if (error) {
            makeText(getBaseContext(), "Favor de ingresar los campos requeridos.", LENGTH_SHORT).show();
        } else {
            String username = txtUsuario.getText().toString();
            String clave = txtClave.getText().toString();
            login(username, clave);
        }
    }

    private void login(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsuario(username);
        loginRequest.setPassword(password);
        try {
            LoginApi.login(loginResponse -> {
                if (S_CERO.equals(loginResponse.getCodigoRespuesta())) {
                    setSharedPreference(String.class, getApplicationContext(), NOMBRE, loginResponse.getUsuario().getNombres());
                    setSharedPreference(String.class, getApplicationContext(), TOKEN, loginResponse.getUsuario().getToken());
                    startActivity(new Intent(this, MenuActivity.class));
                } else {
                    makeText(LoginActivity.this, loginResponse.getMensajeRespuesta(), LENGTH_LONG).show();
                }
            }, loginRequest);
        } catch (JsonProcessingException | JSONException e) {
            makeText(LoginActivity.this, valueOf(e), LENGTH_LONG).show();
        }
    }
}


