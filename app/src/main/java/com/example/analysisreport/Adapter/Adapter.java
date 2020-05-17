package com.example.analysisreport.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.analysisreport.Model.Report;
import com.example.analysisreport.R;




import java.util.List;

public class Adapter extends PagerAdapter {

    Context context;
    List<Report> reportList;
    LayoutInflater inflater;

    public Adapter(Context context, List<Report> reportList) {
        this.context = context;
        this.reportList = reportList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return reportList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //Inflate view
        View view = inflater.inflate(R.layout.view_pager_item,container,false);

        //View
        TextView suhu = (TextView)view.findViewById(R.id.nilaisuhu);
        TextView DOksigen = (TextView)view.findViewById(R.id.nilaido);
        TextView nilaiph = (TextView)view.findViewById(R.id.nilaiph);
        TextView nilaisalinitas = (TextView)view.findViewById(R.id.nilaisalinitas);
        TextView kecerahan = (TextView)view.findViewById(R.id.nilaikecerahan);
        TextView tglpakan = (TextView)view.findViewById(R.id.tglpakan);
        TextView nilaijumlahpakan = (TextView)view.findViewById(R.id.nilaijumlahpakan);
        TextView catatanpakan = (TextView)view.findViewById(R.id.catatanpakan);
        TextView tglsampling = (TextView)view.findViewById(R.id.tglsampling);
        TextView nilaiabw  = (TextView)view.findViewById(R.id.nilaiabw);
        TextView nilaiadg = (TextView)view.findViewById(R.id.nilaiadg);
        TextView tglpanen = (TextView)view.findViewById(R.id.tglpanen);
        TextView nilaiberat = (TextView)view.findViewById(R.id.beratpanen);
        TextView nilaisize = (TextView)view.findViewById(R.id.sizepanen);
        TextView nilaiperlakuan = (TextView)view.findViewById(R.id.nilaiperlakuan);
        TextView tglperlakuan = (TextView)view.findViewById(R.id.tglperlakuan);
        Button input1 = (Button)view.findViewById(R.id.inputdata);
        Button input2 = (Button)view.findViewById(R.id.inputdata1);
        Button input3 = (Button)view.findViewById(R.id.inputdata2);
        Button input4 = (Button)view.findViewById(R.id.inputdata3);
        Button input5 = (Button)view.findViewById(R.id.inputdata4);
        Button detail1 = (Button)view.findViewById(R.id.detail);
        Button detail2 = (Button)view.findViewById(R.id.detail1);
        Button detail3 = (Button)view.findViewById(R.id.detail2);
        Button detail4 = (Button)view.findViewById(R.id.detail3);
        Button detail5 = (Button)view.findViewById(R.id.detail4);

        //Set Data
        suhu.setText(reportList.get(position).getNilaiSuhu());
        DOksigen.setText(reportList.get(position).getDOksigen());
        nilaiph.setText(reportList.get(position).getPH());
        nilaisalinitas.setText(reportList.get(position).getNilaiSalinitas());
        kecerahan.setText(reportList.get(position).getNilaiKecerahan());
        tglpakan.setText(reportList.get(position).getTglPakan());
        nilaijumlahpakan.setText(reportList.get(position).getNilaiJumlah());
        catatanpakan.setText(reportList.get(position).getNilaiCatatan());
        tglsampling.setText(reportList.get(position).getTglSampling());
        nilaiabw.setText(reportList.get(position).getABW());
        nilaiadg.setText(reportList.get(position).getADG());
        tglpanen.setText(reportList.get(position).getTglPanen());
        nilaiberat.setText(reportList.get(position).getNilaiBerat());
        nilaisize.setText(reportList.get(position).getNilaiSize());
        nilaiperlakuan.setText(reportList.get(position).getNilaiPerlakuan());
        tglperlakuan.setText(reportList.get(position).getTglPerlakuan());

        //Event
        input1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        input2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        input3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        input4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        input5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        detail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        detail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        detail3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        detail4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        detail5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        container.addView(view);
        return view;
    }
}

