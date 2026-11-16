package com.sebastian.crealab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// Ventana 3: muestra los datos del formulario
public class ConfirmacionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        // Recibir los datos enviados desde el formulario
        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String correo = intent.getStringExtra("correo");
        int edad = intent.getIntExtra("edad", 0);
        String taller = intent.getStringExtra("taller");
        boolean novedades = intent.getBooleanExtra("novedades", false);

        TextView tvDatos = findViewById(R.id.tvDatos);
        tvDatos.setText(
                "Nombre: " + nombre + "\n" +
                        "Correo: " + correo + "\n" +
                        "Edad: " + edad + " años\n" +
                        "Taller: " + taller + "\n" +
                        "Recibir novedades: " + (novedades ? "Sí" : "No") + "\n" +
                        "Términos: Aceptados"
        );

        findViewById(R.id.btnInicio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volver = new Intent(ConfirmacionActivity.this, MainActivity.class);
                volver.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(volver);
                finish();
            }
        });

        findViewById(R.id.btnSalir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}