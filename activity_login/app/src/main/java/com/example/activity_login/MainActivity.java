package com.example.activity_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity_login.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button btnLogin, btnSignUp;
    private TextView tvAttempts;
    private int attemptsLeft = 3; // Asegúrate de que esté inicializado correctamente

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp); // Inicializa el botón de SignUp
        tvAttempts = findViewById(R.id.tvAttempts);
        updateAttemptsText();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidCredentials()) {
                    // Credenciales válidas, ir a SecondActivity
                    String username = editTextEmail.getText().toString();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("USERNAME", username);
                    startActivity(intent);
                } else {
                    // Credenciales incorrectas, actualizar el contador de intentos
                    attemptsLeft--;
                    updateAttemptsText();

                    if (attemptsLeft == 0) {
                        // Bloquear la aplicación o realizar alguna acción adicional si se agotan los intentos
                        Toast.makeText(MainActivity.this, "Se han agotado los intentos. Aplicación bloqueada.", Toast.LENGTH_SHORT).show();
                        btnLogin.setEnabled(false); // Deshabilitar el botón después de agotar los intentos
                    } else {
                        Toast.makeText(MainActivity.this, "Credenciales incorrectas. Intentos restantes: " + attemptsLeft, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Inicia la actividad SignUpActivity cuando se hace clic en el botón de SignUp
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

private boolean isValidCredentials() {
    // Obtiene el email y la contraseña ingresados
    String enteredEmail = editTextEmail.getText().toString();
    String enteredPassword = editTextPassword.getText().toString();

    // Recupera los valores almacenados durante el registro (sign up)
    SharedPreferences sharedPreferences = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
    String storedEmail = sharedPreferences.getString("Email", "");
    String storedPassword = sharedPreferences.getString("Contraseña", "");

    // Compara el email y la contraseña ingresados con los valores almacenados
    return enteredEmail.equals(storedEmail) && enteredPassword.equals(storedPassword);
}



    private void updateAttemptsText() {
        tvAttempts.setText("Intentos restantes: " + attemptsLeft);
    }

}