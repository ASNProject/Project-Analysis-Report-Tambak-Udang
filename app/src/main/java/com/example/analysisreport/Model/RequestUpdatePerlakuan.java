package com.example.analysisreport.Model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestUpdatePerlakuan implements Serializable {
    private String nperlakuan, ntanggalperlakuan;

    public RequestUpdatePerlakuan() {
    }

    public RequestUpdatePerlakuan(String nperlakuan, String ntanggalperlakuan) {
        this.nperlakuan = nperlakuan;
        this.ntanggalperlakuan = ntanggalperlakuan;
    }

    public String getNperlakuan() {
        return nperlakuan;
    }

    public void setNperlakuan(String nperlakuan) {
        this.nperlakuan = nperlakuan;
    }

    public String getNtanggalperlakuan() {
        return ntanggalperlakuan;
    }

    public void setNtanggalperlakuan(String ntanggalperlakuan) {
        this.ntanggalperlakuan = ntanggalperlakuan;
    }

    @Override
    public String toString() {
        return "RequestUpdatePerlakuan{" +
                "nperlakuan='" + nperlakuan + '\'' +
                ", ntanggalperlakuan='" + ntanggalperlakuan + '\'' +
                '}';
    }
}
