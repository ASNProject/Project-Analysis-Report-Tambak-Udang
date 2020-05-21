package com.example.analysisreport.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class LISTTambak {
    public String nama;

    public LISTTambak() {
    }

    public LISTTambak(String nama) {
        this.nama = nama;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("nama", nama);
        return result;
    }

}
