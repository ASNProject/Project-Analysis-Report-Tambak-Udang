package com.example.analysisreport.Model;


import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class ModelDetailAir {
    public String tanggalairs;
    public String tinggiairs;
    public String dopagi;
    public String domalam;
    public String phpagis;
    public String phmalams;
    public String suhus;
    public String cas;
    public String mgs;
    public String no2s;
    public String no3s;
    public String nh3s;
    public String alkalinitass;

    public ModelDetailAir() {
    }

    public ModelDetailAir(String tanggalairs, String tinggiairs, String dopagi, String domalam, String phpagis, String phmalams, String suhus, String cas, String mgs, String no2s, String no3s, String nh3s, String alkalinitass) {
        this.tanggalairs = tanggalairs;
        this.tinggiairs = tinggiairs;
        this.dopagi = dopagi;
        this.domalam = domalam;
        this.phpagis = phpagis;
        this.phmalams = phmalams;
        this.suhus = suhus;
        this.cas = cas;
        this.mgs = mgs;
        this.no2s = no2s;
        this.no3s = no3s;
        this.nh3s = nh3s;
        this.alkalinitass = alkalinitass;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("tanggalairs", tanggalairs);
        result.put("tinggiairs", tinggiairs);
        result.put("dopagi", dopagi);
        result.put("domalam", domalam);
        result.put("phpagis", phpagis);
        result.put("phmalams", phmalams);
        result.put("suhus", suhus );
        result.put("cas", cas);
        result.put("mgs", mgs);
        result.put("no2s", no2s);
        result.put("no3s", no3s);
        result.put("nh3s", nh3s);
        result.put("alkalinitas", alkalinitass);
        return result;
    }
}
