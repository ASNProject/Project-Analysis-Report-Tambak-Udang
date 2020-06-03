package com.example.analysisreport.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.analysisreport.InputData;
import com.example.analysisreport.ListTambak;
import com.example.analysisreport.MainActivity;
import com.example.analysisreport.Model.RequestDataAir;
import com.example.analysisreport.Model.RequestDataKolam;
import com.example.analysisreport.Model.RequestUpdateAir;
import com.example.analysisreport.Model.RequestUpdatePakan;
import com.example.analysisreport.Model.RequestUpdatePanen;
import com.example.analysisreport.Model.RequestUpdatePerlakuan;
import com.example.analysisreport.Model.RequestUpdateSampling;
import com.example.analysisreport.Model.Tambak;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MyAdapter extends PagerAdapter {

    private static final String TAG = "Custom";
    private String KEY_NAME = "username";
    private String username;
    private Button btnTambah;
    AlertDialog.Builder dialog;
    View dialogView;
    SharePreference sessions;
    private TextView namatambak, namakolam, getsuhu, getsalinitas,
            getkecerahan, getph, getdoksigen;
    private String GetUserID;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase getDatabase;
    private DatabaseReference getRefenence;



    Context context;
    List<Tambak> tambakList;
    LayoutInflater inflater;

    public MyAdapter(Context context, List<Tambak> tambakList) {
        this.context = context;
        this.tambakList = tambakList;
        inflater = LayoutInflater.from(context);

    }


    @Override
    public int getCount() {
        return tambakList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, int position) {
        //Inflate view
        final View view = inflater.inflate(R.layout.view_pager_layout,container,false);

        //View
         final TextView namakolam = (TextView)view.findViewById(R.id.namakolam);
        final TextView namatambak = (TextView)view.findViewById(R.id.namatambak);
        //Set Data
        namakolam.setText(tambakList.get(position).getKolam());
        String datas = namakolam.getText().toString();

        final TextView jumlahudang = (TextView)view.findViewById(R.id.jumlahudang);
        final TextView hariudang = (TextView)view.findViewById(R.id.hariudang);
        final TextView namapetani = (TextView)view.findViewById(R.id.mnamapetani);
        final TextView area = (TextView)view.findViewById(R.id.marea);
        final TextView tanggalkolam = (TextView)view.findViewById(R.id.mtanggalkolam);

        //Data Air
        final TextView Tanggalair = (TextView)view.findViewById(R.id.ttanggalair);
        final TextView Tinggiair = (TextView)view.findViewById(R.id.ttinggiair);
        final TextView DOpagi = (TextView)view.findViewById(R.id.tdopagi);
        final TextView DOmalam = (TextView)view.findViewById(R.id.tdomalam);
        final TextView PHpagi = (TextView)view.findViewById(R.id.tphpagi);
        final TextView PHmalam = (TextView)view.findViewById(R.id.tphmalam);
        final TextView Kecerahan = (TextView)view.findViewById(R.id.tkecerahan);
        final TextView Alkalinitas = (TextView)view.findViewById(R.id.talkalinitas);
        final TextView Suhu = (TextView)view.findViewById(R.id.tsuhu);
        final TextView Ca = (TextView)view.findViewById(R.id.tca);
        final TextView Mg = (TextView)view.findViewById(R.id.Mg);
        final TextView NO2 = (TextView)view.findViewById(R.id.tno2);
        final TextView NO3 = (TextView)view.findViewById(R.id.tno3);
        final TextView NH3 = (TextView)view.findViewById(R.id.tnh3);

        //Data Pakan
        final TextView Tangalpakan = (TextView)view.findViewById(R.id.ttanggalpakan);
        final TextView Kodepakan = (TextView)view.findViewById(R.id.tkodepakan);
        final TextView Jam6 = (TextView)view.findViewById(R.id.tjam6);
        final TextView Jam10 = (TextView)view.findViewById(R.id.jam10);
        final TextView Jam14 = (TextView)view.findViewById(R.id.tjam14);
        final TextView Jam18 = (TextView)view.findViewById(R.id.tjam18);
        final TextView Jam22 = (TextView)view.findViewById(R.id.tjam22);
        final TextView Jumlahharian = (TextView)view.findViewById(R.id.tjumlahharian);
        final TextView Jumlahtotal = (TextView)view.findViewById(R.id.tjumlahtotal);
        final TextView Keterangan = (TextView)view.findViewById(R.id.tketeranganpakan);

        //Data Sampling
        final TextView TanggalSampling = (TextView)view.findViewById(R.id.ttanggalsampling);
        final TextView TanggalTebar = (TextView)view.findViewById(R.id.ttanggaltebar);
        final TextView Jumlahtebarratarata = (TextView)view.findViewById(R.id.tjumlahtebarratarata);
        final TextView Mbw = (TextView)view.findViewById(R.id.tmbw);
        final TextView Pakanperharisampling = (TextView)view.findViewById(R.id.tpakanperharisampling);
        final TextView Jumlahpakantotal = (TextView)view.findViewById(R.id.tjumlahpakantotal);
        final TextView FR = (TextView)view.findViewById(R.id.fr);
        final TextView Populasi = (TextView)view.findViewById(R.id.tpopulasi);
        final TextView Adgmingguan = (TextView)view.findViewById(R.id.tadgmingguan);
        final TextView Biomass = (TextView)view.findViewById(R.id.tbiomass);
        final TextView Sp = (TextView)view.findViewById(R.id.tsp);
        final TextView Konsumsifeed = (TextView)view.findViewById(R.id.tkonsumsifeed);
        final TextView Fcr = (TextView)view.findViewById(R.id.tfcr);

        //Data Panen
        final TextView Tanggalpanen = (TextView)view.findViewById(R.id.tanggalpanen);
        final TextView Berat = (TextView)view.findViewById(R.id.beratpanen);
        final TextView Size = (TextView)view.findViewById(R.id.sizepanen);

        //Perlakuan
        final TextView Tanggalperlakuan = (TextView)view.findViewById(R.id.tglperlakuan);
        final TextView Perlakuan = (TextView)view.findViewById(R.id.nilaiperlakuan);


        final TextView tambahkolam = (TextView)view.findViewById(R.id.tamabahkolam);
        final Button btnTambah = (Button)view.findViewById(R.id.buttontambah);

        tambahkolam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), InputData.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context.getApplicationContext(), ListTambak.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        sessions = new SharePreference(context.getApplicationContext());
        String nama = sessions.getDatas();
        namatambak.setText(nama);
        getDatabase = FirebaseDatabase.getInstance();
        getRefenence = getDatabase.getReference().child(nama);
        String kolamnama = namakolam.getText().toString();
       //Database getFirebase
        getRefenence = FirebaseDatabase.getInstance().getReference().child(nama);
        getRefenence.child(kolamnama).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RequestUpdateAir requestUpdateAir = dataSnapshot.child("Airupdate").getValue(RequestUpdateAir.class);
                Tanggalair.setText(requestUpdateAir.getTanggalairs());
                Tinggiair.setText(requestUpdateAir.getTinggiairs());
                DOpagi.setText(requestUpdateAir.getDopagi());
                DOmalam.setText(requestUpdateAir.getDomalam());
                PHpagi.setText(requestUpdateAir.getPhpagis());
                PHmalam.setText(requestUpdateAir.getPhmalams());
                Kecerahan.setText(requestUpdateAir.getKecerahans());
                Alkalinitas.setText(requestUpdateAir.getAlkalinitass());
                Suhu.setText(requestUpdateAir.getSuhus());
                Ca.setText(requestUpdateAir.getCas());
                Mg.setText(requestUpdateAir.getMgs());
                NO2.setText(requestUpdateAir.getNo2s());
                NO3.setText(requestUpdateAir.getNo3s());
                NH3.setText(requestUpdateAir.getNh3s());

                RequestUpdatePakan requestUpdatePakan = dataSnapshot.child("Pakanupdate").getValue(RequestUpdatePakan.class);
                Tangalpakan.setText(requestUpdatePakan.getTanggalpakan());
                Kodepakan.setText(requestUpdatePakan.getKodepakan());
                Jam6.setText(requestUpdatePakan.getJam6());
                Jam10.setText(requestUpdatePakan.getJam10());
                Jam14.setText(requestUpdatePakan.getJam14());
                Jam18.setText(requestUpdatePakan.getJam18());
                Jam22.setText(requestUpdatePakan.getJam22());
                Jumlahharian.setText(requestUpdatePakan.getJumlahharian());
                Jumlahtotal.setText(requestUpdatePakan.getJumlahtotal());
                Keterangan.setText(requestUpdatePakan.getKeteranganpakan());

                RequestUpdatePanen requestUpdatePanen = dataSnapshot.child("Panenupdate").getValue(RequestUpdatePanen.class);
                Berat.setText(requestUpdatePanen.getNberat());
                Size.setText(requestUpdatePanen.getNsize());
//                Tanggalpanen.setText(requestUpdatePanen.getNtanggalpenen());

                RequestUpdateSampling requestUpdateSampling = dataSnapshot.child("Samplingupdate").getValue(RequestUpdateSampling.class);
                TanggalSampling.setText(requestUpdateSampling.getTanggalsampling());
                TanggalTebar.setText(requestUpdateSampling.getTanggaltebarsampling());
                Jumlahtebarratarata.setText(requestUpdateSampling.getJumlahtebarsamplings());
                Mbw.setText(requestUpdateSampling.getMbw());
                Pakanperharisampling.setText(requestUpdateSampling.getPakanseharisampling());
                Jumlahpakantotal.setText(requestUpdateSampling.getTotalpakansampling());
//                FR.setText(requestUpdateSampling.getFr());
                Populasi.setText(requestUpdateSampling.getPopulasi());
                Adgmingguan.setText(requestUpdateSampling.getAdgmingguan());
                Biomass.setText(requestUpdateSampling.getBiomass());
                Sp.setText(requestUpdateSampling.getSp());
                Konsumsifeed.setText(requestUpdateSampling.getKonsumsifeed());
                Fcr.setText(requestUpdateSampling.getFcr());

                RequestUpdatePerlakuan requestUpdatePerlakuan = dataSnapshot.child("Perlakuanupdate").getValue(RequestUpdatePerlakuan.class);
//                Perlakuan.setText(requestUpdatePerlakuan.getNperlakuan());
//                Tanggalperlakuan.setText(requestUpdatePerlakuan.getNtanggalperlakuan());

                RequestDataKolam requestDataKolam = dataSnapshot.getValue(RequestDataKolam.class);
                jumlahudang.setText(requestDataKolam.getJumlah());
                namapetani.setText(requestDataKolam.getNamapetani());
                area.setText(requestDataKolam.getArea());
                tanggalkolam.setText(requestDataKolam.getTanggaltebar());
                String tanggal = requestDataKolam.getTanggaltebar();
                String date_n = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                try {
                    Date tglawal = (Date) date.parse(date_n);
                    Date tglakhir = (Date) date.parse(tanggal);

                    long bedaHari = Math.abs(tglawal.getTime() - tglakhir.getTime());
                    hariudang.setText(TimeUnit.MILLISECONDS.toDays(bedaHari) +"");
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        container.addView(view);
        return  view;
    }
}
