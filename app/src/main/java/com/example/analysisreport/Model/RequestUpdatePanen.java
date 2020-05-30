package com.example.analysisreport.Model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;


public class RequestUpdatePanen implements Serializable {
    private String nberat, nsize, ntanggalpenen;

    public RequestUpdatePanen() {
    }

    public RequestUpdatePanen(String nberat, String nsize, String ntanggalpenen) {
        this.nberat = nberat;
        this.nsize = nsize;
        this.ntanggalpenen = ntanggalpenen;
    }

    public String getNberat() {
        return nberat;
    }

    public void setNberat(String nberat) {
        this.nberat = nberat;
    }

    public String getNsize() {
        return nsize;
    }

    public void setNsize(String nsize) {
        this.nsize = nsize;
    }

    public String getNtanggalpenen() {
        return ntanggalpenen;
    }

    public void setNtanggalpenen(String ntanggalpenen) {
        this.ntanggalpenen = ntanggalpenen;
    }

    @Override
    public String toString() {
        return "RequestUpdatePanen{" +
                "nberat='" + nberat + '\'' +
                ", nsize='" + nsize + '\'' +
                ", ntanggalpenen='" + ntanggalpenen + '\'' +
                '}';
    }
}
