package com.example.androidgrupo7upc.ui.activity;

import static android.text.InputType.TYPE_NULL;
import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static com.example.androidgrupo7upc.util.Constants.S_CERO;
import static com.example.androidgrupo7upc.util.Constants.TEXTO_VACIO;
import static com.example.androidgrupo7upc.util.Constants.TOKEN;
import static com.example.androidgrupo7upc.util.Util.getSharedPreference;
import static com.example.androidgrupo7upc.util.ui.UiUtil.findViewsWithType;
import static java.lang.String.valueOf;
import static java.util.Objects.requireNonNull;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgrupo7upc.R;
import com.example.androidgrupo7upc.model.DepartmentType;
import com.example.androidgrupo7upc.model.DistrictType;
import com.example.androidgrupo7upc.model.MasterType;
import com.example.androidgrupo7upc.model.PatientRequest;
import com.example.androidgrupo7upc.model.ProvinceType;
import com.example.androidgrupo7upc.network.RESTManager;
import com.example.androidgrupo7upc.network.impl.PacienteApi;
import com.example.androidgrupo7upc.network.impl.UbigeoApi;
import com.example.androidgrupo7upc.ui.dialog.CustomDatePickerDialog;
import com.example.androidgrupo7upc.util.Util;
import com.example.androidgrupo7upc.util.data.DataMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegistroPacienteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText txtNumeroDocumento, txtNombres, txtApellidoPaterno, txtApellidoMaterno;
    private EditText txtFechaNacimiento, txtOcupacion, txtCelular, txtTelefono, txtCorreo;
    private EditText txtDireecion, txtReferencia;

    private AutoCompleteTextView spnTipoDocumento, spnSexo, spnEstadoCivil, spnGrado;
    private AutoCompleteTextView spnDepartamento, spnProvincia, spnDistrito;

    private String tipoDocumento = TEXTO_VACIO;
    private String sexo = TEXTO_VACIO;
    private String estadoCivil = TEXTO_VACIO;
    private String grado = TEXTO_VACIO;
    private String idDepartamento = TEXTO_VACIO;
    private String idProvincia = TEXTO_VACIO;
    private String idUbigeo = TEXTO_VACIO;

    List<DepartmentType> departmentTypeList = new ArrayList<>();
    List<ProvinceType> provinceTypeList = new ArrayList<>();
    List<DistrictType> districtTypeList = new ArrayList<>();

    //Edinson Quevedo
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_paciente);

        RESTManager.getInstance(this);

        spnTipoDocumento = findViewById(R.id.spnDocumentType);
        spnTipoDocumento.setInputType(TYPE_NULL);
        obtenerTipoDocumento();
        spnTipoDocumento.setOnItemClickListener((parent, view, position, id) -> {
            MasterType masterType = Util.getMasterList(DataMapper.tipoDocumentoMap).get(position);
            tipoDocumento = masterType.getId();
        });

        txtNumeroDocumento = findViewById(R.id.txtDocumentNumber);
        txtNombres = findViewById(R.id.txtName);
        txtApellidoPaterno = findViewById(R.id.txtLastName);
        txtApellidoMaterno = findViewById(R.id.txtSecondLastName);

        txtFechaNacimiento = findViewById(R.id.txtBirthday);
        txtFechaNacimiento.setInputType(TYPE_NULL);
        txtFechaNacimiento.setOnClickListener(v -> openDatePickerDialog());
        txtFechaNacimiento.setOnFocusChangeListener((view, b) -> {
            if (b){
                openDatePickerDialog();
            }
        });

        spnSexo = findViewById(R.id.spnGenre);
        spnSexo.setInputType(TYPE_NULL);
        obtenerSexo();
        spnSexo.setOnItemClickListener((parent, view, position, id) -> {
            MasterType masterType = Util.getMasterList(DataMapper.tipoGeneroMap).get(position);
            sexo = masterType.getId();
        });

        spnEstadoCivil = findViewById(R.id.spnMaritalStatus);
        spnEstadoCivil.setInputType(TYPE_NULL);
        obtenerEstadoCivil();
        spnEstadoCivil.setOnItemClickListener((parent, view, position, id) -> {
            MasterType masterType = Util.getMasterList(DataMapper.tipoEstadoCivilMap).get(position);
            estadoCivil = masterType.getId();
        });

        spnGrado = findViewById(R.id.spnDegreeInstruction);
        spnGrado.setInputType(TYPE_NULL);
        obtenerGrado();
        spnGrado.setOnItemClickListener((parent, view, position, id) -> {
            MasterType masterType = Util.getMasterList(DataMapper.tipoGradoMap).get(position);
            grado = masterType.getId();
        });

        txtOcupacion = findViewById(R.id.txtOccupation);
        txtCelular = findViewById(R.id.txtCellphone);
        txtTelefono = findViewById(R.id.txtPhone);
        txtCorreo = findViewById(R.id.txtEmail);

        spnDepartamento = findViewById(R.id.spnDepartment);
        spnDepartamento.setInputType(TYPE_NULL);
        obtenerDepartamentos();
        spnDepartamento.setOnItemClickListener((parent, view, position, id) -> {
            DepartmentType departmentType = departmentTypeList.get(position);
            idDepartamento = departmentType.getIdDepartamento();
            obtenerProvincias(idDepartamento);
        });

        spnProvincia = findViewById(R.id.spnProvince);
        spnProvincia.setInputType(TYPE_NULL);
        spnProvincia.setOnItemClickListener((parent, view, position, id) -> {
            ProvinceType provinceType = provinceTypeList.get(position);
            idProvincia = provinceType.getIdProvincia();
            obtenerDistritos(idProvincia);
        });

        spnDistrito = findViewById(R.id.spnDistrict);
        spnDistrito.setOnItemClickListener((parent, view, position, id) -> {
            DistrictType districtType = districtTypeList.get(position);
            idUbigeo = districtType.getIdUbigeo();
        });

        txtDireecion = findViewById(R.id.txtAddress);
        txtReferencia = findViewById(R.id.txtReference);

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(view -> registrarPaciente());
    }

    private void obtenerTipoDocumento() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                R.layout.drop_down_item, Util.getMasterList(DataMapper.tipoDocumentoMap));
        spnTipoDocumento.setAdapter(adapterTipoDocumento);
    }

    private void obtenerSexo() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                R.layout.drop_down_item, Util.getMasterList(DataMapper.tipoGeneroMap));
        spnSexo.setAdapter(adapterTipoDocumento);
    }

    private void obtenerEstadoCivil() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                R.layout.drop_down_item, Util.getMasterList(DataMapper.tipoEstadoCivilMap));
        spnEstadoCivil.setAdapter(adapterTipoDocumento);
    }

    private void obtenerGrado() {
        ArrayAdapter<MasterType> adapterTipoDocumento = new ArrayAdapter<>(this,
                R.layout.drop_down_item, Util.getMasterList(DataMapper.tipoGradoMap));
        spnGrado.setAdapter(adapterTipoDocumento);
    }

    private void obtenerDepartamentos() {
        UbigeoApi.getDepartments(ubigeoResponse -> {
            if (!ubigeoResponse.getDepartmentTypeList().isEmpty()) {
                departmentTypeList.addAll(ubigeoResponse.getDepartmentTypeList());

                ArrayAdapter<DepartmentType> adapterDepartamento = new ArrayAdapter<>(this,
                        R.layout.drop_down_item, departmentTypeList);
                spnDepartamento.setAdapter(adapterDepartamento);
            } else {
                makeText(RegistroPacienteActivity.this, "No se cargaron los departamentos", LENGTH_LONG).show();
            }
        });
    }

    private void obtenerProvincias(String idDepartamento) {
        provinceTypeList.clear();
        UbigeoApi.getProvinces(ubigeoResponse -> {
            if (!ubigeoResponse.getProvinceTypeList().isEmpty()) {
                provinceTypeList.addAll(ubigeoResponse.getProvinceTypeList());
                ArrayAdapter<ProvinceType> adapterProvincia = new ArrayAdapter<>(this,
                        R.layout.drop_down_item, provinceTypeList);
                spnProvincia.setAdapter(adapterProvincia);
            } else {
                makeText(RegistroPacienteActivity.this, "No se cargaron las provincias", LENGTH_LONG).show();
            }
        }, idDepartamento);
    }

    private void obtenerDistritos(String idProvincia) {
        districtTypeList.clear();
        UbigeoApi.getDistricts(ubigeoResponse -> {
            if (!ubigeoResponse.getDistrictTypes().isEmpty()) {
                districtTypeList.addAll(ubigeoResponse.getDistrictTypes());
                ArrayAdapter<DistrictType> adapterDistrito = new ArrayAdapter<>(this,
                        R.layout.drop_down_item, districtTypeList);
                spnDistrito.setAdapter(adapterDistrito);
            } else {
                makeText(RegistroPacienteActivity.this, "No se cargaron los distritos", LENGTH_LONG).show();
            }
        }, idProvincia);
    }

    private void registrarPaciente() {
        boolean error = false;

        ViewGroup root = findViewById(R.id.lytRegister);
        List<TextInputLayout> textInputLayouts = findViewsWithType(root, TextInputLayout.class);

        for (TextInputLayout textInputLayout : textInputLayouts) {
            String value = requireNonNull(textInputLayout.getEditText()).getText().toString();
            if (StringUtils.isEmpty(value)) {
                textInputLayout.setError(getString(R.string.required_field));
                error = true;
            } else {
                textInputLayout.setError(null);
            }
        }
        if (error) {
            makeText(getBaseContext(), "Favor de registrar los campos requeridos.", LENGTH_SHORT).show();
        } else {
            String numeroDocumento = txtNumeroDocumento.getText().toString();
            String nombres = txtNombres.getText().toString();
            String apellidoPaterno = txtApellidoPaterno.getText().toString();
            String apellidoMaterno = txtApellidoMaterno.getText().toString();
            String fechaNacimiento = txtFechaNacimiento.getText().toString();
            String ocupacion = txtOcupacion.getText().toString();
            String celular = txtCelular.getText().toString();
            String telefono = txtTelefono.getText().toString();
            String correo = txtCorreo.getText().toString();
            String direccion = txtDireecion.getText().toString();
            String referencia = txtReferencia.getText().toString();

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
            patientRequest.setDireccion(direccion);
            patientRequest.setReferencia(referencia);

            addPatient(patientRequest);
        }
    }

    private void addPatient(PatientRequest patientRequest) {
        try {
            String token = (String) getSharedPreference(String.class, RegistroPacienteActivity.this, TOKEN);

            PacienteApi.addPatient(patientResponse -> {
                if (S_CERO.equals(patientResponse.getCodigoRespuesta())) {
                    makeText(RegistroPacienteActivity.this, "Paciente registrado correctamente", LENGTH_LONG).show();
                    startActivity(new Intent(this, ListadoPacienteActivity.class));
                } else {
                    makeText(RegistroPacienteActivity.this, patientResponse.getMensajeRespuesta(), LENGTH_LONG).show();
                }
            }, token, patientRequest);
        } catch (JsonProcessingException | JSONException e) {
            makeText(RegistroPacienteActivity.this, valueOf(e), LENGTH_LONG).show();
        }
    }

    private void openDatePickerDialog() {
        CustomDatePickerDialog datePickerDialog = new CustomDatePickerDialog();
        datePickerDialog.show(getSupportFragmentManager(), "DATE_PICKER");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        txtFechaNacimiento.setText(Util.buildDate(dayOfMonth, month, year));
    }
}