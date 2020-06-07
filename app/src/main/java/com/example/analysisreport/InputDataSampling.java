package com.example.analysisreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.Model.RequestUpdateSampling;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputDataSampling extends AppCompatActivity {
    private EditText  ptanggaltebarsampling, ptanggalsampling, pjumlahtebarrataratasampling, pmbwsampling, ppakanseharisampling, ptotalpakansampling, pfr;
    private Button btnSimpan;
    private TextView  ppopulasi, padgmingguan, pbiomass, psp, pkonsumsifeed, pfcr;
    private DatabaseReference mDatabase;
    SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_sampling);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        ptanggaltebarsampling = findViewById(R.id.itanggaltebarsampling);
        ptanggalsampling = findViewById(R.id.itanggalsampling);
        pjumlahtebarrataratasampling = findViewById(R.id.ijumlahtebarratrata);
        pmbwsampling = findViewById(R.id.imbw);
        ppakanseharisampling = findViewById(R.id.ipakanperharisampling);
        ptotalpakansampling = findViewById(R.id.itotalpakansampling);
        pfr =findViewById(R.id.ifr);
        ppopulasi = findViewById(R.id.ipopulasi);
        padgmingguan = findViewById(R.id.iadgmingguan);
        pbiomass = findViewById(R.id.ibiomass);
        psp = findViewById(R.id.isp);
        pkonsumsifeed = findViewById(R.id.ikonsumsifeed);
        pfcr = findViewById(R.id.ifcr);

        btnSimpan = findViewById(R.id.ibutoonsimpansampling);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggaltebarsampling = ptanggaltebarsampling.getText().toString();
                String tanggalsampling = ptanggalsampling.getText().toString();
                String jumlahtebarsamplings = pjumlahtebarrataratasampling.getText().toString();
                String mbw = pmbwsampling.getText().toString();
                String pakanseharisampling = ppakanseharisampling.getText().toString();
                String totalpakansampling = ptotalpakansampling.getText().toString();
                String fr = pfr.getText().toString();
                String populasi = ppopulasi.getText().toString();
                String adgmingguan = padgmingguan.getText().toString();
                String biomass = pbiomass.getText().toString();
                String sp = psp.getText().toString();
                String konsumsifeed = pkonsumsifeed.getText().toString();
                String fcr = pfcr.getText().toString();

                datasampling(new RequestDataSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                        mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                        adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase()));

                updatedatasampling(new RequestUpdateSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                        mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                        adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase()));
                Intent i = new Intent(InputDataSampling.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
    //Hitung



    private void datasampling(RequestDataSampling requestDataSampling){
        sessions = new SharePreference(InputDataSampling.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Sampling")
                .push()
                .setValue(requestDataSampling)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatasampling(RequestUpdateSampling requestUpdateSampling){
        sessions = new SharePreference(InputDataSampling.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Samplingupdate")
                .setValue(requestUpdateSampling)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
}
