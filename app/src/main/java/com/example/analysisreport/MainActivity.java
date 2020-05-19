package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analysisreport.Adapter.MyAdapter;
import com.example.analysisreport.Listener.IFirebaseLoad;
import com.example.analysisreport.Model.Tambak;
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

public class MainActivity extends AppCompatActivity implements IFirebaseLoad {

    private TextView Kolam, Suhu, Doksigen, Salinitas, Kecerahan, pH, Catatan, Jumlah,
            TanggalPakan, Berat, Size, TanggalPanen, Perlakuan, TanggalPerlakuan,
            ABW, ADG, TanggalSampling;


    ViewPager viewPager;
    MyAdapter adapter;

    DatabaseReference tambaks;

    IFirebaseLoad iFirebaseLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kualitas air
        Suhu = findViewById(R.id.nilaisuhu);
        Doksigen = findViewById(R.id.nilaido);
        Salinitas = findViewById(R.id.nilaisalinitas);
        Kecerahan = findViewById(R.id.nilaikecerahan);
        pH = findViewById(R.id.nilaiph);
        //Pakan
        Catatan = findViewById(R.id.catatanpakan);
        Jumlah = findViewById(R.id.nilaijumlahpakan);
        TanggalPakan = findViewById(R.id.tglpakan);
        //Panen
        Berat = findViewById(R.id.beratpanen);
        Size = findViewById(R.id.sizepanen);
        TanggalPanen = findViewById(R.id.tglpanen);
        //Perlakuan
        Perlakuan = findViewById(R.id.nilaiperlakuan);
        TanggalPerlakuan = findViewById(R.id.tglperlakuan);
        //Sampling
        ABW = findViewById(R.id.nilaiabw);
        ADG = findViewById(R.id.nilaiadg);
        TanggalSampling = findViewById(R.id.tglsampling);


        //Init Firebase
        tambaks = FirebaseDatabase.getInstance().getReference("Tambak1");

        //Init Event
        iFirebaseLoad = this;

        loadData();

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());


    }

    private void loadData() {
        tambaks.addListenerForSingleValueEvent(new ValueEventListener() {
            List<Tambak> tambakList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot tambakSnapshot:dataSnapshot.getChildren())
                    tambakList.add((tambakSnapshot.getValue(Tambak.class)));
                iFirebaseLoad.onFirebaseLoadSuccess(tambakList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            iFirebaseLoad.onFirebaseLoadFailed(databaseError.getMessage());
            }
        });
    }

    @Override
    public void onFirebaseLoadSuccess(List<Tambak> tambakList) {
        adapter = new MyAdapter(this,tambakList);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onFirebaseLoadFailed(String message) {
        Toast.makeText(this, ""+message,Toast.LENGTH_SHORT).show();
    }


}


