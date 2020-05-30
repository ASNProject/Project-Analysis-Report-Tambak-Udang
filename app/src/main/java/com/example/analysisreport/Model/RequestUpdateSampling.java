package com.example.analysisreport.Model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestUpdateSampling implements Serializable{
    private String nabw, nadg, ntanggalsampling;

    public RequestUpdateSampling() {
    }

    public RequestUpdateSampling(String nabw, String nadg, String ntanggalsampling) {
        this.nabw = nabw;
        this.nadg = nadg;
        this.ntanggalsampling = ntanggalsampling;
    }

    public String getNabw() {
        return nabw;
    }

    public void setNabw(String nabw) {
        this.nabw = nabw;
    }

    public String getNadg() {
        return nadg;
    }

    public void setNadg(String nadg) {
        this.nadg = nadg;
    }

    public String getNtanggalsampling() {
        return ntanggalsampling;
    }

    public void setNtanggalsampling(String ntanggalsampling) {
        this.ntanggalsampling = ntanggalsampling;
    }

    @Override
    public String toString() {
        return "RequestUpdateSampling{" +
                "nabw='" + nabw + '\'' +
                ", nadg='" + nadg + '\'' +
                ", ntanggalsampling='" + ntanggalsampling + '\'' +
                '}';
    }
}
