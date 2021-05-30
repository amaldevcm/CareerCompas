package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditProfile extends AppCompatActivity {
    private EditText mail, name, phone;
    private LinearLayout linearLayout;
    private Button save;
    String editmail;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        Intent i = getIntent();
        editmail = i.getStringExtra("email");

        mail = (EditText) findViewById(R.id.Edit_email);
        name = (EditText) findViewById(R.id.Edit_name);
        phone = (EditText) findViewById(R.id.Edit_phone);
        save = (Button) findViewById(R.id.Edit_btn);
        linearLayout = (LinearLayout) findViewById(R.id.edit_layout);

        ref = FirebaseDatabase.getInstance().getReference("Login");
        Query q = ref;

        mail.setText(editmail);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mail.getText().toString();
                if (save.getText().equals("Next")){
                    q.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean flag = false;
                            for (DataSnapshot data: snapshot.getChildren()){
                                if (email.equals(data.child("email").getValue().toString())){
                                    flag = true;
                                    break;
                                }
                            }
                            if(flag){
                                ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
                                params.height = 200;
                                linearLayout.setLayoutParams(params);
                                save.setText("Update");
                            }
                            else{
                                Snackbar.make(view, "Invalid Email",500).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                else{
                    String n = name.getText().toString();
                    String phoneno = phone.getText().toString();

                    q.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot data: snapshot.getChildren()){
                                if (email.equals(data.child("email").getValue().toString())){
                                    ref.child(data.getKey()).child("name").setValue(n);
                                    ref.child(data.getKey()).child("phone").setValue(phoneno);
                                    break;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
}