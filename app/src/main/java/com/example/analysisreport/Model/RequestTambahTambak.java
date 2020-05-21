package com.example.analysisreport.Model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;


public class RequestTambahTambak implements Serializable {

    private String nama;
    private String lokasi;

    public RequestTambahTambak() {
    }

    public RequestTambahTambak(String nama, String lokasi) {
        this.nama = nama;
        this.lokasi = lokasi;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    @Override
    public String toString() {
        return "RequestTambahTambak{" +
                "nama='" + nama + '\'' +
                ", lokasi='" + lokasi + '\'' +
                '}';
    }
}
