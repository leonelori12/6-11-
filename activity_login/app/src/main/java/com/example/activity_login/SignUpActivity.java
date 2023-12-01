package com.example.activity_login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences.Editor;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class SignUpActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEmail, etPass, etRPass;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etNombre = findViewById(R.id.nombre);
        etApellido = findViewById(R.id.apellido);
        etEmail = findViewById(R.id.email);
        etPass = findViewById(R.id.passs);
        etRPass = findViewById(R.id.rpasss);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarDatos();
            }
        });
    }

    private void guardarDatos() {
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String rpass = etRPass.getText().toString();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || pass.isEmpty() || rpass.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!pass.equals(rpass)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar los datos en SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Nombre", nombre);
        editor.putString("Apellido", apellido);
        editor.putString("Email", email);
        editor.putString("Contraseña", pass);
        editor.apply();

        Toast.makeText(this, "Datos guardados exitosamente", Toast.LENGTH_SHORT).show();
    }
}