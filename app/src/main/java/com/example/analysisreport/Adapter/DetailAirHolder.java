package com.example.analysisreport.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Model.ModelDetailAir;
import com.example.analysisreport.R;

public class DetailAirHolder extends RecyclerView.ViewHolder {

    public TextView stanggal;
    public TextView sedit;
    public TextView stinggiair;
    public TextView sdopagi;
    public TextView sdomalam;
    public TextView sphpgi;
    public TextView sphmalm;
    public TextView ssuhu;
    public TextView sca;
    public TextView smg;
    public TextView sno2;
    public TextView sno3;
    public TextView snh3;
    public TextView salkalinitas;
    Context context;

    public DetailAirHolder(@NonNull View itemView) {
        super(itemView);
        stanggal = itemView.findViewById(R.id.tanggalan);
        sedit = itemView.findViewById(R.id.edit);
        stinggiair = itemView.findViewById(R.id.tinggiair);
        sdopagi = itemView.findViewById(R.id.dopagi);
        sdomalam = itemView.findViewById(R.id.domalam);
        sphpgi = itemView.findViewById(R.id.phpagi);
        sphmalm = itemView.findViewById(R.id.phsore);
        ssuhu = itemView.findViewById(R.id.suuhu);
        sca =itemView.findViewById(R.id.ca);
        smg = itemView.findViewById(R.id.mg);
        sno2 = itemView.findViewById(R.id.no2);
        sno3 =itemView.findViewById(R.id.no3);
        snh3 = itemView.findViewById(R.id.nh3);
        salkalinitas = itemView.findViewById(R.id.alkalinitas);
    }

    public void bindoDetailair(ModelDetailAir modelDetailAir, View.OnClickListener onClickListener){
        stanggal.setText(modelDetailAir.tanggalairs);
        stinggiair.setText(modelDetailAir.tinggiairs);
        sdopagi.setText(modelDetailAir.dopagi);
        sdomalam.setText(modelDetailAir.domalam);
        sphpgi.setText(modelDetailAir.phpagis);
        sphmalm.setText(modelDetailAir.phmalams);
        ssuhu.setText(modelDetailAir.suhus);
        sca.setText(modelDetailAir.cas);
        smg.setText(modelDetailAir.mgs);
        sno2.setText(modelDetailAir.no2s);
        sno3.setText(modelDetailAir.no3s);
        snh3.setText(modelDetailAir.nh3s);
        salkalinitas.setText(modelDetailAir.alkalinitass);
        sedit.setOnClickListener(onClickListener);

    }


}
