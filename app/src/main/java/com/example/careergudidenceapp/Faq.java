package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Faq extends AppCompatActivity {
    RecyclerView faqlist;
    DatabaseReference ref;
    List<FAQItems> faqdata = new ArrayList<>();
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
        ref = FirebaseDatabase.getInstance().getReference("FAQ");

        LinearLayoutManager l1 = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        faqlist.setLayoutManager(l1);

        FAQAdapter adapter = new FAQAdapter(faqdata, this.getApplicationContext());

        Query q = ref;
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                faqdata.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    FAQItems item1 = new FAQItems(data.child("Ques").getValue().toString(),data.child("Ans").getValue().toString());
                    faqdata.add(item1);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


        faqlist.setAdapter(adapter);
    }
}