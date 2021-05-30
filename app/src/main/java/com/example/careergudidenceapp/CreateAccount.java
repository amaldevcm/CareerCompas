package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class CreateAccount extends AppCompatActivity {
    EditText name, phone, email, pass ,repass;
    Button create;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        name = findViewById(R.id.acc_name);
        phone = (EditText) findViewById(R.id.phonenumber);
        email = (EditText) findViewById(R.id.acc_email);
        pass = (EditText) findViewById(R.id.acc_pass);
        repass = (EditText) findViewById(R.id.acc_repass);

        ref = FirebaseDatabase.getInstance().getReference("Login");
//        Query q = ref;
        create = (Button) findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pass.getText().toString().equals(repass.getText().toString())){
                    NewAccount acc = new NewAccount(name.getText().toString(), email.getText().toString(),pass.getText().toString(),phone.getText().toString());
                    String id = ref.push().getKey();
                    ref.child(id).setValue(acc);

                    Intent i = new Intent(CreateAccount.this, HomePage.class);
                    i.putExtra("position",ref.child(id).getKey());
                    startActivity(i);
                }
                else{
                    pass.setText("");
                    repass.setText("");
                    Snackbar bar = Snackbar.make(view, "Wrong password", 500);
                    bar.show();
                }
            }
        });
    }
}