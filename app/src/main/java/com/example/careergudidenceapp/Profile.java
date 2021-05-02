package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView name, phone, email;
    Button editprof, editpass;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        editprof = (Button) findViewById(R.id.editProfile);
        editpass = (Button) findViewById(R.id.changepass);
        name = (TextView) findViewById(R.id.pname);
        phone = (TextView) findViewById(R.id.pphone);
        email = (TextView) findViewById(R.id.pemail);

        Intent intent = getIntent();
        String position = intent.getStringExtra("position");
        ref = FirebaseDatabase.getInstance().getReference("Login").child(position);
        Query q = ref;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                name.setText(snapshot.child("name").getValue().toString());
                phone.setText(snapshot.child("phone").getValue().toString());
                email.setText(snapshot.child("email").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        editprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, EditProfile.class);
                i.putExtra("email",email.getText().toString());
                startActivity(i);
            }
        });

        editpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, ForgetPassword.class);
                i.putExtra("email", email.getText().toString());
                startActivity(i);
            }
        });
    }
}