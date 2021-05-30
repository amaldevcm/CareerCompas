package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText email, pass;
    Button login;
    TextView forget, createacc;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        email = (EditText) findViewById(R.id.login_email);
        pass = (EditText) findViewById(R.id.login_pass);
        login = (Button) findViewById(R.id.Login);
        forget = (TextView) findViewById(R.id.FogetPass);
        createacc = (TextView) findViewById(R.id.createAcc);

        ref = FirebaseDatabase.getInstance().getReference("Login");
        Query q =ref;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                String password = pass.getText().toString();

                q.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean flag = false;
                        int count = 0;
                        for(DataSnapshot data: snapshot.getChildren()){
                            count += 1;
                            if(data.child("email").getValue().toString().equals(mail)){
                                flag = true;
                                if(data.child("password").getValue().toString().equals(password)){
                                    Intent i = new Intent(MainActivity.this, HomePage.class);
                                    i.putExtra("position",data.getKey());
                                    startActivity(i);
                                }
                                else{
                                    Snackbar.make(view, "Incorrect Password",600).show();
                                    pass.setText("");
                                }
                            }
                        }

                        if(!flag){
                            Snackbar.make(view, "Invalid Email",600).show();
                            email.setText("");
                            pass.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ForgetPassword.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Forget password", Toast.LENGTH_SHORT).show();
            }
        });

        createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateAccount.class);
                startActivity(i);
            }
        });
    }
}