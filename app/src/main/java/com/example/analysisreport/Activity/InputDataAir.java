package com.example.analysisreport.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.analysisreport.Model.RequestDataAir;
import com.example.analysisreport.Model.RequestUpdateAir;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputDataAir extends AppCompatActivity {
    private EditText DOpagi,Domalam,tanggalair,tinggiair, phpgi, phmalam, kecerahan, alkalinitas, suhu, ca, mg, no2,no3,nh3; //Data air
    private Button Simpan;
    private DatabaseReference mDatabase;
    SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_air);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

        tanggalair = findViewById(R.id.itanggalair);
        tanggalair.setText(date_n);
        tinggiair = findViewById(R.id.itinggiair);
        DOpagi = findViewById(R.id.idopagi);
        Domalam = findViewById(R.id.idomalam);
        phpgi = findViewById(R.id.iphpagi);
        phmalam = findViewById(R.id.iphmalam);
        kecerahan = findViewById(R.id.ikecerahan);
        alkalinitas = findViewById(R.id.ialkalinitas);
        suhu = findViewById(R.id.isuhu);
        ca =  findViewById(R.id.ica);
        mg =  findViewById(R.id.img);
        no2 = findViewById(R.id.ino2);
        no3 = findViewById(R.id.ino3);
        nh3 = findViewById(R.id.inh3);
        Simpan = findViewById(R.id.ibutoonsimpanair);



        Simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggalairs = tanggalair.getText().toString();
                String tinggiairs = tinggiair.getText().toString();
                String dopagi = DOpagi.getText().toString();
                String domalam = Domalam.getText().toString();
                String phpagis = phpgi.getText().toString();
                String phmalams = phmalam.getText().toString();
                String kecerahans = kecerahan.getText().toString();
                String alkalinitass = alkalinitas.getText().toString();
                String suhus = suhu.getText().toString();
                String cas = ca.getText().toString();
                String mgs = mg.getText().toString();
                String no2s = no2.getText().toString();
                String no3s = no3.getText().toString();
                String nh3s = nh3.getText().toString();
                dataair(new RequestDataAir(tanggalairs.toLowerCase(),tinggiairs.toLowerCase(),dopagi.toLowerCase(),domalam.toLowerCase(),phpagis.toLowerCase(),
                        phmalams.toLowerCase(),kecerahans.toLowerCase(),alkalinitass.toLowerCase(),suhus.toLowerCase(),cas.toLowerCase(),mgs.toLowerCase(),no2s.toLowerCase(),no3s.toLowerCase(),nh3s.toLowerCase()));
                updatedataair(new RequestUpdateAir(tanggalairs.toLowerCase(),tinggiairs.toLowerCase(),dopagi.toLowerCase(),domalam.toLowerCase(),phpagis.toLowerCase(),
                        phmalams.toLowerCase(),kecerahans.toLowerCase(),alkalinitass.toLowerCase(),suhus.toLowerCase(),cas.toLowerCase(),mgs.toLowerCase(),no2s.toLowerCase(),no3s.toLowerCase(),nh3s.toLowerCase()));
                Intent i = new Intent(InputDataAir.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void dataair(final RequestDataAir requestDataAir){
        sessions = new SharePreference(InputDataAir.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
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
        sessions = new SharePreference(InputDataAir.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = sessions.getDetailkolam();
        mDatabase.child(data)
                .child(tnamakolam)
                .child("Airupdate")
                .setValue(requestUpdateAirAir)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

    }
}
