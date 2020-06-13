package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.example.analysisreport.Adapter.DataDetailPanen.PostAdapterPanen;
import com.example.analysisreport.Model.ModelDataPanen;
import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataPanen;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailPanen extends AppCompatActivity {
    TextView petani,kolams,luas,tanggal,jumlah,tebar,ratarata,kepadatan;

    private DatabaseReference mDatebase;
    private RecyclerView mRecycler;
    private LinearLayoutManager mManager;

    SharePreference sessions;
    private Context context;
    private PostAdapterPanen adapterPanen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_panen);
        sessions = new SharePreference(DetailPanen.this.getApplicationContext());
        String data = sessions.getDatas();
        String kolam = sessions.getDetailkolam();
        mDatebase = FirebaseDatabase.getInstance().getReference();
        mRecycler = findViewById(R.id.list_detai_panen);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<RequestDataPanen> options =
                new FirebaseRecyclerOptions.Builder<RequestDataPanen>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child(data).child(kolam)
                .child("Panen"), RequestDataPanen.class).build();
    adapterPanen = new PostAdapterPanen(options);
    mRecycler.setAdapter(adapterPanen);

    mManager = new LinearLayoutManager(this);
    mManager.setReverseLayout(true);
    mManager.setStackFromEnd(true);
    mRecycler.setLayoutManager(mManager);
    data();
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (adapterPanen != null) {
            adapterPanen.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapterPanen != null) {
            adapterPanen.stopListening();
        }
    }
    private void data(){
        sessions = new SharePreference(DetailPanen.this.getApplicationContext());
        String nama = sessions.getDatas();
        final String kolam = sessions.getDetailkolam();
        petani = findViewById(R.id.dpetanipanen);
        kolams = findViewById(R.id.dkolampanens);
        jumlah = findViewById(R.id.djumlahpanen);
        tebar = findViewById(R.id.dtebarpanen);
        ratarata = findViewById(R.id.dratarata);
        luas = findViewById(R.id.dluaspanen);
        tanggal = findViewById(R.id.dtanggaltebarpanen);
        kepadatan =findViewById(R.id.dkepadatan);
        mDatebase.child(nama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestDataKolam requestDataKolam = dataSnapshot.child(kolam).getValue(RequestDataKolam.class);
                petani.setText(requestDataKolam.getNamapetani());
                kolams.setText(requestDataKolam.getKolam());
                jumlah.setText(requestDataKolam.getJumlah());
                tebar.setText(requestDataKolam.getJumlahtebarsampling());
                ratarata.setText(requestDataKolam.getJumlahtebarratarata());
                luas.setText(requestDataKolam.getArea());
                tanggal.setText(requestDataKolam.getTanggaltebar());
                kepadatan.setText(requestDataKolam.getKepadatankolam());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
