package com.example.androidgrupo7upc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class hc_general extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hc_general);
    }
    public void psiguiente(View v){
        startActivity(new Intent(this, hc_examen_visual.class));
    }
}