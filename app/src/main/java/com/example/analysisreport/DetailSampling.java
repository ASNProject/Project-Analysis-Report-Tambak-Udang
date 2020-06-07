package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.analysisreport.Adapter.DetailSamplingHolder;
import com.example.analysisreport.Model.ModelDetailSampling;
import com.example.analysisreport.Model.RequestDataKolam;
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

    private TextView Petani, Kolam, Jumlah, Tebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sampling);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mRecycler = findViewById(R.id.list_detai_sampling);
        mRecycler.setHasFixedSize(true);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);

        Query query = getQuery(mDatabase);
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ModelDetailSampling>()
                .setQuery(query, ModelDetailSampling.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<ModelDetailSampling, DetailSamplingHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DetailSamplingHolder detailSamplingHolder, int i, @NonNull ModelDetailSampling modelDetailSampling) {
                detailSamplingHolder.bindoToDetailSampling(modelDetailSampling, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @NonNull
            @Override
            public DetailSamplingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new DetailSamplingHolder(inflater.inflate(R.layout.item_detail_sampling, parent,false));

            }
        };
        data();
        mAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(mAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAdapter != null){
            mAdapter.startListening();
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mAdapter !=null){
            mAdapter.stopListening();
        }
    }

    private Query getQuery(DatabaseReference mDatabase) {
        sessions = new SharePreference(DetailSampling.this.getApplicationContext());
        String nama = sessions.getDatas();
        String kolam = sessions.getDetailkolam();
        Query query = mDatabase.child(nama).child(kolam).child("Sampling");
        return query;
    }
    private void data(){
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
