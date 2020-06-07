package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.analysisreport.Adapter.DataDetailPakan.PostAdapterPakan;
import com.example.analysisreport.Adapter.DetailPakanHolder;
import com.example.analysisreport.Model.ModelDataPakan;
import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataPakan;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPakan extends AppCompatActivity {

    private DatabaseReference mDatebase;

    private FirebaseRecyclerAdapter<ModelDataPakan, DetailPakanHolder> mAdapter;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    SharePreference sessions;
    private Context context;
    private PostAdapterPakan adapterPakan;

    private TextView Petani, Kolam, Jumlah, Tebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pakan);
        sessions = new SharePreference(DetailPakan.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatebase = FirebaseDatabase.getInstance().getReference();
        mRecycler = findViewById(R.id.list_detai_pakan);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RequestDataPakan> options =
                new FirebaseRecyclerOptions.Builder<RequestDataPakan>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(data)
                .child(tnamakolam).child("Pakan"), RequestDataPakan.class).build();

        adapterPakan = new PostAdapterPakan(options, this);
        mRecycler.setAdapter(adapterPakan);

        mManager = new LinearLayoutManager(this);
        mManager.setReverseLayout(true);
        mManager.setStackFromEnd(true);
        mRecycler.setLayoutManager(mManager);
        datas();
/*        Query query = getQuery(mDatebase);
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<ModelDataPakan>()
                .setQuery(query, ModelDataPakan.class)
                .build();
        mAdapter = new FirebaseRecyclerAdapter<ModelDataPakan, DetailPakanHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull DetailPakanHolder detailPakanHolder, int i, @NonNull ModelDataPakan modelDataPakan) {
                detailPakanHolder.bindoToDetailPakan(modelDataPakan, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @NonNull
            @Override
            public DetailPakanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                return new DetailPakanHolder(inflater.inflate(R.layout.item_detail_pakan, parent,false));
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

    private Query getQuery(DatabaseReference mDatebase) {
        sessions = new SharePreference(DetailPakan.this.getApplicationContext());
        String nama = sessions.getDatas();
        String kolam = sessions.getDetailkolam();
        Query query = mDatebase.child(nama).child(kolam).child("Pakan");
        return query;
    }
    private void data(){
        sessions = new SharePreference(DetailPakan.this.getApplicationContext());
        String nama = sessions.getDatas();
        final String kolam = sessions.getDetailkolam();
        Petani = findViewById(R.id.dpetani1);
        Kolam = findViewById(R.id.dkolam1);
        Jumlah = findViewById(R.id.djumlah1);
        Tebar = findViewById(R.id.dtebar1);
        mDatebase.child(nama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestDataKolam requestDataKolam= dataSnapshot.child(kolam).getValue(RequestDataKolam.class);
                Petani.setText(requestDataKolam.getNamapetani());
                Kolam.setText(requestDataKolam.getKolam());
                Jumlah.setText(requestDataKolam.getJumlah());
                Tebar.setText(requestDataKolam.getTanggaltebar());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapterPakan != null) {
            adapterPakan.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterPakan != null) {
            adapterPakan.stopListening();
        }
    }

    private void datas() {
        sessions = new SharePreference(DetailPakan.this.getApplicationContext());
        String nama = sessions.getDatas();
        final String kolam = sessions.getDetailkolam();
        Petani = findViewById(R.id.dpetani1);
        Kolam = findViewById(R.id.dkolam1);
        Jumlah = findViewById(R.id.djumlah1);
        Tebar = findViewById(R.id.dtebar1);
        mDatebase.child(nama).addValueEventListener(new ValueEventListener() {
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
