package com.example.messmapper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

public class mess_registration extends AppCompatActivity {

    Button mess1, mess2, mess3;
    DatabaseReference dbRef, regRef;
    String loggedInUsername = ""; // replace this with the username fetched during login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String username = getIntent().getStringExtra("username");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess_registration);

        mess1 = findViewById(R.id.btnMess1);
        mess2 = findViewById(R.id.btnMess2);
        mess3 = findViewById(R.id.btnMess3);

        // TODO: Set this from actual logged-in user
        loggedInUsername = getIntent().getStringExtra("username");

        dbRef = FirebaseDatabase.getInstance().getReference("Menu");
        regRef = FirebaseDatabase.getInstance().getReference("registrations");

        checkIfAlreadyRegistered();

        mess1.setOnClickListener(view -> registerForMess("Mess1"));
        mess2.setOnClickListener(view -> registerForMess("Mess2"));
        mess3.setOnClickListener(view -> registerForMess("Mess3"));
    }

    private void checkIfAlreadyRegistered() {
        regRef.child(loggedInUsername).child("mess").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String mess = snapshot.getValue(String.class);
                    Toast.makeText(mess_registration.this, "Already registered for " + mess, Toast.LENGTH_SHORT).show();
                    mess1.setEnabled(false);
                    mess2.setEnabled(false);
                    mess3.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mess_registration.this, "Firebase error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void registerForMess(String messName) {
        DatabaseReference messRef = dbRef.child(messName);

        messRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                long count = snapshot.getChildrenCount();

                if (count < 5) {
                    // Save registration for this user
                    regRef.child(loggedInUsername).child("mess").setValue(messName);

                    // Also add user to mess list (optional for admin to view)
                    String id = messRef.push().getKey();
                    messRef.child(id).setValue(loggedInUsername);

                    Toast.makeText(mess_registration.this, "Registered to " + messName, Toast.LENGTH_SHORT).show();
                    mess1.setEnabled(false);
                    mess2.setEnabled(false);
                    mess3.setEnabled(false);
                } else {
                    Toast.makeText(mess_registration.this, messName + " is full! Please try another.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mess_registration.this, "Firebase Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
