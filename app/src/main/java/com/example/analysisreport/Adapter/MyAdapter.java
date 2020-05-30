package com.example.analysisreport.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.analysisreport.InputData;
import com.example.analysisreport.ListTambak;
import com.example.analysisreport.MainActivity;
import com.example.analysisreport.Model.RequestDataAir;
import com.example.analysisreport.Model.RequestUpdateAir;
import com.example.analysisreport.Model.RequestUpdatePakan;
import com.example.analysisreport.Model.RequestUpdatePanen;
import com.example.analysisreport.Model.RequestUpdatePerlakuan;
import com.example.analysisreport.Model.RequestUpdateSampling;
import com.example.analysisreport.Model.Tambak;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MyAdapter extends PagerAdapter {

    private static final String TAG = "Custom";
    private String KEY_NAME = "username";
    private String username;
    private Button btnTambah;
    AlertDialog.Builder dialog;
    View dialogView;
    SharePreference sessions;
    private TextView namatambak, namakolam, getsuhu, getsalinitas, getkecerahan, getph, getdoksigen;
    private String GetUserID;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase getDatabase;
    private DatabaseReference getRefenence;



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
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        //Inflate view
        final View view = inflater.inflate(R.layout.view_pager_layout,container,false);

        //View
         final TextView namakolam = (TextView)view.findViewById(R.id.namakolam);
        final TextView namatambak = (TextView)view.findViewById(R.id.namatambak);
        //Set Data
        namakolam.setText(tambakList.get(position).getKolam());
        String datas = namakolam.getText().toString();


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
        final TextView tambahkolam = (TextView)view.findViewById(R.id.tamabahkolam);
        final Button btnTambah = (Button)view.findViewById(R.id.buttontambah);

        tambahkolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), InputData.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), ListTambak.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        sessions = new SharePreference(context.getApplicationContext());
        String nama = sessions.getDatas();
        namatambak.setText(nama);
        getDatabase = FirebaseDatabase.getInstance();
        getRefenence = getDatabase.getReference().child(nama);
        String kolamnama = namakolam.getText().toString();
       //Database getFirebase
        getRefenence = FirebaseDatabase.getInstance().getReference().child(nama);
        getRefenence.child(kolamnama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAir requestUpdateAir = dataSnapshot.child("Airupdate").getValue(RequestUpdateAir.class);
                Suhu.setText(requestUpdateAir.getNsuhu());
                Doksigen.setText(requestUpdateAir.getNdoksigen());
                Salinitas.setText(requestUpdateAir.getNsalinitas());
                Kecerahan.setText(requestUpdateAir.getNkecerahan());
                pH.setText(requestUpdateAir.getNph());

                RequestUpdatePakan requestUpdatePakan = dataSnapshot.child("Pakanupdate").getValue(RequestUpdatePakan.class);
                Catatan.setText(requestUpdatePakan.getNcatatan());
                Jumlah.setText(requestUpdatePakan.getNjumlah());
                TanggalPakan.setText(requestUpdatePakan.getNtanggappakan());

                RequestUpdatePanen requestUpdatePanen = dataSnapshot.child("Panenupdate").getValue(RequestUpdatePanen.class);
                Berat.setText(requestUpdatePanen.getNberat());
                Size.setText(requestUpdatePanen.getNsize());
                TanggalPanen.setText(requestUpdatePanen.getNtanggalpenen());

                RequestUpdateSampling requestUpdateSampling = dataSnapshot.child("Samplingupdate").getValue(RequestUpdateSampling.class);
                ABW.setText(requestUpdateSampling.getNabw());
                ADG.setText(requestUpdateSampling.getNadg());
                TanggalSampling.setText(requestUpdateSampling.getNtanggalsampling());

                RequestUpdatePerlakuan requestUpdatePerlakuan = dataSnapshot.child("Perlakuanupdate").getValue(RequestUpdatePerlakuan.class);
                Perlakuan.setText(requestUpdatePerlakuan.getNperlakuan());
                TanggalPerlakuan.setText(requestUpdatePerlakuan.getNtanggalperlakuan());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        container.addView(view);
        return  view;
    }
}
