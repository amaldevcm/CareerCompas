package com.example.careergudidenceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class SubjectActivity extends AppCompatActivity {
    RecyclerView rv;
    List<ListItems> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        Intent i = getIntent();
        String course, job;
        course = i.getStringExtra("Course");
        job = i.getStringExtra("Subject");
        ListItems item1 = new ListItems(R.drawable.suitcase,"Bigdata","job", "CSE");
        list.add(item1);
        ListItems item2 = new ListItems(R.drawable.suitcase,"DSA","job", "CSE");
        list.add(item2);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        rv = (RecyclerView) findViewById(R.id.jobList);
        rv.setLayoutManager(layoutManager);

        RecyclerviewAdapter adapter = new RecyclerviewAdapter(list,this.getApplicationContext());
        rv.setAdapter(adapter);

        
    }
}