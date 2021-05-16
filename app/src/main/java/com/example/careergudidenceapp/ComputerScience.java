package com.example.careergudidenceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ComputerScience extends AppCompatActivity {
    RecyclerView job, subject, company, book;

    TextView title, starter_salary, mid_salary, senior_salary;

    List<ListItems> joblist = new ArrayList<ListItems>();
    List<ListItems> subjectlist = new ArrayList<ListItems>();
    List<CompanyItem> companylist = new ArrayList<CompanyItem>();
    List<ListItems> booklist = new ArrayList<>();

    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_science);

        job = (RecyclerView) findViewById(R.id.jobList);
        subject = (RecyclerView) findViewById(R.id.SubjectList);
        book = (RecyclerView) findViewById(R.id.bookList);
        company = (RecyclerView) findViewById(R.id.companyList);

        title = (TextView) findViewById(R.id.course_title);
        starter_salary = (TextView) findViewById(R.id.salary_start);
        mid_salary = (TextView) findViewById(R.id.salary_mid);
        senior_salary = (TextView) findViewById(R.id.salary_senior);


        String degree, course;
        Intent intent = getIntent();
        degree = intent.getStringExtra("Degree");
        course = intent.getStringExtra("Stream");
        ref = FirebaseDatabase.getInstance().getReference("Stream").child(degree).child(course);


//       Getting data from firebase and setting data in recyclerView
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        LinearLayoutManager layoutManager4 = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        job.setLayoutManager(layoutManager1);
        subject.setLayoutManager(layoutManager2);
        company.setLayoutManager(layoutManager3);
        book.setLayoutManager(layoutManager4);

        RecyclerviewAdapter jobadapter = new RecyclerviewAdapter(joblist,this.getApplicationContext());
        RecyclerviewAdapter subadapter = new RecyclerviewAdapter(subjectlist,this.getApplicationContext());
        CompanyListAdapter cadapter = new CompanyListAdapter(companylist,this.getApplicationContext());
        RecyclerviewAdapter bookadapter = new RecyclerviewAdapter(booklist,this.getApplicationContext());

        Query q = ref.child("Title");
        Query q1 = ref.child("Job");
        Query q2 = ref.child("Subject");
        Query q5 = ref.child("Salary");
        Query q3 = ref.child("Company");
        Query q4 = ref.child("Book");

//        Getting Title
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                title.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

//      Adding items into Salary
        q5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                starter_salary.setText(snapshot.child("Starting").getValue().toString());
                mid_salary.setText(snapshot.child("Mid-level").getValue().toString());
                senior_salary.setText(snapshot.child("Senior-level").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

//        Adding items into Job list
        q1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    ListItems item1 = new ListItems(R.drawable.suitcase, data.child("Title").getValue().toString(),"job",course);
                    joblist.add(item1);
                }
                jobadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//      Adding items into Subject list
        q2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data: snapshot.getChildren()){
                    ListItems item2 = new ListItems(R.drawable.books, data.child("Title").getValue().toString(), "subject",course);
                    subjectlist.add(item2);
                }
                subadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


//       Adding items into Company list
        q3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    CompanyItem item = new CompanyItem(data.getValue().toString(), "0");
                    companylist.add(item);
                }
                cadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Adding items into book list
        q4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data: snapshot.getChildren()){
                    ListItems item = new ListItems(R.drawable.books, data.child("Title").getValue().toString(), "book", course);
                    booklist.add(item);
                }
                bookadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });



//        Setting adapter
        job.setAdapter(jobadapter);
        subject.setAdapter(subadapter);
        company.setAdapter(cadapter);
        book.setAdapter(bookadapter);
    }
}