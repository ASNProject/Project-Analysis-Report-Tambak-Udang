package com.example.analysisreport.Chart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.analysisreport.Model.RequestDataPakan;
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

public class ChartPakan extends AppCompatActivity {
    LineChart lineChart;
    LineDataSet lineDataSet = new LineDataSet(null, null);
    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    SharePreference sessions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_pakan);
        sessions = new SharePreference(ChartPakan.this.getApplicationContext());
        lineChart = findViewById(R.id.chratpakan);
        lineChart.setTouchEnabled(true);
        lineChart.setPinchZoom(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference().child(sessions.getDatas()).child(sessions.getDetailkolam()).child("Pakan");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Entry> dataValas = new ArrayList<Entry>();

                if(dataSnapshot.hasChildren()){
                    for(DataSnapshot myDataSnapshot : dataSnapshot.getChildren()){
                        //DataPointPakan dataPointPakan = myDataSnapshot.getValue(DataPointPakan.class);
                        RequestDataPakan requestDataPakan =myDataSnapshot.getValue(RequestDataPakan.class);
                        int xValue = Integer.parseInt(String.valueOf(requestDataPakan.getUsia()));
                        int yValue = Integer.parseInt(String.valueOf(requestDataPakan.getJumlahharian()));
                        dataValas.add(new Entry(xValue, yValue));
                    }
                    showChart(dataValas);
                }
                else {
                    lineChart.clear();
                    lineChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showChart(ArrayList<Entry> dataValas) {
        lineDataSet.setValues(dataValas);
        lineDataSet.setLabel("Data");
        iLineDataSets.clear();;
        iLineDataSets.add(lineDataSet);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();
        lineDataSet.setDrawIcons(false);
        lineDataSet.enableDashedLine(10f, 5f, 0f);
        lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
        lineDataSet.setColor(Color.DKGRAY);
        lineDataSet.setCircleColor(Color.DKGRAY);
        lineDataSet.setLineWidth(1f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(9f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
    }
}
