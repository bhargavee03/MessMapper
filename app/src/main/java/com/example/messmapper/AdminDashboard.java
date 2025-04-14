package com.example.messmapper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboard extends AppCompatActivity {

    Button btnCheckRegistrations, btnRemoveRegistrations, btnPutMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnCheckRegistrations = findViewById(R.id.btnCheckRegistrations);
        btnRemoveRegistrations = findViewById(R.id.btnRemoveRegistrations);
        btnPutMenu = findViewById(R.id.btnPutMenu);

        btnCheckRegistrations.setOnClickListener(v -> {
            // TODO: Start CheckRegistrationsActivity
            startActivity(new Intent(AdminDashboard.this, CheckRegistrations.class));
        });

        btnRemoveRegistrations.setOnClickListener(v -> {
            // TODO: Start RemoveRegistrationsActivity
            startActivity(new Intent(AdminDashboard.this, RemoveRegistrations.class));
        });

        btnPutMenu.setOnClickListener(v -> {
            // TODO: Start PutMenuActivity
            startActivity(new Intent(AdminDashboard.this, PutMenuActivity.class));
        });
    }
}
