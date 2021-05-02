package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class JobActivity extends AppCompatActivity {
    DatabaseReference ref;
    TextView tvtitle,tvmin,tvmax,tvavg;
    TextView btn_skill;
    ListView skillList;
    CardView skill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

        Intent intent = getIntent();
        String course = intent.getStringExtra("Course");
        String job = intent.getStringExtra("Job");
//
//        Toast.makeText(this, job, Toast.LENGTH_SHORT).show();
        btn_skill = (TextView) findViewById(R.id.btn_skill);
        skillList = (ListView) findViewById(R.id.skill_list);
        skill = (CardView) findViewById(R.id.card_skill);
        btn_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn_skill.getText().equals("View More")){
                    ViewGroup.LayoutParams params = skillList.getLayoutParams();
                    params.height = 300;
                    skillList.setLayoutParams(params);
                    btn_skill.setText("View Less");
                }
                else{
                    ViewGroup.LayoutParams params = skillList.getLayoutParams();
                    params.height = 0;
                    skillList.setLayoutParams(params);
                    btn_skill.setText("View More");
                }
            }
        });

//        Firebase connection
        ref = FirebaseDatabase.getInstance().getReference("Stream").child("Engineering").child(course).child("Job");

//        Log.d(String.valueOf(ref), "reference");
        tvtitle = (TextView) findViewById(R.id.tv_title);
        tvmin = (TextView) findViewById(R.id.tv_min);
        tvmax = (TextView) findViewById(R.id.tv_max);
        tvavg = (TextView) findViewById(R.id.tv_avg);

        Query q = ref.child(job);
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String title = snapshot.child("Title").getValue().toString();
                String min = snapshot.child("Salary").child("Min").getValue().toString();
                String max = snapshot.child("Salary").child("Max").getValue().toString();
                String avg = snapshot.child("Salary").child("Avg").getValue().toString();

                tvtitle.setText(title);
                tvmin.setText(min);
                tvmax.setText(max);
                tvavg.setText(avg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}