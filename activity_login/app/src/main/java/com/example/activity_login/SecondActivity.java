package com.example.activity_login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activity_login.R;

import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_second);

        String username = getIntent().getStringExtra("USERNAME");

        TextView textViewMessage = findViewById(R.id.textViewMessage);
        textViewMessage.setText("Usuario " + username + " INGRESO");
    }
}
