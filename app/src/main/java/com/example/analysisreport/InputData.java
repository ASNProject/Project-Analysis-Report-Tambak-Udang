package com.example.analysisreport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputData extends AppCompatActivity {
    private EditText kolamnama, tnamapetani, tjumlah, tarea, tgltebar, jmltebarsampling, keterangankolam, //Kolam
                    DOpagi,Domalam,tanggalair,tinggiair, phpgi, phmalam, kecerahan, alkalinitas, suhu, ca, mg, no2,no3,nh3, //Data air
                    ptanggalpakan, pkodepakan, pjam6, pjam10, pjam14, pjam18, pjam22, pketerangan, //Data pakan
                    pberat, psize, ptanggalpanen, //Data panen
                    ptanggaltebarsampling, ptanggalsampling, pjumlahtebarrataratasampling, pmbwsampling, ppakanseharisampling, ptotalpakansampling, pfr,
                    pperlakuan, ptanggalperlakuan;


    private Button btnSimpan;
    private TextView jmltebarrata2, kepedatan , pjumlahharian, pjumlahtotal,
                        ppopulasi, padgmingguan, pbiomass, psp, pkonsumsifeed, pfcr;
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
        String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

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

        //Data panen
        pberat = findViewById(R.id.isiberat);
        psize = findViewById(R.id.isisize);
        ptanggalpanen = findViewById(R.id.tanggalpanen);

        //Data Sampling
        ptanggaltebarsampling = findViewById(R.id.ntanggaltebarsampling);
        ptanggalsampling = findViewById(R.id.ntangalsampling);
        pjumlahtebarrataratasampling = findViewById(R.id.njumlahtebarrataratasampling);
        pmbwsampling = findViewById(R.id.nmbwsampling);
        ppakanseharisampling = findViewById(R.id.npakanperharisampling);
        ptotalpakansampling = findViewById(R.id.ntotalpakansampling);
        pfr =findViewById(R.id.nfr);
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
                hitung1(Integer.parseInt(jumlah), Integer.parseInt(jumlahtebarsampling));
                String jumlahtebarratarata = jmltebarrata2.getText().toString();
                hitung2(Integer.parseInt(jumlah), Integer.parseInt(area));
                String kepadatankolam = kepedatan.getText().toString();

                //Data pakan
                String tanggalpakan = ptanggalpakan.getText().toString();
                String kodepakan = pkodepakan.getText().toString();
                String jam6 = pjam6.getText().toString();
                String jam10 = pjam10.getText().toString();
                String jam14 = pjam14.getText().toString();
                String jam18 = pjam18.getText().toString();
                String jam22 = pjam22.getText().toString();
                String keteranganpakan = pketerangan.getText().toString();
                String jumlahtotal = pjumlahtotal.getText().toString();

                hitung3(Integer.parseInt(jam6), Integer.parseInt(jam10), Integer.parseInt(jam14), Integer.parseInt(jam18), Integer.parseInt(jam22));
                String jumlahharian = pjumlahharian.getText().toString();

                //Data panen
                String berat = pberat.getText().toString();
                String size = psize.getText().toString();
                String tangalpanen = ptanggalpanen.getText().toString();

                //Data Sampling
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

                //Data Perlakuan
                String perlakuan = pperlakuan.getText().toString();
                String tanggaperlakuan = ptanggalperlakuan.getText().toString();

                //Program upload
                datakolam(new RequestDataKolam(kolam.toLowerCase(),namapetani.toLowerCase(),jumlah.toLowerCase(),area.toLowerCase(),tanggaltebar.toLowerCase(),jumlahtebarsampling.toLowerCase(),jumlahtebarratarata,
                        kepadatankolam.toLowerCase(),keterangankolams.toLowerCase()));
                dataair(new RequestDataAir(tanggalairs.toLowerCase(),tinggiairs.toLowerCase(),dopagi.toLowerCase(),domalam.toLowerCase(),phpagis.toLowerCase(),
                            phmalams.toLowerCase(),kecerahans.toLowerCase(),alkalinitass.toLowerCase(),suhus.toLowerCase(),cas.toLowerCase(),mgs.toLowerCase(),no2s.toLowerCase(),no3s.toLowerCase(),nh3s.toLowerCase()));
                datapakan(new RequestDataPakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                            keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase()));
                datapanen(new RequestDataPanen(berat.toLowerCase(),size.toLowerCase(),tangalpanen.toLowerCase()));
                datasampling(new RequestDataSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                                mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                                adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase()));
                dataperlakuan(new RequestDataPerlakuan(perlakuan.toLowerCase(), tanggaperlakuan.toLowerCase()));

                updatedataair(new RequestUpdateAir(tanggalairs.toLowerCase(),tinggiairs.toLowerCase(),dopagi.toLowerCase(),domalam.toLowerCase(),phpagis.toLowerCase(),
                        phmalams.toLowerCase(),kecerahans.toLowerCase(),alkalinitass.toLowerCase(),suhus.toLowerCase(),cas.toLowerCase(),mgs.toLowerCase(),no2s.toLowerCase(),no3s.toLowerCase(),nh3s.toLowerCase()));
                updatedatapakan(new RequestUpdatePakan(tanggalpakan.toLowerCase(),kodepakan.toLowerCase(),jam6.toLowerCase(),jam10.toLowerCase(),jam14.toLowerCase(),jam18.toLowerCase(),jam22.toLowerCase(),
                        keteranganpakan.toLowerCase(),jumlahharian.toLowerCase(),jumlahtotal.toLowerCase()));
                updatedatapenen(new RequestUpdatePanen(berat.toLowerCase(),size.toLowerCase(),tangalpanen.toLowerCase()));
                updatedatasampling(new RequestUpdateSampling(tanggaltebarsampling.toLowerCase(), tanggalsampling.toLowerCase(), jumlahtebarsamplings.toLowerCase(),
                        mbw.toLowerCase(), pakanseharisampling.toLowerCase(), totalpakansampling.toLowerCase(), fr.toLowerCase(), populasi.toLowerCase(),
                        adgmingguan.toLowerCase(), biomass.toLowerCase(), sp.toLowerCase(), konsumsifeed.toLowerCase(), fcr.toLowerCase()));
                dataperlakuan(new RequestDataPerlakuan(perlakuan.toLowerCase(), tanggaperlakuan.toLowerCase()));

                Intent i = new Intent(InputData.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    private void hitung1(int jumlah, int jumlahtebarsampling){
        int jmltebarratarata = ((jumlah-jumlahtebarsampling)/2);
        jmltebarrata2.setText(String.valueOf(jmltebarratarata));
    }
    private void hitung2(int jumlah, int area){
        int hasilkepadatan = jumlah/area;
        kepedatan.setText(String.valueOf(hasilkepadatan));
    }
    private void hitung3(int jam6, int jam10, int jam14, int jam18, int jam22){
        int jumlahpakanharian = jam6+jam10+jam14+jam18+jam22;
        pjumlahharian.setText(String.valueOf(jumlahpakanharian));
    }
    private void hitungpopulasi(int mbw, int biomass){
        int hasilpopulasi = mbw*biomass;
        ppopulasi.setText(String.valueOf(hasilpopulasi));
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
