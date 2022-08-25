package com.example.androidgrupo7upc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class VisualizarUsuarios_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_usuarios);

        ListView lstProductos = (ListView)findViewById(R.id.lstUsuario);

        int i = 0;
        String[] matriz = new String[6];
        matriz[i++] = "Usuario 01";
        matriz[i++] = "Usuario 02";
        matriz[i++] = "Usuario 03";
        matriz[i++] = "Usuario 04";
        matriz[i++] = "Usuario 05";
        matriz[i++] = "Usuario 06";

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(VisualizarUsuarios_Activity.this,
                android.R.layout.simple_list_item_1,
                matriz);
        lstProductos.setAdapter(adaptador);


    }
}