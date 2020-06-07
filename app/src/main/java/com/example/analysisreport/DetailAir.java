package com.example.analysisreport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.analysisreport.Adapter.DetailAirHolder;
import com.example.analysisreport.DataDetailAir.PostAdapterAir;
import com.example.analysisreport.Model.ModelDetailAir;
import com.example.analysisreport.Model.RequestDataAir;
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

public class DetailAir extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private FirebaseRecyclerAdapter<ModelDetailAir, DetailAirHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    private PostAdapterAir adapterAir;
    SharePreference sessions;
    private Context context;

    private TextView Petani, Kolam, Jumlah, Tebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_air);
        sessions = new SharePreference(DetailAir.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(data).child(tnamakolam).child("Air");
        mRecycler = findViewById(R.id.list_detai_air);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RequestDataAir> options =
                new FirebaseRecyclerOptions.Builder<RequestDataAir>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child(data).child(tnamakolam).child("Air"), RequestDataAir.class)
                        .build();

        adapterAir = new PostAdapterAir(options, this);
        mRecycler.setAdapter(adapterAir);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        datas();


  /*      Query query = getQuery(mDatabase);

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ModelDetailAir>()
                .setQuery(query, ModelDetailAir.class)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<ModelDetailAir, DetailAirHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DetailAirHolder detailAirHolder, int i, @NonNull ModelDetailAir modelDetailAir) {

                detailAirHolder.bindoDetailair(modelDetailAir, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @NonNull
            @Override
            public DetailAirHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new DetailAirHolder(inflater.inflate(R.layout.item_detail_air, parent, false));
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

        sessions = new SharePreference(DetailAir.this.getApplicationContext());
        String nama = sessions.getDatas();
        String kolam = sessions.getDetailkolam();
        Query query = mDatabase.child(nama).child(kolam).child("Air");
        return query;
    }

    */
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapterAir != null) {
            adapterAir.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterAir != null) {
            adapterAir.stopListening();
        }
    }

    private void datas() {
        sessions = new SharePreference(DetailAir.this.getApplicationContext());
        String nama = sessions.getDatas();
        final String kolam = sessions.getDetailkolam();
        Petani = findViewById(R.id.dpetani);
        Kolam = findViewById(R.id.dkolam);
        Jumlah = findViewById(R.id.djumlah);
        Tebar = findViewById(R.id.dtebar);
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
