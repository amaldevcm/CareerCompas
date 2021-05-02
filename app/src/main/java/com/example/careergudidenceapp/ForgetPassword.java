package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ForgetPassword extends AppCompatActivity {
    EditText email, pass, repass;
    Button save;
    LinearLayout fpass;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

//        Declarations
        Intent i = getIntent();
        String editmail = i.getStringExtra("email");

        email = (EditText) findViewById(R.id.Forget_Email);
        pass = (EditText) findViewById(R.id.forget_pass);
        repass = (EditText) findViewById(R.id.forget_repass);
        save = (Button) findViewById(R.id.confirm);
        fpass = (LinearLayout) findViewById(R.id.fpass);

        email.setText(editmail);
//        Firebase connection
        ref = FirebaseDatabase.getInstance().getReference("Login");
        Query q = ref;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(save.getText().equals("Next")){
                    q.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean flag = false;
                            for (DataSnapshot data: snapshot.getChildren()){
                                if (data.child("email").getValue().toString().equals(email.getText().toString())){
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag){
                                ViewGroup.LayoutParams params = fpass.getLayoutParams();
                                params.height = 200;
                                fpass.setLayoutParams(params);
                                save.setText("Update password");
                            }
                            else{
                                Snackbar.make(view, "Invalid Email", 500).show();
                                email.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) { }
                    });

                }
                else{
                    String mail = email.getText().toString();
                    String password = pass.getText().toString();
                    String repassword = repass.getText().toString();
                    if(password.equals(repassword)) {
                        q.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                DatabaseReference c = null;
                                boolean flag = false;
                                for (DataSnapshot data : snapshot.getChildren()) {
                                    if (data.child("email").getValue().toString().equals(mail)) {
                                        c = data.getRef();
                                        flag = true;
                                        break;
                                    }
                                }
                                if (flag){
                                    c.child("password").setValue(password);
                                    Intent i = new Intent(ForgetPassword.this,MainActivity.class);
                                    startActivity(i);
                                }
                                else{
                                    Snackbar.make(view, "Wrong Email", 500).show();
                                    pass.setText("");
                                    repass.setText("");
                                    email.setText("");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                    else{
                        Snackbar.make(view, "Password Missmatch", 500).show();
                    }
                }
            }
        });
    }
}