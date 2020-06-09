package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataKolam implements Serializable {
    private String kolam, namapetani, jumlah, area, tanggaltebar, jumlahtebarsampling, jumlahtebarratarata,
                    kepadatankolam, keterangankolams, feedpakan;

    public RequestDataKolam() {
    }

    public RequestDataKolam(String kolam, String namapetani, String jumlah, String area, String tanggaltebar, String jumlahtebarsampling, String jumlahtebarratarata, String kepadatankolam, String keterangankolams, String feedpakan) {
        this.kolam = kolam;
        this.namapetani = namapetani;
        this.jumlah = jumlah;
        this.area = area;
        this.tanggaltebar = tanggaltebar;
        this.jumlahtebarsampling = jumlahtebarsampling;
        this.jumlahtebarratarata = jumlahtebarratarata;
        this.kepadatankolam = kepadatankolam;
        this.keterangankolams = keterangankolams;
        this.feedpakan = feedpakan;
    }

    public String getKolam() {
        return kolam;
    }

    public void setKolam(String kolam) {
        this.kolam = kolam;
    }

    public String getNamapetani() {
        return namapetani;
    }

    public void setNamapetani(String namapetani) {
        this.namapetani = namapetani;
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

    public String getTanggaltebar() {
        return tanggaltebar;
    }

    public void setTanggaltebar(String tanggaltebar) {
        this.tanggaltebar = tanggaltebar;
    }

    public String getJumlahtebarsampling() {
        return jumlahtebarsampling;
    }

    public void setJumlahtebarsampling(String jumlahtebarsampling) {
        this.jumlahtebarsampling = jumlahtebarsampling;
    }

    public String getJumlahtebarratarata() {
        return jumlahtebarratarata;
    }

    public void setJumlahtebarratarata(String jumlahtebarratarata) {
        this.jumlahtebarratarata = jumlahtebarratarata;
    }

    public String getKepadatankolam() {
        return kepadatankolam;
    }

    public void setKepadatankolam(String kepadatankolam) {
        this.kepadatankolam = kepadatankolam;
    }

    public String getKeterangankolams() {
        return keterangankolams;
    }

    public void setKeterangankolams(String keterangankolams) {
        this.keterangankolams = keterangankolams;
    }

    public String getFeedpakan() {
        return feedpakan;
    }

    public void setFeedpakan(String feedpakan) {
        this.feedpakan = feedpakan;
    }

    @Override
    public String toString() {
        return "RequestDataKolam{" +
                "kolam='" + kolam + '\'' +
                ", namapetani='" + namapetani + '\'' +
                ", jumlah='" + jumlah + '\'' +
                ", area='" + area + '\'' +
                ", tanggaltebar='" + tanggaltebar + '\'' +
                ", jumlahtebarsampling='" + jumlahtebarsampling + '\'' +
                ", jumlahtebarratarata='" + jumlahtebarratarata + '\'' +
                ", kepadatankolam='" + kepadatankolam + '\'' +
                ", keterangankolams='" + keterangankolams + '\'' +
                ", feedpakan='" + feedpakan + '\'' +
                '}';
    }
}
