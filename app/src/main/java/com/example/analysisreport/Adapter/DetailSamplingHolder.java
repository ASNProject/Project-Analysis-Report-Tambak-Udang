package com.example.analysisreport.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Model.ModelDetailSampling;
import com.example.analysisreport.R;

public class DetailSamplingHolder extends RecyclerView.ViewHolder {
    public TextView stanggalsampling;
    public TextView seditsampling;
    public TextView stanggaltebar;
    public TextView sjumlahtebarrata2;
    public TextView smbw;
    public TextView spakanperhari;
    public TextView stotalpakan;
    public TextView stanggalpakan;
    public TextView sfr;
    public TextView spopulasi;
    public TextView sadgmingguan;
    public TextView sbiomass;
    public TextView skonsumsifeed;
    public TextView sfcr;
    public TextView ssp;
    public Button sbuttongambarsampling;


    public DetailSamplingHolder(@NonNull View itemView) {
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

    public void bindoToDetailSampling(ModelDetailSampling modelDetailSampling, View.OnClickListener onClickListener){
        stanggalsampling.setText(modelDetailSampling.tanggalsampling);
        seditsampling.setOnClickListener(onClickListener);
        stanggaltebar.setText(modelDetailSampling.tanggaltebarsampling);
        //sjumlahtebarrata2.setText(modelDetailSampling.);
        //smbw
        spakanperhari.setText(modelDetailSampling.pakanseharisampling);
        stotalpakan.setText(modelDetailSampling.totalpakansampling);
        //stanggalpakan.setText();
        sfr.setText(modelDetailSampling.fr);
        spopulasi.setText(modelDetailSampling.populasi);
        sadgmingguan.setText(modelDetailSampling.adgmingguan);
        //sbiomass.setText();
        skonsumsifeed.setText(modelDetailSampling.konsumsifeed);
        sfcr.setText(modelDetailSampling.fcr)
        ;
    }
}
