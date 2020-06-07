package com.example.analysisreport.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ModelDetailSampling {
    public String adgmingguan;
    public String biomass;
    public String fcr;
    public String fr;
    public String jumlahtebarsampling;
    public String konsumsifeed;
    public String mbw;
    public String pakanseharisampling;
    public String populasi;
    public String sp;
    public String tanggalsampling;
    public String tanggaltebarsampling;
    public String totalpakansampling;

    public ModelDetailSampling() {
    }

    public ModelDetailSampling(String adgmingguna, String biomass, String fcr, String fr, String jumlahtebarsampling, String konsumsifeed, String mbw, String pakanseharisampling, String populasi, String sp, String tanggalsampling, String tanggaltebarsampling, String totalpakansampling) {
        this.adgmingguan = adgmingguan;
        this.biomass = biomass;
        this.fcr = fcr;
        this.fr = fr;
        this.jumlahtebarsampling = jumlahtebarsampling;
        this.konsumsifeed = konsumsifeed;
        this.mbw = mbw;
        this.pakanseharisampling = pakanseharisampling;
        this.populasi = populasi;
        this.sp = sp;
        this.tanggalsampling = tanggalsampling;
        this.tanggaltebarsampling = tanggaltebarsampling;
        this.totalpakansampling = totalpakansampling;
    }
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("adgmingguan", adgmingguan);
        result.put("biomass", biomass);
        result.put("fcr", fcr);
        result.put("fr", fr);
        result.put("jumlahtebarsampling", jumlahtebarsampling);
        result.put("konsumsifeed", konsumsifeed);
        result.put("mbw", mbw);
        result.put("pakanseharisampling", pakanseharisampling);
        result.put("populasi", populasi);
        result.put("sp", sp);
        result.put("tanggalsampling", tanggalsampling);
        result.put("tanggaltebarsampling", tanggaltebarsampling);
        result.put("totalpakansampling", totalpakansampling);
        return result;
    }
}
