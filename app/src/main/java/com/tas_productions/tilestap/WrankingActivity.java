package com.tas_productions.tilestap;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class WrankingActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
      private MyAdapter adapter;
    private DatabaseReference dbr;
    private List<Scores> scoresList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wranking);
       dbr = FirebaseDatabase.getInstance().getReference().child("scores");
       dbr.keepSynced(true);
       recyclerView = findViewById(R.id.rid);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
       dbr.addValueEventListener(new ValueEventListener() {

           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               scoresList = new ArrayList<>();
               for(DataSnapshot dsnapshot : dataSnapshot.getChildren()){
                   Scores gotScore = dsnapshot.getValue(Scores.class);
                   scoresList.add(gotScore);
               }
      Collections.sort(scoresList);

               adapter = new MyAdapter(scoresList);
               recyclerView.setItemAnimator( new DefaultItemAnimator());
               recyclerView.setAdapter(adapter);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
