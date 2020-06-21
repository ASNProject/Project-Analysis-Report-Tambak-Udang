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
import android.widget.Toast;

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InputDataPakan extends AppCompatActivity {

    private EditText ptanggalpakan, pkodepakan, pjam6, pjam10, pjam14, pjam18, pjam22, pketerangan;
    private TextView pjumlahharian, pjumlahtotal, pusia, ptanggaltebar;
    private Button Simpan;
    private DatabaseReference mDatabase;
    private SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_pakan);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final  String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        sessions = new SharePreference(InputDataPakan.this.getApplicationContext());

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
        pusia = findViewById(R.id.iusia);
        ptanggaltebar = findViewById(R.id.tangggaaal);
        Simpan = findViewById(R.id.ibutoonsimpanpakan);


        mDatabase.child(sessions.getDatas()).child(sessions.getDetailkolam()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestDataKolam requestDataKolam = dataSnapshot.getValue(RequestDataKolam.class);
                String tanggaltebars = requestDataKolam.getTanggaltebar();
                ptanggaltebar.setText(tanggaltebars);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


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
                final String feedpakan = sessions.getPakanfeed();


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(InputDataPakan.this);
                alertDialogBuilder.setTitle("Notice!");
                alertDialogBuilder.setMessage("Yakin untuk menyimpan data?")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tanggaltebar = ptanggaltebar.getText().toString();
                                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                                try {
                                    Date tglawal = (Date) date.parse(date_n);
                                    Date tglakhir = (Date) date.parse(tanggaltebar);

                                    long bedaHari = Math.abs((tglawal.getTime() - tglakhir.getTime())/86400000);
                                    String i = String.valueOf(bedaHari);
                                    pusia.setText(String.valueOf(i));

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                String usia = pusia.getText().toString();

                                hitung3(Double.parseDouble(jam6), Double.parseDouble(jam10), Double.parseDouble(jam14), Double.parseDouble(jam18), Double.parseDouble(jam22));
                                String jumlahharian = pjumlahharian.getText().toString();
                                hitung4(Double.parseDouble(jumlahharian), Double.parseDouble(feedpakan));
                                String jumlahtotal = pjumlahtotal.getText().toString();

                                datapakan(new RequestDataPakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                                        keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase(), usia.toLowerCase()));
                                updatedatapakan(new RequestUpdatePakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                                        keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase()));

                                Intent i = new Intent(InputDataPakan.this, MainActivity.class);
                                   startActivity(i);
                                   finish();
                            }
                        });
                AlertDialog alertDialog=alertDialogBuilder.create();
                alertDialog.show();

              //  Intent i = new Intent(InputDataPakan.this, MainActivity.class);
             //   startActivity(i);
             //   finish();
            }
        });
    }
    private void hitung3(double jam6, double jam10, double jam14, double jam18, double jam22){
        double jumlahpakanharian = jam6+jam10+jam14+jam18+jam22;
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
