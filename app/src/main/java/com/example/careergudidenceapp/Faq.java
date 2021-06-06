package com.example.careergudidenceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Faq extends AppCompatActivity {
    RecyclerView faqlist;
//    DatabaseReference ref;
    List<FAQItems> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_faq);

        faqlist = (RecyclerView) findViewById(R.id.rv_faq);
//        ref = FirebaseDatabase.getInstance().getReference("FAQ");
        LinearLayoutManager l1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        faqlist.setLayoutManager(l1);
        FAQAdapter adapter = new FAQAdapter(data, this.getApplicationContext());

        FAQItems item1 = new FAQItems("who are you?","iam amal");
        data.add(item1);

        faqlist.setAdapter(adapter);
    }
}