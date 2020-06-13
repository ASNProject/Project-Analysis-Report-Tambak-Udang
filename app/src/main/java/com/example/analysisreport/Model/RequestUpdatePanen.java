package com.example.analysisreport.Model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;


public class RequestUpdatePanen implements Serializable {
    private String utanggalpanen, udoc, utonase, uabw, usize, upopulasipanen ;

    public RequestUpdatePanen() {
    }

    public RequestUpdatePanen(String utanggalpanen, String udoc, String utonase, String uabw, String usize, String upopulasipanen) {
        this.utanggalpanen = utanggalpanen;
        this.udoc = udoc;
        this.utonase = utonase;
        this.uabw = uabw;
        this.usize = usize;
        this.upopulasipanen = upopulasipanen;
    }

    public String getUtanggalpanen() {
        return utanggalpanen;
    }

    public void setUtanggalpanen(String utanggalpanen) {
        this.utanggalpanen = utanggalpanen;
    }

    public String getUdoc() {
        return udoc;
    }

    public void setUdoc(String udoc) {
        this.udoc = udoc;
    }

    public String getUtonase() {
        return utonase;
    }

    public void setUtonase(String utonase) {
        this.utonase = utonase;
    }

    public String getUabw() {
        return uabw;
    }

    public void setUabw(String uabw) {
        this.uabw = uabw;
    }

    public String getUsize() {
        return usize;
    }

    public void setUsize(String usize) {
        this.usize = usize;
    }

    public String getUpopulasipanen() {
        return upopulasipanen;
    }

    public void setUpopulasipanen(String upopulasipanen) {
        this.upopulasipanen = upopulasipanen;
    }

    @Override
    public String toString() {
        return "RequestUpdatePanen{" +
                "utanggalpanen='" + utanggalpanen + '\'' +
                ", udoc='" + udoc + '\'' +
                ", utonase='" + utonase + '\'' +
                ", uabw='" + uabw + '\'' +
                ", usize='" + usize + '\'' +
                ", upopulasipanen='" + upopulasipanen + '\'' +
                '}';
    }
}
