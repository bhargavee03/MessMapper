package com.example.messmapper;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RemoveRegistrations extends AppCompatActivity {

    Button btnClearAll;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_registrations);

        btnClearAll = findViewById(R.id.btnClearAll);
        dbRef = FirebaseDatabase.getInstance().getReference();

        btnClearAll.setOnClickListener(view -> {
            // 1. Clear mess1, mess2, mess3
            dbRef.child("Menu").child("Mess1").removeValue();
            dbRef.child("Menu").child("Mess2").removeValue();
            dbRef.child("Menu").child("Mess3").removeValue();

            // 2. Clear all user registrations
            dbRef.child("registrations").removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(RemoveRegistrations.this, "All mess registrations cleared. Users can now register again.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RemoveRegistrations.this, "Error clearing registrations.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
