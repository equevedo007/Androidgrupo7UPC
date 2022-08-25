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
        matriz[i++] = "Pop";
        matriz[i++] = "Punk";
        matriz[i++] = "Música Clásica";
        matriz[i++] = "Opera";
        matriz[i++] = "Rock";
        matriz[i++] = "Salsa";

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(VisualizarUsuarios_Activity.this,
                android.R.layout.simple_list_item_1,
                matriz);
        lstProductos.setAdapter(adaptador);


    }
}