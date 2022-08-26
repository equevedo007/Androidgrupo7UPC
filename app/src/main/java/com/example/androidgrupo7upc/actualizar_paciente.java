package com.example.androidgrupo7upc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidgrupo7upc.databinding.ActivityMainBinding;

public class actualizar_paciente extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
            }
            
}