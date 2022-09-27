package com.example.androidgrupo7upc.ui.activity;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.TEXTO_VACIO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.MasterType;
import com.example.androidgrupo7upc.model.PatientType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.PacienteApi;
import com.example.androidgrupo7upc.ui.adapter.PacienteAdapter;
import com.example.androidgrupo7upc.util.Util;
import com.example.androidgrupo7upc.util.data.DataMapper;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListadoPacienteActivity extends AppCompatActivity {

    private EditText txtNroDocumento, txtNombres;
    private AutoCompleteTextView spnTipoDocumento;

    private RecyclerView pacienteRecyclerView;
    private final List<PatientType> patientList = new ArrayList<>();
    private PacienteAdapter pacienteAdapter;
    private ProgressBar progressBar;
    private String nroDocumento = TEXTO_VACIO;
    private String nombres = TEXTO_VACIO;
    private String tipoDocumento = TEXTO_VACIO;
    private int pageNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_paciente);

        RESTManager.getInstance(this);

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
            nroDocumento = txtNroDocumento.getText().toString();
            nombres = txtNombres.getText().toString();

            if (!tipoDocumento.isEmpty() || !nroDocumento.isEmpty() || !nombres.isEmpty()) {
                patientList.clear();
                obtenerPacientes(tipoDocumento, nroDocumento, nombres, pageNumber);
            }
        });

        NestedScrollView nestedScrollView = findViewById(R.id.lytScroll);
        pacienteRecyclerView = findViewById(R.id.lstPatients);
        progressBar = findViewById(R.id.progress_circular);

        pacienteAdapter = new PacienteAdapter(this, patientList);
        pacienteRecyclerView.setHasFixedSize(true);
        pacienteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pacienteRecyclerView.setAdapter(pacienteAdapter);

        obtenerPacientes(TEXTO_VACIO, TEXTO_VACIO, TEXTO_VACIO, pageNumber);

        nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                pageNumber++;
                progressBar.setVisibility(VISIBLE);
                obtenerPacientes(tipoDocumento, nroDocumento, nombres, pageNumber);
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

    private void obtenerPacientes(String tipoDocumento, String numeroDocumento, String nombres, Integer numeroPagina) {
        String token = (String) getSharedPreference(String.class, ListadoPacienteActivity.this, TOKEN);
        PacienteApi.getPatients(patientsResponse -> {
            if (S_CERO.equals(patientsResponse.getCodigoRespuesta())) {
                progressBar.setVisibility(GONE);
                patientList.addAll(patientsResponse.getPacientes());

                pacienteAdapter = new PacienteAdapter(this, patientList);
                pacienteRecyclerView.setAdapter(pacienteAdapter);
            } else {
                progressBar.setVisibility(INVISIBLE);
                makeText(ListadoPacienteActivity.this, "Error al cargar pacientes", LENGTH_LONG).show();
            }
        }, tipoDocumento, numeroDocumento, nombres, String.valueOf(numeroPagina), token);
    }
}