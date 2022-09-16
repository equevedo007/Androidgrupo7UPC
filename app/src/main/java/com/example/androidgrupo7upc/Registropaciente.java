package com.example.androidgrupo7upc;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;
import static java.lang.String.valueOf;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgrupo7upc.model.DepartmentType;
import com.example.androidgrupo7upc.model.DistrictType;
import com.example.androidgrupo7upc.model.MasterType;
import com.example.androidgrupo7upc.model.PatientRequest;
import com.example.androidgrupo7upc.model.ProvinceType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.PacienteApi;
import com.example.androidgrupo7upc.network.impl.UbigeoApi;
import com.example.androidgrupo7upc.util.DataMapper;
import com.example.androidgrupo7upc.util.Util;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Registropaciente extends AppCompatActivity {

    private EditText txtNumeroDocumento, txtNombres, txtApellidoPaterno, txtApellidoMaterno;
    private EditText txtFechaNacimiento, txtOcupacion, txtCelular, txtTelefono, txtCorreo;
    private EditText txtDireecion, txtReferencia;

    private Spinner spnTipoDocumento, spnSexo, spnEstadoCivil, spnGrado;
    private Spinner spnDepartamento, spnProvincia, spnDistrito;

    private String tipoDocumento = "0";
    private String sexo = "0";
    private String estadoCivil = "0";
    private String grado = "0";
    private String idDepartamento = "0";
    private String idProvincia = "0";
    private String idDistrito = "0";
    private String idUbigeo = "0";
    //Edinson Quevedo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registropaciente);

        RESTManager.getInstance(this);

        spnTipoDocumento = findViewById(R.id.txttipodocumento);
        obtenerTipoDocumento();
        spnTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MasterType masterType = (MasterType) parent.getSelectedItem();
                tipoDocumento = masterType.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtNumeroDocumento = findViewById(R.id.txtNumeroDocumento);
        txtNombres = findViewById(R.id.txtNombres);
        txtApellidoPaterno = findViewById(R.id.txtApellidoPaterno);
        txtApellidoMaterno = findViewById(R.id.txtApellidoMaterno);

        txtFechaNacimiento = findViewById(R.id.txtFechaNacimiento);
        txtFechaNacimiento.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Registropaciente.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String nMonth = monthOfYear + 1 < 10 ? "0" + (monthOfYear + 1) : valueOf(monthOfYear + 1);
                        txtFechaNacimiento.setText(dayOfMonth + "/" + nMonth + "/" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        spnSexo = findViewById(R.id.txtSexo);
        obtenerSexo();
        spnSexo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MasterType masterType = (MasterType) parent.getSelectedItem();
                sexo = masterType.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnEstadoCivil = findViewById(R.id.txtEstadoCivil);
        obtenerEstadoCivil();
        spnEstadoCivil.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MasterType masterType = (MasterType) parent.getSelectedItem();
                estadoCivil = masterType.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnGrado = findViewById(R.id.txtGradoInstruccion);
        obtenerGrado();
        spnGrado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MasterType masterType = (MasterType) parent.getSelectedItem();
                grado = masterType.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtOcupacion = findViewById(R.id.txtOcupacion);
        txtCelular = findViewById(R.id.txtCelular);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreo);

        spnDepartamento = findViewById(R.id.ListaDepartamento);
        obtenerDepartamentos();
        spnDepartamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DepartmentType departmentType = (DepartmentType) parent.getSelectedItem();
                idDepartamento = departmentType.getIdDepartamento();
                obtenerProvincias(departmentType.getIdDepartamento());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnProvincia = findViewById(R.id.ListaProvincia);
        obtenerProvincias(null);
        spnProvincia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProvinceType provinceType = (ProvinceType) parent.getSelectedItem();
                idProvincia = provinceType.getIdProvincia();
                obtenerDistritos(provinceType.getIdProvincia());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spnDistrito = findViewById(R.id.ListaDistrito);
        obtenerDistritos(null);
        spnDistrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DistrictType districtType = (DistrictType) parent.getSelectedItem();
                idDistrito = districtType.getIdDistrito();
                idUbigeo = districtType.getIdUbigeo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtDireecion = findViewById(R.id.txtDireecion);
        txtReferencia = findViewById(R.id.txtReferencia);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(view -> registrarPaciente());
    }

    private void obtenerTipoDocumento() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Util.getMasterList(DataMapper.tipoDocumentoMap));
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTipoDocumento.setAdapter(adapterTipoDocumento);
    }

    private void obtenerSexo() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Util.getMasterList(DataMapper.tipoGeneroMap));
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnSexo.setAdapter(adapterTipoDocumento);
    }

    private void obtenerEstadoCivil() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Util.getMasterList(DataMapper.tipoEstadoCivilMap));
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnEstadoCivil.setAdapter(adapterTipoDocumento);
    }

    private void obtenerGrado() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Util.getMasterList(DataMapper.tipoGradoMap));
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnGrado.setAdapter(adapterTipoDocumento);
    }

    private void obtenerDepartamentos() {
        UbigeoApi.getDepartments(ubigeoResponse -> {
            List<DepartmentType> departmentTypeList = new ArrayList<>();
            if (!ubigeoResponse.getDepartmentTypeList().isEmpty()) {
                departmentTypeList.add(new DepartmentType("0", "Selecciona un departamento"));
                departmentTypeList.addAll(ubigeoResponse.getDepartmentTypeList());

                ArrayAdapter<DepartmentType> adapterDepartamento = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, departmentTypeList);
                adapterDepartamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spnDepartamento.setAdapter(adapterDepartamento);
            } else {
                makeText(Registropaciente.this, "No se cargaron los departamentos", LENGTH_LONG).show();
            }
        });
    }

    private void obtenerProvincias(String idDepartamento) {
        List<ProvinceType> provinceTypeList = new ArrayList<>();
        provinceTypeList.add(new ProvinceType("0", "Selecciona una provincia"));

        if (StringUtils.isEmpty(idDepartamento)) {
            ArrayAdapter<ProvinceType> adapterProvincia = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, provinceTypeList);
            adapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spnProvincia.setAdapter(adapterProvincia);
        } else {
            UbigeoApi.getProvinces(ubigeoResponse -> {
                if (!ubigeoResponse.getProvinceTypeList().isEmpty()) {
                    provinceTypeList.addAll(ubigeoResponse.getProvinceTypeList());
                    ArrayAdapter<ProvinceType> adapterProvincia = new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_item, provinceTypeList);
                    adapterProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spnProvincia.setAdapter(adapterProvincia);
                } else {
                    makeText(Registropaciente.this, "No se cargaron las provincias", LENGTH_LONG).show();
                }
            }, idDepartamento);
        }
    }

    private void obtenerDistritos(String idProvincia) {
        List<DistrictType> districtTypeList = new ArrayList<>();
        districtTypeList.add(new DistrictType("0", "0", "Selecciona un distrito"));

        if (StringUtils.isEmpty(idProvincia)) {
            ArrayAdapter<DistrictType> adapterDistrito = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_item, districtTypeList);
            adapterDistrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnDistrito.setAdapter(adapterDistrito);
        } else {
            UbigeoApi.getDistricts(ubigeoResponse -> {
                if (!ubigeoResponse.getDistrictTypes().isEmpty()) {
                    districtTypeList.addAll(ubigeoResponse.getDistrictTypes());
                    ArrayAdapter<DistrictType> adapterDistrito = new ArrayAdapter<>(this,
                            android.R.layout.simple_spinner_item, districtTypeList);
                    adapterDistrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spnDistrito.setAdapter(adapterDistrito);
                } else {
                    makeText(Registropaciente.this, "No se cargaron los distritos", LENGTH_LONG).show();
                }
            }, idProvincia);
        }
    }

    private void registrarPaciente() {
        boolean error = false;

        String numeroDocumento = txtNumeroDocumento.getText().toString();
        String nombres = txtNombres.getText().toString();
        String apellidoPaterno = txtApellidoPaterno.getText().toString();
        String apellidoMaterno = txtApellidoMaterno.getText().toString();
        String fechaNacimiento = txtFechaNacimiento.getText().toString();
        String ocupacion = txtOcupacion.getText().toString();
        String celular = txtCelular.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String correo = txtCorreo.getText().toString();
        String direecion = txtDireecion.getText().toString();
        String referencia = txtReferencia.getText().toString();

        if (tipoDocumento.isEmpty() || S_CERO.equals(tipoDocumento)) {
            setSpinnerError(spnTipoDocumento);
            error = true;
        }
        if (numeroDocumento.isEmpty()) {
            txtNumeroDocumento.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (nombres.isEmpty()) {
            txtNombres.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (apellidoPaterno.isEmpty()) {
            txtApellidoPaterno.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (apellidoMaterno.isEmpty()) {
            txtApellidoMaterno.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (fechaNacimiento.isEmpty()) {
            txtFechaNacimiento.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (sexo.isEmpty() || S_CERO.equals(sexo)) {
            setSpinnerError(spnSexo);
            error = true;
        }
        if (estadoCivil.isEmpty() || S_CERO.equals(estadoCivil)) {
            setSpinnerError(spnEstadoCivil);
            error = true;
        }
        if (grado.isEmpty() || S_CERO.equals(grado)) {
            setSpinnerError(spnGrado);
            error = true;
        }
        if (ocupacion.isEmpty()) {
            txtOcupacion.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (celular.isEmpty()) {
            txtCelular.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (telefono.isEmpty()) {
            txtTelefono.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (correo.isEmpty()) {
            txtCorreo.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (idDepartamento.isEmpty() || S_CERO.equals(idDepartamento)) {
            setSpinnerError(spnDepartamento);
            error = true;
        }
        if (idProvincia.isEmpty() || S_CERO.equals(idProvincia)) {
            setSpinnerError(spnProvincia);
            error = true;
        }
        if (idDistrito.isEmpty() || S_CERO.equals(idDistrito)) {
            setSpinnerError(spnDistrito);
            error = true;
        }
        if (direecion.isEmpty()) {
            txtDireecion.setError(getString(R.string.campo_requerido));
            error = true;
        }
        if (referencia.isEmpty()) {
            txtReferencia.setError(getString(R.string.campo_requerido));
            error = true;
        }

        if (error) {
            makeText(getBaseContext(), "Favor de registrar los campos requeridos.", LENGTH_SHORT).show();
        } else {
            PatientRequest patientRequest = new PatientRequest();
            patientRequest.setTipoDocumento(tipoDocumento);
            patientRequest.setNumeroDocumento(numeroDocumento);
            patientRequest.setNombres(nombres);
            patientRequest.setApePaterno(apellidoPaterno);
            patientRequest.setApeMaterno(apellidoMaterno);
            patientRequest.setFechaNacimiento(fechaNacimiento);
            patientRequest.setSexo(sexo);
            patientRequest.setEstadoCivil(estadoCivil);
            patientRequest.setGradoInstruccion(grado);
            patientRequest.setOcupacion(ocupacion);
            patientRequest.setCelular(celular);
            patientRequest.setTelefono(telefono);
            patientRequest.setEmail(correo);
            patientRequest.setIdUbigeo(idUbigeo);
            patientRequest.setDireccion(direecion);
            patientRequest.setReferencia(referencia);

            try {
                String token = (String) getSharedPreference(String.class, Registropaciente.this, TOKEN);

                PacienteApi.addPatient(patientResponse -> {
                    if (S_CERO.equals(patientResponse.getCodigoRespuesta())) {
                        makeText(Registropaciente.this, "Paciente registrado correctamente", LENGTH_LONG).show();
                        startActivity(new Intent(this, menu_Activity.class));
                    } else {
                        makeText(Registropaciente.this, patientResponse.getMensajeRespuesta(), LENGTH_LONG).show();
                    }
                }, token, patientRequest);
            } catch (JsonProcessingException | JSONException e) {
                makeText(Registropaciente.this, valueOf(e), LENGTH_LONG).show();
            }
        }
    }

    private void setSpinnerError(Spinner spinnerError) {
        TextView errorTextview = (TextView) spinnerError.getSelectedView();
        errorTextview.setError(getString(R.string.campo_requerido));
    }
}