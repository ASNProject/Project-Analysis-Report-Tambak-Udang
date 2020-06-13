package com.example.analysisreport.Adapter.DataDetailSampling;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Activity.MainActivity;
import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class PostAdapterSampling extends FirebaseRecyclerAdapter<RequestDataSampling,PostAdapterSampling.PostViewHolder> {

    private Context context;
    SharePreference sessions;
    public PostAdapterSampling(@NonNull FirebaseRecyclerOptions<RequestDataSampling> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull final PostViewHolder postViewHolder, final int i, @NonNull final RequestDataSampling requestDataSampling) {
        postViewHolder.stanggalsampling.setText(requestDataSampling.getTanggalsampling());
        postViewHolder.stanggaltebar.setText(requestDataSampling.getTanggaltebarsampling());
        postViewHolder.sjumlahtebarrata2.setText(requestDataSampling.getJumlahtebarsamplings());
        postViewHolder.smbw.setText(requestDataSampling.getMbw());
        postViewHolder.spakanperhari.setText(requestDataSampling.getPakanseharisampling());
        postViewHolder.stotalpakan.setText(requestDataSampling.getTotalpakansampling());
        postViewHolder.sfr.setText(requestDataSampling.getFr());
        postViewHolder.spopulasi.setText(requestDataSampling.getPopulasi());
        postViewHolder.sadgmingguan.setText(requestDataSampling.getAdgmingguan());
        postViewHolder.sbiomass.setText(requestDataSampling.getBiomass());
        postViewHolder.skonsumsifeed.setText(requestDataSampling.getKonsumsifeed());
        postViewHolder.sfcr.setText(requestDataSampling.getFcr());
//        postViewHolder.ssp.setText(requestDataSampling.getSp());
        postViewHolder.seditsampling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(context)
                        .setContentHolder(new ViewHolder(R.layout.detailsampling))
                        .setGravity(Gravity.CENTER)
                        .setExpanded(true, 1500)
                        .create();
                View posViewHolder = (LinearLayout)dialogPlus.getHolderView();
                final EditText stanggalterbarsampling = posViewHolder.findViewById(R.id.utanggaltebarsampling);
                final EditText stanggalsampling = posViewHolder.findViewById(R.id.utanggalsampling);
                final EditText sjumlahtebarratarata = posViewHolder.findViewById(R.id.ujumlahtebarratrata);
                final EditText smbw = posViewHolder.findViewById(R.id.umbw);
                final EditText spakanperhari = posViewHolder.findViewById(R.id.upakanperharisampling);
                final EditText stotalpakan = posViewHolder.findViewById(R.id.utotalpakansampling);
                final EditText sfr = posViewHolder.findViewById(R.id.ufr);
                final TextView spopulasi = posViewHolder.findViewById(R.id.upopulasi);
                final TextView sadgmingguan = posViewHolder.findViewById(R.id.uadgmingguan);
                final TextView sbiomass = posViewHolder.findViewById(R.id.ubiomass);
                final TextView ssp = posViewHolder.findViewById(R.id.usp);
                final TextView skonsumsifeed = posViewHolder.findViewById(R.id.ukonsumsifeed);
                final TextView sfcr = posViewHolder.findViewById(R.id.ufcr);
                final Button Simpan = posViewHolder.findViewById(R.id.ubutoonsimpansampling);
                stanggalsampling.setText(requestDataSampling.getTanggalsampling());
                stanggalterbarsampling.setText(requestDataSampling.getTanggaltebarsampling());
                sjumlahtebarratarata.setText(requestDataSampling.getJumlahtebarsamplings());
                smbw.setText(requestDataSampling.getMbw());
                spakanperhari.setText(requestDataSampling.getPakanseharisampling());
                stotalpakan.setText(requestDataSampling.getTotalpakansampling());
                sfr.setText(requestDataSampling.getFr());
                spopulasi.setText(requestDataSampling.getPopulasi());
                sadgmingguan.setText(requestDataSampling.getAdgmingguan());
                sbiomass.setText(requestDataSampling.getBiomass());
                skonsumsifeed.setText(requestDataSampling.getKonsumsifeed());
                sfcr.setText(requestDataSampling.getFcr());
                ssp.setText(requestDataSampling.getSp());
                Simpan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sessions = new SharePreference(context.getApplicationContext());
                        Map<String, Object> map = new HashMap<>();
                        map.put("tanggalsampling", stanggalsampling.getText().toString());
                        map.put("tanggaltebarsampling", stanggalterbarsampling.getText().toString());
                        map.put("jumlahtebarsamplings", sjumlahtebarratarata.getText().toString());
                        map.put("mbw", smbw.getText().toString());
                        map.put("pakanseharisampling", spakanperhari.getText().toString());
                        map.put("totalpakansampling", stotalpakan.getText().toString());
                        map.put("fr", sfr.getText().toString());
                        map.put("populasi", spopulasi.getText().toString());
                        map.put("adgmingguan", sadgmingguan.getText().toString());
                        map.put("biomass", sbiomass.getText().toString());
                        map.put("konsumsifeed", skonsumsifeed.getText().toString());
                        map.put("fcr", sfcr.getText().toString());
                        sessions = new SharePreference(context.getApplicationContext());
                        final String nama = sessions.getDatas();
                        final String kolam = sessions.getDetailkolam();
                        FirebaseDatabase.getInstance().getReference().child(nama)
                                .child(kolam).child("Sampling").child(getRef(i).getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                });
                        FirebaseDatabase.getInstance().getReference()
                                .child(nama).child(kolam).child("Samplingupdate")
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {

                                    }
                                });
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                        alertDialogBuilder.setTitle("Notice!");
                        alertDialogBuilder.setMessage("Yakin untuk merubah data?")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(final DialogInterface dialog, int which) {
                                        String lamambw = sessions.getMbw();
                                        spakanperhari.getText().toString();
                                        sfr.getText().toString();
                                        double pakanperhari = Double.parseDouble(spakanperhari.getText().toString());
                                        double fr = Double.parseDouble(sfr.getText().toString());
                                        double mbw = Double.parseDouble(smbw.getText().toString());
                                        double jmltebar = Double.parseDouble(sjumlahtebarratarata.getText().toString());
                                        double totalpakan = Double.parseDouble(stotalpakan.getText().toString());
                                        double mbwlama = Double.parseDouble(lamambw);
                                        double adgs = Double.parseDouble(sadgmingguan.getText().toString());
                                        double biomass = pakanperhari/(fr/100);
                                        if (Double.isNaN(biomass)){
                                            biomass = 0.0;
                                        }
                                        double hasilpopulasi = (1000/mbw)*biomass;
                                        if (Double.isNaN(hasilpopulasi)){
                                            hasilpopulasi = 0.0;
                                        }
                                        double hasilsp = (hasilpopulasi/jmltebar)*100;
                                        if (Double.isNaN(hasilsp)){
                                            hasilsp = 0.0;
                                        }
                                        double hasilfeed = (mbw*fr*jmltebar)/100000;
                                        if (Double.isNaN(hasilfeed)){
                                            hasilfeed = 0.0;
                                        }
                                        double hasilfcr = totalpakan/biomass;
                                        if (Double.isNaN(hasilfcr)){
                                            hasilfcr = 0.0;
                                        }
                                        double hasiladg = ((mbw-mbwlama)/6)+adgs;
                                        if (Double.isNaN(hasiladg)){
                                            hasiladg = 0.0;
                                        }
                                        String hasilbiomass = String.format("%.3f", biomass);
                                        String populasis= String.format("%.3f", hasilpopulasi);
                                        String sp = String.format("%.3f", hasilsp);
                                        String konsumsifeed = String.format("%.3f", hasilfeed);
                                        String fcr = String.format("%.3f", hasilfcr);
                                        String adg = String.format("%.1f",hasiladg);
                                        Map<String, Object> map = new HashMap<>();
                                        map.put("biomass", hasilbiomass);
                                        map.put("populasi", populasis);
                                        map.put("sp", sp);
                                        map.put("konsumsifeed", konsumsifeed);
                                        map.put("fcr", fcr);
                                        map.put("adgmingguan", adg);
                                        FirebaseDatabase.getInstance().getReference().child(nama)
                                                .child(kolam).child("Sampling").child(getRef(i).getKey())
                                                .updateChildren(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                     dialogPlus.dismiss();
                                                    }
                                                });
                                        FirebaseDatabase.getInstance().getReference()
                                                .child(nama).child(kolam).child("Samplingupdate")
                                                .updateChildren(map)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {

                                                    }
                                                });
                                        Intent i = new Intent(context.getApplicationContext(), MainActivity.class);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);
                                        sessions.setMbw(String.valueOf(mbw));

                                    }
                                });
                        AlertDialog alertDialog=alertDialogBuilder.create();
                        alertDialog.show();
                    }
                });
                dialogPlus.show();
            }
        });
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_detail_sampling, parent, false);
        return new PostViewHolder(view);
    }

    class PostViewHolder extends RecyclerView.ViewHolder{
         TextView stanggalsampling;
         TextView seditsampling;
         TextView stanggaltebar;
         TextView sjumlahtebarrata2;
         TextView smbw;
         TextView spakanperhari;
         TextView stotalpakan;
         TextView stanggalpakan;
         TextView sfr;
         TextView spopulasi;
         TextView sadgmingguan;
         TextView sbiomass;
         TextView skonsumsifeed;
         TextView sfcr;
         TextView ssp;
         Button sbuttongambarsampling;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            stanggalsampling = itemView.findViewById(R.id.tanggalsampling);
            seditsampling = itemView.findViewById(R.id.editsampling);
            stanggaltebar = itemView.findViewById(R.id.tanggaltebar);
            sjumlahtebarrata2 =itemView.findViewById(R.id.jumlahtebarrata2);
            smbw = itemView.findViewById(R.id.mgw);
            spakanperhari = itemView.findViewById(R.id.pakanperhari);
            stotalpakan = itemView.findViewById(R.id.totolpakan);
            stanggalpakan = itemView.findViewById(R.id.tanggalpakan);
            sfr = itemView.findViewById(R.id.fr);
            spopulasi =itemView.findViewById(R.id.populasi);
            sadgmingguan = itemView.findViewById(R.id.adgmingguan);
            sbiomass = itemView.findViewById(R.id.biomass);
            skonsumsifeed = itemView.findViewById(R.id.konsumsifeed);
            sfcr = itemView.findViewById(R.id.fcr);
            sbuttongambarsampling = itemView.findViewById(R.id.buttongambarsampling);
        }
    }
}
