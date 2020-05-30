package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestUpdateAir implements Serializable {
    private String ndoksigen, nph, nkecerahan, nsalinitas, nsuhu;

    public RequestUpdateAir() {
    }

    public RequestUpdateAir(String ndoksigen, String nph, String nkecerahan, String nsalinitas, String nsuhu) {
        this.ndoksigen = ndoksigen;
        this.nph = nph;
        this.nkecerahan = nkecerahan;
        this.nsalinitas = nsalinitas;
        this.nsuhu = nsuhu;
    }

    public String getNdoksigen() {
        return ndoksigen;
    }

    public void setNdoksigen(String ndoksigen) {
        this.ndoksigen = ndoksigen;
    }

    public String getNph() {
        return nph;
    }

    public void setNph(String nph) {
        this.nph = nph;
    }

    public String getNkecerahan() {
        return nkecerahan;
    }

    public void setNkecerahan(String nkecerahan) {
        this.nkecerahan = nkecerahan;
    }

    public String getNsalinitas() {
        return nsalinitas;
    }

    public void setNsalinitas(String nsalinitas) {
        this.nsalinitas = nsalinitas;
    }

    public String getNsuhu() {
        return nsuhu;
    }

    public void setNsuhu(String nsuhu) {
        this.nsuhu = nsuhu;
    }

    @Override
    public String toString() {
        return "RequestUpdateAir{" +
                "ndoksigen='" + ndoksigen + '\'' +
                ", nph='" + nph + '\'' +
                ", nkecerahan='" + nkecerahan + '\'' +
                ", nsalinitas='" + nsalinitas + '\'' +
                ", nsuhu='" + nsuhu + '\'' +
                '}';
    }
}
