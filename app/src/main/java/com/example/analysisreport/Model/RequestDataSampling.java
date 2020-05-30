package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataSampling implements Serializable {
    private String abw, adg, tanggalsampling;

    public RequestDataSampling() {
    }

    public RequestDataSampling(String abw, String adg, String tanggalsampling) {
        this.abw = abw;
        this.adg = adg;
        this.tanggalsampling = tanggalsampling;
    }

    public String getAbw() {
        return abw;
    }

    public void setAbw(String abw) {
        this.abw = abw;
    }

    public String getAdg() {
        return adg;
    }

    public void setAdg(String adg) {
        this.adg = adg;
    }

    public String getTanggalsampling() {
        return tanggalsampling;
    }

    public void setTanggalsampling(String tanggalsampling) {
        this.tanggalsampling = tanggalsampling;
    }

    @Override
    public String toString() {
        return "RequestDataSampling{" +
                "abw='" + abw + '\'' +
                ", adg='" + adg + '\'' +
                ", tanggalpanen='" + tanggalsampling + '\'' +
                '}';
    }
}
