package com.example.careergudidenceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class ExploreEngineering extends AppCompatActivity {
    CardView cse, mech, auto, civil, eee, ece, mec, ce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_engineering);

        cse = (CardView) findViewById(R.id.cse);
        mech = (CardView) findViewById(R.id.mech);
        auto = (CardView) findViewById(R.id.auto);
        civil = (CardView) findViewById(R.id.civil);
        eee = (CardView) findViewById(R.id.eee);
        ece = (CardView) findViewById(R.id.ece);
        mec = (CardView) findViewById(R.id.mec);
        ce = (CardView) findViewById(R.id.ce);

        cse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cse.setCardElevation(20);
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","CSE");
                startActivity(intent);
            }
        });

        mech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","MECH");
                startActivity(intent);
            }
        });

        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","AUTO");
                startActivity(intent);
            }
        });

        civil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","CIVIL");
                startActivity(intent);
            }
        });

        eee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","EEE");
                startActivity(intent);
            }
        });

        ece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","ECE");
                startActivity(intent);
            }
        });

        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","CE");
                startActivity(intent);
            }
        });

        mec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ExploreEngineering.this,ComputerScience.class);
                intent.putExtra("Degree", "Engineering");
                intent.putExtra("Stream","MEC");
                startActivity(intent);
            }
        });
    }
}