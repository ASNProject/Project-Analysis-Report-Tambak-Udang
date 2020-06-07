package com.example.analysisreport.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ModelDataPakan {
    public String jam10;
    public String jam14;
    public String jam18;
    public String jam22;
    public String jam6;
    public String jumlahharian;
    public String jumlahtotal;
    public String keteranganpakan;
    public String kodepakan;
    public String tanggalpakan;

    public ModelDataPakan() {
    }

    public ModelDataPakan(String jam10, String jam14, String jam18, String jam22, String jam6, String jumlahharian, String jumlahtotal, String keteranganpakan, String kodepakan, String tanggalpakan) {
        this.jam10 = jam10;
        this.jam14 = jam14;
        this.jam18 = jam18;
        this.jam22 = jam22;
        this.jam6 = jam6;
        this.jumlahharian = jumlahharian;
        this.jumlahtotal = jumlahtotal;
        this.keteranganpakan = keteranganpakan;
        this.kodepakan = kodepakan;
        this.tanggalpakan = tanggalpakan;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("jam10", jam10);
        result.put("jam14", jam14);
        result.put("jam18", jam18);
        result.put("jam22", jam22);
        result.put("jam6", jam6);
        result.put("jumlahharian", jumlahharian);
        result.put("jumlahtotal", jumlahtotal );
        result.put("keteranganpakan", keteranganpakan);
        result.put("kodepakan", kodepakan);
        result.put("tanggalpakan", tanggalpakan);
        return result;
    }
}
