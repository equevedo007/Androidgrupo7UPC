package com.example.androidgrupo7upc.ui.activity;

import static com.example.androidgrupo7upc.util.Constants.NOMBRE;
import static com.example.androidgrupo7upc.util.Constants.TEXTO_VACIO;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgrupo7upc.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String nombre = (String) getSharedPreference(String.class, MenuActivity.this, NOMBRE);

        TextView txtWelcome = findViewById(R.id.txtWelcome);
        txtWelcome.setText(String.format("Bienvenido%s", isEmpty(nombre) ? TEXTO_VACIO : ", " + nombre));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_Paciente) {
            startActivity(new Intent(this, ListadoPacienteActivity.class));
            return true;
        } else if (itemId == R.id.menu_salir) {
            startActivity(new Intent(this, LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}