package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.analysisreport.Adapter.DataDetailSampling.PostAdapterSampling;
import com.example.analysisreport.Adapter.DetailSamplingHolder;
import com.example.analysisreport.Chart.ChartSampling;
import com.example.analysisreport.Model.ModelDetailSampling;
import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DetailSampling extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<ModelDetailSampling, DetailSamplingHolder>mAdapter;
    private LinearLayoutManager mManager;
    private RecyclerView mRecycler;

    SharePreference sessions;
    private Context context;
    private PostAdapterSampling adapterSampling;

    private TextView Petani, Kolam, Jumlah, Tebar;
    private ImageView lihatgrafik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sampling);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sessions = new SharePreference(DetailSampling.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        lihatgrafik = findViewById(R.id.lihatchartsampling);
        mRecycler = findViewById(R.id.list_detai_sampling);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RequestDataSampling> options =
                new FirebaseRecyclerOptions.Builder<RequestDataSampling>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(data)
                .child(tnamakolam).child("Sampling"), RequestDataSampling.class).build();

        adapterSampling = new PostAdapterSampling(options, this);
        mRecycler.setAdapter(adapterSampling);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        datas();
        lihatgrafik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailSampling.this, ChartSampling.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (adapterSampling != null) {
            adapterSampling.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterSampling != null) {
            adapterSampling.stopListening();
        }
    }
    private void datas() {
        sessions = new SharePreference(DetailSampling.this.getApplicationContext());
        String nama = sessions.getDatas();
        final String kolam = sessions.getDetailkolam();
        Petani = findViewById(R.id.dpetani2);
        Kolam = findViewById(R.id.dkolam2);
        Jumlah = findViewById(R.id.djumlah2);
        Tebar = findViewById(R.id.dtebar2);
        mDatabase.child(nama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestDataKolam requestDataKolam = dataSnapshot.child(kolam).getValue(RequestDataKolam.class);
                Petani.setText(requestDataKolam.getNamapetani());
                Kolam.setText(requestDataKolam.getKolam());
                Jumlah.setText(requestDataKolam.getJumlah());
                Tebar.setText(requestDataKolam.getTanggaltebar());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
