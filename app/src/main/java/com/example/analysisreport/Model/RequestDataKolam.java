package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataKolam implements Serializable {
    private String kolam;
        private String lokasi;
        private String jumlah;
        private String area;

    public RequestDataKolam() {
    }

    public RequestDataKolam(String kolam, String lokasi, String jumlah, String area) {
        this.kolam = kolam;
        this.lokasi = lokasi;
        this.jumlah = jumlah;
        this.area = area;
    }

    public String getKolam() {
        return kolam;
    }

    public void setKolam(String kolam) {
        this.kolam = kolam;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "RequestDataKolam{" +
                "kolam='" + kolam + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", jumlah='" + jumlah + '\'' +
                ", area='" + area + '\'' +
                '}';
    }
}
