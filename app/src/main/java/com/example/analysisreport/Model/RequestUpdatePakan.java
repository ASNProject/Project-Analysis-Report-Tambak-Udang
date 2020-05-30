package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestUpdatePakan implements Serializable {
    private String njumlah, ntanggappakan, ncatatan;

    public RequestUpdatePakan() {
    }

    public RequestUpdatePakan(String njumlah, String ntanggappakan, String ncatatan) {
        this.njumlah = njumlah;
        this.ntanggappakan = ntanggappakan;
        this.ncatatan = ncatatan;
    }

    public String getNjumlah() {
        return njumlah;
    }

    public void setNjumlah(String njumlah) {
        this.njumlah = njumlah;
    }

    public String getNtanggappakan() {
        return ntanggappakan;
    }

    public void setNtanggappakan(String ntanggappakan) {
        this.ntanggappakan = ntanggappakan;
    }

    public String getNcatatan() {
        return ncatatan;
    }

    public void setNcatatan(String ncatatan) {
        this.ncatatan = ncatatan;
    }

    @Override
    public String toString() {
        return "RequestUpdatePakan{" +
                "njumlah='" + njumlah + '\'' +
                ", ntanggappakan='" + ntanggappakan + '\'' +
                ", ncatatan='" + ncatatan + '\'' +
                '}';
    }
}
