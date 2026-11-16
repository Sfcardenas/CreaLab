package com.sebastian.crealab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

// Ventana 2: Formulario (los datos NO se guardan, se envían a la siguiente ventana)
public class FormularioActivity extends AppCompatActivity {

    private EditText etNombre, etCorreo, etEdad;
    private RadioGroup rgTaller;
    private CheckBox cbNovedades, cbTerminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etEdad = findViewById(R.id.etEdad);
        rgTaller = findViewById(R.id.rgTaller);
        cbNovedades = findViewById(R.id.cbNovedades);
        cbTerminos = findViewById(R.id.cbTerminos);

        findViewById(R.id.btnEnviar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarInscripcion();
            }
        });

        findViewById(R.id.btnSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    private void enviarInscripcion() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String edad = etEdad.getText().toString().trim();

        if (nombre.isEmpty() || correo.isEmpty() || edad.isEmpty()) {
            Toast.makeText(this, "Completa nombre, correo y edad", Toast.LENGTH_SHORT).show();
            return;
        }

        int idSeleccionado = rgTaller.getCheckedRadioButtonId();
        if (idSeleccionado == -1) {
            Toast.makeText(this, "Selecciona un taller", Toast.LENGTH_SHORT).show();
            return;
        }
        String taller = ((RadioButton) findViewById(idSeleccionado)).getText().toString();

        if (!cbTerminos.isChecked()) {
            Toast.makeText(this, "Debes aceptar los términos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Enviar los datos a la ventana de confirmación
        Intent intent = new Intent(FormularioActivity.this, ConfirmacionActivity.class);
        intent.putExtra("nombre", nombre);
        intent.putExtra("correo", correo);
        intent.putExtra("edad", Integer.parseInt(edad));
        intent.putExtra("taller", taller);
        intent.putExtra("novedades", cbNovedades.isChecked());
        startActivity(intent);
    }
}