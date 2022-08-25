package com.example.androidgrupo7upc;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class Registropaciente extends AppCompatActivity {

    private EditText dateEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registropaciente);

        // on below line we are initializing our variables.
        dateEdt = findViewById(R.id.txtFechaNacimiento);

        // on below line we are adding click listener
        // for our pick date button
        dateEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        Registropaciente.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                dateEdt.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        Spinner spinnertipodocumento = (Spinner) findViewById(R.id.txttipodocumento);
        ArrayAdapter<CharSequence> adaptertipodocumento = ArrayAdapter.createFromResource(this,
                R.array.tipodocumento, android.R.layout.simple_spinner_item);
        adaptertipodocumento.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinnertipodocumento.setAdapter(adaptertipodocumento);


        Spinner spinnerSeleccionarSexo = (Spinner) findViewById(R.id.txtSexo);
        ArrayAdapter<CharSequence> adapterSeleccionarSexo = ArrayAdapter.createFromResource(this,
                R.array.SeleccionarSexo, android.R.layout.simple_spinner_item);
        adapterSeleccionarSexo.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinnerSeleccionarSexo.setAdapter(adapterSeleccionarSexo);


        Spinner spinnertxtEstadoCivil = (Spinner) findViewById(R.id.txtEstadoCivil);
        ArrayAdapter<CharSequence> adaptertxtEstadoCivil = ArrayAdapter.createFromResource(this,
                R.array.SeleccionarEstadoCivil, android.R.layout.simple_spinner_item);
        adaptertxtEstadoCivil.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinnertxtEstadoCivil.setAdapter(adaptertxtEstadoCivil);


        Spinner spinnertxtGradoInstruccion = (Spinner) findViewById(R.id.txtGradoInstruccion);
        ArrayAdapter<CharSequence> adaptertxtGradoInstruccion = ArrayAdapter.createFromResource(this,
                R.array.SeleccionarGradoInstrucción, android.R.layout.simple_spinner_item);
        adaptertxtGradoInstruccion.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinnertxtGradoInstruccion.setAdapter(adaptertxtGradoInstruccion);











        Spinner spinnerListaDepartamento = (Spinner) findViewById(R.id.ListaDepartamento);
        ArrayAdapter<CharSequence> adapterListaDepartamento = ArrayAdapter.createFromResource(this,
                R.array.ListaDepartamentos, android.R.layout.simple_spinner_item);
        adapterListaDepartamento.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spinnerListaDepartamento.setAdapter(adapterListaDepartamento);
    }


}