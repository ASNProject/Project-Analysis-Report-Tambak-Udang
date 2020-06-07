package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataAir implements Serializable {
    private String tanggalairs, tinggiairs, dopagi, domalam, phpagis, phmalams, kecerahans, alkalinitass,
                    suhus, cas, mgs, no2s, no3s, nh3s;
    private String key;

    public RequestDataAir() {
    }

    public RequestDataAir(String tanggalairs, String tinggiairs, String dopagi, String domalam, String phpagis, String phmalams, String kecerahans, String alkalinitass, String suhus, String cas, String mgs, String no2s, String no3s, String nh3s) {
        this.tanggalairs = tanggalairs;
        this.tinggiairs = tinggiairs;
        this.dopagi = dopagi;
        this.domalam = domalam;
        this.phpagis = phpagis;
        this.phmalams = phmalams;
        this.kecerahans = kecerahans;
        this.alkalinitass = alkalinitass;
        this.suhus = suhus;
        this.cas = cas;
        this.mgs = mgs;
        this.no2s = no2s;
        this.no3s = no3s;
        this.nh3s = nh3s;
    }

    public String getTanggalairs() {
        return tanggalairs;
    }

    public void setTanggalairs(String tanggalairs) {
        this.tanggalairs = tanggalairs;
    }

    public String getTinggiairs() {
        return tinggiairs;
    }

    public void setTinggiairs(String tinggiairs) {
        this.tinggiairs = tinggiairs;
    }

    public String getDopagi() {
        return dopagi;
    }

    public void setDopagi(String dopagi) {
        this.dopagi = dopagi;
    }

    public String getDomalam() {
        return domalam;
    }

    public void setDomalam(String domalam) {
        this.domalam = domalam;
    }

    public String getPhpagis() {
        return phpagis;
    }

    public void setPhpagis(String phpagis) {
        this.phpagis = phpagis;
    }

    public String getPhmalams() {
        return phmalams;
    }

    public void setPhmalams(String phmalams) {
        this.phmalams = phmalams;
    }

    public String getKecerahans() {
        return kecerahans;
    }

    public void setKecerahans(String kecerahans) {
        this.kecerahans = kecerahans;
    }

    public String getAlkalinitass() {
        return alkalinitass;
    }

    public void setAlkalinitass(String alkalinitass) {
        this.alkalinitass = alkalinitass;
    }

    public String getSuhus() {
        return suhus;
    }

    public void setSuhus(String suhus) {
        this.suhus = suhus;
    }

    public String getCas() {
        return cas;
    }

    public void setCas(String cas) {
        this.cas = cas;
    }

    public String getMgs() {
        return mgs;
    }

    public void setMgs(String mgs) {
        this.mgs = mgs;
    }

    public String getNo2s() {
        return no2s;
    }

    public void setNo2s(String no2s) {
        this.no2s = no2s;
    }

    public String getNo3s() {
        return no3s;
    }

    public void setNo3s(String no3s) {
        this.no3s = no3s;
    }

    public String getNh3s() {
        return nh3s;
    }

    public void setNh3s(String nh3s) {
        this.nh3s = nh3s;
    }

    @Override
    public String toString() {
        return "RequestDataAir{" +
                "tanggalairs='" + tanggalairs + '\'' +
                ", tinggiairs='" + tinggiairs + '\'' +
                ", dopagi='" + dopagi + '\'' +
                ", domalam='" + domalam + '\'' +
                ", phpagis='" + phpagis + '\'' +
                ", phmalams='" + phmalams + '\'' +
                ", kecerahans='" + kecerahans + '\'' +
                ", alkalinitass='" + alkalinitass + '\'' +
                ", suhus='" + suhus + '\'' +
                ", cas='" + cas + '\'' +
                ", mgs='" + mgs + '\'' +
                ", no2s='" + no2s + '\'' +
                ", no3s='" + no3s + '\'' +
                ", nh3s='" + nh3s + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
