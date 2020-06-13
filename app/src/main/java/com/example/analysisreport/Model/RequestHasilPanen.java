package com.example.analysisreport.Model;

public class RequestHasilPanen {
    private String tonha, totalpopulasi, panentotal, totalsr, totalpakan, fcrtotal;

    public RequestHasilPanen() {
    }

    public RequestHasilPanen(String tonha, String totalpopulasi, String panentotal, String totalsr, String totalpakan, String fcrtotal) {
        this.tonha = tonha;
        this.totalpopulasi = totalpopulasi;
        this.panentotal = panentotal;
        this.totalsr = totalsr;
        this.totalpakan = totalpakan;
        this.fcrtotal = fcrtotal;
    }

    public String getTonha() {
        return tonha;
    }

    public void setTonha(String tonha) {
        this.tonha = tonha;
    }

    public String getTotalpopulasi() {
        return totalpopulasi;
    }

    public void setTotalpopulasi(String totalpopulasi) {
        this.totalpopulasi = totalpopulasi;
    }

    public String getPanentotal() {
        return panentotal;
    }

    public void setPanentotal(String panentotal) {
        this.panentotal = panentotal;
    }

    public String getTotalsr() {
        return totalsr;
    }

    public void setTotalsr(String totalsr) {
        this.totalsr = totalsr;
    }

    public String getTotalpakan() {
        return totalpakan;
    }

    public void setTotalpakan(String totalpakan) {
        this.totalpakan = totalpakan;
    }

    public String getFcrtotal() {
        return fcrtotal;
    }

    public void setFcrtotal(String fcrtotal) {
        this.fcrtotal = fcrtotal;
    }

    @Override
    public String toString() {
        return "RequestHasilPanen{" +
                "tonha='" + tonha + '\'' +
                ", totalpopulasi='" + totalpopulasi + '\'' +
                ", panentotal='" + panentotal + '\'' +
                ", totalsr='" + totalsr + '\'' +
                ", totalpakan='" + totalpakan + '\'' +
                ", fcrtotal='" + fcrtotal + '\'' +
                '}';
    }
}
