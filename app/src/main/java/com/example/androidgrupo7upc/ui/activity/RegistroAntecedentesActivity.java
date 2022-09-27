package com.example.androidgrupo7upc.ui.activity;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.example.androidgrupo7upc.util.Constants.FORMATO_FECHA_DEFAULT;
import static com.example.androidgrupo7upc.util.Constants.MOTIVO_DEFAULT;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.S_UNO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;
import static com.example.androidgrupo7upc.util.Util.getStringLocalDateTime;
import static java.lang.String.valueOf;
import static java.time.LocalDateTime.now;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.AntecedentType;
import com.example.androidgrupo7upc.model.AttentionRequest;
import com.example.androidgrupo7upc.model.AttentionType;
import com.example.androidgrupo7upc.model.IllnessType;
import com.example.androidgrupo7upc.model.PatientType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.AntecedentesApi;
import com.example.androidgrupo7upc.ui.adapter.checkbox.CheckBoxAdapter;
import com.example.androidgrupo7upc.ui.adapter.checkbox.CheckBoxListener;
import com.example.androidgrupo7upc.ui.adapter.doublecheckbox.DoubleCheckBoxAdapter;
import com.example.androidgrupo7upc.ui.adapter.doublecheckbox.DoubleCheckBoxListener;
import com.example.androidgrupo7upc.ui.dialog.CustomDialog;
import com.example.androidgrupo7upc.util.Util;
import com.example.androidgrupo7upc.util.data.DataMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RegistroAntecedentesActivity extends AppCompatActivity implements CheckBoxListener, DoubleCheckBoxListener {

    private CustomDialog customDialog;

    private PatientType patient;

    private List<String> existingIllnessList = new ArrayList<>();
    private List<String> familyIllnessList = new ArrayList<>();
    private List<IllnessType> refractiveErrorList = new ArrayList<>();
    private List<IllnessType> eyesIllnessList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_antecedentes);

        if (getIntent().getExtras() != null) {
            patient = (PatientType) getIntent().getSerializableExtra("paciente");
        }

        RESTManager.getInstance(this);

        TextView txtPaciente = findViewById(R.id.txtPatient);
        txtPaciente.setText(String.format("[Paciente: %s %s %s]", patient.getNombres(),
                patient.getApePaterno(), patient.getApeMaterno()));

        RecyclerView lstExistingIllness = findViewById(R.id.lstExistingIllness);
        obtenerEnfermedades(lstExistingIllness, Util.getIllnessList(DataMapper.enfermedadExistentes));

        RecyclerView lstFamilyIllness = findViewById(R.id.lstFamilyIllness);
        obtenerEnfermedades(lstFamilyIllness, Util.getIllnessList(DataMapper.enfermedadFamiliar));

        RecyclerView lstRefractiveError = findViewById(R.id.lstRefractiveError);
        obtenerEnfermedadesODOI(lstRefractiveError, Util.getIllnessList(DataMapper.errorRefraccion));

        RecyclerView lstEyesIllness = findViewById(R.id.lstEyesIllness);
        obtenerEnfermedadesODOI(lstEyesIllness, Util.getIllnessList(DataMapper.enfermedadOcular));

        customDialog = new CustomDialog(this);
        MaterialButton btnSearch = findViewById(R.id.btnRegistrar);
        btnSearch.setOnClickListener(view -> {
            obtenerHistoriaClinica(patient.getTipoDocumento(), patient.getNumeroDocumento());
        });
    }

    private void obtenerEnfermedades(RecyclerView recyclerView, List<IllnessType> illnessTypes) {
        CheckBoxAdapter checkBoxAdapter = new CheckBoxAdapter(this, illnessTypes, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(checkBoxAdapter);
    }

    private void obtenerEnfermedadesODOI(RecyclerView recyclerView, List<IllnessType> illnessTypes) {
        DoubleCheckBoxAdapter doubleCheckBoxAdapter = new DoubleCheckBoxAdapter(this, illnessTypes, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(doubleCheckBoxAdapter);
    }

    @Override
    public void onItemClick(List<String> list) {
        existingIllnessList.addAll(DataMapper.enfermedadExistentes
                .keySet()
                .stream()
                .filter(list::contains)
                .collect(Collectors.toList()));

        familyIllnessList.addAll(DataMapper.enfermedadFamiliar
                .keySet()
                .stream()
                .filter(list::contains)
                .collect(Collectors.toList()));
    }

    @Override
    public void onDoubleItemClick(List<IllnessType> list) {
        list.stream()
                .filter(x -> DataMapper.errorRefraccion
                        .keySet()
                        .stream()
                        .anyMatch(y -> x.getIllnessId().equals(y)))
                .reduce((a, b) -> b)
                .ifPresent(illnessType -> refractiveErrorList.add(illnessType));

        list.stream()
                .filter(x -> DataMapper.enfermedadOcular
                        .keySet()
                        .stream()
                        .anyMatch(y -> x.getIllnessId().equals(y)))
                .reduce((a, b) -> b)
                .ifPresent(illnessType -> eyesIllnessList.add(illnessType));
    }

    private List<IllnessType> getReduceIllnessList(List<IllnessType> list) {
        return new HashSet<>(list)
                .stream()
                .filter(x -> Objects.nonNull(x) && (x.isOD() || x.isOI()))
                .collect(Collectors.toList());
    }

    private List<String> getReduceStringList(List<String> list) {
        return new ArrayList<>(new HashSet<>(list));
    }

    private void obtenerHistoriaClinica(String documentType, String documentNumber) {
        customDialog.show();
        String token = (String) getSharedPreference(String.class, RegistroAntecedentesActivity.this, TOKEN);

        PatientType attentionRequest = new PatientType();
        attentionRequest.setTipoDocumento(documentType);
        attentionRequest.setNumeroDocumento(documentNumber);
        try {
            AntecedentesApi.getClinicHistory(attentionResponse -> {
                if (attentionResponse.size() > 0) {
                    AttentionType attentionType = attentionResponse.get(attentionResponse.size() - 1);
                    obtenerAtencionPendiente(token, attentionType.getIdHistoria());
                } else {
                    customDialog.cancel();
                    makeText(RegistroAntecedentesActivity.this, "Error al obtener número de historia", LENGTH_LONG).show();
                }
            }, token, attentionRequest);
        } catch (JsonProcessingException | JSONException e) {
            customDialog.cancel();
            makeText(RegistroAntecedentesActivity.this, valueOf(e), LENGTH_LONG).show();
        }
    }

    private void obtenerAtencionPendiente(String token, String clinicHistoryId) {
        AntecedentesApi.getPendingAttention(attentionResponse -> {
            if (S_CERO.equals(attentionResponse.getCodigoRespuesta())) {
                if (attentionResponse.getAtenciones().size() > 0) {
                    AttentionType attentionType = attentionResponse.getAtenciones().get(attentionResponse.getAtenciones().size() - 1);
                    eliminarAtencionPendiente(token, attentionType.getIdAtencion(), clinicHistoryId);
                } else {
                    registrarAtencion(token, clinicHistoryId);
                }
            } else {
                customDialog.cancel();
                makeText(RegistroAntecedentesActivity.this, "Error al obtener atención pendiente", LENGTH_LONG).show();
            }
        }, token, clinicHistoryId);
    }

    private void eliminarAtencionPendiente(String token, String attentionId, String clinicHistoryId) {
        AntecedentesApi.deletePreviousAttention(attentionResponse -> {
            if (S_CERO.equals(attentionResponse.getCodigoRespuesta())) {
                registrarAtencion(token, clinicHistoryId);
            } else {
                customDialog.cancel();
                makeText(RegistroAntecedentesActivity.this, "Error al eliminar atención pendiente", LENGTH_LONG).show();
            }
        }, token, attentionId);
    }

    private void registrarAtencion(String token, String clinicHistoryId) {
        AttentionRequest attentionRequest = new AttentionRequest();
        attentionRequest.setIdHistoria(clinicHistoryId);
        attentionRequest.setFecha(getStringLocalDateTime(now(), FORMATO_FECHA_DEFAULT));
        attentionRequest.setIdLocal(S_UNO);
        attentionRequest.setMotivo(MOTIVO_DEFAULT);
        try {
            AntecedentesApi.addAttention(attentionResponse -> {
                if (S_CERO.equals(attentionResponse.getCodigoRespuesta())) {
                    registrarAntecedentes(token, clinicHistoryId);
                } else {
                    customDialog.cancel();
                    makeText(RegistroAntecedentesActivity.this, "Error al registrar atención", LENGTH_LONG).show();
                }
            }, token, attentionRequest);
        } catch (JsonProcessingException | JSONException e) {
            customDialog.cancel();
            makeText(RegistroAntecedentesActivity.this, valueOf(e), LENGTH_LONG).show();
        }
    }

    private void registrarAntecedentes(String token, String clinicHistoryId) {
        AntecedentType antecedentRequest = new AntecedentType();
        antecedentRequest.setIdHistoria(clinicHistoryId);
        antecedentRequest.setEnfermedadesExistentes(getReduceStringList(existingIllnessList));
        antecedentRequest.setOtrasExistentes(new ArrayList<>());
        antecedentRequest.setEnfermedadesFamiliares(getReduceStringList(familyIllnessList));
        antecedentRequest.setOtrasFamiliares(new ArrayList<>());
        antecedentRequest.setErroresRefraccion(getReduceIllnessList(refractiveErrorList));
        antecedentRequest.setEnfermedadesOculares(getReduceIllnessList(eyesIllnessList));
        try {
            AntecedentesApi.addAntecedent(antecedentResponse -> {
                if (S_CERO.equals(antecedentResponse.getCodigoRespuesta())) {
                    customDialog.cancel();
                    startActivity(new Intent(this, ListadoPacienteActivity.class));
                } else {
                    customDialog.cancel();
                    makeText(RegistroAntecedentesActivity.this, "Error al registrar antecedentes", LENGTH_LONG).show();
                }
            }, token, antecedentRequest);
        } catch (JsonProcessingException | JSONException e) {
            customDialog.cancel();
            makeText(RegistroAntecedentesActivity.this, valueOf(e), LENGTH_LONG).show();
        }
    }

}