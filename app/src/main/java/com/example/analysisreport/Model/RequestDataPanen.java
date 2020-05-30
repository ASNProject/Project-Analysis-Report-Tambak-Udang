package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataPanen implements Serializable {
    private String berat, size, tanggalpenen;

    public RequestDataPanen() {
    }

    public RequestDataPanen(String berat, String size, String tanggalpenen) {
        this.berat = berat;
        this.size = size;
        this.tanggalpenen = tanggalpenen;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getTanggalpenen() {
        return tanggalpenen;
    }

    public void setTanggalpenen(String tanggalpenen) {
        this.tanggalpenen = tanggalpenen;
    }

    @Override
    public String toString() {
        return "RequestDataPanen{" +
                "berat='" + berat + '\'' +
                ", size='" + size + '\'' +
                ", tanggalpenen='" + tanggalpenen + '\'' +
                '}';
    }
}
