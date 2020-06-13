package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.Model.RequestUpdateSampling;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputDataSampling extends AppCompatActivity {
    private EditText  ptanggaltebarsampling, ptanggalsampling, pjumlahtebarrataratasampling, pmbwsampling, ppakanseharisampling, ptotalpakansampling, pfr;
    private Button btnSimpan;
    private TextView  usia, ppopulasi, padgmingguan, pbiomass, psp, pkonsumsifeed, pfcr, tanggaltebar;
    private DatabaseReference mDatabase;
    SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_sampling);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sessions = new SharePreference(InputDataSampling.this.getApplicationContext());
        final  String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        ptanggaltebarsampling = findViewById(R.id.itanggaltebarsampling);
        ptanggalsampling = findViewById(R.id.itanggalsampling);
        ptanggalsampling.setText(date_n);
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
        tanggaltebar = findViewById(R.id.tangggaaaltebaar);
        usia = findViewById(R.id.iusias);
        mDatabase.child(sessions.getDatas()).child(sessions.getDetailkolam()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestDataKolam requestDataKolam = dataSnapshot.getValue(RequestDataKolam.class);
                String tanggaltebars = requestDataKolam.getTanggaltebar();
                tanggaltebar.setText(tanggaltebars);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnSimpan = findViewById(R.id.ibutoonsimpansampling);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tanggaltebarsampling = ptanggaltebarsampling.getText().toString();
                final String tanggalsampling = ptanggalsampling.getText().toString();
                final String jumlahtebarsamplings = pjumlahtebarrataratasampling.getText().toString();
                final String mbw = pmbwsampling.getText().toString();
                final String pakanseharisampling = ppakanseharisampling.getText().toString();
                final String totalpakansampling = ptotalpakansampling.getText().toString();
                final String fr = pfr.getText().toString();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InputDataSampling.this);
                alertDialogBuilder.setTitle("Notice");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tebar = tanggaltebar.getText().toString();
                                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                                try {
                                    Date tglawal = (Date) date.parse(date_n);
                                    Date tglakhir = (Date) date.parse(tebar);

                                    long bedaHari = Math.abs((tglawal.getTime() - tglakhir.getTime())/86400000);
                                    String i = String.valueOf(bedaHari);
                                    usia.setText(String.valueOf(i));
                                }
                             catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                hitungbiomass(Double.parseDouble(pakanseharisampling), Double.parseDouble(fr));
                                String biomass = pbiomass.getText().toString();
                                hitungpopulasi(Double.parseDouble(mbw), Double.parseDouble(biomass));
                                String populasi = ppopulasi.getText().toString();
                                hitungsp(Double.parseDouble(populasi), Double.parseDouble(jumlahtebarsamplings));
                                String sp = psp.getText().toString();
                                hitungkonsumsifeed(Double.parseDouble(mbw), Double.parseDouble(fr), Double.parseDouble(jumlahtebarsamplings));
                                String konsumsifeed = pkonsumsifeed.getText().toString();
                                hitungfcr(Double.parseDouble(totalpakansampling), Double.parseDouble(biomass));
                                String fcr = pfcr.getText().toString();
                                String lamambw = sessions.getMbw();
                                hitungadg(Double.parseDouble(mbw), Double.parseDouble(lamambw));
                                String adgmingguan = padgmingguan.getText().toString();
                                String usias =usia.getText().toString();

                                datasampling(new RequestDataSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                                        mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                                        adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase(), usias.toLowerCase()));

                                updatedatasampling(new RequestUpdateSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                                        mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                                        adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase()));
                                Intent i = new Intent(InputDataSampling.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();


            }
        });

    }
    private void hitungbiomass(double pakanperharisampling, double fr){
        double jumlahbiomas= pakanperharisampling/(fr/100);
        if (Double.isNaN(jumlahbiomas)){
            jumlahbiomas = 0.0;
        }
        pbiomass.setText(String.valueOf( jumlahbiomas));
    }
    private void hitungpopulasi(double mbw, double biomass){
        double hasilpopulasi = (1000/mbw)*biomass;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        ppopulasi.setText(String.valueOf( hasilpopulasi));
    }
    private void hitungsp(double hasilpopulasi, double jumlahtebarsampling){
        double jumlahsp = (hasilpopulasi/jumlahtebarsampling)*100;
        if (Double.isNaN(jumlahsp)){
            jumlahsp = 0.0;
        }
        psp.setText(String.valueOf( jumlahsp));
    }
    private void hitungkonsumsifeed(double mbw, double fr, double jumlahtebarsamplings){
        double jumlahkonsumsifeed = (mbw * fr * jumlahtebarsamplings)/100000;
        if (Double.isNaN(jumlahkonsumsifeed)){
            jumlahkonsumsifeed = 0.0;
        }
        pkonsumsifeed.setText(String.valueOf( jumlahkonsumsifeed));
    }
    private void hitungfcr(double totalpakansampling, double biomass){
        double hitungfcr = totalpakansampling/biomass;
        if (Double.isNaN(hitungfcr)){
            hitungfcr = 0.0;
        }
        pfcr.setText(String.valueOf(hitungfcr));
    }
    private void hitungadg(double mbw, double lamambw){
        double hasilagd = (mbw-lamambw)/6;
        if (Double.isNaN(hasilagd)){
            hasilagd = 0.0;
        }
        padgmingguan.setText(String.valueOf(hasilagd));
    }





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
