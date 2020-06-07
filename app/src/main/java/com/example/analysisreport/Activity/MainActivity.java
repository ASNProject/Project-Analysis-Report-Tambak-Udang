package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.analysisreport.Adapter.MyAdapter;
import com.example.analysisreport.Listener.IFirebaseLoad;
import com.example.analysisreport.Model.Tambak;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.example.analysisreport.Transformer.DepthPageTransformer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;
    private TextView isianusername;
    private Button btnTambah;

    SharePreference sessions;
    private String KEY_NAME = "TambakUdang";

    private View tambah;


    ViewPager viewPager;
    MyAdapter adapter;

    DatabaseReference tambaks;

    IFirebaseLoad iFirebaseLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        isianusername = findViewById(R.id.isi);
        btnTambah = findViewById(R.id.buttontambah);


        bukadata();
        tambah();

    }

    private void login(){
        final FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            if (user.getDisplayName() !=null){
               //isianusername.setText(user.getDisplayName());
               //final String data = isianusername.getText().toString();

                tambaks = FirebaseDatabase.getInstance().getReference(user.getDisplayName());

                //Init Event
                iFirebaseLoad = this;

                loadData();

                viewPager = (ViewPager)findViewById(R.id.view_pager);
                viewPager.setPageTransformer(true, new DepthPageTransformer());
            }
        }
    }

    private void bukadata(){
        sessions = new SharePreference(MainActivity.this.getApplicationContext());
        isianusername = findViewById(R.id.isi);
        String nama = sessions.getDatas();
        isianusername.setText(nama);
         String tisiannama = isianusername.getText().toString();
        tambaks = FirebaseDatabase.getInstance().getReference(tisiannama);

        Toast.makeText(this, "Membuka data dari "+tisiannama,Toast.LENGTH_SHORT).show();
        if (isianusername.getText().toString().length()==0){
           Intent i = new Intent(MainActivity.this, ListTambak.class);
           startActivity(i);
          finish();

        }
        else {
            tambaks = FirebaseDatabase.getInstance().getReference(tisiannama);
                iFirebaseLoad =this;
                loadData();
                viewPager = (ViewPager)findViewById(R.id.view_pager);
                viewPager.setPageTransformer(true, new DepthPageTransformer());

        }
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
    private void tambah(){
    }
}


