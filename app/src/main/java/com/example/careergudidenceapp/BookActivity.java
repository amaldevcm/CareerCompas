package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BookActivity extends AppCompatActivity {
    TextView bname, bauth;
    ImageView bimg;
    Intent i = getIntent();
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bname= (TextView) findViewById(R.id.book_name);
        bauth = (TextView) findViewById(R.id.book_auth);
        bimg = (ImageView) findViewById(R.id.book_img);

        String course = i.getStringExtra("Course");
        String book = i.getStringExtra("Book");

        ref = FirebaseDatabase.getInstance().getReference("Stream").child("Engineering").child(course).child(book);
        Query q = ref;


    }
}