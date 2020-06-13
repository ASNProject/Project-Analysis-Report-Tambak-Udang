package com.example.analysisreport.Model;
import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class RequestDataPanen implements Serializable {
    private String tanggalpanen, doc, tonase, abw, size, populasipanen ;

    public RequestDataPanen() {
    }

    public RequestDataPanen(String tanggalpanen, String doc, String tonase, String abw, String size, String populasipanen) {
        this.tanggalpanen = tanggalpanen;
        this.doc = doc;
        this.tonase = tonase;
        this.abw = abw;
        this.size = size;
        this.populasipanen = populasipanen;
    }

    public String getTanggalpanen() {
        return tanggalpanen;
    }

    public void setTanggalpanen(String tanggalpanen) {
        this.tanggalpanen = tanggalpanen;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getTonase() {
        return tonase;
    }

    public void setTonase(String tonase) {
        this.tonase = tonase;
    }

    public String getAbw() {
        return abw;
    }

    public void setAbw(String abw) {
        this.abw = abw;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPopulasipanen() {
        return populasipanen;
    }

    public void setPopulasipanen(String populasipanen) {
        this.populasipanen = populasipanen;
    }

    @Override
    public String toString() {
        return "RequestDataPanen{" +
                "tanggalpanen='" + tanggalpanen + '\'' +
                ", doc='" + doc + '\'' +
                ", tonase='" + tonase + '\'' +
                ", abw='" + abw + '\'' +
                ", size='" + size + '\'' +
                ", populasipanen='" + populasipanen + '\'' +
                '}';
    }
}
