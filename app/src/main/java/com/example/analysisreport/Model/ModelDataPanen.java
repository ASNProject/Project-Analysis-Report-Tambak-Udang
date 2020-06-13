package com.example.analysisreport.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ModelDataPanen {
    public String tanggalpanen, doc, tonase, abw, size, populasipanen ;

    public ModelDataPanen() {
    }

    public ModelDataPanen(String tanggalpanen, String doc, String tonase, String abw, String size, String populasipanen) {
        this.tanggalpanen = tanggalpanen;
        this.doc = doc;
        this.tonase = tonase;
        this.abw = abw;
        this.size = size;
        this.populasipanen = populasipanen;
    }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("tanggalpanen", tanggalpanen);
        result.put("doc", doc);
        result.put("tonase", tonase);
        result.put("abw", abw);
        result.put("size", size);
        result.put("populasipanen", populasipanen);
        return result;
    }
}
