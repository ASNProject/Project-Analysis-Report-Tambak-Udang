package com.example.analysisreport.Model;

public class Report {
    private String DOksigen, nilaiKecerahan, PH, nilaiPerlakuan, nilaiSuhu, nilaiSalinitas, nilaiCatatan, nilaiJumlah, tglPakan, nilaiBerat, nilaiSize, tglPanen, tglPerlakuan, ABW, ADG, tglSampling;

    public Report() {
    }

    public Report(String DOksigen, String nilaiKecerahan, String PH, String nilaiPerlakuan, String nilaiSuhu, String nilaiSalinitas, String nilaiCatatan, String nilaiJumlah, String tglPakan, String nilaiBerat, String nilaiSize, String tglPanen, String tglPerlakuan, String ABW, String ADG, String tglSampling) {
        this.DOksigen = DOksigen;
        this.nilaiKecerahan = nilaiKecerahan;
        this.PH = PH;
        this.nilaiPerlakuan = nilaiPerlakuan;
        this.nilaiSuhu = nilaiSuhu;
        this.nilaiSalinitas = nilaiSalinitas;
        this.nilaiCatatan = nilaiCatatan;
        this.nilaiJumlah = nilaiJumlah;
        this.tglPakan = tglPakan;
        this.nilaiBerat = nilaiBerat;
        this.nilaiSize = nilaiSize;
        this.tglPanen = tglPanen;
        this.tglPerlakuan = tglPerlakuan;
        this.ABW = ABW;
        this.ADG = ADG;
        this.tglSampling = tglSampling;
    }

    public String getDOksigen() {
        return DOksigen;
    }

    public void setDOksigen(String DOksigen) {
        this.DOksigen = DOksigen;
    }

    public String getNilaiKecerahan() {
        return nilaiKecerahan;
    }

    public void setNilaiKecerahan(String nilaiKecerahan) {
        this.nilaiKecerahan = nilaiKecerahan;
    }

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getNilaiPerlakuan() {
        return nilaiPerlakuan;
    }

    public void setNilaiPerlakuan(String nilaiPerlakuan) {
        this.nilaiPerlakuan = nilaiPerlakuan;
    }

    public String getNilaiSuhu() {
        return nilaiSuhu;
    }

    public void setNilaiSuhu(String nilaiSuhu) {
        this.nilaiSuhu = nilaiSuhu;
    }

    public String getNilaiSalinitas() {
        return nilaiSalinitas;
    }

    public void setNilaiSalinitas(String nilaiSalinitas) {
        this.nilaiSalinitas = nilaiSalinitas;
    }

    public String getNilaiCatatan() {
        return nilaiCatatan;
    }

    public void setNilaiCatatan(String nilaiCatatan) {
        this.nilaiCatatan = nilaiCatatan;
    }

    public String getNilaiJumlah() {
        return nilaiJumlah;
    }

    public void setNilaiJumlah(String nilaiJumlah) {
        this.nilaiJumlah = nilaiJumlah;
    }

    public String getTglPakan() {
        return tglPakan;
    }

    public void setTglPakan(String tglPakan) {
        this.tglPakan = tglPakan;
    }

    public String getNilaiBerat() {
        return nilaiBerat;
    }

    public void setNilaiBerat(String nilaiBerat) {
        this.nilaiBerat = nilaiBerat;
    }

    public String getNilaiSize() {
        return nilaiSize;
    }

    public void setNilaiSize(String nilaiSize) {
        this.nilaiSize = nilaiSize;
    }

    public String getTglPanen() {
        return tglPanen;
    }

    public void setTglPanen(String tglPanen) {
        this.tglPanen = tglPanen;
    }

    public String getTglPerlakuan() {
        return tglPerlakuan;
    }

    public void setTglPerlakuan(String tglPerlakuan) {
        this.tglPerlakuan = tglPerlakuan;
    }

    public String getABW() {
        return ABW;
    }

    public void setABW(String ABW) {
        this.ABW = ABW;
    }

    public String getADG() {
        return ADG;
    }

    public void setADG(String ADG) {
        this.ADG = ADG;
    }

    public String getTglSampling() {
        return tglSampling;
    }

    public void setTglSampling(String tglSampling) {
        this.tglSampling = tglSampling;
    }
}
