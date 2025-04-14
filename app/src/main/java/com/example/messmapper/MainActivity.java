package com.example.messmapper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRegisterMess, btnViewMenu;
    String username; // To store the username passed from login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the username from the Intent
        username = getIntent().getStringExtra("username");

        btnRegisterMess = findViewById(R.id.btnRegisterMess);
        btnViewMenu = findViewById(R.id.btnViewMenu);

        btnRegisterMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to MessRegistrationActivity with username
                Intent intent = new Intent(MainActivity.this, mess_registration.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        btnViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to ViewMenuActivity with username
                Intent intent = new Intent(MainActivity.this, ViewMenuActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }
}
