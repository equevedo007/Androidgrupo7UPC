package com.example.androidgrupo7upc;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Registropaciente extends AppCompatActivity {

    private EditText dateEdt;

    private Spinner spnTipoDocumento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registropaciente);

        dateEdt = findViewById(R.id.txtFechaNacimiento);
        spnTipoDocumento = findViewById(R.id.txttipodocumento);

        dateEdt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();

            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Registropaciente.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1);
                    },
                    year, month, day);
            datePickerDialog.show();
        });

        obtenerTipoDocumento();

        Spinner spinnerSeleccionarSexo = findViewById(R.id.txtSexo);
        ArrayAdapter<CharSequence> adapterSeleccionarSexo = ArrayAdapter.createFromResource(this,
                R.array.SeleccionarSexo, android.R.layout.simple_spinner_item);
        adapterSeleccionarSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeleccionarSexo.setAdapter(adapterSeleccionarSexo);


        Spinner spinnertxtEstadoCivil = findViewById(R.id.txtEstadoCivil);
        ArrayAdapter<CharSequence> adaptertxtEstadoCivil = ArrayAdapter.createFromResource(this,
                R.array.SeleccionarEstadoCivil, android.R.layout.simple_spinner_item);
        adaptertxtEstadoCivil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertxtEstadoCivil.setAdapter(adaptertxtEstadoCivil);


        Spinner spinnertxtGradoInstruccion = findViewById(R.id.txtGradoInstruccion);
        ArrayAdapter<CharSequence> adaptertxtGradoInstruccion = ArrayAdapter.createFromResource(this,
                R.array.SeleccionarGradoInstrucción, android.R.layout.simple_spinner_item);
        adaptertxtGradoInstruccion.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnertxtGradoInstruccion.setAdapter(adaptertxtGradoInstruccion);


        Spinner spinnerListaDepartamento = findViewById(R.id.ListaDepartamento);
        ArrayAdapter<CharSequence> adapterListaDepartamento = ArrayAdapter.createFromResource(this,
                R.array.ListaDepartamentos, android.R.layout.simple_spinner_item);
        adapterListaDepartamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListaDepartamento.setAdapter(adapterListaDepartamento);


        Spinner spinnerListaProvincia = findViewById(R.id.ListaProvincia);
        ArrayAdapter<CharSequence> adapterListaProvincia = ArrayAdapter.createFromResource(this,
                R.array.ListaProvincias, android.R.layout.simple_spinner_item);
        adapterListaProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListaProvincia.setAdapter(adapterListaProvincia);


        Spinner spinnerListaDistrito = findViewById(R.id.ListaDistrito);
        ArrayAdapter<CharSequence> adapterListaDistrito = ArrayAdapter.createFromResource(this,
                R.array.ListaDistrito, android.R.layout.simple_spinner_item);
        adapterListaProvincia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListaDistrito.setAdapter(adapterListaDistrito);
    }

    private void obtenerTipoDocumento(){
        ArrayAdapter<CharSequence> adapterTipoDocumento = ArrayAdapter.createFromResource(this,
                R.array.tipodocumento, android.R.layout.simple_spinner_item);
        adapterTipoDocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTipoDocumento.setAdapter(adapterTipoDocumento);
    }


}