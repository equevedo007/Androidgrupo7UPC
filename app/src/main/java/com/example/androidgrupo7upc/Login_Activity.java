package com.example.androidgrupo7upc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void procesar (View v){
       // startActivity(new Intent(this, menu_Activity.class));

        startActivity(new Intent(this, menu_Activity.class));
    }


}