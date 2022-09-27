package com.example.androidgrupo7upc.ui.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.TEXTO_VACIO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.MasterType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.PacienteApi;
import com.example.androidgrupo7upc.ui.adapter.PacienteAdapter;
import com.example.androidgrupo7upc.util.Util;
import com.example.androidgrupo7upc.util.data.DataMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListadoPacienteActivity extends AppCompatActivity {

    private EditText txtNroDocumento, txtNombres;

    private AutoCompleteTextView spnTipoDocumento;
    private RecyclerView pacienteRecyclerView;
    private LinearLayoutManager manager;
    private PacienteAdapter pacienteAdapter;
    private ProgressBar progressBar;

    private String tipoDocumento = "0";
    private Boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_paciente);

        RESTManager.getInstance(this);

        progressBar = findViewById(R.id.progress_circular);

        obtenerPacientes(TEXTO_VACIO, TEXTO_VACIO, TEXTO_VACIO);

        spnTipoDocumento = findViewById(R.id.spnDocumentType);
        obtenerTipoDocumento();
        spnTipoDocumento.setOnItemClickListener((parent, view, position, id) -> {
            MasterType masterType = Util.getMasterList(DataMapper.tipoDocumentoMap).get(position);
            tipoDocumento = masterType.getId();
        });

        txtNroDocumento = findViewById(R.id.txtDocumentNumber);
        txtNombres = findViewById(R.id.txtFullName);

        MaterialButton btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(view -> {
            String nroDocumento = txtNroDocumento.getText().toString();
            String nombres = txtNombres.getText().toString();

            obtenerPacientes(tipoDocumento, nroDocumento, nombres);
            pacienteAdapter.notifyDataSetChanged();
        });

        manager = new LinearLayoutManager(this);
        pacienteRecyclerView = findViewById(R.id.lstPatients);
        pacienteRecyclerView.setLayoutManager(manager);
        pacienteRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {
                    isScrolling = false;
                    /**
                     * AQUI SE DEBE COLOCAR EL LLAMADO DEL API, CONSULTAR VALOR CON EDINSON
                     * */
                }
            }
        });

        FloatingActionButton fbtAddPatient = findViewById(R.id.btnAddPatient);
        fbtAddPatient.setOnClickListener(view -> startActivity(new Intent(ListadoPacienteActivity.this, RegistroPacienteActivity.class)));
    }

    private void obtenerTipoDocumento() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                R.layout.drop_down_item, Util.getMasterList(DataMapper.tipoDocumentoMap));
        spnTipoDocumento.setAdapter(adapterTipoDocumento);
    }

    private void obtenerPacientes(String tipoDocumento, String numeroDocumento, String nombres) {
        progressBar.setVisibility(VISIBLE);
        String token = (String) getSharedPreference(String.class, ListadoPacienteActivity.this, TOKEN);
        PacienteApi.getPatients(patientsResponse -> {
            if (S_CERO.equals(patientsResponse.getCodigoRespuesta())) {
                pacienteAdapter = new PacienteAdapter(this, patientsResponse.getPacientes());
                pacienteRecyclerView.setHasFixedSize(true);
                pacienteRecyclerView.setLayoutManager(new LinearLayoutManager(ListadoPacienteActivity.this));
                pacienteRecyclerView.setAdapter(pacienteAdapter);

                pacienteAdapter.notifyDataSetChanged();
                progressBar.setVisibility(GONE);
            } else {
                makeText(ListadoPacienteActivity.this, "Error al cargar pacientes", LENGTH_LONG).show();
            }
        }, tipoDocumento, numeroDocumento, nombres, token);
    }
}