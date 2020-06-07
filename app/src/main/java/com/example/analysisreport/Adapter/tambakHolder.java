package com.example.analysisreport.Adapter;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.analysisreport.Model.LISTTambak;
import com.example.analysisreport.Model.Tambak;
import com.example.analysisreport.R;

public class tambakHolder extends RecyclerView.ViewHolder {

    public TextView tvTambak;
    public Button btnTambak;
    public ImageView delete;

    public tambakHolder(@NonNull View itemView) {
        super(itemView);
        tvTambak = itemView.findViewById(R.id.isi);
        btnTambak = itemView.findViewById(R.id.pilih);
        delete = itemView.findViewById(R.id.deletelist);
    }
    public void bindoToTambak(LISTTambak ltambak, View.OnClickListener onClickListener){
        tvTambak.setText(ltambak.nama);
        btnTambak.setOnClickListener(onClickListener);
    }
    public void cindoToTambak(LISTTambak ltambak, View.OnClickListener onClickListener)
    {
        delete.setOnClickListener(onClickListener);
    }
}
