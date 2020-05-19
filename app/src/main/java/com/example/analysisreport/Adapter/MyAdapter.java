package com.example.analysisreport.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.analysisreport.MainActivity;
import com.example.analysisreport.Model.Tambak;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;

import android.content.SharedPreferences;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    private DatabaseReference getData;
    private DatabaseReference getReference;

    Context context;
    List<Tambak> tambakList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Tambak> tambakList) {
        this.context = context;
        this.tambakList = tambakList;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return tambakList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Inflate view
        View view = inflater.inflate(R.layout.view_pager_layout,container,false);

        //View
        final TextView namakolam = (TextView)view.findViewById(R.id.namakolam);
        //Set Data
        namakolam.setText(tambakList.get(position).getKolam());


        //Kualitas air
        final TextView Suhu = (TextView)view.findViewById(R.id.nilaisuhu);
        final TextView Doksigen = (TextView)view.findViewById(R.id.nilaido);
        final TextView Salinitas = (TextView)view.findViewById(R.id.nilaisalinitas);
        final TextView Kecerahan = (TextView)view.findViewById(R.id.nilaikecerahan);
        final TextView pH = (TextView)view.findViewById(R.id.nilaiph);
        //Pakan
        final TextView Catatan = (TextView)view.findViewById(R.id.catatanpakan);
        final TextView Jumlah = (TextView)view.findViewById(R.id.nilaijumlahpakan);
        final TextView TanggalPakan = (TextView)view.findViewById(R.id.tglpakan);
        //Panen
        final TextView Berat = (TextView)view.findViewById(R.id.beratpanen);
        final TextView Size = (TextView)view.findViewById(R.id.sizepanen);
        final TextView TanggalPanen = (TextView)view.findViewById(R.id.tglpanen);
        //Perlakuan
        final TextView Perlakuan = (TextView)view.findViewById(R.id.nilaiperlakuan);
        final TextView TanggalPerlakuan = (TextView)view.findViewById(R.id.tglperlakuan);
        //Sampling
        final TextView ABW = (TextView)view.findViewById(R.id.nilaiabw);
        final TextView ADG = (TextView)view.findViewById(R.id.nilaiadg);
        final TextView TanggalSampling = (TextView)view.findViewById(R.id.tglsampling);

        //Database getFirebase
        getData = FirebaseDatabase.getInstance().getReference().child("Tambak1");
        getData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String kolamnama = namakolam.getText().toString();
                    //Kualitas Air
                    String suhu = dataSnapshot.child(kolamnama).child("Air").child("nilaiSuhu").getValue().toString();
                    String doksigen = dataSnapshot.child(kolamnama).child("Air").child("DOksigen").getValue().toString();
                    String salinitas = dataSnapshot.child(kolamnama).child("Air").child("nilaiSalinitas").getValue().toString();
                    String kecerahan = dataSnapshot.child(kolamnama).child("Air").child("nilaiKecerahan").getValue().toString();
                    String ph = dataSnapshot.child(kolamnama).child("Air").child("PH").getValue().toString();
                    Suhu.setText(suhu);
                    Doksigen.setText(doksigen);
                    Salinitas.setText(salinitas);
                    Kecerahan.setText(kecerahan);
                    pH.setText(ph);
                    //Pakan
                    String nilaicatatan = dataSnapshot.child(kolamnama).child("Pakan").child("nilaiCatatan").getValue().toString();
                    String nilaijumlah = dataSnapshot.child(kolamnama).child("Pakan").child("nilaiJumlah").getValue().toString();
                    String tglpakan = dataSnapshot.child(kolamnama).child("Pakan").child("tglPakan").getValue().toString();
                    Catatan.setText(nilaicatatan);
                    Jumlah.setText(nilaijumlah);
                    TanggalPakan.setText(tglpakan);
                    //Panen
                    String nilaiberat = dataSnapshot.child(kolamnama).child("Panen").child("nilaiBerat").getValue().toString();
                    String nilaisize = dataSnapshot.child(kolamnama).child("Panen").child("nilaiSize").getValue().toString();
                    String tglpanen = dataSnapshot.child(kolamnama).child("Panen").child("tglPanen").getValue().toString();
                    Berat.setText(nilaiberat);
                    Size.setText(nilaisize);
                    TanggalPanen.setText(tglpanen);
                    //Sampling
                    String abw = dataSnapshot.child(kolamnama).child("Sampling").child("ABW").getValue().toString();
                    String adg = dataSnapshot.child(kolamnama).child("Sampling").child("ADG").getValue().toString();
                    String tglsampling = dataSnapshot.child(kolamnama).child("Sampling").child("tglSampling").getValue().toString();
                    ABW.setText(abw);
                    ADG.setText(adg);
                    TanggalSampling.setText(tglsampling);
                    //Perlakuan
                    String nilaiperlakuan = dataSnapshot.child(kolamnama).child("Perlakuan").child("nilaiPerlakuan").getValue().toString();
                    String tglperlakuan = dataSnapshot.child(kolamnama).child("Perlakuan").child("tglPerlakuan").getValue().toString();
                    Perlakuan.setText(nilaiperlakuan);
                    TanggalPerlakuan.setText(tglperlakuan);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        container.addView(view);
        return  view;
    }

}
