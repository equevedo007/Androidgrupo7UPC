package com.example.androidgrupo7upc;

import static com.example.androidgrupo7upc.util.Constants.TEXTO_VACIO;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.androidgrupo7upc.util.Constants;
import com.example.androidgrupo7upc.util.Util;

import org.apache.commons.lang3.StringUtils;

public class menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String nombre = (String) getSharedPreference(String.class, menu_Activity.this, Constants.NOMBRE);

        TextView txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText(String.format("Bienvenido%s", StringUtils.isEmpty(nombre) ? TEXTO_VACIO : ", " + nombre));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_Paciente:
                Log.i("====>","Click en Menu Paciente!!");
                startActivity(new Intent(this, Registropaciente.class));
                return true;
            case R.id.menu_Registro_Historia_Antecedentes:
                Log.i("====>","Click en Registro Historia Antecedentes!!");
                startActivity(new Intent(this, RegistroAntecedentes_Activity.class));

                return true;
            case R.id.menu_Visualizar_Usuarios:
                Log.i("====>","Click en Visualizar Usuarios!!");
                startActivity(new Intent(this, VisualizarUsuarios_Activity.class));
                return true;

            case R.id.menu_salir:
                Log.i("====>","Click en Salir!!");
                startActivity(new Intent(this,Login_Activity.class));
                return true;
            case R.id.menu_hc:
                Log.i("====>","Click en Historia Clinica");
                startActivity(new Intent(this,hc_general.class));
                return true;
            case R.id.menu_reguser:
                Log.i("====>","Click en Historia clinica");
                startActivity(new Intent(this,create_user.class));
                return true;
            case R.id.menu_changepassword:
                Log.i("====>","Click en Historia clinica");
                startActivity(new Intent(this,change_password.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}