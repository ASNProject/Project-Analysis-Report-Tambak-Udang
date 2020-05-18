package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    private TextView Suhu, Doksigen, Salinitas, Kecerahan, pH, Catatan, Jumlah,
            TanggalPakan, Berat, Size, TanggalPanen, Perlakuan, TanggalPerlakuan,
            ABW, ADG, TanggalSampling;

    private DatabaseReference getKualitasair;
    private DatabaseReference getReference;

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

        //Database getFirebase
        getKualitasair = FirebaseDatabase.getInstance().getReference().child("Tambak1");

        //Memanggil dataReport
        dataReport();

    }

    private void dataReport(){
        getKualitasair.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    //Kualitas Air
                    String suhu = dataSnapshot.child("Kolam1").child("Air").child("nilaiSuhu").getValue().toString();
                    String doksigen = dataSnapshot.child("Kolam1").child("Air").child("DOksigen").getValue().toString();
                    String salinitas = dataSnapshot.child("Kolam1").child("Air").child("nilaiSalinitas").getValue().toString();
                    String kecerahan = dataSnapshot.child("Kolam1").child("Air").child("nilaiKecerahan").getValue().toString();
                    String ph = dataSnapshot.child("Kolam1").child("Air").child("PH").getValue().toString();
                    Suhu.setText(suhu);
                    Doksigen.setText(doksigen);
                    Salinitas.setText(salinitas);
                    Kecerahan.setText(kecerahan);
                    pH.setText(ph);
                    //Pakan
                    String nilaicatatan = dataSnapshot.child("Kolam1").child("Pakan").child("nilaiCatatan").getValue().toString();
                    String nilaijumlah = dataSnapshot.child("Kolam1").child("Pakan").child("nilaiJumlah").getValue().toString();
                    String tglpakan = dataSnapshot.child("Kolam1").child("Pakan").child("tglPakan").getValue().toString();
                    Catatan.setText(nilaicatatan);
                    Jumlah.setText(nilaijumlah);
                    TanggalPakan.setText(tglpakan);
                    //Panen
                    String nilaiberat = dataSnapshot.child("Kolam1").child("Panen").child("nilaiBerat").getValue().toString();
                    String nilaisize = dataSnapshot.child("Kolam1").child("Panen").child("nilaiSize").getValue().toString();
                    String tglpanen = dataSnapshot.child("Kolam1").child("Panen").child("tglPanen").getValue().toString();
                    Berat.setText(nilaiberat);
                    Size.setText(nilaisize);
                    TanggalPanen.setText(tglpanen);
                    //Sampling
                    String abw = dataSnapshot.child("Kolam1").child("Sampling").child("ABW").getValue().toString();
                    String adg = dataSnapshot.child("Kolam1").child("Sampling").child("ADG").getValue().toString();
                    String tglsampling = dataSnapshot.child("Kolam1").child("Sampling").child("tglSampling").getValue().toString();
                    ABW.setText(abw);
                    ADG.setText(adg);
                    TanggalSampling.setText(tglsampling);
                    //Perlakuan
                    String nilaiperlakuan = dataSnapshot.child("Kolam1").child("Perlakuan").child("nilaiPerlakuan").getValue().toString();
                    String tglperlakuan = dataSnapshot.child("Kolam1").child("Perlakuan").child("tglPerlakuan").getValue().toString();
                    Perlakuan.setText(nilaiperlakuan);
                    TanggalPerlakuan.setText(tglperlakuan);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}


