package com.example.analysisreport.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataPakan;
import com.example.analysisreport.Model.RequestDataPanen;
import com.example.analysisreport.Model.RequestHasilPanen;
import com.example.analysisreport.Model.RequestUpdatePakan;
import com.example.analysisreport.Model.RequestUpdatePanen;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InpuDataPanen extends AppCompatActivity {
    EditText ptanggalpanen, pdocpanen, pabwpanen, ptonase;
    Button Simpan;
    TextView psizepanen, ppopulasipanen, ptotalpanen, populasitotal, srpanen, pakanpanen, fcrpanen, ton, ambiltonase, ambilpopulasi, ambilluastambak, ambilpakan;
    DatabaseReference mDatabase;
    SharePreference sessions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inpu_data_panen);
        final String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        mDatabase = FirebaseDatabase.getInstance().getReference();
        sessions = new SharePreference(InpuDataPanen.this.getApplicationContext());

        ptanggalpanen = findViewById(R.id.itanggalpanen);
        ptanggalpanen.setText(date_n);
        pdocpanen = findViewById(R.id.idocpanen);
        ptonase = findViewById(R.id.itonase);
        pabwpanen = findViewById(R.id.iabwpanen);
        psizepanen = findViewById(R.id.isize);
        ppopulasipanen = findViewById(R.id.ipopulasipanen);
        ptotalpanen = findViewById(R.id.itotal);
        populasitotal = findViewById(R.id.ipopulasipanentotal);
        srpanen = findViewById(R.id.isrpanen);
        pakanpanen = findViewById(R.id.ipakanpanen);
        fcrpanen = findViewById(R.id.ifcrpanen);
        ton = findViewById(R.id.iton);
        ambiltonase = findViewById(R.id.aaatonase);
        ambilluastambak = findViewById(R.id.aaaluastambak);
        ambilpopulasi = findViewById(R.id.aaapopulasi);
        ambilpakan = findViewById(R.id.aaajumlahtebar);
        Simpan = findViewById(R.id.ibutoonsimpanpanen);
        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tanggalpanen = ptanggalpanen.getText().toString();
               final String doc = pdocpanen.getText().toString();
               final String tonase = ptonase.getText().toString();
               final String abw = pabwpanen.getText().toString();
                size(Double.parseDouble(abw));
               final String size = psizepanen.getText().toString();
                populasi(Double.parseDouble(abw), Double.parseDouble(tonase));
                final String populasi = ppopulasipanen.getText().toString();
                mDatabase.child(sessions.getDatas()).child(sessions.getDetailkolam()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        RequestHasilPanen requestHasilPanen = dataSnapshot.child("Hasilpanen").getValue(RequestHasilPanen.class);
                        ambiltonase.setText(requestHasilPanen.getPanentotal());
                        ambilpopulasi.setText(requestHasilPanen.getTotalpopulasi());
                        RequestDataKolam requestDataKolam = dataSnapshot.getValue(RequestDataKolam.class);
                        ambilluastambak.setText(requestDataKolam.getArea());
                        ambilpakan.setText(requestDataKolam.getJumlah());
                        RequestUpdatePakan requestUpdatePakan = dataSnapshot.child("Pakanupdate").getValue(RequestUpdatePakan.class);
                        pakanpanen.setText(requestUpdatePakan.getJumlahtotal());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InpuDataPanen.this);
                alertDialogBuilder.setTitle("Notice!");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String totalpakan = pakanpanen.getText().toString();
                                String atonase = ambiltonase.getText().toString();
                                String apopulasi = ambilpopulasi.getText().toString();
                                String jumlahtebar = ambilpakan.getText().toString();
                                String luastambak = ambilluastambak.getText().toString();
                                totalpanen(Double.parseDouble(atonase), Double.parseDouble(tonase));
                                String panentotal = ptotalpanen.getText().toString();
                                totalpopulasi(Double.parseDouble(apopulasi), Double.parseDouble(populasi));
                                String totalpopulasi = populasitotal.getText().toString();
                                totalsr(Double.parseDouble(jumlahtebar), Double.parseDouble(totalpopulasi));
                                String totalsr = srpanen.getText().toString();
                                totalfcr(Double.parseDouble(totalpakan), Double.parseDouble(panentotal));
                                String fcrtotal = fcrpanen.getText().toString();
                                totalton(Double.parseDouble(luastambak), Double.parseDouble(panentotal));
                                String tonha = ton.getText().toString();

                                updatehasilpanen(new RequestHasilPanen(tonha.toLowerCase(), totalpopulasi.toLowerCase(), panentotal
                                        .toLowerCase(), totalsr.toLowerCase(), totalpakan.toLowerCase(), fcrtotal.toLowerCase()));

                                datapanen(new RequestDataPanen(tanggalpanen.toLowerCase(), doc.toLowerCase(), tonase.toLowerCase(), abw.toLowerCase(),
                                        size.toLowerCase(), populasi.toLowerCase()));

                                updatedatapanen(new RequestUpdatePanen(tanggalpanen.toLowerCase(), doc.toLowerCase(), tonase.toLowerCase(), abw.toLowerCase(),
                                        size.toLowerCase(), populasi.toLowerCase()));
                                Intent i = new Intent(InpuDataPanen.this, MainActivity.class);
                                startActivity(i);
                                finish();
                            }

                        });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    private void size(double abw){
        double hasilsize = 1000/abw;
        if (Double.isNaN(hasilsize)){
            hasilsize = 0.0;
        }
        psizepanen.setText(String.format("%.0f", hasilsize));
    }

    private void populasi(double abw, double tonase){
        double hasilpopulasi = abw * tonase;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        ppopulasipanen.setText(String.format("%.0f", hasilpopulasi));
    }
    private void totalpanen(double atonase, double tonase){
        double hasiltotal = atonase+tonase;
        if (Double.isNaN(hasiltotal)){
            hasiltotal = 0.0;
        }
        ptotalpanen.setText(String.format("%.0f", hasiltotal));
    }
    private void totalpopulasi(double apopulasi, double populasi){
        double hasilpopulasi = apopulasi+populasi;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        populasitotal.setText(String.format("%.0f", hasilpopulasi));
    }
    private void totalsr(double jumlahtebar, double totalpopulasi){
        double hasilsr = (jumlahtebar/totalpopulasi)*100;
        if (Double.isNaN(hasilsr)){
            hasilsr = 0.0;
        }
        srpanen.setText(String.format("%.0f", hasilsr));
    }
    private void totalfcr(double totalpakan, double panentotal){
        double hasilfcr = totalpakan/panentotal;
        if (Double.isNaN(hasilfcr)){
            hasilfcr = 0.0;
        }
        fcrpanen.setText(String.format("%.0f", hasilfcr));
    }
    private void totalton(double luastambak, double panentotal){
        double hasilton = (10000/luastambak)*(panentotal/1000);
        if (Double.isNaN(hasilton)){
            hasilton = 0.0;
        }
        ton.setText(String.format("%.0f", hasilton));
    }

    private void datapanen(RequestDataPanen requestDataPanen){
        sessions = new SharePreference(InpuDataPanen.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
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
    private void updatedatapanen(RequestUpdatePanen requestUpdatePanen){
        sessions = new SharePreference(InpuDataPanen.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Panenupdate")
                .setValue(requestUpdatePanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
    private void updatehasilpanen(RequestHasilPanen requestHasilPanen){
        sessions = new SharePreference(InpuDataPanen.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Hasilpanen")
                .setValue(requestHasilPanen)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }


}
