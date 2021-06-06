package com.example.careergudidenceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePage extends AppCompatActivity {
    CardView explore, settings, profile;
    String profilePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        Intent intent = getIntent();
        profilePosition = intent.getStringExtra("position");

        explore = (CardView) findViewById(R.id.explore);
        settings = (CardView) findViewById(R.id.settings);
        profile = (CardView) findViewById(R.id.profile);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this,ExploreEngineering.class);
                startActivity(i);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Profile.class);
                i.putExtra("position", profilePosition);
                startActivity(i);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Faq.class);
//                i.putExtra("position", profilePosition);
                startActivity(i);
            }
        });
    }
}