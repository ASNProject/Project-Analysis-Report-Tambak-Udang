package com.example.analysisreport.Activity;

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

import com.example.analysisreport.Model.RequestDataAir;
import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestDataPakan;
import com.example.analysisreport.Model.RequestDataPanen;
import com.example.analysisreport.Model.RequestDataPerlakuan;
import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.Model.RequestHasilPanen;
import com.example.analysisreport.Model.RequestUpdateAir;
import com.example.analysisreport.Model.RequestUpdatePakan;
import com.example.analysisreport.Model.RequestUpdatePanen;
import com.example.analysisreport.Model.RequestUpdatePerlakuan;
import com.example.analysisreport.Model.RequestUpdateSampling;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class InputData extends AppCompatActivity {
    private EditText kolamnama, tnamapetani, tjumlah, tarea, tgltebar, jmltebarsampling, keterangankolam, //Kolam
                    DOpagi,Domalam,tanggalair,tinggiair, phpgi, phmalam, kecerahan, alkalinitas, suhu, ca, mg, no2,no3,nh3, //Data air
                    ptanggalpakan, pkodepakan, pjam6, pjam10, pjam14, pjam18, pjam22, pketerangan, //Data pakan
                    pberat, psize, ptanggalpanen, //Data panen
                    ptanggaltebarsampling, ptanggalsampling, pjumlahtebarrataratasampling, pmbwsampling, ppakanseharisampling, ptotalpakansampling, pfr,
                    pperlakuan, ptanggalperlakuan;


    private Button btnSimpan;
    private TextView jmltebarrata2, kepedatan , pjumlahharian, pjumlahtotal,pakanfeed,pusia,
                        ppopulasi, padgmingguan, pbiomass, psp, pkonsumsifeed, pfcr;
    private DatabaseReference database, mdatabase;
    SharePreference sessions;
    private FirebaseAuth mAuth;
    private long mBackPassed;
    private static final int TIME_INTERVAL = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data);
        database = FirebaseDatabase.getInstance().getReference();
        mdatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        sessions = new SharePreference(InputData.this.getApplicationContext());

        //Data Kolam
        kolamnama = (EditText) findViewById(R.id.kolamnama);
        tnamapetani = (EditText) findViewById(R.id.namapetani);
        tjumlah = (EditText) findViewById(R.id.jumlah);
        tarea = (EditText) findViewById(R.id.area);
        tgltebar = (EditText) findViewById(R.id.ntangaltebar);
        tgltebar.setText(date_n);
        jmltebarsampling = (EditText) findViewById(R.id.jumlahtebarsamplinglapangan);
        jmltebarrata2 = (TextView) findViewById(R.id.jumlahtebarrata2ekor);
        kepedatan = (TextView)findViewById(R.id.kepadatanperm2);
        keterangankolam = (EditText)findViewById(R.id.keterangankolam);
        pakanfeed = (TextView)findViewById(R.id.pakanfeed);

        //Data air
        tanggalair = findViewById(R.id.tanggalair);
        tanggalair.setText(date_n);
        tinggiair = findViewById(R.id.ntinggiair);
        DOpagi = findViewById(R.id.DOpagi);
        Domalam = findViewById(R.id.DOmalam);
        phpgi = findViewById(R.id.phpagi);
        phmalam = findViewById(R.id.phmalam);
        kecerahan = findViewById(R.id.kecerahan);
        alkalinitas = findViewById(R.id.salinitas);
        suhu = findViewById(R.id.suhu);
        ca =  findViewById(R.id.nca);
        mg =  findViewById(R.id.nmg);
        no2 = findViewById(R.id.nno2);
        no3 = findViewById(R.id.nno3);
        nh3 = findViewById(R.id.nnh3);

        //Data pakan
        ptanggalpakan = findViewById(R.id.tanggalpakan);
        ptanggalpakan.setText(date_n);
        pkodepakan = findViewById(R.id.nkodepakan);
        pjam6 = findViewById(R.id.njam6);
        pjam10 = findViewById(R.id.njam10);
        pjam14 = findViewById(R.id.njam14);
        pjam18 = findViewById(R.id.njam18);
        pjam22 = findViewById(R.id.njam22);
        pketerangan = findViewById(R.id.isicatatan);
        pjumlahharian = findViewById(R.id.jumlahharian);
        pjumlahtotal = findViewById(R.id.isijumlahtotal);
        pusia = findViewById(R.id.usiaa);

        //Data Sampling
        ptanggaltebarsampling = findViewById(R.id.ntanggaltebarsampling);
        ptanggalsampling = findViewById(R.id.ntangalsampling);
        ptanggalsampling.setText(date_n);
        pjumlahtebarrataratasampling = findViewById(R.id.njumlahtebarrataratasampling);
        pmbwsampling = findViewById(R.id.nmbwsampling);
        ppakanseharisampling = findViewById(R.id.npakanperharisampling);
        ptotalpakansampling = findViewById(R.id.ntotalpakansampling);
        pfr = findViewById(R.id.nfr);
        ppopulasi = findViewById(R.id.npopulasi);
        padgmingguan = findViewById(R.id.nadgmingguan);
        pbiomass = findViewById(R.id.nbiomass);
        psp = findViewById(R.id.nsp);
        pkonsumsifeed = findViewById(R.id.nkonsumsifeed);
        pfcr = findViewById(R.id.nfcr);

        //Perlaukuan
        pperlakuan = findViewById(R.id.nperlakuan);
        ptanggalperlakuan = findViewById(R.id.tanggalperlakuan);
        ptanggalperlakuan.setText(date_n);

        btnSimpan = findViewById(R.id.simpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kolam = kolamnama.getText().toString();
                if (kolam.equals("")){
                    kolamnama.setError("Email is required");
                    kolamnama.requestFocus();
                    return;
                }
                String namapetani = tnamapetani.getText().toString();
                if (namapetani.equals("")){
                    tnamapetani.setError("Email is required");
                    tnamapetani.requestFocus();
                    return;
                }
                String jumlah = tjumlah.getText().toString();
                if (jumlah.equals("")){
                    tjumlah.setError("Email is required");
                    tjumlah.requestFocus();
                    return;
                }
                String area = tarea.getText().toString();
                if (area.equals("")){
                    tarea.setError("Email is required");
                    tarea.requestFocus();
                    return;
                }
                String tanggaltebar = tgltebar.getText().toString();
                if (tanggaltebar.equals("")){
                    tgltebar.setError("Email is required");
                    tgltebar.requestFocus();
                    return;
                }
                String jumlahtebarsampling = jmltebarsampling.getText().toString();
                if (jumlahtebarsampling.equals("")){
                    jmltebarsampling.setError("Email is required");
                    jmltebarsampling.requestFocus();
                    return;
                }
                String keterangankolams = keterangankolam.getText().toString();
                if (keterangankolams.equals("")){
                    keterangankolam.setError("Email is required");
                    keterangankolam.requestFocus();
                    return;
                }

                hitung1(Double.parseDouble(jumlah), Double.parseDouble(jumlahtebarsampling));
                String jumlahtebarratarata = jmltebarrata2.getText().toString();
                hitung2(Double.parseDouble(jumlahtebarratarata), Double.parseDouble(area));
                String kepadatankolam = kepedatan.getText().toString();
                String feedpakan = pakanfeed.getText().toString();

                //Dataair
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
                //Perhitungan data air


                //Data pakan
                String tanggalpakan = ptanggalpakan.getText().toString();
                String kodepakan = pkodepakan.getText().toString();
                String jam6 = pjam6.getText().toString();
                String jam10 = pjam10.getText().toString();
                String jam14 = pjam14.getText().toString();
                String jam18 = pjam18.getText().toString();
                String jam22 = pjam22.getText().toString();
                String keteranganpakan = pketerangan.getText().toString();
                hitung3(Double.parseDouble(jam6), Double.parseDouble(jam10), Double.parseDouble(jam14), Double.parseDouble(jam18), Double.parseDouble(jam22));
                String jumlahharian = pjumlahharian.getText().toString();
                hitung4(Double.parseDouble(jumlahharian), Double.parseDouble(feedpakan));
                String jumlahtotal = pjumlahtotal.getText().toString();

                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(date_n);
                    Date tglakhir = (Date) date.parse(tanggaltebar);

                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    String i = String.valueOf(bedaHari);
                    pusia.setText(String.valueOf(i));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String usia = pusia.getText().toString();

                //Data Sampling
                String tanggaltebarsampling = ptanggaltebarsampling.getText().toString();
                String tanggalsampling = ptanggalsampling.getText().toString();
                String jumlahtebarsamplings = pjumlahtebarrataratasampling.getText().toString();
                String mbw = pmbwsampling.getText().toString();
                String pakanseharisampling = ppakanseharisampling.getText().toString();
                String totalpakansampling = ptotalpakansampling.getText().toString();
                String fr = pfr.getText().toString();
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
                hitungadg(Double.parseDouble(mbw));
                String adgmingguan = padgmingguan.getText().toString();


                //Data Perlakuan
                String perlakuan = pperlakuan.getText().toString();
                String tanggaperlakuan = ptanggalperlakuan.getText().toString();

                String utanggalpanen = "";
                String udoc = "0";
                String utonase = "0";
                String uabw = "0";
                String usize = "0";
                String upopulasipanen= "0";

                String tonha ="0";
                String totalpopulasi = "0";
                String panentotal = "0";
                String totalsr = "0";
                String totalpakan = "0";
                String fcrtotal = "0";

                //Program upload
                datakolam(new RequestDataKolam(kolam.toLowerCase(),namapetani.toLowerCase(),jumlah.toLowerCase(),area.toLowerCase(),tanggaltebar.toLowerCase(),jumlahtebarsampling.toLowerCase(),jumlahtebarratarata,
                        kepadatankolam.toLowerCase(),keterangankolams.toLowerCase(),jumlahtotal.toLowerCase()));
                dataair(new RequestDataAir(tanggalairs.toLowerCase(),tinggiairs.toLowerCase(),dopagi.toLowerCase(),domalam.toLowerCase(),phpagis.toLowerCase(),
                            phmalams.toLowerCase(),kecerahans.toLowerCase(),alkalinitass.toLowerCase(),suhus.toLowerCase(),cas.toLowerCase(),mgs.toLowerCase(),no2s.toLowerCase(),no3s.toLowerCase(),nh3s.toLowerCase()));
                datapakan(new RequestDataPakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                            keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase(),usia.toLowerCase()));
                datasampling(new RequestDataSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                                mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                                adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase(), usia.toLowerCase()));
                dataperlakuan(new RequestDataPerlakuan(perlakuan.toLowerCase(), tanggaperlakuan.toLowerCase()));
                datapanen(new RequestHasilPanen(tonha.toLowerCase(), totalpopulasi.toLowerCase(), panentotal.toLowerCase(), totalsr.toLowerCase(), totalpakan.toLowerCase(), fcrtotal.toLowerCase()));


                updatedataair(new RequestUpdateAir(tanggalairs.toLowerCase(),tinggiairs.toLowerCase(),dopagi.toLowerCase(),domalam.toLowerCase(),phpagis.toLowerCase(),
                        phmalams.toLowerCase(),kecerahans.toLowerCase(),alkalinitass.toLowerCase(),suhus.toLowerCase(),cas.toLowerCase(),mgs.toLowerCase(),no2s.toLowerCase(),no3s.toLowerCase(),nh3s.toLowerCase()));
                updatedatapakan(new RequestUpdatePakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                        keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase()));
                updatedatasampling(new RequestUpdateSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                        mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                        adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase()));
                dataperlakuan(new RequestDataPerlakuan(perlakuan.toLowerCase(), tanggaperlakuan.toLowerCase()));
                updatedatapenen(new RequestUpdatePanen(utanggalpanen.toLowerCase(), udoc.toLowerCase(), utonase.toLowerCase(), uabw.toLowerCase(),usize.toLowerCase(), upopulasipanen.toLowerCase()));

                Intent i = new Intent(InputData.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mBackPassed + TIME_INTERVAL> System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }
        else {
           showDialog();
        }
    }
    private void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Notice!");
        alertDialogBuilder.setMessage("Mohon lengkapi Data Kolam terlebih dahulu! Kemudian klik simpan.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    }
                });
        AlertDialog alertDialog=alertDialogBuilder.create();
        alertDialog.show();
    }

    private void hitung1(double jumlah, double jumlahtebarsampling){
        final double jmltebarratarata = ((jumlah+jumlahtebarsampling)/2);
        jmltebarrata2.setText(String.format("%.1f", jmltebarratarata));
    }
    private void hitung2(double jumlahtebarratarata, double area){
        double hasilkepadatan = jumlahtebarratarata/area;
        kepedatan.setText(String.format("%.1f", hasilkepadatan));
    }
    private void hitung3(double jam6, double jam10, double jam14, double jam18, double jam22){
        double jumlahpakanharian = jam6+jam10+jam14+jam18+jam22;
        pjumlahharian.setText(String.valueOf(jumlahpakanharian));
    }
    private void hitung4(double jumlahharian, double feedpakan){
        double totalkonsumsipakan = jumlahharian+feedpakan;
        pjumlahtotal.setText(String.valueOf(totalkonsumsipakan));
        pakanfeed.setText(String.valueOf(totalkonsumsipakan));
    }
    private void hitungpopulasi(double mbw, double biomass){
        double hasilpopulasi = (1000/mbw)*biomass;
        if (Double.isNaN(hasilpopulasi)){
            hasilpopulasi = 0.0;
        }
        ppopulasi.setText(String.format("%.3f", hasilpopulasi));

    }
    private void hitungbiomass(double pakanperharisampling, double fr){
        double jumlahbiomas= pakanperharisampling/(fr/100);
        if (Double.isNaN(jumlahbiomas)){
            jumlahbiomas = 0.0;
        }
        pbiomass.setText(String.format("%.1f", jumlahbiomas));
    }
    private void hitungsp(double hasilpopulasi, double jumlahtebarsampling){
        double jumlahsp = (hasilpopulasi/jumlahtebarsampling)*100;
        if (Double.isNaN(jumlahsp)){
            jumlahsp = 0.0;
        }
        psp.setText(String.format("%.1f", jumlahsp));
    }
    private void hitungkonsumsifeed(double mbw, double fr, double jumlahtebarsamplings){
        double jumlahkonsumsifeed = (mbw * fr * jumlahtebarsamplings)/100000;
        if (Double.isNaN(jumlahkonsumsifeed)){
            jumlahkonsumsifeed = 0.0;
        }
        pkonsumsifeed.setText(String.format("%.1f", jumlahkonsumsifeed));
    }
    private void hitungfcr(double totalpakansampling, double biomass){
        double hitungfcr = totalpakansampling/biomass;
        if (Double.isNaN(hitungfcr)){
            hitungfcr = 0.0;
        }
        pfcr.setText(String.format("%.3f", hitungfcr));
    }
    private void hitungadg(double mbw){
        double hasilagd = (mbw-0)/6;
        if (Double.isNaN(hasilagd)){
            hasilagd = 0.0;
        }
        padgmingguan.setText(String.format("%.3f", hasilagd));
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
                              //  Toast.makeText(InputData.this,
                                //        "Data Berhasil ditambahkan",
                                  //      Toast.LENGTH_SHORT).show();
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
    private void datapanen(RequestHasilPanen requestHasilPanen){
        sessions = new SharePreference(InputData.this.getApplicationContext());
        String data = sessions.getDatas();
        String tnamakolam = kolamnama.getText().toString().toLowerCase();
        database.child(data)
                .child(tnamakolam)
                .child("Hasilpanen")
                .setValue(requestHasilPanen)
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
