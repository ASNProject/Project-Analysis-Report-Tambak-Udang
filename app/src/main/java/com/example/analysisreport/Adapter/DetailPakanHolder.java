package com.example.analysisreport.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Model.ModelDataPakan;
import com.example.analysisreport.R;

public class DetailPakanHolder extends RecyclerView.ViewHolder {
    public TextView stanggalpakan;
    public TextView seditpakan;
    public TextView skodepakan;
    public TextView sjam6;
    public TextView sjam10;
    public TextView sjam14;
    public TextView sjam18;
    public TextView sjam22;
    public TextView stotalharianpakan;
    public TextView stotalpakansemua;
    public TextView sketeranganpakan;
    public Button sbuttongambar;


    public DetailPakanHolder(@NonNull View itemView) {
        super(itemView);
        stanggalpakan = itemView.findViewById(R.id.tanggalanpakan);
        seditpakan = itemView.findViewById(R.id.editpakan);
        skodepakan = itemView.findViewById(R.id.kodepakan);
        sjam6 = itemView.findViewById(R.id.jam6);
        sjam10 = itemView.findViewById(R.id.jam10);
        sjam14 = itemView.findViewById(R.id.jam14);
        sjam18 = itemView.findViewById(R.id.jam18);
        sjam22 = itemView.findViewById(R.id.jam22);
        stotalharianpakan = itemView.findViewById(R.id.totalharianpakan);
        stotalpakansemua = itemView.findViewById(R.id.totalpakansemua);
        sketeranganpakan = itemView.findViewById(R.id.keteranganpakan);
        sbuttongambar = itemView.findViewById(R.id.buttongambar);
    }
    public void bindoToDetailPakan(ModelDataPakan modelDataPakan, View.OnClickListener onClickListener){
        stanggalpakan.setText(modelDataPakan.tanggalpakan);
        seditpakan.setOnClickListener(onClickListener);
        skodepakan.setText(modelDataPakan.kodepakan);
        sjam6.setText(modelDataPakan.jam6);
        sjam10.setText(modelDataPakan.jam10);
        sjam14.setText(modelDataPakan.jam14);
        sjam18.setText(modelDataPakan.jam18);
        sjam22.setText(modelDataPakan.jam22);
        stotalharianpakan.setText(modelDataPakan.jumlahharian);
        stotalpakansemua.setText(modelDataPakan.jumlahtotal);
        sketeranganpakan.setText(modelDataPakan.keteranganpakan);
    }
}
