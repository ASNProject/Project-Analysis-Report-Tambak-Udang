package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataAir implements Serializable {
    private String doksigen, ph, kecerahan, salinitas, suhu;

    public RequestDataAir() {
    }

    public RequestDataAir(String doksigen, String ph, String kecerahan, String salinitas, String suhu) {
        this.doksigen = doksigen;
        this.ph = ph;
        this.kecerahan = kecerahan;
        this.salinitas = salinitas;
        this.suhu = suhu;
    }

    public String getDoksigen() {
        return doksigen;
    }

    public void setDoksigen(String doksigen) {
        this.doksigen = doksigen;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getKecerahan() {
        return kecerahan;
    }

    public void setKecerahan(String kecerahan) {
        this.kecerahan = kecerahan;
    }

    public String getSalinitas() {
        return salinitas;
    }

    public void setSalinitas(String salinitas) {
        this.salinitas = salinitas;
    }

    public String getSuhu() {
        return suhu;
    }

    public void setSuhu(String suhu) {
        this.suhu = suhu;
    }

    @Override
    public String toString() {
        return "RequestDataAir{" +
                "doksigen='" + doksigen + '\'' +
                ", ph='" + ph + '\'' +
                ", kecerahan='" + kecerahan + '\'' +
                ", salinitas='" + salinitas + '\'' +
                ", suhu='" + suhu + '\'' +
                '}';
    }
}
