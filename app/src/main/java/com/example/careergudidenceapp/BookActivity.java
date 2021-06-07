package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class BookActivity extends AppCompatActivity {
    CardView amazon_visit;
    TextView bname, bauth;
    ImageView bimg;
    Intent i;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        i = getIntent();

        bname= (TextView) findViewById(R.id.book_name);
        bauth = (TextView) findViewById(R.id.book_auth);
        bimg = (ImageView) findViewById(R.id.book_img);
        amazon_visit = (CardView) findViewById(R.id.visit);

        String course = i.getStringExtra("Course");
        String book = i.getStringExtra("Book");
        final String[] url = new String[1];

        ref = FirebaseDatabase.getInstance().getReference("Stream").child("Engineering").child(course).child("Book").child(book);
        Query q = ref;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bname.setText(snapshot.child("Title").getValue().toString());
                bauth.setText(snapshot.child("Author").getValue().toString());
                RequestOptions options = new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round);

                Glide.with(BookActivity.this).load(snapshot.child("Image").getValue().toString()).apply(options).into(bimg);
                url[0] = snapshot.child("Link").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        amazon_visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url[0]));
                startActivity(intent);
            }
        });

    }
}