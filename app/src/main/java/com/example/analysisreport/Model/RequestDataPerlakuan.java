package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataPerlakuan implements Serializable {
    private String perlakuan, tanggalperlakuan;

    public RequestDataPerlakuan() {
    }

    public RequestDataPerlakuan(String perlakuan, String tanggalperlakuan) {
        this.perlakuan = perlakuan;
        this.tanggalperlakuan = tanggalperlakuan;
    }

    public String getPerlakuan() {
        return perlakuan;
    }

    public void setPerlakuan(String perlakuan) {
        this.perlakuan = perlakuan;
    }

    public String getTanggalperlakuan() {
        return tanggalperlakuan;
    }

    public void setTanggalperlakuan(String tanggalperlakuan) {
        this.tanggalperlakuan = tanggalperlakuan;
    }

    @Override
    public String toString() {
        return "RequestDataPerlakuan{" +
                "perlakuan='" + perlakuan + '\'' +
                ", tanggalperlakuan='" + tanggalperlakuan + '\'' +
                '}';
    }
}
