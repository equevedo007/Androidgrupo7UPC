package com.example.androidgrupo7upc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class menu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}