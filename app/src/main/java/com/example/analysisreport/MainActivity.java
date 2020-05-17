package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analysisreport.Adapter.Adapter;
import com.example.analysisreport.Listener.IFirebaseLoadDone;
import com.example.analysisreport.Model.Report;
import com.example.analysisreport.Transformer.DepthPageTransformer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IFirebaseLoadDone {

    ViewPager viewPager;
    Adapter adapter;

    DatabaseReference reports;

    IFirebaseLoadDone iFirebaseLoadDone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Init Firebase
        reports = FirebaseDatabase.getInstance().getReference();
        reports.child("Tambak1").child("Kolam1").child("Kualitas air");

        //Init Event
        iFirebaseLoadDone = this;

        loadRepot();

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

    }

    private void loadRepot() {
        reports.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Report> reportList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot reportSnapShot:dataSnapshot.getChildren())
                    reportList.add(reportSnapShot.getValue(Report.class));
                iFirebaseLoadDone.onFirebaseLoadSuccess(reportList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadDone.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Report> reportList) {
        adapter = new Adapter(this,reportList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
}
