package com.example.analysisreport.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.analysisreport.Model.RequestDataAir;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UpdateIsiAir extends AppCompatActivity {
    private DatabaseReference mDatabase;
    SharePreference sessions;
    private EditText DOpagi,Domalam,tanggalair,tinggiair, phpgi, phmalam, kecerahan, alkalinitas, suhu, ca, mg, no2,no3,nh3; //Data air
    private Button Simpan;
    private RequestDataAir requestDataAir;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_isi_air);

        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        tanggalair = findViewById(R.id.utanggalair);
        // tanggalair.setText(date_n);
        tinggiair = findViewById(R.id.utinggiair);
        DOpagi = findViewById(R.id.udopagi);
        Domalam = findViewById(R.id.udomalam);
        phpgi = findViewById(R.id.uphpagi);
        phmalam = findViewById(R.id.uphmalam);
        kecerahan = findViewById(R.id.ukecerahan);
        alkalinitas = findViewById(R.id.ualkalinitas);
        suhu = findViewById(R.id.usuhu);
        ca =  findViewById(R.id.uca);
        mg =  findViewById(R.id.umg);
        no2 = findViewById(R.id.uno2);
        no3 = findViewById(R.id.uno3);
        nh3 = findViewById(R.id.unh3);
        Simpan = findViewById(R.id.ubutoonsimpanisiair);

        mDatabase = FirebaseDatabase.getInstance().getReference();


        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateair(requestDataAir);

                Intent i = new Intent(UpdateIsiAir.this, DetailAir.class);
                startActivity(i);
                finish();
            }


        });

       /* if (requestDataAir != null){
            tanggalair.setText(requestDataAir.getTanggalairs());
            tinggiair.setText(requestDataAir.getTinggiairs());
            DOpagi.setText(requestDataAir.getDopagi());
            Domalam.setText(requestDataAir.getDomalam());
            phpgi.setText(requestDataAir.getPhpagis());
            phmalam.setText(requestDataAir.getPhmalams());
            kecerahan.setText(requestDataAir.getKecerahans());
            alkalinitas.setText(requestDataAir.getAlkalinitass());
            suhu.setText(requestDataAir.getSuhus());
            ca.setText(requestDataAir.getCas());
            mg.setText(requestDataAir.getMgs());
            no2.setText(requestDataAir.getNo2s());
            no3.setText(requestDataAir.getNo3s());
            nh3.setText(requestDataAir.getNh3s());


        } */
    }
    private void updateair(RequestDataAir requestDataAir) {
        sessions = new SharePreference(UpdateIsiAir.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Air")
                .child(requestDataAir.getKey())
                .setValue(requestDataAir)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
}
