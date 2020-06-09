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
import com.example.analysisreport.Model.RequestDataPakan;
import com.example.analysisreport.Model.RequestUpdatePakan;
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
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InputDataPakan extends AppCompatActivity {

    private EditText ptanggalpakan, pkodepakan, pjam6, pjam10, pjam14, pjam18, pjam22, pketerangan;
    private TextView pjumlahharian, pjumlahtotal;
    private Button Simpan;
    private DatabaseReference mDatabase;
    private SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_pakan);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        ptanggalpakan = findViewById(R.id.itanggalpakan);
        ptanggalpakan.setText(date_n);
        pkodepakan = findViewById(R.id.ikodepakan);
        pjam6 = findViewById(R.id.ijam6);
        pjam10 = findViewById(R.id.ijam10);
        pjam14 = findViewById(R.id.ijam14);
        pjam18 = findViewById(R.id.ijam18);
        pjam22 = findViewById(R.id.ijam22);
        pketerangan = findViewById(R.id.iisicatatan);
        pjumlahharian = findViewById(R.id.ijumlahharian);
        pjumlahtotal = findViewById(R.id.itotal);
        Simpan = findViewById(R.id.ibutoonsimpanpakan);

        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessions = new SharePreference(InputDataPakan.this.getApplicationContext());
                final String data = sessions.getDatas();
                final String tnamakolam = sessions.getDetailkolam();
                final String tanggalpakan = ptanggalpakan.getText().toString();
                final String kodepakan = pkodepakan.getText().toString();
                final String jam6 = pjam6.getText().toString();
                final String jam10 = pjam10.getText().toString();
                final String jam14 = pjam14.getText().toString();
                final String jam18 = pjam18.getText().toString();
                final String jam22 = pjam22.getText().toString();
                final String keteranganpakan = pketerangan.getText().toString();
                String feedpakan = sessions.getPakanfeed();

                        hitung3(Integer.parseInt(jam6), Integer.parseInt(jam10), Integer.parseInt(jam14), Integer.parseInt(jam18), Integer.parseInt(jam22));
                        String jumlahharian = pjumlahharian.getText().toString();
                        hitung4(Double.parseDouble(jumlahharian), Double.parseDouble(feedpakan));
                        String jumlahtotal = pjumlahtotal.getText().toString();

                        datapakan(new RequestDataPakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                                keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase()));
                        updatedatapakan(new RequestUpdatePakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                                keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase()));

                Intent i = new Intent(InputDataPakan.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void hitung3(int jam6, int jam10, int jam14, int jam18, int jam22){
        int jumlahpakanharian = jam6+jam10+jam14+jam18+jam22;
        pjumlahharian.setText(String.valueOf(jumlahpakanharian));
    }
    private void hitung4(double jumlahharian, double feedpakan){
        final double totalkonsumsipakan = jumlahharian+feedpakan;
        pjumlahtotal.setText(String.valueOf(totalkonsumsipakan));
    }
    private void datapakan(RequestDataPakan requestDataPakan){
        sessions = new SharePreference(InputDataPakan.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
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
        sessions = new SharePreference(InputDataPakan.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Pakanupdate")
                .setValue(requestUpdatePakan)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }

}