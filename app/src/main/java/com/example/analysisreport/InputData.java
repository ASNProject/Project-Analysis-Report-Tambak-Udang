package com.example.analysisreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.analysisreport.Model.Request;
import com.example.analysisreport.Model.RequestDataAir;
import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataPakan;
import com.example.analysisreport.Model.RequestDataPanen;
import com.example.analysisreport.Model.RequestDataPerlakuan;
import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.Model.RequestUpdateAir;
import com.example.analysisreport.Model.RequestUpdatePakan;
import com.example.analysisreport.Model.RequestUpdatePanen;
import com.example.analysisreport.Model.RequestUpdatePerlakuan;
import com.example.analysisreport.Model.RequestUpdateSampling;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputData extends AppCompatActivity {
    private EditText kolamnama, tlokasi, tjumlah, tarea,
                    DO, ph, kecerahan, salinitas, suhu,
                    isijumlah, tanggalpakan, isicatatan,
                    isiberat, isisize, tanggalpanen,
                    isiabw, isiadg, tanggalsampling,
                    nperlakuan, tanggalperlakuan;
    private Button btnSimpan;

    private DatabaseReference database, mdatabase;
    SharePreference sessions;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        database = FirebaseDatabase.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        kolamnama = (EditText) findViewById(R.id.kolamnama);
        tlokasi = (EditText) findViewById(R.id.lokasi);
        tjumlah = (EditText) findViewById(R.id.jumlah);
        tarea = (EditText) findViewById(R.id.area);
        DO = findViewById(R.id.DO);
        ph = findViewById(R.id.ph);
        kecerahan = findViewById(R.id.kecerahan);
        salinitas = findViewById(R.id.salinitas);
        suhu = findViewById(R.id.suhu);
        isijumlah = findViewById(R.id.isijumlah);
        tanggalpakan = findViewById(R.id.tanggalpakan);
        isicatatan = findViewById(R.id.isicatatan);
        isiberat = findViewById(R.id.isiberat);
        isisize = findViewById(R.id.isisize);
        tanggalpanen = findViewById(R.id.tanggalpanen);
        isiabw = findViewById(R.id.isiabw);
        isiadg = findViewById(R.id.isiadg);
        tanggalsampling = findViewById(R.id.tanggalsampling);
        nperlakuan = findViewById(R.id.nperlakuan);
        tanggalperlakuan = findViewById(R.id.tanggalperlakuan);
        btnSimpan = findViewById(R.id.simpan);


        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kolam = kolamnama.getText().toString();
                String lokasi = tlokasi.getText().toString();
                String jumlah = tjumlah.getText().toString();
                String area = tarea.getText().toString();
                String dO = DO.getText().toString();
                String pH = ph.getText().toString();
                String kec = kecerahan.getText().toString();
                String sal = salinitas.getText().toString();
                String suh = suhu.getText().toString();
                String jum = isijumlah.getText().toString();
                String tglpak = tanggalpakan.getText().toString();
                String cat = isicatatan.getText().toString();
                String ber = isiberat.getText().toString();
                String siz = isisize.getText().toString();
                String tglpan = tanggalpanen.getText().toString();
                String abw = isiabw.getText().toString();
                String adg = isiadg.getText().toString();
                String samp = tanggalsampling.getText().toString();
                String per = nperlakuan.getText().toString();
                String tglper = tanggalperlakuan.getText().toString();

                datakolam(new RequestDataKolam(kolam.toLowerCase(), lokasi.toLowerCase(), jumlah.toLowerCase(), area.toLowerCase()));
                dataair(new RequestDataAir(dO.toLowerCase(), pH.toLowerCase(), kec.toLowerCase(), sal.toLowerCase(), suh.toLowerCase()));
                datapakan(new RequestDataPakan(jum.toLowerCase(), tglpak.toLowerCase(), cat.toLowerCase()));
                datapanen(new RequestDataPanen(ber.toLowerCase(), siz.toLowerCase(), tglpan.toLowerCase()));
                datasampling(new RequestDataSampling(abw.toLowerCase(), adg.toLowerCase(),samp.toLowerCase()));
                dataperlakuan(new RequestDataPerlakuan(per.toLowerCase(), tglper.toLowerCase()));

                updatedataair(new RequestUpdateAir(dO.toLowerCase(), pH.toLowerCase(), kec.toLowerCase(), sal.toLowerCase(), suh.toLowerCase()));
                updatedatapakan(new RequestUpdatePakan(jum.toLowerCase(), tglpak.toLowerCase(), cat.toLowerCase()));
                updatedatapenen(new RequestUpdatePanen(ber.toLowerCase(), siz.toLowerCase(), tglpan.toLowerCase()));
                updatedatasampling(new RequestUpdateSampling(abw.toLowerCase(), adg.toLowerCase(),samp.toLowerCase()));
                updatedataperlakuan(new RequestUpdatePerlakuan(per.toLowerCase(), tglper.toLowerCase()));

                Intent i = new Intent(InputData.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void datakolam(RequestDataKolam requestDataKolam){
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
                database.child(data)
                        .child(tnamakolam)
                        .setValue(requestDataKolam)
                        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(InputData.this,
                                        "Data Berhasil ditambahkan",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

    }
    private void dataair(RequestDataAir requestDataAir){
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        database.child(data)
                .child(tnamakolam)
                .child("Air")
                .push()
                .setValue(requestDataAir)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void updatedataair(RequestUpdateAir requestUpdateAirAir){
        final FirebaseUser user = mAuth.getCurrentUser();
        String das = user.getDisplayName();
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
                mdatabase.child(data)
                        .child(tnamakolam)
                        .child("Airupdate")
                        .setValue(requestUpdateAirAir)
                        .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void datapakan(RequestDataPakan requestDataPakan){
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        database.child(data)
                .child(tnamakolam)
                .child("Pakan")
                .push()
                .setValue(requestDataPakan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatapakan(RequestUpdatePakan requestUpdatePakan){
        final FirebaseUser user = mAuth.getCurrentUser();
        String das = user.getDisplayName();
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        mdatabase.child(data)
                .child(tnamakolam)
                .child("Pakanupdate")
                .setValue(requestUpdatePakan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void datapanen(RequestDataPanen requestDataPanen){
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        database.child(data)
                .child(tnamakolam)
                .child("Panen")
                .push()
                .setValue(requestDataPanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedatapenen(RequestUpdatePanen requestUpdatePanen){
        final FirebaseUser user = mAuth.getCurrentUser();
        String das = user.getDisplayName();
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        mdatabase.child(data)
                .child(tnamakolam)
                .child("Panenupdate")
                .setValue(requestUpdatePanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void datasampling(RequestDataSampling requestDataSampling){
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        database.child(data)
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
        final FirebaseUser user = mAuth.getCurrentUser();
        String das = user.getDisplayName();
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        mdatabase.child(data)
                .child(tnamakolam)
                .child("Samplingupdate")
                .setValue(requestUpdateSampling)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void dataperlakuan(RequestDataPerlakuan requestDataPerlakuan){
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        database.child(data)
                .child(tnamakolam)
                .child("Perlakuan")
                .push()
                .setValue(requestDataPerlakuan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
    }
    private void updatedataperlakuan(RequestUpdatePerlakuan requestUpdatePerlakuan){
        final FirebaseUser user = mAuth.getCurrentUser();
        String das = user.getDisplayName();
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        mdatabase.child(data)
                .child(tnamakolam)
                .child("Perlakuanupdate")
                .setValue(requestUpdatePerlakuan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }

}
