package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataPakan implements Serializable {

    private String jumlah, tanggappakan, catatan;

    public RequestDataPakan() {
    }

    public RequestDataPakan(String jumlah, String tanggappakan, String catatan) {
        this.jumlah = jumlah;
        this.tanggappakan = tanggappakan;
        this.catatan = catatan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggappakan() {
        return tanggappakan;
    }

    public void setTanggappakan(String tanggappakan) {
        this.tanggappakan = tanggappakan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    @Override
    public String toString() {
        return "RequestDataPakan{" +
                "jumlah='" + jumlah + '\'' +
                ", tanggappakan='" + tanggappakan + '\'' +
                ", catatan='" + catatan + '\'' +
                '}';
    }
}
