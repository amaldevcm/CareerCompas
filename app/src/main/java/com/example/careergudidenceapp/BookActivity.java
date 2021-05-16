package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BookActivity extends AppCompatActivity {
    TextView bname, bauth;
//    ImageView bimg;
    Intent i;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        i = getIntent();

        bname= (TextView) findViewById(R.id.book_name);
        bauth = (TextView) findViewById(R.id.book_auth);
//        bimg = (ImageView) findViewById(R.id.book_img);

        String course = i.getStringExtra("Course");
        String book = i.getStringExtra("Book");
//        Toast.makeText(this, book, Toast.LENGTH_SHORT).show();

        ref = FirebaseDatabase.getInstance().getReference("Stream").child("Engineering").child(course).child("Book").child(book);
        Query q = ref;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bname.setText(snapshot.child("Title").getValue().toString());
                bauth.setText(snapshot.child("Author").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

    }
}