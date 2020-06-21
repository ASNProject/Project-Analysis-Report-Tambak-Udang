package com.example.analysisreport.Chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.analysisreport.Model.RequestDataSampling;
import com.example.analysisreport.R;
import com.example.analysisreport.SharePreference.SharePreference;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChartSampling extends AppCompatActivity {
    LineChart lineChart1, lineChart2, lineChart3;
    LineDataSet lineDataSet1 = new LineDataSet(null,null);
    LineDataSet lineDataSet2 = new LineDataSet(null,null);
    LineDataSet lineDataSet3 = new LineDataSet(null,null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    ArrayList<ILineDataSet> iLineDataSets2 = new ArrayList<>();
    ArrayList<ILineDataSet> iLineDataSets3 = new ArrayList<>();
    LineData lineData1;
    LineData lineData2;
    LineData lineData3;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef, myRef2, myRef3;
    SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_sampling);
        sessions = new SharePreference(ChartSampling.this.getApplicationContext());
        lineChart1 =findViewById(R.id.chartbiomass);
        lineChart2 = findViewById(R.id.chartsr);
        lineChart3 = findViewById(R.id.chartfr);
        firebaseDatabase = FirebaseDatabase.getInstance();
        line1();
        line2();
        line3();

    }

    private void line1(){
        myRef = firebaseDatabase.getReference().child(sessions.getDatas()).child(sessions.getDetailkolam()).child("Sampling");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> data1 = new ArrayList<Entry>();
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        RequestDataSampling requestDataSampling = dataSnapshot1.getValue(RequestDataSampling.class);
                        float xValue1 = Float.parseFloat(String.valueOf(requestDataSampling.getUsia()));
                        float yValue1 = Float.parseFloat(String.valueOf(requestDataSampling.getBiomass()));
                        data1.add(new Entry(xValue1, yValue1));
                    }
                    showChart1(data1);
                }
                else {
                    lineChart1.clear();
                    lineChart1.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void line2(){
        myRef2 = firebaseDatabase.getReference().child(sessions.getDatas()).child(sessions.getDetailkolam()).child("Sampling");
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> data2 = new ArrayList<Entry>();
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()){
                        RequestDataSampling requestDataSampling = dataSnapshot2.getValue(RequestDataSampling.class);
                        float xValue2 = Float.parseFloat(String.valueOf(requestDataSampling.getUsia()));
                        float yValue2 = Float.parseFloat(String.valueOf(requestDataSampling.getSp()));
                        data2.add(new Entry(xValue2, yValue2));
                    }
                    showChart2(data2);
                }
                else {
                    lineChart2.clear();
                    lineChart2.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void line3(){
        myRef3 = firebaseDatabase.getReference().child(sessions.getDatas()).child(sessions.getDetailkolam()).child("Sampling");
        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> data3 = new ArrayList<Entry>();
                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot dataSnapshot3 : dataSnapshot.getChildren()){
                        RequestDataSampling requestDataSampling = dataSnapshot3.getValue(RequestDataSampling.class);
                        float xValue3 = Float.parseFloat(String.valueOf(requestDataSampling.getUsia()));
                        float yValue3 = Float.parseFloat(String.valueOf(requestDataSampling.getFcr()));
                        data3.add(new Entry(xValue3, yValue3));
                    }
                    showChart3(data3);
                }
                else {
                    lineChart3.clear();
                    lineChart3.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showChart1(ArrayList<Entry> data1) {
        lineDataSet1.setValues(data1);
        lineDataSet1.setLabel("Data1");
        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet1);
        lineData1 = new LineData(iLineDataSets);
        lineChart1.clear();
        lineChart1.setData(lineData1);
        lineChart1.invalidate();
        lineDataSet1.setDrawIcons(false);
        lineDataSet1.enableDashedLine(10f, 5f, 0f);
        lineDataSet1.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet1.setColor(Color.DKGRAY);
        lineDataSet1.setCircleColor(Color.DKGRAY);
        lineDataSet1.setLineWidth(1f);
        lineDataSet1.setCircleRadius(3f);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setValueTextSize(9f);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setFormLineWidth(1f);
    }
    private void showChart2(ArrayList<Entry> data2) {
        lineDataSet2.setValues(data2);
        lineDataSet2.setLabel("Data2");
        iLineDataSets2.clear();
        iLineDataSets2.add(lineDataSet2);
        lineData2 = new LineData(iLineDataSets2);
        lineChart2.clear();
        lineChart2.setData(lineData2);
        lineChart2.invalidate();
        lineDataSet2.setDrawIcons(false);
        lineDataSet2.enableDashedLine(10f, 5f, 0f);
        lineDataSet2.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet2.setColor(Color.DKGRAY);
        lineDataSet2.setCircleColor(Color.DKGRAY);
        lineDataSet2.setLineWidth(1f);
        lineDataSet2.setCircleRadius(3f);
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet2.setValueTextSize(9f);
        lineDataSet2.setDrawFilled(true);
        lineDataSet2.setFormLineWidth(1f);
    }
    private void showChart3(ArrayList<Entry> data3) {
        lineDataSet3.setValues(data3);
        lineDataSet3.setLabel("Data3");
        iLineDataSets3.clear();
        iLineDataSets3.add(lineDataSet3);
        lineData3 = new LineData(iLineDataSets3);
        lineChart3.clear();
        lineChart3.setData(lineData3);
        lineChart3.invalidate();
        lineDataSet3.setDrawIcons(false);
        lineDataSet3.enableDashedLine(10f, 5f, 0f);
        lineDataSet3.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet3.setColor(Color.DKGRAY);
        lineDataSet3.setCircleColor(Color.DKGRAY);
        lineDataSet3.setLineWidth(1f);
        lineDataSet3.setCircleRadius(3f);
        lineDataSet3.setDrawCircleHole(false);
        lineDataSet3.setValueTextSize(9f);
        lineDataSet3.setDrawFilled(true);
        lineDataSet3.setFormLineWidth(1f);
    }
}
