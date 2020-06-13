package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataSampling implements Serializable {
    private String tanggaltebarsampling, tanggalsampling, jumlahtebarsamplings, mbw, pakanseharisampling, totalpakansampling, fr, populasi, adgmingguan, biomass, sp, konsumsifeed, fcr, usia;

    public RequestDataSampling() {
    }

    public RequestDataSampling(String tanggaltebarsampling, String tanggalsampling, String jumlahtebarsamplings, String mbw, String pakanseharisampling, String totalpakansampling, String fr, String populasi, String adgmingguan, String biomass, String sp, String konsumsifeed, String fcr, String usia) {
        this.tanggaltebarsampling = tanggaltebarsampling;
        this.tanggalsampling = tanggalsampling;
        this.jumlahtebarsamplings = jumlahtebarsamplings;
        this.mbw = mbw;
        this.pakanseharisampling = pakanseharisampling;
        this.totalpakansampling = totalpakansampling;
        this.fr = fr;
        this.populasi = populasi;
        this.adgmingguan = adgmingguan;
        this.biomass = biomass;
        this.sp = sp;
        this.konsumsifeed = konsumsifeed;
        this.fcr = fcr;
        this.usia = usia;
    }

    public String getTanggaltebarsampling() {
        return tanggaltebarsampling;
    }

    public void setTanggaltebarsampling(String tanggaltebarsampling) {
        this.tanggaltebarsampling = tanggaltebarsampling;
    }

    public String getTanggalsampling() {
        return tanggalsampling;
    }

    public void setTanggalsampling(String tanggalsampling) {
        this.tanggalsampling = tanggalsampling;
    }

    public String getJumlahtebarsamplings() {
        return jumlahtebarsamplings;
    }

    public void setJumlahtebarsamplings(String jumlahtebarsamplings) {
        this.jumlahtebarsamplings = jumlahtebarsamplings;
    }

    public String getMbw() {
        return mbw;
    }

    public void setMbw(String mbw) {
        this.mbw = mbw;
    }

    public String getPakanseharisampling() {
        return pakanseharisampling;
    }

    public void setPakanseharisampling(String pakanseharisampling) {
        this.pakanseharisampling = pakanseharisampling;
    }

    public String getTotalpakansampling() {
        return totalpakansampling;
    }

    public void setTotalpakansampling(String totalpakansampling) {
        this.totalpakansampling = totalpakansampling;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getPopulasi() {
        return populasi;
    }

    public void setPopulasi(String populasi) {
        this.populasi = populasi;
    }

    public String getAdgmingguan() {
        return adgmingguan;
    }

    public void setAdgmingguan(String adgmingguan) {
        this.adgmingguan = adgmingguan;
    }

    public String getBiomass() {
        return biomass;
    }

    public void setBiomass(String biomass) {
        this.biomass = biomass;
    }

    public String getSp() {
        return sp;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public String getKonsumsifeed() {
        return konsumsifeed;
    }

    public void setKonsumsifeed(String konsumsifeed) {
        this.konsumsifeed = konsumsifeed;
    }

    public String getFcr() {
        return fcr;
    }

    public void setFcr(String fcr) {
        this.fcr = fcr;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    @Override
    public String toString() {
        return "RequestDataSampling{" +
                "tanggaltebarsampling='" + tanggaltebarsampling + '\'' +
                ", tanggalsampling='" + tanggalsampling + '\'' +
                ", jumlahtebarsamplings='" + jumlahtebarsamplings + '\'' +
                ", mbw='" + mbw + '\'' +
                ", pakanseharisampling='" + pakanseharisampling + '\'' +
                ", totalpakansampling='" + totalpakansampling + '\'' +
                ", fr='" + fr + '\'' +
                ", populasi='" + populasi + '\'' +
                ", adgmingguan='" + adgmingguan + '\'' +
                ", biomass='" + biomass + '\'' +
                ", sp='" + sp + '\'' +
                ", konsumsifeed='" + konsumsifeed + '\'' +
                ", fcr='" + fcr + '\'' +
                ", usia='" + usia + '\'' +
                '}';
    }
}
